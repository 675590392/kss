package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/3/7.
 */

public class BookingInfo {

    /**
     * kssArea : 111
     * kssPrisonerNum : 222
     * kssRoomNum : 333
     * kssPrisoner : 小夏
     * kssBookingContent : 沙发水电费的士速递
     * kssStartTime : 2017-03-11 12:12:12
     * kssType : 1
     */

    private String kssArea;
    private String kssPrisonerNum;
    private String kssRoomNum;
    private String kssPrisoner;
    private String kssBookingContent;
    private int kssType;

    public String getKssArea() {
        return kssArea;
    }

    public void setKssArea(String kssArea) {
        this.kssArea = kssArea;
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

    public String getKssPrisoner() {
        return kssPrisoner;
    }

    public void setKssPrisoner(String kssPrisoner) {
        this.kssPrisoner = kssPrisoner;
    }

    public String getKssBookingContent() {
        return kssBookingContent;
    }

    public void setKssBookingContent(String kssBookingContent) {
        this.kssBookingContent = kssBookingContent;
    }
    public int getKssType() {
        return kssType;
    }

    public void setKssType(int kssType) {
        this.kssType = kssType;
    }
}
