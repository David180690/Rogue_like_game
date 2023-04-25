package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Sword extends Item{


    public Sword(Cell cell) {
        super(cell);
        damage = 10;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
