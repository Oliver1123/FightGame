package com.example.oliver.fightgame;

import android.os.AsyncTask;
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
        long startTime = System.currentTimeMillis();
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
                    Unit target = getEnemy(fighter, mLiveFighters);
                    if (target == null) break;
                    publishProgress(fighter.attackEnemy(target));
                    publishProgress(fighter.regenerate());
                    if (!target.isDead()) {
                        publishProgress(target.counterAttack(fighter));
                    }
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
        long endTime = System.currentTimeMillis();
        publishProgress("Duration of the battle is " + (endTime - startTime) + "ms");
        return null;
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
     * Find enemy for fighter among list of enemies
     * @param fighter fighter who need an enemy
     * @param enemies units who may be enemy
     * @return enemy for given fighter or null otherwise
     */
    private Unit getEnemy(Unit fighter, List<Unit> enemies) {
        List<Unit> potentialEnemies = new ArrayList<>();
        for (Unit unit : enemies) {
            if (!unit.isDead() && !unit.equals(fighter))
                potentialEnemies.add(unit);
        }
        if (potentialEnemies.size() > 0) {
            return potentialEnemies.get(RandomValue.nextInt(potentialEnemies.size()));
        }else {
            return null;
        }
    }
}
