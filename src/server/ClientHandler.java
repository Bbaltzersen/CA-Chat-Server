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
            //close connection
        }
        if (parts[0].equals("USER")) {

            username = parts[1];
            server.addClientHandler(username, this);
        }
        parts = null;
        while (connected && input != null) {
            input = sc.nextLine();
            parts = input.split("#");

            if (parts[0].equals("STOP")) {
                endConnection();
                pw.println("You're logged off loser!!");
                parts = null;
            }
            input = sc.nextLine();
            parts = input.split("#");
            if (parts[0].equals("MSG")) {
//                send();
                pw.println("This is where you should send a message");
                parts = null;
            }
        }

//        input = "";
        parts = null;
    }

    public void endConnection() {
    }

    public String getUsername() {
        return username;
    }

    public void send(String msg) {

        pw.println(msg);

    }
}
