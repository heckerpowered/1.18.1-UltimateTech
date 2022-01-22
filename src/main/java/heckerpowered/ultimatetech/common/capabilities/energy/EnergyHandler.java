package heckerpowered.ultimatetech.common.capabilities.energy;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class EnergyHandler implements IEnergy, ICapabilitySerializable<CompoundTag> {
    private final LazyOptional<IEnergy> holder = LazyOptional.of(() -> this);
    private double energy;
    private double maxEnergy;

    public EnergyHandler() {
        super();
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public double getMaxEnergy() {
        return maxEnergy;
    }

    @Override
    public void setEnergy(double energy) {
        this.energy = energy;
        if (this.energy > maxEnergy) {
            this.energy = maxEnergy;
        }
    }

    @Override
    public void setMaxEnergy(double energy) {
        this.maxEnergy = energy;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return Capabilities.ITEM_ENERGY_CAPABILITY.orEmpty(cap, holder);
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();
        tag.putDouble("Energy", getEnergy());
        tag.putDouble("MaxEnergy", getMaxEnergy());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setEnergy(nbt.getDouble("Energy"));
        setMaxEnergy(nbt.getDouble("MaxEnergy"));
    }
}
