package com.example.cashier.helpers;

import com.example.cashier.state_manager.PaymentResultModel;

import org.json.JSONException;

public interface IPaymentCallback {
    void onSuccess(PaymentResultModel result) throws JSONException;
    void onError(String result);
}
