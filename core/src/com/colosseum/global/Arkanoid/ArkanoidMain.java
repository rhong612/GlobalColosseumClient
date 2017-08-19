package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colosseum.global.Arkanoid.models.World;
import com.globalcolosseum.GlobalColosseumController;
import com.globalcolosseum.NetworkManager;
import com.globalcolosseum.WaitingScreen;

/**
 *
 */
public class ArkanoidMain implements Screen {

    private GlobalColosseumController controller;
    private World world;
    private WorldRenderer worldRenderer;
    private Hud hud;

    private Camera camera;
    private Viewport viewport;
    private int level;

    public static final int GAME_WIDTH = 900;
    public static final int GAME_HEIGHT = 1600;

    public ArkanoidMain(GlobalColosseumController controller, int level) {
        this.controller = controller;
        this.level = level;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        world = new World(viewport, level);
        worldRenderer = new WorldRenderer(world, camera.combined);
        hud = new Hud(viewport, this);

        Gdx.input.setInputProcessor(world);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen

        world.step(delta);
        worldRenderer.render();
        hud.draw();

        if (world.isGameOver()) {
            controller.getNetworkManager().sendScore(world.getScore());
            controller.setScreen(new ArkanoidScore(controller, world.getScore()));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
        world.dispose();
        hud.dispose();
    }

    public int getPlayerScore() {
        return world.getScore();
    }
}
