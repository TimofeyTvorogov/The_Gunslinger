package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DrawThread extends Thread {

    Bitmap bitmap;
    MySurface mySurface;
    SurfaceHolder surfaceHolder;
    boolean isRun = false;

    public DrawThread(MySurface mySurface, SurfaceHolder surfaceHolder) {
        this.mySurface = mySurface;
        this.surfaceHolder = surfaceHolder;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void run() {
        Canvas canvas;
        canvas = surfaceHolder.lockCanvas();



    }
}
