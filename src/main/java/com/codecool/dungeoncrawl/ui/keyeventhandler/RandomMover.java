package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyEvent;

import java.util.Random;

public class RandomMover {
    public int getRandomCoordinate() {
        Random r = new Random();
        int low = -1;
        int high = 2;
        int result = r.nextInt(high - low) + low;
        return result;
    }
}
