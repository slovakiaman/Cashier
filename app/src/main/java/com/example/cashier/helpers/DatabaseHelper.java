package com.example.cashier.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cashier.state_manager.MerchantModel;
import com.example.cashier.state_manager.ProductModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PRODUCT_NAME = "PRODUCT_" + NAME;
    public static final String PRODUCT_CODE = "PRODUCT_CODE";
    public static final String UNIT_PRICE = "UNIT_PRICE";
    public static final String MERCHANT_TABLE = "MERCHANT_TABLE";
    public static final String ICO = "ICO";
    public static final String STREET = "STREET";
    public static final String STREET_NUMBER = "STREET_NUMBER";
    public static final String CITY = "CITY";
    public static final String PSC = "PSC";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "products.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PRODUCT_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT, " + PRODUCT_CODE + " TEXT, " + UNIT_PRICE + " FLOAT)");
        db.execSQL("CREATE TABLE " + MERCHANT_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT, " + ICO + " TEXT, " + STREET + " TEXT, " + STREET_NUMBER + " TEXT, " + CITY + " TEXT, " + PSC + " TEXT)");
        fillData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MERCHANT_TABLE);

        onCreate(db);
    }

    public boolean insertMerchant(MerchantModel merchant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long val;

        cv.put(NAME,merchant.getName());
        cv.put(ICO,merchant.getIco());
        cv.put(STREET,merchant.getStreet());
        cv.put(STREET_NUMBER,merchant.getStreetNumber());
        cv.put(CITY,merchant.getCity());
        cv.put(PSC,merchant.getPsc());

        val = db.insert(MERCHANT_TABLE, null, cv);

        if(val == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public void updateMerchant(MerchantModel merchant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME,merchant.getName());
        cv.put(ICO,merchant.getIco());
        cv.put(STREET,merchant.getStreet());
        cv.put(STREET_NUMBER,merchant.getStreetNumber());
        cv.put(CITY,merchant.getCity());
        cv.put(PSC,merchant.getPsc());

         db.update(MERCHANT_TABLE, cv, "ID = ?", new String[]{"1"});

    }

    public MerchantModel getMerchantDb(){
        MerchantModel merchant = null;
        String query = "SELECT * FROM " + MERCHANT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){ //return true if item was selected
            String name = cursor.getString(1);
            String ico = cursor.getString(2);
            String street = cursor.getString(3);
            String streetNum = cursor.getString(4);
            String city = cursor.getString(5);
            String psc = cursor.getString(6);
            merchant = new MerchantModel(name,ico,street,streetNum,city,psc);
        }

        cursor.close();
        db.close();
        return merchant;


    }

    public boolean addOne(ProductModel product, SQLiteDatabase db){

        SQLiteDatabase dba = db == null ? this.getWritableDatabase() : db;
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_NAME,product.getName());
        cv.put(PRODUCT_CODE,product.getCode());
        cv.put(UNIT_PRICE,product.getUnitPrice());

        long insert = dba.insert(PRODUCT_TABLE, null, cv);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public ProductModel getProductByCode(String code){
        ProductModel product = null;
        String query = "SELECT * FROM " + PRODUCT_TABLE + " WHERE " + PRODUCT_CODE + " == "  + '"' + code + '"';
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){ //return true if item was selected
            String productName = cursor.getString(1);
            String productCode = cursor.getString(2);
            float productPrice = cursor.getFloat(3);
            product = new ProductModel(productName,productCode,1,productPrice);
        }
        else{
            //failed
        }
        cursor.close();
        db.close();
        return product;
    }
    private void fillData(SQLiteDatabase db) {
        addOne(new ProductModel("Chlieb","11112222",1,1.2f),db);
        addOne(new ProductModel("Vianocka","11112223",1,1.49f),db);
        addOne(new ProductModel("Mlieko","11112224",1,0.59f),db);
        addOne(new ProductModel("Maslo","11112225",1,1.2f),db);

    }
}
