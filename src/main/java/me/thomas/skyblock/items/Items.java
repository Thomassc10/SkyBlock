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
    private static final Map<String, SbItem> bows = new HashMap<>();
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
        sets.put(key, new SbArmor[]{armors.get(string + "_helmet"), armors.get(string + "_chesplate"), armors.get(string + "_leggings"), armors.get(string + "_boots")});
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
        items.put("bonzo_staff", new BonzoStaff()); // maybe work?
        items.put("ragnarock_axe", new RagnarockAxe()); // probably works, or not
        items.put("blade_of_the_volcano", null); // blaze?
        items.put("end_stone_sword", new EndStoneSword());
        items.put("ember_rod", new EmberRod());
        items.put("ornate_zombie_sword", new OrnateZombieSword());
        items.put("jerry-chine_gun", new JerryChineGun());
        items.put("ink_wand", new InkWand()); // ...
        items.put("emerald_blade", new EmeraldBlade()); // make purse, formula: coins^1/4 * 2.5
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
        items.put("sinseeker_scythe", null); // kinda difficult
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
        items.put("aspect_of_the_dragons", new AspectOfTheDragons()); // missing particles, discover how to rotate 90º
        items.put("silk-edge_sword", new SilkEdgeSword());
        items.put("pigman_sword", new PigmanSword()); // missing particles, rotate again 90º
        items.put("yeti_sword", null); // falling blocks...
        items.put("midas_sword", null); // coins and dark auction
        items.put("midas_staff", null); // coins, dark auction and falling blocks
        items.put("daedalus_axe", null); // mythological event
        items.put("reaper_scythe", new ReaperScythe()); // check spawning mobs, add custom pathfinder, chance for souls, mana cost, remove when player dies
        items.put("axe_of_the_shredded", new AxeOfTheShredded()); // missing ability
        items.put("pooch_sword", null); // nah
        items.put("atomsplit_katana", new AtomsplitKatana()); // missing ferocity
        items.put("pyrochaos_dagger", null); // blaze slayer
        items.put("deathripper_dagger", null); // blaze slayer
        items.put("flower_of_truth", new FlowerOfTruth()); // probably ok? check armor stand position
        items.put("spirit_sceptre", new SpiritSceptre()); // seems fine
        items.put("shadow_fury", new ShadowFury());
        items.put("livid_dagger", new LividDagger()); // missing right click ability (how to spin arm in place?)
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
        items.put("decent_bow", new SbItem(new ItemStack(Material.BOW), "Bow", 45, 0, null, null, true, SbRarity.UNCOMMON_BOW));
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
        items.put("runaans_bow", new RunaansBow()); // check for arrows vectors, aiming arrows
        items.put("mosquito_bow", new MosquitoBow()); // maybe change how additional damage is dealt
        items.put("terminator", new Terminator()); // check if beam hits more than 1 entity (maybe use vectors or 'getLineOfSight' instead), make beam always crit, check if crit chance works
        items.put("spirit_bow", new SpiritBow()); // missing dungeon ability (but there is no dungeons "yet" :/)
        items.put("last_breath", new LastBreath()); // need to create entities
        items.put("bonemerang", new Bonemerang()); // check armor stand pose

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
        armors.put("leaflet_tunic", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.GREEN), "Leaflet Hat", 35, 0, null, Collections.singletonList(new SbAbility("Energy of the Forest", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While in a Forest zone you", "&7regain &a5.0&c%health%Health &7every", "&7seconds."))), true, SbRarity.COMMON_CHESTPLATE));
        armors.put("leaflet_pants", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.GREEN), "Leaflet Hat", 30, 0, null, Collections.singletonList(new SbAbility("Energy of the Forest", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While in a Forest zone you", "&7regain &a5.0&c%health%Health &7every", "&7seconds."))), true, SbRarity.COMMON_LEGGINGS));
        armors.put("leaflet_sandals", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_BOOTS), Color.GREEN), "Leaflet Hat", 15, 0, null, Collections.singletonList(new SbAbility("Energy of the Forest", AbilityType.FULL_SET_BONUS, Arrays.asList("&7While in a Forest zone you", "&7regain &a5.0&c%health%Health &7every", "&7seconds."))), true, SbRarity.COMMON_BOOTS));
        sets.put("leaflet_armor", new SbArmor[]{armors.get("leaflet_hat"), armors.get("leaflet_tunic"), armors.get("leaflet_pants"), armors.get("leaflet_boots")});

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

        armors.put("miner_helmet", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Miner Helmet", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_HELMET));
        armors.put("miner_chesplate", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Miner Chesplate", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_CHESTPLATE));
        armors.put("miner_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Miner Leggings", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_LEGGINGS));
        armors.put("miner_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Miner Boots", 0, 20, Arrays.asList("&7Each piece of this armour", "&7dramatically increases your", "&7defense bonus when inside of a", "&7mine."), Collections.singletonList(new SbAbility("Regeneration", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Regenerates &a5% &7of your max", "&c%health%Health &every second if you", "&7have been out of combat for &a8", "&7seconds."))), true, SbRarity.RARE_BOOTS));
        putSet("miner_armor", "miner");

        armors.put("hardened_diamond_helmet", new SbArmor(new ItemStack(Material.DIAMOND_HELMET), "Hardened Diamond Helmet", 0, 60, true, SbRarity.RARE_HELMET));
        armors.put("hardened_diamond_chestplate", new SbArmor(new ItemStack(Material.DIAMOND_CHESTPLATE), "Hardened Diamond Chestplate", 0, 120, true, SbRarity.RARE_CHESTPLATE));
        armors.put("hardened_diamond_leggings", new SbArmor(new ItemStack(Material.DIAMOND_LEGGINGS), "Hardened Diamond Leggings", 0, 95, true, SbRarity.RARE_LEGGINGS));
        armors.put("hardened_diamond_boots", new SbArmor(new ItemStack(Material.DIAMOND_BOOTS), "Hardened Diamond Boots", 0, 55, true, SbRarity.RARE_BOOTS));
        putSet("hardened_diamond_armor", "hardened_diamond");

        armors.put("fairys_fedora", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.PURPLE), "Fairy's Fedora", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_HELMET));
        armors.put("fairys_polo", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.PURPLE), "Fairy's Polo", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_CHESTPLATE));
        armors.put("fairys_trousers", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.PURPLE), "Fairy's Trousers", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_LEGGINGS));
        armors.put("fairys_galoshes", new SbArmor(Utils.dyeArmor(new ItemStack(Material.LEATHER_HELMET), Color.PURPLE), "Fairy's Galoshes", 1, 1, 0, 0, 0, -1, 10, 0, null, Collections.singletonList(new SbAbility("Fairy's Outfit", AbilityType.FULL_SET_BONUS, Arrays.asList("&7Gain &c1%health% &7per Fairy Soul.", "&7You found: &d0 Fairy Souls"))), true, SbRarity.RARE_BOOTS));
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



        // Admin items
        items.put("sword_of_the_stars", new SbItem(new ItemStack(Material.GOLDEN_SWORD), "Sword Of The Stars", 99999, 0, Arrays.asList("&d\"Only those with the power to", "&dcreate this world can wield this", "&dblade\""), null, true, SbRarity.SPECIAL_SWORD));
        armors.put("boss", new SbArmor(Utils.getValueHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjAyMGMzMzY5MjhkOTRmODhiZmNlYjUxODE4YTY0MDQwYjgyNjZkNDk3ZDM4ZWUxMjhlNDNhNGUwMmUyYTM2In19fQ=="), "Boss", 1000, 1000, true, SbRarity.LEGENDARY_HELMET));
        items.put("docts_space_helmet", new DctrsSpaceHelmet());
    }
}
