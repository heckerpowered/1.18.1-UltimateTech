package heckerpowered.ultimatetech.common.event;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class CommonEventHandler {
    private CommonEventHandler() {
    }

    @SubscribeEvent
    public static void onLivingAttack(final LivingAttackEvent event) {
        event.getEntity().getCapability(Capabilities.PLAYER_INVULNERABLE_CAPABILITY).ifPresent(inv -> {
            if (inv.isInvulnerable()) {
                event.setCanceled(true);
            }
        });
    }
}
