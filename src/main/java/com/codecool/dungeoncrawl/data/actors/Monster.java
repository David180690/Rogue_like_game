package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class Monster extends Actor{
    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public void attack(Actor target) {
        target.setHealth(target.getHealth() - attackStrength);
        if (target.getHealth() <= 0) {
            target.die();
        }
    }

    @Override
    public void die() {
        getCell().setActor(null);
    }
}
