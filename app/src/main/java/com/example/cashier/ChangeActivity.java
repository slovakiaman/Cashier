package com.example.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeActivity extends AppCompatActivity {

    /*
    Message key to bill activity, send payment type message (cash)
    */
    public static final String EXTRA_MESSAGE_CHANGE_ACTIVITY = "com.example.cashier.paymentMethodsActivity_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        Button backBtn = findViewById(R.id.changeMethodsBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                OnBackBtnClick();
            }
        });

        Button payBtn = findViewById(R.id.payButton);
        payBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                OnPayBtnClick();
            }
        });

        EditText cashPayed = findViewById(R.id.cashPayedEditText);
        cashPayed.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                OnCashPayedChange();
                return false;
            }
        });

        TextView changeText = findViewById(R.id.changeTextView);
        //changeTextView
    }

    private void OnBackBtnClick(){
        startActivity(new Intent(ChangeActivity.this, PaymentMethodsActivity.class));
    }

    private void OnPayBtnClick(){
        Intent intent = new Intent(ChangeActivity.this, BillActivity.class);
        intent.putExtra(EXTRA_MESSAGE_CHANGE_ACTIVITY, "cash");
        startActivity(intent);

    }

    private void OnCashPayedChange(){

    }
}