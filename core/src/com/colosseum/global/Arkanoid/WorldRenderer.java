package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colosseum.global.Arkanoid.models.Brick;
import com.colosseum.global.Arkanoid.models.World;

/**
 *
 */
public class WorldRenderer {
    private ShapeRenderer renderer;
    private World world;

    private SpriteBatch batch;
    private Texture paddleTexture;
    private Texture backgroundTexture;
    private Texture wallTexture;
    private Texture cornerTexture;

    public WorldRenderer(World world, Matrix4 projectionMatrix) {
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(projectionMatrix);
        this.world = world;
        batch = new SpriteBatch();
        batch.setProjectionMatrix(projectionMatrix);
        paddleTexture = new Texture("Arkanoid/paddle.png");
        backgroundTexture = new Texture("Arkanoid/background.png");
        wallTexture = new Texture("Arkanoid/wall.png");
        cornerTexture = new Texture("Arkanoid/corner.png");
    }

    private void renderBackground() {
        batch.begin();
        batch.draw(backgroundTexture, ArkanoidMain.GAME_WIDTH / 2 - backgroundTexture.getWidth() / 2, ArkanoidMain.GAME_HEIGHT / 2 - backgroundTexture.getHeight() / 2);
        batch.end();
    }

    private void renderEdges() {
        batch.begin();
        //TODO: Render the edges
        batch.end();
    }

    public void render() {
        renderBackground();
        renderEdges();
        renderPanel();
        renderBall();
        renderBricks();
    }

    private void renderPanel() {
        batch.begin();
        batch.draw(paddleTexture, world.getPanel().getX(), world.getPanel().getY(), world.getPanel().getWidth(), world.getPanel().getHeight());
        batch.end();
    }

    private void renderBall() {
        renderer.begin(ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        renderer.circle(world.getBall().getX(), world.getBall().getY(), world.getBall().getRadius());
        renderer.end();
    }

    private void renderBricks() {
        renderer.begin(ShapeType.Filled);
        for (Brick brick : world.getBricks()) {
            if (brick.getDurability() == 1) {
                renderer.setColor(Color.WHITE);
            }
            else if (brick.getDurability() == 2) {
                renderer.setColor(Color.BLUE);
            }
            else {
                renderer.setColor(Color.GRAY);
            }
            renderer.rect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        renderer.end();


        renderer.begin(ShapeType.Line);
        renderer.setColor(Color.RED);
        for (Brick brick : world.getBricks()) {
            renderer.rect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        renderer.end();
    }

    public void dispose() {

        renderer.dispose();
        paddleTexture.dispose();
        backgroundTexture.dispose();
        batch.dispose();
    }
}
