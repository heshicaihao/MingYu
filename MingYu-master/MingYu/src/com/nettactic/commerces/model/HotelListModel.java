package com.nettactic.commerces.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.nettactic.commerces.service.exception.EcommerceException;

public class HotelListModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String HotelName = "";
	private String HotelCode = "";
	private String HotelImg = "";
	private String HotelAddr = "";
	private String HotelTel = "";
	private String LowPrice = "";
	private String Longitude = "";
	private String Latitude = "";
	
	public String getHotelName() {
		return HotelName;
	}
	
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	
	public String getHotelCode() {
		return HotelCode;
	}
	
	public void setHotelCode(String hotelCode) {
		HotelCode = hotelCode;
	}
	
	public String getHotelImg() {
		return HotelImg;
	}
	
	public void setHotelImg(String hotelImg) {
		HotelImg = hotelImg;
	}
	
	public String getHotelAddr() {
		return HotelAddr;
	}
	
	public void setHotelAddr(String hotelAddr) {
		HotelAddr = hotelAddr;
	}
	
	public String getHotelTel() {
		return HotelTel;
	}
	
	public void setHotelTel(String hotelTel) {
		HotelTel = hotelTel;
	}
	
	public String getLowPrice() {
		return LowPrice;
	}
	
	public void setLowPrice(String lowPrice) {
		LowPrice = lowPrice;
	}
	
	public String getLongitude() {
		return Longitude;
	}
	
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	
	public String getLatitude() {
		return Latitude;
	}
	
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	
	public HotelListModel(JSONObject json) throws JSONException,
			EcommerceException
	{
		super();
		
		this.HotelName = json.optString("HotelName", "");
		this.HotelCode = json.optString("HotelCode", "");
		this.HotelImg = json.optString("HotelImg", "");
		this.HotelAddr = json.optString("HotelAddr", "");
		this.HotelTel = json.optString("HotelTel", "");
		this.LowPrice = json.optString("LowPrice", "");
		this.Longitude = json.optString("Longitude", "");
		this.Latitude = json.optString("Latitude", "");
	}
}
