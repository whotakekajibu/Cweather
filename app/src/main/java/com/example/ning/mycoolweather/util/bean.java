package com.example.ning.mycoolweather.util;

import java.util.List;

/**
 * Created by Ning on 2016/9/29.
 */
public class bean {
    /**
     * aqi : {"city":{"aqi":"51","co":"0","no2":"14","o3":"45","pm10":"51","pm25":"40","qlty":"优","so2":"2"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-09-29 08:51","utc":"2016-09-29 00:51"}}
     * daily_forecast : [{"astro":{"sr":"06:09","ss":"18:00"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-09-29","hum":"19","pcpn":"0.0","pop":"0","pres":"1017","tmp":{"max":"22","min":"12"},"vis":"10","wind":{"deg":"205","dir":"无持续风向","sc":"微风","spd":"9"}},{"astro":{"sr":"06:09","ss":"17:58"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-09-30","hum":"25","pcpn":"0.0","pop":"0","pres":"1013","tmp":{"max":"24","min":"14"},"vis":"10","wind":{"deg":"178","dir":"无持续风向","sc":"微风","spd":"2"}},{"astro":{"sr":"06:10","ss":"17:57"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-10-01","hum":"29","pcpn":"0.4","pop":"40","pres":"1012","tmp":{"max":"26","min":"16"},"vis":"10","wind":{"deg":"186","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"06:11","ss":"17:55"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2016-10-02","hum":"33","pcpn":"4.3","pop":"86","pres":"1012","tmp":{"max":"27","min":"17"},"vis":"10","wind":{"deg":"184","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"sr":"06:12","ss":"17:53"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2016-10-03","hum":"25","pcpn":"0.1","pop":"49","pres":"1008","tmp":{"max":"27","min":"19"},"vis":"10","wind":{"deg":"89","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"06:13","ss":"17:52"},"cond":{"code_d":"104","code_n":"305","txt_d":"阴","txt_n":"小雨"},"date":"2016-10-04","hum":"45","pcpn":"0.5","pop":"53","pres":"1006","tmp":{"max":"24","min":"16"},"vis":"10","wind":{"deg":"214","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"sr":"06:14","ss":"17:50"},"cond":{"code_d":"104","code_n":"100","txt_d":"阴","txt_n":"晴"},"date":"2016-10-05","hum":"30","pcpn":"0.0","pop":"0","pres":"1018","tmp":{"max":"25","min":"14"},"vis":"10","wind":{"deg":"174","dir":"无持续风向","sc":"微风","spd":"1"}}]
     * hourly_forecast : [{"date":"2016-09-29 10:00","hum":"20","pop":"0","pres":"1021","tmp":"21","wind":{"deg":"194","dir":"西南风","sc":"微风","spd":"9"}},{"date":"2016-09-29 13:00","hum":"19","pop":"0","pres":"1018","tmp":"23","wind":{"deg":"201","dir":"西南风","sc":"微风","spd":"15"}},{"date":"2016-09-29 16:00","hum":"25","pop":"0","pres":"1016","tmp":"24","wind":{"deg":"198","dir":"西南风","sc":"微风","spd":"12"}},{"date":"2016-09-29 19:00","hum":"35","pop":"0","pres":"1016","tmp":"21","wind":{"deg":"198","dir":"西南风","sc":"微风","spd":"8"}},{"date":"2016-09-29 22:00","hum":"42","pop":"0","pres":"1017","tmp":"16","wind":{"deg":"203","dir":"西南风","sc":"微风","spd":"4"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"7","hum":"66","pcpn":"0","pres":"1022","tmp":"13","vis":"10","wind":{"deg":"20","dir":"东南风","sc":"3-4","spd":"16"}}
     * status : ok
     * suggestion : {"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"},"flu":{"brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},"sport":{"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"},"trav":{"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    private List<HeWeatherdataservice30Bean> HeWeatherdataservice30;

    public List<HeWeatherdataservice30Bean> getHeWeatherdataservice30() {
        return HeWeatherdataservice30;
    }

    public void setHeWeatherdataservice30(List<HeWeatherdataservice30Bean> HeWeatherdataservice30) {
        this.HeWeatherdataservice30 = HeWeatherdataservice30;
    }

    public static class HeWeatherdataservice30Bean {
        /**
         * city : {"aqi":"51","co":"0","no2":"14","o3":"45","pm10":"51","pm25":"40","qlty":"优","so2":"2"}
         */
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2016-09-29 08:51","utc":"2016-09-29 00:51"}
         */
        private BasicBean basic;


        private List<DailyForecastBean> daily_forecast;
        /**
         * date : 2016-09-29 10:00
         * hum : 20
         * pop : 0
         * pres : 1021
         * tmp : 21
         * wind : {"deg":"194","dir":"西南风","sc":"微风","spd":"9"}
         */
        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }


        public static class BasicBean {
            private String city;
            /**
             * loc : 2016-09-29 08:51
             * utc : 2016-09-29 00:51
             */
            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                private String loc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }
            }
        }

        public static class DailyForecastBean {
            private AstroBean astro;
            private CondBean cond;
            private String date;
            private String pop;
            private TmpBean tmp;
            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }


            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                private String sr;
                private String ss;

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                private String txt_d;
                private String txt_n;

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                private String dir;
                private String sc;
                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

            }
        }

    }
}
