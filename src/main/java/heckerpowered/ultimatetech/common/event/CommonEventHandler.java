package heckerpowered.ultimatetech.common.event;

import heckerpowered.ultimatetech.common.util.UltimateTechUtil;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class CommonEventHandler {
    private CommonEventHandler() {
    }

    @SubscribeEvent
    public static void onLivingAttack(final LivingAttackEvent event) {
        if (UltimateTechUtil.isInvulnerable(event.getEntity())) {
            event.setCanceled(true);
        }
    }
}
