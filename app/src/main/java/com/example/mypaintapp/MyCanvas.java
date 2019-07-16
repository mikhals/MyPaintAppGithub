package com.example.mypaintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.Vector;

public class MyCanvas extends View {
    MyDrawing currentDrawing;
    MyCanvas canvas;
    Mediator mediator;
    Vector<MyDrawing> rectangles = new Vector<>();
    Paint paint;
    int cH = 40;

    public MyCanvas(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        currentDrawing = new MyDrawing(0,0,canvas);
    }

//    public MyCanvas(Context context, Mediator mediator, Canvas canvas){
//        super(context);
//        this.mediator = mediator;
//        this.canvas = canvas;
//
//        canvas.drawCircle(5,5,50,new Paint());
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(MyDrawing d : rectangles){
            d.draw();
        }
    }

    void repaint(){
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        rectangles.add(new MyDrawing((int)event.getX(),(int)event.getY(),canvas));
        repaint();
        return false ;
    }

    void changeColor(){
        if(paint.getColor() == Color.BLUE) {
            paint.setColor(Color.GREEN);
        }else{
            paint.setColor(Color.BLUE);
        }
        repaint();

    }

    public void setcH(int cH) {
        this.cH = cH;
        repaint();
    }

    public void setCurrentDrawing(MyDrawing currentDrawing) {
        this.currentDrawing = currentDrawing;
    }
}
