package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockNetherWart extends Block implements BlockVersion {

    public BlockNetherWart() {
        super(Material.GRASS);

        c("netherWartBlock");
        c(1);
        a(SoundUtils.GRASS);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_10;
    }

    @Override
    public int getRemapId() {
        return 35;
    }

}
