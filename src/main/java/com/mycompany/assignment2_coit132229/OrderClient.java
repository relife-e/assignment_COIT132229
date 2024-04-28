package com.mycompany.assignment2_coit132229;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class OrderClient {

    public static void main(String args[]){
        System.out.println("PLEASE PLACE YOUR ORDER BY SELECTING A NUMBER\n***********************************************");
        Socket s = null;
        String hostName = "localhost";
        int serverPort = 1127;
        try {
            Scanner sc = new Scanner(System.in);

            
            s = new Socket(hostName, serverPort);
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            String stopS = "0";
            String count = "1";
            while (!stopS.equalsIgnoreCase("3")) {
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
                if (option.equals("1")) {
                    order = new BookOrder(quantity, unitPrice);
                } else if (option.equals("2")) {
                    order = new MovieOrder(quantity, unitPrice);
                }
                // Send the details to the server
                out.writeObject(order);
                stopS = option;
            }

        } catch (IOException e) {
            System.out.print("Message has not been send to client ");
        }
    }
}
