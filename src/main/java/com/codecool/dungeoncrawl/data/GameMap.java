package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Bat;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Wizard;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private final List<Wizard> wizards = new ArrayList<>();
    private final List<Ghost> ghosts = new ArrayList<>();
    private final List<Bat> bats = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;

        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Bat> getBats() {
        return bats;
    }

    public List<Wizard> getWizards() {
        return wizards;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }
}
