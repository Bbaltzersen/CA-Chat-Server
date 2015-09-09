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
        while (connected) {
        String input = sc.nextLine();
        String[] parts = input.split("#");
            System.out.println("User " + parts[0] + "Username" + parts[1]);
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
                System.out.println("User from if : " + parts[1]);
                
//                server.addUser(parts[1], socket);
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
