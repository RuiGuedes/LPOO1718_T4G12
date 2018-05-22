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
        this.mapRenderer = new OrthogonalTiledMapRenderer(UbrosGame.map, 1/PIXEL_TO_METER);


        Gdx.input.setInputProcessor(this);

        loadAssets();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void createCamera() {

        this.gameCam = new OrthographicCamera(SCREEN_WIDTH / PIXEL_TO_METER, SCREEN_HEIGHT/PIXEL_TO_METER);

        this.gameCam.position.set(gameCam.viewportWidth / 2f, gameCam.viewportHeight / 2f, 0);
        this.gameCam.update();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().load("Robot/Robot.pack", TextureAtlas.class);

        this.game.getAssetManager().finishLoading();
        atlas = this.game.getAssetManager().get("Robot/Robot.pack");
        hero = new HeroView(this.game, atlas);

    }

    public void handleInput(float delta) {

        for(int i = 0; i < 3; i++) {
            if (Gdx.input.isTouched(i)) {

                int x = Gdx.input.getX(i);
                int y = Gdx.input.getY(i);

                if ((x > SCREEN_WIDTH / 2) && (y > SCREEN_HEIGHT / 2) && (GameController.getInstance(this.game).getHero().body.getLinearVelocity().x <= 3))
                    GameController.getInstance(this.game).getHero().body.applyLinearImpulse(new Vector2(0.3f, 0), GameController.getInstance(this.game).getHero().body.getWorldCenter(), true);

                if ((x < (SCREEN_WIDTH / 2)) && (y > SCREEN_HEIGHT / 2) && (GameController.getInstance(this.game).getHero().body.getLinearVelocity().x >= -3))
                    GameController.getInstance(this.game).getHero().body.applyLinearImpulse(new Vector2(-0.3f, 0), GameController.getInstance(this.game).getHero().body.getWorldCenter(), true);

            }
        }
    }


    public void update(float delta) {

        handleInput(delta);

        GameController.getInstance(this.game).getWorld().step(1/60f, 6, 2);
        GameController.getInstance(this.game).update(delta);

        game.getBatch().setProjectionMatrix(gameCam.combined);
        gameCam.update();
        this.mapRenderer.setView(gameCam);

    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {

        this.update(delta);

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        this.mapRenderer.render();
        GameController.getInstance(this.game).getDebugRenderer().render(GameController.getInstance(this.game).getWorld(), gameCam.combined);

        game.getBatch().begin();
        drawElements(delta);
        game.getBatch().end();

    }

    public void drawElements(float delta) {

        hero.update(delta, GameController.getInstance(this.game).getHero());
        hero.draw(this.game.getBatch());

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

        if (screenY < SCREEN_HEIGHT / 2)
            GameController.getInstance(this.game).getHero().body.applyLinearImpulse(new Vector2(0, 4f), GameController.getInstance(this.game).getHero().body.getWorldCenter(), true);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Up");
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
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
        UbrosGame.map.dispose();
        this.mapRenderer.dispose();
        GameController.getInstance(this.game).getWorld().dispose();
        GameController.getInstance(this.game).getDebugRenderer().dispose();
    }
}
