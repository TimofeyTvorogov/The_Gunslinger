package com.example.gunslinger;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.HashMap;

public class Lever extends GameObject {
    Roland roland;
    HashMap<String,Bitmap> imageMap = new HashMap<>();
    Bitmap currentImage;
    boolean isOn = false,
    isClickable = false;
    public Lever(DrawMap drawMap, Resources res,int x, int y,Roland roland) {
        super(drawMap,res,x,y);
        this.roland = roland;
        imageMap.put("L0",BitmapFactory.decodeResource(res,R.drawable.lever_l0_32) );
        imageMap.put("L1",BitmapFactory.decodeResource(res,R.drawable.lever_l1_32) );
        imageMap.put("R0",BitmapFactory.decodeResource(res,R.drawable.lever_r0_32) );
        imageMap.put("R1",BitmapFactory.decodeResource(res,R.drawable.lever_r1_32) );
        currentImage = imageMap.get("L0");
        hitbox = new Rect(x,y+12,width,height);
    }


    void changeOnOff(){
        isOn = !isOn;
    }
    public void changeActivity(){
        if (isCollision(roland.hitbox)) {
            isClickable = true;
            if (isOn) currentImage = imageMap.get("R1");
            else currentImage = imageMap.get("L1");
        }
        else{
            isClickable = false;
            if (isOn) currentImage = imageMap.get("R0");
            else currentImage = imageMap.get("L0");

        }
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage,x,y,paint);
        //changeMode
        changeActivity();
    }


}
/*TODO метод изменения переменной рычага (onClick?)
сделал,но не уверен
 */