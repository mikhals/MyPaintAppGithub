package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyDrawing implements Cloneable {
    int x=0,y=0;
    int w=50,h=50;
    Paint paint = new Paint();
    Canvas canvas;

    public MyDrawing(int x,int y,Canvas canvas){
        setCanvas(canvas);
        this.x = x;
        this.y = y;
        setColor(1);
        paint.setStyle(Paint.Style.STROKE);

    }

    void setColor(int color){
        int test;
        if(color==0){
            paint.setColor(Color.BLUE);
        }else{
            paint.setColor(Color.GREEN);
        }
//        paint.setColor();
    }

    public void setH(int h) {
        this.h = h;
    }

    void draw(){
        canvas.drawCircle(x,y,h,paint);
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    void set(int x, int y){
        this.x = x;
        this.y = y;
    }
}
