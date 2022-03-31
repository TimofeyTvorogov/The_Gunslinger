package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
   TextView tV;
   Button butt;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawMap(getResources(),this));
    butt = findViewById(R.id.butt);
    tV = findViewById(R.id.tV);


    Window window = getWindow();
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);




}





    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
        finish();
}




}