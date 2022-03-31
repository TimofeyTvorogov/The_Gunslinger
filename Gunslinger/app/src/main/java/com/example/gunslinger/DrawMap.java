package com.example.gunslinger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DrawMap extends View {
Resources resources;
Bitmap cobbleStone;
Bitmap brick;
Bitmap water;



public DrawMap(Resources resources, Context context) {
    super(context);
    this.resources = resources;
    cobbleStone = BitmapFactory.decodeResource(resources, R.drawable.block);
    brick = BitmapFactory.decodeResource(resources, R.drawable.brick);
    water = BitmapFactory.decodeResource(resources, R.drawable.water);
    //TODO затолкать в список битмапов

}
//генерирует строковый массив из файла
    public String[][] generateMapArray() {
        String [][] mapArray = new String[12][24];
        //размер пока что фиксированный,от разрешения экрана
        InputStream path = resources.openRawResource(R.raw.map_true);
        InputStreamReader isr = new InputStreamReader(path);
        BufferedReader br = new BufferedReader(isr);

        String info = "";
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

    //отрисовка карты
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //забираем массив из функции
        String [][] mapArr = generateMapArray();

        for (int y = 0; y < mapArr.length; y++) {
            for (int x = 0; x < mapArr[y].length; x++) {
               if (mapArr[y][x].equals("t")) {
                   canvas.drawBitmap(cobbleStone, x*cobbleStone.getWidth(), y*cobbleStone.getHeight(),null);
               }
               if (mapArr[y][x].equals("b")) {
                   canvas.drawBitmap(brick, x*brick.getWidth(), y*brick.getHeight(),null);
               }
               if (mapArr[y][x].equals("w")) {
                   canvas.drawBitmap(water, x*water.getWidth(), y*water.getHeight(),null);
               }

// тут хз чё делать, оно кроме свитч кейс ломает и цикл               switch (mapArr[y][x]){
//                   case "t" : canvas.drawBitmap(cobbleStone,x*cobbleStone.getWidth(),y*getHeight(), null);break;
//                   case "b" : canvas.drawBitmap(brick,x*getWidth(),y*getHeight(), null);break
//                   case "w" : canvas.drawBitmap(water,x*getWidth(),y*getHeight(), null);break;
//              }


            }
        }
    }
}

