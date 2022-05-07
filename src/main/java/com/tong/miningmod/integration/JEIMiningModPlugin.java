package com.tong.miningmod.integration;

import java.util.List;
import java.util.Objects;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.recipe.HeavyAnvilRecipe;
import com.tong.miningmod.recipe.NuclearReflectorRecipe;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JEIMiningModPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(MiningMod.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new HeavyAnvilRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new NuclearReflectorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@SuppressWarnings("resource")
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		List<HeavyAnvilRecipe> heavy_anvil = rm.getAllRecipesFor(HeavyAnvilRecipe.Type.INSTANCE);
		List<NuclearReflectorRecipe> nuclear_reflector = rm.getAllRecipesFor(NuclearReflectorRecipe.Type.INSTANCE);
		registration.addRecipes(new RecipeType<>(HeavyAnvilRecipeCategory.UID, HeavyAnvilRecipe.class), heavy_anvil);
		registration.addRecipes(new RecipeType<>(NuclearReflectorRecipeCategory.UID, NuclearReflectorRecipe.class), nuclear_reflector);
	}
}
