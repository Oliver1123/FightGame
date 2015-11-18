package com.example.oliver.fightgame.models;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.oliver.fightgame.UnitFactory;

/**
 * Created by oliver on 17.11.15.
 */
public class UnitTest extends AndroidTestCase {
    private static String UNIT_NAME     = "unit_name";
    private static int UNIT_HP          = 100;
    private static int UNIT_STRENGTH    = 100;
    private static int UNIT_MANA        = 100;

    public void testImmortality() {
        Unit unit = new Unit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA) {};
//        Unit unit = UnitFactory.getRandomUnit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA);
        Log.d("tag", "unit: " + unit);
        assertTrue("Unit must be not dead!", !unit.isDead());
        // some type of unit can avoid or block attack, so try a few times
        for (int i = 0; i < 20; i++) {
            Log.d("tag", unit.getDamage(unit.getMaxHP()));
        }
        assertTrue("Unit must be dead!", unit.isDead());

    }

    public void testOverHeal() {
        Unit unit = new Unit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA) {};
//        Unit unit = UnitFactory.getRandomUnit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA);
        assertTrue("HP must be <= maxHP", unit.getHP() <= unit.getMaxHP());
        Log.d("tag", unit.heal(unit.getMaxHP()));
        assertTrue("HP must be <= maxHP", unit.getHP() <= unit.getMaxHP());
    }

}
