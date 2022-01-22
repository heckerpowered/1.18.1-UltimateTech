package heckerpowered.ultimatetech.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import heckerpowered.ultimatetech.common.entity.projectile.SuperArrow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public final class SuperArrowRenderer extends ArrowRenderer<SuperArrow> {
    public static final ResourceLocation NORMAL_ARROW_LOCATION = new ResourceLocation(
            "textures/entity/projectiles/arrow.png");
    public static final ResourceLocation TIPPED_ARROW_LOCATION = new ResourceLocation(
            "textures/entity/projectiles/tipped_arrow.png");

    public SuperArrowRenderer(Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public void render(SuperArrow p_113839_, float p_113840_, float p_113841_, PoseStack p_113842_,
            MultiBufferSource p_113843_, int p_113844_) {
        super.render(p_113839_, p_113840_, p_113841_, p_113842_, p_113843_, p_113844_);
    }

    @Override
    public ResourceLocation getTextureLocation(SuperArrow p_114482_) {
        return NORMAL_ARROW_LOCATION;
    }

}
