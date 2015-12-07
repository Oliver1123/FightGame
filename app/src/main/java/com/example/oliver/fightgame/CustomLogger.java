package com.example.oliver.fightgame;

import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Class provide logging with TextView, File and standard Log
 */
public class CustomLogger {
    private final int STANDARD_LOGGER   = 0;
    private final int TEXTVIEW_LOGGER   = 1;
    private final int FILE_LOGGER       = 2;

    private TextView mTextView = null;
    private String mTag = null;
    private File mFile = null;
    private int mLoggerType;

    public CustomLogger(TextView _textView) {
        mTextView = _textView;
        mLoggerType = TEXTVIEW_LOGGER;
    }

    public CustomLogger(String _tag) {
        mTag = _tag;
        mLoggerType = STANDARD_LOGGER;
    }

    public CustomLogger(File _file) {
        mFile = _file;
        mLoggerType = FILE_LOGGER;
    }

    public void log(String str) {
        switch (mLoggerType) {
            case STANDARD_LOGGER:
                defaultLog(str);
                break;
            case TEXTVIEW_LOGGER:
                textViewLog(str);
                break;
            case FILE_LOGGER:
                fileLog(str);
                break;
        }
    }

    private void fileLog(String _str) {
        try {
            OutputStream outputStream = new FileOutputStream (mFile, true);
            outputStream.write(_str.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void textViewLog(String _str) {
        StringBuilder prevText = new StringBuilder(mTextView.getText());
        prevText.append("\n");
        prevText.append(_str);
        mTextView.setText(prevText);
    }

    private void defaultLog(String _str) {
        Log.d(mTag, _str);
    }
}
