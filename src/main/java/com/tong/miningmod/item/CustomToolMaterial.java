package com.tong.miningmod.item;

import com.tong.miningmod.init.ItemInit;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class CustomToolMaterial {

	public static final ForgeTier RAINBOW = new ForgeTier(1000000, 1000000, 1000000f, 1000000f, 1000000,
			BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemInit.RAINBOW_INGOT.get()));
	
	public static final ForgeTier COPPER = new ForgeTier(2, 1400, 1.5f, 2f, 22,
			BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
}
