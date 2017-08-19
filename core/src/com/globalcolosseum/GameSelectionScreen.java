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
public class GameSelectionScreen implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private GlobalColosseumController controller;

    private Camera camera;
    private Viewport viewport;
    private static final int VIRTUAL_SCREEN_WIDTH = 600;
    private static final int VIRTUAL_SCREEN_HEIGHT = 600;

    private static final String UP_CONTROL_STRING = "up";
    private static final String DOWN_CONTROL_STRING = "down";
    private static final String LEFT_CONTROL_STRING = "left";
    private static final String RIGHT_CONTROL_STRING = "right";
    private static final String ENTER_CONTROL_STRING = "ok";

    public GameSelectionScreen(GlobalColosseumController controller) {
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

        Table table = new Table();
        table.setFillParent(true);

        //TODO: Replace with actual images
        TextButton upButton = new TextButton("UP", skin);
        upButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.getNetworkManager().sendControl(UP_CONTROL_STRING);
            }
        });
        TextButton downButton = new TextButton("DOWN", skin);
        downButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.getNetworkManager().sendControl(DOWN_CONTROL_STRING);
            }
        });
        TextButton leftButton = new TextButton("LEFT", skin);
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.getNetworkManager().sendControl(LEFT_CONTROL_STRING);
            }
        });
        TextButton rightButton = new TextButton("RIGHT", skin);
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.getNetworkManager().sendControl(RIGHT_CONTROL_STRING);
            }
        });
        TextButton enterButton = new TextButton("ENTER", skin);
        enterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.getNetworkManager().sendControl(ENTER_CONTROL_STRING);
                controller.setScreen(new WaitingScreen(controller));
            }
        });
        table.add(upButton);
        table.row();
        table.add(downButton);
        table.row();
        table.add(leftButton);
        table.row();
        table.add(rightButton);
        table.row();
        table.add(enterButton);

        stage = new Stage(viewport);
        stage.addActor(table);
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
