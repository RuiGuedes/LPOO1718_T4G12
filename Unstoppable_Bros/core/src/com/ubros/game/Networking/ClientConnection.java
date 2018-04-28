package com.ubros.game.Networking;

import com.badlogic.gdx.Gdx;

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
import java.util.Scanner;

public class ClientConnection {

    /**
     *  Port number to be use on server-client connection
     */
    private int PORT_NUMBER = 4444;

    /**
     * Personal IP Address
     */
    private String myIPAddress;

    private Connection connect;
    private ServerConnection server;
    private Socket client;
    private Scanner connection_from_server;
    private PrintStream connection_to_server;
    private ArrayList<String> possibleServerConnections = new ArrayList<String>();

    public ClientConnection(Connection connect, ServerConnection server) {
        this.connect = connect;
        this.server = server;

        searchingMyIPAddress();
        checkPossibleHosts("192.168." + myIPAddress.charAt(8));
        if(!server.CONNECTION_ESTABLISHED)
            initializeClient();
    }

    private void initializeClient() {

        try {
            if(possibleServerConnections.isEmpty())
            {
                System.out.println("NO CONNECTION WAS MADE");
                server.getServer().close();
                return;
            }
            client = new Socket(possibleServerConnections.get(0), PORT_NUMBER);

            server.getServer().close();

            System.out.println("CLIENT IP ::  " + myIPAddress + " ::");
            System.out.println("SERVER IP ::  " + possibleServerConnections.get(0) + " ::");

            connection_from_server = new Scanner(client.getInputStream());

            connection_to_server = new PrintStream(client.getOutputStream());

            CommunicateWithServer thread = new CommunicateWithServer(connect,client,connection_from_server,connection_to_server);
            thread.start();

        } catch (IOException e) {
            System.out.println("CLIENT - SERVER :: CONNECTION ERROR :: " + possibleServerConnections.get(0));
            possibleServerConnections.remove(0);
            initializeClient();
        }
    }

    public void checkPossibleHosts(String subnet){

        int timeout=1000;
        int myConnectionNumber = Integer.parseInt(myIPAddress.substring(myIPAddress.lastIndexOf(".") + 1));
        int limitRange = myConnectionNumber + 5;

        System.out.println("MY IP ADDRESS :: " + myIPAddress + " ::");

        for (int lowerRange = (myConnectionNumber - 5); lowerRange < limitRange;lowerRange++){
            if(server.CONNECTION_ESTABLISHED)
                return;

            String host=subnet + "." + lowerRange;
            try {

                if (InetAddress.getByName(host).isReachable(timeout)){
                    System.out.println(host + " is reachable");
                    if(!host.equals(myIPAddress))
                        possibleServerConnections.add(host);
                }
                else
                    System.out.println(InetAddress.getByName(host) + " not reachable");

            } catch (IOException e) {
                //SOME ERROR MESSAGE
            }
        }

    }

    private void searchingMyIPAddress() {

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        String hostIPAddress = address.getHostAddress();
                        if(hostIPAddress.contains("192.168.")) {
                            myIPAddress = hostIPAddress;
                            return;
                        }

                    }
                }
            }
        } catch (SocketException e) {
           //SOME ERROR MESSAGE
        }

    }

}

class CommunicateWithServer extends Thread {

    private Connection connect;
    private Socket client;
    private Scanner connection_from_server;
    private PrintStream connection_to_server;

    public CommunicateWithServer(Connection connect, Socket client, Scanner connection_from_server, PrintStream connection_to_server) {
        this.connect = connect;
        this.client = client;
        this.connection_from_server= connection_from_server;
        this.connection_to_server = connection_to_server;
    }
    public void run() {

        while(true) {

            if(Gdx.input.isTouched()) {
                System.out.println("SEND TO SERVER VALUE 0");
                connection_to_server.println("0");
            }
            else {
                System.out.println("SEND TO SERVER VALUE 1");
                connection_to_server.println("1");
            }

            int number = connection_from_server.nextInt();

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
