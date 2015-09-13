package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

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

    public ClientHandler(Socket socket, CaServer s) throws IOException {
        this.server = s;
        this.socket = socket;
        pw = new PrintWriter(socket.getOutputStream(), true);
        sc = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        String parts[];
        String name;
        String input = sc.nextLine();
        parts = input.split("#");

        if (!input.startsWith(ProtocolStrings.USER)) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (input.startsWith(ProtocolStrings.USER)) {
            username = parts[1];
            server.addClientHandler(username, this);
        }
 
        while (sc.hasNextLine()) {
            
            System.out.println(username);
            input = sc.nextLine();
            System.out.println("In while loop: " + input);
            parts = input.split("#");

            if (input.equals(ProtocolStrings.STOP)) {
                try {
                    endConnection();
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                pw.println("You're logged off loser!!");
                parts = null;
                break;
            }
            if (input.startsWith(ProtocolStrings.MSG)) {
                if (parts[1] == null || parts[2] == null) {
                    System.out.println("");
                } else {
                    String recievers = parts[1];
                    String[] recS = recievers.split(",");
                    String msg = ProtocolStrings.MSG + username + "#" + parts[2];
                    server.sendSpecific(msg, recS);
                }
            }
            
            

        }
        try {
            endConnection();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void endConnection() throws IOException {
        server.removeUser(username, this);
        socket.close();
    }

    public String getUsername() {
        return username;
    }

    public void send(String msg) {
        pw.println(msg);
    }
}
