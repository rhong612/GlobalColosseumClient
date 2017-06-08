package com.colosseum.global.Arkanoid.models;

/**
 *
 */

public class Ball {
    private float x;
    private float y;
    private float radius;

    public Ball(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }
}
