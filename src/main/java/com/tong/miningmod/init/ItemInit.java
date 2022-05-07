package com.tong.miningmod.init;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.item.CustomArmorMaterial;
import com.tong.miningmod.item.CustomToolMaterial;
import com.tong.miningmod.item.OreDetectorItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MiningMod.MOD_ID);

	public static final RegistryObject<Item> RAINBOW_INGOT = ITEMS.register("rainbow_ingot",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAW_RAINBOW = ITEMS.register("raw_rainbow",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> ORE_DETECTOR = ITEMS.register("ore_detector",
			() -> new OreDetectorItem(new Item.Properties().tab(MiningMod.MINING_TAB).durability(100)));

	public static final RegistryObject<Item> MONITOR = ITEMS.register("monitor",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> GLASS_SHIELD = ITEMS.register("glass_shield",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_SWORD = ITEMS.register("rainbow_sword",
			() -> new SwordItem(CustomToolMaterial.RAINBOW, 1000000, 1000000f,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_AXE = ITEMS.register("rainbow_axe",
			() -> new AxeItem(CustomToolMaterial.RAINBOW, 1000000, 1000000f,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_PICKAXE = ITEMS.register("rainbow_pickaxe",
			() -> new PickaxeItem(CustomToolMaterial.RAINBOW, 1000000, 1000000f,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_SHOVEL = ITEMS.register("rainbow_shovel",
			() -> new ShovelItem(CustomToolMaterial.RAINBOW, 1000000, 1000000f,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_HOE = ITEMS.register("rainbow_hoe",
			() -> new HoeItem(CustomToolMaterial.RAINBOW, 1000000, 1000000f,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
			() -> new SwordItem(CustomToolMaterial.COPPER, 2, 3f, new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
			() -> new AxeItem(CustomToolMaterial.COPPER, 4, 0f, new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
			() -> new PickaxeItem(CustomToolMaterial.COPPER, 1, 1f, new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
			() -> new ShovelItem(CustomToolMaterial.COPPER, 0, 1f, new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
			() -> new HoeItem(CustomToolMaterial.COPPER, 0, 0f, new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_HELMET = ITEMS.register("rainbow_helmet",
			() -> new ArmorItem(CustomArmorMaterial.RAINBOW, EquipmentSlot.HEAD,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_CHESTPLATE = ITEMS.register("rainbow_chestplate",
			() -> new ArmorItem(CustomArmorMaterial.RAINBOW, EquipmentSlot.CHEST,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_LEGGINGS = ITEMS.register("rainbow_leggings",
			() -> new ArmorItem(CustomArmorMaterial.RAINBOW, EquipmentSlot.LEGS,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> RAINBOW_BOOTS = ITEMS.register("rainbow_boots",
			() -> new ArmorItem(CustomArmorMaterial.RAINBOW, EquipmentSlot.FEET,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
			() -> new ArmorItem(CustomArmorMaterial.COPPER, EquipmentSlot.HEAD,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
			() -> new ArmorItem(CustomArmorMaterial.COPPER, EquipmentSlot.CHEST,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
			() -> new ArmorItem(CustomArmorMaterial.COPPER, EquipmentSlot.LEGS,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
			() -> new ArmorItem(CustomArmorMaterial.COPPER, EquipmentSlot.FEET,
					new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB).durability(100)));
	
	public static final RegistryObject<Item> COMPRESSED_IRON = ITEMS.register("compressed_iron",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));
	
	public static final RegistryObject<Item> NEUTRON_REFLECTOR = ITEMS.register("neutron_reflector",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB).durability(100)));
	
	public static final RegistryObject<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));
	
	public static final RegistryObject<Item> HATCH = ITEMS.register("hatch",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));
	
	public static final RegistryObject<Item> HANDLER = ITEMS.register("handler",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));
	
	public static final RegistryObject<Item> CHIMNEY = ITEMS.register("chimney",
			() -> new Item(new Item.Properties().tab(MiningMod.MINING_TAB)));
}
