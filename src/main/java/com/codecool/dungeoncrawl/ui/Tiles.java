package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(20, 9));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword",new Tile(2,29));
        tileMap.put("key",new Tile(16,23));
        tileMap.put("player-death", new Tile(22, 23));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("opened-door",new Tile(6,9));
        tileMap.put("closed-door",new Tile(3,9));
        tileMap.put("bat", new Tile(26, 8));
        tileMap.put("portal", new Tile(2, 9));
        tileMap.put("fire", new Tile(15, 10));
        tileMap.put("food", new Tile(15, 28));
        tileMap.put("wizard", new Tile(24, 2));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
