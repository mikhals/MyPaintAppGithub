package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class MyRectangle extends MyDrawing {

    public MyRectangle(int x, int y, MyCanvas canvas){
        super(x, y, canvas);
        this.x = x;
        this.y = y;
        setColor(1);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    void draw() {
        canvas.drawRect(x,y,x+w,y+h,paint);
    }
}
