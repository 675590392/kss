package com.example.jyxmyt.entity;

/**
 * Created by tanghao on 2017/3/7.
 */

public class SynchFinger {
    /**
     * endTimeStr :
     * fingerSynoId : 8
     * id : 4
     * kssArea : 01
     * kssCreateTime : {"date":3,"day":5,"hours":19,"minutes":3,"month":2,"nanos":0,"seconds":48,"time":1488539028000,"timezoneOffset":-480,"year":117}
     * kssFeatureNumFirst : fdfsfsdfsfsdfdsfsdfsdfsfdgdfgkfdlklgjdjl
     * kssFeatureNumSecond : fdfsfsdfsfsdfdsfsdfsdfsfdgdfgkfdlklgjdjl
     * kssInsideNum : 20170007
     * kssPrisonerName : 池松
     * kssPrisonerNum : 31000011120170007
     * kssRoomNum : 0101
     * kssState : 2
     * startTimeStr :
     * type : 2
     */

    private String endTimeStr;
    private int fingerSynoId;
    private long id;
    private String kssArea;
    private KssCreateTimeBean kssCreateTime;
    private String kssFeatureNumFirst;
    private String kssFeatureNumSecond;
    private String kssInsideNum;
    private String kssPrisonerName;
    private String kssPrisonerNum;
    private String kssRoomNum;
    private String kssState;
    private String startTimeStr;
    private int type;//数据类型，1表示添加，2表示修改，3表示删除

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public int getFingerSynoId() {
        return fingerSynoId;
    }

    public void setFingerSynoId(int fingerSynoId) {
        this.fingerSynoId = fingerSynoId;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKssArea() {
        return kssArea;
    }

    public void setKssArea(String kssArea) {
        this.kssArea = kssArea;
    }

    public KssCreateTimeBean getKssCreateTime() {
        return kssCreateTime;
    }

    public void setKssCreateTime(KssCreateTimeBean kssCreateTime) {
        this.kssCreateTime = kssCreateTime;
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

    public String getKssInsideNum() {
        return kssInsideNum;
    }

    public void setKssInsideNum(String kssInsideNum) {
        this.kssInsideNum = kssInsideNum;
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

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class KssCreateTimeBean {
        /**
         * date : 3
         * day : 5
         * hours : 19
         * minutes : 3
         * month : 2
         * nanos : 0
         * seconds : 48
         * time : 1488539028000
         * timezoneOffset : -480
         * year : 117
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
