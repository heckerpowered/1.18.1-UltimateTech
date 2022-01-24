package heckerpowered.ultimatetech.common.registries;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.common.entity.projectile.SuperArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class UltimateTechEntity {
        private UltimateTechEntity() {
        }

        public static final DeferredRegister<EntityType<?>> DEFERRED_REGISTER = DeferredRegister.create(
                        ForgeRegistries.ENTITIES,
                        UltimateTech.MODID);

        public static final RegistryObject<EntityType<SuperArrow>> SUPER_ARROW = DEFERRED_REGISTER
                        .register("super_arrow",
                                        () -> EntityType.Builder.<SuperArrow>of(SuperArrow::new, MobCategory.MISC)
                                                        .sized(0.5F, 0.5F)
                                                        .clientTrackingRange(4).updateInterval(20).noSave()
                                                        .build("super_arrow"));
}
