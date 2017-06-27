package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/4/28.
 * 菜单权限
 */

//"grantMenu":"点名,个人消费查询,接济单,房间日记载,心理测评,问卷调查,大帐购物,预约投诉",//权限
//id":538, "
//        kssArea":"01",
// "kssInsideNum":"20170015",
// "kssPeopleState":0,
//"kssPrisonerName":"玉素甫·哈斯·阿巴索夫",
//"kssPrisonerNum":"31000011120170015",
//"kssRoomNum":"0102"
public class MenuPermissions {

    private  String grantMenu;
    private  int id;
    private  String kssArea;
    private  String kssInsideNum;
    private  int kssPeopleState;
    private  String kssPrisonerName;
    private  String kssPrisonerNum;
    private  String  kssRoomNum;

    public String getGrantMenu() {
        return grantMenu;
    }

    public void setGrantMenu(String grantMenu) {
        this.grantMenu = grantMenu;
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

    public String getKssInsideNum() {
        return kssInsideNum;
    }

    public void setKssInsideNum(String kssInsideNum) {
        this.kssInsideNum = kssInsideNum;
    }

    public int getKssPeopleState() {
        return kssPeopleState;
    }

    public void setKssPeopleState(int kssPeopleState) {
        this.kssPeopleState = kssPeopleState;
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
}
