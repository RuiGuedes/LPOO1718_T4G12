package com.ubros.game.Networking;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

class ClientWait extends Thread {

    private ServerSocket serverSocket;
    public ClientWait(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void run() {
        try {
            Socket clientSocket = serverSocket.accept();
        } catch (IOException e) {
            //
        }
    }
}

public class Connection {

    private int PORT_NUMBER = 4444;

    private ServerSocket serverSocket;

    public Connection() {

        initializeServer();
    }

    private void initializeServer()  {

        List<String> addresses = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        addresses.add(address.getHostAddress());
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

        System.out.print("FODEU !!!");
        System.out.print(ipAddress);

    }
}
