package com.example.mypaintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Vector;

public class CanvasView extends View {
    Vector<Rect> rects = new Vector<>();
    Vector<MyDrawing> drawings = new Vector<>();
    Canvas canvas;
    Mediator mediator;
    Paint paint;
    int cH = 40;

    public CanvasView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

//    public MyCanvas(Context context, Mediator mediator, Canvas canvasView){
//        super(context);
//        this.mediator = mediator;
//        this.canvasView = canvasView;
//
//        canvasView.drawCircle(5,5,50,new Paint());
//    }

    @Override
    protected void onDraw(Canvas canvas) {


//        canvas.drawRect();
//        for(Rect rect : rects){
//            rect.bottom=cH+rect.left;
//            canvas.drawRect(rect,paint);
//        }

        for(MyDrawing drawing: drawings){
            drawing.draw(canvas);
        }
    }

    void repaint(){
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX(),y=(int)event.getY();
        int w=40;
//        rectangles.add(new MyDrawing((int)event.getX(),(int)event.getY(),canvas));
        rects.add(new Rect(x,y,x+w,y+cH));
        MyRectangle rect = new MyRectangle();
        rect.setCoordinate(x,y);
        drawings.add(rect);
        repaint();




        return false ;
    }

    void changeColor(){
        for(MyDrawing d:drawings){
            if(d.fillColor==Color.BLUE){
                d.fillColor=Color.GREEN;
            }else{
                d.fillColor=Color.BLUE;
            }
        }
        repaint();

    }

    public void setcH(int cH) {
        this.cH = cH;
        repaint();
    }

    void reset(){
        rects.removeAllElements();
        drawings.removeAllElements();
        invalidate();
    }

    void setSize(int i){
        for(MyDrawing d: drawings){
            d.setSize(i);

        }
        repaint();
    }

    void setPaint(){
        for(MyDrawing d: drawings){
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            d.setPaint(p);
        }
        repaint();
    }

//    public void setCurrentDrawing(MyDrawing currentDrawing) {
//        this.currentDrawing = currentDrawing;
//    }
}
