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
    SQLiteDatabase mSQLiteDatabase;
    Dprocess mDprocess;
    public Dprocess(Context ct) {
        CoolweatherOpenHelper co = new CoolweatherOpenHelper(ct,"Ning",null,1);
        mSQLiteDatabase = co.getWritableDatabase();
    }

    private Dprocess getinstance(Context ct){
        if(mDprocess == null)
            return new Dprocess(ct);
        return mDprocess;
    }

    public void saveProvices(List<Province> provinces){
         if(provinces != null && provinces.size() > 0){
             ContentValues cv = new ContentValues();
             for (int i = 0; i < provinces.size() ; i++) {
                  cv.put("provinceName",provinces.get(i).getProvinceName());
                  cv.put("provinceCode",provinces.get(i).getProvinceCode());
                  mSQLiteDatabase.insert("Provice",null,cv);
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
                cv.put("provieceID",Cities.get(i).getProvinceId());
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
                cv.put("cityID",Counties.get(i).getCityId());
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
               Province p;
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
        Cursor cursor = mSQLiteDatabase.query("City", null, "provinceId = ?", new String[]{code}, null, null, null);
        List<City> allCities = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                city = new City();
                city.setCityCode(cursor.getString(cursor.getColumnIndex("cityCode")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("cityName")));
                city.setProvinceId(code);
                allCities.add(city);
            }while (cursor.moveToNext());
        }
        return allCities;
    }


    public List<County> getallCounties(String code){
        County county;
        Cursor cursor = mSQLiteDatabase.query("City", null, "cityId = ?", new String[]{code}, null, null, null);
        List<County> allCounties = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                county = new County();
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("cityCode")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("cityName")));
                county.setCityId(code);
                allCounties.add(county);
            }while (cursor.moveToNext());
        }
        return allCounties;
    }

}
