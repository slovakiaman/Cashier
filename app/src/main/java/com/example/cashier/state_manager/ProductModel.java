package com.example.cashier.state_manager;

public class ProductModel {


    private String name;
    private String code;
    private int numberOfProducts;
    private float unitPrice;

    public ProductModel( String name, String code, int numberOfProducts, float unitPrice){

        this.name = name;
        this.code = code;
        this.numberOfProducts = numberOfProducts;
        this.unitPrice = unitPrice;
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

    public String toString(){
        return getName() +" :   "+ getUnitPrice() + " EUR";
    }
}
