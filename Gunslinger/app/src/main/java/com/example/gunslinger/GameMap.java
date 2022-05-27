package com.example.gunslinger;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;

public class GameMap extends SurfaceView implements SurfaceHolder.Callback {
    //Переменные для рисования
    float x1, y1, //текущее положение картинки
    // x2, y2, //смещение координат
    touchX, touchY; //точки касания
    int levelPath;
    Resources res;
    DrawMap drawMap;
    DrawThread drawThread;
    Roland roland;
    ArrayList<Spike> listOfSpikes = new ArrayList<>();
    ArrayList<Crate> listOfCrates = new ArrayList<>();
    ArrayList<Lever> listOfLevers = new ArrayList<>();
    DoubleTapGuester doubleTapGuester = new DoubleTapGuester(this);
    public GameMap(Context context, int levelPath) {
        super(context);
        getHolder().addCallback(this);
        this.levelPath = levelPath;
        res = getResources();
        drawMap = new DrawMap(res,levelPath);
        for (CoordClass coordItem: drawMap.coordList) {
            switch (coordItem.className){
                case "Spike": listOfSpikes.add(new Spike(this,res,coordItem.xyCoords.first*48,coordItem.xyCoords.second*48));break;
                case "Crate":listOfCrates.add(new Crate(this,res,coordItem.xyCoords.first*48,coordItem.xyCoords.second*48)); break;
                case "Lever":listOfLevers.add(new Lever(this,res,coordItem.xyCoords.first*48,coordItem.xyCoords.second*48));break;
                case"Roland": roland = new Roland(this,res,coordItem.xyCoords.first*48,coordItem.xyCoords.second*48);break;
            }
        }


//       GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
//           @Override
//           public boolean onDoubleTap(MotionEvent e) {
//               // code here
//               return super.onDoubleTap(e);
//           }
//
//           @Override
//           public boolean onSingleTapConfirmed(MotionEvent e) {
//               // code here
//               return super.onSingleTapConfirmed(e);
//           }
//
//       });
//
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        //todo add gestureListener, longClickListener
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                if (touchX<roland.x) roland.currentImage = roland.imageMap.get("Right");
              else roland.currentImage = roland.imageMap.get("Left");
                if (!roland.isJumping)
                    roland.setTargetX(touchX);

         // for (Lever lever:listOfLevers) {
         //     if (lever.hitbox.contains((int)touchX, (int)touchY)&&lever.isClickable) {
         //         lever.changeOnOff();
         //     }



         // }
            break;
            //case MotionEvent.ACTION_UP:
            //    if (!roland.isFalling()){
            //        roland.isJumping = true;
            //        roland.jumpingCounter = 0;
            //    }
            //    break;
        }
        GestureDetector gestureDetector = new GestureDetector(new DoubleTapGuester(this));
        gestureDetector.onTouchEvent(event);
        return true;
    }


    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        drawMap.draw(canvas);

        for (Spike spike: listOfSpikes) {
            spike.draw(canvas);
            roland.checkDeath(spike);
        }
        for (Crate crate:listOfCrates){
            if (crate.isCollision(roland.x,roland.x+roland.width-16,roland.y+roland.height/2)){
                roland.movingVelocity = crate.movingVelocity;
            }
            else roland.movingVelocity = 16;
            roland.checkCrateVerCol(crate);
            crate.draw(canvas);
        }
        for (Lever lever:listOfLevers) {
            lever.draw(canvas);
        }
        roland.draw(canvas);
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