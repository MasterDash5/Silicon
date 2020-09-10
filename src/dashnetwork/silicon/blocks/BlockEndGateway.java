package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockEndGateway extends Block implements BlockVersion {

    public BlockEndGateway() {
        super(Material.PORTAL);

        c("endGateway");
        a(1);
        x();
        b(6000000);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 119;
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
