package com.example.ning.mycoolweather.util;

/**
 * Created by Ning on 2016/9/22.
 */
public interface HttpRequestCallback {

    void onFinish(String response);

    void onError(Exception e);
}
