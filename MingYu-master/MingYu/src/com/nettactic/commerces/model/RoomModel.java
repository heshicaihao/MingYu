package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * 房间详情参数
 */
public class RoomModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 早餐类型 0无早餐 1单早餐 2双早餐
	 */
	private int isBreakfast;
	
	/** 是否有房间 */
	private boolean haveRoom;
	
	private boolean ifShowDetail;
	
	/** 起价 */
	private String firstPrice = "";
	
	/** 挂牌价 */
	private String marketPrice = "";
	
	/** 房间类型名称 */
	private String roomTypeName = "";
	
	/** 房价标识 */
	private String rateCode = "";
	
	/** 房间类型标识 */
	private String roomTypeCode = "";
	
	private String roomTypeDes = "";
	
	private String RoomTypeImages = "";
	
	private String roomType = "";
	
	public String getRoomTypeDes()
	{
		return roomTypeDes;
	}
	
	public void setRoomTypeDes(String roomTypeDes)
	{
		this.roomTypeDes = roomTypeDes;
	}
	
	public String getRoomTypeImages()
	{
		return RoomTypeImages;
	}
	
	public void setRoomTypeImages(String roomTypeImages)
	{
		RoomTypeImages = roomTypeImages;
	}
	
	public boolean isHaveRoom()
	{
		return haveRoom;
	}
	
	public String getRoomType()
	{
		return roomType;
	}
	
	public void setRoomType(String roomType)
	{
		this.roomType = roomType;
	}
	
	public void setHaveRoom(boolean haveRoom)
	{
		this.haveRoom = haveRoom;
	}
	
	public String getFirstPrice()
	{
		return firstPrice;
	}
	
	public void setFirstPrice(String firstPrice)
	{
		this.firstPrice = firstPrice;
	}
	
	public String getMarketPrice()
	{
		return marketPrice;
	}
	
	public void setMarketPrice(String marketPrice)
	{
		this.marketPrice = marketPrice;
	}
	
	public String getRoomTypeName()
	{
		return roomTypeName;
	}
	
	public void setRoomTypeName(String roomTypeName)
	{
		this.roomTypeName = roomTypeName;
	}
	
	public String getRateCode()
	{
		return rateCode;
	}
	
	public void setRateCode(String rateCode)
	{
		this.rateCode = rateCode;
	}
	
	public int getIsBreakfast()
	{
		return isBreakfast;
	}
	
	public void setIsBreakfast(int isBreakfast)
	{
		this.isBreakfast = isBreakfast;
	}
	
	public String getRoomTypeCode()
	{
		return roomTypeCode;
	}
	
	public void setRoomTypeCode(String roomTypeCode)
	{
		this.roomTypeCode = roomTypeCode;
	}
	
	public boolean isIfShowDetail()
	{
		return ifShowDetail;
	}
	
	public void setIfShowDetail(boolean ifShowDetail)
	{
		this.ifShowDetail = ifShowDetail;
	}
	
	public RoomModel(JSONObject jsonObject)
	{
		super();
		
		this.haveRoom = jsonObject.optBoolean("HaveRoom");
		
		this.firstPrice = jsonObject.optString("FirstPrice");
		
		this.marketPrice = jsonObject.optString("MarketPrice");
		
		this.roomTypeName = jsonObject.optString("RoomTypeName");
		
		this.rateCode = jsonObject.optString("RateCode");
		
		this.isBreakfast = jsonObject.optInt("IsBreakfast");
		
		this.roomTypeCode = jsonObject.optString("RoomTypeCode");
		
		this.roomType = jsonObject.optString("Type");
		
		this.roomTypeDes = jsonObject.optString("RoomTypeDes");
		
		this.RoomTypeImages = jsonObject.optString("RoomTypeImages");
		
		this.ifShowDetail = false;
	}
	
}
