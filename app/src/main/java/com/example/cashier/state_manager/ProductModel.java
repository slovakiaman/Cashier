package com.example.cashier.state_manager;

public class ProductModel {

    private int id;
    private String name;
    private String code;
    private int numberOfProducts;
    private float unitPrice;

    public ProductModel(int id, String name, String code, int numberOfProducts, float unitPrice){
        this.id = id;
        this.name = name;
        this.code = code;
        this.numberOfProducts = numberOfProducts;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
