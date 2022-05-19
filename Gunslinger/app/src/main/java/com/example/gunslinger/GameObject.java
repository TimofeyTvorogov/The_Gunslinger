package com.example.gunslinger;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
public abstract class GameObject {
    //переменные для рисования
    Bitmap image;
    Paint paint = new Paint();
    DrawMap drawMap;
    Resources res;

    //переменные для передвижения и взаимодействия
    Rect hitbox;
    int fallingVelocity = 8,
    movingVelocity,
    x, y, //координаты кадра
    height, width, //ширина и высота объекта
    row,column;


    public GameObject(DrawMap drawMap, Resources res, int x, int y){
        this.drawMap = drawMap;
        this.x = x;
        this.y = y;
        this.res = res;
        width = image.getWidth();
        height = image.getHeight();

    }



    public boolean isFalling(){
        if(drawMap.mapArray[row][column].equals("e")||
           drawMap.mapArray[row][column].equals("|")||
           //drawMap.mapArray[row][column].equals("s")||
           drawMap.mapArray[row][column].equals("l")||
           drawMap.mapArray[row][column].equals("c"))
            return true;

        else return false;
    }
    public boolean isCollision(Rect foreignHitbox){return foreignHitbox.contains(hitbox);}

//не лезь сюда, тут всё ок
    public abstract void draw(Canvas canvas);
    public void fall(){};
    public void moveX(){};
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

}
