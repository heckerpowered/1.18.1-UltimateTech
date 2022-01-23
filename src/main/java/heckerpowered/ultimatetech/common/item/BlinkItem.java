package heckerpowered.ultimatetech.common.item;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import heckerpowered.ultimatetech.common.registries.UltimateTechItem;
import heckerpowered.ultimatetech.common.util.UltimateTechUtil;
import heckerpowered.ultimatetech.common.util.concurrent.ScheduledUtil;
import heckerpowered.ultimatetech.common.util.concurrent.cancellation.ConditionCancellation;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public final class BlinkItem extends EnergizedItem {
    public BlinkItem() {
        super(UltimateTechItem.newProperties().stacksTo(1), 4800000);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var item = player.getItemInHand(hand);
        if (!consumeEnergy(item, 12000)) {
            return InteractionResultHolder.pass(item);
        }

        if (player instanceof ServerPlayer) {
            player.getCooldowns().addCooldown(this, 10);
            var impulse = player.getDeltaMovement();
            var direction = Vec3.directionFromRotation(0, player.getYRot());
            var forward = direction.scale(1.2);
            var shouldPhaseWalk = level.getBlockState(new BlockPos(
                    direction.scale(6).add(player.position())).above()).isAir();
            player.getCapability(Capabilities.PLAYER_INVULNERABLE_CAPABILITY).ifPresent(inv -> {
                player.getCapability(Capabilities.PLAYER_PHASE_CAPABILITY).ifPresent(phase -> {
                    ServerPlayer serverPlayer = (ServerPlayer) player;
                    ScheduledUtil.addTask(() -> {
                        player.hasImpulse = true;
                        UltimateTechUtil.setDeltaMovementAndUpdate(player, forward);
                        // Update motion manually
                        serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(
                                player.getId(), player.getDeltaMovement()));
                    }).cancel(ConditionCancellation.afterTicks(10)).then(() -> {
                        if (shouldPhaseWalk) {
                            phase.setPhase(false);
                            phase.updatePhase(serverPlayer);
                        }

                        UltimateTechUtil.setDeltaMovementAndUpdate(player, impulse);
                        inv.setInvulnerable(false);
                    }).with(() -> {
                        if (shouldPhaseWalk) {
                            phase.setPhase(true);
                            phase.updatePhase(serverPlayer);
                        }
                        inv.setInvulnerable(true);
                    });
                });
            });
        }

        return InteractionResultHolder.pass(item);
    }
}
