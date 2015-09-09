package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class ClientHandler extends Thread {
    CaServer server;
    String username;
    Socket socket;
    PrintWriter pw;
    Scanner sc;
//    Map users;

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        pw = new PrintWriter(socket.getOutputStream(), true);
        sc = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        boolean connected = true;
        String name;
        while (connected) {
        String input = sc.nextLine();
        String[] parts = input.split("#");
        if (!parts[0].equals("USER")) {
            try {
                socket.close(); // closing the connection
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            if (parts[0].equals("USER")) {
//                users = new HashMap();
//                users.put(parts[0], parts[1]);
                System.out.println(this.toString() + " <---- This");
                name = parts[1];
//                try {
//                server.addUser(parts[1], socket);
//                }
//                catch (Exception e) {
//                    System.out.println("Error");
//                }
            }
            if(parts[0].equals("MSG")){
                send(parts[1]);
            }
            else{
                System.out.println("Apenado senior, no es un User!");
            }
        }
    }

    public String getUsername() {
        return username;
    }
    public void send(String msg){
        
                 pw.println(msg);

    }
}
