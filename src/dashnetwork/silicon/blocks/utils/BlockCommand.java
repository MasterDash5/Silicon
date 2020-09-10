package dashnetwork.silicon.blocks.utils;

import net.minecraft.server.v1_8_R3.*;

public class BlockCommand extends BlockDirectional {

    public static final BlockStateBoolean CONDITIONAL = BlockStateBoolean.of("conditional");

    protected BlockCommand(Material material) {
        super(material);
    }

    protected BlockCommand(Material material, MaterialMapColor color) {
        super(material, color);
    }

    @Override
    public IBlockData fromLegacyData(int data) {
        return getBlockData().set(FACING, BlockPiston.b(data)).set(CONDITIONAL, data > 7);
    }

    @Override
    public int toLegacyData(IBlockData blockdata) {
        int data = blockdata.get(FACING).a();

        if (blockdata.get(CONDITIONAL))
            data |= 8;

        return data;
    }

    @Override
    protected BlockStateList getStateList() {
        return new BlockStateList(this, FACING, CONDITIONAL);
    }

}
