package com.example.oliver.fightgame;

import com.example.oliver.fightgame.models.Berserk;
import com.example.oliver.fightgame.models.BladeMaster;
import com.example.oliver.fightgame.models.Blocker;
import com.example.oliver.fightgame.models.Knight;
import com.example.oliver.fightgame.models.Ninja;
import com.example.oliver.fightgame.models.Unit;

/**
 * Created by oliver on 17.11.15.
 */
public class UnitFactory {

    public static Unit getRandomUnit(String name, int hp, int strength, int mana) {
        int unitNumber = RandomValue.nextInt() %  5;
        Unit unit;
        switch (unitNumber) {
            case 0: unit = new Knight(name, hp, strength, mana, RandomValue.nextInt(5));
                break;
            case 1: unit = new Berserk(name, hp, strength, mana);
                break;
            case 2: unit = new BladeMaster(name, hp, strength, mana, RandomValue.nextInt(20));
                break;
            case 3: unit = new Blocker(name, hp, strength, mana, RandomValue.nextInt(20));
                break;
            default:
                unit = new Ninja(name, hp, strength, mana, RandomValue.nextInt(20));
        }
        return unit;
    }
}
