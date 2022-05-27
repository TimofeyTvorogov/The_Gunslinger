package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    AudioPlayer audioPlayer;
    GameMap gameMap;
    int recievePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recievePath = getIntent().getIntExtra("LEVEL_NUM", -1);
        // FIXME: 24.05.22
        gameMap = new GameMap(this,recievePath);
        setContentView(gameMap);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        audioPlayer = new AudioPlayer(this, 2);
        audioPlayer.play();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
        finish();
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

}