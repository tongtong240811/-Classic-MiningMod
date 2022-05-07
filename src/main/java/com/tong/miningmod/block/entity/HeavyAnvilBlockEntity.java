package com.tong.miningmod.block.entity;

import java.util.Optional;
import java.util.Random;

import com.tong.miningmod.init.BlockEntityTypesInit;
import com.tong.miningmod.init.ItemInit;
import com.tong.miningmod.menu.HeavyAnvilMenu;
import com.tong.miningmod.recipe.HeavyAnvilRecipe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class HeavyAnvilBlockEntity extends BlockEntity implements MenuProvider {

	private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};

	private LazyOptional<IItemHandler> handler = LazyOptional.empty();

	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 72;

	public HeavyAnvilBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityTypesInit.HEAVY_ANVIL_BLOCK_ENTITY_TYPE.get(), pos, state);
		this.data = new ContainerData() {

			@Override
			public void set(int index, int value) {
				switch (index) {
				case 0:
					HeavyAnvilBlockEntity.this.progress = value;
					break;
				case 1:
					HeavyAnvilBlockEntity.this.maxProgress = value;
					break;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}

			@Override
			public int get(int index) {
				switch (index) {
				case 0:
					return HeavyAnvilBlockEntity.this.progress;
				case 1:
					return HeavyAnvilBlockEntity.this.maxProgress;
				default:
					return 0;
				}
			}
		};
	}

	@Override
	public void onLoad() {
		super.onLoad();
		handler = LazyOptional.of(() -> itemHandler);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		handler.invalidate();
	}

	@Override
	protected void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		compound.put("inventory", itemHandler.serializeNBT());
		compound.putInt("heavy_anvil.progress", progress);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		itemHandler.deserializeNBT(tag.getCompound("inventory"));
		progress = tag.getInt("heavy_anvil.progress");
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		return new HeavyAnvilMenu(windowId, playerInv, this, this.data);
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Heavy Anvil");
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}
		return super.getCapability(cap, side);
	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int slots = 0; slots < itemHandler.getSlots(); slots++) {
			inventory.setItem(slots, itemHandler.getStackInSlot(slots));
		}

		Containers.dropContents(level, worldPosition, inventory);
	}
	
	public static void tick(Level level, BlockPos pos, BlockState state, HeavyAnvilBlockEntity blockEntity) {
		if (hasRecipe(blockEntity)) {
			blockEntity.progress++;
			setChanged(level, pos, state);
			if (blockEntity.progress > blockEntity.maxProgress) {
				level.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 1);
				craftItem(blockEntity);
			}
		} else {
			blockEntity.resetProgress();
			setChanged(level, pos, state);
		}
	}

	private static boolean hasRecipe(HeavyAnvilBlockEntity blockEntity) {
		Level level = blockEntity.level;
		SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots());
		for (int slots = 0; slots < blockEntity.itemHandler.getSlots(); slots++) {
			inventory.setItem(slots, blockEntity.itemHandler.getStackInSlot(slots));
		}

		Optional<HeavyAnvilRecipe> match = level.getRecipeManager().getRecipeFor(HeavyAnvilRecipe.Type.INSTANCE,
				inventory, level);

		return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
				&& hasToolsInToolSlot(blockEntity);
	}

	private static boolean hasToolsInToolSlot(HeavyAnvilBlockEntity blockEntity) {
		return blockEntity.itemHandler.getStackInSlot(0).getItem() == ItemInit.HAMMER.get();
	}

	private static void craftItem(HeavyAnvilBlockEntity blockEntity) {
		Level level = blockEntity.level;
		SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots());
		for (int slots = 0; slots < blockEntity.itemHandler.getSlots(); slots++) {
			inventory.setItem(slots, blockEntity.itemHandler.getStackInSlot(slots));
		}

		Optional<HeavyAnvilRecipe> match = level.getRecipeManager().getRecipeFor(HeavyAnvilRecipe.Type.INSTANCE,
				inventory, level);

		if (match.isPresent()) {
			blockEntity.itemHandler.extractItem(1, 1, false);
			blockEntity.itemHandler.extractItem(2, 1, false);
			blockEntity.itemHandler.getStackInSlot(0).hurt(1, new Random(), null);

			blockEntity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
					blockEntity.itemHandler.getStackInSlot(3).getCount() + 1));

			blockEntity.resetProgress();
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack result) {
		return inventory.getItem(3).getItem() == result.getItem() || inventory.getItem(3).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
	}
}
