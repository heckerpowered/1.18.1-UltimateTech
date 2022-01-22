package heckerpowered.ultimatetech.common.capabilities.invulnerable;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class InvulnerableHandler implements IInvulnerable, ICapabilitySerializable<CompoundTag> {
    private final LazyOptional<IInvulnerable> holder = LazyOptional.of(() -> this);
    private int invulnerable;

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return Capabilities.PLAYER_INVULNERABLE_CAPABILITY.orEmpty(cap, holder);
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();
        tag.putInt("Invulnerable", invulnerable);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        invulnerable = nbt.getInt("Invulnerable");
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        if (invulnerable) {
            this.invulnerable++;
        } else {
            this.invulnerable--;
        }
    }

    @Override
    public boolean isInvulnerable() {
        return invulnerable > 0;
    }

}
