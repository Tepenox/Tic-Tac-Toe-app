package com.example.anass.connetthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //0 for red player 1 for yellow player 2 for now one
    private GridLayout gridLayout;
    private ImageView clickedSpot;
    private TextView winnerTextView;
    private Button playAgainButton;


    private int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] winingStates = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    private boolean winner = false;
    private boolean draw = false;
    private int activePlayer = 0;

    public void onSpotClick(View view) {
        if (view instanceof ImageView && !winner && !draw) {
            clickedSpot = (ImageView) view;
            int clickedSpotNumber = Integer.valueOf(clickedSpot.getTag().toString());
            if (gameStatus[clickedSpotNumber] == 2) {
                gameStatus[clickedSpotNumber] = activePlayer;
                clickedSpot.setTranslationY(-1500);
                if (activePlayer == 0) {
                    clickedSpot.setImageResource(R.drawable.red);
                    checkForWinner();
                    activePlayer = 1;
                } else if (activePlayer == 1) {
                    clickedSpot.setImageResource(R.drawable.yellow);
                    checkForWinner();
                    activePlayer = 0;
                }
                checkForDraw();
                clickedSpot.animate().translationYBy(1500).setDuration(300);


            }

        }
    }

    public void playAgain(View view) {
        winner = false;
        draw = false;
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageDrawable(null);
        }

        for (int i = 0; i < gameStatus.length; i++) {
            gameStatus[i] = 2;
        }
    }

    private void checkForWinner() {
        for (int[] winingState : winingStates) {
            if (gameStatus[winingState[0]] != 2 &&
                    gameStatus[winingState[0]] == gameStatus[winingState[1]] &&
                    gameStatus[winingState[1]] == gameStatus[winingState[2]]) {
                winner = true;
                String winnerPlayer = (activePlayer == 0) ? "red " : "yellow ";
                String winnerText = winnerPlayer + " has won !";
                winnerTextView.setText(winnerText);
                winnerTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private void checkForDraw(){
        for(int i :gameStatus){
            if (i != 2) {
                continue;
            }
            return;
        }
        draw = true;
        winnerTextView.setText("Draw");
        winnerTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridLayout);
        winnerTextView = findViewById(R.id.winnerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
    }
}
