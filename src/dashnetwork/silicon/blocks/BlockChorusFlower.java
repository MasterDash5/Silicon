package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockChorusFlower extends Block implements BlockVersion {

    public BlockChorusFlower() {
        super(Material.PLANT);

        c("chorusFlower");
        c(0.4F);
        a(SoundUtils.WOOD);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 5;
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
