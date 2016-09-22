package com.example.ning.mycoolweather.Model;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ning on 2016/9/19.
 */
public class CoolWeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
}
