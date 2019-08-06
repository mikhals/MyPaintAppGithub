package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class MyHendacagonal extends MyDrawing {
    int type = HENDA;
    int OFFSET_OUTLINE = 5;

    public MyHendacagonal(){
        super();
    }

    @Override
    void draw(Canvas canvas) {
        if(isShadow){
            paint.setColor(Color.BLACK);
            drawHenda(canvas,paint,x+(w/2)+SHADOW_OFFSET,y+(h/2)+SHADOW_OFFSET,w+outlineWidth + OFFSET_OUTLINE+SHADOW_OFFSET,h+outlineWidth + OFFSET_OUTLINE+SHADOW_OFFSET);
        }


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
        drawHenda(canvas,paint,x+w/2,y+h/2,w+outlineWidth + OFFSET_OUTLINE,h+outlineWidth + OFFSET_OUTLINE);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
        drawHenda(canvas,paint,x+w/2,y+h/2,w,h);
        if(isSelected){
            super.draw(canvas);
        }




    }

    public void drawHenda(Canvas canvas, Paint paint, int x, int y, int width, int height) {
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int point = 11;
        double t=2*Math.PI/point;

        Path path = new Path();
        path.moveTo(x , y + halfHeight); // Top
        for(int i=0;i<point+1;i++){
            path.lineTo((float)(halfWidth * (Math.cos((i+1)*t)))+x,(float)(halfHeight * (Math.sin((i+1)*t)))+y);
        }
        path.lineTo(x, y + halfHeight); // Back to Top
        path.close();

        canvas.drawPath(path, paint);


    }

    @Override
    public String toString() {
        return "Hendacagonal";
    }
    public MyDrawing myClone(){
        MyDrawing clone=new MyHendacagonal();
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
