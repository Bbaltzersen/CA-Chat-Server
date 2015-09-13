/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import server.CaServer;
import server.ClientHandler;

/**
 *
 * @author Ben
 */
public class BenClientTest implements Observer {
        private PrintWriter output;

    public BenClientTest() {
    }

    /**
     * Test of connect method, of class BenClient.
     */
    @BeforeClass
  public synchronized static void setUpClass() {
    new Thread(new Runnable(){
      @Override
      public void run() {
        CaServer.main(null);
      }
    }).start();
  }
    @Test
    public synchronized void testConnect() throws Exception {
       BenClient client = new BenClient();
       client.connect("localhost",7777,"Test1",this);
       assertEquals(true, client.user.equals("Test1"));
       client.close();
    }

    /**
     * Test of run method, of class BenClient.
     */
    @Test
    public void testRun() {
       //for connect to pass, the run method must have run 
        
    }

    /**
     * Test of getList method, of class BenClient.
     */
    @Test
    public void testGetList() throws IOException {
        System.out.println("getList");
        BenClient instance = new BenClient();
        instance.connect("localhost",7777,"Test1",this);
        String[] expResult = instance.sUsers ;
        
        String[] result = instance.getList();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of send method, of class BenClient.
     */
    @Test
    public synchronized void testSend() throws Exception {
        System.out.println("send");
            String msg = "MSG#Test2#Hello";
          BenClient client2 = new BenClient();
          BenClient client3= new BenClient();
          client2.connect("localhost",7777,"Test1",this);
          client3.connect("localhost",7777,"Test2",this);
          client3.send(msg);
          assertEquals(msg, client2.incoming);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of close method, of class BenClient.
     */
    @Test
    public synchronized void testClose() throws IOException {
        System.out.println("close");
       BenClient client = new BenClient();
       client.connect("localhost",7777,"Test1",this);
       client.close();
        assertEquals(client, this);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of recieve method, of class BenClient.
     */
    @Test
    public synchronized void testRecieve() throws Exception {
           System.out.println("send");
            String msg = "USERLIST#Test2,Test3";
          BenClient client2 = new BenClient();
          BenClient client3= new BenClient();
          client2.connect("localhost",7777,"Test1",this);
          client3.connect("localhost",7777,"Test2",this);
         
           assertThat((client3.recieve()), is("USERLIST#Test1,Test2"));
          client2.close();
          client3.close();
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
}
