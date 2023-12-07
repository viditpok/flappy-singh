package com.example.s1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


import android.content.Intent;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ConfigScreen extends AppCompatActivity {
    private String selectedSprite;
    private String selectedDifficulty;
    private ArrayList<String> difficulties;
    //private ArrayList<String> sprites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);
        ImageView imageView = findViewById(R.id.image_view);
        Spinner spinnerSprites = findViewById(R.id.spinner_sprites);
        ArrayAdapter<CharSequence> spriteAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.Sprites,
                android.R.layout.simple_spinner_item
        );
        spriteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSprites.setAdapter(spriteAdapter);
        ArrayList<String> sprites = new ArrayList<String>() {
            {
                add("https://toppng.com/uploads/preview/mj-crying-face-png-black-and-white-download-lebron-crying-face-funny-humor-meme-basketball-cavs-11562892795iafxsdm5j8.png");
                add("https://icon2.cleanpng.com/20180416/zcq/kisspng-ms-pac-man-ghosts-video-game-pac-man-5ad4ef0589c5e7.5471043515239042615643.jpg");
                add("https://www.seekpng.com/png/detail/569-5690387_sprite-can-png-clipart-sprite-fizzy-drinks-lemon.png");
            }
        };

        spinnerSprites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle the selected option here
                selectedSprite = parent.getItemAtPosition(position).toString();
                // Update your UI or perform some action based on the selected sprite
                if (selectedSprite.equals("Sprite1")) {
                    Glide.with(ConfigScreen.this)
                            .load(sprites.get(0))
                            .into(imageView);
                    selectedSprite = sprites.get(0);
                } else if (selectedSprite.equals("Sprite2")) {
                    Glide.with(ConfigScreen.this)
                            .load(sprites.get(1))
                            .into(imageView);
                    selectedSprite = sprites.get(1);
                } else if (selectedSprite.equals("Sprite3")) {
                    Glide.with(ConfigScreen.this)
                            .load(sprites.get(2))
                            .into(imageView);
                    selectedSprite = sprites.get(2);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Handle user enter difficulty dropdown
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        difficulties = new ArrayList<>();
        difficulties.add("Easy");
        difficulties.add("Medium");
        difficulties.add("Hard");
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                difficulties
        );
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(difficultyAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDifficulty = difficulties.get(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Handle user enter name
        EditText nameEntry = findViewById(R.id.enterTextName);
        // Handle user click submitButton
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent holdingScreenIntent = new Intent(ConfigScreen.this, HoldingScreen.class);
                String name = nameEntry.getText().toString();
                if (name == null || name.trim().isEmpty()) {
                    Toast.makeText(
                            ConfigScreen.this,
                            "Must supply non-empty/whitespace name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                holdingScreenIntent.putExtra("name", name);
                holdingScreenIntent.putExtra("difficulty", selectedDifficulty);
                holdingScreenIntent.putExtra("spriteURL", selectedSprite);
                checkValidDifficulty(selectedDifficulty);
                checkSprite(selectedSprite);
                checkNotEmptyName(name);
                checkNameExists(name);
                startActivity(holdingScreenIntent);
                finish();
            }
        });
    }
    public boolean checkValidDifficulty(String diff) {
        return diff.equals("Easy") || diff.equals("Medium") || diff.equals("Hard");
    }
    public boolean checkNotEmptyName(String name) {
        boolean validName = !(name.equals(""));
        return validName;
    }
    public boolean checkNameExists(String name) {
        boolean validName = !(name == null);
        return validName;
    }
    public boolean checkSprite(String url) {
        boolean validSprite = url.substring(0, 5).equals("https");
        return validSprite;
    }
}