package heckerpowered.ultimatetech.common.item;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import heckerpowered.ultimatetech.common.util.UltimateTechUtil;
import heckerpowered.ultimatetech.common.util.concurrent.ScheduledUtil;
import heckerpowered.ultimatetech.common.util.concurrent.cancellation.ConditionCancellation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public final class BlinkItem extends EnergizedItem {
    public BlinkItem() {
        super(UltimateTechUtil.newProperties().stacksTo(1), 480000);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var item = player.getItemInHand(hand);
        if (!consumeEnergy(item, 12000)) {
            return InteractionResultHolder.pass(item);
        }

        player.getCooldowns().addCooldown(this, 10);
        var impulse = player.getDeltaMovement();
        var direction = Vec3.directionFromRotation(0, player.getYRot());
        var forward = direction.scale(1.2);
        var shouldPhaseWalk = level.getBlockState(new BlockPos(
                direction.scale(6).add(player.position())).above()).isAir();
        player.getCapability(Capabilities.PLAYER_INVULNERABLE_CAPABILITY).ifPresent(inv -> {
            player.getCapability(Capabilities.PLAYER_PHASE_CAPABILITY).ifPresent(phase -> {
                ScheduledUtil.addTask(() -> {
                    player.setDeltaMovement(forward);
                }).cancel(ConditionCancellation.afterTicks(10)).then(() -> {
                    if (shouldPhaseWalk) {
                        phase.setPhase(false);
                    }

                    player.setDeltaMovement(impulse);
                    inv.setInvulnerable(false);
                }).with(() -> {
                    if (shouldPhaseWalk) {
                        phase.setPhase(true);
                    }
                    inv.setInvulnerable(true);
                });
            });
        });

        return InteractionResultHolder.pass(item);
    }
}
