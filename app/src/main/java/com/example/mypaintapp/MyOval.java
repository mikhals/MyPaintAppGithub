package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyOval extends MyDrawing {
    int type = OVAL;
    public MyOval(){
        super();
    }

    @Override
    void draw(Canvas canvas) {
//        super.draw(canvas);
        if(isShadow){
            paint.setColor(Color.BLACK);
            canvas.drawOval(x-outlineWidth+SHADOW_OFFSET,y-outlineWidth+SHADOW_OFFSET,x+w+outlineWidth+SHADOW_OFFSET,y+h+outlineWidth+SHADOW_OFFSET,paint);
        }

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
        canvas.drawOval(x-outlineWidth,y-outlineWidth,x+w+outlineWidth,y+h+outlineWidth,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
        canvas.drawOval(x,y,x+w,y+h,paint);

        if(isSelected){
            super.draw(canvas);
        }

    }

    @Override
    public String toString() {
        return "Oval";
    }

    public MyDrawing myClone(){
        MyDrawing clone=new MyOval();
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
