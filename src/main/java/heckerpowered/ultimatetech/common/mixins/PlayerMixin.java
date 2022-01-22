package heckerpowered.ultimatetech.common.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public class PlayerMixin {
    private Player player = (Player) (Object) this;

    @Inject(method = "tick", at = @At(value = "FIELD", shift = Shift.AFTER))
    private void tick(CallbackInfo info) {
        // Phase walk
        player.getCapability(Capabilities.PLAYER_PHASE_CAPABILITY).ifPresent(p -> {
            if (p.isPhase()) {
                player.noPhysics = true;
            }
        });
    }
}
