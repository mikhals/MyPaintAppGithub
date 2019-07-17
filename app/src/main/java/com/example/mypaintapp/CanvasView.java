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

    Mediator mediator;
    Paint paint;
    int cH = 40;

    public CanvasView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        setBackgroundColor(Color.WHITE);
        mediator = new Mediator(this);
//        setBackgroundColor(Color.rgb(230,230,230));
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
        for(MyDrawing d:mediator.drawings){
            d.draw(canvas);
        }

//        for(MyDrawing drawing: drawings){
//            drawing.draw(canvas);
//        }
    }

    void repaint(){
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX(),y=(int)event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int w=40;
                MyDrawing rect = new MyRectangle();
                rect.setCoordinate(x,y);
                mediator.addDrawing(rect);
                repaint();
                return true;

            case MotionEvent.ACTION_MOVE:
                rect = mediator.getLastDrawing();
                System.out.println(x);
                rect.setSize(x - rect.pivot.x,y - rect.pivot.y);
                repaint();

            case MotionEvent.ACTION_UP:
        }







        return false ;
    }

    public void setcH(int cH) {
        this.cH = cH;
        repaint();
    }

    void reset(){
        mediator.drawings.removeAllElements();
        invalidate();
    }

    void setSize(int i){
        for(MyDrawing d: mediator.drawings){
            d.setSize(i);

        }
        repaint();
    }

//    public void setCurrentDrawing(MyDrawing currentDrawing) {
//        this.currentDrawing = currentDrawing;
//    }
}
