package com.example.cashier;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // init global state
        StateManager.InitState();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scanButton = (Button)findViewById(R.id.btnScan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scanCode();
            }
        });

        Button payButton = (Button)findViewById(R.id.btnPay);
        payButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // TODO intent to Payment methods activity
                startActivity(new Intent(MainActivity.this, BillActivity.class));//docasny odkaz na test bill

            }
        });
    }

    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
         IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
         if (result != null){
             if (result.getContents() != null){
                 AlertDialog.Builder builder = new AlertDialog.Builder(this);
                 String content = result.getContents();
                 // add to store
                 StateManager.AddScannedCode(content);

                 builder.setMessage(content);
                 builder.setTitle("Scanning result");
                 builder.setPositiveButton("Scan again", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         scanCode();
                     }
                 }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         //Intent intent = new Intent(MainActivity.this, Scanner.class);
                         //startActivity(intent);
                         //finish();
                         dialog.cancel();
                     }
                 });
                 AlertDialog dialog = builder.create();
                 dialog.show();
             }
             else{
                 Toast.makeText(this, "No results", Toast.LENGTH_LONG).show();
             }
         }else{
             super.onActivityResult(requestCode, resultCode, data);
         }
    }
}