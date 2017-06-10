package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colosseum.global.Arkanoid.models.World;
import com.colosseum.global.GlobalColosseumController;
import com.colosseum.global.NetworkManager;

/**
 *
 */
public class Arkanoid implements Screen {

    private GlobalColosseumController controller;
    private World world;
    private WorldRenderer worldRenderer;
    private Hud hud;
    private int playerScore;

    private Camera camera;
    private Viewport viewport;

    protected static final int GAME_WIDTH = 1600;
    protected static final int GAME_HEIGHT = 900;

    public Arkanoid(GlobalColosseumController controller) {
        this.controller = controller;
        controller.getNetworkManager().connect("http://127.0.1.0");
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        playerScore = 0;
        world = new World(viewport);
        worldRenderer = new WorldRenderer(world, camera.combined);
        hud = new Hud(this);

        Gdx.input.setInputProcessor(world);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen

        world.step(delta);
        worldRenderer.render();
        hud.draw();
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

    /**
     * Increases player score by 1
     */
    public void incrementPlayerScore() {
        playerScore++;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
