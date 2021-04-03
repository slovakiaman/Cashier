package com.example.cashier;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashier.helpers.DatabaseHelper;
import com.example.cashier.helpers.HttpHelper;
import com.example.cashier.helpers.ICheckApiCallback;
import com.example.cashier.helpers.IPaymentCallback;
import com.example.cashier.recyclerView.RViewAdapter;
import com.example.cashier.state_manager.MerchantModel;
import com.example.cashier.state_manager.PaymentResultModel;
import com.example.cashier.state_manager.ProductModel;
import com.example.cashier.state_manager.StateManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // init global state
        StateManager.InitState();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
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
                startActivity(new Intent(MainActivity.this, BillActivity.class));//docasny odkaz na test bill - potom presmerovat na pay
            }
        });
        TextView totalPriceText = (TextView) findViewById(R.id.textTotalPrice);
        totalPriceText.setText("0.00€");

        recyclerView = (RecyclerView)findViewById(R.id.RView);
        recyclerView.setHasFixedSize(true);

        rvManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvManager);

        rvAdapter = new RViewAdapter(totalPriceText);
        recyclerView.setAdapter(rvAdapter);
    }

    private void addProduct(ProductModel productModel) {
        ((RViewAdapter)rvAdapter).addItem(productModel);
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
                 DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                 ProductModel product = databaseHelper.getProductByCode(content);

                 String info = "";
                 if(product == null){
                     info  = "Unknown item! ";
                     builder.setNeutralButton("Insert", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             //register item
                             Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                             Bundle b = new Bundle();
                             b.putString("key", content); //send code(content) to register activity
                             intent.putExtras(b);
                             startActivity(intent);
                         }
                     });
                 }
                 else{
                     info = product.toString();
                     addProduct(product);
                 }


                 builder.setMessage(content + " \n" +info);
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