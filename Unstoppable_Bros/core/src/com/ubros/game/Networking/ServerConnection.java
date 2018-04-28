package com.ubros.game.Networking;

import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnection {

    /**
     *  Port number to be use on server-client connection
     */
    private int PORT_NUMBER = 4444;

    /**
     *
     */
    public boolean CONNECTION_ESTABLISHED = false;

    private Connection connect;
    private ServerSocket server;
    private Socket client;
    private Scanner connection_from_client;
    private PrintStream connection_to_client;

    public ServerConnection(Connection connect) {

        this.connect = connect;

        initializeServer();
    }

    private void initializeServer() {

        try {
            server = new ServerSocket(PORT_NUMBER);

            WaitForClient thread = new WaitForClient(connect, this);
            thread.start();

        } catch (IOException e) {
            //SOME ERROR MESSAGE
        }
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void setConnectionFromClient(Scanner connection_from_client) {
        this.connection_from_client = connection_from_client;
    }

    public void setConnectionToClient(PrintStream connection_to_client) {
        this.connection_to_client = connection_to_client;
    }

}

class WaitForClient extends Thread {

    private Connection connect;
    private ServerConnection connection;
    private Socket client;
    private Scanner connection_from_client;
    private PrintStream connection_to_client;

    public WaitForClient(Connection connect, ServerConnection connection) {
        this.connect = connect;
        this.connection = connection;
    }
    public void run() {

        try {
            client = connection.getServer().accept();

            connection.setClient(client);
            connection.CONNECTION_ESTABLISHED = true;

            connection_from_client = new Scanner(client.getInputStream());
            connection.setConnectionFromClient(connection_from_client);

            connection_to_client = new PrintStream(client.getOutputStream());
            connection.setConnectionToClient(connection_to_client);

            CommunicateWithClient thread = new CommunicateWithClient(connect, client, connection_from_client, connection_to_client);
            thread.start();

        } catch (IOException e) {
            //SOME ERROR MESSAGE
        }

    }
}

class CommunicateWithClient extends Thread {

    private Connection connect;
    private ServerSocket server;
    private Socket client;
    private Scanner connection_server;
    private PrintStream connection_client;

    public CommunicateWithClient(Connection connect, Socket client, Scanner connection_server, PrintStream connection_client) {
        this.connect = connect;
        this.client = client;
        this.connection_client = connection_client;
        this.connection_server = connection_server;
    }
    public void run() {

        while(true) {

            int number = connection_server.nextInt();
            System.out.println("RECEIVED FROM CLIENT " + number);

            if(Gdx.input.isTouched())
                number = 0;

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

