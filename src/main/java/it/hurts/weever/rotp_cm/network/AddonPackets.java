package it.hurts.weever.rotp_cm.network;

import java.util.Optional;

import com.github.standobyte.jojo.network.packets.IModPacketHandler;
import it.hurts.weever.rotp_cm.RotpCMAddon;
import it.hurts.weever.rotp_cm.network.server.CommonConfigPacket;

import it.hurts.weever.rotp_cm.network.server.ResetSyncedCommonConfigPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class AddonPackets {
    private static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel channel;
    private static SimpleChannel serverChannel;
    private static SimpleChannel clientChannel;
    private static int packetIndex;

    public static void init() {
        serverChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RotpCMAddon.MOD_ID, "server_channel"))
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .simpleChannel();
        clientChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RotpCMAddon.MOD_ID, "client_channel"))
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .simpleChannel();

        int packetIndex = 0;
        registerMessage(serverChannel, new CommonConfigPacket.Handler(),                   Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        registerMessage(serverChannel, new ResetSyncedCommonConfigPacket.Handler(),        Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    private static <MSG> void registerMessage(SimpleChannel channel, IModPacketHandler<MSG> handler, Optional<NetworkDirection> networkDirection) {
        if (packetIndex > 127) {
            throw new IllegalStateException("Too many packets (> 127) registered for a single channel!");
        }
        channel.registerMessage(packetIndex++, handler.getPacketClass(), handler::encode, handler::decode, handler::enqueueHandleSetHandled, networkDirection);
    }

    public static void sendToClient(Object msg, ServerPlayerEntity player) {
        if (!(player instanceof FakePlayer)) {
            channel.send(PacketDistributor.PLAYER.with(() -> player), msg);
        }
    }

    public static void sendToClientsTracking(Object msg, Entity entity) {
        channel.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
    }

    public static void sendToClientsTrackingAndSelf(Object msg, Entity entity) {
        channel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
    }
}
