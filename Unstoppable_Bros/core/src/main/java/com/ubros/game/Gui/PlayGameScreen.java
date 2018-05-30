package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;
import com.ubros.game.View.Elements.NinjaView;
import com.ubros.game.View.Elements.RobotView;
import com.ubros.game.View.GameView;

import java.util.ArrayList;
import java.util.List;

public class PlayGameScreen extends ScreenAdapter implements InputProcessor {

    /**
     * Device screen width
     */
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth();

    /**
     * Device screen height
     */
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();

    /**
     * Device screen width
     */
    private static final float VIRTUAL_SCREEN_WIDTH = 1920;

    /**
     * Device screen height
     */
    private static final float VIRTUAL_SCREEN_HEIGHT = 1080;

    /**
     * Pixel to meter correspondence
     */
    public static final float PIXEL_TO_METER = 100;

    /**
     * Virtual width of buttons used to move character
     */
    private static final float VIRTUAL_BUTTON_WIDTH = (int) (VIRTUAL_SCREEN_WIDTH * 0.08) / PIXEL_TO_METER;

    /**
     * Virtual height of buttons used to move character
     */
    private static final float VIRTUAL_BUTTON_HEIGHT = (int) (VIRTUAL_SCREEN_HEIGHT * 0.08) / PIXEL_TO_METER;

