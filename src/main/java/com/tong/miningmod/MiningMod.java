package com.tong.miningmod;

import com.tong.miningmod.init.BlockEntityTypesInit;
import com.tong.miningmod.init.BlockInit;
import com.tong.miningmod.init.ItemInit;
import com.tong.miningmod.init.MenuTypesInit;
import com.tong.miningmod.init.ParticleTypesInit;
import com.tong.miningmod.init.RecipesInit;
import com.tong.miningmod.init.SoundInit;
import com.tong.miningmod.particle.TargetParticles;
import com.tong.miningmod.recipe.HeavyAnvilRecipe;
import com.tong.miningmod.recipe.NuclearReflectorRecipe;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("miningmod")
@EventBusSubscriber(modid = MiningMod.MOD_ID, bus = Bus.MOD)
public class MiningMod {

	public static final String MOD_ID = "miningmod";

	public static final CreativeModeTab MINING_TAB = new CreativeModeTab("miningmod") {

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.RAINBOW_INGOT.get());
		}
	};

	public MiningMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		BlockEntityTypesInit.BLOCK_ENTITY_TYPES.register(bus);
		MenuTypesInit.MENU_TYPES.register(bus);
		ParticleTypesInit.PARTICLE_TYPES.register(bus);
		RecipesInit.SERIALIZERS.register(bus);
		SoundInit.SOUND_EVENTS.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFatories(final ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particleEngine.register(ParticleTypesInit.TARGET_PARTICLES.get(),
				TargetParticles.Provider::new);
	}

	@SubscribeEvent
	public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
		Registry.register(Registry.RECIPE_TYPE, HeavyAnvilRecipe.Type.ID, HeavyAnvilRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_TYPE, NuclearReflectorRecipe.Type.ID, NuclearReflectorRecipe.Type.INSTANCE);
	}
}
