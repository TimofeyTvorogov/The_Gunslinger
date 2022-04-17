package com.example.gunslinger;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


import android.graphics.Color;
import android.graphics.Paint;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class DrawMap {
Resources resources;
Bitmap innerBrick, upBrick, downBrick;
String[][] mapArray;

int textureWidth, textureHeight;
boolean generatedFirst = true;
int spawnX, spawnY;
Paint paint = new Paint();






public DrawMap(Resources resources) {
    this.resources = resources;
    innerBrick = BitmapFactory.decodeResource(resources, R.drawable.inner_brick_16);
    upBrick = BitmapFactory.decodeResource(resources, R.drawable.up_brick_16);
    downBrick = BitmapFactory.decodeResource(resources, R.drawable.down_brick_16);
    textureHeight = innerBrick.getHeight();
    textureWidth = innerBrick.getWidth();




}
//генерирует строковый массив из файла
    public String[][] generateMapArray(Canvas canvas) {
        String [][] mapArray = new String[canvas.getHeight()/textureHeight+1][canvas.getWidth()/textureWidth+1];

        InputStream path = resources.openRawResource(R.raw.map_true);
        InputStreamReader isr = new InputStreamReader(path);
        BufferedReader br = new BufferedReader(isr);

        String info;
        int i = 0;
        while (true) {
            try {
                if ((info = br.readLine()) == null) break;

                for (int j = 0; j < info.length(); j++) {
                    mapArray[i][j] = String.valueOf(info.charAt(j));
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
           try {
            br.close();
        }
          catch (IOException e) {
            e.printStackTrace();
        }
    return mapArray;
    }



    public void draw(Canvas canvas) {
        //рисуем задний фон
        canvas.drawColor(Color.DKGRAY);
        drawTextures(canvas);
    }

    public void drawTextures(Canvas canvas) {
    if (generatedFirst){
        mapArray = generateMapArray(canvas);
        generatedFirst = false;
    }
        for (int y = 0; y < mapArray.length; y++) {
            for (int x = 0; x < mapArray[y].length; x++) {
                switch (mapArray[y][x]){
                    case "i" : canvas.drawBitmap(innerBrick,x*textureWidth,y*textureHeight, null);break;
                    case "u" : canvas.drawBitmap(upBrick,x*textureWidth,y*textureHeight, paint);break;
                    case "d" : canvas.drawBitmap(downBrick,x*textureWidth,y*textureHeight, paint);break;
                    case "|" : spawnX = x*textureWidth+(new Random().nextInt( 1)); spawnY = y*textureHeight;break;

                }


            }
        }
    }



}

