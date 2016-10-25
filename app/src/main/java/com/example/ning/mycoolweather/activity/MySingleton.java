package com.example.ning.mycoolweather.activity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ning on 2016/9/29.
 */
public class MySingleton {

    private static MySingleton mInstance;

    private RequestQueue mRequestQueue;
    private static Context mCt;
    public  MySingleton(Context context) {
        mCt = context;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCt.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized MySingleton getInstance(Context ct) {
        if (mInstance == null) {
            mInstance = new MySingleton(ct);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
