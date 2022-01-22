package heckerpowered.ultimatetech.common.registries;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.common.block.EnergyMatrixBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class UltimateTechBlock {
    private UltimateTechBlock() {
    }

    public static final DeferredRegister<Block> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS,
            UltimateTech.MODID);

    public static final RegistryObject<EnergyMatrixBlock> ENERGY_MATRIX_BLOCK = DEFERRED_REGISTER
            .register("energy_matrix_block", EnergyMatrixBlock::new);
}
