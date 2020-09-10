package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.ItemList;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockBeetroots extends BlockCrops implements BlockVersion {

    public BlockBeetroots() {
        c("beetroots");
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 142;
    }

    @Override
    public Item l() {
        return ItemList.BEETROOT_SEEDS;
    }

    @Override
    public Item n() {
        return ItemList.BEETROOT;
    }

    @Override
    public int toLegacyData(IBlockData blockdata) {
        return MathHelper.clamp(blockdata.get(AGE), 0, 3);
    }

}
