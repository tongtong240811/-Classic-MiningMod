package com.tong.miningmod.block;

import java.util.stream.Stream;

import com.tong.miningmod.block.entity.NuclearReflectorBlockEntity;
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

public class NuclearReflectorBlock extends BaseEntityBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	private static final VoxelShape NORTH = Stream
			.of(Block.box(4, 18, 4, 5, 19, 12), Block.box(4, 17, 11, 5, 18, 12), Block.box(4, 17, 4, 5, 18, 5),
					Block.box(2, 16, 2, 9, 17, 14), Block.box(10, 16, 6, 14, 26, 10), Block.box(0, 0, 0, 16, 16, 16))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape EAST = Stream
			.of(Block.box(4, 18, 4, 12, 19, 5), Block.box(4, 17, 4, 5, 18, 5), Block.box(11, 17, 4, 12, 18, 5),
					Block.box(2, 16, 2, 14, 17, 9), Block.box(6, 16, 10, 10, 26, 14), Block.box(0, 0, 0, 16, 16, 16))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape SOUTH = Stream
			.of(Block.box(11, 18, 4, 12, 19, 12), Block.box(11, 17, 4, 12, 18, 5), Block.box(11, 17, 11, 12, 18, 12),
					Block.box(7, 16, 2, 14, 17, 14), Block.box(2, 16, 6, 6, 26, 10), Block.box(0, 0, 0, 16, 16, 16))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape WEST = Stream
			.of(Block.box(4, 18, 11, 12, 19, 12), Block.box(11, 17, 11, 12, 18, 12), Block.box(4, 17, 11, 5, 18, 12),
					Block.box(2, 16, 7, 14, 17, 14), Block.box(6, 16, 2, 10, 26, 6), Block.box(0, 0, 0, 16, 16, 16))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public NuclearReflectorBlock(Properties properties) {
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
		return createTickerHelper(type, BlockEntityTypesInit.NUCLEAR_RFLECTOR_BLOCK_ENTITY_TYPE.get(),
				NuclearReflectorBlockEntity::tick);
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
		return new NuclearReflectorBlockEntity(pos, state);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof NuclearReflectorBlockEntity) {
				((NuclearReflectorBlockEntity) blockEntity).drops();
			}
		}
		super.onRemove(state, level, pos, newState, moving);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		if (!level.isClientSide()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof NuclearReflectorBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (NuclearReflectorBlockEntity) blockEntity, pos);
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide());
	}
}
