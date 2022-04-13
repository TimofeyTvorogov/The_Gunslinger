package com.example.gunslinger;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


import android.graphics.Color;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class DrawMap {
Resources resources;
Bitmap cobbleStone, brick, water;
String[][] mapArray;
//ArrayList<Bitmap> textureArrayList;
int textureWidth, textureHeight;
boolean generatedFirst = false;






public DrawMap(Resources resources) {
    this.resources = resources;
    cobbleStone = BitmapFactory.decodeResource(resources, R.drawable.block);
    brick = BitmapFactory.decodeResource(resources, R.drawable.brick);
    water = BitmapFactory.decodeResource(resources, R.drawable.water);
    textureHeight = cobbleStone.getHeight();
    textureWidth = cobbleStone.getWidth();




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
    if (generatedFirst == false){
        mapArray = generateMapArray(canvas);
        generatedFirst = true;
    }
        for (int y = 0; y < mapArray.length; y++) {
            for (int x = 0; x < mapArray[y].length; x++) {
                switch (mapArray[y][x]){
                    case "t" : canvas.drawBitmap(cobbleStone,x*textureWidth,y*textureHeight, null);break;
                    case "b" : canvas.drawBitmap(brick,x*textureWidth,y*textureHeight, null);break;
                    case "w" : canvas.drawBitmap(water,x*textureWidth,y*textureHeight, null);break;
                }


            }
        }
    }



}

