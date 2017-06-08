package com.colosseum.global.Arkanoid.models;

import com.badlogic.gdx.Gdx;
import com.colosseum.global.Arkanoid.Arkanoid;
import com.colosseum.global.Arkanoid.models.Brick;

import java.util.ArrayList;

/**
 *
 */

public class World {
    private ArrayList<Brick> bricks;
    private final int NUM_COLUMNS = 15;
    private final int NUM_ROWS = 4;
    private final float BRICK_HEIGHT = 10f;
    private Arkanoid arkanoid;

    public World(Arkanoid arkanoid) {
        this.arkanoid = arkanoid;
        //Construct bricks
        for (int i = 0; i < NUM_COLUMNS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                int brickWidth = Gdx.graphics.getWidth() / NUM_COLUMNS;
                bricks.add(new Brick(i * brickWidth, j * (Gdx.graphics.getHeight() / BRICK_HEIGHT), brickWidth, BRICK_HEIGHT));
            }
        }
    }

    public void step(float delta) {

    }
}
