package com.example.cashier.helpers;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpHelper {

    public static void registerPayment(android.content.Context context){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url="http://cashierapi.azurewebsites.net/api/Payment";

        JSONObject requestBody = new JSONObject();

        try{
            JSONObject merchantJson = new JSONObject();
            // merchant
            merchantJson.put("name", "Potraviny");
            merchantJson.put("ico", "44848");
            merchantJson.put("street", "Nova");
            merchantJson.put("streetNumber", "123/54");
            merchantJson.put("city", "Bratislava");
            merchantJson.put("psc", "03156");

            // products
            JSONObject product = new JSONObject();
            product.put("id", 0);
            product.put("name", "Mars tycinka");
            product.put("code", "3454sda3");
            product.put("numberOfProducts", 1);
            product.put("unitPrice", 0.5f);

            JSONArray productJsonArr = new JSONArray();
            productJsonArr.put(product.toString());

            // req object
            requestBody.put("merchant", merchantJson.toString());
            requestBody.put("productsInBasket", productJsonArr.toString());
        }catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(Login_screen.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();
                        try {
                            Log.d("JSON", String.valueOf(response));
                            //loading.dismiss();
                            String Error = response.getString("httpStatus");
                            if (Error.equals("")||Error.equals(null)){

                            }else if(Error.equals("OK")){
                                JSONObject body = response.getJSONObject("body");
                                int a = 3;
                            }else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //loading.dismiss();
                        }
//                        resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error", "Error: " + error.getMessage());
                        //Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
        });
        requestQueue.add(jsonObjectRequest);
    }
}

