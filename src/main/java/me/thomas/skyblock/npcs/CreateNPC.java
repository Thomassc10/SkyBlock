package me.thomas.skyblock.npcs;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class CreateNPC {

    private final String name;
    private final String skin;
    public CreateNPC(String name, String skin){
        this.name = name;
        this.skin = skin;
    }

    public void create(Player player) throws InvocationTargetException {
        EntityPlayer craftPlayer = ((CraftPlayer) player).getHandle();

        // textures
        Property textures = (Property) craftPlayer.getProfile().getProperties().get("textures").toArray()[0];
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        gameProfile.getProperties().put("textures", new Property("textures", skin, textures.getSignature()));

        // create
        EntityPlayer entityPlayer = new EntityPlayer(
                ((CraftServer) Bukkit.getServer()).getServer(),
                ((CraftWorld) player.getWorld()).getHandle(), gameProfile);
        Location loc = player.getLocation();
        entityPlayer.setPosition(loc.getX(), loc.getY(), loc.getZ());

        // send packets
        for (Player p : Bukkit.getOnlinePlayers()){
            /*PlayerConnection playerConnection = ((CraftPlayer) p).getHandle().b;
            playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));*/
            //SkyBlock.getInstance().getProtocolManager().sendServerPacket(p, new PacketContainer(PacketType.Play.Server.NAMED_ENTITY_SPAWN));
        }
    }

    public String getName() {
        return name;
    }

    public String getSkin() {
        return skin;
    }
}
