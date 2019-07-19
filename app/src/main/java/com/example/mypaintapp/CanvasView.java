package com.example.mypaintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {


    Mediator mediator;
    Paint paint;

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



            for(MyDrawing selected:mediator.drawings){
                if(mediator.selectedDrawings.contains(selected)){
                    selected.setSelected(true);
                }else {
                    selected.setSelected(false);
                }
            }
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
                mediator.touchDown(x,y);
                return true;

            case MotionEvent.ACTION_MOVE:
                mediator.touchMove(x,y);
                return true;

            case MotionEvent.ACTION_UP:
                mediator.touchUp(x,y);
                return true;
        }







        return false ;
    }


    void reset(){
        mediator.drawings.removeAllElements();
        invalidate();
    }

    public Mediator getMediator() {
        return mediator;
    }


    //    public void setCurrentDrawing(MyDrawing currentDrawing) {
//        this.currentDrawing = currentDrawing;
//    }
}
