package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/4/27.
 * 通过房间号查询留言信息
 */

//"content":" DFDFDF",//内容
//createTime":{//时间 "
//""date":26,
//"day":3,
// "hours":10,
// "minutes":6,
// "month":3,
//"nanos":52000000,
//"seconds":29,
//"time":1493172389052,
//"timezoneOffset":-480,
//"year":117
//
//"id":46,//id
//// inputMan":"admin",//录入人
// "loadState":0,//接收状态0表示未接收，1表示接收
//  "roomNums":"0201",//房间号
//  "state":2//1表示草稿、2表示发布

public class MessageInformation {
    /**
     * content :  DFDFDF
     * createTime : {"date":26,"day":3,"hours":10,"minutes":6,"month":3,"nanos":52000000,"seconds":29,"time":1493172389052,"timezoneOffset":-480,"year":117}
     * id : 48
     * inputMan : admin
     * loadState : 0
     * roomNums : 0203
     * state : 2
     */

    private String content;
    private CreateTimeBean createTime;
    private int id;
    private String inputMan;
    private int loadState;
    private String roomNums;
    private int state;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getInputMan() {
        return inputMan;
    }

    public void setInputMan(String inputMan) {
        this.inputMan = inputMan;
    }

    public int getLoadState() {
        return loadState;
    }

    public void setLoadState(int loadState) {
        this.loadState = loadState;
    }

    public String getRoomNums() {
        return roomNums;
    }

    public void setRoomNums(String roomNums) {
        this.roomNums = roomNums;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class CreateTimeBean {
        /**
         * date : 26
         * day : 3
         * hours : 10
         * minutes : 6
         * month : 3
         * nanos : 52000000
         * seconds : 29
         * time : 1493172389052
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
