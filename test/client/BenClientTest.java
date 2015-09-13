/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Observable;
import java.util.Observer;
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
    
    public BenClientTest() {
    }

    /**
     * Test of connect method, of class BenClient.
     */
    @BeforeClass
  public static void setUpClass() {
    new Thread(new Runnable(){
      @Override
      public void run() {
        CaServer.main(null);
      }
    }).start();
  }
    @Test
    public void testConnect() throws Exception {
       BenClient client = new BenClient();
       client.connect("localhost",7777,"Test1",this);
       assertEquals(true, client.user.equals("Test1"));
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
    public void testGetList() {
        System.out.println("getList");
        BenClient instance = new BenClient();
        String[] expResult = null;
        String[] result = instance.getList();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class BenClient.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("send");
            ClientHandler ch;
          BenClient client = new BenClient();
    client.connect("localhost",7777,"Test2",this);
    client.connect("localhost",7777,"Test3",this);
        String msg = "MSG#Test2#Hello";
        client.send(msg);
        
        assertEquals(expResult, ch.);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of close method, of class BenClient.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        BenClient instance = new BenClient();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recieve method, of class BenClient.
     */
    @Test
    public void testRecieve() throws Exception {
        System.out.println("recieve");
        BenClient instance = new BenClient();
        String expResult = "";
        String result = instance.recieve();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
