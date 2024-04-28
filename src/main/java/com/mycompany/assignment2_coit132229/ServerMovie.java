/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2_coit132229;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

/**
 *
 * @author Anmol Saru
 */
public class ServerMovie implements Runnable {
    private Socket cSocket; //initalizing Socket variable

    //constructor for ImpRun  with Socket type arguments
    public ServerMovie(Socket s) {
        cSocket = s;

    }


      /*Overriding run method
    Executes when .start is invoked in TCPServer class
    It takes inoput from client and saves it in a file*/
    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(cSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(cSocket.getOutputStream()); // creating
            
            while (true) {
                // Read the data from the client
                Object x = in.readObject();

                System.out.print("Message Recived: ");

                cSocket.close(); //closing socket
                //catching IOException
            }//Catching EOFExcpetion
        } catch (Exception e) {
            System.out.println("Error while reading file at the end.  " + e.getMessage());
        } 

        }
}
