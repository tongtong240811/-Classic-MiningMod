package com.tong.miningmod.block;

import java.util.stream.Stream;

import com.tong.miningmod.block.entity.HeavyAnvilBlockEntity;
import com.tong.miningmod.init.BlockEntityTypesInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class HeavyAnvilBlock extends BaseEntityBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	private static final VoxelShape NORTH = Stream
			.of(Block.box(15, 8, 6, 16, 9, 10), Block.box(14, 8, 5, 15, 9, 11), Block.box(13, 8, 4, 14, 9, 12),
					Block.box(0, 8, 4, 3, 9, 12), Block.box(3, 8, 3, 13, 9, 13), Block.box(5, 3, 5, 11, 8, 11),
					Block.box(4, 0, 4, 12, 3, 12), Block.box(11, 0, 1, 15, 1, 5), Block.box(1, 0, 1, 5, 1, 5),
					Block.box(11, 0, 11, 15, 1, 15), Block.box(1, 0, 11, 5, 1, 15))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape EAST = Stream
			.of(Block.box(6, 8, 15, 10, 9, 16), Block.box(5, 8, 14, 11, 9, 15), Block.box(4, 8, 13, 12, 9, 14),
					Block.box(4, 8, 0, 12, 9, 3), Block.box(3, 8, 3, 13, 9, 13), Block.box(5, 3, 5, 11, 8, 11),
					Block.box(4, 0, 4, 12, 3, 12), Block.box(11, 0, 11, 15, 1, 15), Block.box(11, 0, 1, 15, 1, 5),
					Block.box(1, 0, 11, 5, 1, 15), Block.box(1, 0, 1, 5, 1, 5))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape SOUTH = Stream
			.of(Block.box(0, 8, 6, 1, 9, 10), Block.box(1, 8, 5, 2, 9, 11), Block.box(2, 8, 4, 3, 9, 12),
					Block.box(13, 8, 4, 16, 9, 12), Block.box(3, 8, 3, 13, 9, 13), Block.box(5, 3, 5, 11, 8, 11),
					Block.box(4, 0, 4, 12, 3, 12), Block.box(1, 0, 11, 5, 1, 15), Block.box(11, 0, 11, 15, 1, 15),
					Block.box(1, 0, 1, 5, 1, 5), Block.box(11, 0, 1, 15, 1, 5))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape WEST = Stream
			.of(Block.box(6, 8, 0, 10, 9, 1), Block.box(5, 8, 1, 11, 9, 2), Block.box(4, 8, 2, 12, 9, 3),
					Block.box(4, 8, 13, 12, 9, 16), Block.box(3, 8, 3, 13, 9, 13), Block.box(5, 3, 5, 11, 8, 11),
					Block.box(4, 0, 4, 12, 3, 12), Block.box(1, 0, 1, 5, 1, 5), Block.box(1, 0, 11, 5, 1, 15),
					Block.box(11, 0, 1, 15, 1, 5), Block.box(11, 0, 11, 15, 1, 15))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public HeavyAnvilBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		switch (state.getValue(FACING)) {
		case EAST:
			return EAST;
		case SOUTH:
			return SOUTH;
		case WEST:
			return WEST;
		default:
			return NORTH;
		}
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
			BlockEntityType<T> type) {
		return createTickerHelper(type, BlockEntityTypesInit.HEAVY_ANVIL_BLOCK_ENTITY_TYPE.get(),
				HeavyAnvilBlockEntity::tick);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HeavyAnvilBlockEntity(pos, state);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof HeavyAnvilBlockEntity) {
				((HeavyAnvilBlockEntity) blockEntity).drops();
			}
		}
		super.onRemove(state, level, pos, newState, moving);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		if (!level.isClientSide()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof HeavyAnvilBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (HeavyAnvilBlockEntity) blockEntity, pos);
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide());
	}
}
