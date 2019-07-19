package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class MyHendacagonal extends MyDrawing {
    int OFFSET_OUTLINE = 5;

    public MyHendacagonal(){
        super();
    }

    @Override
    void draw(Canvas canvas) {
//        super.draw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
//        canvas.drawOval(x-outlineWidth,y-outlineWidth,x+w+outlineWidth,y+h+outlineWidth,paint);
        drawHenda(canvas,paint,x+w/2,y+h/2,w+outlineWidth + OFFSET_OUTLINE,h+outlineWidth + OFFSET_OUTLINE);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
//        canvas.drawOval(x,y,x+w,y+h,paint);
        drawHenda(canvas,paint,x+w/2,y+h/2,w,h);

//        int halfWidth = 500 / 2;
//
//        Path path = new Path();
//        path.moveTo(x+ halfWidth, y + halfWidth); // Top
//        path.lineTo(x - halfWidth, y); // Left
//        path.lineTo(x, y - halfWidth); // Bottom
//        path.lineTo(x + halfWidth, y); // Right
//        path.lineTo(x+ halfWidth, y + halfWidth); // Back to Top
//        path.close();
//
//        canvas.drawPath(path, paint);




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
}
