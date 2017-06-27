package com.example.jyxmyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

public class MedicalInfo implements Serializable {

    /**
     * kssPrisonerNum : 31000011120170002
     * kssPrisonerName : 司朝宇
     * rows : [{"id":23,"kssDiagnosisDate":"25-十月-2016","kssPrisonerName":"","kssPrisonerNum":"","kssUpdateTime":null,"kssYs":"黄丹","kssZd":"甲硝唑片 0.8g st 口服  复方对乙酰氨基酚片 1片 st 口","kssZs":"牙痛","state":0}]
     */

    private String kssPrisonerNum;
    private String kssPrisonerName;
    private List<RowsBean> rows;

    public String getKssPrisonerNum() {
        return kssPrisonerNum;
    }

    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
    }

    public String getKssPrisonerName() {
        return kssPrisonerName;
    }

    public void setKssPrisonerName(String kssPrisonerName) {
        this.kssPrisonerName = kssPrisonerName;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 23
         * kssDiagnosisDate : 25-十月-2016
         * kssPrisonerName :
         * kssPrisonerNum :
         * kssUpdateTime : null
         * kssYs : 黄丹
         * kssZd : 甲硝唑片 0.8g st 口服  复方对乙酰氨基酚片 1片 st 口
         * kssZs : 牙痛
         * state : 0
         */

        private int id;
        private String kssDiagnosisDate;
        private String kssPrisonerName;
        private String kssPrisonerNum;
        private Object kssUpdateTime;
        private String kssYs;
        private String kssZd;
        private String kssZs;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKssDiagnosisDate() {
            return kssDiagnosisDate;
        }

        public void setKssDiagnosisDate(String kssDiagnosisDate) {
            this.kssDiagnosisDate = kssDiagnosisDate;
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

        public Object getKssUpdateTime() {
            return kssUpdateTime;
        }

        public void setKssUpdateTime(Object kssUpdateTime) {
            this.kssUpdateTime = kssUpdateTime;
        }

        public String getKssYs() {
            return kssYs;
        }

        public void setKssYs(String kssYs) {
            this.kssYs = kssYs;
        }

        public String getKssZd() {
            return kssZd;
        }

        public void setKssZd(String kssZd) {
            this.kssZd = kssZd;
        }

        public String getKssZs() {
            return kssZs;
        }

        public void setKssZs(String kssZs) {
            this.kssZs = kssZs;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
