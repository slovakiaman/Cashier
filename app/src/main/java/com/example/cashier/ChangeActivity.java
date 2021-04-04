package com.example.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cashier.state_manager.StateManager;

public class ChangeActivity extends AppCompatActivity {

    private Button payButton = null;

    /*
    Message key to bill activity, send payment type message (cash)
    */
    public static final String EXTRA_MESSAGE_CHANGE_ACTIVITY = "com.example.cashier.paymentMethodsActivity_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        // disable pay button by default
        this.payButton = findViewById(R.id.payButton);
        this.payButton.setEnabled(false);

        // set basket price
        TextView basketPriceText = findViewById(R.id.basketPriceText);
        double basketPriceNum = Math.round(StateManager.getBasketValue() * 100.0) / 100.0;
        basketPriceText.setText("Basket: " + basketPriceNum + " €");

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
        cashPayed.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                OnCashPayedChange(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void OnBackBtnClick(){
        startActivity(new Intent(ChangeActivity.this, PaymentMethodsActivity.class));
    }

    private void OnPayBtnClick(){
        Intent intent = new Intent(ChangeActivity.this, BillActivity.class);
        intent.putExtra(EXTRA_MESSAGE_CHANGE_ACTIVITY, "cash");
        startActivity(intent);
    }

    private void OnCashPayedChange(String str){
        try {
            double cash = Double.parseDouble(str);
            double basketPrice = StateManager.getBasketValue();
            double change = cash-basketPrice;
            double roundOff = Math.round(change * 100.0) / 100.0;

            if (change >= 0){
                this.payButton.setEnabled(true);
            }else{
                this.payButton.setEnabled(false);
            }

            TextView changeText = findViewById(R.id.changeTextView);
            changeText.setText(roundOff + " €");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}