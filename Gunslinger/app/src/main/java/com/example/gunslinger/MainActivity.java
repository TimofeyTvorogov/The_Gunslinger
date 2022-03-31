package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
TextView tV;
Button butt;

Canvas canvas;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
butt = findViewById(R.id.butt);
tV = findViewById(R.id.tV);


        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



generateMap(generateMapArray());

    }


    public String[][] generateMapArray() {
        String [][] mapArray = new String[5][5];
                //размер пока что фиксированный,от разрешения экрана



       Resources resources = getResources();

       InputStream path = resources.openRawResource(R.raw.map_true);

       InputStreamReader isr = new InputStreamReader(path);

       BufferedReader br = new BufferedReader(isr);

       String info = "";
       int i = 0;

       while (true) {
           try {   if ((info = br.readLine()) == null) break;
               for (int j = 0; j < info.length(); j++) {
                   mapArray[i][j] = String.valueOf(info.charAt(j));

               }
               i++;
           }
           catch (IOException e) {
               e.printStackTrace();
           }
      }
       try {
           br.close();
       }
       catch (IOException e) {
           e.printStackTrace();
       }

       return mapArray;
   }

   public void generateMap(String[][] mapArr) {
       int x = 0;
       int y = 0;
       int sizeTexture = 32;
       Bitmap texture = BitmapFactory.decodeResource(getResources(), R.drawable.block);
       for (int i = 0; i < mapArr.length; i++,x++) {
           for (int j = 0; j < mapArr[i].length; j++,y++) {
               switch (mapArr[i][j]){
                   case "t" : canvas.drawBitmap(texture,x*sizeTexture,y*sizeTexture, null);
                 case "":
               }

           }

        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
        finish();
    }




}