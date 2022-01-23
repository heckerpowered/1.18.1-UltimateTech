package heckerpowered.ultimatetech.common.network;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public class UpdatePhasePacket implements IUltimateTechPacket {
    private final boolean isPhase;

    public UpdatePhasePacket(boolean isPhase) {
        this.isPhase = isPhase;
    }

    @Override
    public void handle(Context context) {
        var minecraft = Minecraft.getInstance();
        var player = minecraft.player;
        if (player != null) {
            player.getCapability(Capabilities.PLAYER_PHASE_CAPABILITY).ifPresent(p -> {
                p.setPhase(isPhase);
            });
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isPhase);
    }

    public static UpdatePhasePacket decode(FriendlyByteBuf buffer) {
        return new UpdatePhasePacket(buffer.readBoolean());
    }
}
