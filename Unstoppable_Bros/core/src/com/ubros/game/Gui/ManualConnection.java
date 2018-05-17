package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.Networking.Connection;
import com.ubros.game.UbrosGame;

import java.util.HashMap;

public class ManualConnection extends ScreenAdapter {

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
     * Buttons width
     */
    private static final int BUTTON_WIDTH = (int)(SCREEN_WIDTH*0.4);

    /**
     * Buttons yy position
     */
    private static final int BUTTON_YPOS = (int)(SCREEN_HEIGHT*0.37);

    /**
     * Buttons height
     */
    private static final int BUTTON_HEIGHT = (int)(SCREEN_HEIGHT*0.13);


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
    public ManualConnection(UbrosGame game) {
        this.game = game;

        connect = new Connection(this.game);

        loadAssets();
    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().load("ConnectingTitle.png",Texture.class);
        this.game.getAssetManager().load("ConnectingLoad.png",Texture.class);
        this.game.getAssetManager().load("HostGameOn.png",Texture.class);
        this.game.getAssetManager().load("HostGameOff.png",Texture.class);
        this.game.getAssetManager().load("JoinGameOn.png",Texture.class);
        this.game.getAssetManager().load("JoinGameOff.png",Texture.class);

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

        if(Gdx.input.isTouched()) {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if(checkJoinGameButton(x,y)) {
                activateJoinGameButton();
            }
            else if(checkHostGameButton(x,y)) {
                activateHostGameButton();
            }
            else
                defaultMenu();

        }
        else
            defaultMenu();

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

    private void defaultMenu() {
        game.getBatch().draw(game.getAssetManager().get("HostGameOff.png", Texture.class), SCREEN_WIDTH / 4 - BUTTON_WIDTH / 2, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(game.getAssetManager().get("JoinGameOff.png", Texture.class), (int)(SCREEN_WIDTH * 0.75) - BUTTON_WIDTH / 2, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        displayIP();
    }

    private void displayIP(){

        int xPos = (int)(SCREEN_WIDTH*0.1);
        int yPos = BUTTON_YPOS - (int)(SCREEN_HEIGHT*0.08);

        for(int i=0; i < connect.getMyIPAddress().length(); i++) {
            if(connect.getMyIPAddress().charAt(i) != '.') {
                game.getBatch().draw(ipDisplay.get(connect.getMyIPAddress().charAt(i)), xPos, yPos, (int) (SCREEN_WIDTH * 0.025), (int) (SCREEN_HEIGHT * 0.05));
                xPos += (int) (SCREEN_WIDTH * 0.025);
            }
            else {
                game.getBatch().draw(ipDisplay.get(connect.getMyIPAddress().charAt(i)), xPos, yPos, (int) (SCREEN_WIDTH * 0.0125), (int) (SCREEN_HEIGHT * 0.0125));
                xPos += (int) (SCREEN_WIDTH * 0.0125);
            }
        }
    }

    private boolean checkJoinGameButton(int x, int y) {
        return ((x >= (SCREEN_WIDTH * 0.55))
                && (y >= (SCREEN_HEIGHT/2)) && (y <= (SCREEN_HEIGHT/2 + BUTTON_HEIGHT)));
    }

    private boolean checkHostGameButton(int x, int y) {
        return ((x <= (SCREEN_WIDTH * 0.45))
                && (y >= (SCREEN_HEIGHT/2)) && (y <= (SCREEN_HEIGHT/2 + BUTTON_HEIGHT)));
    }

    private void activateHostGameButton() {
        game.getBatch().draw(game.getAssetManager().get("HostGameOn.png", Texture.class), SCREEN_WIDTH / 4 - BUTTON_WIDTH / 2, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(game.getAssetManager().get("JoinGameOff.png", Texture.class), (int)(SCREEN_WIDTH * 0.75) - BUTTON_WIDTH / 2, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        displayIP();
    }

    private void activateJoinGameButton() {
        game.getBatch().draw(game.getAssetManager().get("HostGameOff.png", Texture.class), SCREEN_WIDTH / 4 - BUTTON_WIDTH / 2, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(game.getAssetManager().get("JoinGameOn.png", Texture.class), (int)(SCREEN_WIDTH * 0.75) - BUTTON_WIDTH / 2, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        displayIP();
    }

}
