package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Roland {
    GameMap gameMap;
    Bitmap image; //спрайты
    int x, y; //координаты кадра
    float speedX = 5,
          speedY = 5; //скорость перемещения

    int height, width; //ширина и высота кадра
    Paint paint = new Paint();
    final int IMAGE_ROWS = 1;
    final int IMAGE_COLUMNS = 8;
    int currentFrame = 0;
    int direction = 4;



    public Roland(Bitmap image, GameMap gameMap, int startX, int startY){
        this.gameMap = gameMap;
        this.image = image;
        x = startX;
        y = startY;
        //width = this.image.getWidth() / IMAGE_COLUMNS;
        //height = this.image.getHeight() / IMAGE_ROWS;
    }
    public void spawn(DrawMap drawMap) {
        x = drawMap.spawnX;
        y = drawMap.spawnY;
    }



    public void moveTo(float touchX){
        x = (int) touchX;
//было здесь
        //currentFrame = ++currentFrame%IMAGE_COLUMNS;
    }



    public void draw(Canvas canvas) {

        //Rect src = new Rect(currentFrame*width, direction*height,
        //        currentFrame*width+width, direction*height+height);
        //Rect dst = new Rect(x, y, x+width, y+width);
        //canvas.drawBitmap(image, src, dst, paint);
        //currentFrame = ++currentFrame%IMAGE_COLUMNS;
        canvas.drawBitmap(image,x,y,paint);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
