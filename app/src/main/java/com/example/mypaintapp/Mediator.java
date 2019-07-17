package com.example.mypaintapp;

import android.graphics.Canvas;

import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
    Vector<MyDrawing> drawings,selectedDrawings;
    CanvasView canvasView;

    public Mediator(CanvasView canvasView){
        this.canvasView = canvasView;
        drawings = new Vector<>();
        selectedDrawings = new Vector<>();
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
}
