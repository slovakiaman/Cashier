package com.example.cashier;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.cashier.state_manager.MerchantModel;
import com.example.cashier.state_manager.ProductModel;
import com.example.cashier.state_manager.StateManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class BillActivity extends AppCompatActivity {

    public ArrayList<ProductModel> products = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        products = StateManager.getProductsInBasket();

        TextView status = findViewById(R.id.textPaymentStatus);
        status.setText("Payment succes"); // need to add response from API

        TextView bill = findViewById(R.id.textBill);
        bill.setMovementMethod(new ScrollingMovementMethod());
        bill.setText(generateBill(products));

    }


    public void onHomeButtonClick(View v)
    {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String generateBill(ArrayList<ProductModel> products){
        MerchantModel merchantModel = StateManager.getMerchant();

        LocalDateTime myDateObj = LocalDateTime.now();
        String date = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        String address = String.format("%40s \n",merchantModel.getName());
        address += String.format("%40s \n",merchantModel.getStreet() + " " + merchantModel.getStreetNumber());
        address += String.format("%40s \n",merchantModel.getPsc()+" "+merchantModel.getCity());
        address += String.format("%40s \n%40s\n","ICO: "+ merchantModel.getIco(),date);
        //String head = String.format("%40s\n\n", "   Invoice Reciept   ");
        String s = String.format("%-15s %-10s %25s\n", "Item","Qty","Price");
        String s1 = String.format("%-15s %-10s %35s\n","---------","---------"," --------------");
        String output = address +  s + s1;
        float sum = 0.0f;

        for(ProductModel product : products)
        {
            String name = product.getName();
            int ammount = product.getNumberOfProducts();
            float price = product.getUnitPrice();
            String code = product.getCode();
            sum += (price*ammount);

            String sPrice = String.format("\t%5s ks * %5.2f %30.2f %n",ammount,price,price*ammount);
            String sName = String.format("%-40s %n",name);
            output +=  sName + sPrice;
        }
        output += String.format("%-40s\n","---------------------------------------------------------");
        String Total = String.format("%-40s\n","Total");
        String total = String.format("%50.2f EUR\n\n",sum);
        output += Total + total ;


        return output;
    }
}

