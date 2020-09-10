package dashnetwork.silicon.items;

import dashnetwork.silicon.utils.BlockVersion;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.ItemSeeds;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class ItemBeetrootSeeds extends ItemSeeds implements BlockVersion {

    public ItemBeetrootSeeds(Block block) {
        super(block, Blocks.FARMLAND);

        c("beetroot_seeds");
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 295;
    }

}
