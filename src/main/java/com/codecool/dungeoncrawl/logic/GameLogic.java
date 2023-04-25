package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        System.out.println("map loaded");
    }


    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }


    public GameMap getMap() {
        return map;
    }
    public void setMap(GameMap map) {
         this.map=map;
    }
    public void initMap(){
        MapLoader.loadMap(MapLoader.getNextMap());

    }
}

