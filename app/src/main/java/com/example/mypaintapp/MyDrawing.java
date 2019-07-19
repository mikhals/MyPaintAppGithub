package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class MyDrawing {
    int x,y,w,h,fillColor,lineColor,outlineWidth;
    boolean isSelected;
    Point pivot;
    Paint paint;

    public MyDrawing(){
        x = y = 0;
        w = h = 40;
        outlineWidth = 5;
        fillColor = Color.WHITE;
        lineColor = Color.BLACK;
        pivot = new Point();
        paint = new Paint();

    }

    void draw(Canvas canvas){
        Paint tmp = new Paint();
        tmp.setColor(Color.GREEN);
        canvas.drawRect(x-2,y-2,x+w+2,y+h+2,tmp);
    }

    void setSize(int i){
        this.w = this.h = i;
    }

    void setSize(int w,int h){this.w=w;this.h=h;}

    void setCoordinate(int x, int y){
        this.x = x;this.y=y;
    }

    void setPivot(int x, int y){
        pivot.set(x,y);
    }

    void setPaint(Paint paint){
        this.paint=paint;
    }



}
