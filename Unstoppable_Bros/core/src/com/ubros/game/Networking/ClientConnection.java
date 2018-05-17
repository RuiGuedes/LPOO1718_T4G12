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
     * Personal IP Address
     */
    private String myIPAddress;

    /**
     * IP address splitted components
     */
    private String[] mySplittedIPAddress;

    /**
     *  Object that contains connection information
     */
    private Connection connect;

    /**
     *  Allows to scan information sent by server
     */
    private Scanner connection_from_server;

    /**
     *  Allows client to sent information to server
     */
    private PrintStream connection_to_server;

    /**
     *  Possible connections that can be made
     */
    private ArrayList<String> possibleServerConnections = new ArrayList<String>();

    /**
     * Class constructor responsible to initialize connect and server parameters
     * @param connect object that contains information about the server
     */
    ClientConnection(Connection connect) {
        this.connect = connect;

        searchingMyIPAddress();

        if(myIPAddress == null) {
            connect.closeServer();
            System.out.println("No connection was made :: Own IP address not detected");
            //TODO Change connectingPlayersScream showing that connection could no be made. Try again or go back to main menu
            return;
        }

        String subnet = mySplittedIPAddress[0] + "." + mySplittedIPAddress[1] + "." + mySplittedIPAddress[2];
        checkPossibleHosts(subnet);

        if(!connect.getConnectionEstablishedStatus())
            initializeClient();
    }

    /**
     * Initializes client information structures and connect it to the server
     */
    private void initializeClient() {

        int PORT_NUMBER = 4444;

        try {
            if(possibleServerConnections.isEmpty()) {
                System.out.println("NO CONNECTION WAS MADE");
                connect.closeServer();
                //TODO Change connectingPlayersScream showing that connection could no be made. Try again or go back to main menu
                return;
            }

            Socket client = new Socket(possibleServerConnections.get(0), PORT_NUMBER);

            connect.closeServer();
            connect.getServer().CONNECTION_ESTABLISHED = true;

            System.out.println("CLIENT IP ::  " + myIPAddress + " ::");
            System.out.println("SERVER IP ::  " + possibleServerConnections.get(0) + " ::");

            connection_from_server = new Scanner(client.getInputStream());
            connection_to_server = new PrintStream(client.getOutputStream());

            connect.setClient(this);

            CommunicateWithServer thread = new CommunicateWithServer(connect);
            thread.start();

        } catch (IOException e) {
            System.out.println("CLIENT - SERVER :: CONNECTION ERROR :: " + possibleServerConnections.get(0));
            possibleServerConnections.remove(0);
            initializeClient();
        }
    }

    /**
     * Checks possible hosts on the same subnet that are waiting for client
     * @param subnet IP type that we are looking for
     */
    private void checkPossibleHosts(String subnet){
        int timeout=100;
        int myConnectionNumber = Integer.parseInt(myIPAddress.substring(myIPAddress.lastIndexOf(".") + 1));
        int lowerRange = myConnectionNumber - 50, limitRange = myConnectionNumber + 50;

        if(limitRange > 255)
            limitRange = 255;

        if(lowerRange < 1)
            limitRange = 1;

        System.out.println("MY IP ADDRESS :: " + myIPAddress + " ::");
        lowerRange = 0;
        limitRange = 255;
        for(; lowerRange < limitRange; lowerRange++){
            if(connect.getConnectionEstablishedStatus())
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
                System.out.println("Error while trying to ping possible host connections");
            }
        }
    }

    /**
     *  Responsible to determine this user IP Address
     */
    private void searchingMyIPAddress() {

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        String hostIPAddress = address.getHostAddress();

                        if(!hostIPAddress.equals("127.0.0.1")) {
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
     * Function responsible to send server scanner
     * @return the scanner that allows client receive information from server
     */
    public Scanner getConnection_from_server() {
        return connection_from_server;
    }

    /**
     * Function responsible to send server printer stream
     * @return the printer stream that allows client sent information from server
     */
    public PrintStream getConnection_to_server() {
        return connection_to_server;
    }

}

class CommunicateWithServer extends Thread {

    /**
     *  Object that contains information about the server and client
     */
    private Connection connect;

    /**
     *  Object that allows to receive information from server
     */
    private Scanner connection_from_server;

    /**
     *  Object that allows to send information to the server
     */
    private PrintStream connection_to_server;

    /**
     * Class constructor responsible to initialize his own structures
     * @param connect contains information on both server and client objects
     */
    CommunicateWithServer(Connection connect) {
        this.connect = connect;
        this.connection_from_server = connect.getClient().getConnection_from_server();
        this.connection_to_server = connect.getClient().getConnection_to_server();
    }

    /**
     * Function responsible to allow this thread run simultaneously with the main program process
     */
    public void run() {
        //TODO Create protocol to send and receive information between client-server
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
