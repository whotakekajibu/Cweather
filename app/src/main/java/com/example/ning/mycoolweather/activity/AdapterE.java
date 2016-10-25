package com.example.ning.mycoolweather.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ning.mycoolweather.Model.Hourly;
import com.example.ning.mycoolweather.R;

import java.util.List;

/**
 * Created by Ning on 2016/9/27.
 */
public class AdapterE extends ArrayAdapter<Hourly> {
    private  int id;
    private Context ct;
    public AdapterE(int id, Context ct, List<Hourly> ob){
        super(ct,id,ob);
        this.id = id;
        this.ct = ct;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hourly weather = getItem(position);
        View view = LayoutInflater.from(ct).inflate(id, null);

        TextView forecastTime = (TextView) view.findViewById(R.id.textView14);
        TextView forecastTemp = (TextView) view.findViewById(R.id.textView19);
        TextView forecastPop = (TextView) view.findViewById(R.id.textView18);
        TextView forecastWind = (TextView) view.findViewById(R.id.textView22);

        forecastTime.setText(weather.getTime());
        forecastTemp.setText(weather.getTmp()+"℃");
        forecastPop.setText(weather.getRain());
        forecastWind.setText(weather.getWind());
        return view;
    }
}
