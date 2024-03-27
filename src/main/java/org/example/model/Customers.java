package org.example.model;

public class Customers {
    private String id;
    private String customerName;
    private String tele;
    private String nic;

    public Customers(String id, String customerName, String tele, String nic) {
        this.id = id;
        this.customerName = customerName;
        this.tele = tele;
        this.nic = nic;
    }

    public Customers() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
