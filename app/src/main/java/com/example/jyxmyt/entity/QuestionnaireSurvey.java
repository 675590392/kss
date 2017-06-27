package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/4/27.
 * 通过试卷编号查询问卷调查
 */

//   "id":6,
//     "isMultiselect":0,
//     "kssAnswerA":"都是",
//      kssAnswerB":"十大",
//     "kssAnswerC":"梵蒂冈",
//      "kssAnswerD":"得分",
//      "kssAnswerE":"梵蒂冈",
//      "kssContext":"是打发斯蒂芬",
//      "kssFraction":1,
//     "kssRightAnswer":"A",
//     "paperNum":"1493103920875"
public class QuestionnaireSurvey {
    private  int id;
    private  int isMultiselect;
    private  int kssFraction;


    private String kssAnswerA;
    private String kssAnswerB;
    private String kssAnswerC;
    private String kssAnswerD;
    private String kssAnswerE;
    private String kssContext;
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

    public int getKssFraction() {
        return kssFraction;
    }

    public void setKssFraction(int kssFraction) {
        this.kssFraction = kssFraction;
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
