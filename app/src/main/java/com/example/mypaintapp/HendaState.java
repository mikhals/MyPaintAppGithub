package com.example.mypaintapp;

public class HendaState extends State {

    public HendaState(Mediator mediator){this.mediator = mediator;}

    @Override
    void touchDown(int x, int y) {
        MyDrawing henda = new MyHendacagonal();
        henda.setCoordinate(x,y);
        henda.setPivot(x,y);
        mediator.addDrawing(henda);
        System.out.println("added henda");
        mediator.repaint();
    }

    @Override
    void touchMove(int x, int y) {
        MyDrawing henda = mediator.getLastDrawing();
        henda.setSize(Math.abs(x - henda.pivot.x),Math.abs(y - henda.pivot.y));
        henda.setCoordinate(Math.min(x,henda.pivot.x),Math.min(y,henda.pivot.y));
//        rect.setCoordinate(Math.min(x,rect.pivot.x),Math.min(y,rect.pivot.y));
        mediator.repaint();
    }

    @Override
    void touchUp(int x, int y) {
        super.touchUp(x, y);
    }
}
