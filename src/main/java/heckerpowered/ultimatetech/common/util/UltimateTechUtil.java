package heckerpowered.ultimatetech.common.util;

import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public final class UltimateTechUtil {
    private UltimateTechUtil() {
    }

    public static void setDeltaMovementAndUpdate(Entity entity, Vec3 impulse) {
        entity.setDeltaMovement(impulse);
        entity.hasImpulse = true;
        if (entity instanceof ServerPlayer) {
            var player = (ServerPlayer) entity;
            player.connection.send(new ClientboundSetEntityMotionPacket(
                    player.getId(), player.getDeltaMovement()));
        }
    }
}
