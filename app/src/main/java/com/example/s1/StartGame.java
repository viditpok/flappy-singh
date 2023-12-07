package com.example.s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);

        // Handle user click startGameButton
        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                moveToConfigScreen();
                finish();
            }
        });

        //user clicking quitGameButton
        Button quitGameButton = findViewById(R.id.quitGameButton);
        quitGameButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    protected void moveToConfigScreen() {
        startActivity(new Intent(StartGame.this, ConfigScreen.class));
    }

}