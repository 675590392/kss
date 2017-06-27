package com.example.jyxmyt.entity;

import android.provider.MediaStore.Images;

/**
 * 物品对象(大帐购物)
 * @author 123
 *
 */
public class Goods {
	public String goodId; // 物品ID
	public String goodName; // 物品名称
	public String goodPrice; // 物品价格
	public String goodStandard; // 物品规格
	public String goodNumber; // 物品数量
	public Integer integer; //图片

	public Boolean isBuy; // 是否购买
	public String buyNumber; // 购买数量
	public String buyTotalPrices; // 购买总价
	public Boolean isJS = false; //是否结算

	public Integer getInteger(){
		return integer;
	}

	public void setInteger(Integer integer){
		this.integer = integer;
	}

	public Boolean getIsJS(){
		return isJS;
	}

	public void setIsJS(Boolean isJS){
		this.isJS = isJS;
	}

	public Boolean getIsBuy(){
		return isBuy;
	}

	public void setIsBuy(Boolean isBuy){
		this.isBuy = isBuy;
	}

	public String getBuyNumber(){
		return buyNumber;
	}

	public void setBuyNumber(String buyNumber){
		this.buyNumber = buyNumber;
	}

	public String getBuyTotalPrices(){
		return buyTotalPrices;
	}

	public void setBuyTotalPrices(String buyTotalPrices){
		this.buyTotalPrices = buyTotalPrices;
	}

	public String getGoodId(){
		return goodId;
	}

	public void setGoodId(String goodId){
		this.goodId = goodId;
	}

	public String getGoodName(){
		return goodName;
	}

	public void setGoodName(String goodName){
		this.goodName = goodName;
	}

	public String getGoodPrice(){
		return goodPrice;
	}

	public void setGoodPrice(String goodPrice){
		this.goodPrice = goodPrice;
	}

	public String getGoodStandard(){
		return goodStandard;
	}

	public void setGoodStandard(String goodStandard){
		this.goodStandard = goodStandard;
	}

	public String getGoodNumber(){
		return goodNumber;
	}

	public void setGoodNumber(String goodNumber){
		this.goodNumber = goodNumber;
	}
}
