package com.ubros.game.Networking;

public class Connection {

    /**
     *  ServerConnection class where connections will be made
     */
    private ServerConnection server;

    /**
     *  ClientConnection class responsible to init a client instance and connect it to the server
     */
    private ClientConnection client;

    public boolean MENU_ID = false;

    public Connection() {

        server = new ServerConnection(this);

        client = new ClientConnection(this, server);

    }

}