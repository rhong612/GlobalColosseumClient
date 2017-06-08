package com.colosseum.global.Arkanoid.models;

/**
 *
 */

public class Panel {
    private float x;
    private float y;
    private float width;
    private float height;

    public Panel(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float xMove) {
        x += xMove;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
