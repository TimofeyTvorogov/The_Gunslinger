package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class GameObject {
    //переменные для рисования
    Bitmap image;
    Paint paint = new Paint();

    //переменные для передвижения и взаимодействия
    Rect hitbox;
    boolean collision = false;
    boolean falling = false;
    int fallVelocity = 8;
    int moveVelocity;
    int x, y, //координаты кадра
    height, width; //ширина и высота объекта


    public GameObject(Bitmap image){
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
        y = 0;
    }
    //todo написать коллизию

    public abstract void draw(Canvas canvas);
    public abstract void fall();
    public abstract void moveX();
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

}
