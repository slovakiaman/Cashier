package com.example.cashier.recyclerView;

public class Item {

    private String productName;
    private String productCode;
    private int codeType;
    private float unitPrice;

    public Item(String productName, String productCode, int codeType, float unitPrice) {
        this.productName = productName;
        this.productCode = productCode;
        this.codeType = codeType;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
