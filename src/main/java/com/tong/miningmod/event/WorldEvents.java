package com.tong.miningmod.event;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.world.gen.OreGeneration;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = MiningMod.MOD_ID)
public class WorldEvents {

	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		OreGeneration.generateOres(event);
	}
}
