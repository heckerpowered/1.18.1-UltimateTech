package heckerpowered.ultimatetech.common.network;

import java.util.Optional;
import java.util.function.Function;

import heckerpowered.ultimatetech.UltimateTech;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

public final class UltimateTechPacketHandler {
    // Bump this number every time theres a breaking change, to ensure people dont
    // mess things up when joining on the wrong version
    private static final String PROTOCOL_VERSION = "114514";
    private static int index;
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            UltimateTech.rl("channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private UltimateTechPacketHandler() {
    }

    public static void initialize() {
        registerServerToClient(UpdatePhasePacket.class, UpdatePhasePacket::decode);
    }

    protected static <MSG extends IUltimateTechPacket> void registerClientToServer(Class<MSG> type,
            Function<FriendlyByteBuf, MSG> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_SERVER);
    }

    protected static <MSG extends IUltimateTechPacket> void registerServerToClient(Class<MSG> type,
            Function<FriendlyByteBuf, MSG> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_CLIENT);
    }

    protected static <MSG extends IUltimateTechPacket> void registerMessage(Class<MSG> type,
            Function<FriendlyByteBuf, MSG> decoder,
            NetworkDirection networkDirection) {
        CHANNEL.registerMessage(index++, type, IUltimateTechPacket::encode, decoder, IUltimateTechPacket::handle,
                Optional.of(networkDirection));
    }

    /**
     * Send this message to the specified player.
     *
     * @param message - the message to send
     * @param player  - the player to send it to
     */
    public static <MSG> void sendTo(MSG message, ServerPlayer player) {
        // Validate it is not a fake player, even though none of our code should call
        // this with a fake player
        if (!(player instanceof FakePlayer)) {
            CHANNEL.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    /**
     * Send this message to everyone connected to the server.
     *
     * @param message - message to send
     */
    public static <MSG> void sendToAll(MSG message) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), message);
    }

    /**
     * Send this message to everyone connected to the server if the server has
     * loaded.
     *
     * @param message - message to send
     *
     * @apiNote This is useful for reload listeners
     */
    public static <MSG> void sendToAllIfLoaded(MSG message) {
        if (ServerLifecycleHooks.getCurrentServer() != null) {
            // If the server has loaded, send to all players
            sendToAll(message);
        }
    }

    /**
     * Send this message to everyone within the supplied dimension.
     *
     * @param message   - the message to send
     * @param dimension - the dimension to target
     */
    public static <MSG> void sendToDimension(MSG message, ResourceKey<Level> dimension) {
        CHANNEL.send(PacketDistributor.DIMENSION.with(() -> dimension), message);
    }

    /**
     * Send this message to the server.
     *
     * @param message - the message to send
     */
    public static <MSG> void sendToServer(MSG message) {
        CHANNEL.sendToServer(message);
    }

    public static <MSG> void sendToAllTracking(MSG message, Entity entity) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
    }

    public static <MSG> void sendToAllTrackingAndSelf(MSG message, Entity entity) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
    }

    public static <MSG> void sendToAllTracking(MSG message, BlockEntity tile) {
        sendToAllTracking(message, tile.getLevel(), tile.getBlockPos());
    }

    public static <MSG> void sendToAllTracking(MSG message, Level world, BlockPos pos) {
        if (world instanceof ServerLevel) {
            // If we have a ServerWorld just directly figure out the ChunkPos so as to not
            // require looking up the chunk
            // This provides a decent performance boost over using the packet distributor
            var chunkSource = ((ServerLevel) world).getChunkSource();
            chunkSource.chunkMap.getPlayers(new ChunkPos(pos), false)
                    .forEach(p -> sendTo(message, p));
        } else {
            // Otherwise fallback to entities tracking the chunk if some mod did something
            // odd and our world is not a ServerWorld
            CHANNEL.send(
                    PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunk(pos.getX() >> 4, pos.getZ() >> 4)),
                    message);
        }
    }
}
