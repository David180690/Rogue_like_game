package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int attackStrength = 0;
    protected int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public abstract void attack(Actor target);

    public void move(int dx, int dy) {
        if (health <= 0) {
            if (this.getClass() == Player.class) {
                die();
            }
            return;
        }

        Cell nextCell = cell.getNeighbor(dx, dy);

        if (nextCell.getActor() == null && nextCell.getType() != CellType.WALL && nextCell.getType()!=CellType.CLOSED_DOOR ) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        if (cell.getType() == CellType.FIRE) {
            cell.getActor().setHealth(cell.getActor().getHealth() - 1);
        }

    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public abstract void die() ;


}
