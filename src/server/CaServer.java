package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaServer {

    private String nUser;
    Scanner sc;

    HashMap<String, ClientHandler> clients = new HashMap();

    public static void main(String[] args) {
        CaServer caserver = new CaServer();
        try {
            caserver.handleClient("localhost", 7777);
        } catch (IOException ex) {
            Logger.getLogger(CaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleClient(String ip, int port) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(ip, port));

        while (true) {
            Socket connection = server.accept();
            ClientHandler ch = new ClientHandler(connection);

            System.out.println("Connection established");
            ch.start();

        }
    }

    public void removeUser(String user, ClientHandler ch) {
        clients.remove(user, ch);
    }

    public void addUser(String user, ClientHandler ch) {
        clients.put(nUser, ch);

    }
}
