package com.example.cashier.state_manager;

public class PaymentResultModel {
    private String paymentTime;
    private MerchantModel merchant;
    private ProductModel[] basket;
    private boolean paymentSuccesfull;

    public PaymentResultModel(String paymentTime,
                              MerchantModel merchant,
                              ProductModel[] basket,
                              boolean paymentSuccesfull)
    {
        this.paymentTime = paymentTime;
        this.merchant = merchant;
        this.basket = basket;
        this.paymentSuccesfull = paymentSuccesfull;
    }
    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public MerchantModel getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantModel merchant) {
        this.merchant = merchant;
    }

    public ProductModel[] getBasket() {
        return basket;
    }

    public void setBasket(ProductModel[] basket) {
        this.basket = basket;
    }

    public boolean isPaymentSuccesfull() {
        return paymentSuccesfull;
    }

    public void setPaymentSuccesfull(boolean paymentSuccesfull) {
        this.paymentSuccesfull = paymentSuccesfull;
    }
}
