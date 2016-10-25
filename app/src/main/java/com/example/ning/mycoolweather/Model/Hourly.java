package com.example.ning.mycoolweather.Model;

/**
 * Created by Ning on 2016/9/27.
 */// 天气预测的listview的每一行的
public class Hourly {
    private String time;
    private String tmp;
    private String rain;
    private String wind;


    public String getTmp() {
        return tmp;
    }

    public String getTime() {
        return time;
    }

    public String getRain() {
        return rain;
    }

    public String getWind() {
        return wind;
    }


    public Hourly(String time, String tmp, String rain, String wind){
        this.time = time;
        this.tmp = tmp;
        this.rain = rain;
        this.wind = wind;
    }

}
