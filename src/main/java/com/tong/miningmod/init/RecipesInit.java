package com.tong.miningmod.init;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.recipe.HeavyAnvilRecipe;
import com.tong.miningmod.recipe.NuclearReflectorRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipesInit {

	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, MiningMod.MOD_ID);

	public static final RegistryObject<RecipeSerializer<HeavyAnvilRecipe>> COMPRESSED_SERIALIZER = SERIALIZERS
			.register("compressed", () -> HeavyAnvilRecipe.Serializer.INSTANCE);
			
	public static final RegistryObject<RecipeSerializer<NuclearReflectorRecipe>> REFLECTOR_SERIALIZER = SERIALIZERS
			.register("reflector", () -> NuclearReflectorRecipe.Serializer.INSTANCE);
}
