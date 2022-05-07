package com.tong.miningmod.init;

import com.tong.miningmod.MiningMod;
import com.tong.miningmod.menu.HeavyAnvilMenu;
import com.tong.miningmod.menu.NuclearReflectorMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesInit {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			MiningMod.MOD_ID);

	public static final RegistryObject<MenuType<HeavyAnvilMenu>> HEAVY_ANVIL_MENU_TYPE = MENU_TYPES.register("heavy_anvil",
			() -> IForgeMenuType.create(HeavyAnvilMenu::new));
	
	public static final RegistryObject<MenuType<NuclearReflectorMenu>> NUCLEAR_REFLECTOR_MENU_TYPE = MENU_TYPES.register("nuclear_reflector",
			() -> IForgeMenuType.create(NuclearReflectorMenu::new));
}
