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
        renderBricks();
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
