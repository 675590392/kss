package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/3/4.
 */

public class WeekRecipes {


    /**
     * id : 12
     * kssBreakfast : 炝冬笋、玉兰片
     * kssDayTime : 2017-01-03
     * kssDinner : 黄花鱼、扒海参
     * kssLunch : 蛤蟆鱼、扒带鱼、海鲫鱼
     * kssRemarks :
     * kssWeekTime : 周三
     */

    private int id;
    private String kssBreakfast;
    private String kssDayTime;
    private String kssDinner;
    private String kssLunch;
    private String kssRemarks;
    private String kssWeekTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKssBreakfast() {
        return kssBreakfast;
    }

    public void setKssBreakfast(String kssBreakfast) {
        this.kssBreakfast = kssBreakfast;
    }

    public String getKssDayTime() {
        return kssDayTime;
    }

    public void setKssDayTime(String kssDayTime) {
        this.kssDayTime = kssDayTime;
    }

    public String getKssDinner() {
        return kssDinner;
    }

    public void setKssDinner(String kssDinner) {
        this.kssDinner = kssDinner;
    }

    public String getKssLunch() {
        return kssLunch;
    }

    public void setKssLunch(String kssLunch) {
        this.kssLunch = kssLunch;
    }

    public String getKssRemarks() {
        return kssRemarks;
    }

    public void setKssRemarks(String kssRemarks) {
        this.kssRemarks = kssRemarks;
    }

    public String getKssWeekTime() {
        return kssWeekTime;
    }

    public void setKssWeekTime(String kssWeekTime) {
        this.kssWeekTime = kssWeekTime;
    }
}
