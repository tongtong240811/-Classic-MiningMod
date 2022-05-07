package com.tong.miningmod.init;

import com.tong.miningmod.MiningMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {

	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
			.create(ForgeRegistries.SOUND_EVENTS, MiningMod.MOD_ID);

	public static final RegistryObject<SoundEvent> ORE_DETECTOR_FOUND_ORE = SOUND_EVENTS.register(
			"ore_detector_found_ore",
			() -> new SoundEvent(new ResourceLocation(MiningMod.MOD_ID, "ore_detector_found_ore")));
}
