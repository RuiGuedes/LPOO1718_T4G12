package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ubros.game.Controller.GameController;
import com.ubros.game.UbrosGame;

public class PlayGameScreen extends ScreenAdapter {

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
    private static final float ASPECT_RATIO = SCREEN_HEIGHT/SCREEN_WIDTH;

    /**
     *
     */
    private static final float WORLD_WIDTH = 50;


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


    private Viewport viewport;

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
        UbrosGame.map = this.mapLoader.load("UbrosMap.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(UbrosGame.map, 1/PIXEL_TO_METER);


        loadAssets();
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
        this.game.getAssetManager().finishLoading();

    }

    public void handleInput(float delta) {

        if(Gdx.input.justTouched()) {

            int y = Gdx.input.getY();

            if(y < SCREEN_HEIGHT/2)
                GameController.getInstance(this.game).getHero().body.applyLinearImpulse(new Vector2(0,4f),GameController.getInstance(this.game).getHero().body.getWorldCenter(),true );
        }

        if(Gdx.input.isTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if((x > SCREEN_WIDTH/2) && (y > SCREEN_HEIGHT/2) && (GameController.getInstance(this.game).getHero().body.getLinearVelocity().x <= 2))
                GameController.getInstance(this.game).getHero().body.applyLinearImpulse(new Vector2(0.1f,0),GameController.getInstance(this.game).getHero().body.getWorldCenter(),true );

        }

        if(Gdx.input.isTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if((x < (SCREEN_WIDTH / 2)) && (y > SCREEN_HEIGHT/2) && (GameController.getInstance(this.game).getHero().body.getLinearVelocity().x >= -2))
                GameController.getInstance(this.game).getHero().body.applyLinearImpulse(new Vector2(-0.1f,0),GameController.getInstance(this.game).getHero().body.getWorldCenter(),true );

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
        //Draw something
        game.getBatch().end();

    }

}
