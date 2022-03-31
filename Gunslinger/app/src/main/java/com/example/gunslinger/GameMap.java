package com.example.gunslinger;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameMap {

    int sizeTexture = 32;
    Bitmap texture;
    SurfaceHolder surfaceHolder;



    public GameMap(Resources resources) {
        texture = BitmapFactory.decodeResource(resources, R.drawable.block);
    }

    public void draw(Canvas canvas){
        canvas = surfaceHolder.lockCanvas();

        canvas.drawBitmap(texture, 100, 100, null);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }


}

