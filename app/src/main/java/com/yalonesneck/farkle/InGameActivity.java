package com.yalonesneck.farkle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class InGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        Intent intent = this.getIntent();
        ArrayList<String> players = intent.getStringArrayListExtra(NewGameActivity.EXTRA_PLAYERS);

        LinearLayout scoreBoard = this.findViewById(R.id.linearLayoutScoreBoard);
        ConstraintLayout labelRow = this.findViewById(R.id.constraintLayoutScoreBoardLabelRow);
        TextView scoreLabel = this.findViewById(R.id.textViewScoreListScoreLabel);
        for (String player : players) {
            ConstraintLayout row = new ConstraintLayout(this);

            TextView score = new TextView(this);
            score.setId(View.generateViewId());
            score.setText("0");
            score.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            score.setLayoutParams(scoreLabel.getLayoutParams());

            TextView name = new TextView(this);
            name.setId(View.generateViewId());
            name.setText(player);

            row.addView(score);
            row.addView(name);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(row);
            constraintSet.connect(name.getId(), ConstraintSet.BOTTOM, row.getId(), ConstraintSet.BOTTOM, 0);
            constraintSet.connect(name.getId(), ConstraintSet.END, row.getId(), ConstraintSet.END, 0);
            constraintSet.connect(name.getId(), ConstraintSet.START, score.getId(), ConstraintSet.END, (int) (8 * getResources().getDisplayMetrics().density + 0.5f));
            constraintSet.connect(name.getId(), ConstraintSet.TOP, row.getId(), ConstraintSet.TOP, 0);
            constraintSet.applyTo(row);

            scoreBoard.addView(row);
        }
    }
}