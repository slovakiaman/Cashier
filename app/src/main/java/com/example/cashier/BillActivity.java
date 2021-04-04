package com.example.cashier;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cashier.helpers.HttpHelper;
import com.example.cashier.helpers.IPaymentCallback;
import com.example.cashier.state_manager.MerchantModel;
import com.example.cashier.state_manager.PaymentResultModel;
import com.example.cashier.state_manager.ProductModel;
import com.example.cashier.state_manager.StateManager;

import org.json.JSONException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class BillActivity extends AppCompatActivity {

    /*
        Payment type, parsed from intent message values:(card,cash)
     */
    private String paymentType = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String messageCard = intent.getStringExtra(PaymentMethodsActivity.EXTRA_MESSAGE_PAYMENT_METHODS_ACTIVITY);
        String messageCash = intent.getStringExtra(ChangeActivity.EXTRA_MESSAGE_CHANGE_ACTIVITY);

        this.paymentType = (messageCard != null)? messageCard : messageCash;
        HttpHelper.registerPayment(
                BillActivity.this,
                StateManager.getMerchant(),
                (ProductModel[])StateManager.getProductsInBasket().toArray(new ProductModel[StateManager.getProductsInBasket().size()]),
                new IPaymentCallback() {
            @Override
            public void onSuccess(PaymentResultModel result) throws JSONException {
                TextView status = findViewById(R.id.textPaymentStatus);
                status.setText("Payment succes"); // need to add response from API

                TextView bill = findViewById(R.id.textBill);
                bill.setMovementMethod(new ScrollingMovementMethod());
                bill.setText(generateBill(result));
            }

            @Override
            public void onError(String result) {
                TextView status = findViewById(R.id.textPaymentStatus);
                status.setText("Payment failed"); // need to add response from API
            }
        });

        Button btnHome = (Button)findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onHomeButtonClick();
            }
        });
    }

    public void onHomeButtonClick()
    {
        StateManager.ClearBasket();
        startActivity(new Intent(BillActivity.this, MainActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String generateBill(PaymentResultModel paymentResult){
        MerchantModel merchantModel = paymentResult.getMerchant();
        String paymentTime = paymentResult.getPaymentTime();

        String address = String.format("%40s \n",merchantModel.getName());
        address += String.format("%40s \n",merchantModel.getStreet() + " " + merchantModel.getStreetNumber());
        address += String.format("%40s \n",merchantModel.getPsc()+" "+merchantModel.getCity());
        address += String.format("%40s \n%40s\n","ICO: "+ merchantModel.getIco(),paymentTime);
        address += String.format("%40s\n","Payment type : "+ paymentType);
        //String head = String.format("%40s\n\n", "   Invoice Reciept   ");
        String s = String.format("%-15s %-10s %25s\n", "Item","Qty","Price");
        String s1 = String.format("%-15s %-10s %35s\n","---------","---------"," --------------");
        String output = address +  s + s1;
        float sum = 0.0f;

        for(ProductModel product : paymentResult.getBasket())
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

