package heckerpowered.ultimatetech.common.registries;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.common.UltimateTechGroup;
import heckerpowered.ultimatetech.common.item.BlinkItem;
import heckerpowered.ultimatetech.common.item.ElectricBowItem;
import heckerpowered.ultimatetech.common.item.tier.UltimateTechTier;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class UltimateTechItem {
        public static final DeferredRegister<Item> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,
                        UltimateTech.MODID);

        // #region Energized Item
        public static final RegistryObject<BlinkItem> ITEM_BLINK = DEFERRED_REGISTER.register("blink", BlinkItem::new);
        public static final RegistryObject<ElectricBowItem> ITEM_ELECTRIC_BOW = DEFERRED_REGISTER
                        .register("electric_bow", ElectricBowItem::new);
        public static final RegistryObject<BlockItem> ITEM_ENERGY_MATRIX_BLOCK = DEFERRED_REGISTER.register(
                        "energy_matrix_block",
                        () -> new BlockItem(UltimateTechBlock.ENERGY_MATRIX_BLOCK.get(),
                                        newProperties()));
        // #endregion

        public static final RegistryObject<SwordItem> ITEM_COAL_SWORD = DEFERRED_REGISTER.register("coal_sword",
                        () -> new SwordItem(UltimateTechTier.COAL, 3, -2.4F, newProperties()));
        public static final RegistryObject<PickaxeItem> ITEM_COAL_PICKAXE = DEFERRED_REGISTER.register("coal_pickaxe",
                        () -> new PickaxeItem(UltimateTechTier.COAL, 1, -2.8F, newProperties()));
        public static final RegistryObject<AxeItem> ITEM_COAL_AXE = DEFERRED_REGISTER.register("coal_axe",
                        () -> new AxeItem(UltimateTechTier.COAL, 6.5F, -2.9F, newProperties()));
        public static final RegistryObject<ShovelItem> ITEM_COAL_SHOVEL = DEFERRED_REGISTER.register("coal_shovel",
                        () -> new ShovelItem(UltimateTechTier.COAL, 1.5F, -3.0F, newProperties()));
        public static final RegistryObject<HoeItem> ITEM_COAL_HOE = DEFERRED_REGISTER.register("coal_hoe",
                        () -> new HoeItem(UltimateTechTier.COAL, -1, -1.5F, newProperties()));

        public static Properties newProperties() {
                return new Properties().tab(UltimateTechGroup.getInstance());
        }
}
