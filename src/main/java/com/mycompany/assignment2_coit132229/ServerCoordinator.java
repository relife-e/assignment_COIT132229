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
import java.util.Scanner;

/**
 *
 * @author Anmol Saru
 */
public class ServerCoordinator {

    public static void main(String args[]) {

        try {
            int serverPort = 1127;//initalizing serverport
            ServerSocket sSocket = new ServerSocket(serverPort);
            System.out.println("Server running. \nWaiting for client");
            while (true) {
                Socket s = null;
                Socket cSocket = sSocket.accept();
                ObjectInputStream in = new ObjectInputStream(cSocket.getInputStream()); // reading input stream from socket and storing it in "in" var
                ObjectOutputStream out = new ObjectOutputStream(cSocket.getOutputStream());
                Object order = in.readObject();
                int serverPortObj = 0;
                if (order instanceof BookOrder) {
                    serverPortObj = 1128;
                    
                } else if (order instanceof MovieOrder) {
                    serverPortObj = 1129;
                }
                
                    String hostName = "localhost"; //initializg hostname
                    try {
                        
                        Scanner sc = new Scanner(System.in); // creating scanner object
                        s = new Socket(hostName, serverPortObj);
                        
                        ObjectOutputStream outData = new ObjectOutputStream(s.getOutputStream());
                        //Thread th = new Thread(new ServerBook(cSocket));//creating new thread object that runs ImpRun class that implements runnable
                        //ObjectOutputStream outData = new ObjectOutputStream(s.getOutputStream()); //reading output stream from socket and stroing it in "out"
                        System.out.println("Sending data to SeverBook");
                        outData.writeObject(order);
                        
                        ObjectInputStream inData = new ObjectInputStream(s.getInputStream()); 
                        BookOrder x = (BookOrder)inData.readObject();
                        out.writeObject(x);
                        //th.start();//starts new thread
                    } catch (IOException e) {
                        System.out.print("Message has not been send to client "); //catches error when not able to connect to the server
                    }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage()); //catches error, in this context used to catch error while accepting socket from client
        }
    }
}
