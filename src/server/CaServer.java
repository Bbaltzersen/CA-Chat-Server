
package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CaServer {

    
    public static void main(String[] args) {
        CaServer cserver = new CaServer();
        try {
            cserver.handleClient("localhost", 7777);
        } catch (IOException ex) {
            Logger.getLogger(CaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleClient(String ip, int port) throws IOException {
        ServerSocket servs = new ServerSocket();
        servs.bind(new InetSocketAddress(ip, port));
        
        
        while(true) {
            Socket socket = servs.accept();
            ClientHandler ch = new ClientHandler(socket);
            ch.start();
        }
    }
}
