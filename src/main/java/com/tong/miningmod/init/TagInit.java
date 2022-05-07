package com.tong.miningmod.init;

import com.tong.miningmod.MiningMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagInit {

	public static class Blocks {

		public static final TagKey<Block> ORE_DETECTOR_VALUABLES = tag("ore_detector_valuables");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(new ResourceLocation(MiningMod.MOD_ID, name));
		}
	}
}
