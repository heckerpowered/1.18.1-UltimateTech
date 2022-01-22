package heckerpowered.ultimatetech.common.registries;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.client.menu.EnergyMatrixMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class UltimateTechMenu {
        private UltimateTechMenu() {
        }

        public static final DeferredRegister<MenuType<?>> DEFERRED_REGISTER = DeferredRegister.create(
                        ForgeRegistries.CONTAINERS,
                        UltimateTech.MODID);

        public static final RegistryObject<MenuType<EnergyMatrixMenu>> ENERGY_MATRIX_MENU_TYPE = DEFERRED_REGISTER
                        .register("energy_matrix_menu", () -> new MenuType<>(EnergyMatrixMenu::new));
}
