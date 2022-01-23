package heckerpowered.ultimatetech.common.capabilities.energy;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

/**
 * <strong>WARNIG:</strong> Do not use ICapabilitySerializable<?> on ItemStack
 * <p>
 * YOU WILL REGRET IT!
 */
public class EnergyHandler implements IEnergy, ICapabilityProvider {
    private final LazyOptional<IEnergy> holder = LazyOptional.of(() -> this);
    private ItemStack stack;

    public EnergyHandler(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public double getEnergy() {
        return stack.getOrCreateTag().getDouble("UltimateTechEnergy");
    }

    @Override
    public double getMaxEnergy() {
        return stack.getOrCreateTag().getDouble("UltimateTechMaxEnergy");
    }

    @Override
    public void setEnergy(double energy) {
        stack.getOrCreateTag().putDouble("UltimateTechEnergy", energy);
    }

    @Override
    public void setMaxEnergy(double energy) {
        stack.getOrCreateTag().putDouble("UltimateTechMaxEnergy", energy);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return holder.cast();
    }
}
