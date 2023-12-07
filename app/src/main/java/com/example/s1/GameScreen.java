package com.example.s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class GameScreen extends AppCompatActivity {
    private String name = "";
    private String spriteURL = "";
    private String difficulty = "";
    private int score = 0;
    private HashMap<String, Integer> difficultyToLives = new HashMap<String, Integer>() {{
            put("Easy", 4);
            put("Medium", 3);
            put("Hard", 2);
        }};
    private String startingMapURL = "https://ccl.northwestern.edu/netlogo/2.0.0/models/Sample%20Models/Games/Frogger.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            spriteURL = extras.getString("spriteURL");
            difficulty = extras.getString("difficulty");
        }

        // Display entered name
        TextView nameDisplay = findViewById(R.id.nameDisplay);
        nameDisplay.setText(name);

        // Display selected sprite
        ImageView spriteDisplay = findViewById(R.id.spriteDisplay);
        Glide.with(GameScreen.this)
                .load(spriteURL)
                .into(spriteDisplay);

        // Display selected difficulty
        TextView difficultyDisplay = findViewById(R.id.difficultyDisplay);
        difficultyDisplay.setText(difficulty);
        checkDifficultyLives(difficultyToLives);

        // Display the number of lives remaining
        TextView livesDisplay = findViewById(R.id.livesDisplay);
        int numLives = difficultyToLives.get(difficulty);
        if (difficulty.equals("Easy")) {
            checkEasyLives(numLives);
        } else if (difficulty.equals("Medium")) {
            checkMediumLives(numLives);
        } else {
            checkHardLives(numLives);
        }
        livesDisplay.setText(String.valueOf(numLives));

        // Display the current score
        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        checkScore(score);
        scoreDisplay.setText(Integer.toString(score));

        // Display the first map
        ImageView mapDisplay = findViewById(R.id.mapDisplay);
        Glide.with(GameScreen.this)
                .load(startingMapURL)
                .into(mapDisplay);

        Button playGameButton = findViewById(R.id.playButton);

        playGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent actualGameIntent = new Intent(GameScreen.this, ActualGame.class);
                actualGameIntent.putExtra("livesRemaining", difficultyToLives.get(difficulty));
                startActivity(actualGameIntent);
                finish();
            }
        });


    }

  
    public boolean checkDifficultyLives(HashMap<String, Integer> map) {
        return map.get("Easy") >= map.get("Medium")
                && map.get("Medium") >= map.get("Hard");
    }

    public boolean checkLivesDisplay(HashMap<String, Integer> map) {
        return map.get("Easy") >= map.get("Medium")
                && map.get("Medium") >= map.get("Hard");
    }
    public boolean checkEasyLives(int lives) {
        return lives == 4;
    }
    public boolean checkMediumLives(int lives) {
        return lives == 3;
    }
    public boolean checkHardLives(int lives) {
        return lives == 2;
    }
    public boolean checkScore(int score) {
        return score >= 0;
    }
}