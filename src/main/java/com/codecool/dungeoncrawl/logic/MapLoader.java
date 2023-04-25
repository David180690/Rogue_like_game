package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.io.InputStream;
import java.util.*;

public class MapLoader {

    private static int counter = -1;

    public static GameLogic gameLogic;
    public static String getNextMap() {
        String pathMap1 = "/map.txt";
        String pathMap2 = "/map2.txt";
        String pathMap3 = "/map-big.txt";

        ArrayList<String> mapList = new ArrayList<>(Arrays.asList(pathMap1, pathMap2,pathMap3));
        System.out.println("counter" + counter);
        ++counter;

        return mapList.get(counter);
    };


    public static void loadMap(String pathOfMap) {
        loadMap(pathOfMap, new HashMap<>(), 13);
    }

    public static void loadMap(String pathOfMap, Map<Item, Integer> inventory, int playerHealth) {
        InputStream is = MapLoader.class.getResourceAsStream(pathOfMap);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            Player player = new Player(cell);
                            player.setInventory(inventory);
                            player.setHealth(playerHealth);
                            map.setPlayer(player);
                            break;
                        case '|':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            Ghost ghost = new Ghost(cell);
                            if (map != null) {
                                map.getGhosts().add(ghost);
                            }
                            break;
                        case 'd':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            Bat bat = new Bat(cell);
                            if (map != null) {
                                map.getBats().add(bat);
                            }
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            Wizard wizard = new Wizard(cell);
                            if (map != null) {
                                map.getWizards().add(wizard);
                            }
                            break;
                        case 'p':
                            cell.setType(CellType.PORTAL);
                            break;
                        case '^':
                            cell.setType(CellType.FIRE);
                            break;
                        case '¤':
                            cell.setType(CellType.FOOD);
                            break;

                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        gameLogic.setMap(map);
    }

    public static GameMap generateCentralizedSubMap(GameMap originalMap, int tilesInCanvasX,
                                                    int tilesInCanvasY, int playerX, int playerY)
    {
        GameMap centralizedMap = new GameMap(tilesInCanvasX, tilesInCanvasY, CellType.EMPTY);

        for (int x = 0; x < centralizedMap.getWidth(); x++) {
            for (int y = 0; y < centralizedMap.getHeight(); y++) {
                int originalCellX = playerX - (tilesInCanvasX / 2) + x;
                int originalCellY = playerY - (tilesInCanvasY / 2) + y;

                Cell generatedCell = centralizedMap.getCell(x, y);
                Cell originalCell = new Cell(centralizedMap, x, y, CellType.EMPTY);

                boolean cellFitsInOriginalMapBounds = originalCellX >= 0 && originalCellY >= 0
                    && originalCellX < originalMap.getWidth() && originalCellY < originalMap.getHeight();

                if (cellFitsInOriginalMapBounds)
                    originalCell = originalMap.getCell(originalCellX, originalCellY);

                generatedCell.setType(originalCell.getType());
                generatedCell.setItem(originalCell.getItem());
                generatedCell.setActor(originalCell.getActor());
            }
        }
        return centralizedMap;
    }
}
