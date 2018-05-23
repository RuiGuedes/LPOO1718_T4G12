package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.ubros.game.Controller.GameController;
import com.ubros.game.UbrosGame;
import com.ubros.game.View.Elements.ElementView;
import com.ubros.game.View.Elements.HeroView;

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
     *
     */
    public static final float PIXEL_TO_METER = 100;

    private static final float BUTTON_WIDTH = (int) (SCREEN_WIDTH * 0.08) / PIXEL_TO_METER;

    private static final float BUTTON_HEIGHT = (int) (SCREEN_HEIGHT * 0.08) / PIXEL_TO_METER;


    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Game
     */
    private OrthographicCamera gameCam;

    /**
     * Loads tiled map into the game
     */
    private TmxMapLoader mapLoader;

    /**
     * Renders tiled map through out the screen
     */
    private OrthogonalTiledMapRenderer mapRenderer;


    private Texture leftButton;
    private Texture rightButton;
    private Texture bulletButton;
    private Texture jumpButton;

    private List<Texture> buttonTextures = new ArrayList<Texture>();

    ////////////////////////////


    private TextureAtlas atlas;

    private ElementView hero;

    ////////////////////////////

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public PlayGameScreen(UbrosGame game) {

        this.game = game;

        createCamera();

        this.mapLoader = new TmxMapLoader();
        UbrosGame.map = this.mapLoader.load("UbrosMap/UbrosMap.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(UbrosGame.map, 1 / PIXEL_TO_METER);


        Gdx.input.setInputProcessor(this);

        loadAssets();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void createCamera() {

        this.gameCam = new OrthographicCamera(SCREEN_WIDTH / PIXEL_TO_METER, SCREEN_HEIGHT / PIXEL_TO_METER);

        this.gameCam.position.set(gameCam.viewportWidth / 2f, gameCam.viewportHeight / 2f, 0);
        this.gameCam.update();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().load("moveLeftButtonOff.png", Texture.class);
        this.game.getAssetManager().load("moveLeftButtonOn.png", Texture.class);
        this.game.getAssetManager().load("moveRightButtonOff.png", Texture.class);
        this.game.getAssetManager().load("moveRightButtonOn.png", Texture.class);
        this.game.getAssetManager().load("jumpButtonOff.png", Texture.class);
        this.game.getAssetManager().load("jumpButtonOn.png", Texture.class);
        this.game.getAssetManager().load("bulletButtonOff.png", Texture.class);
        this.game.getAssetManager().load("bulletButtonOn.png", Texture.class);
        this.game.getAssetManager().load("Robot/Robot.pack", TextureAtlas.class);

        this.game.getAssetManager().finishLoading();
        initializeGraphics();
    }

    public void initializeGraphics() {

        this.atlas = this.game.getAssetManager().get("Robot/Robot.pack");
        this.hero = new HeroView(this.game, atlas);

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


    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {

        this.update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        this.mapRenderer.render();
        GameController.getInstance(this.game).getDebugRenderer().render(GameController.getInstance(this.game).getWorld(), gameCam.combined);

        game.getBatch().begin();
        drawInteractiveButtons();
        drawElements(delta);
        game.getBatch().end();

    }

    public void update(float delta) {

        handleInput();

        GameController.getInstance(this.game).getWorld().step(1 / 60f, 6, 2);
        GameController.getInstance(this.game).update(delta);

        game.getBatch().setProjectionMatrix(gameCam.combined);
        gameCam.update();
        this.mapRenderer.setView(gameCam);

    }

    public void handleInput() {

        for (int i = 0; i < 4; i++) {
            if (Gdx.input.isTouched(i)) {
                int x = Gdx.input.getX(i);
                int y = Gdx.input.getY(i);

                if(checkLeftButton(x, y)) {
                    leftButton = buttonTextures.get(1);
                    if (GameController.getInstance(this.game).getHero().getBody().getLinearVelocity().x >= -3)
                        GameController.getInstance(this.game).getHero().getBody().applyLinearImpulse(new Vector2(-0.3f, 0), GameController.getInstance(this.game).getHero().getBody().getWorldCenter(), true);
                }

                if(checkRightButton(x, y)) {
                    rightButton = buttonTextures.get(3);
                    if ((GameController.getInstance(this.game).getHero().getBody().getLinearVelocity().x <= 3))
                        GameController.getInstance(this.game).getHero().getBody().applyLinearImpulse(new Vector2(0.3f, 0), GameController.getInstance(this.game).getHero().getBody().getWorldCenter(), true);
                }
            }
        }
    }

    public void drawElements(float delta) {
        hero.update(delta, GameController.getInstance(this.game).getHero());
        hero.draw(this.game.getBatch());

    }

    public void drawInteractiveButtons() {
        game.getBatch().draw(leftButton, (int) (SCREEN_WIDTH * 0.035) / PIXEL_TO_METER, (int) (SCREEN_HEIGHT * 0.04) / PIXEL_TO_METER, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(rightButton, (int) (SCREEN_WIDTH * 0.035 + BUTTON_WIDTH * PIXEL_TO_METER + SCREEN_WIDTH * 0.01) / PIXEL_TO_METER, (int) (SCREEN_HEIGHT * 0.04) / PIXEL_TO_METER, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(jumpButton, (int) (SCREEN_WIDTH * 0.89) / PIXEL_TO_METER, (int) (SCREEN_HEIGHT * 0.04 + BUTTON_HEIGHT * PIXEL_TO_METER + SCREEN_HEIGHT * 0.01) / PIXEL_TO_METER, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(bulletButton, (int) (SCREEN_WIDTH * 0.89) / PIXEL_TO_METER, (int) (SCREEN_HEIGHT * 0.04) / PIXEL_TO_METER, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public void resetButtons() {
        this.leftButton = buttonTextures.get(0);
        this.rightButton = buttonTextures.get(2);
        this.jumpButton = buttonTextures.get(4);
        this.bulletButton = buttonTextures.get(6);
    }

    public boolean checkLeftButton(int x, int y) {
        if ((x >= (int) (SCREEN_WIDTH * 0.035)) && (x <= (int) (SCREEN_WIDTH * 0.035 + BUTTON_WIDTH * PIXEL_TO_METER) &&
                (y <= ((int) (SCREEN_HEIGHT * 0.96))) && (y >= (int) (SCREEN_HEIGHT * 0.96 - BUTTON_HEIGHT * PIXEL_TO_METER))))
           return true;

        return false;
    }

    public boolean checkRightButton(int x, int y) {
        if ((x >= (int) (SCREEN_WIDTH * 0.035 + BUTTON_WIDTH * PIXEL_TO_METER + SCREEN_WIDTH * 0.01)) && (x <= (int) (SCREEN_WIDTH * 0.035 + BUTTON_WIDTH * PIXEL_TO_METER + SCREEN_WIDTH * 0.01 + BUTTON_WIDTH * PIXEL_TO_METER) &&
                (y <= ((int) (SCREEN_HEIGHT * 0.96))) && (y >= (int) (SCREEN_HEIGHT * 0.96 - BUTTON_HEIGHT * PIXEL_TO_METER))))
            return true;

        return false;
    }

    public boolean checkJumpButton(int x, int y) {
        if ((x <= ((int) (SCREEN_WIDTH * 0.89) + BUTTON_WIDTH * PIXEL_TO_METER)) && (x >= (int) (SCREEN_WIDTH * 0.89)) &&
                (y >= ((int) (SCREEN_HEIGHT - (SCREEN_HEIGHT * 0.04 + BUTTON_HEIGHT * PIXEL_TO_METER + SCREEN_HEIGHT * 0.01) - BUTTON_HEIGHT * PIXEL_TO_METER))) &&
                (y <= ((int) (SCREEN_HEIGHT - (SCREEN_HEIGHT * 0.04 + BUTTON_HEIGHT * PIXEL_TO_METER + SCREEN_HEIGHT * 0.01)))))
            return true;

        return false;
    }

    public boolean checkBulletButton(int x, int y) {
        if ((x <= ((int) (SCREEN_WIDTH * 0.89) + BUTTON_WIDTH * PIXEL_TO_METER)) && (x >= (int) (SCREEN_WIDTH * 0.89)) &&
                (y >= ((int) (SCREEN_HEIGHT - (SCREEN_HEIGHT * 0.04 + BUTTON_HEIGHT * PIXEL_TO_METER)))) &&
                (y <= ((int) (SCREEN_HEIGHT - SCREEN_HEIGHT * 0.04))))
            return true;

        return false;
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (checkJumpButton(screenX, screenY)) {
            jumpButton = buttonTextures.get(5);
            //if (GameController.getInstance(this.game).getHero().body.getLinearVelocity().y == 0)  -> TO AVOID DOUBLE JUMP
            GameController.getInstance(this.game).getHero().getBody().applyLinearImpulse(new Vector2(0, 4f), GameController.getInstance(this.game).getHero().getBody().getWorldCenter(), true);
        }

        if (checkBulletButton(screenX, screenY))
            bulletButton = buttonTextures.get(7);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(checkLeftButton(screenX,screenY))
            leftButton = buttonTextures.get(0);

        if(checkRightButton(screenX,screenY))
            rightButton = buttonTextures.get(2);

        if (checkJumpButton(screenX, screenY))
            jumpButton = buttonTextures.get(4);

        if (checkBulletButton(screenX, screenY))
            bulletButton = buttonTextures.get(6);

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

    @Override
    public void dispose() {
        UbrosGame.map.dispose();
        this.mapRenderer.dispose();
        GameController.getInstance(this.game).getWorld().dispose();
        GameController.getInstance(this.game).getDebugRenderer().dispose();
    }
}
