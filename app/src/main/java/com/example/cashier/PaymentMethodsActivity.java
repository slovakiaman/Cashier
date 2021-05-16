package com.example.cashier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethodsActivity extends AppCompatActivity {

    /*
        Message key to bill activity, send payment type message (card)
     */
    public static final String EXTRA_MESSAGE_PAYMENT_METHODS_ACTIVITY = "com.example.cashier.paymentMethodsActivity_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        Button cashBtn = findViewById(R.id.cashPaymentBtn);
        cashBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                OnCashPaymentBtnClick();
            }
        });

        Button cardBtn = findViewById(R.id.cardPaymentBtn);
        cardBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                OnCardPaymentBtnClick();
            }
        });
    }

    public void clickBack(View v){
        startActivity(new Intent(PaymentMethodsActivity.this, MainActivity.class));
    }

    private void OnCashPaymentBtnClick(){
        startActivity(new Intent(PaymentMethodsActivity.this, ChangeActivity.class));
    }

    private void OnCardPaymentBtnClick(){
        Intent intent = new Intent(PaymentMethodsActivity.this, PaymentProgressActivity.class);
        startActivity(intent);
    }
}