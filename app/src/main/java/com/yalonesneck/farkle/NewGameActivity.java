package com.yalonesneck.farkle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewGameActivity extends AppCompatActivity {
    public static final String EXTRA_PLAYERS = "com.yalonesneck.farkle.players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
    }

    private boolean checkPlayerName() {
        EditText playerNameView = this.findViewById(R.id.editTextPlayerName);
        if (playerNameView.getText().length() <= 0) {
            playerNameView.setError(getResources().getString(R.string.bad_mustnotbeempty));
            return false;
        }
        return true;
    }

    private boolean checkWinScore() {
        EditText winScoreView = this.findViewById(R.id.editTextWinScore);

        if (winScoreView.getText().length() <= 0) {
            winScoreView.setError(getResources().getString(R.string.bad_mustnotbeempty));
            return false;
        }

        Pattern p = Pattern.compile("[1-9]\\d*");
        Matcher m = p.matcher(winScoreView.getText());
        if (!m.matches()) {
            winScoreView.setError(getResources().getString(R.string.bad_winscore));
            return false;
        }
        return true;
    }

    private ArrayList<String> getPlayers() {
        ArrayList<String> players = new ArrayList<String>();

        EditText playerNameView = this.findViewById(R.id.editTextPlayerName);
        players.add(playerNameView.getText().toString());
        players.add("Computer");

        return players;
    }

    public void createGame(View view) {
        boolean error = false;
        error |= !this.checkPlayerName();
        error |= !this.checkWinScore();

        if (!error) {
            ArrayList<String> players = this.getPlayers();

            Intent intent = new Intent(this, InGameActivity.class);
            intent.putExtra(EXTRA_PLAYERS, players);
            startActivity(intent);
        }
    }
}