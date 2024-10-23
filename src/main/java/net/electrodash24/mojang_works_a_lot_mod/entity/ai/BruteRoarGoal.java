package net.electrodash24.mojang_works_a_lot_mod.entity.ai;

import net.electrodash24.mojang_works_a_lot_mod.entity.custom.BruteEntity;
import net.minecraft.world.entity.ai.goal.Goal;


public class BruteRoarGoal extends Goal {
    private final BruteEntity entity;
    private int roarCooldown = 200;

    public BruteRoarGoal(BruteEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return entity.isAggressive() && roarCooldown <= 0 && !entity.isRoaring();
    }

    @Override
    public void start() {
        entity.setRoaring(true);
        roarCooldown = 200;
    }

    @Override
    public boolean canContinueToUse() {
        return entity.isRoaring();
    }

    @Override
    public void stop() {
        entity.setRoaring(false);
    }

    public void tickCooldown() {
        if (roarCooldown > 0) {
            roarCooldown--;
        }
    }
}

