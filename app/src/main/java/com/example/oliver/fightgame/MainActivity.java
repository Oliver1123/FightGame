package com.example.oliver.fightgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.oliver.fightgame.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mLog;
    private TournamentTask mTournament;
    private SeekBar mFightersCount;
    private String[] mNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNames = getResources().getStringArray(R.array.names);
        initViews();
    }

    private void initViews() {
        final TextView progressText = (TextView) findViewById(R.id.tvProgress);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setMovementMethod(new ScrollingMovementMethod());
        mFightersCount = ((SeekBar)findViewById(R.id.fightersCount));
        mFightersCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressText.setText(String.valueOf(progress + 2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        findViewById(R.id.startBattle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int fightersCount = mFightersCount.getProgress() + 2;
                List<Unit> fighters = new ArrayList<>();
                for (int i = 0; i < fightersCount; i++) {
                    fighters.add(UnitFactory.getRandomUnit(mNames[i % mNames.length], 1000, 200, 50));
                }
                mTournament = new TournamentTask(fighters, mLog);
                mTournament.execute();
            }
        });
    }


}
