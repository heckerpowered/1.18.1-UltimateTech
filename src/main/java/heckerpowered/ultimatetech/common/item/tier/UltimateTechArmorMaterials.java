package heckerpowered.ultimatetech.common.item.tier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;

public enum UltimateTechArmorMaterials implements ArmorMaterial {
    COAL("coal", 9, new int[] { 1, 3, 4, 2 }, 10, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            () -> Ingredient.of(Items.COAL)),
    REDSTONE("redstone", 13, new int[] { 1, 4, 5, 2 }, 12, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            () -> Ingredient.of(Items.REDSTONE));

    private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredient;

    private UltimateTechArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections,
            int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance,
            Lazy<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot p_40410_) {
        return HEALTH_PER_SLOT[p_40410_.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot p_40411_) {
        return slotProtections[p_40411_.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
