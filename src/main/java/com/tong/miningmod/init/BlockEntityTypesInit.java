package com.tong.miningmod.init;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.block.entity.HeavyAnvilBlockEntity;
import com.tong.miningmod.block.entity.NuclearReflectorBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityTypesInit {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITIES, MiningMod.MOD_ID);

	public static final RegistryObject<BlockEntityType<HeavyAnvilBlockEntity>> HEAVY_ANVIL_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES
			.register("heavy_anvil", () -> BlockEntityType.Builder
					.of(HeavyAnvilBlockEntity::new, BlockInit.HEAVY_ANVIL.get()).build(null));

	public static final RegistryObject<BlockEntityType<NuclearReflectorBlockEntity>> NUCLEAR_RFLECTOR_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES
			.register("nuclear_reflerctor", () -> BlockEntityType.Builder
					.of(NuclearReflectorBlockEntity::new, BlockInit.NUCLEAR_REFLECTOR.get()).build(null));
}
