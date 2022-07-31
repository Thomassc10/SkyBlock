package me.thomas.skyblock;

import me.thomas.skyblock.commands.GetSbItem;
import me.thomas.skyblock.commands.SbItemsMenu;
import me.thomas.skyblock.commands.SbMenu;
import me.thomas.skyblock.datamanager.DataManager;
import me.thomas.skyblock.events.*;
import me.thomas.skyblock.events.customevents.abilityuse.UseAbility;
import me.thomas.skyblock.events.customevents.armorequip.EquipArmor;
import me.thomas.skyblock.events.customevents.fullset.EquipSet;
import me.thomas.skyblock.helpers.Utils;
import me.thomas.skyblock.items.Items;
import me.thomas.skyblock.items.bows.Terminator;
import me.thomas.skyblock.items.bows.*;
import me.thomas.skyblock.items.powerorbs.ManaFluxPowerOrb;
import me.thomas.skyblock.items.powerorbs.OverfluxPowerOrb;
import me.thomas.skyblock.items.powerorbs.PlasmafluxPowerOrb;
import me.thomas.skyblock.items.powerorbs.RadiantPowerOrb;
import me.thomas.skyblock.items.specialitems.DctrsSpaceHelmet;
import me.thomas.skyblock.items.swords.*;
import me.thomas.skyblock.menus.CraftingMenu;
import me.thomas.skyblock.player.HealthRegeneration;
import me.thomas.skyblock.player.ManaRegeneration;
import me.thomas.skyblock.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class SkyBlock extends JavaPlugin {

	private static SkyBlock instance;
	public DataManager dataManager;
	public SkyBlock() {
		dataManager = new DataManager(this);
	}
	
	@Override
	public void onEnable() {
		instance = this;
		new PlayerManager();
		Items.registerSbItems();
		saveDefaultConfig();
		registerCommands();
		registerEvents();
		//SbEntities.registerEntities();

		me.thomas.skyblock.menus.SbMenu.register();
		me.thomas.skyblock.menus.ItemsMenu.register();
		CraftingMenu.register();

		Bukkit.getWorlds().forEach(world -> world.setPVP(false));
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, (Runnable) new HealthRegeneration(), 1);
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, (Runnable) new ManaRegeneration(), 1);
	}
	
	@Override
	public void onDisable() {

	}

	public static SkyBlock getInstance() {
		return instance;
	}

	public void registerEvents() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new CraftingInventory(), this);
		pm.registerEvents(new EquipArmor(), this);
		pm.registerEvents(new EquipSet(), this);
		pm.registerEvents(new HeldItem(), this);
		pm.registerEvents(new InstantDeathRespawn(), this);
		pm.registerEvents(new ItemsMenu(), this);
		pm.registerEvents(new JoinLeave(), this);
		pm.registerEvents(new OpenMenus(), this);
		pm.registerEvents(new OpenSBMenu(), this);
		pm.registerEvents(new PlayerArmorEquip(), this);
		pm.registerEvents(new PlayerDamageDeal(), this);
		pm.registerEvents(new PlayerDamageReceive(), this);
		pm.registerEvents(new SbItemPlace(), this);
		pm.registerEvents(new UseAbility(), this);
		pm.registerEvents(new WitherImpactAbility(), this);

		pm.registerEvents(new AspectOfTheEnd(), this);
		pm.registerEvents(new AspectOfTheJerry(), this);
		pm.registerEvents(new AspectOfTheVoid(), this);
		pm.registerEvents(new Astraea(), this);
		pm.registerEvents(new AtomsplitKatana(), this);
		pm.registerEvents(new AxeOfTheShredded(), this);
		pm.registerEvents(new BonzoStaff(), this);
		pm.registerEvents(new Cleaver(), this);
		pm.registerEvents(new DarkClaymore(), this);
		pm.registerEvents(new DctrsSpaceHelmet(), this);
		pm.registerEvents(new DreadlordSword(), this);
		pm.registerEvents(new EdibleMace(), this);
		pm.registerEvents(new EmberRod(), this);
		pm.registerEvents(new EmberRod(), this);
		pm.registerEvents(new EndStoneSword(), this);
		pm.registerEvents(new EndSword(), this);
		pm.registerEvents(new FlamingSword(), this);
		pm.registerEvents(new FloridZombieSword(), this);
		pm.registerEvents(new FlowerOfTruth(), this);
		pm.registerEvents(new FrozenScythe(), this);
		pm.registerEvents(new GiantCleaver(), this);
		pm.registerEvents(new GiantSword(), this);
		pm.registerEvents(new GolemSword(), this);
		pm.registerEvents(new HyperCleaver(), this);
		pm.registerEvents(new Hyperion(), this);
		pm.registerEvents(new JerryChineGun(), this);
		pm.registerEvents(new LeapingSword(), this);
		pm.registerEvents(new LividDagger(), this);
		pm.registerEvents(new NecromancerSword(), this);
		pm.registerEvents(new NecronsBlade(), this);
		pm.registerEvents(new OrnateZombieSword(), this);
		pm.registerEvents(new OrnateZombieSword(), this);
		pm.registerEvents(new PigmanSword(), this);
		pm.registerEvents(new PrismarineBlade(), this);
		pm.registerEvents(new RagnarockAxe(), this);
		pm.registerEvents(new ReaperFalchion(), this);
		pm.registerEvents(new ReaperScythe(), this);
		pm.registerEvents(new RecluseFang(), this);
		pm.registerEvents(new RevenantFalchion(), this);
		pm.registerEvents(new RogueSword(), this);
		pm.registerEvents(new Scylla(), this);
		pm.registerEvents(new ShadowFury(), this);
		pm.registerEvents(new SilentDeath(), this);
		pm.registerEvents(new SilkEdgeSword(), this);
		pm.registerEvents(new SoulWhip(), this);
		pm.registerEvents(new SpiderSword(), this);
		pm.registerEvents(new SpiritSceptre(), this);
		pm.registerEvents(new SuperCleaver(), this);
		pm.registerEvents(new TribalSpeer(), this);
		pm.registerEvents(new UndeadSword(), this);
		pm.registerEvents(new Valkyrie(), this);
		pm.registerEvents(new VoidedgeKatana(), this);
		pm.registerEvents(new VoidwalketKatana(), this);
		pm.registerEvents(new VorpalKatana(), this);
		pm.registerEvents(new WitherCloakSword(), this);
		pm.registerEvents(new ZombieSoldierCutlass(), this);
		pm.registerEvents(new ZombieSword(), this);

		pm.registerEvents(new ArtisanalBow(), this);
		pm.registerEvents(new EndStoneBow(), this);
		pm.registerEvents(new EnderBow(), this);
		pm.registerEvents(new ExplosiveBow(), this);
		pm.registerEvents(new HurricaneBow(), this);
		pm.registerEvents(new JuJuShortBow(), this);
		pm.registerEvents(new MagmaBow(), this);
		pm.registerEvents(new SavannaBow(), this);
		pm.registerEvents(new SniperBow(), this);
		pm.registerEvents(new SoulsRebound(), this);
		pm.registerEvents(new SoulstealerBow(), this);
		pm.registerEvents(new UndeadBow(), this);
		pm.registerEvents(new WitherBow(), this);
		pm.registerEvents(new RunaansBow(), this);
		pm.registerEvents(new MosquitoBow(), this);
		pm.registerEvents(new Terminator(), this);
		pm.registerEvents(new SpiritBow(), this);
		pm.registerEvents(new LastBreath(), this);
		pm.registerEvents(new Bonemerang(), this);

		pm.registerEvents(new RadiantPowerOrb(), this);
		pm.registerEvents(new ManaFluxPowerOrb(), this);
		pm.registerEvents(new OverfluxPowerOrb(), this);
		pm.registerEvents(new PlasmafluxPowerOrb(), this);
	}
	
	public void registerCommands() {
		this.getCommand("sbmenu").setExecutor(new SbMenu());
		this.getCommand("items").setExecutor(new SbItemsMenu());
		this.getCommand("sbitem").setExecutor(new GetSbItem());
	}

	public static ItemStack menuItem() {
		ItemStack star = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = star.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "SkyBlock Menu " + ChatColor.GRAY + "(Click)");
		List<String> lore = new ArrayList<>();
		lore.add(Utils.color("&7View all of your SkyBlock"));
		lore.add(Utils.color("&7progress, including your Skills,"));
		lore.add(Utils.color("&7Collections, Recipes, and more!"));
		lore.add("");
		lore.add(Utils.color("&eClick to open!"));
		meta.setLore(lore);
		star.setItemMeta(meta);
		return star;
	}
}
