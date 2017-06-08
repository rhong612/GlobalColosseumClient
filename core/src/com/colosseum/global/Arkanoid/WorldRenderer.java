package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.colosseum.global.Arkanoid.models.Brick;
import com.colosseum.global.Arkanoid.models.World;

/**
 *
 */
public class WorldRenderer {
    private ShapeRenderer renderer;
    private World world;

    public WorldRenderer(World world, Matrix4 projectionMatrix) {
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(projectionMatrix);
        this.world = world;
    }

    public void render() {
        renderPanel();
        renderBall();
        renderBricks();
    }

    private void renderPanel() {
        renderer.begin(ShapeType.Filled);
        renderer.setColor(Color.YELLOW);
        renderer.rect(world.getPanel().getX(), world.getPanel().getY(), world.getPanel().getWidth(), world.getPanel().getHeight());
        renderer.end();
    }

    private void renderBall() {
        renderer.begin(ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        renderer.circle(world.getBall().getX(), world.getBall().getY(), world.getBall().getRadius());
        renderer.end();
    }

    private void renderBricks() {
        renderer.begin(ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        for (Brick brick : world.getBricks()) {
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
    }
}
