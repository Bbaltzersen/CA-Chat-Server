/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben
 */
public class BenClient extends Observable implements Runnable {

    String user;
    String[] sUsers;
    String sender;
    String message;
    String incoming;

    private Socket socket;

    private BufferedReader input;
    private PrintWriter output;

    public void connect(String address, int port, String user, Observer o) throws UnknownHostException, IOException {
        this.user = user;
        socket = new Socket(address, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);  //Set to true, to get auto flush behaviour
        Thread t1 = new Thread(this);
        addObserver(o);
        t1.start();
    }

    @Override
    public void run() {
        output.println("USER#" + this.user);
        String parts[];
        
        
        try {
            while ((incoming = input.readLine()) != null) {
                System.out.println("Received this:" + incoming);
                parts = incoming.split("#");
                if (parts[0].equals("USERLIST")) {
                    System.out.println("received userlist");
                    String users = parts[1];//.split(",");
                    sUsers = users.split(",");
                    setChanged();
                    notifyObservers(incoming);
                }
                if (parts[0].equals("MSG")) {
                    sender = parts[1];
                    message = parts[2];
                    System.out.println("received message");
                    setChanged();
                    notifyObservers(incoming);
                }
                if (parts[0].equals("STOP")) {
                    close();
                    System.out.println("received stop");
                    setChanged();
                    notifyObservers(incoming);
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(BenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        

    }

    public String[] getList() {
        return sUsers;
    }

    public void send(String msg) {
        output.println(msg);
    }

    public void close() {
        output.println("STOP#");
    }

    public void recieve(String msg) throws IOException {

        output.println(msg);
    }

}
