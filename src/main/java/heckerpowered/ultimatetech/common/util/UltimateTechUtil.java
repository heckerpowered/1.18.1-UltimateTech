package heckerpowered.ultimatetech.common.util;

import heckerpowered.ultimatetech.common.UltimateTechGroup;
import net.minecraft.world.item.Item.Properties;

public final class UltimateTechUtil {
    private UltimateTechUtil() {
    }

    public static Properties newProperties() {
        return new Properties().tab(UltimateTechGroup.getInstance());
    }
}
