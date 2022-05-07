package com.tong.miningmod.world.feature;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {

	public static final Holder<PlacedFeature> RAINBOW_ORE_PLACED = PlacementUtils.register("rainbow_ore_placed",
			ConfiguredFeatures.RAINBOW_ORE,
			List.of(CountPlacement.of(20), InSquarePlacement.spread(),
					HeightRangePlacement.uniform(VerticalAnchor.absolute(-30), VerticalAnchor.absolute(30)),
					BiomeFilter.biome()));

	public static final Holder<PlacedFeature> TUNGSTEN_ORE_PLACED = PlacementUtils.register("tungsten_ore_placed",
			ConfiguredFeatures.TUNGSTEN_ORE,
			List.of(CountPlacement.of(40), InSquarePlacement.spread(),
					HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(50)),
					BiomeFilter.biome()));
}
