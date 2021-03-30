package com.example.cashier.state_manager;

public class MerchantModel {

    private String name;
    private String ico;
    private String street;
    private String streetNumber;
    private String city;
    private String psc;

    public MerchantModel(String name, String ico, String street, String streetNumber, String city, String psc){
        this.name = name;
        this.ico = ico;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.psc = psc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }
}
