package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.nettactic.commerces.service.exception.EcommerceException;

/**
 * 酒店详情的参数
 * 
 * 
 * 
 */
public class HotelListItemModel implements Serializable
{

	private static final long serialVersionUID = -4219906881218424077L;

	private String hotelName = "";
	private String hotelCode = "";
	private String hotelImg = "";
	private String hotelAddr = "";
	private String hotelTel = "";
	private String lowPrice = "";

	public String getHotelName()
	{
		return hotelName;
	}

	public void setHotelName(String hotelName)
	{
		this.hotelName = hotelName;
	}

	public String getHotelCode()
	{
		return hotelCode;
	}

	public void setHotelCode(String hotelCode)
	{
		this.hotelCode = hotelCode;
	}

	public String getHotelImg()
	{
		return hotelImg;
	}

	public void setHotelImg(String hotelImg)
	{
		this.hotelImg = hotelImg;
	}

	public String getHotelAddr()
	{
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr)
	{
		this.hotelAddr = hotelAddr;
	}

	public String getHotelTel()
	{
		return hotelTel;
	}

	public void setHotelTel(String hotelTel)
	{
		this.hotelTel = hotelTel;
	}

	public String getLowPrice()
	{
		return lowPrice;
	}

	public void setLowPrice(String lowPrice)
	{
		this.lowPrice = lowPrice;
	}

	public HotelListItemModel(JSONObject json) throws JSONException,
			EcommerceException
	{
		super();
		this.hotelName = json.optString("HotelName", "");
		this.hotelCode = json.optString("HotelCode", "");
		this.hotelAddr = json.optString("HotelAddr", "");
		this.hotelImg = json.optString("HotelImg", "");
		this.hotelTel = json.optString("HotelTel", "");
		this.lowPrice = json.optString("LowPrice", "");

	}
}
