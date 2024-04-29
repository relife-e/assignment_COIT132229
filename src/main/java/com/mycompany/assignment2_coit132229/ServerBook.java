/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2_coit132229;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

/**
 *
 * @author Anmol Saru
 */
public class ServerBook{
    public static void main (String args[]) {
		
    try{
        int serverPort = 1128; 
        ServerSocket listenSocket = new ServerSocket(serverPort);

        while(true) {
            Socket clientSocket = listenSocket.accept();
            Connection c = new Connection(clientSocket);
        }

    } catch(IOException e) {
        System.out.println("Listen socket:"+e.getMessage());
    }
  }}
    
    class Connection extends Thread {
	
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;

    public Connection (Socket aClientSocket) {

      try {
        clientSocket = aClientSocket;
        in = new ObjectInputStream( clientSocket.getInputStream());
        out =new ObjectOutputStream( clientSocket.getOutputStream());
        this.start();
    } catch(IOException e) {
        System.out.println("Connection:"+e.getMessage());}
    }


      /*Overriding run method
    Executes when .start is invoked in TCPServer class
    It takes inoput from client and saves it in a file*/
    @Override
    public void run() {
        try {

            while (true) {
                BookOrder x = (BookOrder) in.readObject();
                x.executeTask();
                out.writeObject(x);
                out.flush(); // Flush the output stream
                System.out.println("Seding Data to ServerCordinator");
            }
        } catch (IOException e) {
            System.out.println("Error handling client connection: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
           } catch (IOException e) {
               System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}