package com.tong.miningmod.event;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.init.MenuTypesInit;
import com.tong.miningmod.screen.HeavyAnvilScreen;
import com.tong.miningmod.screen.NuclearReflectorScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = MiningMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(MenuTypesInit.HEAVY_ANVIL_MENU_TYPE.get(), HeavyAnvilScreen::new);
		MenuScreens.register(MenuTypesInit.NUCLEAR_REFLECTOR_MENU_TYPE.get(), NuclearReflectorScreen::new);
	}
}
