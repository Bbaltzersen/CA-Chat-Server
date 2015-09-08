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

    String username;
    Socket socket;
    PrintWriter pw;
    Scanner sc;
    Map users;

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        pw = new PrintWriter(socket.getOutputStream(), true);
        sc = new Scanner(socket.getInputStream());
    }

    public void run() {
        String input = sc.nextLine();
        String[] parts = input.split("#");
        if (!parts[0].equals("USER#")) {
            try {
                socket.close(); // closing the connection
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean connected = true;
        while (connected) {
            if (parts[0].equals("USER#")) {
                users = new HashMap();
                users.put(parts[0], parts[1]);

            }else{
                System.out.println("Apenado senior, no es un User!");
            }
        }
    }
}
