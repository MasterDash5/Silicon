package dashnetwork.silicon;

import dashnetwork.silicon.blocks.*;
import dashnetwork.silicon.blocks.BlockNetherWart;
import dashnetwork.silicon.items.ItemBeetroot;
import dashnetwork.silicon.items.ItemBeetrootSeeds;
import dashnetwork.silicon.items.ItemElytra;
import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.ChunkUtils;
import dashnetwork.silicon.utils.MaterialList;
import dashnetwork.silicon.utils.ReflectionUtils;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

import java.lang.reflect.Field;
import java.util.Map;

public class Injector {

    public static void registerAll() throws NoSuchFieldException, IllegalAccessException {
        Block endRod = new BlockEndRod();
        Block chorusPlant = new BlockChorusPlant();
        Block chorusFlower = new BlockChorusFlower();
        Block purpurBlock = new BlockPurpur();
        Block purpurPillar = new BlockPurpurPillar();
        Block purpurStairs = new BlockPurpurStairs(purpurBlock);
        BlockStepAbstract purpurSlab = new BlockPurpurSlab(false);
        BlockStepAbstract purpurDoubleSlab = new BlockPurpurSlab(true);
        Block endBricks = new BlockEndBricks();
        Block beetroots = new BlockBeetroots();
        Block grassPath = new BlockGrassPath();
        Block endGateway = new BlockEndGateway();
        Block repeatingCommandBlock = new BlockRepeatingCommand();
        Block chainCommandBlock = new BlockChainCommand();
        Block frostedIce = new BlockFrostedIce();
        Block magmaBlock = new BlockMagma();
        Block netherWartBlock = new BlockNetherWart();
        Block redNetherBrick = new BlockRedNetherBrick();
        Block boneBlock = new BlockBone();
        Block structureVoid = new BlockStructureVoid();

        registerBlock(198, "end_rod", endRod, new ItemBlock(endRod));
        registerBlock(199, "chorus_plant", chorusPlant, new ItemBlock(chorusPlant));
        registerBlock(200, "chorus_flower", chorusFlower, new ItemBlock(chorusFlower));
        registerBlock(201, "purpur_block", purpurBlock, new ItemBlock(purpurBlock));
        registerBlock(202, "purpur_pillar", purpurPillar, new ItemBlock(purpurPillar));
        registerBlock(203, "purpur_stairs", purpurStairs, new ItemBlock(purpurStairs));
        registerBlock(204, "purpur_double_slab", purpurDoubleSlab);
        registerBlock(205, "purpur_slab", purpurSlab, new ItemStep(purpurSlab, purpurSlab, purpurDoubleSlab));
        registerBlock(206, "end_bricks", endBricks, new ItemBlock(endBricks));
        registerBlock(207, "beetroots", beetroots, new ItemBlock(beetroots));
        registerBlock(208, "grass_path", grassPath, new ItemBlock(grassPath));
        registerBlock(209, "end_gateway", endGateway, new ItemBlock(endGateway));
        registerBlock(210, "repeating_command_block", repeatingCommandBlock, new ItemBlock(repeatingCommandBlock));
        registerBlock(211, "chain_command_block", chainCommandBlock, new ItemBlock(chainCommandBlock));
        registerBlock(212, "frosted_ice", frostedIce, new ItemBlock(frostedIce));
        registerBlock(213, "magma_block", magmaBlock, new ItemBlock(magmaBlock));
        registerBlock(214, "nether_wart_block", netherWartBlock, new ItemBlock(netherWartBlock));
        registerBlock(215, "red_nether_brick", redNetherBrick, new ItemBlock(redNetherBrick));
        registerBlock(216, "bone_block", boneBlock, new ItemBlock(boneBlock));
        registerBlock(217, "structure_void", structureVoid, new ItemBlock(structureVoid));

        Item beetroot = new ItemBeetroot();
        Item beetrootSeeds = new ItemBeetrootSeeds(beetroots);
        Item elytra = new ItemElytra();

        registerItem(434, "beetroot", beetroot);
        registerItem(435, "beetroot_seeds", beetrootSeeds);
        registerItem(443, "elytra", elytra);

        MaterialList.startup();

        fixBlocksRefs();
        fixItemsRefs();
    }

