package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    ImageButton exitButton, playButton;
    Button noDialog_btn, yesDialog_btn;
    private long buttonPressedTime;
    private Toast toastToExit;
    Dialog dialogWindow;
    AudioPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        exitButton = findViewById(R.id.button_for_exit);
        playButton = findViewById(R.id.button_for_play);

        dialogWindow = new Dialog(this);
        dialogWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWindow.setContentView(R.layout.dialog_preview);
        dialogWindow.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogWindow.setCancelable(false);

        noDialog_btn = dialogWindow.findViewById(R.id.dialog_window_no_button);
        yesDialog_btn = dialogWindow.findViewById(R.id.dialog_window_yes_btn);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_for_exit:
                        dialogWindow.show(); break;
                    case R.id.button_for_play:
                        Intent intent = new Intent(MainMenu.this, LevelListActivity.class);
                        startActivity(intent); finish(); audioPlayer.stop(); break;
                    case R.id.dialog_window_yes_btn:
                        MainMenu.super.onBackPressed(); break;
                    case R.id.dialog_window_no_button:
                        dialogWindow.dismiss(); break;
                }
            }
        };

        exitButton.setOnClickListener(onClickListener);
        playButton.setOnClickListener(onClickListener);
        noDialog_btn.setOnClickListener(onClickListener);
        yesDialog_btn.setOnClickListener(onClickListener);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        audioPlayer = new AudioPlayer(this, 0);
        audioPlayer.play();
    }

    @Override
    public void onBackPressed() {
        if (buttonPressedTime + 2000 > System.currentTimeMillis()){
            toastToExit.cancel();
            super.onBackPressed();
            return;
        } else {
            toastToExit = Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти.", Toast.LENGTH_SHORT);
            toastToExit.show();
        }
        buttonPressedTime = System.currentTimeMillis();
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