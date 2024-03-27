package org.example.model;

public class Items {
    private String id;
    private String itemName;
    private int qty;
    private double unitPrice;
    private String suppId;

    public Items(String itemName, int qty, double unitPrice, String suppId) {
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.suppId = suppId;
    }

    public Items(String id, String itemName, int qty, double unitPrice, String suppId) {
        this.id = id;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.suppId = suppId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSuppId() {
        return suppId;
    }

    public void setSuppId(String suppId) {
        this.suppId = suppId;
    }
}
