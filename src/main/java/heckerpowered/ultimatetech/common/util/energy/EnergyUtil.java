package heckerpowered.ultimatetech.common.util.energy;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class EnergyUtil {
    private static final String ENERGY_STRING = "UltimateTechEnergy";
    private static final String MAX_ENERGY_STRING = "UltimateTechMaxEnergy";

    public static final double getEnergy(CompoundTag tag) {
        return tag.getDouble(ENERGY_STRING);
    }

    public static final void setEnergy(CompoundTag tag, double energy) {
        tag.putDouble(ENERGY_STRING, energy);
    }

    public static final double getMaxEnergy(CompoundTag tag) {
        return tag.getDouble(MAX_ENERGY_STRING);
    }

    public static final double getNeeded(CompoundTag tag) {
        return getMaxEnergy(tag) - getEnergy(tag);
    }
}
