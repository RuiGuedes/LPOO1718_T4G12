package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.GameController;
import com.ubros.game.UbrosGame;

public class PlayGameScreen extends ScreenAdapter {

    /**
     * Device screen width
     */
    private static final int SCREEN_WIDTH = Gdx.graphics.getWidth();

    /**
     * Device screen height
     */
    private static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

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

    private World world;

    private Box2DDebugRenderer b2dr;


    ////////////////////////////

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public PlayGameScreen(UbrosGame game) {
        this.game = game;

        this.gameCam = new OrthographicCamera();
        this.gameCam.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);
        this.gameCam.update();

        this.mapLoader = new TmxMapLoader();
        UbrosGame.map = this.mapLoader.load("UbrosMap.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(UbrosGame.map);

        /*
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        Body body;


        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(PolygonMapObject.class)) {
            Polygon rect = ((PolygonMapObject) object).getPolygon();

            System.out.println("ASAAS : " + rect.getBoundingRectangle().getWidth());

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX(), rect.getY());

            body = world.createBody(bdef);

            shape.set(rect.getVertices());
            fdef.shape = shape;
            body.createFixture(fdef);
        }*/


        loadAssets();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().finishLoading();

    }

    public void update(float delta) {

        GameController.getInstance(this.game).update(delta);

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
