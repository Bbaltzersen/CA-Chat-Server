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
import shared.ProtocolStrings;

/**
 *
 * @author Ben
 */
public class BenClient extends Observable implements Runnable {

    String user;
    String[] sUsers;
    String msgRecieve;
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
    public  void run() {
        
        output.println(ProtocolStrings.USER + this.user);
        
        String parts[];
       
        
        try {
            
            while ((incoming = input.readLine()) != null) {
                System.out.println(incoming);
                System.out.println("Received this:" + incoming);
                parts = incoming.split("#");
                if (incoming.startsWith(ProtocolStrings.USERLIST)) {  // compares the ingoing line with the protocol
                    System.out.println("received userlist");
                    String users = parts[1];//.split(",");
                    sUsers = users.split(",");
                    setChanged();
                    notifyObservers(incoming);
                }
                if (incoming.startsWith(ProtocolStrings.MSG)) {
                    parts = null;
                    parts = incoming.split("#");
                    String juMsg = parts[1] + ": " + parts[2];
                    msgRecieve = juMsg;
                    setChanged();
                    notifyObservers(incoming);
                    msgRecieve = null;
                }
                if (incoming.startsWith(ProtocolStrings.STOP)) {
                    close();
                    setChanged();
                    notifyObservers(incoming);
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(BenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        

    }

    
    public String[] getList() {
        System.out.println("here");
        return sUsers;
    }

    public void send(String msg) {
        System.out.println("Send method: " + msg);
        System.out.println(msg + "WHY!!!");
        if(msg != null) {
        output.println(msg);
        }
    }

    public void close() {
        output.println(ProtocolStrings.STOP);
    }

    public String recieve() throws IOException {
        return msgRecieve;
    }

}
