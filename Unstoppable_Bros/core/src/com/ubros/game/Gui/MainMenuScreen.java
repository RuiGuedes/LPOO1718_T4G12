package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.UbrosGame;

public class MainMenuScreen extends ScreenAdapter {

    /**
     * Device screen width
     */
    private static final int SCREEN_WIDTH = Gdx.graphics.getWidth();

    /**
     * Device screen height
     */
    private static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    /**
     * Exit button width
     */
    private static final int EXIT_BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.25);

    /**
     * Exit button yy position
     */
    private static final int EXIT_BUTTON_YPOS = (int) (SCREEN_HEIGHT * 0.1);

    /**
     * Settings button width
     */
    private static final int SETTINGS_BUTTON_WIDTH = (int)(SCREEN_WIDTH*0.4);

    /**
     * Settings button yy position
     */
    private static final int SETTINGS_BUTTON_YPOS = (int)(SCREEN_HEIGHT*0.31);

    /**
     * Play button width
     */
    private static final int PLAY_BUTTON_WIDTH = (int)(SCREEN_WIDTH*0.5);

    /**
     * Play button yy position
     */
    private static final int PLAY_BUTTON_YPOS = (int)(SCREEN_HEIGHT*0.5);

    /**
     * Every button height
     */
    private static final int BUTTON_HEIGHT = (int)(SCREEN_HEIGHT*0.13);

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     *  Array that contains all textures to represent active and inactive buttons
     */
    private Texture[] menuButtons = new Texture[6];

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

        this.game.getAssetManager().load("MainMenuExitButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuPlayButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuExitButtonOn.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOn.png",Texture.class);
        this.game.getAssetManager().load("MainMenuPlayButtonOn.png",Texture.class);

        this.game.getAssetManager().finishLoading();
        initializeTextures();
    }

    /**
     *  Initializes textures array previously defined
     */
    private void initializeTextures() {
        menuButtons[0] = game.getAssetManager().get("MainMenuExitButtonOff.png", Texture.class);
        menuButtons[1] = game.getAssetManager().get("MainMenuSettingsButtonOff.png",Texture.class);
        menuButtons[2] = game.getAssetManager().get("MainMenuPlayButtonOff.png",Texture.class);
        menuButtons[3] = game.getAssetManager().get("MainMenuExitButtonOn.png", Texture.class);
        menuButtons[4] = game.getAssetManager().get("MainMenuSettingsButtonOn.png",Texture.class);
        menuButtons[5] = game.getAssetManager().get("MainMenuPlayButtonOn.png",Texture.class);
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

        if(Gdx.input.isTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if(checkExitButton(x,y)) {
                activateExitButton();
                Gdx.app.exit();
            }
            else if(checkSettingsButton(x,y)) {
               activateSettingsButton();
               //game.setScreen(new SettingsScreen(game));
            }
            else if(checkPlayButton(x,y)) {
                activatePlayButton();
                game.setScreen(new PlayGameScreen(game));
            }
            else
                defaultMainMenu();
        }
        else
            defaultMainMenu();
    }

    private boolean checkPlayButton(int x, int y) {
        return (x <= (SCREEN_WIDTH / 2 + PLAY_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - PLAY_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - PLAY_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    private boolean checkSettingsButton(int x, int y) {

        return (x <= (SCREEN_WIDTH / 2 + SETTINGS_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - SETTINGS_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - SETTINGS_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    private boolean checkExitButton(int x, int y) {
        return (x <= (SCREEN_WIDTH / 2 + EXIT_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    private void activatePlayButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[5], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void activateSettingsButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[4], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void activateExitButton() {
        game.getBatch().draw(menuButtons[3], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void defaultMainMenu() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }
}
