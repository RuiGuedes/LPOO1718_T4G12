package com.ubros.game.Networking;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * ServerConnection class
 *
 * @author Rui Guedes
 */
public class ServerConnection {

    /**
     * Determines whether connection is or is not established already
     */
    public boolean CONNECTION_ESTABLISHED = false;

    /**
     * Object that contains connection information
     */
    private Connection connect;

    /**
     * Server where it will made connections
     */
    private ServerSocket server;

    /**
     * Scanner that allows to receive information from client
     */
    private Scanner connection_from_client;

    /**
     * PrintStream that allows server send information to client
     */
    private PrintStream connection_to_client;

    /**
     * Initializes server connection object
     *
     * @param connect object that contains information about both server and client objects
     */
    ServerConnection(Connection connect) {
        this.connect = connect;

        initializeServer();
    }

    /**
     * Creates a new server socket and launch´s thread responsible to wait for clients
     */
    private void initializeServer() {

        int PORT_NUMBER = 4444;

        try {
            server = new ServerSocket(PORT_NUMBER);

            WaitForClient thread = new WaitForClient(connect, this);
            thread.start();

        } catch (IOException e) {
            System.out.println("Port number :: " + PORT_NUMBER + " :: may be occupied. Try another one");
        }
    }

    /**
     * Gets the server socket created
     *
     * @return returns server
     */
    public ServerSocket getServer() {
        return server;
    }

    /**
     * Changes the server scanner
     *
     * @param connection_from_client scanner used to communicate between server and client
     */
    public void setConnectionFromClient(Scanner connection_from_client) {
        this.connection_from_client = connection_from_client;
    }

    /**
     * Gets server scanner
     *
     * @return connection_from_client scanner
     */
    public Scanner getConnectionFromClient() {
        return connection_from_client;
    }

    /**
     * Changes the server PrintStream
     *
     * @param connection_to_client PrintStream used to communicate between server and client
     */
    public void setConnectionToClient(PrintStream connection_to_client) {
        this.connection_to_client = connection_to_client;
    }

    /**
     * Gets server printStream
     *
     * @return connection_to_client scanner
     */
    public PrintStream getConnectionToClient() {
        return connection_to_client;
    }
}

/**
 * WaitForClient class
 *
 * @author Rui Guedes
 */
class WaitForClient extends Thread {

    /**
     * Connection object that contains both server and client
     */
    private Connection connect;

    /**
     * Server previously created
     */
    private ServerConnection connection;

    /**
     * Initializes both connect and connection objects
     *
     * @param connect    Connection object
     * @param connection ServerConnection object
     */
    WaitForClient(Connection connect, ServerConnection connection) {
        this.connect = connect;
        this.connection = connection;
    }

    /**
     * Waits until a client connects to server and launch´s another thread to start communicate with the server
     */
    public void run() {

        try {
            Socket client = connection.getServer().accept();

            connection.CONNECTION_ESTABLISHED = true;

            connection.setConnectionFromClient(new Scanner(client.getInputStream()));
            connection.setConnectionToClient(new PrintStream(client.getOutputStream()));

            CommunicateWithClient thread = new CommunicateWithClient(connect, connection.getConnectionFromClient(), connection.getConnectionToClient());
            thread.start();

        } catch (IOException e) {
            System.out.println("Error while connecting client and server");
        }
    }
}

/**
 * CommunicateWithClient class
 *
 * @author Rui Guedes
 */
class CommunicateWithClient extends Thread {

    /**
     * Connection object that contains both server and client
     */
    private Connection connect;

    /**
     * Allows to receive information from client
     */
    private Scanner connection_server;

    /**
     * Allows send information to client
     */
    private PrintStream connection_client;

    /**
     * Initializes all class parameters
     *
     * @param connect           Connection object
     * @param connection_server Scanner from server
     * @param connection_client PrintStream from server
     */
    CommunicateWithClient(Connection connect, Scanner connection_server, PrintStream connection_client) {
        this.connect = connect;
        this.connection_client = connection_client;
        this.connection_server = connection_server;
    }


    public void run() {

        while (true) {
            //Exchange information between server and client
        }
    }
}

