package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MyOval extends MyDrawing {
    public MyOval(){
        super();
    }

    @Override
    void draw(Canvas canvas) {
//        super.draw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
        canvas.drawOval(x-outlineWidth,y-outlineWidth,x+w+outlineWidth,y+h+outlineWidth,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
        canvas.drawOval(x,y,x+w,y+h,paint);

    }
}
