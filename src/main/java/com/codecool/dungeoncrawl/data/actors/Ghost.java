package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Ghost extends Monster{
    public Ghost(Cell cell) {
        super(cell);
        attackStrength = 3;
        health = 20;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    public void followPlayer(GameMap map, Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int ghostX = getX();
        int ghostY = getY();

        int dx = 0;
        int dy = 0;
        if (playerX != ghostX) {
            dx = playerX < ghostX ? -1 : 1;
        }
        if (playerY != ghostY) {
            dy = playerY < ghostY ? -1 : 1;
        }

        move(dx, dy);
    }
}
