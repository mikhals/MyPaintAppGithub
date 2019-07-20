package com.example.mypaintapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class Mediator {
    Vector<MyDrawing> drawings,selectedDrawings,buffers;
    CanvasView canvasView;
    State state;
    TextView statusbar;
    Canvas canvas;
    File filerDir;
    int currentColor;
    int canvasH,canvasW;

    public Mediator(CanvasView canvasView){
        this.canvasView = canvasView;
        drawings = new Vector<>();
        selectedDrawings = new Vector<>();
        buffers = new Vector<>();
        state = null;
        currentColor = Color.WHITE;
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

    void clearBuffers(){
        buffers = new Vector<>();
    }

    void copy(){
        clearBuffers();
        for(MyDrawing d:selectedDrawings){
            buffers.add(d.myClone());
        }
    }

    void cut(){
        clearBuffers();
        copy();
        selectedDrawings.removeAllElements();
    }

    void paste(int x, int y){
        Vector<MyDrawing> clone = new Vector<>();

        for(MyDrawing d:buffers){
            clone.add(d.myClone());
        }

//        Vector<MyDrawing> clone = new Vector<>(buffers);
        Point topLeft= new Point(), bottomRight = new Point();
        MyDrawing firstElement = clone.firstElement();
        topLeft.set(firstElement.x,firstElement.y);
        bottomRight.set(firstElement.x+firstElement.w,firstElement.y+firstElement.h);


        for(MyDrawing d:clone){
            topLeft.set(Math.min(topLeft.x,d.x),Math.min(topLeft.y,d.y));
            bottomRight.set(Math.max(bottomRight.x,d.x+d.w),Math.max(bottomRight.y,d.y+d.h));
        }

        for(MyDrawing d:clone){
            int diffX = bottomRight.x-topLeft.x;
            int diffY = bottomRight.y - topLeft.y;
//            d.setCoordinate(0,0);
//            d.setPivot(0,0);
            int newX = d.x - topLeft.x + x -diffX;
            int newY = d.y - topLeft.y + y -diffY;
            d.setCoordinate(newX,newY);
            d.setPivot(newX,newY);
        }


        for(MyDrawing d:clone){
            drawings.add(d);
        }
        repaint();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        canvasH = canvas.getHeight();
        canvasW = canvas.getWidth();
        System.out.println("h = "+canvasH+", W = "+canvasW);
    }


    public void setFilerDir(File filerDir) {
        this.filerDir = filerDir;
    }

    void savepic(String file){

        String filename = "/storage/emulated/0/a.jpg";
        Bitmap myBitmap = Bitmap.createBitmap( canvasW, canvasH, Bitmap.Config.RGB_565 );
        canvas.drawBitmap(myBitmap,0,0,null);
        try (FileOutputStream out = new FileOutputStream(filename)) {
            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStatusText("Saved to :"+filename);
        System.out.println("Saved to :"+filename);
    }
}
