package com.tong.miningmod.world.feature;

import java.util.List;

import com.tong.miningmod.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class ConfiguredFeatures {

	public static final List<OreConfiguration.TargetBlockState> OVERWORLD_RAINBOW_ORES = List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
					BlockInit.RAINBOW_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
					BlockInit.DEEPSLATE_RAINBOW_ORE.get().defaultBlockState()));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> RAINBOW_ORE = FeatureUtils
			.register("rainbow_ore", Feature.ORE, new OreConfiguration(OVERWORLD_RAINBOW_ORES, 4));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TUNGSTEN_ORE = FeatureUtils
			.register("tungsten_ore", Feature.ORE, new OreConfiguration(List.of(OreConfiguration
					.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.TUNGSTEN_ORE.get().defaultBlockState())), 8));
}
