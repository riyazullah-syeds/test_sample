package com.entrolabs.testapplications.fininfocomtest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dummyData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "DummyData";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CITY = "city";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_AGE + " INTEGER, "
                + COLUMN_CITY + " TEXT)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addDummyData(DummyData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getTitle());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        values.put(COLUMN_AGE, data.getAge());
        values.put(COLUMN_CITY, data.getCity());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public List<DummyData> getAllData() {
        List<DummyData> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") DummyData data = new DummyData(
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CITY))
                );
                dataList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }
}
