package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.Networking.Connection;
import com.ubros.game.UbrosGame;

public class ConnectingPlayersScreen extends ScreenAdapter {

    ////////////////////////////

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


    ///////////////////////////


    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    private Connection connect;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public ConnectingPlayersScreen(UbrosGame game) {
        this.game = game;

        connect = new Connection(this.game);

        loadAssets();

    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().load("MainMenuExitButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOff.png",Texture.class);
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

    public boolean MENU_ID = true;

    /**
     *  Array that contains all textures to represent active and inactive buttons
     */
    private Texture[] menuButtons = new Texture[6];

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {

        super.render(delta);

        MENU_ID = connect.MENU_ID;

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawBackground();

        if(MENU_ID)
            defaultMainMenu();
        else
            activateSettingsButton();

        game.getBatch().end();

    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.jpg", Texture.class);
        game.getBatch().draw(background, 0, 0,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    private void activateSettingsButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[4], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void defaultMainMenu() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }
}
