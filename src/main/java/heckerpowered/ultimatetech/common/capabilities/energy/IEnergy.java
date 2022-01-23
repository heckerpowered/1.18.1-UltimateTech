package heckerpowered.ultimatetech.common.capabilities.energy;

public interface IEnergy {
    public double getEnergy();

    public double getMaxEnergy();

    public void setEnergy(double energy);

    public void setMaxEnergy(double energy);

    public boolean isDirty();

    public void markDirty();

    default public double getNeeded() {
        return getMaxEnergy() - getEnergy();
    }

    default public boolean consumeEnergy(double energy) {
        var currentEnergy = getEnergy();
        if (currentEnergy >= energy) {
            setEnergy(currentEnergy - energy);
            return true;
        }

        return false;
    }

    default public void increaseEnergy(double energy) {
        setEnergy(getEnergy() + energy);
    }
}
