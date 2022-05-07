package com.tong.miningmod.world.gen;

import java.util.List;

import com.tong.miningmod.world.feature.PlacedFeatures;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {

	public static void generateOres(final BiomeLoadingEvent event) {
		List<Holder<PlacedFeature>> base = event.getGeneration()
				.getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
		
		base.add(PlacedFeatures.RAINBOW_ORE_PLACED);
		base.add(PlacedFeatures.TUNGSTEN_ORE_PLACED);
	}
}
