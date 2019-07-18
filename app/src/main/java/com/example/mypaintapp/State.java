package com.example.mypaintapp;

public class State {
    Mediator mediator;

    void touchDown(int x, int y){}

    void touchUp(int x, int y){}

    void touchMove(int x, int y){}

    public Mediator getMediator() {
        return mediator;
    }
}
