package com.minesweeper.s6h.minesweeper;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import com.minesweeper.s6h.minesweeper.util.Generator;
import com.minesweeper.s6h.minesweeper.views.grid.Cell;

/**
 * Created by S6H on 1/2/2018.
 */

public class GameEngine {
    private static GameEngine instance;

    public static boolean isTimerStarted; // check if timer already started or not

    private static final String GAME_WIN_MESSAGE = "Game Won";
    private static final String GAME_LOST_MESSAGE = "Game Lost";

    private Context context;

    private Cell[][] mineSweeperGrid = new Cell[SettingsActivity.getColumnNumber()]
            [SettingsActivity.getRowNumber()];

    private GameEngine() {}

    public static GameEngine getInstance() {
        if(instance == null) {
            instance = new GameEngine();
        }

        return instance;
    }

    public void createGrid(Context context){
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = Generator.generate(SettingsActivity.getBombNumber(),
                SettingsActivity.getColumnNumber(), SettingsActivity.getRowNumber());
        setGrid(context,GeneratedGrid);
    }

    private void setGrid( final Context context, final int[][] grid ){
        for(int x = 0; x < SettingsActivity.getColumnNumber(); x++ ){
            for(int y = 0; y < SettingsActivity.getRowNumber(); y++ ){
                if( mineSweeperGrid[x][y] == null ){
                    mineSweeperGrid[x][y] = new Cell( context , x,y);
                }
                mineSweeperGrid[x][y].setValue(grid[x][y]);
                mineSweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int position) {
        int x = position % SettingsActivity.getColumnNumber();
        int y = position / SettingsActivity.getColumnNumber();

        return mineSweeperGrid[x][y];
    }

    public Cell getCellAt( int x , int y ){
        return mineSweeperGrid[x][y];
    }

    public void click( int x , int y ){

        // start timer on first click
        if (!isTimerStarted) {
            GameActivity.mTimerChrono.setBase(SystemClock.elapsedRealtime());
            GameActivity.mTimerChrono.start();
            isTimerStarted = true;
        }

        if( x >= 0 && y >= 0 && x < SettingsActivity.getColumnNumber() && y < SettingsActivity.getRowNumber() && !getCellAt(x,y).isClicked() ){
            getCellAt(x,y).setClicked();

            if( getCellAt(x,y).getValue() == 0 ){
                for( int xt = -1 ; xt <= 1 ; xt++ ){
                    for( int yt = -1 ; yt <= 1 ; yt++){
                        if( xt != yt ){
                            click(x + xt , y + yt);
                        }
                    }
                }
            }

            if( getCellAt(x,y).isBomb() ){
                onGameLost();
            }


        }

        // check if we win the game
        checkEnd();
    }


    private boolean checkEnd(){
        int bombFound = SettingsActivity.getBombNumber();
        int notRevealed = SettingsActivity.getColumnNumber() * SettingsActivity.getRowNumber();
        for (int x = 0; x < SettingsActivity.getColumnNumber(); x++ ){
            for(int y = 0; y < SettingsActivity.getRowNumber(); y++ ){
                if( getCellAt(x,y).isRevealed() || getCellAt(x,y).isFlagged() ){
                    notRevealed--;
                }

                if( getCellAt(x,y).isFlagged() && getCellAt(x,y).isBomb() ){
                    bombFound--;
                }
            }
        }

        if( bombFound == notRevealed ){
            winGame();
            return true;
        }
        return false;
    }

    private void winGame() {
        GameActivity.mTimerChrono.stop();
        GameActivity.mGameStatusTextView.setText(GAME_WIN_MESSAGE);
        GameActivity.mPlayAgainBtn.setVisibility(View.VISIBLE);
        isTimerStarted = false;
    }

    public void flag( int x , int y ){
        boolean isFlagged = getCellAt(x,y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).invalidate();
    }

    private void onGameLost(){
        // handle lost game
        GameActivity.mGameStatusTextView.setText(GAME_LOST_MESSAGE);
        GameActivity.mPlayAgainBtn.setVisibility(View.VISIBLE);
        GameActivity.mTimerChrono.stop();
        isTimerStarted = false;
        for (int x = 0; x < SettingsActivity.getColumnNumber(); x++ ) {
            for (int y = 0; y < SettingsActivity.getRowNumber(); y++) {
                if(!getCellAt(x, y).isBomb()) {
                    getCellAt(x, y).setClicked();
                } else {
                    getCellAt(x,y).setRevealed();
                }
            }
        }
    }
}
