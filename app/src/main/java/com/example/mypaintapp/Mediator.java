package com.example.mypaintapp;

import android.widget.TextView;

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

    void touchUp(int x, int y){
        if(state!=null){
            state.touchUp(x, y);
        }
    }

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

    void changeSelectedFillColor(int c){
        for(MyDrawing d:selectedDrawings){
            d.fillColor = c;
        }
    }

    public void move(int dx, int dy){
        for(MyDrawing d : selectedDrawings){
            d.move(dx,dy);
        }
        repaint();
    }

    boolean containsSelected(int x, int y,MyDrawing neglect){
        for(MyDrawing d:selectedDrawings){
            if(d.region.contains(x,y) && d!=neglect){
                return true;
            }
        }
        return false;
    }

    boolean removeSelected(){//if something is deleted, return true
        boolean b = false;
        for(MyDrawing d:selectedDrawings){
            if(drawings.contains(d)){
                drawings.remove(d);
                b = true;
            }
        }
        repaint();
        return b;
    }

}
