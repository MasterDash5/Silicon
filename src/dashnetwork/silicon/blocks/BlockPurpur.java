package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockPurpur extends Block implements BlockVersion {

    public BlockPurpur() {
        super(Material.STONE);

        c("purpurBlock");
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
