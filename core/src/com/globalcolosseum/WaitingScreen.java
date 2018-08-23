package com.globalcolosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 */
public class WaitingScreen implements Screen {
    private GlobalColosseumController controller;
    private float timeElapsed;
    private static final float SECONDS_PER_POLL = 2;
    private TextureAtlas atlas;
    private Skin skin;
    private Stage stage;

    private Camera camera;
    private Viewport viewport;
    private static final int VIRTUAL_SCREEN_WIDTH = 600;
    private static final int VIRTUAL_SCREEN_HEIGHT = 600;

    public WaitingScreen(GlobalColosseumController controller) {
        this.controller = controller;
        timeElapsed = 0;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);

        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        atlas = new TextureAtlas("textures/comic-ui.atlas");
        skin = new Skin(Gdx.files.internal("textures/comic-ui.json"), atlas);
        stage = new Stage(viewport);
        Label waitingLabel = new Label("Please wait...", new Label.LabelStyle(skin.getFont("button"), Color.WHITE));

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.add(waitingLabel);

        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
        timeElapsed += delta;
        if (timeElapsed > SECONDS_PER_POLL) {
            timeElapsed = 0;
            controller.poll();
        }
        stage.draw();
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
        atlas.dispose();
        skin.dispose();
        stage.dispose();
    }
}
