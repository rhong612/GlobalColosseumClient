package com.globalcolosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 */
public class WaitingScreen implements Screen {

    public WaitingScreen(GlobalColosseumController controller, String playerName, String ipAddress) {
    	String password = "password";
        controller.getNetworkManager().connect(ipAddress, playerName, password);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
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
