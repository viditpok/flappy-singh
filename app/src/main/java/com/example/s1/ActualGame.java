package com.example.s1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.example.s1.gameplay.GamePanel;
import com.example.s1.gameplay.Player;


public class ActualGame extends AppCompatActivity {
    private Integer startingLives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startingLives = extras.getInt("livesRemaining");
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FrameLayout game = new FrameLayout(this);

        GamePanel gp = new GamePanel(this);


        Button upButton = new Button(this);
        Button downButton = new Button(this);
        Button rightButton = new Button(this);
        Button leftButton = new Button(this);

        // Set the button size
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(100, 100);
        params1.leftMargin = 490;
        params1.topMargin = 1770;
        params1.gravity = Gravity.TOP | Gravity.LEFT;
        upButton.setLayoutParams(params1);
        // Set the button size
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(100, 100);
        params2.leftMargin = 490;
        params2.topMargin = 1870;
        params2.gravity = Gravity.TOP | Gravity.LEFT;
        downButton.setLayoutParams(params2);

        // Set the button size
        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(100, 100);
        params3.leftMargin = 390;
        params3.topMargin = 1820;
        params3.gravity = Gravity.TOP | Gravity.LEFT;
        leftButton.setLayoutParams(params3);

        // Set the button size
        FrameLayout.LayoutParams params4 = new FrameLayout.LayoutParams(100, 100);
        params4.leftMargin = 590;
        params4.topMargin = 1820;
        params4.gravity = Gravity.TOP | Gravity.LEFT;
        rightButton.setLayoutParams(params4);


        /*
        upButton.setWidth(75);
        upButton.setHeight(75); */
        upButton.setText("U");
        downButton.setText("D");
        rightButton.setText("R");
        leftButton.setText("L");

        game.addView(gp);
        game.addView(upButton);
        game.addView(downButton);
        game.addView(rightButton);
        game.addView(leftButton);
        setContentView(game);

        Player player = gp.getPlayer();
        player.getScoreboard().setLivesRemaining(startingLives);
        upButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                player.up();
            }
        });
        downButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                player.down();
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                player.right();
            }
        });
        leftButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                player.left();
            }
        });
        player.getScoreboard().getLivesRemaining().observe(
                ActualGame.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if (integer <= 0) {
                            gp.getThread().setRunning(false);
                            Intent intent = new Intent(ActualGame.this, GameOverScreen.class);
                            intent.putExtra("score", 0);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

        player.getScoreboard().getWin().observe(ActualGame.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if (bool) {
                    gp.getThread().setRunning(false);
                    Intent intent = new Intent(ActualGame.this, GameOverScreen.class);
                    intent.putExtra("score", player.getScoreboard().getScore());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
