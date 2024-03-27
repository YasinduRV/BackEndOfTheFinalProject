package org.example.model;

public class Suppliers {
    private  String id;
    private String supplierName;
    private String tele;
    private String address;

    public Suppliers(String supplierName, String tele, String address) {
        this.supplierName = supplierName;
        this.tele = tele;
        this.address = address;
    }

    public Suppliers(String id, String supplierName, String tele, String address) {
        this.id = id;
        this.supplierName = supplierName;
        this.tele = tele;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
