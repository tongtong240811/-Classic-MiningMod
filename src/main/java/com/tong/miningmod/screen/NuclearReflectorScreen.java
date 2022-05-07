package com.tong.miningmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tong.miningmod.MiningMod;
import com.tong.miningmod.menu.NuclearReflectorMenu;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NuclearReflectorScreen extends AbstractContainerScreen<NuclearReflectorMenu> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(MiningMod.MOD_ID,
			"textures/gui/nuclear_reflector_gui.png");

	public NuclearReflectorScreen(NuclearReflectorMenu menu, Inventory playerInv, Component component) {
		super(menu, playerInv, component);
	}
	
	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTick) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTick);
		renderTooltip(stack, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack stack, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		
		blit(stack, x, y, 0, 0, imageWidth, imageHeight);
		
		if (menu.isCrafting()) {
			blit(stack, x + 102, y + 41, 176, 0, 8, menu.getScaledProgress());
		}
	}
}
