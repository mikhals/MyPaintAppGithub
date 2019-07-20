package com.example.mypaintapp;

public class CopyState extends State {
    public CopyState(Mediator mediator){
        this.mediator = mediator;
    }

    @Override
    void touchDown(int x, int y) {
        mediator.paste(x,y);
    }

    @Override
    void touchMove(int x, int y) {

    }

    @Override
    void touchUp(int x, int y) {

    }
}
