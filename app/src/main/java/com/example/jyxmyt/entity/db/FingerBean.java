package com.example.jyxmyt.entity.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tanghao on 2017/3/7.
 */

@Entity
public class FingerBean {

    @Id
    private Long id;
    private int fingerSynoId;//握手Id
    private int type;//数据类型，1表示添加，2表示修改，3表示删除
    private String kssArea;
    private String kssFeatureNumFirst;
    private String kssFeatureNumSecond;
    private String kssPrisonerName;
    private String kssPrisonerNum;
    private String kssRoomNum;
    private String kssState;//0表示未录入、1指纹信息第一次、2指纹信息第二次
    @Generated(hash = 1051976372)
    public FingerBean(Long id, int fingerSynoId, int type, String kssArea,
            String kssFeatureNumFirst, String kssFeatureNumSecond,
            String kssPrisonerName, String kssPrisonerNum, String kssRoomNum,
            String kssState) {
        this.id = id;
        this.fingerSynoId = fingerSynoId;
        this.type = type;
        this.kssArea = kssArea;
        this.kssFeatureNumFirst = kssFeatureNumFirst;
        this.kssFeatureNumSecond = kssFeatureNumSecond;
        this.kssPrisonerName = kssPrisonerName;
        this.kssPrisonerNum = kssPrisonerNum;
        this.kssRoomNum = kssRoomNum;
        this.kssState = kssState;
    }
    @Generated(hash = 110665956)
    public FingerBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getFingerSynoId() {
        return this.fingerSynoId;
    }
    public void setFingerSynoId(int fingerSynoId) {
        this.fingerSynoId = fingerSynoId;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getKssArea() {
        return this.kssArea;
    }
    public void setKssArea(String kssArea) {
        this.kssArea = kssArea;
    }
    public String getKssFeatureNumFirst() {
        return this.kssFeatureNumFirst;
    }
    public void setKssFeatureNumFirst(String kssFeatureNumFirst) {
        this.kssFeatureNumFirst = kssFeatureNumFirst;
    }
    public String getKssFeatureNumSecond() {
        return this.kssFeatureNumSecond;
    }
    public void setKssFeatureNumSecond(String kssFeatureNumSecond) {
        this.kssFeatureNumSecond = kssFeatureNumSecond;
    }
    public String getKssPrisonerName() {
        return this.kssPrisonerName;
    }
    public void setKssPrisonerName(String kssPrisonerName) {
        this.kssPrisonerName = kssPrisonerName;
    }
    public String getKssPrisonerNum() {
        return this.kssPrisonerNum;
    }
    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
    }
    public String getKssRoomNum() {
        return this.kssRoomNum;
    }
    public void setKssRoomNum(String kssRoomNum) {
        this.kssRoomNum = kssRoomNum;
    }
    public String getKssState() {
        return this.kssState;
    }
    public void setKssState(String kssState) {
        this.kssState = kssState;
    }


}
