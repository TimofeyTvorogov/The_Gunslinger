package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long buttonPressedTime;
    Toast toastToExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        buttonPressedTime = System.currentTimeMillis();
        if (buttonPressedTime + 2000 > System.currentTimeMillis()){
            toastToExit.cancel();
            super.onBackPressed();
            return;
            //TODO вылетает. Спросить
        } else {
            toastToExit = Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти.", Toast.LENGTH_SHORT);
            toastToExit.show();
        }
    }
}