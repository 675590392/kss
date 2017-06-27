package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/4/27.
 *  通过编号查询心理心理测评
 */

// "id":22,
//     "isMultiselect":1,//0表示单选，1表示多选
//      kssAnswerA":"4",//选项A
//     "kssAnswerB":"4",
//      "kssAnswerC":"4",
//      "kssAnswerD":"4",
//      "kssAnswerE":"4",
//      "kssContext":"4",//标题内容
//      "kssFraction":4,
//      "kssRightAnswer":"B,C",
//      "paperNum":"1493105512858"//试卷编号
public  class MeasurementofHeart {

    private  int id;
    private  int isMultiselect;
    private String kssAnswerA;
    private String kssAnswerB;
    private String kssAnswerC;
    private String kssAnswerD;
    private String kssAnswerE;

    private String kssContext;
    private String kssFraction;
    private String kssRightAnswer;
    private String paperNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsMultiselect() {
        return isMultiselect;
    }

    public void setIsMultiselect(int isMultiselect) {
        this.isMultiselect = isMultiselect;
    }

    public String getKssAnswerA() {
        return kssAnswerA;
    }

    public void setKssAnswerA(String kssAnswerA) {
        this.kssAnswerA = kssAnswerA;
    }

    public String getKssAnswerB() {
        return kssAnswerB;
    }

    public void setKssAnswerB(String kssAnswerB) {
        this.kssAnswerB = kssAnswerB;
    }

    public String getKssAnswerC() {
        return kssAnswerC;
    }

    public void setKssAnswerC(String kssAnswerC) {
        this.kssAnswerC = kssAnswerC;
    }

    public String getKssAnswerD() {
        return kssAnswerD;
    }

    public void setKssAnswerD(String kssAnswerD) {
        this.kssAnswerD = kssAnswerD;
    }

    public String getKssAnswerE() {
        return kssAnswerE;
    }

    public void setKssAnswerE(String kssAnswerE) {
        this.kssAnswerE = kssAnswerE;
    }

    public String getKssContext() {
        return kssContext;
    }

    public void setKssContext(String kssContext) {
        this.kssContext = kssContext;
    }

    public String getKssFraction() {
        return kssFraction;
    }

    public void setKssFraction(String kssFraction) {
        this.kssFraction = kssFraction;
    }

    public String getKssRightAnswer() {
        return kssRightAnswer;
    }

    public void setKssRightAnswer(String kssRightAnswer) {
        this.kssRightAnswer = kssRightAnswer;
    }

    public String getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(String paperNum) {
        this.paperNum = paperNum;
    }
}
