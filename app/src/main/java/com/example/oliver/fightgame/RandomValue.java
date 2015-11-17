package com.example.oliver.fightgame;

import java.util.Random;

/**
 * Created by oliver on 17.11.15.
 */
public class RandomValue {
    private static Random r = new Random();

    public static int nextInt() {
        return r.nextInt();
    }
    public static int nextInt(int limit) {
        return r.nextInt(limit);
    }
}
