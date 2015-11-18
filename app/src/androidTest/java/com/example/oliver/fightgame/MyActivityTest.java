import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.oliver.fightgame.MainActivity;
import com.example.oliver.fightgame.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by oliver on 18.11.15.
 *
 */
@RunWith(RobolectricTestRunner.class)
   public class MyActivityTest {
    private MainActivity activity;

    @Before
    public void setup()  {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(activity);
    }



    @Test
    public void clickingStartBattle_ShouldFillLog() {

        Button btnStartBattle = (Button) activity.findViewById(R.id.startBattle);
        assertNotNull(btnStartBattle);
        btnStartBattle.performClick();

        TextView tvLog = (TextView) activity.findViewById(R.id.tvLog);
        assertNotNull(tvLog);
        assertTrue("Log is not filled", TextUtils.isEmpty(tvLog.getText()));
    }

    @Test
    public void seekbarChange_ShouldChangetvProgress() {
        TextView tvCount = (TextView) activity.findViewById(R.id.tvFightersCount);

        SeekBar fightersCount = (SeekBar) activity.findViewById(R.id.sbFightersCount);
        final int progress = 10;
        fightersCount.setProgress(progress);
        assertTrue("Text must be " + (progress + 2), tvCount.getText().equals(String.valueOf(progress + 2)));

    }

}

