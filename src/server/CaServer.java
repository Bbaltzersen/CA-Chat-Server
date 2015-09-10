package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
            ClientHandler c = new ClientHandler(connection, this);
            c.start();
            
        }
    }

    public void removeUser(String user, ClientHandler ch) {
        System.out.println("Clients before erase : " + clients.toString());
        clients.remove(user, ch);
        System.out.println("Clients after erase : " +clients.toString());
    }

//    public void addUser(String user, Socket socket) throws IOException {
//        ClientHandler clientH = new ClientHandler(socket);
//        clients.put(nUser, clientH);
//        System.out.println("From clients:  " + clients.toString());
//    }

    public void sendtoAll(String msg) {
        Iterator it = clients.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ClientHandler temp = (ClientHandler) pair.getValue();
            temp.send(msg);

        }

    }
    
    public void sendSpecific(String msg, String[] recievers) {
        ClientHandler ch;
        int i;
        System.out.println(recievers.length);
        for(i = 0; i < recievers.length; i++) {
            ch = clients.get(recievers[i]);
            ch.send(msg);
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
