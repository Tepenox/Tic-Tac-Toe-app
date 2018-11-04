package com.example.anass.connetthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    //0 for red player 1 for yellow player 2 for now one
    private int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    private int activePlayer = 0;

    ImageView clickedSpot;


    public void onSpotClick(View view) {
        if (view instanceof ImageView) {
            clickedSpot = (ImageView) view;
            int clickedSpotNumber = Integer.valueOf(clickedSpot.getTag().toString());
            if (gameStatus[clickedSpotNumber] == 2) {
                gameStatus[clickedSpotNumber] = activePlayer;
                clickedSpot.setTranslationY(-1500);
                if (activePlayer == 0) {
                    clickedSpot.setImageResource(R.drawable.red);
                    activePlayer = 1;
                } else if (activePlayer == 1) {
                    clickedSpot.setImageResource(R.drawable.yellow);
                    activePlayer = 0;
                }

                clickedSpot.animate().translationYBy(1500).setDuration(300);
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
