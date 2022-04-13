package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprites {
    GameMap gameMap;
    Bitmap image; //спрайты
    int x, y; //координаты кадра
    float dx = 0, dy = 0; //скорость перемещения
    int height, width; //ширина и высота кадра
    Paint paint = new Paint();
    final int IMAGE_ROWS = 8;
    final int IMAGE_COLUMNS = 10;
    int currentFrame = 0;
    int direction = 4;

    public Sprites(Bitmap image, GameMap gameMap, int x, int y){
        this.gameMap = gameMap;
        this.image = image;
        width = this.image.getWidth() / IMAGE_COLUMNS;
        height = this.image.getHeight() / IMAGE_ROWS;
        this.x = x;
        this.y = y;
    }

    public void controlRoute(){
        if (x < 0 || x > gameMap.getWidth() - image.getWidth()){
            dx = -dx;
        }
        if (y < 0 || y > gameMap.getHeight() - image.getHeight()){
            dy = -dy;
        }
        x += dx;
        y += dy;
        currentFrame = ++currentFrame%IMAGE_COLUMNS;
    }

    public void setSpeed(float sX, float sY){
        dx = sX;
        dy = sY;
    }

    public void draw(Canvas canvas) {
        controlRoute();
        Rect src = new Rect(currentFrame*width, direction*height,
                currentFrame*width+width, direction*height+height);
        Rect dst = new Rect(x, y, x+width, y+width);
        canvas.drawBitmap(image, src, dst, paint);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
