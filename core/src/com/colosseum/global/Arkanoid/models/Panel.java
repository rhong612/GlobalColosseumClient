package com.colosseum.global.Arkanoid.models;

/**
 *
 */

public class Panel {
    private int x;
    private int y;

    public Panel() {

    }

    public void move(int xMove) {
        x += xMove;
    }
}
