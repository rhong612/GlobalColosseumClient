package com.globalcolosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 */
public class WaitingScreen implements Screen {

    private GlobalColosseumController controller;
    private float timeElapsed;
    private static final float SECONDS_PER_POLL = 5;

    public WaitingScreen(GlobalColosseumController controller, String playerName, String ipAddress) {
        this.controller = controller;
        timeElapsed = 0;
    	String password = "password";
    	String screenName = "Test";
        controller.getNetworkManager().connect(ipAddress, screenName, playerName, password);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
        timeElapsed += delta;
        if (timeElapsed > SECONDS_PER_POLL) {
            timeElapsed = 0;
            controller.getNetworkManager().poll();
        }
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
}