    /**
     * Width of buttons used to move character
     */
    private static final float BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.08) / PIXEL_TO_METER;

    /**
     * Height of buttons used to move character
     */
    private static final float BUTTON_HEIGHT = (int) (SCREEN_HEIGHT * 0.08) / PIXEL_TO_METER;

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Game camera used to view game map
     */
    private OrthographicCamera gameCam;

    /**
     * Renders tiled map through out the screen
     */
    private OrthogonalTiledMapRenderer mapRenderer;

    /**
     * Texture responsible to represent left movement
     */
    private Texture leftButton;

    /**
     * Texture responsible to represent right movement
     */
    private Texture rightButton;

    /**
     * Texture responsible to represent shoot action
     */
    private Texture shootButton;

    /**
     * Texture responsible to represent jump movement
     */
    private Texture jumpButton;

    /**
     * Structure responsible to hold buttons (off and on) textures
     */
    private List<Texture> buttonTextures = new ArrayList<Texture>();

    /**
     * True if robot is selected. False if ninja is selected
     */
    private boolean selectedPlayer;

    /**
     * If true game is paused and players can't move
     */
    private boolean paused;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public PlayGameScreen(UbrosGame game, boolean selectedPlayer) {

        this.game = game;
        this.selectedPlayer = selectedPlayer;
        this.paused = false;

        createCamera();


        TmxMapLoader mapLoader = new TmxMapLoader();
        UbrosGame.map = mapLoader.load("UbrosMap/UbrosMap.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(UbrosGame.map, 1 / PIXEL_TO_METER);

        Gdx.input.setInputProcessor(this);

        GameController.getInstance(null).dispose();
        GameController.getInstance(this.game).setState(GameController.GameStatus.PLAYING);

        initializeGraphics();

    }

    /**
     * Creates game camera used to visualize game tiled map
     */
    private void createCamera() {
        this.gameCam = new OrthographicCamera(VIRTUAL_SCREEN_WIDTH / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT / PIXEL_TO_METER);
        this.gameCam.position.set(gameCam.viewportWidth / 2f, gameCam.viewportHeight / 2f, 0);
        this.gameCam.update();
    }

    /**
     * Resets camera to it's default state
     */
    private void resetCamera() {
        this.gameCam = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.gameCam.position.set(gameCam.viewportWidth / 2f, gameCam.viewportHeight / 2f, 0);
        this.gameCam.update();
        this.game.getBatch().setProjectionMatrix(gameCam.combined);
    }

    /**
     * Initializes all game elements view
     */
    private void initializeGraphics() {

        buttonTextures.add(game.getAssetManager().get("moveLeftButtonOff.png", Texture.class));
        buttonTextures.add(game.getAssetManager().get("moveLeftButtonOn.png", Texture.class));
        buttonTextures.add(game.getAssetManager().get("moveRightButtonOff.png", Texture.class));
        buttonTextures.add(game.getAssetManager().get("moveRightButtonOn.png", Texture.class));
        buttonTextures.add(game.getAssetManager().get("jumpButtonOff.png", Texture.class));
        buttonTextures.add(game.getAssetManager().get("jumpButtonOn.png", Texture.class));
        buttonTextures.add(game.getAssetManager().get("bulletButtonOff.png", Texture.class));
        buttonTextures.add(game.getAssetManager().get("bulletButtonOn.png", Texture.class));

        resetButtons();
    }

    @Override
    public void render(float delta) {

        super.render(delta);

        this.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        this.mapRenderer.render();
        GameController.getInstance(this.game).getDebugRenderer().render(GameController.getInstance(this.game).getWorld(), gameCam.combined);

        game.getBatch().begin();
        drawGraphics(delta);
        game.getBatch().end();

        checkGameStatus();
    }

    /**
     * Draws all game graphics on screen
     */
    private void drawGraphics(float delta) {
        drawHand();
        drawInteractiveButtons();
        GameView.getInstance(this.game).drawElements(delta);
        drawBackground();
    }

    /**
     * Updates both world and all elements created
     */
    private void update() {

        if(!paused)
            handleInput();

        GameController.getInstance(this.game).getWorld().step(1 / 60f, 6, 2);
        GameController.getInstance(this.game).update();

        game.getBatch().setProjectionMatrix(gameCam.combined);
        gameCam.update();

        this.mapRenderer.setView(gameCam);
    }

    /**
     * Checks possible inputs and acts accordingly
     */
    private void handleInput() {

        if (selectedPlayer)
            ((RobotView) GameView.getInstance(this.game).getRobot()).setHorizontalMovement(false);   //((RobotView) robot).setHorizontalMovement(false);
        else
            ((NinjaView) GameView.getInstance(this.game).getNinja()).setHorizontalMovement(false);   //((NinjaView) ninja).setHorizontalMovement(false);

        for (int i = 0; i < 4; i++) {
            if (Gdx.input.isTouched(i)) {
                int x = Gdx.input.getX(i);
                int y = Gdx.input.getY(i);

                if (checkLeftButton(x, y)) {

                    if (selectedPlayer)
                        ((RobotView) GameView.getInstance(this.game).getRobot()).setHorizontalMovement(true);  //((RobotView) robot).setHorizontalMovement(true);
                    else
                        ((NinjaView) GameView.getInstance(this.game).getNinja()).setHorizontalMovement(true);  //((NinjaView) ninja).setHorizontalMovement(true);

                    if ((GameController.getInstance(this.game).getRobot().getBody().getLinearVelocity().x >= -GameController.PLAYER_SPEED * 10) && selectedPlayer)
                        GameController.getInstance(this.game).getRobot().getBody().applyLinearImpulse(new Vector2(-GameController.PLAYER_SPEED, 0), GameController.getInstance(this.game).getRobot().getBody().getWorldCenter(), true);
                    else if ((GameController.getInstance(this.game).getNinja().getBody().getLinearVelocity().x >= -GameController.PLAYER_SPEED * 10) && !selectedPlayer)
                        GameController.getInstance(this.game).getNinja().getBody().applyLinearImpulse(new Vector2(-GameController.PLAYER_SPEED, 0), GameController.getInstance(this.game).getNinja().getBody().getWorldCenter(), true);

                    leftButton = buttonTextures.get(1);
                }

                if (checkRightButton(x, y)) {

                    if (selectedPlayer)
                        ((RobotView) GameView.getInstance(this.game).getRobot()).setHorizontalMovement(true);  //((RobotView) robot).setHorizontalMovement(true);
                    else
                        ((NinjaView) GameView.getInstance(this.game).getNinja()).setHorizontalMovement(true);  //((NinjaView) ninja).setHorizontalMovement(true);

                    if ((GameController.getInstance(this.game).getRobot().getBody().getLinearVelocity().x <= GameController.PLAYER_SPEED * 10) && selectedPlayer)
                        GameController.getInstance(this.game).getRobot().getBody().applyLinearImpulse(new Vector2(GameController.PLAYER_SPEED, 0), GameController.getInstance(this.game).getRobot().getBody().getWorldCenter(), true);
                    else if ((GameController.getInstance(this.game).getNinja().getBody().getLinearVelocity().x <= GameController.PLAYER_SPEED * 10) && !selectedPlayer)
                        GameController.getInstance(this.game).getNinja().getBody().applyLinearImpulse(new Vector2(GameController.PLAYER_SPEED, 0), GameController.getInstance(this.game).getNinja().getBody().getWorldCenter(), true);

                    rightButton = buttonTextures.get(3);
                }
            }
        }
    }

    /**
     * Draws hand to make possible distinguish which player is selected
     */
    private void drawHand() {
        if (selectedPlayer)
            game.getBatch().draw(game.getAssetManager().get("robotHand.png", Texture.class), (int) (VIRTUAL_SCREEN_WIDTH * 0.02) / PIXEL_TO_METER, (int) (VIRTUAL_SCREEN_HEIGHT * 0.5) / PIXEL_TO_METER, VIRTUAL_SCREEN_WIDTH * 0.07f / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT * 0.14f / PIXEL_TO_METER);
        else
            game.getBatch().draw(game.getAssetManager().get("ninjaHand.png", Texture.class), (int) (VIRTUAL_SCREEN_WIDTH * 0.02) / PIXEL_TO_METER, (int) (VIRTUAL_SCREEN_HEIGHT * 0.5) / PIXEL_TO_METER, VIRTUAL_SCREEN_WIDTH * 0.07f / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT * 0.14f / PIXEL_TO_METER);
    }

    /**
     * Draws all interactive buttons in their respective state
     */
    private void drawInteractiveButtons() {
        game.getBatch().draw(leftButton, (int) (VIRTUAL_SCREEN_WIDTH * 0.05) / PIXEL_TO_METER, (int) (VIRTUAL_SCREEN_HEIGHT * 0.04) / PIXEL_TO_METER, VIRTUAL_BUTTON_WIDTH, VIRTUAL_BUTTON_HEIGHT);
        game.getBatch().draw(rightButton, (int) (VIRTUAL_SCREEN_WIDTH * 0.05 + VIRTUAL_BUTTON_WIDTH * PIXEL_TO_METER + VIRTUAL_SCREEN_WIDTH * 0.01) / PIXEL_TO_METER, (int) (VIRTUAL_SCREEN_HEIGHT * 0.04) / PIXEL_TO_METER, VIRTUAL_BUTTON_WIDTH, VIRTUAL_BUTTON_HEIGHT);
        game.getBatch().draw(jumpButton, (int) (VIRTUAL_SCREEN_WIDTH * 0.89) / PIXEL_TO_METER, (int) (VIRTUAL_BUTTON_WIDTH * PIXEL_TO_METER - VIRTUAL_SCREEN_HEIGHT * 0.01) / PIXEL_TO_METER, VIRTUAL_BUTTON_WIDTH, VIRTUAL_BUTTON_HEIGHT);
        game.getBatch().draw(shootButton, (int) (VIRTUAL_SCREEN_WIDTH * 0.89) / PIXEL_TO_METER, (int) (VIRTUAL_SCREEN_HEIGHT * 0.04) / PIXEL_TO_METER, VIRTUAL_BUTTON_WIDTH, VIRTUAL_BUTTON_HEIGHT);
    }

    /**
     * Draws the all background elements (pause and focus)
     */
    private void drawBackground() {

        if(paused) {
            Texture background = game.getAssetManager().get("backLoseFocus.png", Texture.class);
            game.getBatch().draw(background, 0, 0, VIRTUAL_SCREEN_WIDTH / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT / PIXEL_TO_METER);


            game.getBatch().draw(game.getAssetManager().get("pauseOn.png", Texture.class), VIRTUAL_SCREEN_WIDTH * 0.51f / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT * 0.205f / PIXEL_TO_METER, VIRTUAL_SCREEN_WIDTH * 0.1f / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT * 0.18f / PIXEL_TO_METER);
        }
        else
            game.getBatch().draw(game.getAssetManager().get("pauseOff.png", Texture.class), VIRTUAL_SCREEN_WIDTH * 0.51f / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT * 0.205f / PIXEL_TO_METER, VIRTUAL_SCREEN_WIDTH * 0.1f / PIXEL_TO_METER, VIRTUAL_SCREEN_HEIGHT * 0.18f / PIXEL_TO_METER);
    }

    /**
     * Reset all buttons textures to their default textures (yellow state)
     */
    private void resetButtons() {
        this.leftButton = buttonTextures.get(0);
        this.rightButton = buttonTextures.get(2);
        this.jumpButton = buttonTextures.get(4);
        this.shootButton = buttonTextures.get(6);
    }

    /**
     * Checks if button responsible to move character to left direction
     *
     * @param x x position of input
     * @param y y position of input
     * @return true if it's pressed, false otherwise
     */
    private boolean checkLeftButton(int x, int y) {
        return (x >= (int) (SCREEN_WIDTH * 0.05)) && (x <= (int) (SCREEN_WIDTH * 0.05 + BUTTON_WIDTH * PIXEL_TO_METER) &&
                (y <= ((int) (SCREEN_HEIGHT * 0.96))) && (y >= (int) (SCREEN_HEIGHT * 0.96 - BUTTON_HEIGHT * PIXEL_TO_METER)));
    }

    /**
     * Checks if button responsible to move character to right direction
     *
     * @param x x position of input
     * @param y y position of input
     * @return true if it's pressed, false otherwise
     */
    private boolean checkRightButton(int x, int y) {
        return (x >= (int) (SCREEN_WIDTH * 0.05 + BUTTON_WIDTH * PIXEL_TO_METER + SCREEN_WIDTH * 0.01)) && (x <= (int) (SCREEN_WIDTH * 0.05 + BUTTON_WIDTH * PIXEL_TO_METER + SCREEN_WIDTH * 0.01 + BUTTON_WIDTH * PIXEL_TO_METER) &&
                (y <= ((int) (SCREEN_HEIGHT * 0.96))) && (y >= (int) (SCREEN_HEIGHT * 0.96 - BUTTON_HEIGHT * PIXEL_TO_METER)));
    }

    /**
     * Checks if button responsible to make character jump
     *
     * @param x x position of input
     * @param y y position of input
     * @return true if it's pressed, false otherwise
     */
    private boolean checkJumpButton(int x, int y) {
        return (x <= ((int) (SCREEN_WIDTH * 0.89) + BUTTON_WIDTH * PIXEL_TO_METER)) && (x >= (int) (SCREEN_WIDTH * 0.89)) &&
                (y >= ((int) (SCREEN_HEIGHT - (SCREEN_HEIGHT * 0.04 + BUTTON_HEIGHT * PIXEL_TO_METER + SCREEN_HEIGHT * 0.01) - BUTTON_HEIGHT * PIXEL_TO_METER))) &&
                (y <= ((int) (SCREEN_HEIGHT - (SCREEN_HEIGHT * 0.04 + BUTTON_HEIGHT * PIXEL_TO_METER + SCREEN_HEIGHT * 0.01))));
    }

    /**
     * Checks if button responsible to make character shoot
     *
     * @param x x position of input
     * @param y y position of input
     * @return true if it's pressed, false otherwise
     */
    private boolean checkBulletButton(int x, int y) {
        return (x <= ((int) (SCREEN_WIDTH * 0.89) + BUTTON_WIDTH * PIXEL_TO_METER)) && (x >= (int) (SCREEN_WIDTH * 0.89)) &&
                (y >= ((int) (SCREEN_HEIGHT - (SCREEN_HEIGHT * 0.04 + BUTTON_HEIGHT * PIXEL_TO_METER)))) &&
                (y <= ((int) (SCREEN_HEIGHT - SCREEN_HEIGHT * 0.04)));
    }

    /**
     * Swaps between players
     *
     * @param x x position of input
     * @param y y position of input
     * @return true if it's pressed, false otherwise
     */
    private boolean swapPlayers(int x, int y) {
        return ((x >= (int) (SCREEN_WIDTH * 0.025)) && (x <= (int) (SCREEN_WIDTH * 0.065)) &&
                (y >= ((int) (SCREEN_HEIGHT * 0.4))) && (y <= (int) (SCREEN_HEIGHT * 0.47)));
    }

    /**
     * Checks if pause button is pressed
     *
     * @param x x position of input
     * @param y y position of input
     * @return true if it's pressed, false otherwise
     */
    private boolean checkPause(int x, int y) {
        return ((x >= (int) (SCREEN_WIDTH * 0.51f)) && (x <= (int) (SCREEN_WIDTH * 0.61)) &&
                (y >= ((int) (SCREEN_HEIGHT * 0.615))) && (y <= (int) (SCREEN_HEIGHT * 0.795)));
    }

    /**
     * Checks whether player's completed the game or if they have lose
     */
    private void checkGameStatus() {

        if ((GameController.getInstance(this.game).getState() == GameController.GameStatus.GAMEOVER) || (GameController.getInstance(this.game).getState() == GameController.GameStatus.VICTORY)){
            GameController.GameStatus status = GameController.getInstance(this.game).getState();
            this.dispose();
            this.game.setScreen(new TransitiveScreen(this.game, status));
        }

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Checks if jump or shoot button was pressed or not
     *
     * @param screenX x position of input
     * @param screenY y position of input
     * @param pointer pointer representing the input
     * @param button  button pressed
     * @return false if game is pause, true otherwise
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(checkPause(screenX,screenY)) {
            paused = !paused;

            if(paused && SettingsScreen.soundActive)
                SettingsScreen.playGameMusic.setVolume(SettingsScreen.playGameMusic.getVolume()/10);
            else if(!paused && SettingsScreen.soundActive)
                SettingsScreen.playGameMusic.setVolume(SettingsScreen.playGameMusic.getVolume()*10);
        }

        if(paused)
            return false;

        if (checkJumpButton(screenX, screenY)) {
            jumpButton = buttonTextures.get(5);

            if ((selectedPlayer) && (GameController.getInstance(this.game).getRobot().getBody().getLinearVelocity().y == 0))
                GameController.getInstance(this.game).getRobot().getBody().applyLinearImpulse(new Vector2(0, 4f), GameController.getInstance(this.game).getRobot().getBody().getWorldCenter(), true);
            else if ((!selectedPlayer) && (GameController.getInstance(this.game).getNinja().getBody().getLinearVelocity().y == 0))
                GameController.getInstance(this.game).getNinja().getBody().applyLinearImpulse(new Vector2(0, 4f), GameController.getInstance(this.game).getNinja().getBody().getWorldCenter(), true);

        }

        if (checkBulletButton(screenX, screenY) && selectedPlayer) {
            shootButton = buttonTextures.get(7);
            GameModel.getInstance(this.game).createBullet(GameView.getInstance(this.game).getRobot().getElement().getX(), GameView.getInstance(this.game).getRobot().getElement().getY(), ((RobotView) GameView.getInstance(this.game).getRobot()).isRunningRight());
        }

        if (swapPlayers(screenX, screenY))
             selectedPlayer = !selectedPlayer;


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (checkLeftButton(screenX, screenY))
            leftButton = buttonTextures.get(0);

        if (checkRightButton(screenX, screenY))
            rightButton = buttonTextures.get(2);

        if (checkJumpButton(screenX, screenY))
            jumpButton = buttonTextures.get(4);

        if (checkBulletButton(screenX, screenY))
            shootButton = buttonTextures.get(6);

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    /**
     * Resets camera and disposes unneeded elements
     */
    @Override
    public void dispose() {
        this.resetCamera();
        this.mapRenderer.dispose();
        super.dispose();
    }
}
