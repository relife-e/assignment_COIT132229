/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2_coit132229;

import java.io.Serializable;

/**
 *
 * @author Anmol Saru
 *//**
 *
 * Author :Anmol Saru Magar
 * File Name:BookOrder.java
 * Date :5/3/2025
 * Purpose :
 * Defines properites of Book
 * Interface Task and Serializable is implemented
 *
 * ******************************************************
 */
public class BookOrder implements Serializable, Task {
   private int quantity;
    private int unitPrice;
    private double tax;
    private double totalBill;

    public BookOrder(int quantity, int unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
    
    //defining abstract method executeTask
    @Override
    public void executeTask() {
        int price = quantity * unitPrice;
        double totalTax = (0.1 * price);
        double totalPrice = totalTax + price;       
        setTax(totalTax);
        setTotalBill(totalPrice);
    }
    //defining abstract method getResult
    @Override
    public double getResult() {
    return totalBill;   }
}
