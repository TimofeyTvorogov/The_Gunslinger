package com.example.gunslinger;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class DoubleTapGuester extends GestureDetector.SimpleOnGestureListener {
    GameMap gameMap;

public DoubleTapGuester(GameMap gameMap){
    this.gameMap = gameMap;

}
    @Override
    public boolean onDoubleTap(MotionEvent e) {

        if (!gameMap.roland.isFalling()){
            gameMap.roland.isJumping = true;
           gameMap.roland.jumpingCounter = 0;
        }
        return super.onDoubleTap(e);
    }

  @Override
  public void onLongPress(MotionEvent e) {
      for (Lever lever:gameMap.listOfLevers) {
          if (lever.hitbox.contains((int)gameMap.touchX, (int)gameMap.touchY)&&lever.isClickable) {
              lever.changeOnOff();
          }



      }
      super.onLongPress(e);

  }
}
