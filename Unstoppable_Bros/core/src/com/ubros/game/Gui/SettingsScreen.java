package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
     * Sound button width
     */
    private static final int SOUND_BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.15);

    /**
     * Sound button yy position
     */
    private static final int SOUND_BUTTON_YPOS = (int) (SCREEN_HEIGHT * 0.45f);

    /**
     * Tutorial button width
     */
    private static final int TUTORIAL_BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.3);

    /**
     * Tutorial button yy position
     */
    private static final int TUTORIAL_BUTTON_YPOS = (int) (SCREEN_HEIGHT * 0.2);

    /**
     * Return button width
     */
    private static final int RETURN_BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.15);

    /**
     * Return button yy position
     */
    private static final int RETURN_BUTTON_YPOS = (int) (SCREEN_HEIGHT * 0.05);

    /**
     * Every button height
     */
    private static final int BUTTON_HEIGHT = (int) (SCREEN_HEIGHT * 0.13);

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Array that contains all textures to represent active and inactive buttons
     */
    private Texture[] menuButtons = new Texture[6];

    /**
     * Determines whether sound is active or not
     */
    public static boolean soundActive;

    /**
     * Menu music
     */
    public static Music menuMusic = null;

    /**
     * Music while playing
     */
    public static Music playGameMusic = null;

    /**
     * Sound that represents picking up an object
     */
    public static Sound pickObjectiveSound = null;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public SettingsScreen(UbrosGame game) {

        this.game = game;
        soundActive = true;

        initializeTextures();
        initializeAudio();
    }

    /**
     * Initializes textures array previously defined
     */
    private void initializeTextures() {
        menuButtons[0] = game.getAssetManager().get("soundOn.png", Texture.class);
        menuButtons[1] = game.getAssetManager().get("soundOff.png", Texture.class);
        menuButtons[2] = game.getAssetManager().get("tutorialOff.png", Texture.class);
        menuButtons[3] = game.getAssetManager().get("tutorialOn.png", Texture.class);
        menuButtons[4] = game.getAssetManager().get("returnButtonOff.png", Texture.class);
        menuButtons[5] = game.getAssetManager().get("returnButtonOn.png", Texture.class);
    }

    /**
     * Initializes all audio used all over the game
     */
    private void initializeAudio() {

        menuMusic = game.getAssetManager().get("audio/music/BullyWalkingTheme.mp3", Music.class);
        menuMusic.setLooping(true);
        if (soundActive)
            menuMusic.play();

        playGameMusic = game.getAssetManager().get("audio/music/BullyMainTheme.mp3", Music.class);
        playGameMusic.setLooping(true);
        playGameMusic.setVolume(menuMusic.getVolume() / 5);

        pickObjectiveSound = game.getAssetManager().get("audio/sounds/pickSound.wav", Sound.class);

    }

    @Override
    public void render(float delta) {

        super.render(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        game.getBatch().begin();
        drawBackground();
        drawButtons();
        game.getBatch().end();

    }

    /**
     * Draws the background along with title and sound option
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.jpg", Texture.class);
        Texture title = game.getAssetManager().get("MainMenuSettingsButtonOn.png", Texture.class);
        Texture sound = game.getAssetManager().get("sound.png", Texture.class);

        game.getBatch().draw(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        game.getBatch().draw(title, SCREEN_WIDTH * 0.3f, SCREEN_HEIGHT * 0.7f, SCREEN_WIDTH * 0.4f, SCREEN_HEIGHT * 0.2f);
        game.getBatch().draw(sound, SCREEN_WIDTH * 0.3f, SCREEN_HEIGHT * 0.45f, SCREEN_WIDTH * 0.2f, BUTTON_HEIGHT);
    }

    /**
     * Draws this menu buttons and handle's input
     */
    private void drawButtons() {

        if (Gdx.input.justTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (checkSound(x, y)) {
                changeAudio();
                defaultMainMenu();
            } else if (checkReturnButton(x, y)) {
                activateReturnButton();
                this.game.setScreen(UbrosGame.mainMenu);
            } else
                defaultMainMenu();

        } else if (Gdx.input.isTouched() && checkTutorialButton(Gdx.input.getX(), Gdx.input.getY()))
            activateTutorialButton();
        else {
            defaultMainMenu();
        }
    }

    /**
     * Changes audio settings
     */
    private void changeAudio() {
        soundActive = !soundActive;
        if (menuMusic.isPlaying())
            menuMusic.stop();
        else
            menuMusic.play();
    }

    /**
     * Checks if sound is pressed or not
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkSound(int x, int y) {
        return (x <= (SCREEN_WIDTH * 0.55f + SOUND_BUTTON_WIDTH)) && (x >= SCREEN_WIDTH * 0.55f)
                && (y <= (SCREEN_HEIGHT - SOUND_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - SOUND_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    /**
     * Checks if tutorial button is pressed or not
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkTutorialButton(int x, int y) {
        return (x <= (SCREEN_WIDTH / 2 + TUTORIAL_BUTTON_WIDTH / 2)) && (x >= (SCREEN_WIDTH / 2 - TUTORIAL_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - TUTORIAL_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - TUTORIAL_BUTTON_YPOS - BUTTON_HEIGHT/2));
    }

    /**
     * Checks if return button is pressed or not
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkReturnButton(int x, int y) {
        return (x <= (SCREEN_WIDTH * 0.90f + RETURN_BUTTON_WIDTH)) && (x >= (SCREEN_WIDTH * 0.90f))
                && (y <= (SCREEN_HEIGHT - RETURN_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - RETURN_BUTTON_YPOS - BUTTON_HEIGHT));
    }

    /**
     * Draws all buttons with return button active
     */
    private void activateReturnButton() {
        if (soundActive)
            game.getBatch().draw(game.getAssetManager().get("soundOn.png", Texture.class), SCREEN_WIDTH * 0.55f, SOUND_BUTTON_YPOS, SOUND_BUTTON_WIDTH, BUTTON_HEIGHT);
        else
            game.getBatch().draw(game.getAssetManager().get("soundOff.png", Texture.class), SCREEN_WIDTH * 0.55f, SOUND_BUTTON_YPOS, SOUND_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH * 0.45f - TUTORIAL_BUTTON_WIDTH / 2, TUTORIAL_BUTTON_YPOS, TUTORIAL_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[5], SCREEN_WIDTH * 0.90f - RETURN_BUTTON_WIDTH / 2, RETURN_BUTTON_YPOS, RETURN_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * Draws all buttons with tutorial button active
     */
    private void activateTutorialButton() {
        if (soundActive)
            game.getBatch().draw(game.getAssetManager().get("soundOn.png", Texture.class), SCREEN_WIDTH * 0.55f, SOUND_BUTTON_YPOS, SOUND_BUTTON_WIDTH, BUTTON_HEIGHT);
        else
            game.getBatch().draw(game.getAssetManager().get("soundOff.png", Texture.class), SCREEN_WIDTH * 0.55f, SOUND_BUTTON_YPOS, SOUND_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[3], SCREEN_WIDTH * 0.45f - TUTORIAL_BUTTON_WIDTH / 2, TUTORIAL_BUTTON_YPOS, TUTORIAL_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[4], SCREEN_WIDTH * 0.90f - RETURN_BUTTON_WIDTH / 2, RETURN_BUTTON_YPOS, RETURN_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * Draws default menu
     */
    private void defaultMainMenu() {
        if (soundActive)
            game.getBatch().draw(game.getAssetManager().get("soundOn.png", Texture.class), SCREEN_WIDTH * 0.55f, SOUND_BUTTON_YPOS, SOUND_BUTTON_WIDTH, BUTTON_HEIGHT);
        else
            game.getBatch().draw(game.getAssetManager().get("soundOff.png", Texture.class), SCREEN_WIDTH * 0.55f, SOUND_BUTTON_YPOS, SOUND_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH * 0.45f - TUTORIAL_BUTTON_WIDTH / 2, TUTORIAL_BUTTON_YPOS, TUTORIAL_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[4], SCREEN_WIDTH * 0.90f - RETURN_BUTTON_WIDTH / 2, RETURN_BUTTON_YPOS, RETURN_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

}
