package com.example.ning.mycoolweather.activity;

import android.content.Context;
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

import com.example.ning.mycoolweather.Model.City;
import com.example.ning.mycoolweather.Model.County;
import com.example.ning.mycoolweather.Model.Province;
import com.example.ning.mycoolweather.R;
import com.example.ning.mycoolweather.db.Dprocess;
import com.example.ning.mycoolweather.util.HttpRequest;
import com.example.ning.mycoolweather.util.HttpRequestCallback;
import com.example.ning.mycoolweather.util.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static  String TAG="kajibu";
    String httpUrl = "http://www.weather.com.cn/data/list3/city2808.xml";

    Dprocess DatabaseWeather;
    private List<Province> provinceList;

    private int levelProvice = 0;
    private int levelCity = 1;
    private int levelCounty = 2;

    private int CurrentLevel;
    private List<City> cityList;

    private List<County> countyList;
    private  List<String> datalist;
    ListView mListView;
    TextView mTextView;
    List<String> mList;
    ArrayAdapter<String> ap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        SharedPreferences pref =getSharedPreferences("Weather", Context.MODE_PRIVATE);

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
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });

        QueryProvince();

    }

    private void QueryProvince() {
      provinceList = DatabaseWeather.getallProvices();
      if(provinceList.size() > 0){
          datalist.clear();
          for (Province p: provinceList) {
              datalist.add(p.getProvinceName());
          }

          ap.notifyDataSetChanged();
         // mListView.setSelection(0);
          mTextView.setText("中国");


      }
        else
          QueryFromServer(null,"province");
    }

    private void QueryFromServer(final String code ,final String type) {
        String address;
        // code不为空
        if (!TextUtils.isEmpty(code)) {
            address = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
        } else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }

        HttpRequest.sendHttpRequest(address, new HttpRequestCallback() {
            @Override
            public void onFinish(String response) {

                boolean result = false;
                if ("province".equals(type))
                    result = HttpResponse.saveProvincesResponse(DatabaseWeather, response);
                if (true) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ("province".equals(type)) {
                                  QueryProvince();
                            } else if ("city".equals(type)) {
                                // queryCities();
                            } else if ("county".equals(type)) {
                                // queryCounties();
                            }
                        }
                    });

                }
            }

            @Override
            public void onError(Exception e) {

            }


        });

    }


}
