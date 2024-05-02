/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2_coit132229;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Author :Anmol Saru Magar
 * File Name:ServerCordinator.java
 * Date :5/3/2025
 * Purpose :
 * Creates new thread for each new client
 * Receives Object from OrderClient
 * Determines the object if its BookOrder or MovieOrder and sends the object accordingly to ServeBook or ServerMovie
 * Receives object from the corresponding server and sends back to OrderClient
 *
 * ******************************************************
 */
public class ServerCoordinator {

    public static void main(String args[]) {

        try {
            int serverPort = 1127;//initalizing serverport
            ServerSocket sSocket = new ServerSocket(serverPort);
            System.out.println("Server running. \nWaiting for client");
            while (true) {
                Socket cSocket = sSocket.accept();
                ConnectionHandler c = new ConnectionHandler(cSocket);

            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage()); //catches error, in this context used to catch error while accepting socket from client
        }
    }

}
//class conncetuon hanndler that create new thread for each new client
class ConnectionHandler extends Thread {

    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    
    public ConnectionHandler(Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            
            this.start();//starting run method
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }

    }

    /*Overriding run method
    Executes when .start is invoked 
    */
    @Override
    public void run() {

        try {
            long threadId = Thread.currentThread().getId();//thread Id
            int count = 1;
            while (true) {
                
                Object order = in.readObject();
                System.out.println("Recived Client Number: " + (threadId-22)  +  " Object Number: " + count);
                
                int serverPortObj = 0;
                if (order instanceof BookOrder) {
                    serverPortObj = 1128;
                    System.out.println("Sending data to Sever for Book.....");

                } else if (order instanceof MovieOrder) {
                    serverPortObj = 1129;
                    System.out.println("Sending data to Sever for Movie.....");
                }

                String hostName = "localhost"; //initializg hostname

                Socket s = new Socket(hostName, serverPortObj);

                ObjectOutputStream outData = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream inData = new ObjectInputStream(s.getInputStream());
                outData.writeObject(order);
                Object x = inData.readObject();
                out.writeObject(x);
  
                System.out.println("Sending requested object back to client");
                

                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Message has not been send to client " + e.getMessage()); //catches error when not able to connect to the server
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
