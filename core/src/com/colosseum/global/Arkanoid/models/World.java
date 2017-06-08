package com.colosseum.global.Arkanoid.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
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
    private final int BRICK_HEIGHT = 30;

    private Panel playerPanel;
    private final int PANEL_HEIGHT = 30;

    private Ball ball;
    private final int BALL_RADIUS = 15;

    private float startX;
    private Viewport viewport;


    public World(Viewport viewport) {
        this.viewport = viewport;
        playerPanel = new Panel(Arkanoid.GAME_WIDTH / 2, Arkanoid.GAME_HEIGHT / 10, Arkanoid.GAME_WIDTH / NUM_COLUMNS, PANEL_HEIGHT);
        ball = new Ball(playerPanel.getX(), playerPanel.getY() + playerPanel.getHeight(), BALL_RADIUS);
        bricks = new ArrayList<>();
        final int brickWidth = Arkanoid.GAME_WIDTH / NUM_COLUMNS;

        //Construct bricks
        for (int i = 0; i < NUM_COLUMNS; i++) {
            for (int j = 1; j <= NUM_ROWS; j++) {
                bricks.add(new Brick(i * brickWidth, Arkanoid.GAME_HEIGHT - j * BRICK_HEIGHT, brickWidth, BRICK_HEIGHT));
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
            startX = screenToWorldUnits(screenX, screenY).x;
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
            float worldScreenX = screenToWorldUnits(screenX, screenY).x;
            playerPanel.move(worldScreenX - startX);
            startX = worldScreenX;
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

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Panel getPanel() {
        return playerPanel;
    }

    private Vector3 screenToWorldUnits(int screenX, int screenY) {
        return viewport.getCamera().unproject(new Vector3(screenX, screenY, 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
    }

    public Ball getBall() {
        return ball;
    }

}
