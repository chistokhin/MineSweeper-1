package com.minesweeper.s6h.minesweeper;

import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    static TextView mGameStatusTextView;
    static TextView mMineCount;
    static Button mPlayAgainBtn;

    static Chronometer mTimerChrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // prevent from rotating the screen
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mGameStatusTextView = findViewById(R.id.gameStatusMessage);
        mMineCount = findViewById(R.id.mineCount);
        mPlayAgainBtn = findViewById(R.id.playAgain_btn);
        mPlayAgainBtn.setVisibility(View.INVISIBLE);
        mMineCount.setText("" + SettingsActivity.getBombNumber());

        mTimerChrono = findViewById(R.id.timerChronometer);

        startNewGame();

        mPlayAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    public void startNewGame() {
        mGameStatusTextView.setText("");
        mTimerChrono.setBase(SystemClock.elapsedRealtime());
        mPlayAgainBtn.setVisibility(View.INVISIBLE);
        GameEngine.getInstance().createGrid(this);
    }
}
