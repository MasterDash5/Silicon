package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockStairs;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockPurpurStairs extends BlockStairs implements BlockVersion {

    public BlockPurpurStairs(Block block) {
        super(block.getBlockData());

        c("stairsPurpur");
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 109;
    }

}