    public static void registerPackets(Player player) {
        ChannelDuplexHandler handler = new ChannelDuplexHandler() {
            private int version = Via.getAPI().getPlayerVersion(player);

            @Override
            public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
                super.channelRead(context, packet);
            }

            @Override
            public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
                if (version < ProtocolVersion.v1_12_2.getId()) {
                    if (packet instanceof PacketPlayOutBlockChange) {
                        PacketPlayOutBlockChange blockChange = (PacketPlayOutBlockChange) packet;
                        Block block = blockChange.block.getBlock();

                        if (block instanceof BlockVersion) {
                            BlockVersion blockVersion = (BlockVersion) block;

                            if (version < blockVersion.getVersion().getId())
                                blockChange.block = Block.getById(blockVersion.getRemapId()).getBlockData();
                        }
                    } /** else if (packet instanceof PacketPlayOutMultiBlockChange) {
                     PacketPlayOutMultiBlockChange multiBlockChange = (PacketPlayOutMultiBlockChange) packet;
                     Field field = multiBlockChange.getClass().getField("b");

                     field.setAccessible(true);

                     PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[] infos = (PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[]) field.get(PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[].class);

                     for (PacketPlayOutMultiBlockChange.MultiBlockChangeInfo info : infos) {
                     Block block = info.c().getBlock();

                     if (block instanceof BlockVersion) {
                     BlockVersion blockVersion = (BlockVersion) block;

                     if (version < blockVersion.getVersion().getId()) {
                     PacketPlayOutMultiBlockChange.MultiBlockChangeInfo overwrite = multiBlockChange.MultiBlockChangeInfo(info.b(), Block.getById(blockVersion.getRemapId()));
                     }
                     }
                     }
                     }*/ else if (packet instanceof PacketPlayOutMapChunk) {
                        PacketPlayOutMapChunk mapChunk = (PacketPlayOutMapChunk) packet;
                        Field field = mapChunk.getClass().getDeclaredField("c");
                        field.setAccessible(true);
                        PacketPlayOutMapChunk.ChunkMap chunk = (PacketPlayOutMapChunk.ChunkMap) field.get(PacketPlayOutMapChunk.ChunkMap.class);

                        int blocksNumber = 4096 * ChunkUtils.getSection(chunk.b);
                        byte[] data = chunk.a;

                        for (int i = 0; i < blocksNumber; i++) {
                            Block block = Block.getById(data[i] & 0xFF);

                            if (block instanceof BlockVersion) {
                                BlockVersion blockVersion = (BlockVersion) block;

                                if (version < blockVersion.getVersion().getId())
                                    data[i] = (byte) blockVersion.getRemapId();
                            }
                        }

                        field.set(mapChunk, chunk);
                    }
                }

                super.write(context, packet, promise);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), handler);
    }

    private static void registerBlock(int id, String name, Block block) {
        Block.REGISTRY.a(id, new MinecraftKey(name), block);

        for (IBlockData blockdata : block.P().a()) {
            final int stateId = (id << 4) | block.toLegacyData(blockdata);
            Block.d.a(blockdata, stateId);
        }
    }

    private static void registerItem(int id, String name, Item item) {
        Item.REGISTRY.a(id, new MinecraftKey(name), item);
    }

    private static void registerBlock(int id, String name, Block block, Item item) {
        MinecraftKey key = new MinecraftKey(name);
        Block.REGISTRY.a(id, key, block);

        for (IBlockData blockdata : block.P().a()) {
            final int stateId = (id << 4) | block.toLegacyData(blockdata);
            Block.d.a(blockdata, stateId);
        }

        Item.REGISTRY.a(id, key, item);
        ReflectionUtils.<Map<Block, Item>>getFieldValue(Item.class, "a", null).put(block, item);
    }

    private static void fixBlocksRefs() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        for (Field field : Blocks.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (Block.class.isAssignableFrom(field.getType())) {
                Block block = (Block) field.get(null);
                Block newblock = Block.getById(Block.getId(block));

                if (block != newblock)
                    ReflectionUtils.setFinalField(field, null, newblock);
            }
        }
    }

    private static void fixItemsRefs() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        for (Field field : Items.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (Item.class.isAssignableFrom(field.getType())) {
                Item block = (Item) field.get(null);
                Item newblock = Item.getById(Item.getId(block));

                if (block != newblock)
                    ReflectionUtils.setFinalField(field, null, newblock);
            }
        }
    }

}
