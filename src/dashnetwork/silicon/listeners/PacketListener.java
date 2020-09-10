package dashnetwork.silicon.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.injector.GamePhase;
import com.comphenix.protocol.wrappers.MultiBlockChangeInfo;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import dashnetwork.carbon.Carbon;
import dashnetwork.silicon.Silicon;
import dashnetwork.silicon.utils.BlockVersion;
import dashnetwork.silicon.utils.ChunkUtils;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import us.myles.ViaVersion.api.Via;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class PacketListener extends PacketAdapter {

    private static Silicon plugin = Silicon.getInstance();
    private static Set<PacketType> enabledPackets = Arrays.asList(PacketType.Play.Server.MAP_CHUNK, PacketType.Play.Server.MAP_CHUNK_BULK, PacketType.Play.Server.BLOCK_CHANGE, PacketType.Play.Server.MULTI_BLOCK_CHANGE).stream().collect(Collectors.toSet());

    public PacketListener() {
        super(new AdapterParameteters().gamePhase(GamePhase.BOTH).plugin(plugin).types(enabledPackets).listenerPriority(ListenerPriority.HIGHEST));
    }

    public void start() {
        ProtocolLibrary.getProtocolManager().addPacketListener(this);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        PacketType type = packet.getType();
        Player player = event.getPlayer();
        int version = Via.getAPI().getPlayerVersion(player);

        event.setCancelled(true);

        //             byte[][] inflatedBuffers = (byte[][])event.getPacket().getSpecificModifier(byte[][].class).read(0);
        //            int[] chunkSectionsData = (int[])event.getPacket().getIntegerArrays().read(2);
        //            for (int chunkNumber = 0; chunkNumber < inflatedBuffers.length; chunkNumber++) {
        //              int blocksNumber = 4096 * ProtocolBlockListener.this.getChunkSectionNumber(chunkSectionsData[chunkNumber]);
        //              byte[] data = inflatedBuffers[chunkNumber];
        //              for (int i = 0; i < blocksNumber; i++) {
        //                int id = data[i] & 0xFF;
        //                if (ProtocolBlockListener.this.replacements[id] != -1)
        //                  data[i] = (byte)ProtocolBlockListener.this.replacements[id];
        //              }
        //            }

        if (type.equals(PacketType.Play.Server.MAP_CHUNK)) {
            PacketPlayOutMapChunk.ChunkMap map = packet.getSpecificModifier(PacketPlayOutMapChunk.ChunkMap.class).read(0);
            int blocks = 4096 * ChunkUtils.getSection(map.b);
            byte[] data = map.a;

            for (int i = 0; i < blocks; i++) {
                int id = data[i] & 0xFF;
                Block block = Block.getById(id);

                if (block instanceof BlockVersion) {
                    BlockVersion blockVersion = (BlockVersion) block;

                    if (version < blockVersion.getVersion().getId())
                        data[i] = (byte) blockVersion.getRemapId();
                }
            }

            map.a = data;

            packet.getSpecificModifier(PacketPlayOutMapChunk.ChunkMap.class).write(0, map);

            sendPacket(player, packet);
        } else if (type.equals(PacketType.Play.Server.MAP_CHUNK_BULK)) {
            PacketPlayOutMapChunk.ChunkMap[] maps = packet.getSpecificModifier(PacketPlayOutMapChunk.ChunkMap[].class).read(0);

            for (int chunk = 0; chunk < maps.length; chunk++) {
                PacketPlayOutMapChunk.ChunkMap map = maps[chunk];
                int blocks = 4096 * ChunkUtils.getSection(map.b);
                byte[] data = map.a;

                for (int i = 0; i < blocks; i++) {
                    int id = data[i] & 0xFF;
                    Block block = Block.getById(id);

                    if (block instanceof BlockVersion) {
                        BlockVersion blockVersion = (BlockVersion) block;

                        if (version < blockVersion.getVersion().getId())
                            data[i] = (byte) blockVersion.getRemapId();
                    }
                }

                map.a = data;
                maps[chunk] = map;
            }

            packet.getSpecificModifier(PacketPlayOutMapChunk.ChunkMap[].class).write(0, maps);

            sendPacket(player, packet);
        } else if (type.equals(PacketType.Play.Server.BLOCK_CHANGE)) {
            WrappedBlockData blockdata = packet.getBlockData().read(0);
            Block block = Block.getById(blockdata.getType().getId());

            if (block instanceof BlockVersion) {
                BlockVersion blockVersion = (BlockVersion) block;

                if (version < blockVersion.getVersion().getId()) {
                    blockdata.setType(Material.getMaterial(blockVersion.getRemapId()));
                    packet.getBlockData().write(0, blockdata);
                }
            }

            sendPacket(player, packet);
        } else if (type.equals(PacketType.Play.Server.MULTI_BLOCK_CHANGE)) {
            MultiBlockChangeInfo[] records = packet.getMultiBlockChangeInfoArrays().read(0);

            for (MultiBlockChangeInfo record : records) {
                WrappedBlockData blockdata = record.getData();
                Block block = Block.getById(blockdata.getType().getId());

                if (block instanceof BlockVersion) {
                    BlockVersion blockVersion = (BlockVersion) block;

                    if (version < blockVersion.getVersion().getId()) {
                        blockdata.setType(Material.getMaterial(blockVersion.getRemapId()));
                        record.setData(blockdata);
                    }
                }
            }

            packet.getMultiBlockChangeInfoArrays().write(0, records);

            sendPacket(player, packet);
        }
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public ListeningWhitelist getReceivingWhitelist() {
        return ListeningWhitelist.newBuilder().gamePhase(GamePhase.PLAYING).types(enabledPackets).priority(ListenerPriority.HIGHEST).build();
    }

    @Override
    public ListeningWhitelist getSendingWhitelist() {
        return ListeningWhitelist.newBuilder().gamePhase(GamePhase.PLAYING).types(enabledPackets).priority(ListenerPriority.HIGHEST).build();
    }

    private void sendPacket(Player player, PacketContainer packet) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet, false);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
