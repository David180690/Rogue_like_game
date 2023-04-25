package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Bat;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Wizard;
import com.codecool.dungeoncrawl.ui.keyeventhandler.RandomMover;

import java.util.Iterator;

public class MonsterMover {
    private final RandomMover randomMover;

    public MonsterMover(RandomMover randomMover) {
        this.randomMover = randomMover;
    }

    public void moveWizards(GameMap map) {
        Iterator<Wizard> wizardsIterator = map.getWizards().iterator();
        while (wizardsIterator.hasNext()) {
            Wizard wizard = wizardsIterator.next();
            wizard.teleport(map);
        }
    }

    public void moveGhosts(GameMap map) {
        Iterator<Ghost> ghostIterator = map.getGhosts().iterator();
        while (ghostIterator.hasNext()) {
            Ghost ghost = ghostIterator.next();
            ghost.followPlayer(map, map.getPlayer());
        }
    }

    public void moveBats(GameMap map) {
        Iterator<Bat> batIterator = map.getBats().iterator();
        while (batIterator.hasNext()) {
            Bat bat = batIterator.next();
            bat.moveRandomly(randomMover);
        }
    }
}
