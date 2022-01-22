package heckerpowered.ultimatetech.common.block.entity;

import heckerpowered.ultimatetech.common.registries.UltimateTechBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyMatrixBlockEntity extends BaseContainerBlockEntity {
    private final NonNullList<ItemStack> stacks = NonNullList.withSize(2, ItemStack.EMPTY);

    public EnergyMatrixBlockEntity(BlockPos p_155077_, BlockState p_155078_) {
        this(UltimateTechBlockEntity.ENERGY_MATRIX_BLOCK_ENTITY_TYPE.get(), p_155077_, p_155078_);
    }

    public EnergyMatrixBlockEntity(BlockEntityType<?> p_155076_, BlockPos p_155077_, BlockState p_155078_) {
        super(p_155076_, p_155077_, p_155078_);
    }

    @Override
    public int getContainerSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    @Override
    public ItemStack getItem(int p_18941_) {
        return stacks.get(p_18941_);
    }

    @Override
    public ItemStack removeItem(int p_18942_, int p_18943_) {
        var stack = stacks.get(p_18942_);
        stack.shrink(p_18943_);
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_18951_) {
        return stacks.remove(p_18951_);
    }

    @Override
    public void setItem(int p_18944_, ItemStack p_18945_) {
        stacks.set(p_18944_, p_18945_);
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return false;
    }

    @Override
    public void clearContent() {
        stacks.clear();
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return null;
    }

}
