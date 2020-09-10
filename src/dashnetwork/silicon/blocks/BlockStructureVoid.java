package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockStructureVoid extends Block implements BlockVersion {

    public BlockStructureVoid() {
        super(Material.BUILDABLE_GLASS);

        c("structureVoid");
        x();
        b(6000000);
        e(255);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_10;
    }

    @Override
    public int getRemapId() {
        return 0;
    }

    @Override
    public AxisAlignedBB a(World world, BlockPosition position, IBlockData blockdata) {
        return null;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean A() {
        return false;
    }

}
