package net.electrodash24.mojang_works_a_lot_mod.entity.custom;

import net.electrodash24.mojang_works_a_lot_mod.entity.ai.BruteAttackGoal;
import net.electrodash24.mojang_works_a_lot_mod.entity.ai.BruteRoarGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;

public class BruteEntity extends AbstractIllager {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(BruteEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> AGGRESIVE =
            SynchedEntityData.defineId(BruteEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> ROARING =
            SynchedEntityData.defineId(BruteEntity.class, EntityDataSerializers.BOOLEAN);

    public BruteEntity(EntityType<? extends AbstractIllager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public final AnimationState roarAnimationState = new AnimationState();
    public int roarAnimationTimeout = 0;
    public final AnimationState watchAnimationState = new AnimationState();
    public int watchAnimationTimeout = this.random.nextInt(50)+500;

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        } else {
            setAggresive(this.getTarget() != null);

            for (Goal goal : this.goalSelector.getAvailableGoals()) {
                if (goal instanceof BruteRoarGoal) {
                    ((BruteRoarGoal) goal).tickCooldown();
                }
            }
        }
    }

    private void setupAnimationStates() {

        if (this.watchAnimationTimeout <= 0.0f && this.idleAnimationTimeout <= 0.0f) {
            this.watchAnimationTimeout = this.random.nextInt(50) + 500;
            this.watchAnimationState.start(this.tickCount);

        } else {
            --this.watchAnimationTimeout;
        }

        if (this.idleAnimationTimeout <= 0.0f) {
            this.idleAnimationTimeout = this.random.nextInt(10) + 60;
            this.idleAnimationState.start(this.tickCount);

        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 18;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }

        if (this.isRoaring() && roarAnimationTimeout <= 0) {
            roarAnimationTimeout = 80;
            roarAnimationState.start(this.tickCount);
        } else {
            --this.roarAnimationTimeout;
        }

        if (!this.isAttacking()) {
            roarAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 8f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAggresive(boolean hasTarget){
        this.entityData.set(AGGRESIVE, hasTarget);
    }

    public boolean isAggressive() {
        return this.entityData.get(AGGRESIVE);
    }

    public void setRoaring(boolean hasTarget){
        this.entityData.set(ROARING, hasTarget);
    }

    public boolean isRoaring() {
        return this.entityData.get(ROARING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(AGGRESIVE, false);
        this.entityData.define(ROARING, false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new BruteAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
        this.goalSelector.addGoal(4, new BruteRoarGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(Raider.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @Override
    public void applyRaidBuffs(int wave, boolean p_37844_) {
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.PILLAGER_CELEBRATE;
    }
}