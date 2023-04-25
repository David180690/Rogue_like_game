package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.MapLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Player extends Actor {
    private String tileName;
    private Map<Item, Integer> inventory = new HashMap<>();

    public Player(Cell cell) {
        super(cell);
        attackStrength = 5;
        health = 13;
        tileName = "player";
    }

    @Override
    public void attack(Actor target) {
        int hitPoints = attackStrength;
        for (Item item : inventory.keySet()) {
            hitPoints += item.getDamage();
        }
        target.setHealth(target.getHealth() - hitPoints);

        if (target.getHealth() <= 0) {
            target.die();
        } else {
            target.attack(this);
        }
    }


    @Override
    public void move(int dx, int dy) {
        if (tileName.equals("player-death")) {
            return;
        }

        super.move(dx, dy);
        Cell neighbor = getCell().getNeighbor(dx, dy);
        if (neighbor.getType() == CellType.CLOSED_DOOR) {
            for (Item item : inventory.keySet()) {
                if (item.getTileName().equals("key") && inventory.get(item) > 0) {
                    neighbor.setType(CellType.OPENED_DOOR);
                }
            }

        }

        if (getCell().getType() == CellType.PORTAL) {
            MapLoader.loadMap(MapLoader.getNextMap(), inventory, health);
            System.out.println("new map");

        }

        handleItem(getCell().getItem());
        handleEnemy(neighbor.getActor());
        handleFood(cell);
    }

    private void handleEnemy(Actor currentEnemy) {
        if (currentEnemy != null) {
            if (currentEnemy.getClass().getSuperclass().equals(Monster.class)) {
                attack(currentEnemy);
            }
        }
    }

    private void handleItem(Item currentItem) {
        if (currentItem != null) {
            pickupItem(currentItem);
            getCell().setItem(null);
        }
    }

    private void handleFood(Cell cell) {
        if (cell.getType() == CellType.FOOD) {
            health += 5;
            cell.setType(CellType.FLOOR);
        }
    }

    @Override
    public void die() {
        System.out.println("You are dead!");
        tileName = "player-death";
    }

    private void pickupItem(Item item) {
        Optional<Item> containedItem = inventory.keySet().stream()
                .filter(i -> i.getTileName().equals(item.getTileName())).findFirst();

        if (containedItem.isPresent()) {
            Integer currentItemCount = inventory.get(containedItem.get());
            if (!(currentItemCount == null || currentItemCount == 0)) {
                inventory.put(containedItem.get(), currentItemCount + 1);
            }

            System.out.println("Item " + item.getTileName() + " added to inventory.");
        } else {
            inventory.put(item, 1);
        }
    }

    public String getTileName() {
        return tileName;
    }

    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Item, Integer> inventory) {
        this.inventory = inventory;
    }
}
