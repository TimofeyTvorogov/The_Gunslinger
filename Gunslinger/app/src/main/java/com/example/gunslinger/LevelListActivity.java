package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LevelListActivity extends AppCompatActivity {

    ImageButton btnToMainMenu, btnLevelFirst,btnLevelSecond;
    AudioPlayer audioPlayer;
    Resources resources;
    int putPath;
    Intent toMMIntent;
    Intent chooseLevelIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lever_list);

        btnToMainMenu = findViewById(R.id.buttonToMainMenu);
        btnLevelFirst = findViewById(R.id.one);
        btnLevelSecond = findViewById(R.id.two);
        resources = getResources();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMMIntent = new Intent(LevelListActivity.this, MainMenu.class);
                chooseLevelIntent = new Intent(LevelListActivity.this, MainActivity.class);
                switch (view.getId()){
                    case R.id.buttonToMainMenu:
                        startActivity(toMMIntent); finish(); audioPlayer.stop(); break;
                    case R.id.one:
                        putPath = R.raw.level_one; break;
                    case R.id.two: putPath = R.raw.level_two; break;
                }
                chooseLevelIntent.putExtra("LEVEL_NUM",putPath);
                startActivity(chooseLevelIntent); finish(); audioPlayer.stop();
            }

        };

        btnLevelFirst.setOnClickListener(onClickListener);
        btnToMainMenu.setOnClickListener(onClickListener);
        btnLevelSecond.setOnClickListener(onClickListener);


        audioPlayer = new AudioPlayer(this, 1);
        audioPlayer.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        audioPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        audioPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        audioPlayer.play();
    }

//    public void buttonClickMap (View view){
//        int id = view.getId();
//        switch (id){
//            case R.id.one:putPath = R.raw.level_one;  break;
//            case R.id.two: putPath = R.raw.level_two; break;
//            case R.id.three: break;
//            case R.id.four: break;
//            case R.id.five: break;
//            case R.id.six: break;
//        }
//
//
//
//    }
}