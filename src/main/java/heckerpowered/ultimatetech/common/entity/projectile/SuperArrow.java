package heckerpowered.ultimatetech.common.entity.projectile;

import heckerpowered.ultimatetech.common.registries.UltimateTechItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class SuperArrow extends AbstractArrow {
    public SuperArrow(EntityType<? extends AbstractArrow> p_36717_, Level p_36719_) {
        super(p_36717_, p_36719_);
    }

    public SuperArrow(EntityType<? extends AbstractArrow> p_36717_, LivingEntity p_36718_, Level p_36719_) {
        super(p_36717_, p_36718_, p_36719_);
    }

    @Override
    public void tick() {
        if (tickCount > 200) {
            remove(RemovalReason.KILLED);
            return;
        }

        baseTick();
        var vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;
        for (int i = 0; i < 4; ++i) {
            this.level.addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double) i / 4.0D,
                    this.getY() + d6 * (double) i / 4.0D, this.getZ() + d1 * (double) i / 4.0D, -d5, -d6 + 0.2D, -d1);
        }

        var position = position();
        var forward = getForward().scale(10);
        setPos(position.add(forward));

        for (var e : level.getEntities(getOwner(), new AABB(position, position()))) {
            e.invulnerableTime = 0;
            var owner = getOwner();
            DamageSource damageSource = DamageSource.GENERIC;
            if (owner != null && owner instanceof LivingEntity) {
                damageSource = DamageSource.mobAttack((LivingEntity) owner);
            }

            e.hurt(damageSource, (float) getBaseDamage());
            setBaseDamage(1.0D);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(UltimateTechItem.ITEM_ELECTRIC_BOW.get());
    }
}
