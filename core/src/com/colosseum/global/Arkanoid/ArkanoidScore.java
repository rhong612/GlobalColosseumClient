package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.Gdx;
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
import com.colosseum.global.Arkanoid.models.World;
import com.globalcolosseum.GlobalColosseumController;
import com.globalcolosseum.NetworkManager;
import com.globalcolosseum.WaitingScreen;

/**
 *
 */
public class ArkanoidScore implements Screen {

    private GlobalColosseumController controller;
    private int finalScore;
    private Camera camera;
    private Viewport viewport;
    private Label scoreTitleLabel;
    private Label scoreNumberLabel;
    private Stage stage;

    private float timeElapsed;


    public static final int GAME_WIDTH = 1600;
    public static final int GAME_HEIGHT = 900;

    public ArkanoidScore(GlobalColosseumController controller, int finalScore) {
        this.finalScore = finalScore;
        this.controller = controller;
        stage = new Stage();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        scoreTitleLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        scoreNumberLabel = new Label(Integer.toString(finalScore), new Label.LabelStyle(new BitmapFont(), Color.BLUE));

        Table labelTable = new Table();
        labelTable.top();
        labelTable.setFillParent(true);
        labelTable.add(scoreTitleLabel);
        labelTable.row();
        labelTable.add(scoreNumberLabel);

        stage.addActor(labelTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
        stage.draw();

        timeElapsed += delta;
        if (timeElapsed > 5) {
            controller.setScreen(new WaitingScreen(controller));
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
        stage.dispose();
    }
}
