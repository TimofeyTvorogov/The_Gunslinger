package com.example.gunslinger;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Roland extends GameObject  {
    final int IMAGE_ROWS = 1;
    final int IMAGE_COLUMNS = 8;
    int currentFrame = 0;
    int direction = 4;

    //переменные для передвижения и взаимодействия
    boolean isJumping = false,
    isDead = false;
    int targetX,
    jumpingVelocity = 6,
    jumpingBorder = 72, // высота прыжка (потолок прыжка)
    jumpingCounter = 0; //счетчик текущей высоты прыжка

    public Roland(DrawMap drawMap, Resources res, int x, int y){
        super(drawMap,res,x,y);
        image = BitmapFactory.decodeResource(res, R.drawable.roland_single_32);
        hitbox = new Rect(x+24,y+9,width-30,height);
        row = Math.round(hitbox.bottom/48);
        column = Math.round((hitbox.left+(hitbox.right-hitbox.left)/2)/48);
//width = this.image.getWidth() / IMAGE_COLUMNS;
//height = this.image.getHeight() / IMAGE_ROWS;
    }

    public void jump(){
        if (isJumping && jumpingCounter < jumpingBorder) {
            y -= jumpingVelocity;
            jumpingCounter += jumpingVelocity;
        }
        else {
            isJumping = false;
        }
    }
    //public boolean isJumping(){return isJumping;}

    @Override
    public void fall(){
        if (isFalling() && !isJumping) {
            y += fallingVelocity;
        }
        //else {
        //    falling = false;
        //}
    }
    public void setTargetX(float touchX) {
        targetX = (int) touchX;
        movingVelocity = 7;
    }
    @Override
    public void moveX(){
        if (targetX >x) {
            if (Math.abs(x - targetX)-width> 5)
                x += movingVelocity;
        }

        else if (targetX <x){
            if (Math.abs(x - targetX)> 5)
                x -= movingVelocity;
        }

        else movingVelocity = 0;
//было здесь
//currentFrame = ++currentFrame%IMAGE_COLUMNS;
    }
    @Override
    public void draw(Canvas canvas) {
//Rect src = new Rect(currentFrame*width, direction*height,
// currentFrame*width+width, direction*height+height);
//Rect dst = new Rect(x, y, x+width, y+width);
//canvas.drawBitmap(image, src, dst, paint);
//currentFrame = ++currentFrame%IMAGE_COLUMNS;
        canvas.drawBitmap(image,x,y,paint);
        moveX();
        jump();
        fall();
        hitbox.set(x+24,y+9,width-30,height);
        row = Math.round(hitbox.bottom/48);
        column = Math.round((hitbox.left+(hitbox.right-hitbox.left)/2)/48);

    }
}
//TODO спрайтовая анимация или две картинки (если нет времени)
