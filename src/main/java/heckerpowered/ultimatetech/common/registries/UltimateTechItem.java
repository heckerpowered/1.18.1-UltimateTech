package heckerpowered.ultimatetech.common.registries;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.common.UltimateTechGroup;
import heckerpowered.ultimatetech.common.item.BlinkItem;
import heckerpowered.ultimatetech.common.item.ElectricBowItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class UltimateTechItem {
        public static final DeferredRegister<Item> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,
                        UltimateTech.MODID);

        public static final RegistryObject<BlinkItem> ITEM_BLINK = DEFERRED_REGISTER.register("blink", BlinkItem::new);
        public static final RegistryObject<ElectricBowItem> ITEM_ELECTRIC_BOW = DEFERRED_REGISTER
                        .register("electric_bow", ElectricBowItem::new);
        public static final RegistryObject<BlockItem> ITEM_ENERGY_MATRIX_BLOCK = DEFERRED_REGISTER.register(
                        "energy_matrix_block",
                        () -> new BlockItem(UltimateTechBlock.ENERGY_MATRIX_BLOCK.get(),
                                        new Properties().tab(UltimateTechGroup.getInstance())));
}
