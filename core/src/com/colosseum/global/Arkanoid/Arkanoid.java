package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.colosseum.global.Arkanoid.models.World;
import com.colosseum.global.NetworkManager;

/**
 *
 */
public class Arkanoid implements Screen, InputProcessor {

    private NetworkManager manager;
    private World world;
    private WorldRenderer worldRenderer;
    private Hud hud;
    private int playerScore;

    public Arkanoid(NetworkManager manager) {
        this.manager = manager;
    }

    @Override
    public void show() {
        playerScore = 0;
        Gdx.input.setInputProcessor(this);
        world = new World(this);
        worldRenderer = new WorldRenderer(world);
        hud = new Hud(playerScore);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen

        world.step(delta);
        worldRenderer.render(delta);
        hud.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    /**
     * Increases player score by 1
     */
    public void incrementPlayerScore() {
        playerScore++;
    }
}
