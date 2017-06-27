package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/3/7.
 */

public class ServcieActionDetailed {

    /**
     * id : 1
     * kssPrisoner :
     * kssProductName : 单衣
     * kssProductUnit : 条
     */

    private int id;
    private String kssPrisoner;
    private String kssProductName;
    private String kssProductUnit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKssPrisoner() {
        return kssPrisoner;
    }

    public void setKssPrisoner(String kssPrisoner) {
        this.kssPrisoner = kssPrisoner;
    }

    public String getKssProductName() {
        return kssProductName;
    }

    public void setKssProductName(String kssProductName) {
        this.kssProductName = kssProductName;
    }

    public String getKssProductUnit() {
        return kssProductUnit;
    }

    public void setKssProductUnit(String kssProductUnit) {
        this.kssProductUnit = kssProductUnit;
    }
}
