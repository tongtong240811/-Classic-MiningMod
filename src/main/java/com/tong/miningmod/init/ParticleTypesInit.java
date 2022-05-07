package com.tong.miningmod.init;

import com.tong.miningmod.MiningMod;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleTypesInit {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, MiningMod.MOD_ID);

	public static final RegistryObject<SimpleParticleType> TARGET_PARTICLES = PARTICLE_TYPES.register("target_particles",
			() -> new SimpleParticleType(true));
}
