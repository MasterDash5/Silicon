package dashnetwork.silicon.blocks.utils;

import net.minecraft.server.v1_8_R3.*;

// Minecraft's BlockDirectional class is limited to horizontal only
public class BlockDirectional extends net.minecraft.server.v1_8_R3.BlockDirectional {

    public static final BlockStateDirection FACING = BlockStateDirection.of("facing");

    protected BlockDirectional(Material material) {
        super(material);
    }

    protected BlockDirectional(Material material, MaterialMapColor color) {
        super(material, color);
    }

    @Override
    public IBlockData getPlacedState(World world, BlockPosition position, EnumDirection direction, float x, float y, float z, int data, EntityLiving entity) {
        return getBlockData().set(FACING, BlockPiston.a(world, position, entity));
    }

}
