package com.example.oliver.fightgame;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by oliver on 25.11.15.
 */
public class CustomLogger {
    private final int STANDART_LOGGER = 0;
    private final int TEXTVIEW_LOGGER   = 1;

    private  TextView mTextView = null;
    private  String mTag = null;
    private int mLoggerType;

    public CustomLogger(TextView _textView) {
        mTextView = _textView;
        mLoggerType = TEXTVIEW_LOGGER;
    }
    public CustomLogger(String _tag) {
        mTag = _tag;
        mLoggerType = STANDART_LOGGER;
    }

    public void log(String str) {
        switch (mLoggerType) {
            case STANDART_LOGGER:
                defaultLog(str);
                break;
            case TEXTVIEW_LOGGER:
                textViewLog(str);
                break;
        }
    }

    private void textViewLog(String _str) {
        StringBuilder prevText = new StringBuilder(mTextView.getText());
        prevText.append(System.lineSeparator());
        prevText.append(_str);
        mTextView.setText(prevText);
    }

    private void defaultLog(String _str) {
        Log.d(mTag, _str);
    }
}
