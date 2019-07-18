package com.example.mypaintapp;

public class OvalState extends State {
    public OvalState(Mediator mediator){
        this.mediator = mediator;
    }

    @Override
    void touchDown(int x, int y) {
        MyDrawing oval = new MyOval();
        oval.setCoordinate(x,y);
        oval.setPivot(x,y);
        mediator.addDrawing(oval);
        mediator.repaint();
    }

    @Override
    void touchMove(int x, int y) {
        MyDrawing lastDrawing = mediator.getLastDrawing();
        lastDrawing.setSize(Math.abs(x - lastDrawing.pivot.x),Math.abs(y - lastDrawing.pivot.y));
        lastDrawing.setCoordinate(Math.min(x,lastDrawing.pivot.x),Math.min(y,lastDrawing.pivot.y));
//        rect.setCoordinate(Math.min(x,rect.pivot.x),Math.min(y,rect.pivot.y));
        mediator.repaint();
    }

    @Override
    void touchUp(int x, int y) {
        super.touchUp(x, y);
    }
}
