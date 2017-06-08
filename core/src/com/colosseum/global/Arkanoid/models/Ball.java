package com.colosseum.global.Arkanoid.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 *
 */

public class Ball {
    private float x;
    private float y;
    private float radius;
    private Vector2 velocity;

    public Ball(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        velocity = new Vector2(-500, 500); //initial velocity
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

    public Vector2 getVelocity() {
        return velocity;
    }

    public void moveBall(float delta) {
        x += velocity.x * delta;
        y += velocity.y * delta;
    }

}
