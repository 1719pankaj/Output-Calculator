package com.example.outputcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper1 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Normal.db";
    private static final String TABLE_NAME1 = "Normals";

    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME1 + " (ID,TRUCK_UNLOAD,WAGON_UNLOAD,WAGON_PF" +
                ",PF_SHED,TRUCK_LODING,TRUCK_PF,SHED_WAGON,WAGON_TRUCK,REFILLING,RESTACKING,WEIGHMENT,NORMAL,OTNORM)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }

    //For INSERT or SAVE Data----
    public boolean insertdata(int id,short truckunload, short wagontoshed, short wagontopf, short pftoshed, short truckloading,
                              short trucktopf, short shedtowagon, short wagontotruck, short refill, short restack, short weight, short norm, short otnorm) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("TRUCK_UNLOAD", truckunload);
        contentValues.put("WAGON_UNLOAD", wagontoshed);
        contentValues.put("WAGON_PF", wagontopf);
        contentValues.put("PF_SHED", pftoshed);
        contentValues.put("TRUCK_LODING", truckloading);
        contentValues.put("TRUCK_PF", trucktopf);
        contentValues.put("SHED_WAGON", shedtowagon);
        contentValues.put("WAGON_TRUCK", wagontotruck);
        contentValues.put("REFILLING", refill);
        contentValues.put("RESTACKING", restack);
        contentValues.put("WEIGHMENT", weight);
        contentValues.put("NORMAL", norm);
        contentValues.put("OTNORM", otnorm);

        long result = sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);
        return result != -1;
    }
    //For Update Data----
    public boolean updateData(String id,short truckunload, short wagontoshed, short wagontopf, short pftoshed, short truckloading,
                              short trucktopf, short shedtowagon, short wagontotruck, short refill, short restack, short weight, short norm, short otnorm) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("TRUCK_UNLOAD", truckunload);
        contentValues.put("WAGON_UNLOAD", wagontoshed);
        contentValues.put("WAGON_PF", wagontopf);
        contentValues.put("PF_SHED", pftoshed);
        contentValues.put("TRUCK_LODING", truckloading);
        contentValues.put("TRUCK_PF", trucktopf);
        contentValues.put("SHED_WAGON", shedtowagon);
        contentValues.put("WAGON_TRUCK", wagontotruck);
        contentValues.put("REFILLING", refill);
        contentValues.put("RESTACKING", restack);
        contentValues.put("WEIGHMENT", weight);
        contentValues.put("NORMAL", norm);
        contentValues.put("OTNORM", otnorm);

        sqLiteDatabase.update(TABLE_NAME1, contentValues, "ID = ?", new String[]{id});
        return true;
    }
    // For GET Data-----
    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME1, null);
        return cursor;
    }
}
