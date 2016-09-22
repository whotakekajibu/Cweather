package com.example.ning.mycoolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ning.mycoolweather.Model.City;
import com.example.ning.mycoolweather.Model.County;
import com.example.ning.mycoolweather.Model.Province;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ning on 2016/9/22.
 */
public class Dprocess {
    private static SQLiteDatabase  mSQLiteDatabase;
    private  static Dprocess mDprocess;
    private Dprocess(Context ct) {
        CoolweatherOpenHelper co = new CoolweatherOpenHelper(ct,"Ning",null,1);
        mSQLiteDatabase = co.getWritableDatabase();
    }

    public static Dprocess getinstance(Context ct){
        if(mDprocess == null)
            return new Dprocess(ct);
        return mDprocess;
    }

    public  void saveProvinces(List<Province> provinces){
         if(provinces != null && provinces.size() > 0){
             ContentValues cv = new ContentValues();
             for (int i = 0; i < provinces.size() ; i++) {
                  cv.put("provinceName",provinces.get(i).getProvinceName());
                  cv.put("provinceCode",provinces.get(i).getProvinceCode());
                  mSQLiteDatabase.insert("Province" ,null , cv);
                  cv.clear();
             }
         }
    }

    public void saveCities(List<City> Cities){
        if(Cities != null && Cities.size() > 0){
            ContentValues cv = new ContentValues();
            for (int i = 0; i < Cities.size() ; i++) {
                cv.put("cityName",Cities.get(i).getCityName());
                cv.put("cityCode",Cities.get(i).getCityCode());
                cv.put("provinceCode",Cities.get(i).getProvinceCode());
                mSQLiteDatabase.insert("City",null,cv);
                cv.clear();
            }
        }
    }

    public void saveCounties(List<County> Counties){
        if(Counties != null &&Counties.size() > 0){
            ContentValues cv = new ContentValues();
            for (int i = 0; i < Counties.size() ; i++) {
                cv.put("countyName",Counties.get(i).getCountyName());
                cv.put("countyCode",Counties.get(i).getCountyCode());
                cv.put("cityCode",Counties.get(i).getCityCode());
                mSQLiteDatabase.insert("County",null,cv);
                cv.clear();
            }
        }
    }

    public List<Province> getallProvices(){
        Province p;
        Cursor cursor = mSQLiteDatabase.query("Province",null,null,null,null,null,null);
        List<Province> allProvices = new ArrayList<>();
        if (cursor.moveToFirst()) {
           do {
               p = new Province();
               p.setProvinceCode(cursor.getString(cursor.getColumnIndex("provinceCode")));
               p.setProvinceName(cursor.getString(cursor.getColumnIndex("provinceName")));
               allProvices.add(p);
            }while (cursor.moveToNext());
        }
        return allProvices;
    }


    public List<City> getallCities(String code){
        City city;
        Cursor cursor = mSQLiteDatabase.query("City", null, "provinceCode = ?", new String[]{code}, null, null, null);
        List<City> allCities = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                city = new City();
                city.setCityCode(cursor.getString(cursor.getColumnIndex("cityCode")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("cityName")));
                city.setProvinceCode(code);
                allCities.add(city);
            }while (cursor.moveToNext());
        }
        return allCities;
    }


    public List<County> getallCounties(String code){
        County county;
        Cursor cursor = mSQLiteDatabase.query("City", null, "cityCode = ?", new String[]{code}, null, null, null);
        List<County> allCounties = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                county = new County();
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("cityCode")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("cityName")));
                county.setCityCode(code);
                allCounties.add(county);
            }while (cursor.moveToNext());
        }
        return allCounties;
    }

}
