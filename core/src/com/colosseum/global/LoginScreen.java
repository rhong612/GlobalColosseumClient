package com.colosseum.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 */
public class LoginScreen implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private GlobalColosseumController controller;
    private NetworkManager manager;

    public LoginScreen(GlobalColosseumController controller, NetworkManager networkManager) {
        this.controller = controller;
        manager = networkManager;
    }

    @Override
    public void show() {
        atlas = new TextureAtlas("textures/comic-ui.atlas");
        skin = new Skin(Gdx.files.internal("textures/comic-ui.json"), atlas);

        stage = new Stage();

        Table mainTable = new Table();
        mainTable.setFillParent(true);


        Label serverLabel = new Label("Server IP Address:", new Label.LabelStyle(skin.getFont("title"), Color.WHITE));
        final TextField textField = new TextField("", skin);
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.setScreen(new WaitingScreen(textField.getText(), manager));
            }
        });;

        mainTable.add(serverLabel);
        mainTable.add(textField);
        mainTable.row();
        mainTable.add(startButton);


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
