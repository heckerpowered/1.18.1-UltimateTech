package heckerpowered.ultimatetech.common;

import heckerpowered.ultimatetech.common.registries.UltimateTechItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class UltimateTechGroup extends CreativeModeTab {

    public UltimateTechGroup() {
        super("ultimate_tech");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(UltimateTechItem.ITEM_BLINK.get());
    }

    public static UltimateTechGroup getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UltimateTechGroup INSTANCE = new UltimateTechGroup();
    }
}
