package com.example.cashier;

public class ProductModel {

    public ProductModel(String name, String code, int ammount, double price, CodeType type){
        this._name = name;
        this._code = code;
        this._ammount = ammount;
        this._price = price;
        this._codeType = type;
    }

    private String _name;
    private String _code;
    private int _ammount;
    private double _price;
    private CodeType _codeType;

    public String GetName(){
        return this._name;
    }

    public void SetName(String str){
        this._code = str;
    }

    public String GetCode(){
        return this._code;
    }

    public void SetCode(String str){
        this._code = str;
    }

    public int GetAmmount(){
        return this._ammount;
    }

    public void SetAmmount(int ammount){
        this._ammount = ammount;
    }

    public double GetPrice(){
        return this._price;
    }

    public void SetPrice(double price){
        this._price = price;
    }

    public CodeType GetCodeType(){
        return this._codeType;
    }

    public void SetCodeType(CodeType val){
        this._codeType = val;
    }
}

enum CodeType {
    QR,
    EAN,
}
