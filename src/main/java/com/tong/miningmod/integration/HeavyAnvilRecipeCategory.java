package com.tong.miningmod.integration;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.init.BlockInit;
import com.tong.miningmod.init.ItemInit;
import com.tong.miningmod.recipe.HeavyAnvilRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class HeavyAnvilRecipeCategory implements IRecipeCategory<HeavyAnvilRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(MiningMod.MOD_ID, "compressed");
	public static final ResourceLocation TEXTURE = new ResourceLocation(MiningMod.MOD_ID,
			"textures/gui/heavy_anvil_gui.png");
	
	private final IDrawable background;
	private final IDrawable icon;

	@SuppressWarnings("removal")
	public HeavyAnvilRecipeCategory(IGuiHelper helper) {
		background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(BlockInit.HEAVY_ANVIL.get()));
	}

	@Override
	public Component getTitle() {
		return new TextComponent("Compressed");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends HeavyAnvilRecipe> getRecipeClass() {
		return HeavyAnvilRecipe.class;
	}
	
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, HeavyAnvilRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 18, 50).addIngredients(Ingredient.of(ItemInit.HAMMER.get()));
		builder.addSlot(RecipeIngredientRole.INPUT, 66, 16).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 66, 50).addIngredients(recipe.getIngredients().get(1));
		
		builder.addSlot(RecipeIngredientRole.OUTPUT, 114, 33).addItemStack(recipe.getResultItem());
	}
}
