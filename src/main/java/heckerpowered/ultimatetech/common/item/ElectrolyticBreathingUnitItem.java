package heckerpowered.ultimatetech.common.item;

import heckerpowered.ultimatetech.common.registries.UltimateTechItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class ElectrolyticBreathingUnitItem extends EnergizedItem {

    public ElectrolyticBreathingUnitItem() {
        super(UltimateTechItem.newProperties(), 40000);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        if (p_41406_ instanceof Player) {
            var player = (Player) p_41406_;
            if (consumeEnergy(p_41404_, player.getMaxAirSupply() - player.getAirSupply())) {
                player.setAirSupply(player.getMaxAirSupply());
            }
        }
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
    }
}
