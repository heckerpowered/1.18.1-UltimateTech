package heckerpowered.ultimatetech.common.capabilities.phase;

import heckerpowered.ultimatetech.common.network.UltimateTechPacketHandler;
import heckerpowered.ultimatetech.common.network.UpdatePhasePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public interface IPhase {
    public void setPhase(boolean phase);

    public void clearPhase();

    public boolean isPhase();

    default void updatePhase(Player player) {
        if (player instanceof ServerPlayer) {
            UltimateTechPacketHandler.sendTo(new UpdatePhasePacket(isPhase()), (ServerPlayer) player);
        }
    }
}
