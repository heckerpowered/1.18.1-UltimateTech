package heckerpowered.ultimatetech.client.screen.menu;

import heckerpowered.ultimatetech.UltimateTech;
import heckerpowered.ultimatetech.client.menu.EnergyMatrixMenu;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public final class EnergyMatrixMenuScreen extends ItemCombinerScreen<EnergyMatrixMenu> {
    private static final ResourceLocation LOCATION = new ResourceLocation(UltimateTech.MODID,
            "textures/gui/container/charge.png");

    public EnergyMatrixMenuScreen(EnergyMatrixMenu p_98901_, Inventory p_98902_, Component p_98903_) {
        super(p_98901_, p_98902_, p_98903_, LOCATION);
    }

}
