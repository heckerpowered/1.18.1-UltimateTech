package heckerpowered.ultimatetech.common.item;

import java.util.List;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import heckerpowered.ultimatetech.common.capabilities.energy.EnergyHandler;
import heckerpowered.ultimatetech.common.util.energy.EnergyDisplay;
import heckerpowered.ultimatetech.common.util.math.ElectricUnit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class EnergizedItem extends Item {
    private double maxEnergy;

    public EnergizedItem(Properties p_41383_) {
        super(p_41383_);
    }

    public EnergizedItem(Properties p_41383_, double maxEnergy) {
        super(p_41383_);
        this.maxEnergy = maxEnergy;
    }

    @Override
    public int getBarWidth(ItemStack p_150900_) {
        var energy = p_150900_.getCapability(Capabilities.ITEM_ENERGY_CAPABILITY).resolve().get();
        return (int) (energy.getEnergy() / energy.getMaxEnergy() * 13.0);
    }

    @Override
    public boolean isBarVisible(ItemStack p_150899_) {
        return true;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public int getDamage(ItemStack stack) {
        var energy = stack.getCapability(Capabilities.ITEM_ENERGY_CAPABILITY);
        return (int) energy.resolve().get().getMaxEnergy();
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        var capability = new EnergyHandler();
        capability.setMaxEnergy(maxEnergy);
        return capability;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        var energy = p_41421_.getCapability(Capabilities.ITEM_ENERGY_CAPABILITY).resolve().get();
        p_41423_.add(EnergyDisplay.of(energy.getEnergy(), energy.getMaxEnergy(), ElectricUnit.JOULES));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

    public boolean consumeEnergy(ItemStack stack, double energy) {
        return stack.getCapability(Capabilities.ITEM_ENERGY_CAPABILITY).resolve().get().consumeEnergy(energy);
    }

    public void increaseEnergy(ItemStack stack, double energy) {
        stack.getCapability(Capabilities.ITEM_ENERGY_CAPABILITY).resolve().get().increaseEnergy(energy);
    }

    @Override
    public int getBarColor(ItemStack p_150901_) {
        return 3997338;
    }
}
