package me.thomas.skyblock.helpers;

import org.bukkit.inventory.*;
import java.nio.charset.*;
import org.bukkit.persistence.*;
import java.io.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.configuration.serialization.*;

public class StoreItemStack implements PersistentDataType<byte[], ItemStack> {

    private Charset charset;
    public StoreItemStack() {
        this.charset = Charset.defaultCharset();
    }

    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    public Class<ItemStack> getComplexType() {
        return ItemStack.class;
    }

    public byte[] toPrimitive(final ItemStack items, final PersistentDataAdapterContext itemTagAdapterContext) {
        return this.itemsToString(items).getBytes(this.charset);
    }

    public ItemStack fromPrimitive(final byte[] bytes, final PersistentDataAdapterContext itemTagAdapterContext) {
        return this.stringToItems(new String(bytes, this.charset));
    }

    private String itemsToString(final ItemStack items) {
        try {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this.serializeItemStack(items));
            oos.flush();
            return printBase64Binary(bos.toByteArray());
        }
        catch (Exception ex) {
            return "";
        }
    }

    private ItemStack stringToItems(final String s) {
        try {
            final ByteArrayInputStream bis = new ByteArrayInputStream(parseBase64Binary(s));
            final ObjectInputStream ois = new ObjectInputStream(bis);
            return this.deserializeItemStack((Map<String, Object>) ois.readObject());
        }
        catch (Exception ex) {
            return new ItemStack(Material.AIR);
        }
    }

    private Map<String, Object> serializeItemStack(ItemStack item) {
        Map<String, Object> result = item.serialize();
        if (item.hasItemMeta()) {
            result.put("meta", item.getItemMeta().serialize());
        }
        return result;
    }

    private ItemStack deserializeItemStack(Map<String, Object> map) {
        ItemStack items = null;
        try {
            if (map.containsKey("meta")) {
                Map<String, Object> im = new HashMap<>((Integer) map.remove("meta"));
                im.put("==", "ItemMeta");
                ItemStack is = ItemStack.deserialize(map);
                is.setItemMeta((ItemMeta)ConfigurationSerialization.deserializeObject(im));
            }
            else {
                items = ItemStack.deserialize(map);
            }
        }
        catch (Exception ignored) {
        }
        return items;
    }

    private static int guessLength(final String text) {
        final int len = text.length();
        int j = len - 1;
        while (j >= 0) {
            final byte code = decodeMap()[text.charAt(j)];
            if (code == 127) {
                --j;
            }
            else {
                if (code == -1) {
                    return text.length() / 4 * 3;
                }
                break;
            }
        }
        ++j;
        final int padSize = len - j;
        if (padSize > 2) {
            return text.length() / 4 * 3;
        }
        return text.length() / 4 * 3 - padSize;
    }

    private static byte[] decodeMap() {
        final byte[] map = new byte[128];
        for (int i = 0; i < 128; ++i) {
            map[i] = -1;
        }
        for (int i = 65; i <= 90; ++i) {
            map[i] = (byte)(i - 65);
        }
        for (int i = 97; i <= 122; ++i) {
            map[i] = (byte)(i - 97 + 26);
        }
        for (int i = 48; i <= 57; ++i) {
            map[i] = (byte)(i - 48 + 52);
        }
        map[43] = 62;
        map[47] = 63;
        map[61] = 127;
        return map;
    }

    private static byte[] parseBase64Binary(final String text) {
        final int buflen = guessLength(text);
        final byte[] out = new byte[buflen];
        int o = 0;
        final int len = text.length();
        final byte[] quadruplet = new byte[4];
        int q = 0;
        for (int i = 0; i < len; ++i) {
            final char ch = text.charAt(i);
            final byte v = decodeMap()[ch];
            if (v != -1) {
                quadruplet[q++] = v;
            }
            if (q == 4) {
                out[o++] = (byte)(quadruplet[0] << 2 | quadruplet[1] >> 4);
                if (quadruplet[2] != 127) {
                    out[o++] = (byte)(quadruplet[1] << 4 | quadruplet[2] >> 2);
                }
                if (quadruplet[3] != 127) {
                    out[o++] = (byte)(quadruplet[2] << 6 | quadruplet[3]);
                }
                q = 0;
            }
        }
        return out;
    }

    private static String printBase64Binary(final byte[] input) {
        return printBase64Binary(input, 0, input.length);
    }

    private static String printBase64Binary(final byte[] input, final int offset, final int len) {
        final char[] buf = new char[(len + 2) / 3 * 4];
        final int ptr = printBase64Binary(input, offset, len, buf, 0);
        assert ptr == buf.length;
        return new String(buf);
    }

    private static int printBase64Binary(final byte[] input, final int offset, final int len, final char[] buf, int ptr) {
        int remaining;
        int i;
        for (remaining = len, i = offset; remaining >= 3; remaining -= 3, i += 3) {
            buf[ptr++] = encode(input[i] >> 2);
            buf[ptr++] = encode((input[i] & 0x3) << 4 | (input[i + 1] >> 4 & 0xF));
            buf[ptr++] = encode((input[i + 1] & 0xF) << 2 | (input[i + 2] >> 6 & 0x3));
            buf[ptr++] = encode(input[i + 2] & 0x3F);
        }
        if (remaining == 1) {
            buf[ptr++] = encode(input[i] >> 2);
            buf[ptr++] = encode((input[i] & 0x3) << 4);
            buf[ptr++] = '=';
            buf[ptr++] = '=';
        }
        if (remaining == 2) {
            buf[ptr++] = encode(input[i] >> 2);
            buf[ptr++] = encode((input[i] & 0x3) << 4 | (input[i + 1] >> 4 & 0xF));
            buf[ptr++] = encode((input[i + 1] & 0xF) << 2);
            buf[ptr++] = '=';
        }
        return ptr;
    }

    private static char encode(final int i) {
        return encodeMap()[i & 0x3F];
    }

    private static char[] encodeMap() {
        final char[] map = new char[64];
        for (int i = 0; i < 26; ++i) {
            map[i] = (char)(65 + i);
        }
        for (int i = 26; i < 52; ++i) {
            map[i] = (char)(97 + (i - 26));
        }
        for (int i = 52; i < 62; ++i) {
            map[i] = (char)(48 + (i - 52));
        }
        map[62] = '+';
        map[63] = '/';
        return map;
    }
}
