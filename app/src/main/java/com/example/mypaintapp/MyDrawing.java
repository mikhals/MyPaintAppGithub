package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class MyDrawing {
    int x,y,w,h,fillColor,lineColor,outlineWidth;
    boolean isSelected;
    Point pivot;
    Paint paint;
    int SIZE = 25;
    Rect region;

    public MyDrawing(){
        isSelected = false;
        x = y = 0;
        w = h = 40;
        outlineWidth = 5;
        fillColor = Color.WHITE;
        lineColor = Color.BLACK;
        pivot = new Point();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

    }

    void draw(Canvas canvas){
        Paint tmp = new Paint();
        tmp.setColor(Color.BLACK);
        canvas.drawRect(x - SIZE/2,y - SIZE/2,x+ SIZE/2,y+ SIZE/2,tmp);
        canvas.drawRect(x - SIZE/2 + w/2,y - SIZE/2,x+ SIZE/2 + w/2,y+ SIZE/2,tmp);
        canvas.drawRect(x - SIZE/2 + w,y - SIZE/2,x+ SIZE/2 + w,y+ SIZE/2,tmp);
        canvas.drawRect(x - SIZE/2,y - SIZE/2 + h/2,x+ SIZE/2,y+ SIZE/2 + h/2,tmp);
        canvas.drawRect(x - SIZE/2 + w/2,y - SIZE/2 + h/2,x+ SIZE/2 +w/2,y+ SIZE/2 + h/2,tmp);
        canvas.drawRect(x - SIZE/2 + w,y - SIZE/2 + h/2,x+ SIZE/2 + w,y+ SIZE/2 + h/2,tmp);
        canvas.drawRect(x - SIZE/2,y - SIZE/2 + h,x+ SIZE/2,y+ SIZE/2 + h,tmp);
        canvas.drawRect(x - SIZE/2 + w/2,y - SIZE/2 + h,x+ SIZE/2 + w/2,y+ SIZE/2 + h,tmp);
        canvas.drawRect(x - SIZE/2 + w,y - SIZE/2 + h,x+ SIZE/2 + w,y+ SIZE/2 + h,tmp);
    }

    void setSize(int i){
        this.w = this.h = i;
    }

    void setSize(int w,int h){
        this.w=w;this.h=h;
        setRegion();
    }

    void setCoordinate(int x, int y){
        this.x = x;this.y=y;
        setRegion();
    }

    void setPivot(int x, int y){
        pivot.set(x,y);
    }

    void setPaint(Paint paint){
        this.paint=paint;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setRegion() {
        region = new Rect(x,y,x+w,y+h);
    }

    void move(int dx, int dy){
        setCoordinate(pivot.x+dx,pivot.y+dy);
    }
}
