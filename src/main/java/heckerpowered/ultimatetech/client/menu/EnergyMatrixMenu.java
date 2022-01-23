package heckerpowered.ultimatetech.client.menu;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import heckerpowered.ultimatetech.common.item.EnergizedItem;
import heckerpowered.ultimatetech.common.registries.UltimateTechMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public final class EnergyMatrixMenu extends ItemCombinerMenu {
    private int count;

    public EnergyMatrixMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public EnergyMatrixMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(UltimateTechMenu.ENERGY_MATRIX_MENU_TYPE.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(Player p_39798_, boolean p_39799_) {
        return true;
    }

    @Override
    protected void onTake(Player p_150601_, ItemStack p_150602_) {
        inputSlots.setItem(0, ItemStack.EMPTY);
        var stack = inputSlots.getItem(1);
        stack.shrink(count);
        inputSlots.setItem(1, stack);
    }

    @Override
    protected boolean isValidBlock(BlockState p_39788_) {
        return true;
    }

    @Override
    public void createResult() {
        var input = this.inputSlots.getItem(0);
        var battery = this.inputSlots.getItem(1);
        if (input.getItem() instanceof EnergizedItem && (battery.is(Items.REDSTONE)
                || battery.is(Items.REDSTONE_BLOCK))) {
            var result = input.copy();
            result.getCapability(Capabilities.ITEM_ENERGY_CAPABILITY).ifPresent(energy -> {
                if (energy.getMaxEnergy() == energy.getEnergy()) {
                    resultSlots.setItem(0, ItemStack.EMPTY);
                    return;
                }

                int charge = battery.is(Items.REDSTONE) ? 8000 : 72000;
                count = (int) (energy.getNeeded() / charge) + 1;
                var maxCount = battery.getCount();
                if (count > maxCount) {
                    count = maxCount;
                }

                energy.increaseEnergy(charge * count);
                resultSlots.setItem(0, result);
            });
        } else {
            resultSlots.setItem(0, ItemStack.EMPTY);
        }
    }
}
