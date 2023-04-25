package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.MonsterMover;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import com.codecool.dungeoncrawl.ui.keyeventhandler.RandomMover;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Set;

public class UI {
    private final int CANVAS_WIDTH = 1100;
    private final int CANVAS_HEIGHT = 900;
    private Canvas canvas;
    private GraphicsContext context;

    private MainStage mainStage;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;


    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        /*this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);*/
        this.canvas = new Canvas(
                CANVAS_WIDTH,
                CANVAS_HEIGHT);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic.getMap());
        }
        RandomMover randomMover = new RandomMover();

        MonsterMover monsterMover = new MonsterMover(randomMover);
        monsterMover.moveWizards(logic.getMap());
        monsterMover.moveGhosts(logic.getMap());
        monsterMover.moveBats(logic.getMap());

        refresh();
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int playerX = logic.getMap().getPlayer().getX();
        int playerY = logic.getMap().getPlayer().getY();
        int tilesInCanvasX = (int) Math.ceil(canvas.getWidth() / Tiles.TILE_WIDTH);
        int tilesInCanvasY = (int) Math.ceil(canvas.getWidth() / Tiles.TILE_WIDTH);
        GameMap centralizedGameMap = MapLoader.generateCentralizedSubMap(
                logic.getMap(), tilesInCanvasX, tilesInCanvasY, playerX, playerY
        );

        for (int x = 0; x < centralizedGameMap.getWidth(); x++) {
            for (int y = 0; y < centralizedGameMap.getHeight(); y++) {
                Cell cell = centralizedGameMap.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);

                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        mainStage.setHealthLabelText(logic.getPlayerHealth());

        Map<Item, Integer> playerInventory = logic.getMap().getPlayer().getInventory();
        String inventoryValueFiled = "";
        for (Item item : playerInventory.keySet()) {
            inventoryValueFiled += "  " + item.getTileName() + ": " + playerInventory.get(item) + "\n";
        }
        mainStage.setInventoryValueText(inventoryValueFiled);
    }
}
