package com.colosseum.global.Arkanoid.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colosseum.global.Arkanoid.Arkanoid;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */

public class World implements InputProcessor {
    private ArrayList<Brick> bricks;
    private Panel playerPanel;

    private Ball ball;

    private float startX;
    private Viewport viewport;

    private boolean worldStart;

    private int activeHitCount; //Incremented whenever the ball destroys a brick
    private int addRowLimit; // If the activeHitCount reaches this value, a new row is added. Set to Integer.MAX_VALUE if unneeded

    private Sound hitPanelSound;
    private Sound hitWallSound;
    private Sound missSound;


    private final int PANEL_HEIGHT = 30;
    private final int BALL_RADIUS = 15;
    private final int NUM_COLUMNS = 15;
    private final int NUM_ROWS = 4;
    private final int BRICK_HEIGHT = 70;
    private final int BRICK_WIDTH = Arkanoid.GAME_WIDTH / NUM_COLUMNS;
    private final int PANEL_WIDTH = BRICK_WIDTH * 2;

    public World(Viewport viewport) {
        bricks = new ArrayList<>();
        initializeLevel(1);
        worldStart = false;
        this.viewport = viewport;
        playerPanel = new Panel(Arkanoid.GAME_WIDTH / 2, Arkanoid.GAME_HEIGHT / 10, PANEL_WIDTH, PANEL_HEIGHT);
        ball = new Ball(playerPanel.getX(), playerPanel.getY() + playerPanel.getHeight(), BALL_RADIUS);


        hitPanelSound = Gdx.audio.newSound(Gdx.files.internal("pong_panel.wav"));
        hitWallSound = Gdx.audio.newSound(Gdx.files.internal("pong_wall.wav"));
        missSound = Gdx.audio.newSound(Gdx.files.internal("pong_miss.wav"));
    }

    public void step(float delta) {
        if (worldStart) {
            ball.moveBall(delta);
            checkCollisions(delta);
            if (activeHitCount >= addRowLimit) {
                activeHitCount = 0;
                addRowToTop();
            }
        }
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
        if (!worldStart) {
            worldStart = true;
        }
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

    private void checkCollisions(float delta) {
        //Left Wall
        if (ball.getX() <= 0) {
            ball.getVelocity().x *= -1;
            ball.setX(0);
            hitWallSound.play();
        }

        //Right Wall
        else if (ball.getX() >= Arkanoid.GAME_WIDTH) {
            ball.getVelocity().x *= -1;
            ball.setX(Arkanoid.GAME_WIDTH - ball.getRadius());
            hitWallSound.play();
        }

        //Ceiling
        else if (ball.getY() >= Arkanoid.GAME_HEIGHT) {
            ball.getVelocity().y *= -1;
            ball.setY(Arkanoid.GAME_HEIGHT - ball.getRadius());
            hitWallSound.play();
        }

        //Lose
        else if (ball.getY() <= 0) {
            missSound.play();
            worldStart = false;
        }

        //Panel
        else if (ball.getX() > playerPanel.getX() && ball.getX() < playerPanel.getX() + playerPanel.getWidth() && ball.getY() > playerPanel.getY() && ball.getY() < playerPanel.getY() + playerPanel.getHeight()) {
            final float MAX_BOUNCE_ANGLE = 60; //60 Degrees
            float distanceFromCenter = ball.getX() - (playerPanel.getX() + playerPanel.getWidth() / 2);
            float normalizedDistanceFromCenter = distanceFromCenter / (playerPanel.getWidth() / 2);
            float bounceAngle = normalizedDistanceFromCenter * MAX_BOUNCE_ANGLE;

            ball.getVelocity().x = ball.MAGNITUDE * (float)Math.sin(bounceAngle * (Math.PI / 180f));
            ball.getVelocity().y = ball.MAGNITUDE * (float)Math.cos(bounceAngle * (Math.PI / 180f));

            hitPanelSound.play();
            ball.setY(playerPanel.getY() + playerPanel.getHeight() + ball.getRadius());
        }
        //Brick
        else {
            Iterator<Brick> brickIterator = bricks.iterator();
            boolean collisionFound = false;
            while (brickIterator.hasNext() && !collisionFound) {
                Brick brick = brickIterator.next();
                if (ball.getX() > brick.getX() && ball.getX() < brick.getX() + brick.getWidth() && ball.getY() > brick.getY() && ball.getY() < brick.getY() + brick.getHeight()) {

                    //Bottom
                    if (ball.getVelocity().y > 0 && ball.getY() - ball.getRadius()< brick.getY()) {
                        ball.getVelocity().y *= -1;
                    }
                    //Right
                    else if (ball.getVelocity().x < 0 && ball.getX() + ball.getRadius() > brick.getX() + brick.getWidth()) {
                        ball.getVelocity().x *= -1;
                    }
                    //Left
                    else if (ball.getVelocity().x > 0 && ball.getX() - ball.getRadius() * 2 < brick.getX()) {
                        ball.getVelocity().x *= -1;
                    }
                    //Top
                    else {
                        ball.getVelocity().y *= -1;
                    }

                    hitPanelSound.play();
                    brickIterator.remove();
                    collisionFound = true;
                    activeHitCount++;
                }
            }
        }
    }

    public void dispose() {
        hitPanelSound.dispose();
        hitWallSound.dispose();
        missSound.dispose();
    }


    private void addRowToTop() {
        //Move all bricks down 1 row
        for (Brick brick : bricks) {
            brick.setY(brick.getY() - BRICK_HEIGHT);
        }

        //Add top row
        for (int i = 0; i < NUM_COLUMNS; i++) {
            bricks.add(new Brick(i * BRICK_WIDTH, Arkanoid.GAME_HEIGHT - BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT));
        }
    }

    private void initializeLevel(int level) {
        bricks.clear();
        if (level == 0) {
            for (int i = 0; i < NUM_COLUMNS; i++) {
                for (int j = 1; j <= NUM_ROWS; j++) {
                    bricks.add(new Brick(i * BRICK_WIDTH, Arkanoid.GAME_HEIGHT - j * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT));
                }
            }
            addRowLimit = Integer.MAX_VALUE;
        }
        else if (level == 1) {
            for (int i = 0; i < NUM_COLUMNS; i++) {
                for (int j = 1; j <= NUM_ROWS; j++) {
                    bricks.add(new Brick(i * BRICK_WIDTH, Arkanoid.GAME_HEIGHT - j * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT));
                }
            }
            addRowLimit = 10;
        }
    }
}
