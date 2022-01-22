package heckerpowered.ultimatetech.common.registries;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.common.block.entity.EnergyMatrixBlockEntity;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class UltimateTechBlockEntity {
    private UltimateTechBlockEntity() {
    }

    public static final DeferredRegister<BlockEntityType<?>> DEFERRED_REGISTER = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITIES,
            UltimateTech.MODID);

    public static final RegistryObject<BlockEntityType<EnergyMatrixBlockEntity>> ENERGY_MATRIX_BLOCK_ENTITY_TYPE = DEFERRED_REGISTER
            .register("energy_matrix_block_entity",
                    () -> BlockEntityType.Builder.<EnergyMatrixBlockEntity>of(EnergyMatrixBlockEntity::new,
                            UltimateTechBlock.ENERGY_MATRIX_BLOCK.get()).build(
                                    Util.fetchChoiceType(References.BLOCK_ENTITY, "energy_matrix_block_entity")));
}
