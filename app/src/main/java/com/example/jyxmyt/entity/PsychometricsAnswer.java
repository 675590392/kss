package com.example.jyxmyt.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public class PsychometricsAnswer {
    /**
     * kssPrisonerNum : 31000011120170004
     * kssRoomNum : 0101
     * kssPrisonerName : 庄剑
     * data : [{"answer":"2_A"},{"answer":"3_A"},{"answer":"4_C"}]
     */

    private String kssPrisonerNum;
    private String kssRoomNum;
    private String kssPrisonerName;
    private List<DataBean> data;

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

    public String getKssPrisonerName() {
        return kssPrisonerName;
    }

    public void setKssPrisonerName(String kssPrisonerName) {
        this.kssPrisonerName = kssPrisonerName;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * answer : 2_A
         */

        private String answer;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
