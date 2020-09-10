package dashnetwork.silicon.blocks.utils;

import net.minecraft.server.v1_8_R3.*;

public class BlockRotatable extends net.minecraft.server.v1_8_R3.BlockRotatable {

    public static final BlockStateEnum<EnumDirection.EnumAxis> AXIS = BlockStateEnum.of("axis", EnumDirection.EnumAxis.class);

    protected BlockRotatable(Material material) {
        super(material, material.r());
    }

    protected BlockRotatable(Material material, MaterialMapColor color) {
        super(material, color);
    }

    @Override
    public IBlockData fromLegacyData(int data) {
        EnumDirection.EnumAxis axis = EnumDirection.EnumAxis.Y;
        int read = data & 12;

        switch (read) {
            case 4:
                axis = EnumDirection.EnumAxis.X;
                break;
            case 8:
                axis = EnumDirection.EnumAxis.Z;
                break;
        }

        return getBlockData().set(AXIS, axis);
    }

    @Override
    public int toLegacyData(IBlockData blockdata) {
        EnumDirection.EnumAxis axis = blockdata.get(AXIS);
        int data = 0;

        switch (axis) {
            case X:
                data |= 4;
                break;
            case Z:
                data |= 8;
                break;
        }

        return data;
    }

    @Override
    protected BlockStateList getStateList() {
        return new BlockStateList(this, AXIS);
    }

    @Override
    public IBlockData getPlacedState(World world, BlockPosition position, EnumDirection direction, float x, float y, float z, int data, EntityLiving entity) {
        return super.getPlacedState(world, position, direction, x, y, z, data, entity).set(AXIS, direction.k());
    }

}
