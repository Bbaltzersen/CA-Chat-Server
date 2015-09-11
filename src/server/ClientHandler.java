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

    ClientHandler(Socket socket, CaServer s) throws IOException {
        this.server = s;
        this.socket = socket;
        pw = new PrintWriter(socket.getOutputStream(), true);
        sc = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        boolean connected = true;
        String parts[];
        String name;
        String input = sc.nextLine();
        parts = input.split("#");
        
        if (!parts[0].equals("USER")) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String uname = parts[1];
        if (parts[0].equals("USER")) {

            username = parts[1];
            server.addClientHandler(username, this);
        }
        parts = null;
        
        while (connected) {
            System.out.println(username);
            input = sc.nextLine();
            parts = input.split("#");

            if (parts[0].equals("STOP")) {
                try {
                    endConnection();
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                pw.println("You're logged off loser!!");
                parts = null;
                break;
            }
            if (parts[0].equals("MSG") && parts[1].equals(username)) {
                String msg = "MSG#" + username + "#" + parts[2];
                server.sendtoAll(msg);
            }
            if (parts[0].equals("MSG") && !parts[1].equals(username)) {
                String recievers = parts[1];
                String[] recS = recievers.split(",");
                String msg = "MSG#" + username + "#" + parts[2];
                server.sendSpecific(msg, recS);
            }
        
        }

    }

    public void endConnection() throws IOException {
        server.removeUser(username, this);
        socket.close();
    }

    public String getUsername() {
        return username;
    }

    public synchronized void send(String msg) {

        pw.println(msg);

    }
}
