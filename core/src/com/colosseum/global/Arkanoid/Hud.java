package com.colosseum.global.Arkanoid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 */

public class Hud extends Stage{

    private Label scoreTitleLabel;
    private Label scoreNumberLabel;
    private ArkanoidMain arkanoid;

    public Hud(Viewport viewport, ArkanoidMain arkanoid) {
        super(viewport);
        this.arkanoid = arkanoid;

        final int fontScale = 2;
        scoreTitleLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreNumberLabel = new Label(Integer.toString(arkanoid.getPlayerScore()), new Label.LabelStyle(new BitmapFont(), Color.RED));

        scoreTitleLabel.setFontScale(fontScale);
        scoreNumberLabel.setFontScale(fontScale);

        Table labelTable = new Table();
        labelTable.top();
        labelTable.setFillParent(true);
        labelTable.add(scoreTitleLabel);
        labelTable.row();
        labelTable.add(scoreNumberLabel);

        this.addActor(labelTable);
    }

    @Override
    public void draw() {
        scoreNumberLabel.setText(Integer.toString(arkanoid.getPlayerScore()));
        super.draw();
    }
}
