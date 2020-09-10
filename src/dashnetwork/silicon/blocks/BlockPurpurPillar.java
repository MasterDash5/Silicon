package dashnetwork.silicon.blocks;

import dashnetwork.silicon.blocks.utils.BlockRotatable;
import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockPurpurPillar extends BlockRotatable implements BlockVersion {

    public BlockPurpurPillar() {
        super(Material.STONE);

        c("purpurPillar");
        c(1.5F);
        b(10);
        a(SoundUtils.STONE2);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 98;
    }

}
