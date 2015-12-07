package com.example.oliver.fightgame;

import com.example.oliver.fightgame.global.Constants;
import com.example.oliver.fightgame.global.RandomValue;
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
    /**
     * Create a random child of Unit class with given basic parameters
     */
    public static Unit getRandomUnit(String _name, int _hp, int _strength, int _mana) {
        int unitNumber = RandomValue.nextInt() %  5;
        Unit unit;
        switch (unitNumber) {
            case 0:
                // default armor may be [0; strength/2]
                unit = createKnight(_name, _hp, _strength, _mana, RandomValue.nextInt(_strength / 2));
                break;
            case 1:
                unit = createBerserk(_name, _hp, _strength, _mana);
                break;
            case 2:
                unit = createBladeMaster(_name, _hp, _strength, _mana, RandomValue.nextInt(Constants.DEFAULT_MAX_SKILL_VALUE));
                break;
            case 3:
                unit = createBlocker(_name, _hp, _strength, _mana, RandomValue.nextInt(Constants.DEFAULT_MAX_SKILL_VALUE));
                break;
            default:
                unit = createNinja(_name, _hp, _strength, _mana, RandomValue.nextInt(Constants.DEFAULT_MAX_SKILL_VALUE));
        }
        return unit;
    }
    /**Create a Berserk unit with given parameters*/
    public static Berserk createBerserk(String _name, int _hp, int _strength, int _mana) {
        return new Berserk(_name, _hp, _strength, _mana);
    }

    /**Create a BladeMaster unit with given parameters*/
    public static BladeMaster createBladeMaster(String _name, int _hp, int _strength, int _mana, int _criticalStrikeChance) {
        return new BladeMaster(_name, _hp, _strength, _mana, _criticalStrikeChance);
    }

    /**Create a Blocker unit with given parameters*/
    public static Blocker createBlocker(String _name, int _hp, int _strength, int _mana, int _blockedDamagePercent) {
        return new Blocker(_name, _hp, _strength, _mana, _blockedDamagePercent);
    }

    /**Create a Knight unit with given parameters*/
    public static Knight createKnight(String _name, int _hp, int _strength, int _mana, int _armor) {
        return new Knight(_name, _hp, _strength, _mana, _armor);
    }

    /**Create a Ninja unit with given parameters*/
    public static Ninja createNinja(String _name, int _hp, int _strength, int _mana, int _evasionChance) {
        return new Ninja(_name, _hp, _strength, _mana, _evasionChance);
    }

}
