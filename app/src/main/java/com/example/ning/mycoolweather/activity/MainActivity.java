package com.example.ning.mycoolweather.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ning.mycoolweather.R;

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

    ListView mListView;
    TextView mTextView;
    List<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText("中国");
        mList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> ap = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,mList);
        mListView.setAdapter(ap);


    }


}
