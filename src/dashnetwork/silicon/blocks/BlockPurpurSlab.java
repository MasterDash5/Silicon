package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockPurpurSlab extends BlockStepAbstract implements BlockVersion {

    public static final BlockStateEnum<EnumPurpurSlabType> VARIANT = BlockStateEnum.of("variant", EnumPurpurSlabType.class);
    private boolean isDouble;

    public BlockPurpurSlab(boolean isDouble) {
        super(Material.STONE);

        this.isDouble = isDouble;

        IBlockData blockdata = blockStateList.getBlockData();

        if (!isDouble)
            blockdata = blockdata.set(HALF, EnumSlabHalf.BOTTOM);

        c("purpurSlab");
        j(blockdata.set(VARIANT, EnumPurpurSlabType.DEFAULT));
        c(2);
        b(10);
        a(SoundUtils.STONE2);
    }

    @Override
    public ProtocolVersion getVersion() {
        return ProtocolVersion.v1_9;
    }

    @Override
    public int getRemapId() {
        return isDouble ? 43 : 44;
    }

    @Override
    public String b(int data) {
        return super.getName();
    }

    @Override
    public boolean l() {
        return isDouble;
    }

    @Override
    public IBlockState<EnumPurpurSlabType> n() {
        return VARIANT;
    }

    @Override
    public Object a(ItemStack item) {
        return EnumPurpurSlabType.DEFAULT;
    }

    @Override
    public IBlockData fromLegacyData(int data) {
        IBlockData blockdata = getBlockData().set(VARIANT, EnumPurpurSlabType.DEFAULT);

        if (!isDouble)
            blockdata = blockdata.set(HALF, (data & 8) == 0 ? EnumSlabHalf.BOTTOM : EnumSlabHalf.TOP);

        return blockdata;
    }

    @Override
    public int toLegacyData(IBlockData blockdata) {
        int data = 0;

        if (!isDouble && (blockdata.get(HALF) == EnumSlabHalf.TOP))
            data |= 8;

        return data;
    }

    @Override
    protected BlockStateList getStateList() {
        return isDouble ? new BlockStateList(this, VARIANT) : new BlockStateList(this, HALF, VARIANT);
    }

    public enum EnumPurpurSlabType implements INamable {

        DEFAULT;

        @Override
        public String getName() {
            return "default";
        }

    }

}
