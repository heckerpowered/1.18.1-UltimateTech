package heckerpowered.ultimatetech.common.capabilities.phase;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PhaseHandler implements IPhase, ICapabilitySerializable<CompoundTag> {
    private final LazyOptional<IPhase> holder = LazyOptional.of(() -> this);
    private int phase;

    @Override
    public void setPhase(boolean phase) {
        if (phase) {
            this.phase++;
        } else {
            this.phase--;
        }
    }

    @Override
    public boolean isPhase() {
        return phase > 0;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return Capabilities.PLAYER_PHASE_CAPABILITY.orEmpty(cap, holder);
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();
        tag.putInt("Phase", phase);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        phase = nbt.getInt("Phase");
    }

    @Override
    public void clearPhase() {
        phase = 0;
    }
}
