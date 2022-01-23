package heckerpowered.ultimatetech.common.item.armor;

import heckerpowered.ultimatetech.UltimateTech;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class CoalArmorItem extends ArmorItem {

    public CoalArmorItem(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return UltimateTech.ARMOR_PATH + (slot == EquipmentSlot.LEGS ? "coal_layer_2.png" : "coal_layer_1.png");
    }
}
