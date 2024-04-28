/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2_coit132229;

import java.io.Serializable;

/**
 *
 * @author Anmol Saru
 */
public class BookOrder implements Serializable, Task {
    private int quantity;
    private int unitPrice;
    private int tax;
    private int totalBill;

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

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public void executeTask() {
        
    }

    @Override
    public double getResult() {
        return 0.0;
    }
}
