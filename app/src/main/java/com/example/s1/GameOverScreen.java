package com.example.s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverScreen extends AppCompatActivity {
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            finalScore = extras.getInt("score");
        }

        // Handle user click startGameButton
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameOverScreen.this, ConfigScreen.class));
                finish();
            }
        });

        //user clicking quitGameButton
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //System.exit(0);
            }
        });

        TextView winDisplay = findViewById(R.id.winText);
        winDisplay.setText((finalScore > 0) ? "You Win" : "You're a Disgrace");

        TextView scoreDisplay = findViewById(R.id.scoreText);
        scoreDisplay.setText("Score: " + finalScore);
    }
}