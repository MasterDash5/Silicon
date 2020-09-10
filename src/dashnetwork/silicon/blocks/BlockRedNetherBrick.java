package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockRedNetherBrick extends Block implements BlockVersion {

    public BlockRedNetherBrick() {
        super(Material.STONE);

        c("redNetherBrick");
        c(2);
        b(10);
        a(SoundUtils.STONE3);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_10;
    }

    @Override
    public int getRemapId() {
        return 112;
    }

}
