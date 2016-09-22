package com.example.ning.mycoolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by Ning on 2016/9/19.
 */
public class CoolweatherOpenHelper extends SQLiteOpenHelper {


    private final String CREATE_PROVINCE = "create table Province ("
            + "provinceName text," + "provinceCode text )";

    private final String CREATE_CITY = "create table City("
            + "cityName text," + "cityCode text," + "provinceCode text)";

    private final String CREATE_COUNTY = "create table County("
            + "countyName text," + "countyCode text," + "cityCode text)";

    public CoolweatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
