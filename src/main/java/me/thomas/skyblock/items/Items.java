package me.thomas.skyblock.items;

import me.thomas.skyblock.helpers.AbilityType;
import me.thomas.skyblock.helpers.SbRarity;
import me.thomas.skyblock.helpers.StatType;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.bows.*;
import me.thomas.skyblock.items.powerorbs.ManaFluxPowerOrb;
import me.thomas.skyblock.items.powerorbs.OverfluxPowerOrb;
import me.thomas.skyblock.items.powerorbs.PlasmafluxPowerOrb;
import me.thomas.skyblock.items.powerorbs.RadiantPowerOrb;
import me.thomas.skyblock.items.specialitems.DctrsSpaceHelmet;
import me.thomas.skyblock.items.swords.*;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Items {

    private static final Map<String, SbItem> items = new HashMap<>();
    private static final Map<String, SbArmor> armors = new HashMap<>();
    private static final Map<String, SbArmor[]> sets = new HashMap<>();

    public static Map<String, SbItem> getSbItems(){
        return items;
    }

    public static Map<String, SbArmor> getSbArmors() {
        return armors;
    }

    public static Map<String, SbArmor[]> getSets() {
        return sets;
    }

    public static SbItem getSbItemByKey(String key) {
        try {
            return items.get(key);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static SbItem getSbItem(ItemStack item) {
        if (item == null || !Utils.isSbItem(item)) return null;
        String key = Utils.getStringFromItem(item, "item_key");
        if (items.containsKey(key)) {
            SbItem sbItem = items.get(key);
            ItemMeta meta = item.getItemMeta();
            Material type = item.getType();
            String name = meta.getDisplayName();
            int damage = Utils.getLoreStat(item, StatType.DAMAGE);
            int strenght = Utils.getLoreStat(item, StatType.STRENGHT);
            int critChance = Utils.getLoreStat(item, StatType.CRITICAL_CHANCE);
            int criDamage = Utils.getLoreStat(item, StatType.CRITICAL_DAMAGE);
            int intelligence = Utils.getLoreStat(item, StatType.INTELLIGENCE);
            int speed = Utils.getLoreStat(item, StatType.SPEED);
            int defense = Utils.getLoreStat(item, StatType.DEFENSE);
            int ferocity = Utils.getLoreStat(item, StatType.FEROCITY);
            int swingRange = Utils.getLoreStat(item, StatType.SWING_RANGE);
            String rarity = ChatColor.stripColor(meta.getLore().get(meta.getLore().size() - 1).replace(" ", "_"));
            boolean canBeReforged = meta.getLore().get(meta.getLore().size() - 2).contains("can be reforged");
            return new SbItem(new ItemStack(type), name, damage, strenght, critChance, criDamage, intelligence, speed, defense, ferocity, swingRange, sbItem.getDescription(), sbItem.getAbilities(), canBeReforged, SbRarity.valueOf(rarity));
        }
        return null;
    }

    public static SbArmor getSbArmorByKey(String key) {
        try {
            return armors.get(key);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static SbArmor getSbArmor(ItemStack item) {
        if (item == null || !Utils.isSbItem(item)) return null;
        String key = Utils.getStringFromItem(item, "item_key");
        if (armors.containsKey(key)) {
            SbArmor sbArmor = armors.get(key);
            ItemMeta meta = item.getItemMeta();
            Material type = item.getType();
            String name = meta.getDisplayName();
            int health = Utils.getLoreStat(item, StatType.HEALTH);
            int defense = Utils.getLoreStat(item, StatType.DEFENSE);
            int strenght = Utils.getLoreStat(item, StatType.STRENGHT);
            int critChance = Utils.getLoreStat(item, StatType.CRITICAL_CHANCE);
            int criDamage = Utils.getLoreStat(item, StatType.CRITICAL_DAMAGE);
            int intelligence = Utils.getLoreStat(item, StatType.INTELLIGENCE);
            int speed = Utils.getLoreStat(item, StatType.SPEED);
            double seaCreatureChance = Utils.getLoreStat(item, StatType.SEA_CREATURE_CHANCE);
            String rarity = ChatColor.stripColor(meta.getLore().get(meta.getLore().size() - 1).replace(" ", "_"));
            boolean canBeReforged = meta.getLore().get(meta.getLore().size() - 2).contains("can be reforged");
            return new SbArmor(new ItemStack(type), name, health, defense, strenght, critChance, criDamage, intelligence, speed, seaCreatureChance, sbArmor.getDescription(), sbArmor.getAbilities(), canBeReforged, SbRarity.valueOf(rarity));
        }
        return null;
    }

    public static SbArmor[] getArmorSetByKey(String key) {
        try {
            return sets.get(key);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static void putItem(String key, SbItem sbItem) {
        items.put(key, sbItem);
    }

    public static void putSet(String key, String string) {
        sets.put(key, new SbArmor[]{armors.get(string + "_helmet"), armors.get(string + "_chestplate"), armors.get(string + "_leggings"), armors.get(string + "_boots")});
    }

    // ArmorsSets: +86 - Swords: 86 - Bows: 30
    public static void registerSbItems() {
        // Swords
        items.put("wooden_sword", new SbItem(new ItemStack(Material.WOODEN_SWORD), "Wooden Sword", 20, 0, true, SbRarity.COMMON_SWORD));
        items.put("golden_sword", new SbItem(new ItemStack(Material.GOLDEN_SWORD), "Golden Sword", 20, 0, true, SbRarity.COMMON_SWORD));
        items.put("stone_sword", new SbItem(new ItemStack(Material.STONE_SWORD), "Stone Sword", 25, 0, true, SbRarity.COMMON_SWORD));
        items.put("iron_sword", new SbItem(new ItemStack(Material.IRON_SWORD), "Iron Sword", 30, 0, true, SbRarity.COMMON_SWORD));
        items.put("rogue_sword", new RogueSword());
        items.put("fancy_sword", new SbItem(new ItemStack(Material.GOLDEN_SWORD), "Fancy Sword", 20, 0, true, SbRarity.COMMON_SWORD)); // missing enchants
        items.put("aspect_of_the_jerry", new AspectOfTheJerry());
        items.put("undead_sword", new UndeadSword());
        items.put("spider_sword", new SpiderSword());
        items.put("diamond_sword", new SbItem(new ItemStack(Material.DIAMOND_SWORD), "Diamond Sword", 35, 0, true, SbRarity.UNCOMMON_SWORD));
        items.put("cleaver", new Cleaver());
        items.put("hunter_knife", new SbItem(new ItemStack(Material.IRON_SWORD), "Hunter Knife", 50, 0, 0, 0, 0, 40, 0,null, null, true, SbRarity.UNCOMMON_SWORD));
        items.put("flaming_sword", new FlamingSword());
        items.put("squire_sword", new SbItem(new ItemStack(Material.IRON_SWORD), "Squire Sword", 50, 10, true, SbRarity.UNCOMMON_SWORD));
        items.put("end_sword", new EndSword());
        items.put("silver_fang", new SbItem(new ItemStack(Material.GHAST_TEAR), "Silver Fang", 100, 0, true, SbRarity.UNCOMMON_SWORD));
        items.put("prismarine_blade", new PrismarineBlade());
        items.put("voidwalker_katana", new VoidwalketKatana());
        items.put("sword_of_bad_health", null); // blaze slayer
        items.put("mercenary_axe", new SbItem(new ItemStack(Material.IRON_AXE), "Mercenary Axe", 70, 20, null, null, true, SbRarity.RARE_SWORD));
        items.put("tribal_speer", new TribalSpeer()); // better speer return
        items.put("golem_sword", new GolemSword());
        items.put("zombie_sword", new ZombieSword());
        items.put("frozen_scythe", new FrozenScythe()); // better 'ice bolt'
        items.put("raider_axe", new RaiderAxe()); // strenght from wood collections, +20 coins from kills, damage from kills might not be working
        items.put("tacticians_sword", new TacticiansSword()); // collections damage buff
        items.put("aspect_of_the_end", new AspectOfTheEnd());
        items.put("revenant_falchion", new RevenantFalchion());
        items.put("recluse_fang", new RecluseFang());
        items.put("edible_mace", new EdibleMace());
        items.put("voidedge_katana", new VoidedgeKatana());
        items.put("firedust_dagger", null); // blaze slayer
        items.put("twilight_dagger", null); // blaze slayer
        items.put("super_cleaver", new SuperCleaver());
        items.put("dreadlord_sword", new DreadlordSword());
        items.put("zombie_soldier_cutlass", new ZombieSoldierCutlass());
        items.put("silent_death", new SilentDeath());
        items.put("conjuring", new Conjuring()); // launch sheep
        items.put("ice_spray_wand", new IceSprayWand()); // ...
        items.put("bonzo_staff", new BonzoStaff());
        items.put("ragnarock_axe", new RagnarockAxe()); // probably works
        items.put("blade_of_the_volcano", null); // blaze?
        items.put("end_stone_sword", new EndStoneSword());
        items.put("ember_rod", new EmberRod());
        items.put("ornate_zombie_sword", new OrnateZombieSword());
        items.put("jerry-chine_gun", new JerryChineGun());
        items.put("ink_wand", new InkWand()); // ...
        items.put("emerald_blade", new EmeraldBlade()); // create purse, formula: coins^1/4 * 2.5
        items.put("leaping_sword", new LeapingSword());
        items.put("sword_of_revelations", null); // mythological event
        items.put("ghoul_buster", null); // spooky festival
        items.put("fire_freeze_staff", null); // crimson
        items.put("fire_fury_staff", null); // crimson
        items.put("enrager", null); // crimson
        items.put("aurora staff", null); // crimson
        items.put("reaper_falchion", new ReaperFalchion());
        items.put("scorpion_foil", null); // don't know how to do blocking (maybe shield only with this sword?)
        items.put("shaman_sword", null); // nah
        items.put("vorpal_katana", new VorpalKatana());
        items.put("sinseeker_scythe", null); // kinda difficult (or just annoying)
        items.put("aspect_of_the_void", new AspectOfTheVoid());
        items.put("kindlebane_dagger", null); // blaze
        items.put("mawdredge_dagger", null); // blaze
        items.put("hyper_cleaver", new HyperCleaver());
        items.put("giant_cleaver", new GiantCleaver());
        items.put("zombie_knight_sword", new ZombieKnightSword()); // missing full set bonus
        items.put("zombie_commander_whip", null); // dungeons thing
        items.put("earth_shard", null); // dungeons class thing
        items.put("fel_sword", null); // later?
        items.put("wither_cloak_sword", new WitherCloakSword()); // rotate creepers, follow player
        items.put("adaptive_blade", null); // dungeon class thing
        items.put("spirit_sword", null); // dungeon thing
        items.put("florid_zombie_sword", new FloridZombieSword());
        items.put("soul_whip", new SoulWhip()); // maybe change arc equation
        items.put("aspect_of_the_dragons", new AspectOfTheDragons()); // missing particles, rotate 90º
        items.put("silk-edge_sword", new SilkEdgeSword());
        items.put("pigman_sword", new PigmanSword()); // missing particles, rotate again 90º
        items.put("yeti_sword", null); // falling blocks...
        items.put("midas_sword", null); // coins and dark auction
        items.put("midas_staff", null); // coins, dark auction and falling blocks
        items.put("daedalus_axe", null); // mythological event
        items.put("reaper_scythe", new ReaperScythe()); // check spawning mobs, add custom pathfinder, chance for souls, mana cost, remove when player dies
        items.put("axe_of_the_shredded", new AxeOfTheShredded()); // missing ability
        items.put("pooch_sword", null); // nah
        items.put("atomsplit_katana", new AtomsplitKatana());
        items.put("pyrochaos_dagger", null); // blaze slayer
        items.put("deathripper_dagger", null); // blaze slayer
        items.put("flower_of_truth", new FlowerOfTruth()); // check ricochets
        items.put("spirit_sceptre", new SpiritSceptre()); // seems fine
        items.put("shadow_fury", new ShadowFury());
        items.put("livid_dagger", new LividDagger()); // missing right click ability
        items.put("giants_sword", new GiantSword());
        items.put("necromancer_sword", new NecromancerSword()); // check spawning mobs, add custom pathfinder, chance for souls, mana cost, remove when player dies
        items.put("necrons_blade", new NecronsBlade());
        items.put("valkyrie", new Valkyrie());
        items.put("upgraded_valkyrie", items.get("valkyrie").setAbilities(Collections.singletonList(new SbAbility("Wither Impact", AbilityType.RIGHT_CLICK,
                Arrays.asList("&7Teleport &a10 blocks &7ahead of", "&7you. Then implode dealing",
                        "&c10,000 &7damage to nearby", "&7enemies. Also applies the Wither",
                        "&7shield scroll ability reducing", "&7damage taken and granting an",
                        "&7absorption shield for &e5", "&7seconds."), 150))));
        items.put("hyperion", new Hyperion());
        items.put("upgraded_hyperion", items.get("hyperion").setAbilities(Collections.singletonList(new SbAbility("Wither Impact", AbilityType.RIGHT_CLICK,
                Arrays.asList("&7Teleport &a10 blocks &7ahead of", "&7you. Then implode dealing",
                        "&c10,000 &7damage to nearby", "&7enemies. Also applies the Wither",
                        "&7shield scroll ability reducing", "&7damage taken and granting an",
                        "&7absorption shield for &e5", "&7seconds."), 150))));
        items.put("scylla", new Scylla());
        items.put("upgraded_scylla", items.get("scylla").setAbilities(Collections.singletonList(new SbAbility("Wither Impact", AbilityType.RIGHT_CLICK,
                Arrays.asList("&7Teleport &a10 blocks &7ahead of", "&7you. Then implode dealing",
                        "&c10,000 &7damage to nearby", "&7enemies. Also applies the Wither",
                        "&7shield scroll ability reducing", "&7damage taken and granting an",
                        "&7absorption shield for &e5", "&7seconds."), 150))));
        items.put("astraea", new Astraea()); // missing true defense
        items.put("upgraded_astraea", items.get("astraea").setAbilities(Collections.singletonList(new SbAbility("Wither Impact", AbilityType.RIGHT_CLICK,
                Arrays.asList("&7Teleport &a10 blocks &7ahead of", "&7you. Then implode dealing",
                        "&c10,000 &7damage to nearby", "&7enemies. Also applies the Wither",
                        "&7shield scroll ability reducing", "&7damage taken and granting an",
                        "&7absorption shield for &e5", "&7seconds."), 150))));
        items.put("dark_claymore", new DarkClaymore());

        /*
        *
        *
        *
        */

        // Bows
        items.put("bow", new SbItem(new ItemStack(Material.BOW), "Bow", 30, 0, null, null, true, SbRarity.COMMON_BOW));
        items.put("decent_bow", new SbItem(new ItemStack(Material.BOW), "Decent Bow", 45, 0, null, null, true, SbRarity.UNCOMMON_BOW));
        items.put("wither_bow", new WitherBow());
        items.put("prismarine_bow", null); // useless, arrows don't work on guardians
        items.put("savanna_bow", new SavannaBow());
        items.put("artisanal_bow", new ArtisanalBow());
        items.put("ender_bow", new EnderBow());
        items.put("machine_gun_bow", new MachineGunBow());
        items.put("soulstealer_bow", new SoulstealerBow());
        items.put("sniper_bow", new SniperBow());
        items.put("undead_bow", new UndeadBow());
        items.put("end_stone_bow", new EndStoneBow());
        items.put("slime_bow", null); // nah, kinda useless
        items.put("hurricane_bow", new HurricaneBow());
        items.put("explosive_bow", new ExplosiveBow());
        items.put("magma_bow", new MagmaBow()); // no longer obtainable, missing quiver
        items.put("spider_queens_stinger", null);
        items.put("venoms_touch", null);
        items.put("souls_rebound", new SoulsRebound());
        items.put("scorpion_bow", null);
        items.put("juju_shortbow", new JuJuShortBow());
        items.put("super_undead_bow", null); // bounce arrow to other entity?
        items.put("death_bow", null); // same as above
        items.put("sulphur_bow", null); // crimson
        items.put("runaans_bow", new RunaansBow()); // check for arrows vectors, missing aiming arrows
        items.put("mosquito_bow", new MosquitoBow()); // maybe change how additional damage is dealt
        items.put("terminator", new Terminator());
        items.put("spirit_bow", new SpiritBow()); // missing dungeon ability (but there is no dungeons "yet" :/)
        items.put("last_breath", new LastBreath()); // need to create entities
        items.put("bonemerang", new Bonemerang()); // check returning

        /*
         *
         *
         *
         */

        // Power Orbs
        items.put("radiant_power_orb", new RadiantPowerOrb());
        items.put("mana_flux_power_orb", new ManaFluxPowerOrb());
        items.put("overflux_power_orb", new OverfluxPowerOrb());
        items.put("plasmaflux_power_orb", new PlasmafluxPowerOrb());

        /*
         *
         *
         *
         */

        // Armors
        armors.put("farm_suit_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.YELLOW), "Farm Suit Helmet", 0, 15, null, Arrays.asList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+20 &7near Farming Minions or", "&7farming islands.")), new SbAbility("Farmer Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regrow and extra crom on farming", "&7islands every &a3 &7seconds."))), true, SbRarity.COMMON_HELMET));
        armors.put("farm_suit_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.YELLOW), "Farm Suit Chestplate", 0, 40, null, Arrays.asList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+20 &7near Farming Minions or", "&7farming islands.")), new SbAbility("Farmer Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regrow and extra crom on farming", "&7islands every &a3 &7seconds."))), true, SbRarity.COMMON_CHESTPLATE));
        armors.put("farm_suit_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.YELLOW), "Farm Suit Leggings", 0, 30, null, Arrays.asList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+20 &7near Farming Minions or", "&7farming islands.")), new SbAbility("Farmer Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regrow and extra crom on farming", "&7islands every &a3 &7seconds."))), true, SbRarity.COMMON_LEGGINGS));
        armors.put("farm_suit_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.YELLOW), "Farm Suit Boots", 0, 15, null, Arrays.asList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+20 &7near Farming Minions or", "&7farming islands.")), new SbAbility("Farmer Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regrow and extra crom on farming", "&7islands every &a3 &7seconds."))), true, SbRarity.COMMON_BOOTS));
        putSet("farm_suit", "farm_suit");

        armors.put("mushroom_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.RED), "Mushroom Helmet", 20, 0, null, Collections.singletonList(new SbAbility("Night Affinity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Night Vision &7while", "&7worn, and during the night, the", "&7stats of the armor pieces are &atripled&7."))), true, SbRarity.COMMON_HELMET));
        armors.put("mushroom_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.RED), "Mushroom Chestplate", 10, 10, null, Collections.singletonList(new SbAbility("Night Affinity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Night Vision &7while", "&7worn, and during the night, the", "&7stats of the armor pieces are &atripled&7."))), true, SbRarity.COMMON_CHESTPLATE));
        armors.put("mushroom_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.RED), "Mushroom Leggings", 10, 5, null, Collections.singletonList(new SbAbility("Night Affinity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Night Vision &7while", "&7worn, and during the night, the", "&7stats of the armor pieces are &atripled&7."))), true, SbRarity.COMMON_LEGGINGS));
        armors.put("mushroom_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.RED), "Mushroom boots", 15, 0, null, Collections.singletonList(new SbAbility("Night Affinity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Night Vision &7while", "&7worn, and during the night, the", "&7stats of the armor pieces are &atripled&7."))), true, SbRarity.COMMON_BOOTS));
        putSet("mushroom_armor", "mushroom");

        armors.put("angler_helmet", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Angler Helmet", 0, 15, 0, 0, 0, 0, 0, 1, null, Arrays.asList(new SbAbility("Depth Champion", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-30% &7damage from Sea Creatures.", "&7Increase their spawn chance by &e4%&7.")), new SbAbility("Deepness Within", AbilityType.FULL_SET_BONUS, Collections.singletonList("&7Gain &c10%health% &7per Fishing Level."))), true, SbRarity.COMMON_HELMET));
        armors.put("angler_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLUE), "Angler Chestplate", 0, 40, 0, 0, 0, 0, 0, 1, null, Arrays.asList(new SbAbility("Depth Champion", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-30% &7damage from Sea Creatures.", "&7Increase their spawn chance by &e4%&7.")), new SbAbility("Deepness Within", AbilityType.FULL_SET_BONUS, Collections.singletonList("&7Gain &c10%health% &7per Fishing Level."))), true, SbRarity.COMMON_CHESTPLATE));
        armors.put("angler_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.BLUE), "Angler Leggings", 0, 30, 0, 0, 0, 0, 0, 1, null, Arrays.asList(new SbAbility("Depth Champion", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-30% &7damage from Sea Creatures.", "&7Increase their spawn chance by &e4%&7.")), new SbAbility("Deepness Within", AbilityType.FULL_SET_BONUS, Collections.singletonList("&7Gain &c10%health% &7per Fishing Level."))), true, SbRarity.COMMON_LEGGINGS));
        armors.put("angler_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.BLUE), "Angler Boots", 0, 15, 0, 0, 0, 0, 0, 1, null, Arrays.asList(new SbAbility("Depth Champion", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-30% &7damage from Sea Creatures.", "&7Increase their spawn chance by &e4%&7.")), new SbAbility("Deepness Within", AbilityType.FULL_SET_BONUS, Collections.singletonList("&7Gain &c10%health% &7per Fishing Level."))), true, SbRarity.COMMON_BOOTS));
        putSet("angler_armor", "angler");

        armors.put("pumpkin_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.ORANGE), "Pumpkin Helmet", 8, 8, null, Collections.singletonList(new SbAbility("Pumpkin Buff", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Reduces all taken damage by", "&a+10% &7and deal &a+10%", "&7damage."))), true, SbRarity.COMMON_HELMET));
        armors.put("pumpkin_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.ORANGE), "Pumpkin Chestplate", 14, 14, null, Collections.singletonList(new SbAbility("Pumpkin Buff", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Reduces all taken damage by", "&a+10% &7and deal &a+10%", "&7damage."))), true, SbRarity.COMMON_CHESTPLATE));
        armors.put("pumpkin_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.ORANGE), "Pumpkin Leggings", 10, 10, null, Collections.singletonList(new SbAbility("Pumpkin Buff", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Reduces all taken damage by", "&a+10% &7and deal &a+10%", "&7damage."))), true, SbRarity.COMMON_LEGGINGS));
        armors.put("pumpkin_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.ORANGE), "Pumpkin Boots", 8, 8, null, Collections.singletonList(new SbAbility("Pumpkin Buff", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Reduces all taken damage by", "&a+10% &7and deal &a+10%", "&7damage."))), true, SbRarity.COMMON_BOOTS));
        putSet("pumpkin_armor", "pumpkin");

        armors.put("cactus_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.GREEN), "Cactus Helmet", 5, 10, null, Collections.singletonList(new SbAbility("Deflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Rebound &a33.0% &7of the damage", "&7you take back at your enemy."))), true, SbRarity.COMMON_HELMET));
        armors.put("cactus_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GREEN), "Cactus Chestplate", 15, 25, null, Collections.singletonList(new SbAbility("Deflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Rebound &a33.0% &7of the damage", "&7you take back at your enemy."))), true, SbRarity.COMMON_CHESTPLATE));
        armors.put("cactus_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GREEN), "Cactus Leggings", 10, 20, null, Collections.singletonList(new SbAbility("Deflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Rebound &a33.0% &7of the damage", "&7you take back at your enemy."))), true, SbRarity.COMMON_LEGGINGS));
        armors.put("cactus_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GREEN), "Cactus Boots", 5, 10, null, Collections.singletonList(new SbAbility("Deflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Rebound &a33.0% &7of the damage", "&7you take back at your enemy."))), true, SbRarity.COMMON_BOOTS));
        putSet("cactus_armor", "cactus");

        armors.put("leaflet_hat", new SbArmor(new ItemStack(Material.OAK_LEAVES), "Leaflet Hat", 20, 0, null, Collections.singletonList(new SbAbility("Energy of the Forest", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While in a Forest zone you", "&7regain &a5.0&c%health%Health &7every", "&7seconds."))), true, SbRarity.COMMON_HELMET));
        armors.put("leaflet_tunic", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GREEN), "Leaflet Tunic", 35, 0, null, Collections.singletonList(new SbAbility("Energy of the Forest", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While in a Forest zone you", "&7regain &a5.0&c%health%Health &7every", "&7seconds."))), true, SbRarity.COMMON_CHESTPLATE));
        armors.put("leaflet_pants", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GREEN), "Leaflet Pants", 30, 0, null, Collections.singletonList(new SbAbility("Energy of the Forest", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While in a Forest zone you", "&7regain &a5.0&c%health%Health &7every", "&7seconds."))), true, SbRarity.COMMON_LEGGINGS));
        armors.put("leaflet_sandals", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GREEN), "Leaflet Sandals", 15, 0, null, Collections.singletonList(new SbAbility("Energy of the Forest", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While in a Forest zone you", "&7regain &a5.0&c%health%Health &7every", "&7seconds."))), true, SbRarity.COMMON_BOOTS));
        sets.put("leaflet_armor", new SbArmor[]{armors.get("leaflet_hat"), armors.get("leaflet_tunic"), armors.get("leaflet_pants"), armors.get("leaflet_sandals")});

        armors.put("leather_helmet", new SbArmor(new ItemStack(Material.LEATHER_HELMET), "Leather Helmet", 0, 5, true, SbRarity.COMMON_HELMET));
        armors.put("leather_chestplate", new SbArmor(new ItemStack(Material.LEATHER_CHESTPLATE), "Leather Chestplate", 0, 15, true, SbRarity.COMMON_CHESTPLATE));
        armors.put("leather_leggings", new SbArmor(new ItemStack(Material.LEATHER_LEGGINGS), "Leather Leggings", 0, 10, true, SbRarity.COMMON_LEGGINGS));
        armors.put("leather_boots", new SbArmor(new ItemStack(Material.LEATHER_BOOTS), "Leather Boots", 0, 5, true, SbRarity.COMMON_BOOTS));
        putSet("leather_armor", "leather");

        armors.put("golden_helmet", new SbArmor(new ItemStack(Material.GOLDEN_HELMET), "Golden Helmet", 0, 10, true, SbRarity.COMMON_HELMET));
        armors.put("golden_chestplate", new SbArmor(new ItemStack(Material.GOLDEN_CHESTPLATE), "Golden Chestplate", 0, 25, true, SbRarity.COMMON_CHESTPLATE));
        armors.put("golden_leggings", new SbArmor(new ItemStack(Material.GOLDEN_LEGGINGS), "Golden Leggings", 0, 15, true, SbRarity.COMMON_LEGGINGS));
        armors.put("golden_boots", new SbArmor(new ItemStack(Material.GOLDEN_BOOTS), "Golden Boots", 0, 5, true, SbRarity.COMMON_BOOTS));
        putSet("golden_armor", "golden");

        armors.put("iron_helmet", new SbArmor(new ItemStack(Material.IRON_HELMET), "Iron Helmet", 0, 10, true, SbRarity.COMMON_HELMET));
        armors.put("iron_chestplate", new SbArmor(new ItemStack(Material.IRON_CHESTPLATE), "Iron Chestplate", 0, 30, true, SbRarity.COMMON_CHESTPLATE));
        armors.put("iron_leggings", new SbArmor(new ItemStack(Material.IRON_LEGGINGS), "Iron Leggings", 0, 25, true, SbRarity.COMMON_LEGGINGS));
        armors.put("iron_boots", new SbArmor(new ItemStack(Material.IRON_BOOTS), "Iron Boots", 0, 10, true, SbRarity.COMMON_BOOTS));
        putSet("iron_armor", "iron");

        armors.put("lapis_armor_helmet", new SbArmor(new ItemStack(Material.SEA_LANTERN), "Lapis Armor Helmet", 0, 25, Arrays.asList("&7Each piece of this armor grants", "&a50% &7bonus experience when", "&7mining ores."), Collections.singletonList(new SbAbility("Health", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the wearer's maximum", "&c%health%Health &7by &a60&7."))), true, SbRarity.UNCOMMON_HELMET));
        armors.put("lapis_armor_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLUE), "Lapis Armor Chestplate", 0, 40, Arrays.asList("&7Each piece of this armor grants", "&a50% &7bonus experience when", "&7mining ores."), Collections.singletonList(new SbAbility("Health", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the wearer's maximum", "&c%health%Health &7by &a60&7."))), true, SbRarity.UNCOMMON_CHESTPLATE));
        armors.put("lapis_armor_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.BLUE), "Lapis Armor Leggings", 0, 35, Arrays.asList("&7Each piece of this armor grants", "&a50% &7bonus experience when", "&7mining ores."), Collections.singletonList(new SbAbility("Health", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the wearer's maximum", "&c%health%Health &7by &a60&7."))), true, SbRarity.UNCOMMON_LEGGINGS));
        armors.put("lapis_armor_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.BLUE), "Lapis Armor Boots", 0, 20, Arrays.asList("&7Each piece of this armor grants", "&a50% &7bonus experience when", "&7mining ores."), Collections.singletonList(new SbAbility("Health", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the wearer's maximum", "&c%health%Health &7by &a60&7."))), true, SbRarity.UNCOMMON_BOOTS));
        putSet("lapis_armor", "lapis_armor");

        armors.put("miners_outfit_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.GRAY), "Miner's Outfit Helmet", 0, 15, null, Collections.singletonList(new SbAbility("Haste", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Haste II &7while", "&7worn."))), true, SbRarity.UNCOMMON_HELMET));
        armors.put("miners_outfit_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GRAY), "Miner's Outfit Chestplate", 0, 40, null, Collections.singletonList(new SbAbility("Haste", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Haste II &7while", "&7worn."))), true, SbRarity.UNCOMMON_CHESTPLATE));
        armors.put("miners_outfit_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GRAY), "Miner's Outfit Leggings", 0, 30, null, Collections.singletonList(new SbAbility("Haste", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Haste II &7while", "&7worn."))), true, SbRarity.UNCOMMON_LEGGINGS));
        armors.put("miners_outfit_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GRAY), "Miner's Outfit Boots", 0, 15, null, Collections.singletonList(new SbAbility("Haste", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer with", "&apermanent Haste II &7while", "&7worn."))), true, SbRarity.UNCOMMON_BOOTS));
        putSet("miners_outfit", "miners_outfit");

        armors.put("chainmail_helmet", new SbArmor(new ItemStack(Material.CHAINMAIL_HELMET), "Chainmail Helmet", 0, 12, true, SbRarity.UNCOMMON_HELMET));
        armors.put("chainmail_chestplate", new SbArmor(new ItemStack(Material.CHAINMAIL_CHESTPLATE), "Chainmail Chestplate", 0, 30, true, SbRarity.UNCOMMON_CHESTPLATE));
        armors.put("chainmail_leggings", new SbArmor(new ItemStack(Material.CHAINMAIL_LEGGINGS), "Chainmail Leggings", 0, 20, true, SbRarity.UNCOMMON_LEGGINGS));
        armors.put("chainmail_boots", new SbArmor(new ItemStack(Material.CHAINMAIL_BOOTS), "Chainmail Boots", 0, 7, true, SbRarity.UNCOMMON_BOOTS));
        putSet("chainmail_armor", "chainmail");

        armors.put("diamond_helmet", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Diamond Helmet", 0, 15, true, SbRarity.UNCOMMON_HELMET));
        armors.put("diamond_chestplate", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Diamond Chestplate", 0, 40, true, SbRarity.UNCOMMON_CHESTPLATE));
        armors.put("diamond_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Diamond Leggings", 0, 30, true, SbRarity.UNCOMMON_LEGGINGS));
        armors.put("diamond_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Diamond Boots", 0, 15, true, SbRarity.UNCOMMON_BOOTS));
        putSet("diamond_armor", "diamond");

        armors.put("bronze_hunter_helmet", null); // crimson
        armors.put("bronze_hunter_chestplate", null); // crimson
        armors.put("bronze_hunter_leggings", null); // crimson
        armors.put("bronze_hunter_boots", null); // crimson;

        armors.put("miner_helmet", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Miner Helmet", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &7every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_HELMET));
        armors.put("miner_chestplate", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Miner Chestplate", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &7every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_CHESTPLATE));
        armors.put("miner_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Miner Leggings", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &7every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_LEGGINGS));
        armors.put("miner_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Miner Boots", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &7every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_BOOTS));
        putSet("miner_armor", "miner");

        armors.put("hardened_diamond_helmet", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Hardened Diamond Helmet", 0, 60, true, SbRarity.RARE_HELMET));
        armors.put("hardened_diamond_chestplate", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Hardened Diamond Chestplate", 0, 120, true, SbRarity.RARE_CHESTPLATE));
        armors.put("hardened_diamond_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Hardened Diamond Leggings", 0, 95, true, SbRarity.RARE_LEGGINGS));
        armors.put("hardened_diamond_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Hardened Diamond Boots", 0, 55, true, SbRarity.RARE_BOOTS));
        putSet("hardened_diamond_armor", "hardened_diamond");

        armors.put("fairys_fedora", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.PURPLE), "Fairy's Fedora", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_HELMET));
        armors.put("fairys_polo", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.PURPLE), "Fairy's Polo", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_CHESTPLATE));
        armors.put("fairys_trousers", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.PURPLE), "Fairy's Trousers", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_LEGGINGS));
        armors.put("fairys_galoshes", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.PURPLE), "Fairy's Galoshes", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_BOOTS));
        sets.put("fairy_armor", new SbArmor[]{armors.get("fairys_fedora"), armors.get("fairys_polo"), armors.get("fairys_trousers"), armors.get("fairys_galoshes")});

        armors.put("farm_armor_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.YELLOW), "Farm Armor Helmet", 20, 40, null, Collections.singletonList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+25 &7while worn near Farming", "&7Minions or in the &bFarm&7, &bThe Barn&7, and &eMushroom", "&eDesert&7."))), true, SbRarity.RARE_HELMET));
        armors.put("farm_armor_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.YELLOW), "Farm Armor Chestplate", 20, 75, null, Collections.singletonList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+25 &7while worn near Farming", "&7Minions or in the &bFarm&7, &bThe Barn&7, and &eMushroom", "&eDesert&7."))), true, SbRarity.RARE_CHESTPLATE));
        armors.put("farm_armor_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.YELLOW), "Farm Armor Leggings", 20, 50, null, Collections.singletonList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+25 &7while worn near Farming", "&7Minions or in the &bFarm&7, &bThe Barn&7, and &eMushroom", "&eDesert&7."))), true, SbRarity.RARE_LEGGINGS));
        armors.put("farm_armor_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.YELLOW), "Farm Armor Boots", 20, 35, null, Collections.singletonList(new SbAbility("Bonus Speed", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases your &f%speed%Speed &7by", "&a+25 &7while worn near Farming", "&7Minions or in the &bFarm&7, &bThe Barn&7, and &eMushroom", "&eDesert&7."))), true, SbRarity.RARE_BOOTS));
        putSet("farm_armor", "farm_armor");

        armors.put("golem_armor_helmet", new SbArmor(new ItemStack(Material.IRON_HELMET), "Golem Armor Helmet", 45, 45, null, Collections.singletonList(new SbAbility("Absorption", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer &aAbsorption", "&a3 &7for &a20 seconds &7when they", "&7kill an enemy."))), true, SbRarity.RARE_HELMET));
        armors.put("golem_armor_chestplate", new SbArmor(new ItemStack(Material.IRON_CHESTPLATE), "Golem Armor Chestplate", 65, 90, null, Collections.singletonList(new SbAbility("Absorption", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer &aAbsorption", "&a3 &7for &a20 seconds &7when they", "&7kill an enemy."))), true, SbRarity.RARE_CHESTPLATE));
        armors.put("golem_armor_leggings", new SbArmor(new ItemStack(Material.IRON_LEGGINGS), "Golem Armor Leggings", 55, 75, null, Collections.singletonList(new SbAbility("Absorption", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer &aAbsorption", "&a3 &7for &a20 seconds &7when they", "&7kill an enemy."))), true, SbRarity.RARE_LEGGINGS));
        armors.put("golem_armor_boots", new SbArmor(new ItemStack(Material.IRON_BOOTS), "Golem Armor Boots", 40, 40, null, Collections.singletonList(new SbAbility("Absorption", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Grants the wearer &aAbsorption", "&a3 &7for &a20 seconds &7when they", "&7kill an enemy."))), true, SbRarity.RARE_BOOTS));
        putSet("golem_armor", "golem_armor");

        armors.put("helmet_of_growth", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.GREEN), "Helmet Of Growth", 50, 30, null, Collections.singletonList(new SbAbility("Growth", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Heals your for &a1%&c%health%Health", "&7after killing a Monster, and", "&7also increases the &c%health%Health", "&7bonus of a piece of the armor", "&7by &a1 &8(Max 100)"), 0, 4)), true, SbRarity.RARE_HELMET));
        armors.put("chestplate_of_growth", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GREEN), "Chestplate Of Growth", 100, 50, null, Collections.singletonList(new SbAbility("Growth", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Heals your for &a1%&c%health%Health", "&7after killing a Monster, and", "&7also increases the &c%health%Health", "&7bonus of a piece of the armor", "&7by &a1 &8(Max 100)"), 0, 4)), true, SbRarity.RARE_CHESTPLATE));
        armors.put("leggings_of_growth", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GREEN), "Leggings Of Growth", 80, 40, null, Collections.singletonList(new SbAbility("Growth", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Heals your for &a1%&c%health%Health", "&7after killing a Monster, and", "&7also increases the &c%health%Health", "&7bonus of a piece of the armor", "&7by &a1 &8(Max 100)"), 0, 4)), true, SbRarity.RARE_LEGGINGS));
        armors.put("boots_of_growth", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GREEN), "Boots Of Growth", 50, 25, null, Collections.singletonList(new SbAbility("Growth", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Heals your for &a1%&c%health%Health", "&7after killing a Monster, and", "&7also increases the &c%health%Health", "&7bonus of a piece of the armor", "&7by &a1 &8(Max 100)"), 0, 4)), true, SbRarity.RARE_BOOTS));
        sets.put("growth_armor", new SbArmor[]{armors.get("helmet_of_growth"), armors.get("chestplate_of_growth"), armors.get("leggings_of_growth"), armors.get("boots_of_growth")});

        armors.put("skeletons_helmet", new SbArmor(new ItemStack(Material.IRON_HELMET), "Skeleton's Helmet", 0, 75, null, Arrays.asList(new SbAbility("Bone Shield", AbilityType.NONE, Arrays.asList("&7A Bone Shield will surround you,", "&7nullifying damage you take but", "&7consuming a bone in the process.", "&7Bones regenerate every &a30", "&7seconds.")), new SbAbility("Monster Hunter Armor", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-25% &7damage when being", "&7attacked by Monsters, and deal", "&a+25% &7damage when attacking", "&7Monsters."))), true, SbRarity.RARE_HELMET));
        armors.put("guardian_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.TEAL), "Guardian Chestplate", 20, 50, null, Arrays.asList(new SbAbility("Block Damage", AbilityType.NONE, Arrays.asList("&7If your are at full &c%health%Health&7,", "&7the first damage you take will", "&7be nullified."), 0, 60), new SbAbility("Monster Hunter Armor", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-25% &7damage when being", "&7attacked by Monsters, and deal", "&a+25% &7damage when attacking", "&7Monsters."))), true, SbRarity.RARE_CHESTPLATE));
        armors.put("creeper_pants", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GREEN), "Creeper Pants", 200, 65, null, Arrays.asList(new SbAbility("Detonate", AbilityType.NONE, Arrays.asList("&7Causes and explosion when", "&7dropping below &e20%HP&7,", "&7damaging and knocking back all", "&7monsters around your."), 0, 60), new SbAbility("Monster Hunter Armor", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-25% &7damage when being", "&7attacked by Monsters, and deal", "&a+25% &7damage when attacking", "&7Monsters."))), true, SbRarity.RARE_LEGGINGS));
        armors.put("spiders_boots", new SbArmor(new ItemStack(Material.IRON_BOOTS), "Spider's Boots", 0, 45, 0, 0, 0, 50, 5, 0, null, Arrays.asList(new SbAbility("Double Jump", AbilityType.NONE, Collections.singletonList("&7Allows you to double jump!"), 50), new SbAbility("Monster Hunter Armor", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Take &a-25% &7damage when being", "&7attacked by Monsters, and deal", "&a+25% &7damage when attacking", "&7Monsters."))), true, SbRarity.RARE_BOOTS));
        sets.put("monster_hunter_armor", new SbArmor[]{armors.get("skeletons_helmet"), armors.get("guardian_chestplate"), armors.get("creeper_pants"), armors.get("spiders_boots")});

        armors.put("perfect_helmet_1", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier I", 0, 110, true, SbRarity.RARE_HELMET));
        armors.put("perfect_chestplate_1", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier I", 0, 160, true, SbRarity.RARE_CHESTPLATE));
        armors.put("perfect_leggings_1", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier I", 0, 140, true, SbRarity.RARE_LEGGINGS));
        armors.put("perfect_boots_1", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier I", 0, 90, true, SbRarity.RARE_BOOTS));
        sets.put("perfect_armor_1", new SbArmor[]{armors.get("perfect_helmet_1"), armors.get("perfect_chestplate_1"), armors.get("perfect_leggings_1"), armors.get("perfect_boots_1")});

        armors.put("perfect_helmet_2", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier II", 0, 130, true, SbRarity.RARE_HELMET));
        armors.put("perfect_chestplate_2", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier II", 0, 180, true, SbRarity.RARE_CHESTPLATE));
        armors.put("perfect_leggings_2", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier II", 0, 160, true, SbRarity.RARE_LEGGINGS));
        armors.put("perfect_boots_2", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier II", 0, 110, true, SbRarity.RARE_BOOTS));
        sets.put("perfect_armor_2", new SbArmor[]{armors.get("perfect_helmet_2"), armors.get("perfect_chestplate_2"), armors.get("perfect_leggings_2"), armors.get("perfect_boots_2")});

        armors.put("perfect_helmet_3", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier III", 0, 150, true, SbRarity.RARE_HELMET));
        armors.put("perfect_chestplate_3", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier III", 0, 200, true, SbRarity.RARE_CHESTPLATE));
        armors.put("perfect_leggings_3", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier III", 0, 180, true, SbRarity.RARE_LEGGINGS));
        armors.put("perfect_boots_3", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier III", 0, 130, true, SbRarity.RARE_BOOTS));
        sets.put("perfect_armor_3", new SbArmor[]{armors.get("perfect_helmet_3"), armors.get("perfect_chestplate_3"), armors.get("perfect_leggings_3"), armors.get("perfect_boots_3")});

        //armors.put("goblin_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGJmNTZjNjU0MWMxMjY2ZmZjNDYzNTEwYmRiNTVhZWY5MzE1YWY1NDg4OThjZjVkM2NiYTFiNWI0YzAxIn19fQ=="), "Goblin Helmet", 70, 0, ));
        armors.put("goblin_helmet", null); // mining speed/fortune
        armors.put("goblin_chestplate", null);
        armors.put("goblin_leggings", null);
        armors.put("goblin_boots", null);
        putSet("goblin_armor", "goblin");

        armors.put("heat_helmet", null);
        armors.put("heat_chestplate", null);
        armors.put("heat_leggings", null);
        armors.put("heat_boots", null);
        putSet("heat_armor", "heat");

        armors.put("taurus_helmet", null);
        armors.put("flaming_chestplate", null);
        armors.put("moogma_leggings", null);
        armors.put("slug_boots", null);
        sets.put("lava_sea_creature_armor", new SbArmor[]{armors.get("taurus_helmet"), armors.get("flaming_chestplate"), armors.get("moogma_leggings"), armors.get("slug_boots")});

        armors.put("rampart_helmet", null);
        armors.put("rampart_chestplate", null);
        armors.put("rampart_leggings", null);
        armors.put("rampart_boots", null);
        putSet("rampart_armor", "rampart");

        armors.put("shimmering_light_hood", null);
        armors.put("shimmering_light_tunic", null);
        armors.put("shimmering_light_trousers", null);
        armors.put("shimmering_light_slippers", null);
        sets.put("shimmering_light_armor", new SbArmor[]{armors.get("shimmering_light_hood"), armors.get("shimmering_light_tunic"), armors.get("shimmering_light_trousers"), armors.get("shimmering_light_slippers")});

        armors.put("silver_hunter_helmet", null);
        armors.put("silver_hunter_chestplate", null);
        armors.put("silver_hunter_leggings", null);
        armors.put("silver_hunter_boots", null);
        putSet("silver_hunter_armor", "silver_hunter");

        armors.put("armor_of_magma_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.ORANGE), "Armor Of Magma Helmet", 50, 15, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every &910 Magma Cubes killed", "&7gives the wearer &a+1 &c%health% Health", "&7and &b%intelligence% Intelligence &7while", "&7wearing the set. Max 200 each"))), true, SbRarity.EPIC_HELMET));
        armors.put("armor_of_magma_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.ORANGE), "Armor Of Magma Chestplate", 100, 30, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every &910 Magma Cubes killed", "&7gives the wearer &a+1 &c%health% Health", "&7and &b%intelligence% Intelligence &7while", "&7wearing the set. Max 200 each"))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("armor_of_magma_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.ORANGE), "Armor Of Magma Leggings", 75, 25, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every &910 Magma Cubes killed", "&7gives the wearer &a+1 &c%health% Health", "&7and &b%intelligence% Intelligence &7while", "&7wearing the set. Max 200 each"))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("armor_of_magma_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.ORANGE), "Armor Of Magma Boots", 45, 15, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every &910 Magma Cubes killed", "&7gives the wearer &a+1 &c%health% Health", "&7and &b%intelligence% Intelligence &7while", "&7wearing the set. Max 200 each"))), true, SbRarity.EPIC_BOOTS));
        putSet("armor_of_magma", "armor_of_magma");

        armors.put("emerald_armor_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.fromRGB(0, 255, 0)), "Emerald Armor Helmet", 0, 50, null, Collections.singletonList(new SbAbility("Tank", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases &c%health% Health &7by &a+1", "&7and &a%defense% Defense &7by &a+1 &7for", "&7every &b3,000 &7Emeralds in your", "&7collection. Max 350 each."))), true, SbRarity.EPIC_HELMET));
        armors.put("emerald_armor_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(0, 255, 0)), "Emerald Armor Chestplate", 0, 100, null, Collections.singletonList(new SbAbility("Tank", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases &c%health% Health &7by &a+1", "&7and &a%defense% Defense &7by &a+1 &7for", "&7every &b3,000 &7Emeralds in your", "&7collection. Max 350 each."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("emerald_armor_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(0, 255, 0)), "Emerald Armor Leggings", 0, 75, null, Collections.singletonList(new SbAbility("Tank", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases &c%health% Health &7by &a+1", "&7and &a%defense% Defense &7by &a+1 &7for", "&7every &b3,000 &7Emeralds in your", "&7collection. Max 350 each."))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("emerald_armor_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(0, 255, 0)), "Emerald Armor Boots", 0, 45, null, Collections.singletonList(new SbAbility("Tank", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases &c%health% Health &7by &a+1", "&7and &a%defense% Defense &7by &a+1 &7for", "&7every &b3,000 &7Emeralds in your", "&7collection. Max 350 each."))), true, SbRarity.EPIC_BOOTS));
        putSet("emerald_armor", "emerald_armor");

        armors.put("ember_helmet", null); // no longer obtainable
        armors.put("ember_chestplate", null);
        armors.put("ember_leggings", null);
        armors.put("ember_boots", null);
        putSet("ember_armor", "ember");

        armors.put("rekindled_ember_helmet", null); // ...
        armors.put("rekindled_ember_chestplate", null);
        armors.put("rekindled_ember_leggings", null);
        armors.put("rekindled_ember_boots", null);
        putSet("rekindled_ember_armor", "rekindled_ember");

        armors.put("crystal_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.SILVER), "Crystal Helmet", 0, 20, 0, 0, 0, 65, 0, 0, null, Collections.singletonList(new SbAbility("Refraction", AbilityType.FULL_SET_BONUS, Arrays.asList("&7The stats of this armor change", "&7from &a0 &7to &a200% &7depending", "&7on the current light level.", "", "&7Current Light Level", "&a15 &8(200.0%)"))), true, SbRarity.EPIC_HELMET));
        armors.put("crystal_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.SILVER), "Crystal Chestplate", 0, 35, 0, 0, 0, 120, 0, 0, null, Collections.singletonList(new SbAbility("Refraction", AbilityType.FULL_SET_BONUS, Arrays.asList("&7The stats of this armor change", "&7from &a0 &7to &a200% &7depending", "&7on the current light level.", "", "&7Current Light Level", "&a15 &8(200.0%)"))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("crystal_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.SILVER), "Crystal Leggings", 0, 30, 0, 0, 0, 100, 0, 0, null, Collections.singletonList(new SbAbility("Refraction", AbilityType.FULL_SET_BONUS, Arrays.asList("&7The stats of this armor change", "&7from &a0 &7to &a200% &7depending", "&7on the current light level.", "", "&7Current Light Level", "&a15 &8(200.0%)"))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("crystal_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.SILVER), "Crystal Boots", 0, 15, 0, 0, 0, 60, 0, 0, null, Collections.singletonList(new SbAbility("Refraction", AbilityType.FULL_SET_BONUS, Arrays.asList("&7The stats of this armor change", "&7from &a0 &7to &a200% &7depending", "&7on the current light level.", "", "&7Current Light Level", "&a15 &8(200.0%)"))), true, SbRarity.EPIC_BOOTS));
        putSet("crystal_armor", "crystal");

        armors.put("zombie_chestplate", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Zombie Chestplate", 200, 40, null, Collections.singletonList(new SbAbility("Projectile Absorption", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Heals the wearer for &a10 &7per", "&7second for &a5 &7seconds when", "&7hit by a projectile."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("zombie_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Zombie Leggings", 160, 30, null, Collections.singletonList(new SbAbility("Projectile Absorption", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Heals the wearer for &a10 &7per", "&7second for &a5 &7seconds when", "&7hit by a projectile."))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("zombie_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Zombie Boots", 120, 25, null, Collections.singletonList(new SbAbility("Projectile Absorption", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Heals the wearer for &a10 &7per", "&7second for &a5 &7seconds when", "&7hit by a projectile."))), true, SbRarity.EPIC_BOOTS));
        sets.put("zombie_armor", new SbArmor[]{armors.get("zombie_chestplate"), armors.get("zombie_leggings"), armors.get("zombie_boots")});

        armors.put("blaze_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk2ZmYxNDVlYTE4ZDYxNjAxNTE4YzY0NmJhZTY3OGY1ZmRlMDk2Y2YwMzYwZWMzNmNhZGFjNDE0MjU1MzRlIn19fQ=="), "Blaze Helmet", 0, 90, 30, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs within &a5 &7blocks", "&7for &a3% &7of their max &c%health%", "&cHealth &7per second.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60"))), true, SbRarity.EPIC_HELMET));
        armors.put("blaze_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.YELLOW), "Blaze Chestplate", 0, 165, 30, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs within &a5 &7blocks", "&7for &a3% &7of their max &c%health%", "&cHealth &7per second.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60"))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("blaze_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.YELLOW), "Blaze Leggings", 0, 130, 30, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs within &a5 &7blocks", "&7for &a3% &7of their max &c%health%", "&cHealth &7per second.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60"))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("blaze_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.YELLOW), "Blaze Boots", 0, 80, 30, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs within &a5 &7blocks", "&7for &a3% &7of their max &c%health%", "&cHealth &7per second.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60"))), true, SbRarity.EPIC_BOOTS));
        putSet("blaze_armor", "blaze");

        armors.put("ender_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjcxNWNhMGY3NDI1NDRhZTNjYTEwNDI5NzU3OGMyZWQ3MDBlYTNhNTQ5ODA0MTM1MTJmNWU3YTBiYzA2NzI5YSJ9fX0="), "Ender Helmet", 20, 35, Arrays.asList("&7All stats of this armor piece", "&7are multiplied by &52x &7while", "&7on the End Island!"), null, true, SbRarity.EPIC_HELMET));
        armors.put("ender_chestplate", new SbArmor(new ItemStack(Material.CHAINMAIL_CHESTPLATE), "Ender Chestplate", 30, 60, Arrays.asList("&7All stats of this armor piece", "&7are multiplied by &52x &7while", "&7on the End Island!"), null, true, SbRarity.EPIC_CHESTPLATE));
        armors.put("ender_leggings", new SbArmor(new ItemStack(Material.CHAINMAIL_LEGGINGS), "Ender Leggings", 25, 50, Arrays.asList("&7All stats of this armor piece", "&7are multiplied by &52x &7while", "&7on the End Island!"), null, true, SbRarity.EPIC_LEGGINGS));
        armors.put("ender_boots", new SbArmor(new ItemStack(Material.CHAINMAIL_BOOTS), "Ender Boots", 15, 25, Arrays.asList("&7All stats of this armor piece", "&7are multiplied by &52x &7while", "&7on the End Island!"), null, true, SbRarity.EPIC_BOOTS));
        putSet("ender_armor", "ender");

        armors.put("revenant_chestplate", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Revenant Chestplate", 180, 70, null, Arrays.asList(new SbAbility("Trolling The Reaper", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Healing Wands heal &a+50%&7.", "&7Gain &a+100%defense% &7against Zombies.")), new SbAbility("Zombie Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Zombies to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)"))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("revenant_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Revenant Leggings", 120, 50, null, Arrays.asList(new SbAbility("Trolling The Reaper", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Healing Wands heal &a+50%&7.", "&7Gain &a+100%defense% &7against Zombies.")), new SbAbility("Zombie Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Zombies to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)"))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("revenant_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Revenant Boots", 100, 30, null, Arrays.asList(new SbAbility("Trolling The Reaper", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Healing Wands heal &a+50%&7.", "&7Gain &a+100%defense% &7against Zombies.")), new SbAbility("Zombie Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Zombies to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)"))), true, SbRarity.EPIC_BOOTS));
        sets.put("revenant_armor", new SbArmor[]{armors.get("revenant_chestplate"), armors.get("revenant_leggings"), armors.get("revenant_boots")});

        armors.put("helmet_of_the_pack", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.SILVER), "Helmet Of The Pack", 70, 65, Arrays.asList("&7Gain &a+50%defense% Defense &7against animals.", "&7Gain &f+5%trueDefense% True Defense&7."), Collections.singletonList(new SbAbility("Armor of the Pack", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c+35%strenght% Strenght &7and", "&a+80%defense% Defense &7for each", "&cArmor of the Pack &7wearer", "&7within &a30 &7blocks.", "&8Max of 3 players."))), true, SbRarity.EPIC_HELMET));
        armors.put("chestplate_of_the_pack", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.RED), "Chestplate Of The Pack", 100, 95, Arrays.asList("&7Gain &a+75%defense% Defense &7against animals.", "&7Gain &f+5%trueDefense% True Defense&7."), Collections.singletonList(new SbAbility("Armor of the Pack", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c+35%strenght% Strenght &7and", "&a+80%defense% Defense &7for each", "&cArmor of the Pack &7wearer", "&7within &a30 &7blocks.", "&8Max of 3 players."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("leggings_of_the_pack", new SbArmor(new ItemStack(Material.IRON_LEGGINGS), "Leggings Of The Pack", 80, 75, Arrays.asList("&7Gain &a+50%defense% Defense &7against animals.", "&7Gain &f+5%trueDefense% True Defense&7."), Collections.singletonList(new SbAbility("Armor of the Pack", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c+35%strenght% Strenght &7and", "&a+80%defense% Defense &7for each", "&cArmor of the Pack &7wearer", "&7within &a30 &7blocks.", "&8Max of 3 players."))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("boots_of_the_pack", new SbArmor(new ItemStack(Material.IRON_BOOTS), "Boots Of The Pack", 70, 65, Arrays.asList("&7Gain &a+50%defense% Defense &7against animals.", "&7Gain &f+5%trueDefense% True Defense&7."), Collections.singletonList(new SbAbility("Armor of the Pack", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c+35%strenght% Strenght &7and", "&a+80%defense% Defense &7for each", "&cArmor of the Pack &7wearer", "&7within &a30 &7blocks.", "&8Max of 3 players."))), true, SbRarity.EPIC_BOOTS));
        sets.put("armor_of_the_pack", new SbArmor[]{armors.get("helmet_of_the_pack"), armors.get("chestplate_of_the_pack"), armors.get("leggings_of_the_pack"), armors.get("boots_of_the_pack")});

        armors.put("sponge_helmet", new SbArmor(new ItemStack(Material.SPONGE), "Sponge Helmet", 0, 80, 0, 0, 0, 0, 0, 1.8, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water."))), true, SbRarity.EPIC_HELMET));
        armors.put("sponge_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.YELLOW), "Sponge Chestplate", 0, 145, 0, 0, 0, 0, 0, 1.8, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("sponge_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.YELLOW), "Sponge Leggings", 0, 100, 0, 0, 0, 0, 0, 1.8, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water."))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("sponge_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.YELLOW), "Sponge Boots", 0, 60, 0, 0, 0, 0, 0, 1.8, null, Collections.singletonList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water."))), true, SbRarity.EPIC_BOOTS));
        putSet("sponge_armor", "sponge");

        armors.put("gold_hunter_helmet", null);
        armors.put("gold_hunter_chestplate", null);
        armors.put("gold_hunter_leggings", null);
        armors.put("gold_hunter_boots", null);
        putSet("gold_hunter_armor", "gold_hunter");

        armors.put("thunder_helmet", null);
        armors.put("thunder_chestplate", null);
        armors.put("thunder_leggings", null);
        armors.put("thunder_boots", null);
        putSet("thunder_armor", "thunder");

        armors.put("mastiff_crown", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzMzMDgxY2FiY2IxMzg1OTViMDRmMmI3OGY3ZDBkMzNmN2EzZjhkNDUxYzY1OThkMmZkYmI5OTQwYThiNGYwNSJ9fX0="), "Mastiff Crown", 500, -1000000, 0, 0, 0, 125, 0, 0, null, Collections.singletonList(new SbAbility("Absolute Unit", AbilityType.FULL_SET_BONUS, Arrays.asList("&c+50%health% &7per &c1% Crit Damage", "&7Regain &c2% &7of &cmax %health% &7when hit. &8(1s cd)", "&7Receive &a-20% &7damage from wolves.", "&7Your crit damage is &950% &7less effective."))), true, SbRarity.EPIC_HELMET));
        armors.put("mastiff_chestplate", new SbArmor(new ItemStack(Material.GOLDEN_CHESTPLATE), "Mastiff Chestplate", 500, -1000000, null, Collections.singletonList(new SbAbility("Absolute Unit", AbilityType.FULL_SET_BONUS, Arrays.asList("&c+50%health% &7per &c1% Crit Damage", "&7Regain &c2% &7of &cmax %health% &7when hit. &8(1s cd)", "&7Receive &a-20% &7damage from wolves.", "&7Your crit damage is &950% &7less effective."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("mastiff_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Mastiff Leggings", 500, -1000000, null, Collections.singletonList(new SbAbility("Absolute Unit", AbilityType.FULL_SET_BONUS, Arrays.asList("&c+50%health% &7per &c1% Crit Damage", "&7Regain &c2% &7of &cmax %health% &7when hit. &8(1s cd)", "&7Receive &a-20% &7damage from wolves.", "&7Your crit damage is &950% &7less effective."))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("mastiff_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Mastiff Boots", 500, -1000000, 0, 0, 0, 25, 0, 0, null, Collections.singletonList(new SbAbility("Absolute Unit", AbilityType.FULL_SET_BONUS, Arrays.asList("&c+50%health% &7per &c1% Crit Damage", "&7Regain &c2% &7of &cmax %health% &7when hit. &8(1s cd)", "&7Receive &a-20% &7damage from wolves.", "&7Your crit damage is &950% &7less effective."))), true, SbRarity.EPIC_HELMET));
        sets.put("mastiff_armor", new SbArmor[]{armors.get("mastiff_crown"), armors.get("mastiff_chestplate"), armors.get("mastiff_leggings"), armors.get("mastiff_boots")});

        armors.put("tarantula_helmet", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.BLACK), "Tarantula Helmet", 100, 80, 0, 0, 0, 100, 0, 0, null, Arrays.asList(new SbAbility("Octodexterity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every 4th strike, deal &cdouble", "&cdamage &7and apply Venom", "&7reducing healing by &240% &7for", "&b4 &7seconds.")), new SbAbility("Spider Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Spiders to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)")), new SbAbility("Radioactive", AbilityType.EXTRA_BONUS, Arrays.asList("&7Gain &a+1&9%critDamage% Crit Damage &7per", "&c10 %strenght% Strenght &7(max &c1,000", "&c%strenght% Strenght&7)."))), true, SbRarity.EPIC_HELMET));
        armors.put("tarantula_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLACK), "Tarantula Chestplate", 120, 100, 0, 0, 0, 100, 0, 0, null, Arrays.asList(new SbAbility("Octodexterity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every 4th strike, deal &cdouble", "&cdamage &7and apply Venom", "&7reducing healing by &240% &7for", "&b4 &7seconds.")), new SbAbility("Spider Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Spiders to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)")), new SbAbility("Anti-Toxin", AbilityType.EXTRA_BONUS, Collections.singletonList("&7Immune to healing reduction."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("tarantula_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.BLACK), "Tarantula Leggings", 60, 20, null, Arrays.asList(new SbAbility("Octodexterity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every 4th strike, deal &cdouble", "&cdamage &7and apply Venom", "&7reducing healing by &240% &7for", "&b4 &7seconds.")), new SbAbility("Spider Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Spiders to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)"))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("tarantula_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.BLACK), "Tarantula Boots", 70, 100, 0, 0, 0, 50, 0, 0, null, Arrays.asList(new SbAbility("Octodexterity", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Every 4th strike, deal &cdouble", "&cdamage &7and apply Venom", "&7reducing healing by &240% &7for", "&b4 &7seconds.")), new SbAbility("Spider Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Spiders to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)")), new SbAbility("Double Jump", AbilityType.NONE, Arrays.asList("&7Allows you to double jump by", "&7sneaking mid air!"), 40)), true, SbRarity.EPIC_BOOTS));
        putSet("tarantula_armor", "tarantula");

        armors.put("spooky_helmet", null);
        armors.put("spooky_chestplate", null);
        armors.put("spooky_leggings", null);
        armors.put("spooky_boots", null);
        putSet("spooky_armor", "spooky");

        armors.put("cheap_tuxedo_jacket", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GRAY), "Cheap Tuxedo Jacket", 0, 0, 0, 0, 50, 50, 0, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c75%health%&7.", "&7Deal &c+50% &7damage!", "&8Very stylish."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("cheap_tuxedo_pants", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.SILVER), "Cheap Tuxedo Pants", 0, 0, 0, 0, 25, 25, 0, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c75%health%&7.", "&7Deal &c+50% &7damage!", "&8Very stylish."))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("cheap_tuxedo_oxfords", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GRAY), "Cheap Tuxedo Oxfords", 0, 0, 0, 0, 25, 25, 0, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c75%health%&7.", "&7Deal &c+50% &7damage!", "&8Very stylish."))), true, SbRarity.EPIC_BOOTS));
        sets.put("cheap_tuxedo", new SbArmor[]{armors.get("cheap_tuxedo_jacket"), armors.get("cheap_tuxedo_pants"), armors.get("cheap_tuxedo_oxfords")});

        armors.put("perfect_helmet_4", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier IV", 0, 170, true, SbRarity.EPIC_HELMET));
        armors.put("perfect_chestplate_4", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier IV", 0, 220, true, SbRarity.EPIC_CHESTPLATE));
        armors.put("perfect_leggings_4", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier IV", 0, 200, true, SbRarity.EPIC_LEGGINGS));
        armors.put("perfect_boots_4", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier IV", 0, 150, true, SbRarity.EPIC_BOOTS));
        sets.put("perfect_armor_4", new SbArmor[]{armors.get("perfect_helmet_4"), armors.get("perfect_chestplate_4"), armors.get("perfect_leggings_4"), armors.get("perfect_boots_4")});

        armors.put("perfect_helmet_5", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier V", 0, 190, true, SbRarity.EPIC_HELMET));
        armors.put("perfect_chestplate_5", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier V", 0, 240, true, SbRarity.EPIC_CHESTPLATE));
        armors.put("perfect_leggings_5", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier V", 0, 220, true, SbRarity.EPIC_LEGGINGS));
        armors.put("perfect_boots_5", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier V", 0, 170, true, SbRarity.EPIC_BOOTS));
        sets.put("perfect_armor_5", new SbArmor[]{armors.get("perfect_helmet_5"), armors.get("perfect_chestplate_5"), armors.get("perfect_leggings_5"), armors.get("perfect_boots_5")});

        armors.put("perfect_helmet_6", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier VI", 0, 210, true, SbRarity.EPIC_HELMET));
        armors.put("perfect_chestplate_6", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier VI", 0, 260, true, SbRarity.EPIC_CHESTPLATE));
        armors.put("perfect_leggings_6", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier VI", 0, 240, true, SbRarity.EPIC_LEGGINGS));
        armors.put("perfect_boots_6", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier VI", 0, 190, true, SbRarity.EPIC_BOOTS));
        sets.put("perfect_armor_6", new SbArmor[]{armors.get("perfect_helmet_6"), armors.get("perfect_chestplate_6"), armors.get("perfect_leggings_6"), armors.get("perfect_boots_6")});

        armors.put("perfect_helmet_7", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier VII", 0, 230, true, SbRarity.EPIC_HELMET));
        armors.put("perfect_chestplate_7", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier VII", 0, 280, true, SbRarity.EPIC_CHESTPLATE));
        armors.put("perfect_leggings_7", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier VII", 0, 260, true, SbRarity.EPIC_LEGGINGS));
        armors.put("perfect_boots_7", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier VII", 0, 210, true, SbRarity.EPIC_BOOTS));
        sets.put("perfect_armor_7", new SbArmor[]{armors.get("perfect_helmet_7"), armors.get("perfect_chestplate_7"), armors.get("perfect_leggings_7"), armors.get("perfect_boots_7")});

        armors.put("snow_suit_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2QxMTE5NjQzY2VjZmJlMDA0MTQxYTFlYTQwNjJmODMxNWNhNzhlNTAzOTU4YWM1MDUwNWU4NTkyYzQ0YTYwMSJ9fX0="), "Snow Suit Helmet", 70, 30, Arrays.asList("&7Each piece grants a &a5% &7bonus", "&7gift chance for every present", "&7you earn from the &cGift Attack", "&7minigame!", "&7All stats of this armor piece", "&7are doubled while while on the winter", "&7Island!"), Collections.singletonList(new SbAbility("Cold Thumb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Allows the wearer to shoot", "&7unlimited snowballs when using", "&9Frosty the Snow Cannon&7."))), true, SbRarity.EPIC_HELMET));
        armors.put("snow_suit_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.WHITE), "Snow Suit Chestplate", 100, 40, Arrays.asList("&7Each piece grants a &a5% &7bonus", "&7gift chance for every present", "&7you earn from the &cGift Attack", "&7minigame!", "&7All stats of this armor piece", "&7are doubled while while on the winter", "&7Island!"), Collections.singletonList(new SbAbility("Cold Thumb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Allows the wearer to shoot", "&7unlimited snowballs when using", "&9Frosty the Snow Cannon&7."))), true, SbRarity.EPIC_CHESTPLATE));
        armors.put("snow_suit_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.WHITE), "Snow Suit Leggings", 75, 30, Arrays.asList("&7Each piece grants a &a5% &7bonus", "&7gift chance for every present", "&7you earn from the &cGift Attack", "&7minigame!", "&7All stats of this armor piece", "&7are doubled while while on the winter", "&7Island!"), Collections.singletonList(new SbAbility("Cold Thumb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Allows the wearer to shoot", "&7unlimited snowballs when using", "&9Frosty the Snow Cannon&7."))), true, SbRarity.EPIC_LEGGINGS));
        armors.put("snow_suit_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.WHITE), "Snow Suit Boots", 65, 25, Arrays.asList("&7Each piece grants a &a5% &7bonus", "&7gift chance for every present", "&7you earn from the &cGift Attack", "&7minigame!", "&7All stats of this armor piece", "&7are doubled while while on the winter", "&7Island!"), Collections.singletonList(new SbAbility("Cold Thumb", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Allows the wearer to shoot", "&7unlimited snowballs when using", "&9Frosty the Snow Cannon&7."))), true, SbRarity.EPIC_BOOTS));
        putSet("snow_suit", "snow_suit");

        armors.put("mineral_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTI1ZTYxMjE5NzFlOTFjNTQxNWY2MTdlMTcxMTQ5OTBhMTQ2YTc0NmRkOTUyZDQxNzZlMjU3NjQxODNlZiJ9fX0="), "Mineral Helmet", 0, 70, 0, 0, 0, 0, 10, 0, Arrays.asList("&7Each piece of this armor grants", "&7the ability to &9automatically", "&9mine &a2 &7extra nearby blocks", "&7while mining (very short", "&7cooldown, blocks must require", "&7breaking power of less than 4)."), null, true, SbRarity.EPIC_HELMET));
        armors.put("mineral_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(204, 229, 255)), "Mineral Chestplate", 0, 125, 0, 0, 0, 0, 15, 0, Arrays.asList("&7Each piece of this armor grants", "&7the ability to &9automatically", "&9mine &a2 &7extra nearby blocks", "&7while mining (very short", "&7cooldown, blocks must require", "&7breaking power of less than 4)."), null, true, SbRarity.EPIC_CHESTPLATE));
        armors.put("mineral_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(204, 229, 255)), "Mineral Leggings", 0, 125, 0, 0, 0, 0, 15, 0, Arrays.asList("&7Each piece of this armor grants", "&7the ability to &9automatically", "&9mine &a2 &7extra nearby blocks", "&7while mining (very short", "&7cooldown, blocks must require", "&7breaking power of less than 4)."), null, true, SbRarity.EPIC_LEGGINGS));
        armors.put("mineral_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(204, 229, 255)), "Mineral Boots", 0, 70, 0, 0, 0, 0, 10, 0, Arrays.asList("&7Each piece of this armor grants", "&7the ability to &9automatically", "&9mine &a2 &7extra nearby blocks", "&7while mining (very short", "&7cooldown, blocks must require", "&7breaking power of less than 4)."), null, true, SbRarity.EPIC_BOOTS));
        putSet("mineral_armor", "mineral");

        armors.put("glacite_helmet", null);
        armors.put("glacite_chestplate", null);
        armors.put("glacite_leggings", null);
        armors.put("glacite_boots", null);
        putSet("glacite_armor", "glacite");

        armors.put("helmet_of_the_rising_sun", null);
        armors.put("chestplate_of_the_rising_sun", null);
        armors.put("leggings_of_the_rising_sun", null);
        armors.put("boots_of_the_rising_sun", null);

        armors.put("flamebreaker_helmet", null);
        armors.put("flamebreaker_chestplate", null);
        armors.put("flamebreaker_leggings", null);
        armors.put("flamebreaker_boots", null);
        putSet("flambreaker_armor", "flamebreaker");

        armors.put("berserker_helmet", null);
        armors.put("berserker_chestplate", null);
        armors.put("berserker_leggings", null);
        armors.put("berserker_boots", null);
        putSet("berserker_armor", "berserker");

        armors.put("frozen_blaze_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTVhMTNiYjQ4ZTM1OTViNTVkZThkZDY5NDNmYzM4ZGI1MjM1MzcxMjc4YzY5NWJkNDUzZTQ5YTA5OTkifX19"), "Frozen Blaze Helmet", 0, 105, 40, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Frozen Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs in a &a5 &7blocks", "&7range for &a300 &7base damage +", "&a3% &7of their max &c%health% Health", "&7every second and applies", "&bSlowness I &7for &e4", "&7seconds.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60", "&8+100 per 5,000 rods", "", "&7Permanent &cFire &7and &cLava &7immunity."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("frozen_blaze_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(160, 218, 239)), "Frozen Blaze Chestplate", 0, 180, 40, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Frozen Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs in a &a5 &7blocks", "&7range for &a300 &7base damage +", "&a3% &7of their max &c%health% Health", "&7every second and applies", "&bSlowness I &7for &e4", "&7seconds.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60", "&8+100 per 5,000 rods", "", "&7Permanent &cFire &7and &cLava &7immunity."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("frozen_blaze_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(160, 218, 239)), "Frozen Blaze Leggings", 0, 140, 40, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Frozen Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs in a &a5 &7blocks", "&7range for &a300 &7base damage +", "&a3% &7of their max &c%health% Health", "&7every second and applies", "&bSlowness I &7for &e4", "&7seconds.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60", "&8+100 per 5,000 rods", "", "&7Permanent &cFire &7and &cLava &7immunity."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("frozen_blaze_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(160, 218, 239)), "Frozen Blaze Boots", 0, 95, 40, 0, 0, 0, 2, 0, null, Collections.singletonList(new SbAbility("Frozen Blazing Aura", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Damages mobs in a &a5 &7blocks", "&7range for &a300 &7base damage +", "&a3% &7of their max &c%health% Health", "&7every second and applies", "&bSlowness I &7for &e4", "&7seconds.", "", "&7Max &c500 &7damage/s", "&7Blaze Rod Collection: &60", "&8+100 per 5,000 rods", "", "&7Permanent &cFire &7and &cLava &7immunity."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("frozen_blaze_armor", "frozen_blaze");

        armors.put("perfect_helmet_8", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier VIII", 0, 250, true, SbRarity.LEGENDARY_HELMET));
        armors.put("perfect_chestplate_8", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier VIII", 0, 300, true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("perfect_leggings_8", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier VIII", 0, 280, true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("perfect_boots_8", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier VIII", 0, 230, true, SbRarity.LEGENDARY_BOOTS));
        sets.put("perfect_armor_8", new SbArmor[]{armors.get("perfect_helmet_8"), armors.get("perfect_chestplate_8"), armors.get("perfect_leggings_8"), armors.get("perfect_boots_8")});

        armors.put("perfect_helmet_9", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier IX", 0, 270, true, SbRarity.LEGENDARY_HELMET));
        armors.put("perfect_chestplate_9", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier IX", 0, 320, true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("perfect_leggings_9", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier IX", 0, 300, true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("perfect_boots_9", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier IX", 0, 250, true, SbRarity.LEGENDARY_BOOTS));
        sets.put("perfect_armor_9", new SbArmor[]{armors.get("perfect_helmet_9"), armors.get("perfect_chestplate_9"), armors.get("perfect_leggings_9"), armors.get("perfect_boots_9")});

        armors.put("perfect_helmet_10", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier X", 0, 290, true, SbRarity.LEGENDARY_HELMET));
        armors.put("perfect_chestplate_10", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier X", 0, 340, true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("perfect_leggings_10", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier X", 0, 320, true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("perfect_boots_10", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier X", 0, 270, true, SbRarity.LEGENDARY_BOOTS));
        sets.put("perfect_armor_10", new SbArmor[]{armors.get("perfect_helmet_10"), armors.get("perfect_chestplate_10"), armors.get("perfect_leggings_10"), armors.get("perfect_boots_10")});

        armors.put("perfect_helmet_11", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier XI", 0, 310, true, SbRarity.LEGENDARY_HELMET));
        armors.put("perfect_chestplate_11", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier XI", 0, 360, true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("perfect_leggings_11", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier XI", 0, 340, true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("perfect_boots_11", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier XI", 0, 290, true, SbRarity.LEGENDARY_BOOTS));
        sets.put("perfect_armor_11", new SbArmor[]{armors.get("perfect_helmet_11"), armors.get("perfect_chestplate_11"), armors.get("perfect_leggings_11"), armors.get("perfect_boots_11")});

        armors.put("perfect_helmet_12", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Perfect Helmet - Tier XII", 0, 330, true, SbRarity.LEGENDARY_HELMET));
        armors.put("perfect_chestplate_12", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Perfect Chestplate - Tier XII", 0, 380, true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("perfect_leggings_12", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Perfect Leggings - Tier XII", 0, 360, true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("perfect_boots_12", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Perfect Boots - Tier XII", 0, 310, true, SbRarity.LEGENDARY_BOOTS));
        sets.put("perfect_armor_12", new SbArmor[]{armors.get("perfect_helmet_12"), armors.get("perfect_chestplate_12"), armors.get("perfect_leggings_12"), armors.get("perfect_boots_12")});

        armors.put("bat_person_helmet", null);
        armors.put("bat_person_chestplate", null);
        armors.put("bat_person_leggings", null);
        armors.put("bat_person_boots", null);
        putSet("bat_person_armor", "bat_person");

        armors.put("divers_mask", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc4MzBjMWQ4Mjg0NWM5MWI4MzQyOWY5ZGM1OTczMTc4NDE1MzhlMTRkNGZiZWQ2MWFlMWEzYjBlYjdjY2QifX19"), "Diver's Mask", 65, 120, 0, 0, 0, 0, 0, 2, null, Collections.singletonList(new SbAbility("One with the Fish", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While touching water you move", "&9incredibly fast &7and can", "&7breathe &9permanently (sneak to", "&9slow down)."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("divers_shirt", new SbArmor(new ItemStack(Material.GOLDEN_CHESTPLATE), "Diver's Shirt", 100, 200, 0, 0, 0, 0, 0, 2, null, Collections.singletonList(new SbAbility("One with the Fish", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While touching water you move", "&9incredibly fast &7and can", "&7breathe &9permanently (sneak to", "&9slow down)."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("divers_trunks", new SbArmor(new ItemStack(Material.GOLDEN_LEGGINGS), "Diver's Trunks", 75, 170, 0, 0, 0, 0, 0, 2, null, Collections.singletonList(new SbAbility("One with the Fish", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While touching water you move", "&9incredibly fast &7and can", "&7breathe &9permanently (sneak to", "&9slow down)."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("divers_boots", new SbArmor(new ItemStack(Material.GOLDEN_BOOTS), "Diver's Boots", 60, 110, 0, 0, 0, 0, 0, 2, null, Collections.singletonList(new SbAbility("One with the Fish", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While touching water you move", "&9incredibly fast &7and can", "&7breathe &9permanently (sneak to", "&9slow down)."))), true, SbRarity.LEGENDARY_BOOTS));
        sets.put("divers_armor", new SbArmor[]{armors.get("divers_mask"), armors.get("divers_shirt"), armors.get("divers_trunks"), armors.get("divers_boots")});

        armors.put("fancy_tuxedo_jacket", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(51, 42, 42)), "Fancy Tuxedo Jacket", 0, 0, 0, 0, 80, 150, 0, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c150%health%&7.", "&7Deal &c+100% &7damage!", "&8Very stylish."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("fancy_tuxedo_pants", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GRAY), "Fancy Tuxedo Pants", 0, 0, 0, 0, 35, 75, 0, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c150%health%&7.", "&7Deal &c+100% &7damage!", "&8Very stylish."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("fancy_tuxedo_oxfords", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(51, 42, 42)), "Fancy Tuxedo Oxfords", 0, 0, 0, 0, 35, 75, 10, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c150%health%&7.", "&7Deal &c+100% &7damage!", "&8Very stylish."))), true, SbRarity.LEGENDARY_BOOTS));
        sets.put("fancy_tuxedo", new SbArmor[]{armors.get("fancy_tuxedo_jacket"), armors.get("fancy_tuxedo_pants"), armors.get("fancy_tuxedo_oxfords")});

        armors.put("elegant_tuxedo_jacket", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLACK), "Elegant Tuxedo Jacket", 0, 0, 0, 0, 100, 300, 0, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c250%health%&7.", "&7Deal &c+150% &7damage!", "&8Very stylish."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("elegant_tuxedo_pants", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GRAY), "Elegant Tuxedo Pants", 0, 0, 0, 0, 50, 100, 0, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c250%health%&7.", "&7Deal &c+150% &7damage!", "&8Very stylish."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("elegant_tuxedo_oxfords", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.BLACK), "Elegant Tuxedo Oxfords", 0, 0, 0, 0, 50, 100, 10, 0, null, Collections.singletonList(new SbAbility("Dashing", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Max health set to &c250%health%&7.", "&7Deal &c+150% &7damage!", "&8Very stylish."))), true, SbRarity.LEGENDARY_BOOTS));
        sets.put("elegant_tuxedo", new SbArmor[]{armors.get("elegant_tuxedo_jacket"), armors.get("elegant_tuxedo_pants"), armors.get("elegant_tuxedo_oxfords")});

        armors.put("werewolf_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2U0NjA2YzZkOTczYTk5OWFlYzE2ODdjN2UwNzVmN2QzN2RiODE4NWU4OGI4NDQ1MDdmMTZiM2UyYjNlYjY5MCJ9fX0="), "Werewolf Helmet", 0, 135, 0, 0, 0, 50, 0, 0, 10, null, Collections.singletonList(new SbAbility("Regenerative Howl", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Upon activating &c%ferocity%Ferocity", "&7heal players within &925", "&7blocks for &910% &7of your &a%defense%", "&cDefense &7and gain &a50%defense% Defense", "&7for 5s (up to 10 stacks)."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("werewolf_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLACK), "Werewolf Chestplate", 0, 240, 0, 0, 0, 50, 0, 0, 10, null, Collections.singletonList(new SbAbility("Regenerative Howl", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Upon activating &c%ferocity%Ferocity", "&7heal players within &925", "&7blocks for &910% &7of your &a%defense%", "&cDefense &7and gain &a50%defense% Defense", "&7for 5s (up to 10 stacks)."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("werewolf_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.BLACK), "Werewolf Leggings", 0, 200, 0, 0, 0, 50, 0, 0, 10, null, Collections.singletonList(new SbAbility("Regenerative Howl", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Upon activating &c%ferocity%Ferocity", "&7heal players within &925", "&7blocks for &910% &7of your &a%defense%", "&cDefense &7and gain &a50%defense% Defense", "&7for 5s (up to 10 stacks)."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("werewolf_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.BLACK), "Werewolf Boots", 0, 125, 0, 0, 0, 50, 0, 0, 10, null, Collections.singletonList(new SbAbility("Regenerative Howl", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Upon activating &c%ferocity%Ferocity", "&7heal players within &925", "&7blocks for &910% &7of your &a%defense%", "&cDefense &7and gain &a50%defense% Defense", "&7for 5s (up to 10 stacks)."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("werewolf_armor", "werewolf");

        armors.put("shark_scale_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzkwMTE2ZGE1MTRhNzNkZGQ5Mjk1NDkxODUyYWRjNTg4MDA3ZjJhY2Q3ZGUyNjk3ODhhMjEyOGQ4ZTRhNzY0YiJ9fX0="), "Shark Scale Helmet", 120, 120, 0, 0, 0, 0, 0, 2.5, null, Arrays.asList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water.")), new SbAbility("Reflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Your sharp scales reflect", "&915% &7of damage onto the", "&7attacker."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("shark_scale_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(0, 44, 166)), "Shark Scale Chestplate", 175, 175, 0, 0, 0, 0, 0, 2.5, null, Arrays.asList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water.")), new SbAbility("Reflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Your sharp scales reflect", "&915% &7of damage onto the", "&7attacker."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("shark_scale_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(0, 44, 166)), "Shark Scale Leggings", 150, 150, 0, 0, 0, 0, 0, 2.5, null, Arrays.asList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water.")), new SbAbility("Reflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Your sharp scales reflect", "&915% &7of damage onto the", "&7attacker."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("shark_scale_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(0, 44, 166)), "Shark Scale Boots", 100, 100, 0, 0, 0, 0, 0, 2.5, null, Arrays.asList(new SbAbility("Absorb", AbilityType.FULL_SET_BONUS, Arrays.asList("&9Doubles &7your &a%defense%Defense", "&7while in water.")), new SbAbility("Reflect", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Your sharp scales reflect", "&915% &7of damage onto the", "&7attacker."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("shark_scale_armor", "shark_scale");

        armors.put("diamond_hunter_helmet", null);
        armors.put("diamond_hunter_chestplate", null);
        armors.put("diamond_hunter_leggings", null);
        armors.put("diamond_hunter_boots", null);
        putSet("diamond_hunter_armor", "diamond_hunter");

        armors.put("magma_lord_helmet", null);
        armors.put("magma_lord_chestplate", null);
        armors.put("magma_lord_leggings", null);
        armors.put("magma_lord_boots", null);
        putSet("magma_lord_armor", "magma_lord");

        armors.put("sorrow_helmet", null);
        armors.put("sorrow_chestplate", null);
        armors.put("sorrow_leggings", null);
        armors.put("sorrow_boots", null);
        putSet("sorrow_armor", "sorrow");

        armors.put("reaper_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLACK), "Reaper Chestplate", 180, 70, 25, 0, 0, 250, 0, 0, null, Arrays.asList(new SbAbility("Enrage " + ChatColor.YELLOW + ""  + ChatColor.BOLD + "SNEAK", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Enrage for &a6s &7gaining &f100%speed%", "&fSpeed&7, &c100%strenght% Damage&7, and &c100%strenght%", "&cStrenght."), 0, 25), new SbAbility("Trolling The Reaper", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Healing Wands heal &a+50%&7.", "&7Gain &a+100%defense% &7against Zombies.", "&7Deal &c+100% &7damage to zombies", "&7but &c1% &7to all other mobs.")), new SbAbility("Zombie Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Zombies to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)"))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("reaper_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.BLACK), "Reaper Leggings", 120, 50, 25, 0, 0, 250, 0, 0, null, Arrays.asList(new SbAbility("Enrage " + ChatColor.YELLOW + ""  + ChatColor.BOLD + "SNEAK", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Enrage for &a6s &7gaining &f100%speed%", "&fSpeed&7, &c100%strenght% Damage&7, and &c100%strenght%", "&cStrenght."), 0, 25), new SbAbility("Trolling The Reaper", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Healing Wands heal &a+50%&7.", "&7Gain &a+100%defense% &7against Zombies.", "&7Deal &c+100% &7damage to zombies", "&7but &c1% &7to all other mobs.")), new SbAbility("Zombie Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Zombies to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)"))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("reaper_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.BLACK), "Reaper Boots", 100, 30, 25, 0, 0, 250, 0, 0, null, Arrays.asList(new SbAbility("Enrage " + ChatColor.YELLOW + ""  + ChatColor.BOLD + "SNEAK", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Enrage for &a6s &7gaining &f100%speed%", "&fSpeed&7, &c100%strenght% Damage&7, and &c100%strenght%", "&cStrenght."), 0, 25), new SbAbility("Trolling The Reaper", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Healing Wands heal &a+50%&7.", "&7Gain &a+100%defense% &7against Zombies.", "&7Deal &c+100% &7damage to zombies", "&7but &c1% &7to all other mobs.")), new SbAbility("Zombie Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Zombies to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c50&8)"))), true, SbRarity.LEGENDARY_CHESTPLATE));
        putSet("reaper_armor", "reaper");

        armors.put("final_destination_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTc3ZTZmZDNiOTMxNzE5ZDQ0ZWYwZjU3MThhMGZlMzQ2NDg2YmQzZTVlOTgwMGRmYmZjMjQ4YThjZjE0MDBiZSJ9fX0="), "Final Destination Helmet", 140, 100, 0, 0, 0, 100, 0, 0, null, Arrays.asList(new SbAbility("Vivacious Darkness", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Costs &32%soulFlow% &7per 5s", "&7in combat while &asneaking&7:", "&3⁍&c+100%strenght% Strenght", "&3⁍&e+30%attackSpeed% Bonus Attack Speed", "&3⁍&f+10%speed% Speed", "&3⁍&7Multiply &b%intelligence%Intelligence &7by &b1.5x", "&3⁍&c+200%ferocity% Ferocity &7against Endermen", "&3⁍&a+100% &7damage against Endermen")), new SbAbility("Enderman Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Endermen to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c100&8)"))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("final_destination_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLACK), "Final Destination Chestplate", 200, 100, 0, 0, 0, 100, 0, 0, null, Arrays.asList(new SbAbility("Vivacious Darkness", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Costs &32%soulFlow% &7per 5s", "&7in combat while &asneaking&7:", "&3⁍&c+100%strenght% Strenght", "&3⁍&e+30%attackSpeed% Bonus Attack Speed", "&3⁍&f+10%speed% Speed", "&3⁍&7Multiply &b%intelligence%Intelligence &7by &b1.5x", "&3⁍&c+200%ferocity% Ferocity &7against Endermen", "&3⁍&a+100% &7damage against Endermen")), new SbAbility("Enderman Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Endermen to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c100&8)"))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("final_destination_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(255, 117, 255)), "Final Destination Leggings", 160, 100, 0, 0, 0, 100, 0, 0, null, Arrays.asList(new SbAbility("Vivacious Darkness", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Costs &32%soulFlow% &7per 5s", "&7in combat while &asneaking&7:", "&3⁍&c+100%strenght% Strenght", "&3⁍&e+30%attackSpeed% Bonus Attack Speed", "&3⁍&f+10%speed% Speed", "&3⁍&7Multiply &b%intelligence%Intelligence &7by &b1.5x", "&3⁍&c+200%ferocity% Ferocity &7against Endermen", "&3⁍&a+100% &7damage against Endermen")), new SbAbility("Enderman Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Endermen to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c100&8)"))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("final_destination_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.BLACK), "Final Destination Boots", 100, 100, 0, 0, 0, 100, 0, 0, null, Arrays.asList(new SbAbility("Vivacious Darkness", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Costs &32%soulFlow% &7per 5s", "&7in combat while &asneaking&7:", "&3⁍&c+100%strenght% Strenght", "&3⁍&e+30%attackSpeed% Bonus Attack Speed", "&3⁍&f+10%speed% Speed", "&3⁍&7Multiply &b%intelligence%Intelligence &7by &b1.5x", "&3⁍&c+200%ferocity% Ferocity &7against Endermen", "&3⁍&a+100% &7damage against Endermen")), new SbAbility("Enderman Bulwark", AbilityType.PIECE_BONUS, Arrays.asList("&7Kill Endermen to accumulate", "&7defense against them.", "&7Piece Bonus: &a+0%defense%", "&7Next Upgrade: &a+20%defense% &8(&a0&7/&c100&8)"))), true, SbRarity.LEGENDARY_HELMET));
        putSet("final_destination_armor", "final_destination");

        armors.put("helmet_of_divan", null);
        armors.put("chestplate_of_divan", null);
        armors.put("leggings_of_divan", null);
        armors.put("boots_of_divan", null);
        sets.put("armor_of_diva", new SbArmor[]{armors.get("helmet_of_divan"), armors.get("chestplate_of_divan"), armors.get("leggings_of_divan"), armors.get("boots_of_divan")});

        armors.put("young_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWM0ODZhZjNiODgyNzY2ZTgyYTBiYzE2NjVmZjAyZWI2ZTg3M2I2ZTBkNzcxZjNhZGFiYzc1OWI3MjAyMjZhIn19fQ=="), "Young Dragon Helmet", 70, 110, 0, 0, 0, 0, 20, 0, null, Collections.singletonList(new SbAbility("Young Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &a+70 &7Walk Speed while", "&7you are above &a50% &7HP.", "&8+100 Walk Speed Cap"))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("young_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.WHITE), "Young Dragon Chestplate", 120, 160, 0, 0, 0, 0, 20, 0, null, Collections.singletonList(new SbAbility("Young Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &a+70 &7Walk Speed while", "&7you are above &a50% &7HP.", "&8+100 Walk Speed Cap"))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("young_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.WHITE), "Young Dragon Leggings", 100, 140, 0, 0, 0, 0, 20, 0, null, Collections.singletonList(new SbAbility("Young Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &a+70 &7Walk Speed while", "&7you are above &a50% &7HP.", "&8+100 Walk Speed Cap"))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("young_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.WHITE), "Young Dragon Boots", 60, 90, 0, 0, 0, 0, 20, 0, null, Collections.singletonList(new SbAbility("Young Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &a+70 &7Walk Speed while", "&7you are above &a50% &7HP.", "&8+100 Walk Speed Cap"))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("young_dragon_armor", "young_dragon");

        armors.put("old_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTllOWU1NjAwNDEwYzFkMDI1NDQ3NGE4MWZlY2ZiMzg4NWMxY2Y2ZjUwNDE5MGQ4NTZmMGVjN2M5ZjA1NWM0MiJ9fX0="), "Old Dragon Helmet", 90, 110, null, Collections.singletonList(new SbAbility("Old Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the strenght of", "&9Growth&7, &9Protection&7,", "&9Feather Falling&, &9Sugar", "&9Rush&7, and &9True Protection", "&7while worn."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("old_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(240, 230, 170)), "Old Dragon Helmet", 150, 160, null, Collections.singletonList(new SbAbility("Old Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the strenght of", "&9Growth&7, &9Protection&7,", "&9Feather Falling&, &9Sugar", "&9Rush&7, and &9True Protection", "&7while worn."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("old_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(240, 230, 170)), "Old Dragon Helmet", 130, 140, null, Collections.singletonList(new SbAbility("Old Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the strenght of", "&9Growth&7, &9Protection&7,", "&9Feather Falling&, &9Sugar", "&9Rush&7, and &9True Protection", "&7while worn."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("old_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(240, 230, 170)), "Old Dragon Helmet", 80, 90, null, Collections.singletonList(new SbAbility("Old Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the strenght of", "&9Growth&7, &9Protection&7,", "&9Feather Falling&, &9Sugar", "&9Rush&7, and &9True Protection", "&7while worn."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("old_dragon_armor", "old_dragon");

        armors.put("wise_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWEyOTg0Y2YwN2M0OGRhOTcyNDgxNmE4ZmYwODY0YmM2OGJjZTY5NGNlOGJkNmRiMjExMmI2YmEwMzEwNzBkZSJ9fX0="), "Wise Dragon Helmet", 70, 110, 0, 0, 0, 125, 0, 0, null, Collections.singletonList(new SbAbility("Wise Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Abilities have &a2/3 &7of the", "&7mana cost."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("wise_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB(41, 240, 233)), "Wise Dragon Chestplate", 120, 160, 0, 0, 0, 75, 0, 0, null, Collections.singletonList(new SbAbility("Wise Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Abilities have &a2/3 &7of the", "&7mana cost."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("wise_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB(41, 240, 233)), "Wise Dragon Leggings", 100, 140, 0, 0, 0, 75, 0, 0, null, Collections.singletonList(new SbAbility("Wise Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Abilities have &a2/3 &7of the", "&7mana cost."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("wise_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB(41, 240, 233)), "Wise Dragon Boots", 60, 90, 0, 0, 0, 75, 0, 0, null, Collections.singletonList(new SbAbility("Wise Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Abilities have &a2/3 &7of the", "&7mana cost."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("wise_dragon_armor", "wise_dragon");

        armors.put("protector_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjM3YTU5NmNkYzRiMTFhOTk0OGZmYTM4YzJhYTNjNjk0MmVmNDQ5ZWIwYTM5ODIyODFkM2E1YjVhMTRlZjZhZSJ9fX0="), "Protector Dragon Helmet", 70, 135, null, Collections.singletonList(new SbAbility("Protective Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the defense of each", "&7armor piece by &a+1% %defense%", "&aDefense &7for each missing", "&7percent of HP."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("protector_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GRAY), "Protector Dragon Chestplate", 120, 185, null, Collections.singletonList(new SbAbility("Protective Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the defense of each", "&7armor piece by &a+1% %defense%", "&aDefense &7for each missing", "&7percent of HP."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("protector_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GRAY), "Protector Dragon Leggings", 100, 165, null, Collections.singletonList(new SbAbility("Protective Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the defense of each", "&7armor piece by &a+1% %defense%", "&aDefense &7for each missing", "&7percent of HP."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("protector_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GRAY), "Protector Dragon Boots", 60, 115, null, Collections.singletonList(new SbAbility("Protective Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the defense of each", "&7armor piece by &a+1% %defense%", "&aDefense &7for each missing", "&7percent of HP."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("protector_dragon_armor", "protector_dragon");

        armors.put("strong_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWZkZTA5NjAzYjAyMjViOWQyNGE3M2EwZDNmM2UzYWYyOTA1OGQ0NDhjY2Q3Y2U1YzY3Y2QwMmZhYjBmZjY4MiJ9fX0="), "Strong Dragon Helmet", 70, 110, 25, 0, 0, 0, 0, 0, null, Collections.singletonList(new SbAbility("Strong Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Improves &9Aspect of the End", "&8⋗&c+75 Damage", "", "&7Instant Transmission:", "&8⋗&a+2 &7teleport range", "&8⋗&a+3 &7seconds", "&8⋗&c+5%strenght% Strenght on cast"))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("strong_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.RED), "Strong Dragon Chestplate", 120, 160, 25, 0, 0, 0, 0, 0, null, Collections.singletonList(new SbAbility("Strong Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Improves &9Aspect of the End", "&8⋗&c+75 Damage", "", "&7Instant Transmission:", "&8⋗&a+2 &7teleport range", "&8⋗&a+3 &7seconds", "&8⋗&c+5%strenght% Strenght on cast"))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("strong_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.ORANGE), "Strong Dragon Leggings", 100, 140, 25, 0, 0, 0, 0, 0, null, Collections.singletonList(new SbAbility("Strong Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Improves &9Aspect of the End", "&8⋗&c+75 Damage", "", "&7Instant Transmission:", "&8⋗&a+2 &7teleport range", "&8⋗&a+3 &7seconds", "&8⋗&c+5%strenght% Strenght on cast"))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("strong_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.YELLOW), "Strong Dragon Boots", 60, 90, 25, 0, 0, 0, 0, 0, null, Collections.singletonList(new SbAbility("Strong Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Improves &9Aspect of the End", "&8⋗&c+75 Damage", "", "&7Instant Transmission:", "&8⋗&a+2 &7teleport range", "&8⋗&a+3 &7seconds", "&8⋗&c+5%strenght% Strenght on cast"))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("strong_dragon_armor", "strong_dragon");

        armors.put("unstable_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkyMmI1ZjhkNTU0Y2E5MjNmOTY4MzJhNWE0ZTkxNjliYzJjZGIzNjBhMmIwNmViZWMwOWI2YTZhZjQ2MThlMyJ9fX0="), "Unstable Dragon Helmet", 70, 110, 0, 5, 15, 25, 0, 0, null, Collections.singletonList(new SbAbility("Unstable Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Sometimes strikes nearby mobs", "&7with lightning. It's unstable!"))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("unstable_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.PURPLE), "Unstable Dragon Chestplate", 120, 160, 0, 5, 15, 25, 0, 0, null, Collections.singletonList(new SbAbility("Unstable Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Sometimes strikes nearby mobs", "&7with lightning. It's unstable!"))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("unstable_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.PURPLE), "Unstable Dragon Leggings", 100, 140, 0, 5, 15, 25, 0, 0, null, Collections.singletonList(new SbAbility("Unstable Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Sometimes strikes nearby mobs", "&7with lightning. It's unstable!"))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("unstable_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.PURPLE), "Unstable Dragon Boots", 60, 90, 0, 5, 15, 25, 0, 0, null, Collections.singletonList(new SbAbility("Unstable Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Sometimes strikes nearby mobs", "&7with lightning. It's unstable!"))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("unstable_dragon_armor", "unstable_dragon");

        armors.put("holy_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDEzYWQ0ZmE0ODExOGQxMGExZWY0MmZkNmQ1ODU0NzIyMDNiZDg4YTk4YTA4N2FiNDMxODJhYTA0OTNlYTg0MiJ9fX0="), "Holy Dragon Helmet", 110, 110, null, Collections.singletonList(new SbAbility("Holy Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the natural health", "&7regeneration of you and all", "players in a &a6 &7block radius", "&7by &a3x&7."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("holy_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GREEN), "Holy Dragon Chestplate", 180, 160, null, Collections.singletonList(new SbAbility("Holy Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the natural health", "&7regeneration of you and all", "players in a &a6 &7block radius", "&7by &a3x&7."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("holy_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GREEN), "Holy Dragon Leggings", 155, 140, null, Collections.singletonList(new SbAbility("Holy Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the natural health", "&7regeneration of you and all", "players in a &a6 &7block radius", "&7by &a3x&7."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("holy_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GREEN), "Holy Dragon Boots", 100, 90, null, Collections.singletonList(new SbAbility("Holy Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Increases the natural health", "&7regeneration of you and all", "players in a &a6 &7block radius", "&7by &a3x&7."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("holy_dragon_armor", "holy_dragon");

        armors.put("superior_dragon_helmet", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0="), "Superior Dragon Helmet", 90, 130, 10, 2, 10, 25, 3, 0, null, Collections.singletonList(new SbAbility("Superior Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Most of your stats are increased", "&7by &a5% &7and &6Aspect of the", "&6Dragons &7ability deals &a50%", "&7more damage."))), true, SbRarity.LEGENDARY_HELMET));
        armors.put("superior_dragon_chestplate", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.YELLOW), "Superior Dragon Chestplate", 150, 190, 10, 2, 10, 25, 3, 0, null, Collections.singletonList(new SbAbility("Superior Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Most of your stats are increased", "&7by &a5% &7and &6Aspect of the", "&6Dragons &7ability deals &a50%", "&7more damage."))), true, SbRarity.LEGENDARY_CHESTPLATE));
        armors.put("superior_dragon_leggings", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.YELLOW), "Superior Dragon Leggings", 130, 170, 10, 2, 10, 25, 3, 0, null, Collections.singletonList(new SbAbility("Superior Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Most of your stats are increased", "&7by &a5% &7and &6Aspect of the", "&6Dragons &7ability deals &a50%", "&7more damage."))), true, SbRarity.LEGENDARY_LEGGINGS));
        armors.put("superior_dragon_boots", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.ORANGE), "Superior Dragon Boots", 80, 110, 10, 2, 10, 25, 3, 0, null, Collections.singletonList(new SbAbility("Superior Blood", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Most of your stats are increased", "&7by &a5% &7and &6Aspect of the", "&6Dragons &7ability deals &a50%", "&7more damage."))), true, SbRarity.LEGENDARY_BOOTS));
        putSet("superior_dragon_armor", "superior_dragon");

        // Admin items
        items.put("sword_of_the_stars", new SbItem(new ItemStack(Material.GOLDEN_SWORD), "Sword Of The Stars", 99999, 0, Arrays.asList("&d\"Only those with the power to", "&dcreate this world can wield this", "&dblade\""), null, true, SbRarity.SPECIAL_SWORD));
        armors.put("boss", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjAyMGMzMzY5MjhkOTRmODhiZmNlYjUxODE4YTY0MDQwYjgyNjZkNDk3ZDM4ZWUxMjhlNDNhNGUwMmUyYTM2In19fQ=="), "Boss", 1000, 1000, true, SbRarity.LEGENDARY_HELMET));
        items.put("dctrs_space_helmet", new DctrsSpaceHelmet());
    }
}
