package com.example.cashier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String ID = "ID";
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final String PRODUCT_CODE = "PRODUCT_CODE";
    public static final String UNIT_PRICE = "UNIT_PRICE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PRODUCT_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT, " + PRODUCT_CODE + " TEXT, " + UNIT_PRICE + " FLOAT)";
        db.execSQL(createTableStatement);
        fillData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ProductModel product,SQLiteDatabase db){

        SQLiteDatabase dba = db == null ? this.getWritableDatabase() : db;
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_NAME,product.GetName());
        cv.put(PRODUCT_CODE,product.GetCode());
        cv.put(UNIT_PRICE,product.GetPrice());

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
        String query = "SELECT * FROM " + PRODUCT_TABLE + " WHERE " + PRODUCT_CODE + " LIKE " + code;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){ //return true if item was selected
            String productName = cursor.getString(1);
            String productCode = cursor.getString(2);
            double productPrice = cursor.getDouble(3);
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
        addOne(new ProductModel("Chlieb","11112222",1,1.2),db);
        addOne(new ProductModel("Vianocka","11112223",1,1.49),db);
        addOne(new ProductModel("Mlieko","11112224",1,0.59),db);
        addOne(new ProductModel("Maslo","11112225",1,1.2),db);
    }
}
