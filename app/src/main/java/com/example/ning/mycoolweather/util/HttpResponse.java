package com.example.ning.mycoolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.ning.mycoolweather.Model.City;
import com.example.ning.mycoolweather.Model.County;
import com.example.ning.mycoolweather.Model.Hourly;
import com.example.ning.mycoolweather.Model.Province;
import com.example.ning.mycoolweather.activity.WeatherShow;
import com.example.ning.mycoolweather.db.Dprocess;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ning on 2016/9/22.
 */
public class HttpResponse {
      public static boolean saveProvincesResponse(Dprocess cd,String response){
          if(!TextUtils.isEmpty(response)){
              String[] allp = response.split(",");
              if (allp != null && allp.length > 0) {

                  List<Province> allprovice = new ArrayList<>();
                  Province p ;
                  for (String pt:allp) {
                  p = new Province();
                  String[] str = pt.split("\\|");
                  p.setProvinceCode(str[0]);
                  p.setProvinceName(str[1]);
                  allprovice.add(p);
                  }
                  cd.saveProvinces(allprovice);
                  return true;
              }

          }

          return false;
      }


    public static boolean saveCitiesResponse(Dprocess cd, String response , String code){
        if(!TextUtils.isEmpty(response)){
            String[] allc = response.split(",");
            if (allc != null && allc.length > 0) {

                List<City> allcity = new ArrayList<>();
                City p ;
                for (String ct:allc) {
                    p = new City();
                    String[] str = ct.split("\\|");
                    p.setProvinceCode(code);
                    p.setCityCode(str[0]);
                    p.setCityName(str[1]);
                    allcity.add(p);
                }
                cd.saveCities(allcity);
                return true;
            }

        }

        return false;
    }


