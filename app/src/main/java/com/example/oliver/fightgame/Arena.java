package com.example.oliver.fightgame;

import android.util.Pair;

import com.example.oliver.fightgame.global.RandomValue;
import com.example.oliver.fightgame.models.Unit;

import java.util.List;

/**
 * Class represent a square arena with list of fighters on it.<p>
 * Each position {@code Pair<Integer, Integer>} can be occupied or available.<p>
 * To each fighter can be found the nearest enemy.<p>
 * Each fighter can be moved in the direction to another fighter. Also the position of each player can be changed
 */
public class Arena {
    private List<Unit> mFighters;
    private final int mArenaSize;
    private Unit[][] mArena;

    public Arena (List<Unit> _fighters, int _arenaSize) {
        mFighters = _fighters;
        mArenaSize = _arenaSize;
        initArena(mArenaSize);
        fightersEmplacementRandom(_fighters);
    }

    private void initArena(int _arenaSize) {
        mArena = new Unit[_arenaSize][_arenaSize];
        for (int i = 0; i < _arenaSize; i++) {
            for (int j = 0; j < _arenaSize; j++) {
                mArena[i][j] = null;
            }
        }
    }

    /**
     * @return true if there are no fighters on given position, false otherwise
     */
    public boolean isPositionAvailable(Pair<Integer, Integer> _position) {
        if (_position.first < 0 || _position.first >= mArena.length ||
                _position.second < 0 || _position.second >= mArena.length )
            return false;

        return mArena[_position.first][_position.second] == null;
    }

    /**
     * Place fighters in the random position in the arena
     * @param _fighters
     */
    private void fightersEmplacementRandom(List<Unit> _fighters) {
        for (Unit fighter : _fighters) {
           setUnitPositionRandom(fighter);
        }
    }

    /**
     * @return fighters count on this Arena
     */
    public int getUnitCount() {
        return mFighters.size();
    }

    public Unit getUnit(int _index) {
        return mFighters.get(_index);
    }

    public List<Unit> getAllUnits() {
        return mFighters;
    }

    /**
     * Find an opponent who is in the area with the given radius
     * @return found enemy or null otherwise
     */
    private Unit getEnemyNearBy(Unit _fighter, int _radius) {
        int fighterPosI = _fighter.getPosition().first;
        int fighterPosJ = _fighter.getPosition().second;

        for (int i = fighterPosI - _radius; i <= fighterPosI + _radius ; i++) {
            for (int j = fighterPosJ - _radius; j <= fighterPosJ + _radius ; j++) {
                if (i >= 0 && i < mArena.length && j >= 0 && j < mArena.length ) {// i,j inside mArena
                    if (mArena[i][j] != null && !_fighter.equals(mArena[i][j])) {
                        Unit enemy = mArena[i][j];
                        if (!enemy.isDead()) {
                            return enemy;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Find the nearest opponent for given fighter
     * @return found enemy or null otherwise
     */
    public Unit getTheNearestEnemy(Unit _fighter) {
        Unit enemy;
        int radius = 1;
        while (true) {
            enemy = getEnemyNearBy(_fighter, radius);
            if (enemy != null) break;           // enemy found
            radius++;
            if (radius > mArenaSize) break;     // can't find enemy
        }

        return enemy;
    }

    /**
     * Move fighter in the direction of the enemy
     * @return information about process
     */
    public String  moveTo(Unit _fighter, Unit _enemy) {
        int diffI = _fighter.getPosition().first - _enemy.getPosition().first;
        int diffJ = _fighter.getPosition().second - _enemy.getPosition().second;
        if (Math.abs(diffI) > Math.abs(diffJ)) {
            if (diffI > 0)
                return unitMove(_fighter, Unit.DIRECTION_UP);
            else
                return unitMove(_fighter, Unit.DIRECTION_DOWN);
        } else {
            if (diffJ > 0)
                return unitMove(_fighter, Unit.DIRECTION_LEFT);
            else
                return unitMove(_fighter, Unit.DIRECTION_RIGHT);
        }
    }

    private boolean unitCanMoveUp(Unit _unit) {
        return _unit.getPosition().first - 1 >= 0;
    }

    private boolean unitCanMoveDown(Unit _unit) {
        return _unit.getPosition().first + 1 < mArena.length;
    }

    private boolean unitCanMoveLeft(Unit _unit) {
        return _unit.getPosition().second - 1 >= 0;
    }

    private boolean unitCanMoveRight(Unit _unit) {
        return _unit.getPosition().second + 1 < mArena.length;
    }

    private String  unitMove(Unit _unit, int _direction) {
        mArena[_unit.getPosition().first][_unit.getPosition().second] = null;
        String result = _unit.getClass().getSimpleName() + " " + _unit.getName() + " move from (" +
                + _unit.getPosition().first + ", " + _unit.getPosition().second + ") to ";
        switch (_direction) {
            case Unit.DIRECTION_UP:
                if (unitCanMoveUp(_unit))
                    _unit.move(Unit.DIRECTION_UP);
                break;
            case Unit.DIRECTION_DOWN:
                if (unitCanMoveDown(_unit))
                    _unit.move(Unit.DIRECTION_DOWN);
                break;
            case Unit.DIRECTION_LEFT:
                if (unitCanMoveLeft(_unit))
                    _unit.move(Unit.DIRECTION_LEFT);
                break;
            case Unit.DIRECTION_RIGHT:
                if (unitCanMoveRight(_unit))
                    _unit.move(Unit.DIRECTION_RIGHT);
                break;
        }

        mArena[_unit.getPosition().first][_unit.getPosition().second] = _unit;
        result += "(" + _unit.getPosition().first + ", " + _unit.getPosition().second + ")";
        return result;
    }

    /**
     * Change given Unit position to specified value
     *
     * @throws IllegalArgumentException
     *                if {@param _unit} isn't on the Arena.
     * @throws IllegalArgumentException
     *                if {@param _position} isn't available.
     */
    public void setUnitPosition(Unit _unit, Pair<Integer, Integer> _position) {
        if (!mFighters.contains(_unit))
            throw new IllegalArgumentException("Unit: " + _unit + " isn't on the Arena");
        if (!isPositionAvailable(_position))
            throw new IllegalArgumentException("Suggested cell [" + _position.first + ", " +
                                                + _position.second +"] is already occupied");

        mArena[_unit.getPosition().first][_unit.getPosition().second] = null;
        _unit.setPosition(_position);
        mArena[_unit.getPosition().first][_unit.getPosition().second] = _unit;
    }
    /**
     * Change given Unit position to random available
     *
     * @throws IllegalArgumentException
     *                if {@param _unit} isn't on the Arena.
     */
    public void setUnitPositionRandom(Unit _unit) {
        if (!mFighters.contains(_unit))
            throw new IllegalArgumentException("Unit: " + _unit + " isn't on the Arena");

        if (_unit.getPosition() != null) // unit has position on arena
            mArena[_unit.getPosition().first][_unit.getPosition().second] = null;

        Pair<Integer, Integer> position;
        do {
            position = new Pair<>(RandomValue.nextInt(mArenaSize), RandomValue.nextInt(mArenaSize));
        } while (!isPositionAvailable(position));
        _unit.setPosition(position);
        mArena[position.first][position.second] = _unit;
    }
}
