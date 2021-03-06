/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.Dimension;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.DefaultCaret;
import shared.ProtocolStrings;

/**
 *
 * @author Ben
 */
public class BenGUI extends javax.swing.JFrame implements Observer {

    private PrintWriter out;
    DefaultListModel model = new DefaultListModel();

    BenClient echo;

    public BenGUI() throws IOException {
        initComponents();
        DefaultCaret caret = (DefaultCaret) chatField.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        chatField.setCaretPosition(chatField.getText().length());
        jList1.setModel(model);
        send.setEnabled(false);
        disconnect.setEnabled(false);
        user.setEditable(true);
        echo = new BenClient();
//        echo.connect("localhost", 9090,this,user);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        send = new javax.swing.JButton();
        sendMessage = new javax.swing.JTextField();
        user = new javax.swing.JTextField();
        connect = new javax.swing.JButton();
        disconnect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatField = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        sendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageActionPerformed(evt);
            }
        });

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        disconnect.setText("Disconnect");
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });

        jLabel1.setText("users");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setToolTipText("");
        jList1.setSelectedIndex(0);
        jList1.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(jList1);

        chatField.setEditable(false);
        chatField.setColumns(20);
        chatField.setRows(5);
        jScrollPane2.setViewportView(chatField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(send)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(connect)
                                .addGap(18, 18, 18)
                                .addComponent(disconnect))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(connect)
                            .addComponent(disconnect))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(send)
                    .addComponent(sendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:

        String msg = sendMessage.getText();
        int occurance;
        int count = msg.length() - msg.replace("#", "").length();
        System.out.println(count);
        if (count <= 1) {
            if (msg.contains("#")) {
                String[] parts = msg.split("#");
                echo.send(ProtocolStrings.MSG + msg);
                chatField.append("To " + parts[0] + ": " + parts[1] + "\n");
            } else {
                echo.send(ProtocolStrings.MSG + "*#" + msg);
            }
        } else {
            sendMessage.setText("the message can't include more than #");
        }

    }//GEN-LAST:event_sendActionPerformed

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        // TODO add your handling code here:
        String newUser = user.getText();

        System.out.println(newUser);
        try {
            echo.connect("localhost", 7777, newUser, this);
        } catch (IOException ex) {
            Logger.getLogger(BenGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        chatField.setText("Connected. \n");
        connect.setEnabled(false);
        user.setEditable(false);
        send.setEnabled(true);
        disconnect.setEnabled(true);

    }//GEN-LAST:event_connectActionPerformed

    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed
        // TODO add your handling code here:
        echo.close();
        connect.setEnabled(true);
        send.setEnabled(false);
        disconnect.setEnabled(false);
        chatField.setText(null);
        chatField.setText("Disconnected.");
        sendMessage.setText(null);
        user.setText(null);
        user.setEditable(true);
        model.removeAllElements();
    }//GEN-LAST:event_disconnectActionPerformed

    private void sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendMessageActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BenGUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(BenGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatField;
    private javax.swing.JButton connect;
    private javax.swing.JButton disconnect;
    private javax.swing.JLabel jLabel1;
    javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton send;
    private javax.swing.JTextField sendMessage;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {

        try {
            String message = echo.recieve();
            if (message != null) {
                chatField.append(echo.recieve() + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(BenGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] userList = echo.getList();
        model.removeAllElements();
        for (String uname : userList) {
            model.addElement(uname);
            jList1.setSelectedIndex(0);
        }
    }

}
