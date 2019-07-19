package com.example.mypaintapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class MyStar extends MyDrawing {
    int OFFSET_OUTLINE = 25;
    public MyStar(){
        super();
    }

    @Override
    void draw(Canvas canvas) {
//        super.draw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(lineColor);
        drawStar(canvas,paint,x+w/2,y+h/2,w+outlineWidth+OFFSET_OUTLINE,h+outlineWidth+OFFSET_OUTLINE);



        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);



        drawStar(canvas,paint,x+w/2,y+h/2,w,h);


        if(isSelected){
            super.draw(canvas);
        }


    }

    public void drawStar(Canvas canvas, Paint paint, int x, int y, int width, int height) {
//        paint.setColor(Color.BLACK);
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int point = 5;
        double t=2*Math.PI/point;

        Path path = new Path();
        path.moveTo(x , y + halfHeight); // Top
        for(int i=0;i<point*2+1;i=i+2){
            path.lineTo((float)(halfWidth * (Math.cos((i+1)*t)))+x,(float)(halfHeight * (Math.sin((i+1)*t)))+y);
        }
        path.lineTo(x, y + halfHeight); // Back to Top
        path.close();

        canvas.drawPath(path, paint);


    }

    @Override
    public String toString() {
        return "Star";
    }
}
