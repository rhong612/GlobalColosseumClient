package com.globalcolosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
public class DiceRollScreen implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private GlobalColosseumController controller;

    private ShapeRenderer renderer;
    private Label diceNumberLabel;
    private short currentDiceNum;
    private boolean touched;

    private Camera camera;
    private Viewport viewport;
    private static final int VIRTUAL_SCREEN_WIDTH = 1600;
    private static final int VIRTUAL_SCREEN_HEIGHT = 900;

    private static final short MAX_ROLL = 6;
    private static final short LOWEST_ROLL = 1;

    public DiceRollScreen(GlobalColosseumController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        touched = false;
        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);

        atlas = new TextureAtlas("textures/comic-ui.atlas");
        skin = new Skin(Gdx.files.internal("textures/comic-ui.json"), atlas);

        currentDiceNum = MAX_ROLL;
        diceNumberLabel = new Label(Integer.toString(currentDiceNum), new Label.LabelStyle(skin.getFont("title"), Color.WHITE));

        Table table = new Table();
        table.setFillParent(true);
        table.add(diceNumberLabel);

        stage = new Stage();
        stage.addActor(table);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                touched = true;
                controller.getNetworkManager().sendRoll(currentDiceNum);
                controller.setScreen(new GameSelectionScreen(controller));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLUE);
        final int DICE_LENGTH = VIRTUAL_SCREEN_HEIGHT / 3;
        renderer.rect(VIRTUAL_SCREEN_WIDTH / 2 - DICE_LENGTH / 2, VIRTUAL_SCREEN_HEIGHT / 2 - DICE_LENGTH / 2, DICE_LENGTH, DICE_LENGTH);
        renderer.end();

        if (!touched) {
            currentDiceNum = currentDiceNum == MAX_ROLL ? LOWEST_ROLL : (short) (currentDiceNum + 1);
        }

        diceNumberLabel.setText(Integer.toString(currentDiceNum));
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
