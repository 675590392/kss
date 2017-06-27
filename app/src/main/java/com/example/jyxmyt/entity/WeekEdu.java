package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/3/3.
 */

public class WeekEdu {

    /**
     * createTime : {"date":24,"day":5,"hours":9,"minutes":32,"month":1,"nanos":362000000,"seconds":18,"time":1487899938362,"timezoneOffset":-480,"year":117}
     * id : 158
     * state : 0
     * timesEight : 做事
     * timesEleven : 做事
     * timesFive : 做事
     * timesFour : 做事
     * timesNine : 做事
     * timesOne : 做事
     * timesSeven : 做事
     * timesSix : 做事
     * timesTen : 做事
     * timesThree : 做事
     * timesTwo : 做事
     * weekDay : 星期1
     */

    private CreateTimeBean createTime;
    private int id;
    private int state;
    private String timesEight;
    private String timesEleven;
    private String timesFive;
    private String timesFour;
    private String timesNine;
    private String timesOne;
    private String timesSeven;
    private String timesSix;
    private String timesTen;
    private String timesThree;
    private String timesTwo;
    private String weekDay;

    public CreateTimeBean getCreateTime() {
        return createTime;
    }

    public void setCreateTime(CreateTimeBean createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTimesEight() {
        return timesEight;
    }

    public void setTimesEight(String timesEight) {
        this.timesEight = timesEight;
    }

    public String getTimesEleven() {
        return timesEleven;
    }

    public void setTimesEleven(String timesEleven) {
        this.timesEleven = timesEleven;
    }

    public String getTimesFive() {
        return timesFive;
    }

    public void setTimesFive(String timesFive) {
        this.timesFive = timesFive;
    }

    public String getTimesFour() {
        return timesFour;
    }

    public void setTimesFour(String timesFour) {
        this.timesFour = timesFour;
    }

    public String getTimesNine() {
        return timesNine;
    }

    public void setTimesNine(String timesNine) {
        this.timesNine = timesNine;
    }

    public String getTimesOne() {
        return timesOne;
    }

    public void setTimesOne(String timesOne) {
        this.timesOne = timesOne;
    }

    public String getTimesSeven() {
        return timesSeven;
    }

    public void setTimesSeven(String timesSeven) {
        this.timesSeven = timesSeven;
    }

    public String getTimesSix() {
        return timesSix;
    }

    public void setTimesSix(String timesSix) {
        this.timesSix = timesSix;
    }

    public String getTimesTen() {
        return timesTen;
    }

    public void setTimesTen(String timesTen) {
        this.timesTen = timesTen;
    }

    public String getTimesThree() {
        return timesThree;
    }

    public void setTimesThree(String timesThree) {
        this.timesThree = timesThree;
    }

    public String getTimesTwo() {
        return timesTwo;
    }

    public void setTimesTwo(String timesTwo) {
        this.timesTwo = timesTwo;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public static class CreateTimeBean {
        /**
         * date : 24
         * day : 5
         * hours : 9
         * minutes : 32
         * month : 1
         * nanos : 362000000
         * seconds : 18
         * time : 1487899938362
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
