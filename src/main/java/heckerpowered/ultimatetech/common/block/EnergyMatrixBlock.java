package heckerpowered.ultimatetech.common.block;

import heckerpowered.ultimatetech.client.menu.EnergyMatrixMenu;
import heckerpowered.ultimatetech.common.block.entity.EnergyMatrixBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class EnergyMatrixBlock extends BaseEntityBlock {
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container.charge");

    public EnergyMatrixBlock() {
        super(Properties.of(Material.METAL));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new EnergyMatrixBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState p_49234_, Level p_49235_, BlockPos p_49236_) {
        return new SimpleMenuProvider((p_48785_, p_48786_, p_48787_) -> {
            return new EnergyMatrixMenu(p_48785_, p_48786_, ContainerLevelAccess.create(p_49235_, p_49236_));
        }, CONTAINER_TITLE);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_,
            InteractionHand p_60507_, BlockHitResult p_60508_) {
        if (p_60504_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            p_60506_.openMenu(p_60503_.getMenuProvider(p_60504_, p_60505_));
            return InteractionResult.CONSUME;
        }
    }
}
