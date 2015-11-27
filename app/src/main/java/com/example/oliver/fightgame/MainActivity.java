package com.example.oliver.fightgame;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.oliver.fightgame.global.Constants;
import com.example.oliver.fightgame.models.Unit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private TextView tvLog;
    private TournamentTask mTournament;
    private SeekBar sbFightersCount;
    private String[] mNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNames = getResources().getStringArray(R.array.names);
        initViews();
    }

    private void initViews() {
        final TextView tvProgress = (TextView) findViewById(R.id.tvFightersCount);
        tvLog = (TextView) findViewById(R.id.tvLog);
        sbFightersCount = ((SeekBar)findViewById(R.id.sbFightersCount));
        sbFightersCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgress.setText(String.valueOf(progress + Constants.MIN_FIGHTERS_COUNT));
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
                tvLog.setText("New Battle Began!");

                int fightersCount = sbFightersCount.getProgress() + Constants.MIN_FIGHTERS_COUNT;
                List<Unit> fighters = new ArrayList<>();
                for (int i = 0; i < fightersCount; i++) {
                    fighters.add(UnitFactory.getRandomUnit(mNames[i % mNames.length],
                            Constants.DEFAULT_UNIT_HEALTH,
                            Constants.DEFAULT_UNIT_STRENGTH,
                            Constants.DEFAULT_UNIT_MANA));
                }
                if (mTournament != null) mTournament.cancel(true);
                mTournament = new TournamentTask(fighters, new CustomLogger(tvLog));
//                mTournament = new TournamentTask(fighters, new CustomLogger("tournament"));
                mTournament.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTournament != null) mTournament.cancel(true);
    }
}
