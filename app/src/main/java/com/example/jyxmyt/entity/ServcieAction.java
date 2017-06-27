package com.example.jyxmyt.entity;

import java.util.Date;

import static com.example.jyxmyt.R.id.date;

/**
 * Created by Administrator on 2017/3/7.
 */

public class ServcieAction {

    /**
     * kssPrisonerNum : 31000011120170005
     * kssArea : 01
     * kssRoomNum : 0101
     * kssPrisoner : 小兵
     * kssProductName : 单衣
     * kssProductNum : 12
     * kssProductUnit : 件
     * kssHelpTime : {"date":6,"day":1,"hours":16,"minutes":35,"month":2,"nanos":293000000,"seconds":48,"time":1488789348293,"timezoneOffset":-480,"year":117}
     * kssName : 李某
     * kssPhone : 13724568987
     */

    private String kssPrisonerNum;
    private String kssArea;
    private String kssRoomNum;
    private String kssPrisoner;
    private String kssProductName;
    private int kssProductNum;
    private String kssProductUnit;
    private Date kssHelpTime;
    private String kssName;
    private String kssPhone;

    public ServcieAction() {
    }

    public ServcieAction(String kssArea, Date kssHelpTime, String kssName, String kssPhone, String kssPrisoner, String kssPrisonerNum, String kssProductName, int kssProductNum, String kssProductUnit, String kssRoomNum) {
        this.kssArea = kssArea;
        this.kssHelpTime = kssHelpTime;
        this.kssName = kssName;
        this.kssPhone = kssPhone;
        this.kssPrisoner = kssPrisoner;
        this.kssPrisonerNum = kssPrisonerNum;
        this.kssProductName = kssProductName;
        this.kssProductNum = kssProductNum;
        this.kssProductUnit = kssProductUnit;
        this.kssRoomNum = kssRoomNum;
    }

    public String getKssPrisonerNum() {
        return kssPrisonerNum;
    }

    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
    }

    public String getKssArea() {
        return kssArea;
    }

    public void setKssArea(String kssArea) {
        this.kssArea = kssArea;
    }

    public String getKssRoomNum() {
        return kssRoomNum;
    }

    public void setKssRoomNum(String kssRoomNum) {
        this.kssRoomNum = kssRoomNum;
    }

    public String getKssPrisoner() {
        return kssPrisoner;
    }

    public void setKssPrisoner(String kssPrisoner) {
        this.kssPrisoner = kssPrisoner;
    }

    public String getKssProductName() {
        return kssProductName;
    }

    public void setKssProductName(String kssProductName) {
        this.kssProductName = kssProductName;
    }

    public int getKssProductNum() {
        return kssProductNum;
    }

    public void setKssProductNum(int kssProductNum) {
        this.kssProductNum = kssProductNum;
    }

    public String getKssProductUnit() {
        return kssProductUnit;
    }

    public void setKssProductUnit(String kssProductUnit) {
        this.kssProductUnit = kssProductUnit;
    }

    public Date getKssHelpTime() {
        return kssHelpTime;
    }

    public void setKssHelpTime(Date kssHelpTime) {
        this.kssHelpTime = kssHelpTime;
    }

    public String getKssName() {
        return kssName;
    }

    public void setKssName(String kssName) {
        this.kssName = kssName;
    }

    public String getKssPhone() {
        return kssPhone;
    }

    public void setKssPhone(String kssPhone) {
        this.kssPhone = kssPhone;
    }

}
