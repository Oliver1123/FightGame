package com.example.oliver.fightgame.models;

import android.util.Pair;

import com.example.oliver.fightgame.Arena;
import com.example.oliver.fightgame.UnitFactory;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliver on 30.11.15.
 */
public class ArenaTest  extends TestCase {
    private final String UNIT_NAME     = "unit_name";
    private final int UNIT_HP          = 100;
    private final int UNIT_STRENGTH    = 100;
    private final int UNIT_MANA        = 100;
    private final int ARENA_SIZE       = 10;
    private List<Unit> mFighters;
    private Arena mArena;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFighters = new ArrayList<>();
        mFighters.add(UnitFactory.getRandomUnit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA));
        mFighters.add(UnitFactory.getRandomUnit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA));

        mArena = new Arena(mFighters, ARENA_SIZE);
    }

    public void testNearestEnemy() {
        Unit unit = mArena.getTheNearestEnemy(mFighters.get(0));
        assertFalse("Can't find nearest enemy", unit == mFighters.get(1));
    }

    public void testMoveToEnemy() {
        Unit fighter = mArena.getUnit(0);
        Unit enemy = mArena.getUnit(1);
        mArena.setUnitPosition(fighter, new Pair<Integer, Integer>(7, 8));
        mArena.setUnitPosition(enemy, new Pair<Integer, Integer>(5,5));

        assertFalse("Error fighter should not get at the enemy", fighter.canAttackEnemy(enemy));
        while (!fighter.canAttackEnemy(enemy)) {
            mArena.moveTo(fighter, enemy);
        }
        assertTrue("Error fighter must to get at the enemy", fighter.canAttackEnemy(enemy));
    }
}
