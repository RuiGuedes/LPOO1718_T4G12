package com.ubros.game.Networking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.UbrosGame;

import java.util.HashMap;

public class AutomaticConnectionScreen extends ScreenAdapter {

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
    private static final int TITLE_WIDTH = (int) (SCREEN_WIDTH * 0.7);

    /**
     * Exit button yy position
     */
    private static final int TITLE_YPOS = (int) (SCREEN_HEIGHT * 0.75);

    /**
     * Title height
     */
    private static final int TITLE_HEIGHT = (int)(SCREEN_HEIGHT*0.15);


    /**
     * Loading connection sprite
     */
    private Sprite loadingSprite;

    /**
     * Ip texture numbers
     */
    private HashMap<Character,Texture> ipDisplay = new HashMap<Character, Texture>();

    /**
     * Contains information about server and client status
     */
    private Connection connect;

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public AutomaticConnectionScreen(UbrosGame game) {
        this.game = game;

        connect = new Connection(this.game);

        loadAssets();

        StartConnection thread = new StartConnection(connect);
        thread.start();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().load("ConnectingTitle.png",Texture.class);
        this.game.getAssetManager().load("ConnectingLoad.png",Texture.class);

        this.game.getAssetManager().load("Number0.png",Texture.class);
        this.game.getAssetManager().load("Number1.png",Texture.class);
        this.game.getAssetManager().load("Number2.png",Texture.class);
        this.game.getAssetManager().load("Number3.png",Texture.class);
        this.game.getAssetManager().load("Number4.png",Texture.class);
        this.game.getAssetManager().load("Number5.png",Texture.class);
        this.game.getAssetManager().load("Number6.png",Texture.class);
        this.game.getAssetManager().load("Number7.png",Texture.class);
        this.game.getAssetManager().load("Number8.png",Texture.class);
        this.game.getAssetManager().load("Number9.png",Texture.class);
        this.game.getAssetManager().load("IPDot.png",Texture.class);


        this.game.getAssetManager().finishLoading();
        initializeSprite();
        initializeIPTextures();
    }

    /**
     * Initializes hasp map with respective number textures
     */
    private void initializeIPTextures() {
        ipDisplay.put('0', game.getAssetManager().get("Number0.png", Texture.class));
        ipDisplay.put('1', game.getAssetManager().get("Number1.png", Texture.class));
        ipDisplay.put('2', game.getAssetManager().get("Number2.png", Texture.class));
        ipDisplay.put('3', game.getAssetManager().get("Number3.png", Texture.class));
        ipDisplay.put('4', game.getAssetManager().get("Number4.png", Texture.class));
        ipDisplay.put('5', game.getAssetManager().get("Number5.png", Texture.class));
        ipDisplay.put('6', game.getAssetManager().get("Number6.png", Texture.class));
        ipDisplay.put('7', game.getAssetManager().get("Number7.png", Texture.class));
        ipDisplay.put('8', game.getAssetManager().get("Number8.png", Texture.class));
        ipDisplay.put('9', game.getAssetManager().get("Number9.png", Texture.class));
        ipDisplay.put('.', game.getAssetManager().get("IPDot.png", Texture.class));
    }

    /**
     *  Initializes loading connecting sprite
     */
    private void initializeSprite() {
        Texture connectingLoad = game.getAssetManager().get("ConnectingLoad.png", Texture.class);
        loadingSprite = new Sprite(connectingLoad);
        loadingSprite.setPosition(SCREEN_WIDTH/2 - SCREEN_HEIGHT/4, (int)(SCREEN_HEIGHT*0.15));
        loadingSprite.setSize(SCREEN_HEIGHT/2, SCREEN_HEIGHT/2);
        loadingSprite.setRotation(0);
        loadingSprite.setOrigin(SCREEN_HEIGHT/4, SCREEN_HEIGHT/4);
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
        drawConnectingTitle();

        automaticConnection();

        if (connect.getConnectionEstablishedStatus()) {
            UbrosGame.playGame = new PlayGameScreen(this.game, connect.type);
            ((PlayGameScreen)UbrosGame.playGame).connect = connect;
            game.setScreen(UbrosGame.playGame);
        }

        game.getBatch().end();
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.jpg", Texture.class);
        game.getBatch().draw(background, 0, 0,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    private void drawConnectingTitle() {
        Texture connecting = game.getAssetManager().get("ConnectingTitle.png", Texture.class);
        game.getBatch().draw(connecting, SCREEN_WIDTH / 2 - TITLE_WIDTH / 2, TITLE_YPOS, TITLE_WIDTH, TITLE_HEIGHT);
    }

    private void automaticConnection() {
        loadingSprite.setRotation(loadingSprite.getRotation() - 4);
        loadingSprite.draw(game.getBatch());
    }

}

class StartConnection extends Thread {

    /**
     * Connection object that contains both server and client
     */
    private Connection connect;

    /**
     * Initializes both connect and connection objects
     * @param connect Connection object
     */
    StartConnection(Connection connect) {
        this.connect = connect;
    }

    /**
     * Waits until a client connects to server and launch´s another thread to start communicate with the server
     */
    public void run() {
        connect.startConnection();
    }
}