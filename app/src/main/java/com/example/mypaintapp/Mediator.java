package com.example.mypaintapp;

import android.graphics.Canvas;
import android.widget.TextView;

import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
    Vector<MyDrawing> drawings,selectedDrawings;
    CanvasView canvasView;
    State state;
    TextView statusbar;

    public Mediator(CanvasView canvasView){
        this.canvasView = canvasView;
        drawings = new Vector<>();
        selectedDrawings = new Vector<>();
        state = null;
    }

    public void addDrawing(MyDrawing d){
        drawings.add(d);
    }

    public void removeDrawing(MyDrawing d){
        drawings.remove(d);
    }

    public void addSelectedDrawing(MyDrawing d){
        selectedDrawings.add(d);
    }

    public void removeSelectedDrawing(MyDrawing d){
        selectedDrawings.remove(d);
    }

    public void repaint(){
        canvasView.invalidate();
    }

    public MyDrawing getLastDrawing(){
        return drawings.lastElement();
    }

    public void setState(State state) {
        this.state = state;
        System.out.println(state);

    }

    void touchDown(int x, int y){
        System.out.println(state);
//
//        RectState rectS = new RectState(this);
//        setState(rectS);
//        state.touchDown(x,y);

        if(state!=null){
            state.touchDown(x,y);
        }
    }

    void touchUp(int x, int y){}

    void touchMove(int x, int y){
        if(state!=null){
            state.touchMove(x,y);
        }
    }

    public void setStatusbar(TextView statusbar) {
        this.statusbar = statusbar;
    }

    void setStatusText(String s){
        statusbar.setText(s);
    }
}
