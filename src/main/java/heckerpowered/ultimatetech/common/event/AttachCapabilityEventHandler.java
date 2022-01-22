package heckerpowered.ultimatetech.common.event;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.common.capabilities.phase.PhaseHandler;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class AttachCapabilityEventHandler {
    private AttachCapabilityEventHandler() {
    }

    @SubscribeEvent
    public static final void onAttachCapability(final AttachCapabilitiesEvent<Entity> event) {
        event.addCapability(UltimateTech.rl("phase"), new PhaseHandler());
    }
}
