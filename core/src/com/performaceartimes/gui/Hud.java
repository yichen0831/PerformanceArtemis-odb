package com.performaceartimes.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.performaceartimes.PerformanceArtimes;

/**
 * Created by yichen on 10/25/15.
 */
public class Hud implements Disposable {

    private PerformanceArtimes game;
    private Stage stage;

    private BitmapFont font;

    private Label fpsLabel;
    private Label totalEntitiesLabel;
    private Label removeEntityLabel;

    private int totalEntities = 0;

    public Hud(PerformanceArtimes game) {
        this.game = game;

        FitViewport fitViewport = new FitViewport(640, 480);

        stage = new Stage(fitViewport);
        font = new BitmapFont();

        fpsLabel = new Label("FPS:", new Label.LabelStyle(font, Color.WHITE));
        totalEntitiesLabel = new Label("Total Entities:", new Label.LabelStyle(font, Color.WHITE));
        removeEntityLabel = new Label("Remove entities:", new Label.LabelStyle(font, Color.WHITE));

        Table table = new Table();
        table.top();
        table.pad(10.0f);
        table.setFillParent(true);
        table.add(fpsLabel).expandX().left();
        table.row();
        table.add(totalEntitiesLabel).expandX().left();
        table.row();
        table.add(removeEntityLabel).expandX().left();

        stage.addActor(table);
    }

    public void draw() {
        update();
        stage.draw();
    }

    public void setTotalEntities(int total) {
        totalEntities = total;
    }

    private void update() {
        fpsLabel.setText(String.format("FPS: %03d", Gdx.graphics.getFramesPerSecond()));
        totalEntitiesLabel.setText(String.format("Total entities: %d", totalEntities));
        removeEntityLabel.setText(String.format("Remove entities: %b", game.getRemoveEntity()));
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
