package com.example.mypaintapp;

public class RectState extends State {

    public RectState(Mediator mediator){
        this.mediator = mediator;
    }

    @Override
    void touchDown(int x, int y) {
        MyDrawing rect = new MyRectangle();
        rect.setCoordinate(x,y);
        rect.setPivot(x,y);
        mediator.addDrawing(rect);
        mediator.repaint();
    }

    @Override
    void touchMove(int x, int y) {
        MyDrawing rect = mediator.getLastDrawing();
        rect.setSize(Math.abs(x - rect.pivot.x),Math.abs(y - rect.pivot.y));
        rect.setCoordinate(Math.min(x,rect.pivot.x),Math.min(y,rect.pivot.y));
//        rect.setCoordinate(Math.min(x,rect.pivot.x),Math.min(y,rect.pivot.y));
        mediator.repaint();
    }

    @Override
    void touchUp(int x, int y) {
        super.touchUp(x, y);
    }

}
