package com.example.oliver.fightgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.oliver.fightgame.models.Arena;
import com.example.oliver.fightgame.models.Berserk;
import com.example.oliver.fightgame.models.Knight;
import com.example.oliver.fightgame.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Unit> fighters = new ArrayList<>();
        fighters.add( new Knight("рыцарь1", 100, 10, 2));
        fighters.add( new Knight("рыцарь2", 105, 9, 1));
        fighters.add( new Knight("рыцарь3", 95, 11, 2));
        fighters.add( new Berserk("берсерк1", 120, 8));
        fighters.add( new Berserk("берсерк2", 125, 7));
        fighters.add( new Berserk("берсерк3", 115, 9));
        final Arena arena = new Arena(fighters);
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
