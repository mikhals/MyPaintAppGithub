package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyRectangle extends MyDrawing{
    public MyRectangle(){
        super();
    }

    @Override
    void draw(Canvas canvas) {
//        super.draw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
        canvas.drawRect(x-outlineWidth,y-outlineWidth,x+w+outlineWidth,y+h+outlineWidth,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
        canvas.drawRect(x,y,x+w,y+h,paint);

        if(isSelected){
            super.draw(canvas);
        }

    }



}
