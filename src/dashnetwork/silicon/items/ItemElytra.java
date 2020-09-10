package dashnetwork.silicon.items;

import dashnetwork.silicon.utils.BlockVersion;
import net.minecraft.server.v1_8_R3.Item;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class ItemElytra extends Item implements BlockVersion {

    public ItemElytra() {
        c("elytra");
        maxStackSize = 1;
        setMaxDurability(432);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 299;
    }

}
