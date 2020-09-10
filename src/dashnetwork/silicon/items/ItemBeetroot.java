package dashnetwork.silicon.items;

import dashnetwork.silicon.utils.BlockVersion;
import net.minecraft.server.v1_8_R3.ItemFood;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class ItemBeetroot extends ItemFood implements BlockVersion {

    public ItemBeetroot() {
        super(2, 1.2F, false);

        c("beetroot");
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 392;
    }

}
