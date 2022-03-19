package com.example.gunslinger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MySurface extends SurfaceView implements SurfaceHolder.Callback {

    int sizeTexture = 32;
    GameMap gameMap;
    Bitmap texture;
    Canvas canvas;


    public MySurface(Context context) {
        super(context);
        getHolder().addCallback(this);


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);;

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        /*try {
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(getContext().
                            openFileInput("map_true")));
            String str = "";
            while ((str = bufferedReader.readLine()) != null){
                canvas.drawBitmap(texture, 5,5,null);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*Resources res = getResources();
        InputStream path = res.openRawResource(R.raw.map_true);
        InputStreamReader inputStreamReader = new InputStreamReader(path);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = "";
        while (true) {
            try {
                if ((str = bufferedReader.readLine()) != null){
                    if ((str = bufferedReader.readLine()) == "t"){
                        canvas.drawBitmap(texture,5,5,null);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
