package com.tong.miningmod.init;

import java.util.function.Function;
import java.util.function.Supplier;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.block.HeavyAnvilBlock;
import com.tong.miningmod.block.NuclearReflectorBlock;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			MiningMod.MOD_ID);

	public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;

	public static final RegistryObject<Block> RAINBOW_ORE = register("rainbow_ore",
			() -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()
					.sound(SoundType.STONE), UniformInt.of(1, 6)),
			objects -> () -> new BlockItem(objects.get(), new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Block> TUNGSTEN_ORE = register("tungsten_ore",
			() -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()
					.sound(SoundType.STONE)),
			objects -> () -> new BlockItem(objects.get(), new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Block> DEEPSLATE_RAINBOW_ORE = register("deepslate_rainbow_ore",
			() -> new OreBlock(BlockBehaviour.Properties.copy(RAINBOW_ORE.get()).strength(4.5f, 3f)
					.color(MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE),
					UniformInt.of(1, 6)),
			objects -> () -> new BlockItem(objects.get(), new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Block> HEAVY_ANVIL = register("heavy_anvil",
			() -> new HeavyAnvilBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(5f, 2400f)
					.requiresCorrectToolForDrops().sound(SoundType.ANVIL)),
			objects -> () -> new BlockItem(objects.get(), new Item.Properties().tab(MiningMod.MINING_TAB)));

	public static final RegistryObject<Block> NUCLEAR_REFLECTOR = register("nuclear_reflector",
			() -> new NuclearReflectorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f, 6f)
					.requiresCorrectToolForDrops().sound(SoundType.METAL)),
			objects -> () -> new BlockItem(objects.get(), new Item.Properties().tab(MiningMod.MINING_TAB)));

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block,
			Function<RegistryObject<T>, Supplier<Item>> item) {
		RegistryObject<T> obj = BLOCKS.register(name, block);
		ITEMS.register(name, item.apply(obj));
		return obj;
	}
}
