package com.example.jyxmyt.entity;

/**
 * Created by Administrator on 2017/4/28.
 */

public class QueryMsgPage {

    /**
     * page : 1
     * roomNum : 0201
     */

    private int  page;
    private String roomNum;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
