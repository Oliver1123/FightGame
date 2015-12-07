package com.example.oliver.fightgame;

import android.os.AsyncTask;

import com.example.oliver.fightgame.models.Unit;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class get a list of Unit and CustomLogger for logging as an input parameters,
 * and can show fight between that units.
 */
public class TournamentTask extends AsyncTask<Void, String, Void> {
    private static final int PAUSE_MILLISECONDS = 1000;
    private final List<Unit> mFighters;
    private final int mArenaSize;
    private WeakReference<CustomLogger> mLogger;
    private Arena mArena;

    public TournamentTask(List<Unit> _fighters, int _arenaSize, CustomLogger _logger) {
        mFighters = _fighters;
        mArenaSize = _arenaSize;
        mLogger = new WeakReference<>(_logger);
    }
    @Override
    protected Void doInBackground(Void... params) {
        // reanimate all units =)
        List<Unit> liveFighter = copyFighters(mFighters);
        mArena = new Arena(liveFighter, mArenaSize);
        for (Unit fighter: liveFighter) {
            publishProgress(fighter + ". Start position (" + fighter.getPosition().first + ", " + fighter.getPosition().second + ")");
        }

        int roundCount = 1;
        while (liveFighter.size() > 1) {
            publishProgress("\n == Round " + roundCount++ + " began! == ");

            for (Unit fighter : liveFighter) {
                if (!fighter.isDead()) {
                    Unit enemy = mArena.getTheNearestEnemy(fighter);
                    if (enemy == null) break;
                    if (fighter.canAttackEnemy(enemy)) {
                        publishProgress(fighter.attackEnemy(enemy));
                        publishProgress(fighter.regenerate());
                        if (!enemy.isDead()) {
                            publishProgress(enemy.counterAttack(fighter));
                        }
                    } else {
                       publishProgress(mArena.moveTo(fighter, enemy));
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
}
