package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockGrassPath extends Block implements BlockVersion {

    public BlockGrassPath() {
        super(Material.EARTH);

        c("grassPath");
        c(0.6F);
        e(255);
        a(SoundUtils.GRASS);
        a(0, 0, 0, 1, 0.9375F, 1);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 60;
    }

}
