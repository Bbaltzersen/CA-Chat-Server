
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
    
    
    ClientHandler(Socket socket) throws IOException{
       this.socket = socket;
       pw = new PrintWriter(socket.getOutputStream(), true);
       sc = new Scanner(socket.getInputStream());
    }
    
    public void run(){
        String input = sc.nextLine();
        String[] parts = input.split("#");
        if(!parts[0].equals("USER#")) {
            try {
                socket.close(); // closing the connection
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
