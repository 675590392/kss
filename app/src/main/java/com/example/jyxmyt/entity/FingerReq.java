package com.example.jyxmyt.entity;

/**
 * Created by tanghao on 2017/3/6.
 */

public class FingerReq {
    /**
     * kssPrisonerNum : 31000011120170006
     * kssFeatureNumFirst : fdfsfsdfsfsdfdsfsdfsdfsfdgdfgkfdlklgjdjl
     * kssFeatureNumSecond :
     */

    private String kssPrisonerNum;
    private String kssFeatureNumFirst;
    private String kssFeatureNumSecond;

    public String getKssPrisonerNum() {
        return kssPrisonerNum;
    }

    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
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
}
