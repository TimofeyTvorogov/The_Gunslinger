package com.example.gunslinger;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Map;
public class GameMap extends SurfaceView implements SurfaceHolder.Callback {
    //Переменные для рисования
    float x1, y1, //текущее положение картинки
    // x2, y2, //смещение координат
    touchX, touchY; //точки касания
    Resources res;
    DrawMap drawMap;
    DrawThread drawThread;
    Roland roland;
    ArrayList<Spike> listOfSpikes = new ArrayList<>();
    ArrayList<Crate> listOfCrates = new ArrayList<>();
    ArrayList<Lever> listOfLevers = new ArrayList<>();
    public GameMap(Context context) {
        super(context);
        getHolder().addCallback(this);
        res = getResources();
        drawMap = new DrawMap(res);
        for(Map.Entry<String, Pair<Integer, Integer>> entry: drawMap.coordInform.entrySet()){
            switch (entry.getKey()){
                //todo роланд почему-то null
                case "Roland": roland = new Roland(drawMap,res, entry.getValue().first*48,entry.getValue().second*48);break;
                case "Lever": listOfLevers.add(new Lever(drawMap,res,entry.getValue().first*48,entry.getValue().second*48,roland)); break;
                case "Spike": break;
                case "Crate": listOfCrates.add(new Crate(drawMap,res,entry.getValue().first*48,entry.getValue().second*48,roland)); break;
            }
        }
        roland = new Roland(drawMap,res,10*48,11*48);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                roland.setTargetX(touchX);
            for (Lever lever:listOfLevers) {
                if (lever.hitbox.contains((int)touchX, (int)touchY)) {
                    lever.changeOnOff();
                }
            }
            break;
            case MotionEvent.ACTION_UP:
                if (!roland.isFalling()){
                    roland.isJumping = true;
                    roland.jumpingCounter = 0;
                }
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        drawMap.draw(canvas);
        roland.draw(canvas);
        for (Spike spike: listOfSpikes) {
            spike.draw(canvas);
        }
        for (Lever lever:listOfLevers) {
            lever.draw(canvas);
        }
        for (Crate crate:listOfCrates){
            crate.draw(canvas);
        }
        //crate.draw(canvas);
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