package com.example.oliver.fightgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.oliver.fightgame.models.Arena;
import com.example.oliver.fightgame.models.Berserk;
import com.example.oliver.fightgame.models.Knight;
import com.example.oliver.fightgame.models.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] names = getResources().getStringArray(R.array.names);
        final Arena arena = new Arena();
        arena.addFighter(names[RandomValue.nextInt(names.length)], 100, 10);
        arena.addFighter(names[RandomValue.nextInt(names.length)], 100, 10);
        arena.addFighter(names[RandomValue.nextInt(names.length)], 100, 10);
        arena.addFighter(names[RandomValue.nextInt(names.length)], 100, 10);
        findViewById(R.id.startBattle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            arena.startBattle();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


}
