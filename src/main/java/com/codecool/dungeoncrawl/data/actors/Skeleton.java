package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

public class Skeleton extends Monster {
    private String tile;
    public Skeleton(Cell cell) {
        super(cell);
        attackStrength = 2;
        health = 15;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    @Override
    public void die (){
        getCell().setActor(null);
        getCell().setType(CellType.FOOD);
        System.out.println("skeleton is dead,bon appetit");
    }
}
