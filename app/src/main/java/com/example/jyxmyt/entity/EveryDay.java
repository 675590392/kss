package com.example.jyxmyt.entity;

/**
 * 一日作息
 * Created by Administrator on 2017/3/2.
 */

public class EveryDay {


    /**
     * id : 236
     * kssContentFisrt : e
     * kssContentSecond : s
     * kssCreateTime : {"date":1,"day":3,"hours":17,"minutes":13,"month":2,"nanos":150000000,"seconds":24,"time":1488359604150,"timezoneOffset":-480,"year":117}
     * kssEndtime : 23:59
     * kssHolidayState : 1
     * kssSeasonState : 1
     * kssStarttime : 00:00
     */

    private int id;
    private String kssContentFisrt;
    private String kssContentSecond;
    private KssCreateTimeBean kssCreateTime;
    private String kssEndtime;
    private String kssHolidayState;
    private String kssSeasonState;
    private String kssStarttime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKssContentFisrt() {
        return kssContentFisrt;
    }

    public void setKssContentFisrt(String kssContentFisrt) {
        this.kssContentFisrt = kssContentFisrt;
    }

    public String getKssContentSecond() {
        return kssContentSecond;
    }

    public void setKssContentSecond(String kssContentSecond) {
        this.kssContentSecond = kssContentSecond;
    }

    public KssCreateTimeBean getKssCreateTime() {
        return kssCreateTime;
    }

    public void setKssCreateTime(KssCreateTimeBean kssCreateTime) {
        this.kssCreateTime = kssCreateTime;
    }

    public String getKssEndtime() {
        return kssEndtime;
    }

    public void setKssEndtime(String kssEndtime) {
        this.kssEndtime = kssEndtime;
    }

    public String getKssHolidayState() {
        return kssHolidayState;
    }

    public void setKssHolidayState(String kssHolidayState) {
        this.kssHolidayState = kssHolidayState;
    }

    public String getKssSeasonState() {
        return kssSeasonState;
    }

    public void setKssSeasonState(String kssSeasonState) {
        this.kssSeasonState = kssSeasonState;
    }

    public String getKssStarttime() {
        return kssStarttime;
    }

    public void setKssStarttime(String kssStarttime) {
        this.kssStarttime = kssStarttime;
    }

    public static class KssCreateTimeBean {
        /**
         * date : 1
         * day : 3
         * hours : 17
         * minutes : 13
         * month : 2
         * nanos : 150000000
         * seconds : 24
         * time : 1488359604150
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
