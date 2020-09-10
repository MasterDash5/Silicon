package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockMagma extends Block implements BlockVersion {

    public BlockMagma() {
        super(Material.STONE);

        c("magma");
        c(0.5F);
        a(0.2F);
        a(true);
        a(SoundUtils.STONE3);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_10;
    }

    @Override
    public int getRemapId() {
        return 87;
    }

}
