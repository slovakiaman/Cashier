package com.example.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class BillActivity extends AppCompatActivity {

    public ArrayList<ProductModel> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        products.add(new ProductModel("Chlieb","hfg5j4gh5j",1,1.5,CodeType.EAN));
        products.add(new ProductModel("Syr","56654sdgs",2,3.2,CodeType.EAN));
        products.add(new ProductModel("Mlieko","hfg5j4sdgh5j",12,0.49,CodeType.EAN));
        products.add(new ProductModel("Kakao","hfg5sdg5j",1,1.2,CodeType.EAN));
        products.add(new ProductModel("Borovicka","hfg5sdg5j",1,10,CodeType.EAN));
        products.add(new ProductModel("Ananas","hfg5sdg5j",1,2.2,CodeType.EAN));
        products.add(new ProductModel("E","hfg5sdg5j",1,2.2,CodeType.EAN));
        products.add(new ProductModel("Anaasdgsadnas","hfg5sdg5j",1,2.2,CodeType.EAN));


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

    private String generateBill(ArrayList<ProductModel> products){
        LocalDateTime myDateObj = LocalDateTime.now();
        String date = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        String address = String.format("%40s \n","Nazov Prevadzky");
        address += String.format("%40s \n","Nazov Ulice 12345");
        address += String.format("%40s \n","123 45 Nazov Mesta");
        address += String.format("%20s %20s\n%40s\n","ICO: 1234567890","IC DPH: 9876543210",date);
        //String head = String.format("%40s\n\n", "   Invoice Reciept   ");
        String s = String.format("%-15s %-10s %25s\n", "Item","Qty","Price");
        String s1 = String.format("%-15s %-10s %35s\n","---------","---------"," --------------");
        String output = address +  s + s1;
        double sum = 0.0;

        for(ProductModel product : products)
        {
            String name = product.GetName();
            int ammount = product.GetAmmount();
            double price = product.GetPrice();
            String code = product.GetCode();
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

