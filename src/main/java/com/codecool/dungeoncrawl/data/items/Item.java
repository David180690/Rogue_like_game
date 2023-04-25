package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

abstract public class Item implements Drawable {
  private Cell cell;
  protected int damage = 0;

 public Item(Cell cell) {
  this.cell = cell;
  this.cell.setItem(this);
 }

    public int getDamage() {
        return damage;
    }
}
