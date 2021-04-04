package com.example.cashier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cashier.helpers.DatabaseHelper;
import com.example.cashier.state_manager.MerchantModel;
import com.example.cashier.state_manager.ProductModel;
import com.example.cashier.state_manager.StateManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        MerchantModel merchant = StateManager.getMerchant();
        if(StateManager.getMerchant() != null){
            ((EditText)findViewById(R.id.idSetName)).setText(merchant.getName());
            ((EditText)findViewById(R.id.idSetStreet)).setText(merchant.getStreet());
            ((EditText)findViewById(R.id.idSetStreetNum)).setText(merchant.getStreetNumber());
            ((EditText)findViewById(R.id.idSetCity)).setText(merchant.getCity());
            ((EditText)findViewById(R.id.idSetICO)).setText(merchant.getIco());
            ((EditText)findViewById(R.id.idSetPSC)).setText(merchant.getPsc());
        }
    }

    public void buttonSave(View v){
        String msg = "";
        String name = ((EditText)findViewById(R.id.idSetName)).getText().toString();
        String street = ((EditText)findViewById(R.id.idSetStreet)).getText().toString();
        String streetNum = ((EditText)findViewById(R.id.idSetStreetNum)).getText().toString();
        String city = ((EditText)findViewById(R.id.idSetCity)).getText().toString();
        String ico = ((EditText)findViewById(R.id.idSetICO)).getText().toString();
        String psc = ((EditText)findViewById(R.id.idSetPSC)).getText().toString();

        if(name.isEmpty() || street.isEmpty() || streetNum.isEmpty() || city.isEmpty() || ico.isEmpty() || psc.isEmpty()) {
            msg = "Wrong inputs!";
        }else{
            MerchantModel merchant = new MerchantModel(name,ico,street,streetNum,city,psc);

            DatabaseHelper db = new DatabaseHelper(this);
            if (StateManager.getMerchant() == null) {
                db.insertMerchant(merchant);
            } else {
                db.updateMerchant(merchant);
            }

            StateManager.setMerchant(merchant);
            msg = "Merchant saved!";
        }
        finish();

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void clickBack(View v){
        if(StateManager.getMerchant() != null){
            finish();
        }else{
            Toast toast = Toast.makeText(this, "Need to add Merchant!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}