package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class Roland  {
    Bitmap image; //спрайты
    Paint paint = new Paint();

    final int IMAGE_ROWS = 1;
    final int IMAGE_COLUMNS = 8;
    int currentFrame = 0;
    int direction = 4;

    //переменные для передвижения и взаимодействия
    int x, y; //координаты кадра
    int moveVelocity;
    int targetX;
    boolean jumping = false;
    boolean falling = false;
    int height, width; //ширина и высота персонажа
    int fallVelocity = 8;
    int jumpVelocity = 6;
    int jumpBorder = 72; // высота прыжка (потолок прыжка)
    int jumpCounter = 0; //счетчик текущей высоты прыжка
    Rect hitbox;

    public Roland(Bitmap image){
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
        y = 0;
        hitbox = new Rect(x+24,y+9,width-30,height-3);

//width = this.image.getWidth() / IMAGE_COLUMNS;
//height = this.image.getHeight() / IMAGE_ROWS;
    }
    public void setTargetX(float touchX) {
        targetX = (int) touchX;
        moveVelocity = 7;

    }
    public void jump(){

        if (jumping && jumpCounter < jumpBorder) {
            y -= jumpVelocity;
            jumpCounter += jumpVelocity;
        }
        else {
            jumping = false;

        }
    }

    public void fall(){
        if (falling && !jumping) {
            y += fallVelocity;

        }
        else {
            falling = false;


        }
    }

    public void moveX(){
        if (targetX >x){
            if (Math.abs(x - targetX)-width> 5) {
                x += moveVelocity;
            }
        }
        else if (targetX <x){
            if (Math.abs(x - targetX)> 5) {
                x -= moveVelocity;
            }
        }

        else moveVelocity = 0;

//было здесь
//currentFrame = ++currentFrame%IMAGE_COLUMNS;
    }

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
        hitbox.set(x+24,y+9,width-30,height-3);


    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}