package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Crate extends GameObject {

    public Crate(Bitmap image) {
        super(image);
        hitbox = new Rect(x,y,width,height);
        moveVelocity = 3;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y,paint);
        //moveX();
        fall();
        hitbox.set(x,y,width,height);
    }

    @Override
    public void fall() {
        if (falling) {
            y += fallVelocity;

        }
        else {
            falling = false;


        }
    }

   @Override
   public void moveX() {
       //if (collision&&roland.x ==width){
       //    x-=moveVelocity;
       //}
       //else if (collision&&roland.x ==width){x+=moveVelocity;}
   }


}
