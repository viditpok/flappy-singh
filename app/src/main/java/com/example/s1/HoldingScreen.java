package com.example.s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HoldingScreen extends AppCompatActivity {
    private String name = "";
    private String spriteURL = "";
    private String difficulty = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holding_screen);

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
        Glide.with(HoldingScreen.this)
                .load(spriteURL)
                .into(spriteDisplay);

        // Display selected difficulty
        TextView difficultyDisplay = findViewById(R.id.difficultyDisplay);

        difficultyDisplay.setText(difficulty);

        // Handle user click confirm selections
        Button gameScreenButton = findViewById(R.id.gameScreenButton);

        gameScreenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gameScreenIntent = new Intent(HoldingScreen.this, GameScreen.class);
                gameScreenIntent.putExtra("name", name);
                gameScreenIntent.putExtra("difficulty", difficulty);
                gameScreenIntent.putExtra("spriteURL", spriteURL);
                startActivity(gameScreenIntent);
                finish();
            }
        });
    }
}