package com.example.oliver.fightgame;

import android.os.AsyncTask;

import com.example.oliver.fightgame.global.RandomValue;
import com.example.oliver.fightgame.models.Unit;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class get a list of Unit and TextView for logging as an input parameters,
 * and can show fight between that units.
 */
public class TournamentTask extends AsyncTask<Void, String, Void> {
    private static final int PAUSE_MILLISECONDS = 1000;
    private final List<Unit> mFighters;
    private WeakReference<CustomLogger> mLogger;

    public TournamentTask(List<Unit> _fighters, CustomLogger _logger) {
        mFighters = _fighters;
        mLogger = new WeakReference<>(_logger);
    }
    @Override
    protected Void doInBackground(Void... params) {
        // reanimate all units =)
        List<Unit> liveFighter = copyFighters(mFighters);

        int roundCount = 1;
        while (liveFighter.size() > 1) {
            publishProgress("\n == Round " + roundCount++ + " began! == ");

            for (Unit fighter : liveFighter) {
                if (!fighter.isDead()) {
                    Unit target = getEnemy(fighter, liveFighter);
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
            takeAwayDeadBodies(liveFighter);
        }

        Unit winner = liveFighter.get(0);
        publishProgress(winner.getClass().getSimpleName() + " " + winner.getName() + " win the battle!!!");
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
//        Log.d("tag", values[0]);
        mLogger.get().log(values[0]);
    }

    /**
     * Create copy of list of fighters
     * @param _fighters list of fighters
     * @return copy of given list
     */
    private List<Unit> copyFighters(List<Unit> _fighters) {
        if (_fighters == null) return null;

        List<Unit> result = new ArrayList<>(mFighters.size());
        for (Unit unit : mFighters) {
            publishProgress(unit.toString());
            result.add(unit);
        }
        return  result;
    }

    /**
     * Remove dead unit from fighters list
     * @param _fighters list of fighters
     */
    private void takeAwayDeadBodies(List<Unit> _fighters) {
        Iterator<Unit> iterator = _fighters.iterator();
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
