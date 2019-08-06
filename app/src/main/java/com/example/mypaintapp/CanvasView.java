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

    public CanvasView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        setBackgroundColor(Color.WHITE);
        mediator = new Mediator(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

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

        mediator.setCanvas(canvas);

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
        mediator.selectedDrawings.removeAllElements();
        invalidate();
    }

    public Mediator getMediator() {
        return mediator;
    }
}
