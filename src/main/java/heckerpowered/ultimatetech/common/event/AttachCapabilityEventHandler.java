package heckerpowered.ultimatetech.common.event;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import heckerpowered.ultimatetech.common.capabilities.invulnerable.IInvulnerable;
import heckerpowered.ultimatetech.common.capabilities.invulnerable.InvulnerableHandler;
import heckerpowered.ultimatetech.common.capabilities.phase.IPhase;
import heckerpowered.ultimatetech.common.capabilities.phase.PhaseHandler;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class AttachCapabilityEventHandler {
    private AttachCapabilityEventHandler() {
    }

    @SubscribeEvent
    public static final void onAttachCapability(final AttachCapabilitiesEvent<Entity> event) {
        event.addCapability(UltimateTech.rl("phase"), new PhaseHandler());
        event.addCapability(UltimateTech.rl("invulnerable"), new InvulnerableHandler());
    }

    @SubscribeEvent
    public static final void onPlayerLoggedOut(final PlayerLoggedOutEvent event) {
        var player = event.getPlayer();
        player.getCapability(Capabilities.PLAYER_PHASE_CAPABILITY).ifPresent(IPhase::clearPhase);
        player.getCapability(Capabilities.PLAYER_INVULNERABLE_CAPABILITY).ifPresent(IInvulnerable::clearInvulnerable);
    }

    @SubscribeEvent
    public static final void onPlayerLoggedIn(final PlayerLoggedInEvent event) {
        var player = event.getPlayer();
        player.getCapability(Capabilities.PLAYER_PHASE_CAPABILITY).ifPresent(IPhase::clearPhase);
        player.getCapability(Capabilities.PLAYER_INVULNERABLE_CAPABILITY).ifPresent(IInvulnerable::clearInvulnerable);
    }
}
