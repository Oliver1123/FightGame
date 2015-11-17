package com.example.oliver.fightgame;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.oliver.fightgame.models.Unit;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by oliver on 17.11.15.
 */
public class TournamentTask extends AsyncTask<Void, String, Void> {
    private static final int PAUSE_MILLISECONDS = 1000;
    private List<Unit> mFighters, mLiveFighters;
    private WeakReference<TextView> mLog;

    public TournamentTask(List<Unit> fighters, TextView log) {
        mFighters = fighters;
        mLog = new WeakReference<>(log);
    }
    @Override
    protected Void doInBackground(Void... params) {
        // reanimate all units =)
        mLiveFighters = new ArrayList<>(mFighters.size());
        for (Unit unit : mFighters) {
            publishProgress(unit.toString());
            mLiveFighters.add(unit);
        }

        int roundCount = 1;
        while (mLiveFighters.size() > 1) {
            publishProgress("\n == Round " + roundCount++ + " began! == ");

            for (Unit fighter : mLiveFighters) {
                if (!fighter.isDead()) {
                    Unit target = getEnemy(fighter);
                    publishProgress(fighter.attackEnemy(target));
                    publishProgress(fighter.heal());
                    try {
                        TimeUnit.MILLISECONDS.sleep(PAUSE_MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            takeAwayDeadBodies();
        }

        Unit winner = mLiveFighters.get(0);
        publishProgress(winner.getClass().getSimpleName() + " " + winner.getName() + " win the battle!!!");

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mLog.get().setText("");
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
//        Log.d("tag", values[0]);
        String currentProgress = mLog.get().getText().toString();
        mLog.get().setText(currentProgress + "\n" + values[0]);
    }

    private void takeAwayDeadBodies() {
        Iterator<Unit> iterator = mLiveFighters.iterator();
        while (iterator.hasNext()) {
            Unit unit = iterator.next();
            if (unit.isDead()) {
                iterator.remove();
            }
        }
    }

    /**
     * Find enemy for unit among alive units
     * @param unit fighter who need an enemy
     * @return enemy for given fighter
     */
    private Unit getEnemy(Unit unit) {
        Unit enemy;
        do {
            enemy = mLiveFighters.get(RandomValue.nextInt(mLiveFighters.size()));
        } while (unit.equals(enemy) || enemy.isDead());
        return enemy;
    }
}
