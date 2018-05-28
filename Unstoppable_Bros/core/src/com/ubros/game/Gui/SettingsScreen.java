package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.UbrosGame;

public class SettingsScreen extends ScreenAdapter {

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
    private static final int EXIT_BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.15);

    /**
     * Exit button yy position
     */
    private static final int EXIT_BUTTON_YPOS = (int) (SCREEN_HEIGHT*0.45f);

    /**
     * Settings button width
     */
    private static final int SETTINGS_BUTTON_WIDTH = (int)(SCREEN_WIDTH*0.3);

    /**
     * Settings button yy position
     */
    private static final int SETTINGS_BUTTON_YPOS = (int)(SCREEN_HEIGHT*0.25);

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

    private Music music;


    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public SettingsScreen (UbrosGame game) {
        this.game = game;

        loadAssets();

        music = game.getAssetManager().get("audio/music/BullyWalkingTheme.mp3", Music.class);
        music.setLooping(true);
        music.play();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("audio/music/BullyWalkingTheme.mp3", Music.class);
        this.game.getAssetManager().load("sound.png",Texture.class);
        this.game.getAssetManager().load("soundOff.png",Texture.class);
        this.game.getAssetManager().load("soundOn.png",Texture.class);
        this.game.getAssetManager().load("tutorial.png",Texture.class);
        //this.game.getAssetManager().load("gameTitle.png",Texture.class);

        this.game.getAssetManager().finishLoading();
        initializeTextures();
    }

    /**
     *  Initializes textures array previously defined
     */
    private void initializeTextures() {
        menuButtons[0] = game.getAssetManager().get("soundOn.png", Texture.class);
        menuButtons[1] = game.getAssetManager().get("soundOff.png",Texture.class);
        menuButtons[2] = game.getAssetManager().get("tutorial.png",Texture.class);
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
        Texture title = game.getAssetManager().get("MainMenuSettingsButtonOn.png", Texture.class);
        Texture sound = game.getAssetManager().get("sound.png", Texture.class);
        game.getBatch().draw(background, 0, 0,SCREEN_WIDTH,SCREEN_HEIGHT);
        game.getBatch().draw(title, SCREEN_WIDTH*0.3f, SCREEN_HEIGHT*0.7f,SCREEN_WIDTH*0.4f,SCREEN_HEIGHT*0.2f);
        game.getBatch().draw(sound, SCREEN_WIDTH*0.3f, SCREEN_HEIGHT*0.45f,SCREEN_WIDTH*0.2f,BUTTON_HEIGHT);
    }

    /**
     * Draws main menu buttons
     */
    private void drawButtons() {

        if(Gdx.input.justTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if(checkSound(x,y)) {
                if(music.isPlaying())
                    music.stop();
                else
                    music.play();

                defaultMainMenu();
            }
            else
                defaultMainMenu();
        }
        else if(Gdx.input.isTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if(checkTutorialButton(x,y))
                this.game.setScreen(UbrosGame.mainMenu);
            else
                defaultMainMenu();
            
        }
        else {
            defaultMainMenu();
        }
    }

    private boolean checkSound(int x, int y) {
        return (x <= (SCREEN_WIDTH*0.55f + EXIT_BUTTON_WIDTH)) && (x >= SCREEN_WIDTH*0.55f)
                && (y <= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    private boolean checkTutorialButton(int x, int y) {
        return (x <= (SCREEN_WIDTH / 2 + SETTINGS_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - SETTINGS_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - SETTINGS_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    private boolean checkReturnButton(int x, int y) {
        return (x <= (SCREEN_WIDTH / 2 + EXIT_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    private void activatePlayButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        //game.getBatch().draw(menuButtons[5], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void activateTutorialButton() {
        if(music.isPlaying())
            game.getBatch().draw(game.getAssetManager().get("soundOn.png", Texture.class), SCREEN_WIDTH*0.55f , EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        else
            game.getBatch().draw(game.getAssetManager().get("soundOff.png", Texture.class), SCREEN_WIDTH*0.55f , EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH*0.45f - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void activateExitButton() {
        game.getBatch().draw(menuButtons[3], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void defaultMainMenu() {
        if(music.isPlaying())
            game.getBatch().draw(game.getAssetManager().get("soundOn.png", Texture.class), SCREEN_WIDTH*0.55f , EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        else
            game.getBatch().draw(game.getAssetManager().get("soundOff.png", Texture.class), SCREEN_WIDTH*0.55f , EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH*0.45f - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

}
