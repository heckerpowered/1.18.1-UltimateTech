package heckerpowered.ultimatetech.common.util;

import net.minecraft.world.entity.Entity;

public final class UltimateTechUtil {
    private UltimateTechUtil() {
    }

    public static final boolean isInvulnerable(Entity entity) {
        return entity.getPersistentData().getInt("UltimateTechInvulnerableCount") > 0;
    }

    public static final void setInvulnerable(Entity entity, boolean invulnerable) {
        var persistentData = entity.getPersistentData();
        var invulnerableCount = persistentData.getInt("UltimateTechInvulnerableCount");
        persistentData.putInt("UltimateTechInvulnerableCount",
                invulnerable ? ++invulnerableCount : --invulnerableCount);
    }
}
