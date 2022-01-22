package heckerpowered.ultimatetech.common.capabilities;

import heckerpowered.ultimatetech.common.capabilities.energy.IEnergy;
import heckerpowered.ultimatetech.common.capabilities.invulnerable.IInvulnerable;
import heckerpowered.ultimatetech.common.capabilities.phase.IPhase;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public final class Capabilities {
    private Capabilities() {
    }

    public static final Capability<IEnergy> ITEM_ENERGY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<IPhase> PLAYER_PHASE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<IInvulnerable> PLAYER_INVULNERABLE_CAPABILITY = CapabilityManager
            .get(new CapabilityToken<>() {
            });

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IEnergy.class);
        event.register(IPhase.class);
        event.register(IInvulnerable.class);
    }
}
