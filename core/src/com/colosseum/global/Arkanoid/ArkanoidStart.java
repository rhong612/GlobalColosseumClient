package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.globalcolosseum.GlobalColosseumController;

/**
 *
 */
public class ArkanoidStart implements Screen, InputProcessor {

    private GlobalColosseumController controller;
    private Camera camera;
    private Viewport viewport;
    private Label titleLabel;
    private Label startLabel;
    private Label levelLabel;
    private Stage stage;
    private int level;


    public static final int GAME_WIDTH = 900;
    public static final int GAME_HEIGHT = 1600;

    public ArkanoidStart(GlobalColosseumController controller, int level) {
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

        final int fontScale = 5;
        titleLabel = new Label("ARKANOID", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        titleLabel.setFontScale(fontScale);
        startLabel = new Label("PRESS TO START...", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        startLabel.setFontScale(fontScale);
        levelLabel = new Label("LEVEL: " + level, new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        levelLabel.setFontScale(fontScale);

        Table labelTable = new Table();
        labelTable.top();
        labelTable.setFillParent(true);
        labelTable.add(titleLabel);
        labelTable.row();
        labelTable.add(startLabel);
        labelTable.row();
        labelTable.add(levelLabel);

        stage = new Stage(viewport);
        stage.addActor(labelTable);


        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
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
        stage.dispose();
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
        controller.setScreen(new ArkanoidMain(controller, level));
        return true;
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
}
