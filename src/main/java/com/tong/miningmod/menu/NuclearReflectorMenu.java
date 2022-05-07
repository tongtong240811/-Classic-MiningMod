package com.tong.miningmod.menu;

import com.tong.miningmod.block.entity.NuclearReflectorBlockEntity;
import com.tong.miningmod.init.BlockInit;
import com.tong.miningmod.init.MenuTypesInit;
import com.tong.miningmod.screen.slot.ResultSlot;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NuclearReflectorMenu extends AbstractContainerMenu {
	
	private final BlockEntity blockEntity;
	private final Level level;
	private final ContainerData data;

	public NuclearReflectorMenu(int windowId, Inventory playerInv, BlockEntity blockEntity, ContainerData data) {
		super(MenuTypesInit.NUCLEAR_REFLECTOR_MENU_TYPE.get(), windowId);
		checkContainerSize(playerInv, 4);
		this.blockEntity = ((NuclearReflectorBlockEntity) blockEntity);
		this.level = playerInv.player.level;
		this.data = data;
		
		blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
			addSlot(new SlotItemHandler(handler, 0, 34, 40));
			addSlot(new SlotItemHandler(handler, 1, 57, 18));
			addSlot(new SlotItemHandler(handler, 2, 103, 18));
			addSlot(new ResultSlot(handler, 3, 80, 60));
		});
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 86 + row * 18));
			}
		}

		for (int col = 0; col < 9; col++) {
			addSlot(new Slot(playerInv, col, 8 + col * 18, 144));
		}
		
		addDataSlots(data);
	}
	
	public boolean isCrafting() {
		return data.get(0) > 0;
	}
	
	public int getScaledProgress() {
		int progress = this.data.get(0);
		int maxProgress = this.data.get(1);
		int progressArrowSize = 26;
		
		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}
	
	public NuclearReflectorMenu(int windowId, Inventory playerInv, FriendlyByteBuf data) {
		this(windowId, playerInv, playerInv.player.level.getBlockEntity(data.readBlockPos()),
				new SimpleContainerData(2));
	}
	
	@Override
	public boolean stillValid(Player player) {
		return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
				BlockInit.NUCLEAR_REFLECTOR.get());
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);
		if (slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < 4 && !moveItemStackTo(stack1, 4, slots.size(), true)) {
				return ItemStack.EMPTY;
			}

			if (!moveItemStackTo(stack1, 0, 4, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}
}
