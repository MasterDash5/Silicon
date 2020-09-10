package dashnetwork.silicon.blocks;

import dashnetwork.silicon.blocks.utils.BlockRotatable;
import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockBone extends BlockRotatable implements BlockVersion {

    public BlockBone() {
        super(Material.STONE);

        c("boneBlock");
        c(2);
        a(SoundUtils.STONE3);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_10;
    }

    @Override
    public int getRemapId() {
        return 255;
    }

}
