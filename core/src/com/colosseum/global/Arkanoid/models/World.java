package com.colosseum.global.Arkanoid.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.colosseum.global.Arkanoid.Arkanoid;
import com.colosseum.global.Arkanoid.models.Brick;

import java.util.ArrayList;

/**
 *
 */

public class World implements InputProcessor {
    private ArrayList<Brick> bricks;
    private final int NUM_COLUMNS = 15;
    private final int NUM_ROWS = 4;
    private final float BRICK_HEIGHT = 10f;
    private Arkanoid arkanoid;
    private Panel playerPanel;

    private int startX;


    public World(Arkanoid arkanoid) {
        this.arkanoid = arkanoid;
        playerPanel = new Panel();
        bricks = new ArrayList<>();
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //0 for first finger
        if (pointer == 0) {
            startX = screenX;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //0 for first finger
        if (pointer == 0) {
            playerPanel.move(screenX - startX);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
