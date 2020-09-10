package dashnetwork.silicon.blocks;

import dashnetwork.silicon.blocks.utils.BlockDirectional;
import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockEndRod extends BlockDirectional implements BlockVersion {

    public BlockEndRod() {
        super(Material.ORIENTABLE);

        c("endRod");
        j(blockStateList.getBlockData().set(FACING, EnumDirection.UP));
        a(0.9375F);
        c(0);
        a(SoundUtils.WOOD);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 89;
    }

    @Override
    public boolean isOccluding() {
        return true;
    }

    @Override
    public IBlockData getPlacedState(World world, BlockPosition position, EnumDirection direction, float x, float y, float z, int data, EntityLiving entity) {
        IBlockData blockdata = world.getType(position.shift(direction.opposite()));
        EnumDirection facing = blockdata.get(FACING);

        if (facing == direction)
            return getBlockData().set(FACING, direction.opposite());

        return getBlockData().set(FACING, direction);
    }

    @Override
    public AxisAlignedBB a(World world, BlockPosition position, IBlockData blockdata) {
        EnumDirection direction = blockdata.get(FACING);

        switch (direction) {
            case NORTH:
            case SOUTH:
                return new AxisAlignedBB(0.375, 0.375, 0, 0.625, 0.625, 1);
            case EAST:
            case WEST:
                return new AxisAlignedBB(0, 0.375, 0.375, 1, 0.625, 0.625);
            default:
                return new AxisAlignedBB(0.375, 0, 0.375, 0.625, 1, 0.625);
        }
    }

    @Override
    public IBlockData fromLegacyData(int data) {
        return getBlockData().set(FACING, EnumDirection.fromType1(data));
    }

    @Override
    public int toLegacyData(IBlockData blockdata) {
        return blockdata.get(FACING).a();
    }

    @Override
    protected BlockStateList getStateList() {
        return new BlockStateList(this, FACING);
    }

}
