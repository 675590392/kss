package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/3/10.
 */

public class Psychometrics {

    /**
     * id : 2
     * isMultiselect : 1
     * kssAnswerA : 梵蒂冈4
     * kssAnswerB : 44
     * kssAnswerC : 343
     * kssAnswerD : 43
     * kssAnswerE : 仍梵蒂冈
     * kssContext : 电放费
     * kssFraction : 0
     */

    private int id;
    private int isMultiselect;
    private String kssAnswerA;
    private String kssAnswerB;
    private String kssAnswerC;
    private String kssAnswerD;
    private String kssAnswerE;
    private String kssContext;
    private int kssFraction;

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

    public int getKssFraction() {
        return kssFraction;
    }

    public void setKssFraction(int kssFraction) {
        this.kssFraction = kssFraction;
    }
}
