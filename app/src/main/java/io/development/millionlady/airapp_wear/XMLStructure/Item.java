package io.development.millionlady.airapp_wear.XMLStructure;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Item {
    private String title;
    private String description;
    private String param;
    private double conc;
    private double nowCastConc;
    private int aqi;
    private String desc;
    private String readingDataTime;

    public Item() {
        title = new String();
        description = new String();
        param = new String();
        conc = 0.0;
        nowCastConc = 0.0;
        aqi = 0;
        desc = new String();
        readingDataTime = new String();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public double getConc() {
        return conc;
    }

    public void setConc(double conc) {
        this.conc = conc;
    }

    public double getNowCastConc() {
        return nowCastConc;
    }

    public void setNowCastConc(double nowCastConc) {
        this.nowCastConc = nowCastConc;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReadingDataTime() {
        return readingDataTime;
    }

    public void setReadingDataTime(String readingDataTime) {
        this.readingDataTime = readingDataTime;
    }

    public String getDay() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());

        try {
            Date date = dateFormat.parse(readingDataTime);

            String s = new SimpleDateFormat("EEEE").format(date);
            return s;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
