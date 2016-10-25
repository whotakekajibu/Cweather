package com.example.ning.mycoolweather.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ning.mycoolweather.Model.Hourly;
import com.example.ning.mycoolweather.R;
import com.example.ning.mycoolweather.util.HttpRequest;
import com.example.ning.mycoolweather.util.HttpRequestCallback;
import com.example.ning.mycoolweather.util.HttpResponse;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ning on 2016/9/27.
 */
public class WeatherShow extends AppCompatActivity implements View.OnClickListener {

    // 城市切换按钮
    private Button citySwitch;
    // 刷新数据按钮
    private Button weatherRefresh;
    // 城市名
    private TextView cityName;

    // 白天
    private TextView  DayWeather;
    //夜晚天气
    private  TextView Nightweather;

    // 温度
    private TextView temp;
    // 日出时间
    private TextView sunriseTime;
    // 日落时间
    private TextView sunsetTime;
    // 风力
    private TextView wind;
    // 降水概率
    private TextView pop;
    // 发布时间
    private TextView updateTime;
    // 今日天气预测列表
    private ListView listview;


    private SharedPreferences sharedPreferences;


    public static List<Hourly> weatherlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherlayout);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
        init();

    }

    private void init() {

        citySwitch = (Button) findViewById(R.id.button);
        weatherRefresh = (Button) findViewById(R.id.button2);
        listview = (ListView) findViewById(R.id.listView2);
        citySwitch.setOnClickListener(this);
        weatherRefresh.setOnClickListener(this);
        cityName = (TextView) findViewById(R.id.textView8);
        temp = (TextView) findViewById(R.id.textView3);
        sunriseTime = (TextView) findViewById(R.id.textView20);
        sunsetTime = (TextView) findViewById(R.id.textView16);
        DayWeather = (TextView) findViewById(R.id.textView11);
        Nightweather = (TextView) findViewById(R.id.textView12);
        wind = (TextView) findViewById(R.id.textView6);
        pop = (TextView) findViewById(R.id.textView5);
        updateTime = (TextView) findViewById(R.id.textView13);
        sharedPreferences = getSharedPreferences("Weather", Context.MODE_PRIVATE);

        String countyName = getIntent().getStringExtra("CountyName");
        // 当countyName不为空
        if (!TextUtils.isEmpty(countyName)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("CountyName", countyName);
            editor.apply();
        } else {
            countyName = sharedPreferences.getString("CountyName", "");

        }
       // weatherRefresh.setText("同步中……");
        //Log.d("kajibu", countyName);
        queryFromServer(countyName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("MainActivity", true);
                startActivity(intent);
                finish();
                break;
            case R.id.button2:
               // weatherRefresh.setText("同步中……");
                String countyName = sharedPreferences.getString("CountyName", "");
                if (!TextUtils.isEmpty(countyName)) {
                    queryFromServer(countyName);
                }
                break;

        }
    }

    private void queryFromServer( String countyName) {
        try {
            String url = "http://apis.baidu.com/heweather/weather/free?city=";
            String name = new String(countyName.getBytes("UTF-8"), "iso-8859-1");//将数据库里的数据编码成iso-8859-1
            MyStringRequest sr = new MyStringRequest(Request.Method.POST, url + name, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("spy1", response);
                    HttpResponse.handlejson(WeatherShow.this, response);
                    showWeather();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(WeatherShow.this, "同步失败", Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> map = new HashMap<>();
                    map.put("apikey","af73d6d0c9c337d6ab3b652560cf7dd5");
                    //map.put("Accept-Encoding", "gzip,deflate");
                    return map;
                }
            };
            MySingleton.getInstance(getApplication()).addToRequestQueue(sr);
            //http header头要求其内容必须为iso8859-1编码
           /* HttpRequest.sendHttpRequest(url + name, new HttpRequestCallback() {
                @Override
                public void onFinish(String response) {
                    HttpResponse.handlejson(WeatherShow.this, response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeather();
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WeatherShow.this, "同步失败", Toast.LENGTH_LONG).show();
                           // weatherRefresh.setText("更新数据");
                        }
                    });
                }
            });*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showWeather() {
        cityName.setText(sharedPreferences.getString("cityName", "未知"));
        sunriseTime.setText("日出："+sharedPreferences.getString("sunriseTime", "未知"));
        sunsetTime.setText("日落："+sharedPreferences.getString("sunsetTime", "未知"));
        DayWeather.setText("日："+sharedPreferences.getString("dayWeather", "未知"));
        Nightweather.setText("夜："+sharedPreferences.getString("nightWeather", "未知"));
        temp.setText(sharedPreferences.getString("temp", "未知"));
        wind.setText("风力："+sharedPreferences.getString("wind", "未知"));
        pop.setText("降水概率："+sharedPreferences.getString("pop", "未知")+"%");
        updateTime.setText("更新时间："+sharedPreferences.getString("updateTime", "未知"));
        AdapterE adapter = new AdapterE(R.layout.hour,this, weatherlist);
        listview.setAdapter(adapter);
        Toast.makeText(WeatherShow.this, "已经是最新数据了", Toast.LENGTH_SHORT).show();
        //weatherRefresh.setText("更新数据");
    }

}

/*
        cityName.setText(sharedPreferences.getString("cityName", "未知"));
        sunriseTime.setText(sharedPreferences.getString("sunriseTime", "未知"));
        sunsetTime.setText(sharedPreferences.getString("sunsetTime", "未知"));
        DayWeather.setText(sharedPreferences.getString("dayWeather", "未知"));
        Nightweather.setText(sharedPreferences.getString("nightWeather", "未知"));
        temp.setText(sharedPreferences.getString("temp", "未知"));
        wind.setText(sharedPreferences.getString("wind", "未知"));
        pop.setText(sharedPreferences.getString("pop", "未知"));
        updateTime.setText(sharedPreferences.getString("updateTime", "未知"));
       //  AdapterE adapter = new AdapterE(R.layout.hour,this, weatherlist);
       // listview.setAdapter(adapter);
        Toast.makeText(WeatherShow.this, "已经是最新数据了", Toast.LENGTH_SHORT).show();
        weatherRefresh.setText("更新数据");
 */