    public static boolean saveCountiesResponse(Dprocess cd, String response, String code) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                County county;
                List<County> countyList = new ArrayList<>();
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityCode(code);
                    countyList.add(county);
                }
                cd.saveCounties(countyList);
                return true;
            }
        }
        return false;
    }



    public static void handlejson(Context ct, String response){
//        try {
//            response = new String(response.getBytes("iso-8859-1"),"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        response = response.replaceFirst("\\.","");
        response = response.replaceFirst(" ","");
        response = response.replaceFirst(" ","");
        response = response.replaceFirst(" ","");

        Gson mg = new Gson();

        bean mbean = mg.fromJson(response,bean.class);

        List<bean.HeWeatherdataservice30Bean> HeWeatherdataservice30 = mbean.getHeWeatherdataservice30();
        bean.HeWeatherdataservice30Bean bhs = HeWeatherdataservice30.get(0);
        bean.HeWeatherdataservice30Bean.BasicBean bhsb = bhs.getBasic();
        bean.HeWeatherdataservice30Bean.BasicBean.UpdateBean bhsbu = bhsb.getUpdate();
        List<bean.HeWeatherdataservice30Bean.DailyForecastBean> daily_forecast =bhs.getDaily_forecast();

        String cityName = bhsb.getCity();
        String updateTime = bhsbu.getLoc();



        bean.HeWeatherdataservice30Bean.DailyForecastBean xdb = daily_forecast.get(0);
        bean.HeWeatherdataservice30Bean.DailyForecastBean.AstroBean xab = xdb.getAstro();
        //日出
        String sunriseTime = xab.getSr();
        //日落
        String sunsetTime = xab.getSs();

        bean.HeWeatherdataservice30Bean.DailyForecastBean.CondBean xcb = xdb.getCond();

        //白天天气
        String dayWeather = xcb.getTxt_d();
        //夜晚天气
        String nightWeather = xcb.getTxt_n();

        bean.HeWeatherdataservice30Bean.DailyForecastBean.WindBean xwb = xdb.getWind();
        //风力
        String windText = xwb.getDir() + " " + xwb.getSc() + "级";
        //降水概率
        String pop = xdb.getPop();

        bean.HeWeatherdataservice30Bean.DailyForecastBean.TmpBean xtb = xdb.getTmp();
        //温度
        String tempText = xtb.getMin() + "℃~" + xtb.getMax() + "℃";
        WeatherShow.weatherlist.clear();
        for (int i = 1; i < daily_forecast.size(); i++) {
            bean.HeWeatherdataservice30Bean.DailyForecastBean x = daily_forecast.get(i);
            String datah = x.getDate();
            bean.HeWeatherdataservice30Bean.DailyForecastBean.TmpBean xt = x.getTmp();
            String tmph = xt.getMax();
            String poph = x.getPop();
            bean.HeWeatherdataservice30Bean.DailyForecastBean.WindBean xw = x.getWind();
            String windh = xw.getDir();
            Hourly hour = new Hourly(datah,tmph,poph+"%",windh);
            WeatherShow.weatherlist.add(hour);
        }
        saveWeatherInfo(ct, cityName, sunriseTime, sunsetTime, dayWeather, nightWeather, windText, pop, tempText, updateTime);
      /*  try {
            JSONObject jsb = new JSONObject(response);
            JSONArray array = jsb.getJSONArray("HeWeather data service 3.0");
            JSONObject FirstObject = (JSONObject) array.get(0);
            JSONObject basic = (JSONObject) FirstObject.get("basic");
            JSONObject update = (JSONObject) basic.get("update");
            JSONArray  forecast = (JSONArray) FirstObject.get("daily_forecast");
            JSONObject forecast_content = (JSONObject) forecast.get(0);
            JSONObject cond = (JSONObject) forecast_content.get("cond");
            JSONObject astro = (JSONObject) forecast_content.get("astro");
            JSONObject wind = (JSONObject) forecast_content.get("wind");
            JSONObject tmp = (JSONObject) forecast_content.get("tmp");
            WeatherShow.weatherlist.clear();
            for (int i = 1; i < forecast.length(); i++) {
                JSONObject json = (JSONObject) forecast.get(i);
                String datah = json.getString("date");
                JSONObject jsoncontent = (JSONObject) json.get("tmp");
                String tmph = jsoncontent.getString("max");
                String poph = json.getString("pop");
                JSONObject windcom = (JSONObject) json.get("wind");
                String windh = windcom.getString("dir");
                Hourly hour = new Hourly(datah,tmph,poph+"%",windh);
                WeatherShow.weatherlist.add(hour);
            }
            Lrucache

            //日出
            String sunriseTime = astro.getString("sr");
            //日落
            String sunsetTime = astro.getString("ss");
            //白天天气
            String dayWeather = cond.getString("txt_d");
            //夜晚天气
            String nightWeather = cond.getString("txt_n");
            //风力
            String windText = wind.getString("dir") + " " + wind.getString("sc") + "级";
            //降水概率
            String pop = forecast_content.getString("pop");
            //温度
            String tempText = tmp.getString("min") + "℃~" + tmp.getString("max") + "℃";

            //更新时间
            String updateTime = update.getString("loc");
            String[] x = updateTime.split(" ");

            //城市名
            String cityName = basic.getString("city");
            saveWeatherInfo(ct, cityName, sunriseTime, sunsetTime, dayWeather, nightWeather, windText, pop, tempText, x[1]);
        }
        catch (Exception e){
            e.printStackTrace();
        }*/

    }


    private static void saveWeatherInfo(Context context, String cityName,
                                        String sunriseTime, String sunsetTime, String dayWeather, String nightWeather,
                                        String windText, String pop, String tempText, String updateTime) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Weather", Context.MODE_PRIVATE).edit();
        editor.putString("cityName", cityName);
        editor.putString("sunriseTime", sunriseTime);
        editor.putString("sunsetTime", sunsetTime);
        editor.putString("dayWeather", dayWeather);
        editor.putString("nightWeather", nightWeather);
        editor.putString("wind", windText);
        editor.putString("pop", pop);
        editor.putString("temp", tempText);
        editor.putString("updateTime", updateTime);
        editor.apply();
    }

}


