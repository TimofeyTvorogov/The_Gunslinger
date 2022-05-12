package com.example.gunslinger;

import android.content.Context;
import android.content.res.Resources;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameMap extends SurfaceView implements SurfaceHolder.Callback {
    //Переменные для рисования
    float x1, y1, //текущее положение картинки
        // x2, y2, //смещение координат
            touchX, touchY; //точки касания
    int rolRow,rolColumn,crateRow,crateColumn;
    Resources res;
    DrawMap drawMap;
    DrawThread drawThread;
    boolean isSpawned = false;
    Roland roland;
    Crate crate;


    public GameMap(Context context) {
        super(context);
        getHolder().addCallback(this);
        res = getResources();
        drawMap = new DrawMap(res);
        roland = new Roland(BitmapFactory.decodeResource(res,R.drawable.roland_single_32));
       crate = new Crate(BitmapFactory.decodeResource(res,R.drawable.crate_32));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                roland.setTargetX(touchX); break;
            case MotionEvent.ACTION_UP:
                if (!roland.falling){
                roland.jumping = true;
                roland.jumpCounter = 0;
                }
                break;

        }

        return true;
    }
    public void checkFalling(){
                //row = Math.round((roland.y + roland.height) / 48);
        rolRow = Math.round((roland.hitbox.top + roland.hitbox.bottom) / 48);
        //if (roland.tX>roland.x)
                //column = Math.round(roland.x/48);
        rolColumn = Math.round(roland.hitbox.left/48);
        //else column = Math.round(roland.width/48);
        crateRow = Math.round((crate.hitbox.top + crate.hitbox.bottom) / 48);
        crateColumn = Math.round(crate.hitbox.left/48);
        if (drawMap.mapArray[rolRow][rolColumn].equals("e")||drawMap.mapArray[rolRow][rolColumn].equals("|")||drawMap.mapArray[rolRow][rolColumn].equals("c"))
         roland.falling = true;
        else roland.falling = false;
        if (drawMap.mapArray[crateRow][crateColumn].equals("e")||drawMap.mapArray[crateRow][crateColumn].equals("|")||drawMap.mapArray[crateRow][crateColumn].equals("c"))
            crate.falling =  true;
        else crate.falling = false;


    }


    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        drawMap.draw(canvas);
        if (!isSpawned) {
            roland.setX(drawMap.spawnRX);
            roland.setY(drawMap.spawnRY);
            crate.setX(drawMap.spawnCX);
            crate.setY(drawMap.spawnRY);
            isSpawned = true;
        }
        roland.draw(canvas);
        crate.draw(canvas);
        checkFalling();


        //Log.d("rol cord", String.format("%d, %d, %d, %d",roland.x
        //,roland.y, roland.width, roland.height));
        //Log.d("hitbox cord", String.format("%d, %d, %d, %d",roland.hitbox.left
        //,roland.hitbox.top, roland.hitbox.right, roland.hitbox.bottom));

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