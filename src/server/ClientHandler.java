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
        }
    }

    public String getUsername() {
        return username;
    }
    public void send(String msg){
        
                 pw.println(msg);

    }
}
