package com.ubros.game.Networking;

import com.ubros.game.UbrosGame;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Connection class
 *
 * @author Rui Guedes
 */
public class Connection {

    /**
     * ServerConnection class where connections will be made
     */
    private ServerConnection server;

    /**
     * ClientConnection class responsible to init a client instance and connect it to the server
     */
    private ClientConnection client;

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Personal IP Address
     */
    private String myIPAddress;

    /**
     * IP address splitted components
     */
    private String[] mySplittedIPAddress;

    /**
     * Creates both server and client objects
     *
     * @param game game object
     */
    public Connection(UbrosGame game) {
        this.game = game;

        searchingMyIPAddress();
    }

    /**
     * Starts both server and client connection
     */
    public void startConnection() {
        server = new ServerConnection(this);
        client = new ClientConnection(this);
    }

    /**
     * Responsible to determine this user IP Address
     */
    private void searchingMyIPAddress() {

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            for (NetworkInterface ni : Collections.list(interfaces)) {
                for (InetAddress address : Collections.list(ni.getInetAddresses())) {
                    if (address instanceof Inet4Address) {
                        String hostIPAddress = address.getHostAddress();

                        if (!hostIPAddress.equals("127.0.0.1")) {
                            mySplittedIPAddress = hostIPAddress.split("\\.");
                            myIPAddress = hostIPAddress;
                            return;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error occurred while searching for my IP address");
        }
    }

    /**
     * Function responsible to retrieve server object
     *
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
     *
     * @return true if connection was already established false otherwise
     */
    public boolean getConnectionEstablishedStatus() {
        return server.CONNECTION_ESTABLISHED;
    }

    /**
     * Function responsible to retrieve client object
     *
     * @return client
     */
    public ClientConnection getClient() {
        return this.client;
    }

    /**
     * Set's server new client
     *
     * @param client new client
     */
    public void setClient(ClientConnection client) {
        this.client = client;
    }

    /**
     * Ubros game instance
     *
     * @return game instance
     */
    public UbrosGame getGame() {
        return game;
    }

    /**
     * Returns user IP address
     *
     * @return user IP address
     */
    public String getMyIPAddress() {
        return myIPAddress;
    }

    /**
     * Ip address splitted in portions
     *
     * @return Ip address splitted in portions
     */
    public String[] getMySplittedIPAddress() {
        return mySplittedIPAddress;
    }

    /**
     * Returns type of connection
     *
     * @return User connection (192.168 - true), false for private connections
     */
    public boolean getTypeOfConnection() {
        return myIPAddress.contains("192.168");
    }
}