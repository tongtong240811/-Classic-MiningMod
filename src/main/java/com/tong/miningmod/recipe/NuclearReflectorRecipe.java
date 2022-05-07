package com.tong.miningmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tong.miningmod.MiningMod;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class NuclearReflectorRecipe implements Recipe<SimpleContainer> {
	
	private final ResourceLocation id;
	private final ItemStack result;
	private final NonNullList<Ingredient> ingredients;

	public NuclearReflectorRecipe(ResourceLocation id, ItemStack result, NonNullList<Ingredient> ingredients) {
		this.id = id;
		this.result = result;
		this.ingredients = ingredients;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	@Override
	public boolean matches(SimpleContainer container, Level level) {
		return ingredients.get(0).test(container.getItem(1));
	}

	@Override
	public ItemStack assemble(SimpleContainer container) {
		return result;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return result.copy();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}
	
	public static class Type implements RecipeType<NuclearReflectorRecipe> {
		private Type() {
		}

		public static final Type INSTANCE = new Type();
		public static final String ID = "reflector";
	}

	public static class Serializer implements RecipeSerializer<NuclearReflectorRecipe> {

		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(MiningMod.MOD_ID, "reflector");

		@Override
		public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
			return INSTANCE;
		}

		@Override
		public ResourceLocation getRegistryName() {
			return ID;
		}

		@Override
		public Class<RecipeSerializer<?>> getRegistryType() {
			return Serializer.castClass(RecipeSerializer.class);
		}

		@SuppressWarnings("unchecked")
		private static <G> Class<G> castClass(Class<?> cls) {
			return (Class<G>) cls;
		}

		@Override
		public NuclearReflectorRecipe fromJson(ResourceLocation id, JsonObject json) {
			ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

			JsonArray array = GsonHelper.getAsJsonArray(json, "ingredients");
			NonNullList<Ingredient> ingredients = NonNullList.withSize(1, Ingredient.EMPTY);

			for (int i = 0; i < ingredients.size(); i++) {
				ingredients.set(i, Ingredient.fromJson(array.get(i)));
			}
			
			return new NuclearReflectorRecipe(id, result, ingredients);
		}

		@Override
		public NuclearReflectorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
			
			for (int i = 0; i < ingredients.size(); i++) {
				ingredients.set(i, Ingredient.fromNetwork(buffer));
			}
			
			ItemStack result = buffer.readItem();
			return new NuclearReflectorRecipe(id, result, ingredients);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, NuclearReflectorRecipe recipe) {
			buffer.writeInt(recipe.getIngredients().size());
			for (Ingredient ing : recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(recipe.getResultItem(), false);
		}
	}
}
