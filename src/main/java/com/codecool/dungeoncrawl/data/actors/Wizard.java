package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;

import java.util.Random;

public class Wizard extends Monster {
    public Wizard(Cell cell) {
        super(cell);
        attackStrength = 10;
        health = 1;
    }

    public void teleport(GameMap map) {
        if (health <= 0) {
            return;
        }

        Random random = new Random();
        Cell destinationCell = map.getCell(random.nextInt(map.getWidth()),
                random.nextInt(map.getHeight()));

        if (destinationCell.getType() == CellType.FLOOR && destinationCell.getActor() == null
                && destinationCell.getItem() == null)
        {
            cell.setActor(null);
            destinationCell.setActor(this);
            cell = destinationCell;
        }
    }

    @Override
    public String getTileName() {
        return "wizard";
    }
}
