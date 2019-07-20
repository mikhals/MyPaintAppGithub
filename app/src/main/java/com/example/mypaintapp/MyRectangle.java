package com.example.mypaintapp;

import android.graphics.Canvas;

public class MyRectangle extends MyDrawing{
    int type = RECT;
    public MyRectangle(){
        super();
    }

    @Override
    void draw(Canvas canvas) {
//        super.draw(canvas);
//        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
        canvas.drawRect(x-outlineWidth,y-outlineWidth,x+w+outlineWidth,y+h+outlineWidth,paint);
//        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
        canvas.drawRect(x,y,x+w,y+h,paint);

        if(isSelected){
            super.draw(canvas);
        }

    }

    @Override
    public String toString() {
        return "Rectangle";
    }

    public MyDrawing myClone(){
        MyDrawing clone=new MyRectangle();
        clone.setCoordinate(x,y);
        clone.w = w;clone.h = h;
        clone.setPivot(pivot.x,pivot.y);
        clone.fillColor = fillColor;
        clone.lineColor = lineColor;
        clone.outlineWidth = outlineWidth;
        clone.paint = paint;
        clone.region = region;
        return clone;
    }
}
