package com.example.gunslinger;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
public class Crate extends GameObject {
    Roland roland;

    public Crate(DrawMap drawMap, Resources res,int x, int y,Roland roland) {
        super(drawMap,res,x,y);
        this.roland = roland;
        image = BitmapFactory.decodeResource(res, R.drawable.crate_32);
        hitbox = new Rect(x,y,width,height);
        movingVelocity = 3;
        row = Math.round(hitbox.bottom/48);
        column = Math.round(hitbox.left/48);

    }



    @Override
    public void fall() {
        if (isFalling())
        y += fallingVelocity;

       // else {
       //     falling = false;
       // }
    }
    @Override
    public void moveX() {
        if (isCollision(roland.hitbox)){
            if (roland.x<x)
            x+= movingVelocity;
            else
            x-= movingVelocity;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y,paint);
        moveX();
        fall();
        hitbox.set(x,y,width,height);
        row = Math.round(hitbox.bottom/48);
        column = Math.round(hitbox.left/48);
    }

}
//todo нейтрализация шипов