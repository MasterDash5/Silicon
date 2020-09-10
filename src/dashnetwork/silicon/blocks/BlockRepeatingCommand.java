package dashnetwork.silicon.blocks;

import dashnetwork.silicon.blocks.utils.BlockCommand;
import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Material;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockRepeatingCommand extends BlockCommand implements BlockVersion {

    public BlockRepeatingCommand() {
        super(Material.ORE);

        c("repeatingCommandBlock");
        j(blockStateList.getBlockData().set(FACING, EnumDirection.DOWN).set(CONDITIONAL, false));
        x();
        b(6000000);
        a(SoundUtils.WOOD);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return 137;
    }

}
