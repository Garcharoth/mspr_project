package fr.epsi.rennes.poec.bog.mspr.domain;

public class Product {
    private int id;
    private String brand;
    private String model;
    private double price;
    private int quantity;



    ///// CONSTRUCTORS /////

    public Product() {
    }

    public Product(int id, String brand, String model, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }



    ///// GETTERS /////


    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    ///// SETTERS /////


    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


