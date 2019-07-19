package com.example.mypaintapp;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;

public class SelectState extends State {
    MyDrawing selector;
    Point pivot = new Point();

    public SelectState(Mediator mediator){
        this.mediator = mediator;


    }

    @Override
    void touchDown(int x, int y) {
        pivot.set(x,y);
        selector = new MyRectangle();
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{10,20},0));
        selector.setPaint(paint);


        if(!mediator.containsSelected(x,y,selector)){
//            if(mediator.selectedDrawings.isEmpty()){
            mediator.addDrawing(selector);
            selector.setPaint(paint);

            selector.setCoordinate(x,y);
            selector.setPivot(x,y);
            mediator.repaint();
        }else{
            pivot.set(x, y);
        }
    }

    @Override
    void touchMove(int x, int y) {
        System.out.println(mediator.selectedDrawings.size());

        if(!mediator.containsSelected(x,y,selector)){
            selector.setSize(Math.abs(x - selector.pivot.x),Math.abs(y - selector.pivot.y));
            selector.setCoordinate(Math.min(x,selector.pivot.x),Math.min(y,selector.pivot.y));
//        rect.setCoordinate(Math.min(x,rect.pivot.x),Math.min(y,rect.pivot.y));
            mediator.selectedDrawings.removeAllElements();
            for(MyDrawing drawing:mediator.drawings){
                if(selector.region.contains(drawing.region) && drawing!=selector){
                    mediator.addSelectedDrawing(drawing);
                }else{

                }
            }
            mediator.repaint();
            mediator.setStatusText("Selected: " + mediator.selectedDrawings);

        }else {
            int dx = x - pivot.x;
            int dy = y - pivot.y;
            mediator.move(dx,dy);
        }


    }

    @Override
    void touchUp(int x, int y) {
        for(MyDrawing d:mediator.selectedDrawings){
            d.setPivot(d.x,d.y);
        }
        mediator.removeDrawing(selector);
        mediator.repaint();
    }
}
