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
import java.net.Socket;
import java.util.Timer;

/**
 *
 * @author Anmol Saru
 */
public class ServerBook implements Runnable {
    private Socket cSocket; //initalizing Socket variable

    //constructor for ImpRun  with Socket type arguments
    public ServerBook(Socket s) {
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
            int bookOrders = 0;
            while (true) {
                // Read the data from the client
                BookOrder x = (BookOrder)in.readObject();
                    x.executeTask();
                    x.getResult();
                    out.writeObject(x.getResult());
                System.out.print("Message Recived: ");
                
                bookOrders++;
                cSocket.close(); //closing socket
                out.close();
                in.close();
                //catching IOException
            }//Catching EOFExcpetion
        } catch (Exception e) {
            System.out.println("Error while reading file at the end.  " + e.getMessage());
        } 

        }
    }
