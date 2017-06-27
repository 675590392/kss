package com.example.jyxmyt.entity;

import java.io.Serializable;

/**
 * Created by tanghao on 2017/3/4.
 */

public class Finger implements Serializable {
    /**
     * kssArea : 01
     * kssFeatureNumFirst : 1
     * kssFeatureNumSecond : fdfsfsdfsfsdfdsfsdfsdfsfdgdfgkfdlklgjdjl
     * kssPrisonerName : 付德山
     * kssPrisonerNum : 31000011120170008
     * kssRoomNum : 0101
     * kssState : 0
     */

    private String kssArea;
    private String kssFeatureNumFirst;
    private String kssFeatureNumSecond;
    private String kssPrisonerName;
    private String kssPrisonerNum;
    private String kssRoomNum;
    private String kssState;

    public String getKssArea() {
        return kssArea;
    }

    public void setKssArea(String kssArea) {
        this.kssArea = kssArea;
    }

    public String getKssFeatureNumFirst() {
        return kssFeatureNumFirst;
    }

    public void setKssFeatureNumFirst(String kssFeatureNumFirst) {
        this.kssFeatureNumFirst = kssFeatureNumFirst;
    }

    public String getKssFeatureNumSecond() {
        return kssFeatureNumSecond;
    }

    public void setKssFeatureNumSecond(String kssFeatureNumSecond) {
        this.kssFeatureNumSecond = kssFeatureNumSecond;
    }

    public String getKssPrisonerName() {
        return kssPrisonerName;
    }

    public void setKssPrisonerName(String kssPrisonerName) {
        this.kssPrisonerName = kssPrisonerName;
    }

    public String getKssPrisonerNum() {
        return kssPrisonerNum;
    }

    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
    }

    public String getKssRoomNum() {
        return kssRoomNum;
    }

    public void setKssRoomNum(String kssRoomNum) {
        this.kssRoomNum = kssRoomNum;
    }

    public String getKssState() {
        return kssState;
    }

    public void setKssState(String kssState) {
        this.kssState = kssState;
    }
}
