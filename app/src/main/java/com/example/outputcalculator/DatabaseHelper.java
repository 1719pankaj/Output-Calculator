package com.example.outputcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Rate.db";
    private static final String TABLE_NAME = "Rates";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID,BASIC,DA,HRA,DAYS,BASICOLD,DAOLD,HRAOLD,PERDAYOLD" +
                ",SLAB_1,SLAB_2,SLAB_3,SLAB_4,HIGHT_11,HIGHT_13,HIGHT_15,HIGHT_17,HIGHT_19,LEAD_1,LEAD_2,LEAD_3,LEAD_4)");
      }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //For INSERT or SAVE Data----
    public Boolean insertdata(int id, String basic, Double da, Double house, int days, String basicold, Double daold, Double houseold,Double perday_rate,
                              Double slab_a, Double slab_b, Double slab_c, Double slab_d,
                              Double hight_a, Double hight_b,Double hight_c, Double hight_d, Double hight_e, Double lead_a, Double lead_b, Double lead_c, Double lead_d) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("BASIC", basic);
        contentValues.put("DA", da);
        contentValues.put("HRA", house);
        contentValues.put("DAYS", days);
        contentValues.put("BASICOLD", basicold);
        contentValues.put("DAOLD", daold);
        contentValues.put("HRAOLD", houseold);
        contentValues.put("PERDAYOLD", perday_rate);
        contentValues.put("SLAB_1", slab_a);
        contentValues.put("SLAB_2", slab_b);
        contentValues.put("SLAB_3", slab_c);
        contentValues.put("SLAB_4", slab_d);
        contentValues.put("HIGHT_11", hight_a);
        contentValues.put("HIGHT_13", hight_b);
        contentValues.put("HIGHT_15", hight_c);
        contentValues.put("HIGHT_17", hight_d);
        contentValues.put("HIGHT_19", hight_e);
        contentValues.put("LEAD_1", lead_a);
        contentValues.put("LEAD_2", lead_b);
        contentValues.put("LEAD_3", lead_c);
        contentValues.put("LEAD_4", lead_d);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    //For Update Data----

    public Boolean updateData(String id, String basic, Double da, Double house, int days, String basicold, Double daold, Double houseold,Double perday_rate,
                              Double slab_a, Double slab_b, Double slab_c, Double slab_d,
                              Double hight_a, Double hight_b, Double hight_c, Double hight_d, Double hight_e, Double lead_a, Double lead_b, Double lead_c, Double lead_d) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("BASIC", basic);
        contentValues.put("DA", da);
        contentValues.put("HRA", house);
        contentValues.put("DAYS", days);
        contentValues.put("BASICOLD", basicold);
        contentValues.put("DAOLD", daold);
        contentValues.put("HRAOLD", houseold);
        contentValues.put("PERDAYOLD", perday_rate);
        contentValues.put("SLAB_1", slab_a);
        contentValues.put("SLAB_2", slab_b);
        contentValues.put("SLAB_3", slab_c);
        contentValues.put("SLAB_4", slab_d);
        contentValues.put("HIGHT_11", hight_a);
        contentValues.put("HIGHT_13", hight_b);
        contentValues.put("HIGHT_15", hight_c);
        contentValues.put("HIGHT_17", hight_d);
        contentValues.put("HIGHT_19", hight_e);
        contentValues.put("LEAD_1", lead_a);
        contentValues.put("LEAD_2", lead_b);
        contentValues.put("LEAD_3", lead_c);
        contentValues.put("LEAD_4", lead_d);

        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});

        return true;
    }

    // For GET Data-----
    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }
}
