package com.example.jyxmyt.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */

public class PublicIpBunkInfo {

    /**
     * surelyName :
     * assistName :
     * row : [{"endTimeStr":"","fingerSynoId":0,"id":1583,"kssArea":"01","kssBedNum":3,"kssBench":"0","kssDate":{"date":13,"day":1,"hours":0,"minutes":0,"month":2,"nanos":0,"seconds":0,"time":1489334400000,"timezoneOffset":-480,"year":117},"kssGlass":"0","kssPrisonerName":"纪煜城","kssPrisonerNum":"31000011120170003","kssRoomNum":"0101","kssUserName":"admin","startTimeStr":"","type":0},{"endTimeStr":"","fingerSynoId":0,"id":1585,"kssArea":"01","kssBedNum":1,"kssBench":"1","kssDate":{"date":13,"day":1,"hours":13,"minutes":20,"month":2,"nanos":0,"seconds":20,"time":1489382420000,"timezoneOffset":-480,"year":117},"kssGlass":"1","kssPrisonerName":"翁润东","kssPrisonerNum":"31000011120170011","kssRoomNum":"0101","kssUserName":"admin","startTimeStr":"","type":0}]
     */

    private String surelyName;
    private String assistName;
    private List<RowBean> row;

    public String getSurelyName() {
        return surelyName;
    }

    public void setSurelyName(String surelyName) {
        this.surelyName = surelyName;
    }

    public String getAssistName() {
        return assistName;
    }

    public void setAssistName(String assistName) {
        this.assistName = assistName;
    }

    public List<RowBean> getRow() {
        return row;
    }

    public void setRow(List<RowBean> row) {
        this.row = row;
    }

    public static class RowBean {
        /**
         * endTimeStr :
         * fingerSynoId : 0
         * id : 1583
         * kssArea : 01
         * kssBedNum : 3
         * kssBench : 0
         * kssDate : {"date":13,"day":1,"hours":0,"minutes":0,"month":2,"nanos":0,"seconds":0,"time":1489334400000,"timezoneOffset":-480,"year":117}
         * kssGlass : 0
         * kssPrisonerName : 纪煜城
         * kssPrisonerNum : 31000011120170003
         * kssRoomNum : 0101
         * kssUserName : admin
         * startTimeStr :
         * type : 0
         */

        private String endTimeStr;
        private int fingerSynoId;
        private int id;
        private String kssArea;
        private int kssBedNum;
        private String kssBench;
        private KssDateBean kssDate;
        private String kssGlass;
        private String kssPrisonerName;
        private String kssPrisonerNum;
        private String kssRoomNum;
        private String kssUserName;
        private String startTimeStr;
        private int type;

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

        public int getKssBedNum() {
            return kssBedNum;
        }

        public void setKssBedNum(int kssBedNum) {
            this.kssBedNum = kssBedNum;
        }

        public String getKssBench() {
            return kssBench;
        }

        public void setKssBench(String kssBench) {
            this.kssBench = kssBench;
        }

        public KssDateBean getKssDate() {
            return kssDate;
        }

        public void setKssDate(KssDateBean kssDate) {
            this.kssDate = kssDate;
        }

        public String getKssGlass() {
            return kssGlass;
        }

        public void setKssGlass(String kssGlass) {
            this.kssGlass = kssGlass;
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

        public String getKssUserName() {
            return kssUserName;
        }

        public void setKssUserName(String kssUserName) {
            this.kssUserName = kssUserName;
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

        public static class KssDateBean {
            /**
             * date : 13
             * day : 1
             * hours : 0
             * minutes : 0
             * month : 2
             * nanos : 0
             * seconds : 0
             * time : 1489334400000
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
}
