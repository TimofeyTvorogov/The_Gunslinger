package com.example.gunslinger;

import android.content.Context;
import android.content.res.Resources;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameMap extends SurfaceView implements SurfaceHolder.Callback {
    //Переменные для рисования
    float x1, y1; //текущее положение картинки
    float x2, y2; //смещение координат
    float touchX, touchY; //точки касания

    Resources res;
    DrawMap drawMap;
    DrawThread drawThread;

    Roland roland;

    public GameMap(Context context) {
        super(context);
        getHolder().addCallback(this);
        res = getResources();
        drawMap = new DrawMap(res);
        roland = new Roland(BitmapFactory.decodeResource(res,R.drawable.roland_single),
                this, drawMap.spawnX,drawMap.spawnY);




    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touchX = event.getX();
            touchY = event.getY();
        }


        return true;
    }


    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        drawMap.draw(canvas);
        roland.draw(canvas);
        roland.moveTo(touchX);



    }




    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(this, getHolder());
        drawThread.setRun(true);
        drawThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean stop = true;
        drawThread.setRun(false);
        while(stop) {
            try {
                drawThread.join();
                stop = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

