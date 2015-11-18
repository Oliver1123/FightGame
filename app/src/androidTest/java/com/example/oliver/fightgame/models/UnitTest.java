package com.example.oliver.fightgame.models;

import android.test.AndroidTestCase;

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
        for (int i = 0; i < 100; i++) {
            Unit unit = UnitFactory.getRandomUnit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA);

            assertTrue("Unit " + unit +" must be not dead!", !unit.isDead());
            // some type of unit can avoid or block attack, so try a few times
            for (int j = 0; j < 20; j++) {
                unit.getDamage(unit.getMaxHP());
            }
            assertTrue("Unit " + unit +" must be dead!", unit.isDead());
        }
    }

    public void testOverHeal() {
        for (int i = 0; i < 100; i++) {
            Unit unit = UnitFactory.getRandomUnit(UNIT_NAME, UNIT_HP, UNIT_STRENGTH, UNIT_MANA);

            assertTrue("HP must be <= maxHP", unit.getHP() <= unit.getMaxHP());
            unit.heal(unit.getMaxHP());
            assertTrue("HP must be <= maxHP", unit.getHP() <= unit.getMaxHP());
        }
    }

}
