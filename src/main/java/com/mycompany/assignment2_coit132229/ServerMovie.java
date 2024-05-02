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
 * File Name:ServerMovie.java
 * Date :5/3/2025
 * Purpose :
 * Receives Movie Order From ServerCordiator 
 * Executes executeTask() function
 * Sends object back to ServerCordinator
 *
 * ******************************************************
 */
public class ServerMovie{
    
     public static void main (String args[]) {
		
    try{
        int serverPort = 1129; 
        ServerSocket listenSocket = new ServerSocket(serverPort);
        int count = 1;
        while(true) {
            Socket clientSocket = listenSocket.accept();
            System.out.println("Recived Movie Object Number: " + count);
                count++;
            handleConnection(clientSocket);
        }

    } catch(IOException e) {
        System.out.println("Listen socket:"+e.getMessage());
    }
  }

    //methods that takses socket parameter and executes executeTask function of MovieOrder
    public static void handleConnection(Socket s) {
        try {
            
            ObjectInputStream in = new ObjectInputStream( s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream( s.getOutputStream());
 
                MovieOrder x = (MovieOrder) in.readObject();
                x.executeTask();
                out.writeObject(x);
                out.flush(); 
                System.out.println("Seding Data to ServerCordinator");

        } catch (IOException e) {
            System.out.println("Error handling client connection: " );
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } finally{
            try{
            s.close();}    
            catch (IOException e) {
                
                e.printStackTrace();
            }}
        }
    }

