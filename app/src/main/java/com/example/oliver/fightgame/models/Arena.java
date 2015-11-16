package com.example.oliver.fightgame.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by oliver on 16.11.15.
 */
public class Arena {
    private Random r = new Random();
    private final List<Unit> mFighters;
    private List<Unit> mLiveFighters;

    public Arena(List<Unit> fighters) {
        mFighters = fighters;
    }

    public void startBattle() throws InterruptedException {
        mLiveFighters = new ArrayList<>(mFighters.size());
        for (Unit unit : mFighters) {
            Log.d("tag", unit.toString());
            mLiveFighters.add(unit);
        }
        int roundCount = 1;
        while (mLiveFighters.size() > 1) {
            Log.d("tag", " == Round " + roundCount++  + " began! == ");
            for (int i = 0; i < mLiveFighters.size(); i++) {
                Unit fighter = mLiveFighters.get(i);
                Unit target = getEnemy(i);
                fighter.attackEnemy(target);
                if (target.isDead()) {
                    mLiveFighters.remove(target);
                }
                Thread.sleep(1000);
            }
        }
        Log.d("tag", mLiveFighters.get(0).getName() + " win!!!");
    }

    private Unit getEnemy(int i) {
        int enemy;
        do {
            enemy = r.nextInt(mLiveFighters.size());
        } while (i == enemy);
        return mLiveFighters.get(enemy);
    }

}
