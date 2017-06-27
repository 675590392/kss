package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/3/8.
 */

public class PublicQueryUserDuty {

    /**
     * id : 1
     * kssDate : 2017-03-09
     * kssDutyContent : 扫地
     * kssDutyEndTime : 14:10
     * kssDutyStartTimer : 02:00
     * kssPrisoner :
     * kssPrisonerName : 詹吉
     * kssPrisonerNum : 31000011120170001
     * kssRoomNum : 0101
     * kssWeek : 四
     */

    private int id;
    private String kssDate;
    private String kssDutyContent;
    private String kssDutyEndTime;
    private String kssDutyStartTimer;
    private String kssPrisoner;
    private String kssPrisonerName;
    private String kssPrisonerNum;
    private String kssRoomNum;
    private String kssWeek;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKssDate() {
        return kssDate;
    }

    public void setKssDate(String kssDate) {
        this.kssDate = kssDate;
    }

    public String getKssDutyContent() {
        return kssDutyContent;
    }

    public void setKssDutyContent(String kssDutyContent) {
        this.kssDutyContent = kssDutyContent;
    }

    public String getKssDutyEndTime() {
        return kssDutyEndTime;
    }

    public void setKssDutyEndTime(String kssDutyEndTime) {
        this.kssDutyEndTime = kssDutyEndTime;
    }

    public String getKssDutyStartTimer() {
        return kssDutyStartTimer;
    }

    public void setKssDutyStartTimer(String kssDutyStartTimer) {
        this.kssDutyStartTimer = kssDutyStartTimer;
    }

    public String getKssPrisoner() {
        return kssPrisoner;
    }

    public void setKssPrisoner(String kssPrisoner) {
        this.kssPrisoner = kssPrisoner;
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

    public String getKssWeek() {
        return kssWeek;
    }

    public void setKssWeek(String kssWeek) {
        this.kssWeek = kssWeek;
    }
}
