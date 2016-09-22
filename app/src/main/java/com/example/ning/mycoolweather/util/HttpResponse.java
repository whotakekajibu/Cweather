package com.example.ning.mycoolweather.util;

import android.text.TextUtils;

import com.example.ning.mycoolweather.Model.Province;
import com.example.ning.mycoolweather.db.Dprocess;

import java.util.ArrayList;
import java.util.List;

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
}
