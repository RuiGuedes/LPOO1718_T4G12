package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
     * Loads the assets needed by UbrosGame game
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);

        this.game.getAssetManager().load("MainMenuExitButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuPlayButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuExitButtonOn.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOn.png",Texture.class);
        this.game.getAssetManager().load("MainMenuPlayButtonOn.png",Texture.class);
        this.game.getAssetManager().load("gameTitle.png",Texture.class);

        this.game.getAssetManager().load("audio/music/BullyWalkingTheme.mp3", Music.class);
        this.game.getAssetManager().load("audio/music/BullyMainTheme.mp3", Music.class);
        this.game.getAssetManager().load("audio/sounds/pickSound.wav", Sound.class);

        this.game.getAssetManager().load("sound.png", Texture.class);
        this.game.getAssetManager().load("soundOff.png", Texture.class);
        this.game.getAssetManager().load("soundOn.png", Texture.class);
        this.game.getAssetManager().load("tutorialOff.png", Texture.class);
        this.game.getAssetManager().load("tutorialOn.png", Texture.class);
        this.game.getAssetManager().load("returnButtonOff.png", Texture.class);
        this.game.getAssetManager().load("returnButtonOn.png", Texture.class);


        this.game.getAssetManager().load("robotHand.png", Texture.class);
        this.game.getAssetManager().load("ninjaHand.png", Texture.class);
        this.game.getAssetManager().load("moveLeftButtonOff.png", Texture.class);
        this.game.getAssetManager().load("moveLeftButtonOn.png", Texture.class);
        this.game.getAssetManager().load("moveRightButtonOff.png", Texture.class);
        this.game.getAssetManager().load("moveRightButtonOn.png", Texture.class);
        this.game.getAssetManager().load("jumpButtonOff.png", Texture.class);
        this.game.getAssetManager().load("jumpButtonOn.png", Texture.class);
        this.game.getAssetManager().load("bulletButtonOff.png", Texture.class);
        this.game.getAssetManager().load("bulletButtonOn.png", Texture.class);
        this.game.getAssetManager().load("mechanismOff.png", Texture.class);
        this.game.getAssetManager().load("mechanismOn.png", Texture.class);
        this.game.getAssetManager().load("DoorLocked.png", Texture.class);
        this.game.getAssetManager().load("DoorUnlocked.png", Texture.class);
        this.game.getAssetManager().load("DoorOpen.png", Texture.class);
        this.game.getAssetManager().load("bullet.png", Texture.class);

        this.game.getAssetManager().load("Robot/Robot.pack", TextureAtlas.class);
        this.game.getAssetManager().load("Ninja/Ninja.pack", TextureAtlas.class);
        this.game.getAssetManager().load("Enemy/Enemy.pack", TextureAtlas.class);


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
        Texture title = game.getAssetManager().get("gameTitle.png", Texture.class);
        game.getBatch().draw(background, 0, 0,SCREEN_WIDTH,SCREEN_HEIGHT);
        game.getBatch().draw(title, SCREEN_WIDTH*0.1f, SCREEN_HEIGHT*0.7f,SCREEN_WIDTH*0.8f,SCREEN_HEIGHT*0.2f);
    }

    /**
     * Draws main menu buttons and handle's input
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
               game.setScreen(UbrosGame.settings);
            }
            else if(checkPlayButton(x,y)) {
                activatePlayButton();

                UbrosGame.playGame = new PlayGameScreen(this.game, true);
                if(SettingsScreen.soundActive) {
                    SettingsScreen.menuMusic.stop();
                    SettingsScreen.playGameMusic.play();
                }
                game.setScreen(UbrosGame.playGame);
            }
            else
                defaultMainMenu();
        }
        else {
            defaultMainMenu();
        }
    }

    /**
     * Checks if play button is pressed or not
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkPlayButton(int x, int y) {
        return (x <= (SCREEN_WIDTH / 2 + PLAY_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - PLAY_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - PLAY_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    /**
     * Checks if settings button is pressed or not
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkSettingsButton(int x, int y) {

        return (x <= (SCREEN_WIDTH / 2 + SETTINGS_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - SETTINGS_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - SETTINGS_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    /**
     * Checks if exit button is pressed or not
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkExitButton(int x, int y) {
        return (x <= (SCREEN_WIDTH / 2 + EXIT_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    /**
     * Draws all buttons with play button active
     */
    private void activatePlayButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[5], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * Draws all buttons with settings button active
     */
    private void activateSettingsButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[4], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * Draws all buttons with exit button active
     */
    private void activateExitButton() {
        game.getBatch().draw(menuButtons[3], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * Draws default menu
     */
    private void defaultMainMenu() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

}
