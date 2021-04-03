package com.example.cashier.helpers;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cashier.state_manager.MerchantModel;
import com.example.cashier.state_manager.PaymentResultModel;
import com.example.cashier.state_manager.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpHelper {

    public static void registerPayment(android.content.Context context,
                                       MerchantModel merchant,
                                       ProductModel[] products,
                                       final IPaymentCallback callback){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url="http://cashierapi.azurewebsites.net/api/Payment";

        JSONObject requestBody = new JSONObject();

        try{
            JSONObject merchantJson = new JSONObject();
            // merchant
            merchantJson.put("name", merchant.getName());
            merchantJson.put("ico", merchant.getIco());
            merchantJson.put("street", merchant.getStreet());
            merchantJson.put("streetNumber", merchant.getStreetNumber());
            merchantJson.put("city", merchant.getCity());
            merchantJson.put("psc", merchant.getPsc());

            JSONArray productJsonArr = new JSONArray();
            for (int i = 0; i < products.length; i++) {
                // products
                JSONObject product = new JSONObject();
                product.put("name", products[i].getName());
                product.put("code", products[i].getCode());
                product.put("numberOfProducts", products[i].getNumberOfProducts());
                product.put("unitPrice", products[i].getUnitPrice());
                productJsonArr.put(product);
            }

            // req object
            requestBody.put("merchant", merchantJson);
            requestBody.put("productsInBasket", productJsonArr);
        }catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(Login_screen.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();
                        try {
                            String paymentTime = response.getString("paymentTime");
                            boolean paymentState = response.getBoolean("paymentSuccesfull");

                            JSONObject paymentJSON = response.getJSONObject("payment");
                            JSONObject merchantJSON = paymentJSON.getJSONObject("merchant");
                            JSONArray productsInBasketJSONArr = paymentJSON.getJSONArray("productsInBasket");

                            MerchantModel merchantObj = new MerchantModel(
                                    merchantJSON.getString("name"),
                                    merchantJSON.getString("ico"),
                                    merchantJSON.getString("street"),
                                    merchantJSON.getString("streetNumber"),
                                    merchantJSON.getString("city"),
                                    merchantJSON.getString("psc"));

                            ProductModel[] productObjArr = new ProductModel[productsInBasketJSONArr.length()];

                            for (int i=0; i < productsInBasketJSONArr.length(); i++){
                                JSONObject productJSON = productsInBasketJSONArr.getJSONObject(i);
                                ProductModel productObj = new ProductModel(
                                        productJSON.getString("name"),
                                        productJSON.getString("code"),
                                        productJSON.getInt("numberOfProducts"),
                                        (float)productJSON.getDouble("unitPrice")
                                );
                                productObjArr[i]=productObj;
                            }

                            PaymentResultModel paymentResultObj = new PaymentResultModel(paymentTime, merchantObj, productObjArr, paymentState);
                            callback.onSuccess(paymentResultObj);

                        } catch (JSONException e) {
                            callback.onError("");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error", "Error: " + error.getMessage());
                    }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public static void checkApi(android.content.Context context, final ICheckApiCallback callback){
        String url = "http://cashierapi.azurewebsites.net/api/Payment";

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonRequst = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                try{
                    String result = response.getString(0).toString();
                    callback.onSuccess(result);
                }catch (JSONException e){
                    callback.onError("");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonRequst);
    }
}

