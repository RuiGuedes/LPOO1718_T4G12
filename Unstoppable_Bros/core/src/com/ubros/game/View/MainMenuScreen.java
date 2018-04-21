package com.ubros.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.UbrosGame;

public class MainMenuScreen extends ScreenAdapter {

    private static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public MainMenuScreen(UbrosGame game) {
        this.game = game;

        loadAssets();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);

        this.game.getAssetManager().load("MainMenuExitButton.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButton.png",Texture.class);
        this.game.getAssetManager().load("MainMenuPlayButton.png",Texture.class);

        this.game.getAssetManager().finishLoading();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawBackground();
        drawButtons();
        game.getBatch().end();

    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.jpg", Texture.class);
        game.getBatch().draw(background, 0, 0,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    /**
     * Draws main menu buttons
     */
    private void drawButtons() {
        Texture exitButton = game.getAssetManager().get("MainMenuExitButton.png", Texture.class);
        Texture settingsButton = game.getAssetManager().get("MainMenuSettingsButton.png",Texture.class);
        Texture playButton = game.getAssetManager().get("MainMenuPlayButton.png",Texture.class);

        game.getBatch().draw(exitButton,SCREEN_WIDTH/2 - (int)(SCREEN_WIDTH*0.25)/2, (int)(SCREEN_HEIGHT*0.1), (int)(SCREEN_WIDTH*0.25), (int)(SCREEN_HEIGHT*0.13));
        game.getBatch().draw(settingsButton,SCREEN_WIDTH/2 - (int)(SCREEN_WIDTH*0.4)/2,(int)(SCREEN_HEIGHT*0.31), (int)(SCREEN_WIDTH*0.4) ,(int)(SCREEN_HEIGHT*0.13));
        game.getBatch().draw(playButton,SCREEN_WIDTH/2 - (int)(SCREEN_WIDTH*0.5)/2,SCREEN_HEIGHT/2, (int)(SCREEN_WIDTH*0.5),(int)(SCREEN_HEIGHT*0.13));
    }

}
