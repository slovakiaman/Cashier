package com.example.cashier;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cashier.helpers.DatabaseHelper;
import com.example.cashier.state_manager.ProductModel;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_activity_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*.4));


    }
    public void buttonAdd(View v){
        EditText tName = findViewById(R.id.textProductName);
        EditText tPrice = findViewById(R.id.textProductPrice);
        String msg = "";
        String name = tName.getText().toString();
        String sPrice = tPrice.getText().toString();
        if(name.equals("") || sPrice.equals("")) {
            msg = "Wrong inputs!";
        }else{


            float price = Float.parseFloat(sPrice);

            Bundle b = getIntent().getExtras();
            String code = "";
            if (b != null)
                code = b.getString("key");

            ProductModel product = new ProductModel(name, code, 1, price);
            DatabaseHelper db = new DatabaseHelper(this);
            db.addOne(product, null);
            msg = "Product added";
        }
        finish();

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void buttonCancel(View v){
        finish();
    }
}
