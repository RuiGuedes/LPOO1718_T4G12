package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.Controller.GameController;
import com.ubros.game.UbrosGame;

public class TransitiveScreen extends ScreenAdapter {

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
    private static final int EXIT_BUTTON_YPOS = (int) (SCREEN_HEIGHT * 0.31);

    /**
     * Settings button width
     */
    private static final int RETRY_BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.3);

    /**
     * Settings button yy position
     */
    private static final int RETRY_BUTTON_YPOS = (int) (SCREEN_HEIGHT * 0.27);

    /**
     * Every button height
     */
    private static final int BUTTON_HEIGHT = (int) (SCREEN_HEIGHT * 0.13);

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Final game status
     */
    private GameController.GameStatus status;

    /**
     * Array that contains all textures to represent active and inactive buttons
     */
    private Texture[] menuButtons = new Texture[4];

    public TransitiveScreen(UbrosGame game, GameController.GameStatus status) {

        this.game = game;
        this.status = status;

        initializeTextures();
    }

    /**
     * Initializes textures array previously defined
     */
    private void initializeTextures() {
        menuButtons[0] = game.getAssetManager().get("MainMenuExitButtonOff.png", Texture.class);
        menuButtons[1] = game.getAssetManager().get("MainMenuExitButtonOn.png", Texture.class);
        menuButtons[2] = game.getAssetManager().get("retryOff.png", Texture.class);
        menuButtons[3] = game.getAssetManager().get("retryOn.png", Texture.class);
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
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.jpg", Texture.class);
        game.getBatch().draw(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        if (this.status == GameController.GameStatus.GAMEOVER)
            game.getBatch().draw(game.getAssetManager().get("gameOver.png", Texture.class), SCREEN_WIDTH * 0.2f, SCREEN_HEIGHT * 0.6f, SCREEN_WIDTH * 0.6f, SCREEN_HEIGHT * 0.2f);
        else
            game.getBatch().draw(game.getAssetManager().get("victory.png", Texture.class), SCREEN_WIDTH * 0.26f, SCREEN_HEIGHT * 0.6f, SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.2f);
    }

    /**
     * Draws main menu buttons and handle's input
     */
    private void drawButtons() {

        if (Gdx.input.isTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (checkExitButton(x, y)) {
                activateExitButton();
                this.game.setScreen(UbrosGame.mainMenu);
            } else if (checkRetryButton(x, y)) {
                activateRetryButton();
                UbrosGame.playGame = new PlayGameScreen(this.game, true);
                game.setScreen(UbrosGame.playGame);
            } else
                defaultMainMenu();
        } else {
            defaultMainMenu();
        }
    }

    /**
     * Checks if settings button is pressed or not
     *
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkRetryButton(int x, int y) {

        return (x <= (SCREEN_WIDTH * 0.24f - RETRY_BUTTON_WIDTH / 2 + RETRY_BUTTON_WIDTH)) && (x >= (SCREEN_WIDTH *0.24f - RETRY_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - RETRY_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - RETRY_BUTTON_YPOS - BUTTON_HEIGHT - BUTTON_HEIGHT *0.4f));
    }

    /**
     * Checks if exit button is pressed or not
     *
     * @param x X position on screen
     * @param y Y position on screen
     * @return true if pressed, false otherwise
     */
    private boolean checkExitButton(int x, int y) {
        return (x <= (SCREEN_WIDTH * 0.77f - EXIT_BUTTON_WIDTH / 2 + EXIT_BUTTON_WIDTH)) && (x >= (SCREEN_WIDTH * 0.77f - EXIT_BUTTON_WIDTH / 2))
                && (y <= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS)) && (y >= (SCREEN_HEIGHT - EXIT_BUTTON_YPOS - BUTTON_HEIGHT));
    }


    /**
     * Draws all buttons with settings button active
     */
    private void activateRetryButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH * 0.77f - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[3], SCREEN_WIDTH * 0.24f - RETRY_BUTTON_WIDTH / 2, RETRY_BUTTON_YPOS, RETRY_BUTTON_WIDTH, BUTTON_HEIGHT + BUTTON_HEIGHT *0.4f);
    }

    /**
     * Draws all buttons with exit button active
     */
    private void activateExitButton() {
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH * 0.77f - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH * 0.24f - RETRY_BUTTON_WIDTH / 2, RETRY_BUTTON_YPOS, RETRY_BUTTON_WIDTH, BUTTON_HEIGHT + BUTTON_HEIGHT *0.4f);
    }

    /**
     * Draws default menu
     */
    private void defaultMainMenu() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH * 0.77f - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH * 0.24f - RETRY_BUTTON_WIDTH / 2, RETRY_BUTTON_YPOS, RETRY_BUTTON_WIDTH, BUTTON_HEIGHT + BUTTON_HEIGHT * 0.4f);
    }

}
