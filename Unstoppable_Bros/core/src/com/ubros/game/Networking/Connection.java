package com.ubros.game.Networking;

import com.ubros.game.UbrosGame;

import java.io.IOException;

public class Connection {

    /**
     *  ServerConnection class where connections will be made
     */
    private ServerConnection server;

    /**
     *  ClientConnection class responsible to init a client instance and connect it to the server
     */
    private ClientConnection client;

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;


    public boolean MENU_ID = false;

    /**
     *  Creates both server and client objects
     */
    public Connection(UbrosGame game) {
        this.game = game;

        server = new ServerConnection(this);
        client = new ClientConnection(this);
    }

    /**
     * Function responsible to retrieve server object
     * @return server
     */
    public ServerConnection getServer() {
        return this.server;
    }

    /**
     * Function responsible to close server
     */
    public void closeServer() {
        try {
            server.getServer().close();
        } catch (IOException e) {
            System.out.println("Error on closing server");
        }
    }

    /**
     * Function responsible to access server connection established status
     * @return true if connection was already established false otherwise
     */
    public boolean getConnectionEstablishedStatus() {
        return server.CONNECTION_ESTABLISHED;
    }

    /**
     * Function responsible to retrieve client object
     * @return client
     */
    public ClientConnection getClient() {
        return this.client;
    }

    public void setClient(ClientConnection client) {
        this.client = client;
    }

    public UbrosGame getGame() {
        return game;
    }
}