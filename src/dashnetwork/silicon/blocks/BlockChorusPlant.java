package dashnetwork.silicon.blocks;

import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.BlockList;
import dashnetwork.silicon.utils.SoundUtils;
import net.minecraft.server.v1_8_R3.*;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class BlockChorusPlant extends Block implements BlockVersion {

    public static final BlockStateBoolean UP = BlockStateBoolean.of("up");
    public static final BlockStateBoolean DOWN = BlockStateBoolean.of("down");
    public static final BlockStateBoolean NORTH = BlockStateBoolean.of("north");
    public static final BlockStateBoolean EAST = BlockStateBoolean.of("east");
    public static final BlockStateBoolean SOUTH = BlockStateBoolean.of("south");
    public static final BlockStateBoolean WEST = BlockStateBoolean.of("west");

    public BlockChorusPlant() {
        super(Material.PLANT);

        c("chorusPlant");
        j(blockStateList.getBlockData()
                .set(UP, false)
                .set(DOWN, false)
                .set(NORTH, false)
                .set(EAST, false)
                .set(SOUTH, false)
                .set(WEST, false));
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

    @Override
    public AxisAlignedBB a(World world, BlockPosition position, IBlockData blockdata) {
        float minX = blockdata.get(WEST) ? 0 : 0.1875F;
        float minY = blockdata.get(DOWN) ? 0 : 0.1875F;
        float minZ = blockdata.get(NORTH) ? 0 : 0.1875F;
        float maxX = blockdata.get(EAST) ? 0 : 0.1875F;
        float maxY = blockdata.get(UP) ? 0 : 0.1875F;
        float maxZ = blockdata.get(SOUTH) ? 0 : 0.1875F;

        return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public IBlockData updateState(IBlockData blockdata, IBlockAccess access, BlockPosition position) {
        Block up = access.getType(position.up()).getBlock();
        Block down = access.getType(position.down()).getBlock();
        Block north = access.getType(position.north()).getBlock();
        Block east = access.getType(position.east()).getBlock();
        Block south = access.getType(position.south()).getBlock();
        Block west = access.getType(position.west()).getBlock();

        blockdata.set(UP, up == this || up == BlockList.CHORUS_FLOWER);
        blockdata.set(DOWN, down == this || down == BlockList.CHORUS_FLOWER || down == Blocks.END_STONE);
        blockdata.set(NORTH, north == this || up == BlockList.CHORUS_FLOWER);
        blockdata.set(EAST, east == this || up == BlockList.CHORUS_FLOWER);
        blockdata.set(SOUTH, south == this || up == BlockList.CHORUS_FLOWER);
        blockdata.set(WEST, west == this || up == BlockList.CHORUS_FLOWER);

        return blockdata;
    }

    @Override
    public int toLegacyData(IBlockData blockdata) {
        return 0;
    }

    @Override
    protected BlockStateList getStateList() {
        return new BlockStateList(this, UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }

}
