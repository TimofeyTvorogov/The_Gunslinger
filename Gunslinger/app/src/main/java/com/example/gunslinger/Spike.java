package com.example.gunslinger;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Spike extends GameObject   {

    Roland roland;

    public Spike(DrawMap drawMap, Resources res, int x, int y, Roland roland){
        super(drawMap,res,x,y);
        this.roland = roland;
        image = BitmapFactory.decodeResource(res, R.drawable.spike_16);
        hitbox = new Rect(x+15,y+12,width-15,height-9);

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y, paint);
        hitbox.set(x+15,y+12,width-15,height-9);

    }
    //TODO как реализовывать смерть

}