/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2_coit132229;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
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
            Socket s = null;
            s = new Socket("localhost", 1128);
            ObjectInputStream in = new ObjectInputStream (s.getInputStream()); // reading input stream from socket and storing it in "in" var
            ObjectOutputStream out = new ObjectOutputStream (s.getOutputStream());
            System.out.println("Server running. \nWaiting for client");
            Object order = in.readObject();

                Socket cSocket = sSocket.accept();//accepting connection from client when port matches
                
                if (order instanceof BookOrder){ 
                    int serverPortBook = 1128;
                    
                    String hostName = "localhost"; //initializg hostname
        try {
                    Scanner sc = new Scanner (System.in); // creating scanner object
                s = new Socket (hostName, serverPortBook); 
                Thread th = new Thread(new ServerBook(cSocket));//creating new thread object that runs ImpRun class that implements runnable
            DataInputStream inData = new DataInputStream (s.getInputStream()); // reading input stream from socket and storing it in "in" var
            DataOutputStream outData = new DataOutputStream (s.getOutputStream()); //reading output stream from socket and stroing it in "out"
                

                out.writeObject(order);
                th.start();//starts new thread
        }
         catch (IOException e){
        System.out.print("Message has not been send to client "); //catches error when not able to connect to the server
    }
    }   else if (order instanceof MovieOrder){ 
                    int serverPortBook = 1129;
                    
                    String hostName = "localhost"; //initializg hostname
        try {
                    Scanner sc = new Scanner (System.in); // creating scanner object
                s = new Socket (hostName, serverPortBook); 
                Thread th = new Thread(new ServerMovie(cSocket));//creating new thread object that runs ImpRun class that implements runnable
            DataInputStream inData = new DataInputStream (s.getInputStream()); // reading input stream from socket and storing it in "in" var
            DataOutputStream outData = new DataOutputStream (s.getOutputStream()); //reading output stream from socket and stroing it in "out"
                

                out.writeObject(order);
                th.start();//starts new thread
        }
         catch (IOException e){
        System.out.print("Message has not been send to client "); //catches error when not able to connect to the server
    }
    }                                  
                }
         catch (Exception e) {
            System.out.println("Error" + e.getMessage()); //catches error, in this context used to catch error while accepting socket from client
        }
    }
}
