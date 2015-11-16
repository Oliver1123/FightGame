package com.example.oliver.fightgame.models;

import android.util.Log;
import android.util.Pair;

import java.util.Random;

/**
 * Created by oliver on 16.11.15.
 */
public  class Unit {
    public static Random r;
    private String mName;
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mStrength;
    private boolean isDead;

    static {
        r = new Random();
    }

    public Unit(String name, int hp, int strength) {
        mName = name;
        mMaxHealth = hp;
        mCurrentHealth = hp;
        mStrength = strength;
    }
    public void getDamage(int strength) {
        if (this.mCurrentHealth > strength) {
            this.mCurrentHealth -= strength;
            Log.d("tag", getClass().getSimpleName() + " " + mName + " lose " + strength + "hp, " + mCurrentHealth + "hp left.");
        } else {
            Log.d("tag", getClass().getSimpleName() + " " + mName + " lose " + mCurrentHealth + "hp, 0hp left. " + mName + " is dead.");
            this.mCurrentHealth = 0;
            this.isDead = true;
        }

    }

    public void attackEnemy(Unit enemy){
        Log.d("tag", getClass().getSimpleName() +" " + mName + " attack " + enemy.getName());
    }

    public void heal(int health) {
        if (mCurrentHealth + health < mMaxHealth) {
            Log.d("tag", getClass().getSimpleName() + " heal " + health + "hp.");
        } else {
            Log.d("tag", getClass().getSimpleName() + " heal " + (mMaxHealth - mCurrentHealth) + "hp.");
            mCurrentHealth = mMaxHealth;
        }
    }

    public int getHP(){
        return mCurrentHealth;
    }

    public int getStrength() {
        return mStrength;
    }

    public boolean isDead() {
        return isDead;
    }
    public String getName() {
        return mName;
    }

    /** Calculate hit strength = strength +/- random[0; strength/4)
     *
     * @return hit strength
     */
    public int hit() {
        int sign = (r.nextInt() % 2 == 0) ? 1 : -1;
        int divergence = r.nextInt((int) (getStrength() * 0.25));
        return (getStrength() + sign * divergence);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "name: '" + mName + '\'' +
                ", maxHP: " + mMaxHealth +
                ", hp: " + mCurrentHealth +
                ", strength: " + mStrength;
    }
}
