package com.colosseum.global.Arkanoid.models;

/**
 *
 */

public class Panel {
    private int x;
    private int y;
    private int width;
    private int height;

    public Panel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int xMove) {
        x += xMove;
    }
}
