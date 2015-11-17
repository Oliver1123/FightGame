package com.example.oliver.fightgame.models;

import android.util.Log;

import com.example.oliver.fightgame.RandomValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by oliver on 16.11.15.
 */
public class Arena {
    private Random r = new Random();
    private List<Unit> mFighters;
    private List<Unit> mLiveFighters;

    public Arena(List<Unit> fighters) {
        mFighters = fighters;
    }
    public Arena() {
        mFighters = new ArrayList<>();
    }

    public void addFighter(String name, int hp, int strength) {
        mFighters.add(getRandomUnit(name, hp, strength));
    }

    public Unit getRandomUnit(String name, int hp, int strength) {
        int unitNumber = RandomValue.nextInt() %  5;
        Unit unit = null;
        switch (unitNumber) {
            case 0: unit = new Knight(name, hp, strength, RandomValue.nextInt(5));
                break;
            case 1: unit = new Berserk(name, hp, strength);
                break;
            case 2: unit = new BladeMaster(name, hp, strength, RandomValue.nextInt(20));
                break;
            case 3: unit = new Blocker(name, hp, strength, RandomValue.nextInt(20));
                break;
            default:
                unit = new Ninja(name, hp, strength, RandomValue.nextInt(20));
        }
        return unit;
    }
    public void startBattle() throws InterruptedException {
        mLiveFighters = new ArrayList<>(mFighters.size());
        for (Unit unit : mFighters) {
            Log.d("tag", unit.toString());
            mLiveFighters.add(unit);
        }
        int roundCount = 1;
        while (mLiveFighters.size() > 1) {
            Log.d("tag", " == Round " + roundCount++ + " began! == ");

            for (Unit fighter : mLiveFighters) {
                if (!fighter.isDead()) {
                    Unit target = getEnemy(fighter);
                    fighter.attackEnemy(target);
                    fighter.heal();
                    Thread.sleep(100);
                }
            }
            removeDeadBodies();
        }
        Log.d("tag", mLiveFighters.get(0).getName() + " win!!!");
    }

    private void removeDeadBodies() {
        Iterator<Unit> iterator = mLiveFighters.iterator();
        while (iterator.hasNext()) {
            Unit unit = iterator.next();
            if (unit.isDead()) {
                iterator.remove();
            }
        }
    }

    private Unit getEnemy(Unit unit) {
        Unit enemy;
        do {
            enemy = mLiveFighters.get(r.nextInt(mLiveFighters.size()));
        } while (unit.equals(enemy) || enemy.isDead());
        return enemy;
    }

}
