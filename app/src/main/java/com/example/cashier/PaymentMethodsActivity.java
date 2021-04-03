package com.example.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentMethodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        Button backBtn = findViewById(R.id.paymentMethodsBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                OnBackBtnClick();
            }
        });

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

    private void OnBackBtnClick(){
        startActivity(new Intent(PaymentMethodsActivity.this, MainActivity.class));
    }

    private void OnCashPaymentBtnClick(){
        startActivity(new Intent(PaymentMethodsActivity.this, ChangeActivity.class));
    }

    private void OnCardPaymentBtnClick(){
        startActivity(new Intent(PaymentMethodsActivity.this, BillActivity.class));
    }
}