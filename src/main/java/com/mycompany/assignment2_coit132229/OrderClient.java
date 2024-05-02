package com.mycompany.assignment2_coit132229;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * Author :Anmol Saru Magar
 * File Name:ServerMovie.java
 * Date :5/3/2025
 * Purpose :
 * Asks user to select product then ask for user to enter quantity and unit price
 * Sends object to ServerCordinator then receives the object
 * Fetches tax and totalPrice via received object
 *
 * ******************************************************
 */
public class OrderClient {

    public static void main(String args[]){
        System.out.println("PLEASE PLACE YOUR ORDER BY SELECTING A NUMBER\n***********************************************");
        
        String hostName = "localhost";
        int serverPort = 1127;
        try {
            Scanner sc = new Scanner(System.in);
            
            Socket s = new Socket(hostName, serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            String stopS = "0";
            int count = 1;
            while (!stopS.equals("3")) {
                // Process the order
                double totalPrice = 0.0;
                double tax = 0.0;
                System.out.println("Enter details for your member number: " + count);
                System.out.println("1. Purchase Book");
                System.out.println("2. Purchase Movie");
                System.out.println("3. Exit");
                System.out.println("***********************************************");
                System.out.print("Enter Your Option: ");
                String option = sc.nextLine();
                
                int quantity, unitPrice;
                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(sc.nextLine()); // Read as String and then parse to handle newline character
                System.out.print("Enter unit price: ");
                unitPrice = Integer.parseInt(sc.nextLine()); // Read as String and then parse to handle newline character
                Object order = null;
                System.out.print("Sending Object to Server......\n");
                System.out.print("Reciving Object from Server.......\n");
               if(option.equals("1")){
                   order = new BookOrder(quantity,unitPrice);
               }
               else if (option.equals("2")){
                    order = new MovieOrder(quantity,unitPrice);
               }
               else if (option.equals("3")){
                    System.exit(1);
               }
               else{
               System.out.println("Please Choose Between 1, 2, 3");
               }
                // Send the details to the server
                out.writeObject(order);
                out.flush();
                //creating if..else if.. statement that determines the instance of object
                 if (order instanceof BookOrder) {
                    BookOrder bookOrder = (BookOrder)in.readObject();
                    totalPrice = bookOrder.getTotalBill();
                    tax = bookOrder.getTax();
                } else if (order instanceof MovieOrder) {
                    MovieOrder movieOrder = (MovieOrder)in.readObject();
                    movieOrder.executeTask();
                    totalPrice = movieOrder.getTotalBill();
                    tax = movieOrder.getTax();
                }
                System.out.println("Number of Books: " + quantity + "Unit Price: " + unitPrice + "Tax : " + tax + "Total Price: " + totalPrice + "\n");
                System.out.println("========================================");
                count++;
                stopS = option;
            }
            
             sc.close();
            out.close();
            in.close();
            s.close();

        } catch (Exception e) {
                System.out.print("Message has not been send to client " + e.getLocalizedMessage() + " " +e.getMessage());
        }
    }
    
    
}
