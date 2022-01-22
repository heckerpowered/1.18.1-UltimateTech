package heckerpowered.ultimatetech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import heckerpowered.ultimatetech.client.render.SuperArrowRenderer;
import heckerpowered.ultimatetech.client.screen.menu.EnergyMatrixMenuScreen;
import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import heckerpowered.ultimatetech.common.event.AttachCapabilityEventHandler;
import heckerpowered.ultimatetech.common.event.CommonEventHandler;
import heckerpowered.ultimatetech.common.event.CommonTickEventHandler;
import heckerpowered.ultimatetech.common.registries.UltimateTechBlock;
import heckerpowered.ultimatetech.common.registries.UltimateTechBlockEntity;
import heckerpowered.ultimatetech.common.registries.UltimateTechEntity;
import heckerpowered.ultimatetech.common.registries.UltimateTechItem;
import heckerpowered.ultimatetech.common.registries.UltimateTechMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UltimateTech.MODID)
public final class UltimateTech {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "ultimatetech";

    public UltimateTech() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        UltimateTechItem.DEFERRED_REGISTER.register(modEventBus);
        UltimateTechBlock.DEFERRED_REGISTER.register(modEventBus);
        UltimateTechBlockEntity.DEFERRED_REGISTER.register(modEventBus);
        UltimateTechMenu.DEFERRED_REGISTER.register(modEventBus);
        UltimateTechEntity.DEFERRED_REGISTER.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(CommonTickEventHandler.class);
        MinecraftForge.EVENT_BUS.register(CommonEventHandler.class);
        MinecraftForge.EVENT_BUS.register(AttachCapabilityEventHandler.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(UltimateTechMenu.ENERGY_MATRIX_MENU_TYPE.get(), EnergyMatrixMenuScreen::new);
        EntityRenderers.register(UltimateTechEntity.SUPER_ARROW.get(), SuperArrowRenderer::new);
    }

    public static final Logger getLogger() {
        return LOGGER;
    }

    public static final ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void registerCapabilities(final RegisterCapabilitiesEvent event) {
        Capabilities.register(event);
    }
}
