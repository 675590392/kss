package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/3/6.
 */

public class IdBooking {
    /**
     * historyState : 2
     * id : 499
     * kssArea : 111
     * kssBookingContent : 沙发水电费的士速递
     * kssEndTime : {"date":2,"day":4,"hours":14,"minutes":19,"month":2,"nanos":0,"seconds":55,"time":1488435595000,"timezoneOffset":-480,"year":117}
     * kssPrisoner : 小夏
     * kssPrisonerNum : 222
     * kssResponses : <p>asfdasdsad</p>
     * kssRoomNum : 333
     * kssStartTime : {"date":11,"day":6,"hours":12,"minutes":12,"month":2,"nanos":0,"seconds":12,"time":1489205532000,"timezoneOffset":-480,"year":117}
     * kssState : 1
     * kssType : 1
     * kssUserName : admin
     */

    private int historyState;
    private int id;
    private String kssArea;
    private String kssBookingContent;
    private KssEndTimeBean kssEndTime;
    private String kssPrisoner;
    private String kssPrisonerNum;
    private String kssResponses;
    private String kssRoomNum;
    private KssStartTimeBean kssStartTime;
    private String kssState;
    private int kssType;
    private String kssUserName;

    public int getHistoryState() {
        return historyState;
    }

    public void setHistoryState(int historyState) {
        this.historyState = historyState;
    }

    public int getId() {
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

    public String getKssBookingContent() {
        return kssBookingContent;
    }

    public void setKssBookingContent(String kssBookingContent) {
        this.kssBookingContent = kssBookingContent;
    }

    public KssEndTimeBean getKssEndTime() {
        return kssEndTime;
    }

    public void setKssEndTime(KssEndTimeBean kssEndTime) {
        this.kssEndTime = kssEndTime;
    }

    public String getKssPrisoner() {
        return kssPrisoner;
    }

    public void setKssPrisoner(String kssPrisoner) {
        this.kssPrisoner = kssPrisoner;
    }

    public String getKssPrisonerNum() {
        return kssPrisonerNum;
    }

    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
    }

    public String getKssResponses() {
        return kssResponses;
    }

    public void setKssResponses(String kssResponses) {
        this.kssResponses = kssResponses;
    }

    public String getKssRoomNum() {
        return kssRoomNum;
    }

    public void setKssRoomNum(String kssRoomNum) {
        this.kssRoomNum = kssRoomNum;
    }

    public KssStartTimeBean getKssStartTime() {
        return kssStartTime;
    }

    public void setKssStartTime(KssStartTimeBean kssStartTime) {
        this.kssStartTime = kssStartTime;
    }

    public String getKssState() {
        return kssState;
    }

    public void setKssState(String kssState) {
        this.kssState = kssState;
    }

    public int getKssType() {
        return kssType;
    }

    public void setKssType(int kssType) {
        this.kssType = kssType;
    }

    public String getKssUserName() {
        return kssUserName;
    }

    public void setKssUserName(String kssUserName) {
        this.kssUserName = kssUserName;
    }

    public static class KssEndTimeBean {
        /**
         * date : 2
         * day : 4
         * hours : 14
         * minutes : 19
         * month : 2
         * nanos : 0
         * seconds : 55
         * time : 1488435595000
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

    public static class KssStartTimeBean {
        /**
         * date : 11
         * day : 6
         * hours : 12
         * minutes : 12
         * month : 2
         * nanos : 0
         * seconds : 12
         * time : 1489205532000
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
