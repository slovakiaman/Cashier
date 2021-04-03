package com.example.cashier.helpers;

import org.json.JSONException;
import org.json.JSONObject;

public interface ICheckApiCallback {
    void onSuccess(String result) throws JSONException;
    void onError(String result);
}