package com.tong.miningmod.item;

import java.util.List;

import com.tong.miningmod.init.ParticleTypesInit;
import com.tong.miningmod.init.SoundInit;
import com.tong.miningmod.init.TagInit;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class OreDetectorItem extends Item {

	public OreDetectorItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getLevel().isClientSide()) {
			BlockPos pos = context.getClickedPos();
			Player player = context.getPlayer();
			boolean found = false;

			for (int i = 0; i <= pos.getY() + 64; i++) {
				Block block = context.getLevel().getBlockState(pos.below(i)).getBlock();
				if (isValuableBlock(block)) {
					outputValuableCoordinates(pos.below(i), player, block);
					found = true;

					spawnFoundParticles(context, pos);

					context.getLevel().playSound(player, pos, SoundInit.ORE_DETECTOR_FOUND_ORE.get(),
							SoundSource.BLOCKS, 1f, 1f);
					
					break;
				}
			}

			if (!found) {
				player.sendMessage(new TranslatableComponent("item.miningmod.ore_detector.no_valuables"),
						player.getUUID());
			}
		}

		context.getItemInHand().hurtAndBreak(1, context.getPlayer(),
				(player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

		return super.useOn(context);
	}

	private void spawnFoundParticles(UseOnContext context, BlockPos pos) {
		for (int i = 0; i < 360; i++) {
			if (i % 20 == 0) {
				context.getLevel().addParticle(ParticleTypesInit.TARGET_PARTICLES.get(), pos.getX() + 0.5d,
						pos.getY() + 1, pos.getZ() + 0.5d, Math.cos(i) + 0.25d, 0.15d, Math.sin(i) + 0.25d);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> component, TooltipFlag flag) {
		if (Screen.hasShiftDown()) {
			component.add(new TranslatableComponent("tooltip.miningmod.ore_detector.tooltip.shift"));
		} else {
			component.add(new TranslatableComponent("tooltip.miningmod.ore_detector.tooltip"));
		}
	}

	private void outputValuableCoordinates(BlockPos below, Player player, Block block) {
		player.sendMessage(new TextComponent("Found " + block.asItem().getRegistryName().toString() + " at " + "("
				+ below.getX() + ", " + below.getY() + ", " + below.getZ() + ")"), player.getUUID());
	}

	@SuppressWarnings("deprecation")
	private boolean isValuableBlock(Block block) {
		return Registry.BLOCK.getHolderOrThrow(Registry.BLOCK.getResourceKey(block).get())
				.is(TagInit.Blocks.ORE_DETECTOR_VALUABLES);
	}
}
