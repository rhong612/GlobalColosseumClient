package com.colosseum.global.Arkanoid.models;

/**
 *
 */

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;
    private int durability;

    public Brick(int x, int y, int width, int height, int durability) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.durability = durability;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void hit() {
        durability--;
    }

    public int getDurability() {
        return durability;
    }
}
