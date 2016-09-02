/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lalleaul
 */
public class Server extends JFrame {

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    //Constructeur
    public Server() {
        super("Chat");
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

//sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300, 150);
        setVisible(true);
    }

    // set up & run the server
    public void startRunning() {
        try {
            server = new ServerSocket(6789, 100);
            while (true) {
                try {
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                } catch (EOFException eOFE) {
                    showMessage("\n Server ended the connection!");
                } finally {
                    closeCrap();
                }
            }
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
    }

    //wait for a connection, then display connection information
    private void waitForConnection() throws IOException {
        showMessage("Waiting for someone to connect...\n");
        connection = server.accept();
        showMessage("Now connected to " + connection.getInetAddress().getHostName());
    }

//get stream to send and receive data
    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are new setup!");
    }

//during the chat conversation
    private void whileChatting() throws IOException{
        String message = "You are now connected ! ";
        sendMessage(message);
        ableToType(true);
        do{
            // have a conversation
        }while();
    }
}
