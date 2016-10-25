package com.example.ning.mycoolweather.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ning.mycoolweather.Model.City;
import com.example.ning.mycoolweather.Model.County;
import com.example.ning.mycoolweather.Model.Province;
import com.example.ning.mycoolweather.R;
import com.example.ning.mycoolweather.db.Dprocess;
import com.example.ning.mycoolweather.util.HttpRequest;
import com.example.ning.mycoolweather.util.HttpRequestCallback;
import com.example.ning.mycoolweather.util.HttpResponse;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static  String TAG="kajibu";
    private Province  Slectedp;
    private City Slectedc;

    private Dprocess DatabaseWeather;


    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;

    private int levelProvice = 0;
    private int levelCity = 1;
    private int levelCounty = 2;
    private int CurrentLevel;



    ListView mListView;
    TextView mTextView;
    List<String> mList;
    ArrayAdapter<String> ap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SharedPreferences pref =getSharedPreferences("Weather", Context.MODE_PRIVATE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        DatabaseWeather = Dprocess.getinstance(this);
        mList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.listView);
        ap  = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,mList);
        mListView.setAdapter(ap);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(CurrentLevel == levelProvice){
                    Slectedp = provinceList.get(i);
                    QueryCities();
                }
               else if(CurrentLevel == levelCity){
                    Slectedc = cityList.get(i);
                    QueryCounties();
                }
               else if(CurrentLevel == levelCounty){
                    String countyName = countyList.get(i).getCountyName();
                    Intent intent = new Intent(MainActivity.this, WeatherShow.class);
                    intent.putExtra("CountyName", countyName);
                    startActivity(intent);
                    finish();
                }
            }
        });
        QueryProvince();
    }

    private void QueryCities() {
       cityList = DatabaseWeather.getallCities(Slectedp.getProvinceCode());
        if (cityList.size() > 0) {
            mList.clear();
            for (City c : cityList) {
                mList.add(c.getCityName());
            }
            ap.notifyDataSetChanged();
           // mListView.setSelection(0);
            mTextView.setText(Slectedp.getProvinceName());
            CurrentLevel = levelCity;

        } else {
            QueryFromServer(Slectedp.getProvinceCode(), "city");
        }

    }

    private void QueryCounties() {
        countyList = DatabaseWeather.getallCounties(Slectedc.getCityCode());
        if (countyList.size() > 0) {
            mList.clear();
            for (County county : countyList) {
                mList.add(county.getCountyName());
            }
            ap.notifyDataSetChanged();//此处刷新加载listview
           // mListView.setSelection(0);
            mTextView.setText(Slectedc.getCityName());
            CurrentLevel = levelCounty;

        } else {
            QueryFromServer(Slectedc.getCityCode(), "county");
        }
    }

    private void QueryProvince() {
      provinceList = DatabaseWeather.getallProvices();
      if(provinceList.size() > 0){
          mList.clear();
          for (Province p: provinceList) {
              mList.add(p.getProvinceName());
          }
          ap.notifyDataSetChanged();
         // mListView.setSelection(0);
          mTextView.setText("中国");
          CurrentLevel = levelProvice;
      }
        else
          QueryFromServer(null,"province");
    }
    private void QueryFromServer(final String code ,final String type) {

        String address;
        if (!TextUtils.isEmpty(code)) {
            address = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
        } else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, address, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    response = new String(response.getBytes("iso-8859-1"),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                boolean result = false;
                if ("province".equals(type))
                     result = HttpResponse.saveProvincesResponse(DatabaseWeather, response);
                else if("city".equals(type))
                     result = HttpResponse.saveCitiesResponse(DatabaseWeather,response,Slectedp.getProvinceCode());
                else if("county".equals(type))
                     result = HttpResponse.saveCountiesResponse(DatabaseWeather,response,Slectedc.getCityCode());
                if(result){
                    if ("province".equals(type)) {
                        QueryProvince();
                    } else if ("city".equals(type)) {
                        QueryCities();
                    } else if ("county".equals(type)) {
                        QueryCounties();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("请求城市数据时出错！");
                //Toast.makeText(MainActivity.this,"请求城市数据时出错！",Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(getApplication()).addToRequestQueue(stringRequest);
        /*HttpRequest.sendHttpRequest(address, new HttpRequestCallback() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                if ("province".equals(type))
                    result = HttpResponse.saveProvincesResponse(DatabaseWeather, response);
                else if("city".equals(type))
                    result = HttpResponse.saveCitiesResponse(DatabaseWeather,response,Slectedp.getProvinceCode());
                else if("county".equals(type))
                    result = HttpResponse.saveCountiesResponse(DatabaseWeather,response,Slectedc.getCityCode());
                if (result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ("province".equals(type)) {
                                  QueryProvince();
                            } else if ("city".equals(type)) {
                                QueryCities();
                            } else if ("county".equals(type)) {
                                QueryCounties();
                            }
                        }
                    });

                }
            }
            @Override
            public void onError(Exception e) {
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        if (CurrentLevel == levelCounty) {
            QueryCities();
        } else if (CurrentLevel == levelCity) {
            QueryProvince();
        }
    }
}
