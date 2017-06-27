package com.example.jyxmyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tanghao on 2017/3/8.
 */

public class Shopping {
    /**
     * userInfo : {"kssPrisonerName":"庄剑","kssPrisonerNum":"31000011120170004","totalMoney":19613.5,"purchaseQuantity":500,"consumeMoney":386.5}
     * page : 1
     * total : 1
     * records : 3
     * rows : [{"id":33,"limitedNumber":1,"shopName":"SA","shopUnit":0,"shopCode":"0000000000001","shopPicture":"","shopPrice":122,"shopType":"1"},{"id":42,"limitedNumber":1,"shopName":"苹果醋","shopUnit":0,"shopCode":"000002","shopPrice":1,"shopType":"1"},{"id":41,"limitedNumber":1,"shopName":"饼干","shopUnit":0,"shopCode":"0000001","shopPrice":1,"shopType":"1"}]
     */

    private UserInfoBean userInfo;
    private int page;
    private int total;
    private int records;
    private List<RowsBean> rows;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class UserInfoBean {
        /**
         * kssPrisonerName : 庄剑
         * kssPrisonerNum : 31000011120170004
         * totalMoney : 19613.5
         * purchaseQuantity : 500
         * consumeMoney : 386.5
         */

        private String kssPrisonerName;
        private String kssPrisonerNum;
        private double totalMoney;
        private double purchaseQuantity;//限购金额
        private double consumeMoney;//消费金额

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

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public double getPurchaseQuantity() {
            return purchaseQuantity;
        }

        public void setPurchaseQuantity(int purchaseQuantity) {
            this.purchaseQuantity = purchaseQuantity;
        }

        public double getConsumeMoney() {
            return consumeMoney;
        }

        public void setConsumeMoney(double consumeMoney) {
            this.consumeMoney = consumeMoney;
        }
    }

    public static class RowsBean implements Serializable {
        /**
         * id : 33
         * limitedNumber : 1
         * shopName : SA
         * shopUnit : 0
         * shopCode : 0000000000001
         * shopPicture :
         * shopPrice : 122
         * shopType : 1
         */

        private int id;
        private int limitedNumber;
        private String shopName;//商品名称
        private int shopUnit;//商品单位
        private String shopCode;//商品编号
        private String shopPicture;//商品图片
        private double shopPrice;//商品单价
        private String shopType;//商品类型
        private int buyShopNum;//已购物品数量
        private int count = 1;//数目
        private String shopStandard;//商品规格

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLimitedNumber() {
            return limitedNumber;
        }

        public void setLimitedNumber(int limitedNumber) {
            this.limitedNumber = limitedNumber;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getShopUnit() {
            return shopUnit;
        }

        public void setShopUnit(int shopUnit) {
            this.shopUnit = shopUnit;
        }

        public String getShopCode() {
            return shopCode;
        }

        public void setShopCode(String shopCode) {
            this.shopCode = shopCode;
        }

        public String getShopPicture() {
            return shopPicture;
        }

        public void setShopPicture(String shopPicture) {
            this.shopPicture = shopPicture;
        }

        public double getShopPrice() {
            return shopPrice;
        }

        public void setShopPrice(double shopPrice) {
            this.shopPrice = shopPrice;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public int getBuyShopNum() {
            return buyShopNum;
        }

        public void setBuyShopNum(int buyShopNum) {
            this.buyShopNum = buyShopNum;
        }

        public String getShopStandard() {
            return shopStandard;
        }

        public void setShopStandard(String shopStandard) {
            this.shopStandard = shopStandard;
        }
    }
}