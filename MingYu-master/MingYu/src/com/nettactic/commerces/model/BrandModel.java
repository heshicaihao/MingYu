package com.nettactic.commerces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nettactic.commerces.service.exception.EcommerceException;

/**
 * 酒店品牌参数对象
 * 
 * 
 */
public class BrandModel implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 品牌名称
	 */
	private String brand_name;
	
	/**
	 * 酒店集团log的url地址
	 */
	private String brand_LogUrl;
	
	/**
	 * 酒店集团的描述
	 */
	private String brand_Introl;
	
	private String brand_Img;
	
	/**
	 * 此品牌下的酒店集合
	 */
	private List<HotelModel> hotelList;
	
	/**
	 * 判断品牌信息在页面上是否已经显示出来
	 */
	private boolean ifShowItem;
	
	public String getBrand_name() {
		return brand_name;
	}
	
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	public String getBrand_LogUrl() {
		return brand_LogUrl;
	}
	
	public String getBrand_Img() {
		return brand_Img;
	}
	
	public void setBrand_Img(String brand_Img) {
		this.brand_Img = brand_Img;
	}
	
	public void setBrand_LogUrl(String brand_LogUrl) {
		this.brand_LogUrl = brand_LogUrl;
	}
	
	public String getBrand_Introl() {
		return brand_Introl;
	}
	
	public void setBrand_Introl(String brand_Introl) {
		this.brand_Introl = brand_Introl;
	}
	
	public List<HotelModel> getHotelList() {
		return hotelList;
	}
	
	public void setHotelList(List<HotelModel> hotelList) {
		this.hotelList = hotelList;
	}
	
	public boolean isIfShowItem() {
		return ifShowItem;
	}
	
	public void setIfShowItem(boolean ifShowItem) {
		this.ifShowItem = ifShowItem;
	}
	
	public BrandModel(JSONObject json) throws JSONException, EcommerceException
	{
		super();
		this.brand_name = json.optString("Name", "");
		this.brand_Introl = json.optString("Intro", "");
		this.brand_LogUrl = json.optString("Logo", "");
		this.brand_Img = json.optString("BrandImg", "");
		this.ifShowItem = false;
		JSONArray hotelArray = json.optJSONArray("Hotels");
		List<HotelModel> myHotelList = new ArrayList<HotelModel>();
		HotelModel hotelModel;
		for (int i = 0; i < hotelArray.length(); i++)
		{
			hotelModel = new HotelModel(hotelArray.optJSONObject(i), null);
			myHotelList.add(hotelModel);
		}
		this.hotelList = myHotelList;
		
	}
}
