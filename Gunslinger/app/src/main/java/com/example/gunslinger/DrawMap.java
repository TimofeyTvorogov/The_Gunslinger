package com.example.gunslinger;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Pair;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
public class DrawMap {
    Resources res;
    Bitmap innerBrick, upBrick, downBrick;
    String[][] mapArray;
    int textureWidth, textureHeight;
    boolean generatedFirst = true;
    Paint paint = new Paint();
    //переменные для работы с файлами
    InputStream path;
    InputStreamReader isr;
    BufferedReader br;
    String info;
    HashMap<String, Pair<Integer,Integer>> coordInform =new HashMap<>();
    public DrawMap(Resources res) {
        this.res = res;
        innerBrick = BitmapFactory.decodeResource(res, R.drawable.inner_brick_16);
        upBrick = BitmapFactory.decodeResource(res, R.drawable.up_brick_16);
        downBrick = BitmapFactory.decodeResource(res, R.drawable.down_brick_16);
        textureHeight = innerBrick.getHeight();
        textureWidth = innerBrick.getWidth();
        path = res.openRawResource(R.raw.test_map_23_49);
        isr = new InputStreamReader(path);
        br = new BufferedReader(isr);
        if (generatedFirst) {
            try {
                generateMapArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            generatedFirst = false;
        }
    }
    //рассчитывает длину и высоту массива перед выделением памяти
    private void checkLength() throws IOException {
        int rowCounter = 0;
        int columnCounter = 0;
        boolean hasTaken = false;
        while ((info = br.readLine()) != null) {
//чтобы забрать кол-во колонок лишь один раз
            if (!hasTaken) {
                columnCounter = info.length();
                hasTaken = true;
            }
            rowCounter++;
        }
        br.close();
        mapArray = new String[rowCounter][columnCounter];
    }
    //генерирует строковый массив из файла
    public void generateMapArray() throws IOException {
        checkLength();
        path = res.openRawResource(R.raw.test_map_23_49);
        isr = new InputStreamReader(path);
        br = new BufferedReader(isr);
        int i = 0;
        while ((info = br.readLine()) != null) {
            for (int j = 0; j < info.length(); j++) {
                mapArray[i][j] = String.valueOf(info.charAt(j));
            }
            i++;
        }
        br.close();
    }
    public void draw(Canvas canvas) {
//рисуем задний фон
        canvas.drawBitmap(BitmapFactory.decodeResource(res, R.drawable.level_bg),0,0,paint);
        drawTextures(canvas);
    }
    public void drawTextures(Canvas canvas) {
        for (int y = 0; y < mapArray.length; y++) {
            for (int x = 0; x < mapArray[y].length; x++) {
                switch (mapArray[y][x]){
                    case "i" : canvas.drawBitmap(innerBrick,x*textureWidth,y*textureHeight, paint);break;
                    case "u" : canvas.drawBitmap(upBrick,x*textureWidth,y*textureHeight, paint);break;
                    case "d" : canvas.drawBitmap(downBrick,x*textureWidth,y*textureHeight, paint);break;
                    case "|" : coordInform.put("Roland",new Pair<>(x,y));break;
                    case "c" : coordInform.put("Crate",new Pair<>(x,y));break;
                    case "l" : coordInform.put("Lever",new Pair<>(x,y));break;
                    case "s" : coordInform.put("Spike",new Pair<>(x,y));break;
                }
            }
        }
    }
}