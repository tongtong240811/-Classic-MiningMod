package com.tong.miningmod.integration;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.init.BlockInit;
import com.tong.miningmod.init.ItemInit;
import com.tong.miningmod.recipe.NuclearReflectorRecipe;

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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class NuclearReflectorRecipeCategory implements IRecipeCategory<NuclearReflectorRecipe> {
	
	public static final ResourceLocation UID = new ResourceLocation(MiningMod.MOD_ID, "reflector");
	public static final ResourceLocation TEXTURE = new ResourceLocation(MiningMod.MOD_ID,
			"textures/gui/nuclear_reflector_gui.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	
	@SuppressWarnings("removal")
	public NuclearReflectorRecipeCategory(IGuiHelper helper) {
		background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(BlockInit.NUCLEAR_REFLECTOR.get()));
	}

	@Override
	public Component getTitle() {
		return new TextComponent("Reflector");
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
	public Class<? extends NuclearReflectorRecipe> getRecipeClass() {
		return NuclearReflectorRecipe.class;
	}
	
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, NuclearReflectorRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 34, 40).addIngredients(Ingredient.of(Items.COAL));
		builder.addSlot(RecipeIngredientRole.INPUT, 57, 18).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 103, 18).addIngredients(Ingredient.of(ItemInit.NEUTRON_REFLECTOR.get()));
		
		builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem());
	}
}
