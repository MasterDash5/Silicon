package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockFrostedIce extends BlockIce implements BlockVersion {

    public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 3);

    public BlockFrostedIce() {
        c("frostedIce");
        j(blockStateList.getBlockData().set(AGE, 0));
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 79;
    }

    @Override
    public IBlockData fromLegacyData(int data) {
        return getBlockData().set(AGE, MathHelper.clamp(data, 0, 3));
    }

    @Override
    public int toLegacyData(IBlockData blockdata) {
        return blockdata.get(AGE);
    }

    @Override
    protected BlockStateList getStateList() {
        return new BlockStateList(this, AGE);
    }

}
