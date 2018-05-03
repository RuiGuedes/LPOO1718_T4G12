package com.ubros.game.Gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ubros.game.UbrosGame;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class PlayGameScreen extends ScreenAdapter {

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


    ///////////////////////////


    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    private boolean CLIENT_CREATED = false;

    private ArrayList<String> network_ips = new ArrayList<String>();

    /**
     *  Array that contains all textures to represent active and inactive buttons
     */
    private Texture[] menuButtons = new Texture[6];

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public PlayGameScreen(UbrosGame game) {
        this.game = game;

        loadAssets();
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

        if(!CLIENT_CREATED) {
            checkHosts("192.168.0");
            initializeClient();
            CLIENT_CREATED = true;
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

    public void checkHosts(String subnet){

        List<String> addresses = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        String host_ip_adress = address.getHostAddress();
                        if(host_ip_adress.contains("192.168.0."))
                            addresses.add(host_ip_adress);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // Print the contents of our array to a string.  Yeah, should have used StringBuilder
        String ipAddress = new String("");
        for(String str:addresses)
        {
            ipAddress = ipAddress + str + "\n";
        }

        System.out.print("MY IP ADRESS IS :: ");
        System.out.print(ipAddress);

        int timeout=1000;
        for (int i=97;i<105;i++){
            String host=subnet + "." + i;
            try {
                if (InetAddress.getByName(host).isReachable(timeout)){
                    System.out.println(host + " is reachable");
                    network_ips.add(host);
                }
                else
                    System.out.println(InetAddress.getByName(host) + " not reachable");
            } catch (IOException e) {
                System.out.println("EXCEPTION TRHOWED");
                return;
            }
        }

        System.out.println("OUT FUNCTION");
    }

    private Socket client;
    private Scanner connection_client;
    private PrintStream connection_server;

    private void initializeClient() {

        try {
            client = new Socket(network_ips.get(0), 4444);

            System.out.println("CLIENT - SERVER :: CONNECTED :: " + network_ips.get(0));

            connection_client = new Scanner(client.getInputStream());

            connection_server = new PrintStream(client.getOutputStream());

            ClientWait var = new ClientWait(this, client,connection_client,connection_server);
            var.start();

        } catch (IOException e) {
            System.out.println("CLIENT - SERVER :: CONNECTION ERROR :: " + network_ips.get(0));
            network_ips.remove(0);
            initializeClient();
        }
    }
}

class ClientWait extends Thread {

    private PlayGameScreen connect;
    private Socket client;
    private Scanner connection_client;
    private PrintStream connection_server;

    public ClientWait(PlayGameScreen connect, Socket client, Scanner connection_client, PrintStream connection_server) {
        this.connect = connect;
        this.client = client;
        this.connection_client = connection_client;
        this.connection_server = connection_server;
    }
    public void run() {

        while(true) {

            if(Gdx.input.isTouched()) {
                System.out.println("SEND TO SERVER VALUE 0");
                connection_server.println("0");
            }
            else {
                System.out.println("SEND TO SERVER VALUE 1");
                connection_server.println("1");
            }

            int number = connection_client.nextInt();

            if (number == 0) {
                System.out.println("RECEIVED FROM SERVER VALUE " + number);
                connect.MENU_ID = false;
            }
            else {
                System.out.println("RECEIVED FROM SERVER VALUE " + number);
                connect.MENU_ID = true;
            }
        }
    }

}
