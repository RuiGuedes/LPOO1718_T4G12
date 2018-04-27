package com.ubros.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.UbrosGame;

public class PlayGameScreen extends ScreenAdapter {

    private static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /////////////////////////////////


    /**
     * The physical world.
     */
    private final World world;

    /**
     * The ground body
     */
    private final Body groundBody;

    /**
     * The ball physical body
     */
    private final Body ballBody;


    ///////////////////////////////

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public PlayGameScreen(UbrosGame game) {
        this.game = game;

        loadAssets();

        // Create the physical world
        world = new World(new Vector2(0, -10), true);

        groundBody = createGroundBody();
        ballBody = createBallBody();
    }

    /**
     * Viewport width in meters. Height depends on screen ratio
     */
    private static final int VIEWPORT_WIDTH = 6;

    /**
     * A football is 22cm in diameter and the sprite has a width of 200px
     */
    private static final float PIXEL_TO_METER = 0.22f / 800;

    /**
     * Creates the ball body.
     */
    private Body createBallBody() {
        // Create the ball body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Create the ball body
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        Body body = world.createBody(bodyDef);
        body.setTransform(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0); // Middle of the viewport, no rotation

        // Create circle shape
        CircleShape circle = new CircleShape();
        circle.setRadius(0.11f); // 22cm / 2

        // Create ball fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = .5f;      // how heavy is the ball
        fixtureDef.friction =  .5f;    // how slippery is the ball
        fixtureDef.restitution =  .5f; // how bouncy is the ball

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        circle.dispose();

        return body;
    }


    private Body createGroundBody() {
        // Create the ground body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        // Create the ground body
        Body body = world.createBody(bodyDef);
        body.setTransform(0, 0, 0); // Bottom left corner

        // Create rectangular shape
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(SCREEN_WIDTH, (int)(SCREEN_HEIGHT*0.05)); // Viewport width and 50cm height

        // Create ground fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = .5f;      // how heavy is the ground
        fixtureDef.friction =  .5f;    // how slippery is the ground
        fixtureDef.restitution =  .5f; // how bouncy is the ground

        // Attach fixture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        rectangle.dispose();

        return body;
    }


    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().load("ground.jpg", Texture.class);
        this.game.getAssetManager().load("ball.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {

        super.render(delta);

        // Update the world
        world.step(delta, 6, 2);

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawBackground();
        drawGround();
        drawBall();
        game.getBatch().end();

    }

    private void drawBall() {
        Texture ballTexture = game.getAssetManager().get("ball.png");
        game.getBatch().draw(ballTexture, (ballBody.getPosition().x -.11f) / PIXEL_TO_METER,  (ballBody.getPosition().y - .11f) / PIXEL_TO_METER);


        //game.getBatch().draw(ballTexture, ballBody.getPosition().x, ballBody.getPosition().y,100,100);
    }

    private void drawGround() {
        Texture groundTexture = game.getAssetManager().get("ground.jpg");
        groundTexture.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);

        game.getBatch().draw(groundTexture, 0, 0, 0, 0, SCREEN_WIDTH, (int)(SCREEN_HEIGHT*0.05));
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.jpg", Texture.class);
        game.getBatch().draw(background, 0, 0,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

}
