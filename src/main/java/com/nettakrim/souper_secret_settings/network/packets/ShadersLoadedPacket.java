package com.nettakrim.souper_secret_settings.network.packets;

import com.nettakrim.souper_secret_settings.SouperSecretSettingsClient;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.nio.charset.StandardCharsets;

public record ShadersLoadedPacket(String shaders) implements CustomPayload {
    public static final Identifier SHADERS_LOADED_PACKET_ID =
            Identifier.of(SouperSecretSettingsClient.MODID, "shaders_loaded_packet_id");

    public static final CustomPayload.Id<ShadersLoadedPacket> PACKET_ID =
            new CustomPayload.Id<>(ShadersLoadedPacket.SHADERS_LOADED_PACKET_ID);

    public static final PacketCodec<RegistryByteBuf, ShadersLoadedPacket> CODEC = PacketCodec.tuple(
            new PacketCodec<ByteBuf, String>() {

                public String decode(ByteBuf byteBuf) {
                    int readableBytes = byteBuf.readableBytes();
                    byte[] bytes = new byte[readableBytes];
                    byteBuf.readBytes(bytes);
                    return new String(bytes, StandardCharsets.UTF_8);
                }

                public void encode(ByteBuf byteBuf, String string) {
                    byteBuf.writeBytes(string.getBytes(StandardCharsets.UTF_8));
                }
            },
            ShadersLoadedPacket::shaders, ShadersLoadedPacket::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}