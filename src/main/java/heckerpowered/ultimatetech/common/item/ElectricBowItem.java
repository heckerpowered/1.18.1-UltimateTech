package heckerpowered.ultimatetech.common.item;

import heckerpowered.ultimatetech.common.UltimateTechGroup;
import heckerpowered.ultimatetech.common.entity.projectile.SuperArrow;
import heckerpowered.ultimatetech.common.registries.UltimateTechEntity;
import heckerpowered.ultimatetech.common.util.concurrent.ScheduledUtil;
import heckerpowered.ultimatetech.common.util.concurrent.cancellation.ConditionCancellation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public final class ElectricBowItem extends EnergizedItem {

    public ElectricBowItem() {
        super(new Properties().stacksTo(1).tab(UltimateTechGroup.getInstance()), 1200000);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        p_41433_.startUsingItem(p_41434_);
        return InteractionResultHolder.pass(p_41433_.getItemInHand(p_41434_));
    }

    @Override
    public void onUseTick(Level p_41428_, LivingEntity p_41429_, ItemStack p_41430_, int p_41431_) {
        super.onUseTick(p_41428_, p_41429_, p_41430_, p_41431_);
        if (p_41428_.isClientSide) {
            return;
        }

        if (p_41431_ % 10 == 0 && consumeEnergy(p_41430_, 1200)) {
            ScheduledUtil.addTask(() -> {
                var power = 1.0F;
                var arrow = new SuperArrow(UltimateTechEntity.SUPER_ARROW.get(), p_41429_, p_41428_);
                arrow.shootFromRotation(p_41429_, p_41429_.getXRot(), p_41429_.getYRot(), 0.0F, power * 3.0F, 1.0F);
                arrow.pickup = Pickup.CREATIVE_ONLY;
                arrow.setCritArrow(true);
                arrow.setXRot(p_41429_.getXRot());
                arrow.setYRot(p_41429_.getYRot());
                p_41428_.addFreshEntity(arrow);

                p_41428_.playSound((Player) null, p_41429_.getX(), p_41429_.getY(), p_41429_.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            }).cancel(ConditionCancellation.afterTicks(12));
        }
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }
}
