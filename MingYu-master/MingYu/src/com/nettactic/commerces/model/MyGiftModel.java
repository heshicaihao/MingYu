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
public class MyGiftModel implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String Code = "";
	private String Giftname = "";
	private String BelongInc = "";
	private String Point = "";
	private String Date = "";
	private String Orderstatus = "";
	
	public String getCode()
	{
		return Code;
	}
	
	public void setCode(String code)
	{
		Code = code;
	}
	
	public String getGiftname()
	{
		return Giftname;
	}
	
	public void setGiftname(String giftname)
	{
		Giftname = giftname;
	}
	
	public String getBelongInc()
	{
		return BelongInc;
	}
	
	public void setBelongInc(String belongInc)
	{
		BelongInc = belongInc;
	}
	
	public String getPoint()
	{
		return Point;
	}
	
	public void setPoint(String point)
	{
		Point = point;
	}
	
	public String getDate()
	{
		return Date;
	}
	
	public void setDate(String date)
	{
		Date = date;
	}
	
	public String getOrderstatus()
	{
		return Orderstatus;
	}
	
	public void setOrderstatus(String orderstatus)
	{
		Orderstatus = orderstatus;
	}
	
	public MyGiftModel(JSONObject json) throws JSONException,
			EcommerceException
	{
		super();
		this.Code = json.optString("Code", "");
		this.Giftname = json.optString("Giftname", "");
		this.BelongInc = json.optString("BelongInc", "");
		this.Point = json.optString("Point", "");
		this.Date = json.optString("Date", "");
		this.Orderstatus = json.optString("Orderstatus", "");
		
	}
}
