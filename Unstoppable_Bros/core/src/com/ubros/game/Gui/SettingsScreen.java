package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.UbrosGame;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SettingsScreen extends ScreenAdapter {

    ////////////////////////////

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
    private static final int EXIT_BUTTON_YPOS = (int) (SCREEN_HEIGHT * 0.1);

    /**
     * Settings button width
     */
    private static final int SETTINGS_BUTTON_WIDTH = (int)(SCREEN_WIDTH*0.4);

    /**
     * Settings button yy position
     */
    private static final int SETTINGS_BUTTON_YPOS = (int)(SCREEN_HEIGHT*0.31);

    /**
     * Play button width
     */
    private static final int PLAY_BUTTON_WIDTH = (int)(SCREEN_WIDTH*0.5);

    /**
     * Play button yy position
     */
    private static final int PLAY_BUTTON_YPOS = (int)(SCREEN_HEIGHT*0.5);

    /**
     * Every button height
     */
    private static final int BUTTON_HEIGHT = (int)(SCREEN_HEIGHT*0.13);

    /**
     *  Array that contains all textures to represent active and inactive buttons
     */
    private Texture[] menuButtons = new Texture[6];

    ///////////////////////////

    private boolean SERVER_CREATED = false;

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public SettingsScreen(UbrosGame game) {
        this.game = game;

        loadAssets();

    }

    private ServerSocket server;
    private Socket client;
    private Scanner connection_server;
    private PrintStream connection_client;

    private void initializeServer() {

        try {
            server = new ServerSocket(4444,2);

            client = server.accept();

            connection_server = new Scanner(client.getInputStream());

            connection_client = new PrintStream(client.getOutputStream());

        } catch (IOException e) {
        }

    }

    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);
        this.game.getAssetManager().load("MainMenuExitButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOff.png",Texture.class);
        this.game.getAssetManager().finishLoading();
        initializeTextures();
    }

    /**
     *  Initializes textures array previously defined
     */
    private void initializeTextures() {
        menuButtons[0] = game.getAssetManager().get("MainMenuExitButtonOff.png", Texture.class);
        menuButtons[1] = game.getAssetManager().get("MainMenuSettingsButtonOff.png",Texture.class);
        menuButtons[2] = game.getAssetManager().get("MainMenuPlayButtonOff.png",Texture.class);
        menuButtons[3] = game.getAssetManager().get("MainMenuExitButtonOn.png", Texture.class);
        menuButtons[4] = game.getAssetManager().get("MainMenuSettingsButtonOn.png",Texture.class);
        menuButtons[5] = game.getAssetManager().get("MainMenuPlayButtonOn.png",Texture.class);
    }

    public boolean MENU_ID = true;

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

        if(MENU_ID)
            defaultMainMenu();
        else
            activateSettingsButton();

        game.getBatch().end();


        if(!SERVER_CREATED) {
            initializeServer();
            ServerWait var = new ServerWait(this,client,connection_server,connection_client);
            var.start();
            SERVER_CREATED = true;
        }
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.jpg", Texture.class);
        game.getBatch().draw(background, 0, 0,SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    private void activateSettingsButton() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[4], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void defaultMainMenu() {
        game.getBatch().draw(menuButtons[0], SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_YPOS, EXIT_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[1], SCREEN_WIDTH / 2 - SETTINGS_BUTTON_WIDTH / 2, SETTINGS_BUTTON_YPOS, SETTINGS_BUTTON_WIDTH, BUTTON_HEIGHT);
        game.getBatch().draw(menuButtons[2], SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_YPOS, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
    }

}

class ServerWait extends Thread {

    private SettingsScreen connect;
    private ServerSocket server;
    private Socket client;
    private Scanner connection_server;
    private PrintStream connection_client;

    public ServerWait(SettingsScreen connect, Socket client, Scanner connection_server, PrintStream connection_client) {
        this.connect = connect;
        this.client = client;
        this.connection_client = connection_client;
        this.connection_server = connection_server;
    }
    public void run() {

        while(true) {

            int number = connection_server.nextInt();
            System.out.println("RECEIVED FROM CLIENT " + number);

            if (number == 0) {
                connect.MENU_ID = false;
                connection_client.println("0");
            }
            else {
                connect.MENU_ID = true;
                connection_client.println("1");
            }

            }
        }

}
