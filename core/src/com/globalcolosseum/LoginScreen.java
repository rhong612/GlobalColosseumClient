package com.globalcolosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 */
public class LoginScreen implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private GlobalColosseumController controller;

    private Camera camera;
    private Viewport viewport;
    private static final int VIRTUAL_SCREEN_WIDTH = 1600;
    private static final int VIRTUAL_SCREEN_HEIGHT = 900;

    public LoginScreen(GlobalColosseumController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        atlas = new TextureAtlas("textures/comic-ui.atlas");
        skin = new Skin(Gdx.files.internal("textures/comic-ui.json"), atlas);

        stage = new Stage();

        Table mainTable = new Table();
        mainTable.setFillParent(true);


        Label serverLabel = new Label("Server IP Address:", new Label.LabelStyle(skin.getFont("button"), Color.WHITE));
        final TextField serverTextField = new TextField("", skin);

        Label playerLabel = new Label("Player Name:", new Label.LabelStyle(skin.getFont("button"), Color.WHITE));
        final TextField playerTextField = new TextField("", skin);

        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.setScreen(new WaitingScreen(controller, playerTextField.getText(), serverTextField.getText()));
            }
        });

        mainTable.add(serverLabel);
        mainTable.add(serverTextField);
        mainTable.row();
        mainTable.add(playerLabel);
        mainTable.add(playerTextField);
        mainTable.row();
        mainTable.add(startButton).colspan(2);

        stage.addActor(mainTable);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
        stage.act();
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
