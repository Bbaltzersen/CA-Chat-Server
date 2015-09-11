package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
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
//            caserver.handleClient("10.77.20.55", 7777);
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
            ClientHandler c = new ClientHandler(connection, this);
            c.start();
            
        }
    }

    public void removeUser(String user, ClientHandler ch) {
        clients.remove(user, ch);
        for(ClientHandler handler : clients.values()){
            handler.send(user + " disconnected from server.");
        }
    }

    public void sendtoAll(String msg) {
        for(ClientHandler handler : clients.values()){
            handler.send(msg);
        }

    }
    
    public void sendSpecific(String msg, String[] recievers) {
        ClientHandler ch;
        int i;
        if(recievers[0].equals("*")) {
            for(ClientHandler handler : clients.values()){
            handler.send(msg);
            }
        }
        else {
        for(i = 0; i < recievers.length; i++) {
            try {
            ch = clients.get(recievers[i]);
            ch.send(msg);
            }
            catch(Exception e) {
                System.out.println("Error");
            }
        }
        }
        
    }
    
    private void sendUserList(){
        String users = "";
        for(String user : clients.keySet()){
            users = users+"," + user;
            
        }
        String fullInfo = "USERLIST#" + users;
        for(ClientHandler handler : clients.values()){
            handler.send(fullInfo);
        }
    }
    public void addClientHandler(String username, ClientHandler ch){
        clients.put(username, ch);
        sendUserList();
        
    }
}
