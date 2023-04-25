package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.ui.keyeventhandler.RandomMover;

import java.util.Random;

public class Bat extends Monster {


    public Bat(Cell cell) {
        super(cell);
        attackStrength = 1;
        health = 8;

    }

    @Override
    public String getTileName() {
        return "bat";
    }


    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (nextCell.getActor() == null && nextCell.getType() != CellType.WALL && nextCell.getType() != CellType.EMPTY
                && nextCell.getType() != CellType.CLOSED_DOOR) {
            cell.setBat(null);
            nextCell.setBat(this);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }


    public void moveRandomly(RandomMover randomMover) {
        if (health <= 0) {
            return;
        }

        int dx = randomMover.getRandomCoordinate();
        int dy = randomMover.getRandomCoordinate();
        move(dx, dy);
    }
}

