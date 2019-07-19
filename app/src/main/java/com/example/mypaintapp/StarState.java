package com.example.mypaintapp;

public class StarState extends State {

    public StarState(Mediator mediator){
        this.mediator = mediator;
    }

    @Override
    void touchDown(int x, int y) {
        MyDrawing star = new MyStar();
        star.setCoordinate(x,y);
        star.setPivot(x,y);
        mediator.addDrawing(star);
        mediator.repaint();
    }

    @Override
    void touchMove(int x, int y) {
        MyDrawing star = mediator.getLastDrawing();
        star.setSize(Math.abs(x - star.pivot.x),Math.abs(y - star.pivot.y));
        star.setCoordinate(Math.min(x,star.pivot.x),Math.min(y,star.pivot.y));
//        rect.setCoordinate(Math.min(x,rect.pivot.x),Math.min(y,rect.pivot.y));
        mediator.repaint();
    }

    @Override
    void touchUp(int x, int y) {
        super.touchUp(x, y);
    }
}
