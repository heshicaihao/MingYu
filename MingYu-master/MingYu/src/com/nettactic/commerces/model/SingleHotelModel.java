package com.nettactic.commerces.model;

import java.io.Serializable;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.nettactic.commerces.service.exception.EcommerceException;

public class SingleHotelModel implements Serializable
{
	
	private static final long serialVersionUID = -4219906881218424077L;
	
	private String hotelID = "";
	
	/**
	 * 酒店名称
	 */
	private String hotelName = "";
	
	/**
	 * 酒店描述标题
	 */
	private String hotelTitle = "";
	
	/**
	 * 酒店描述
	 */
	private String hotelIntro = "";
	
	/**
	 * 酒店图片
	 */
	private String hotelImg = "";
	
	/**
	 * 酒店URL
	 */
	private String hotelUrl = "";
	
	/**
	 * 酒店星级
	 */
	private String hotelStar = "";
	
	/**
	 * 酒店传真
	 */
	private String hotelFax = "";
	
	/**
	 * 酒店描述
	 */
	private String hotelRemark = "";
	
	/**
	 * 酒店电话
	 */
	private String hotelTel = "";
	
	/**
	 * 酒店地址
	 */
	private String hotelAddress = "";
	
	/**
	 * 酒店优惠精选有效期的起始时间
	 */
	private String hotelTimeFrom = "";
	
	/**
	 * 酒店优惠精选有效期的停止时间
	 */
	private String hotelTimeTo = "";
	
	/**
	 * 酒店所属的城市代码
	 */
	private String cityCode = "";
	
	/**
	 * 酒店代码
	 */
	private String hotelCode = "";
	
	/**
	 * 酒店是否开业
	 */
	private String Offer = "";
	
	/**
	 * 酒店介绍
	 */
	private String Intro = "";
	
	/**
	 * 酒店地图
	 */
	private String mapUrl = "";
	
	/**
	 * 餐饮娱乐详情链接
	 */
	private String contentsUrl = "";
	
	/**
	 * 酒店点评地址
	 */
	private String commentUrl = "";
	
	public String getCommentUrl()
	{
		return commentUrl;
	}
	
	public void setCommentUrl(String commentUrl)
	{
		this.commentUrl = commentUrl;
	}
	
	public String getHotelID()
	{
		return hotelID;
	}
	
	public String getOffer()
	{
		return Offer;
	}
	
	public void setOffer(String offer)
	{
		Offer = offer;
	}
	
	public String getIntro()
	{
		return Intro;
	}
	
	public void setIntro(String intro)
	{
		Intro = intro;
	}
	
	public void setHotelID(String hotelID)
	{
		this.hotelID = hotelID;
	}
	
	public String getHotelName()
	{
		return hotelName;
	}
	
	public void setHotelName(String hotelName)
	{
		this.hotelName = hotelName;
	}
	
	public String getHotelTitle()
	{
		return hotelTitle;
	}
	
	public void setHotelTitle(String hotelTitle)
	{
		this.hotelTitle = hotelTitle;
	}
	
	public String getHotelIntro()
	{
		return hotelIntro;
	}
	
	public void setHotelIntro(String hotelIntro)
	{
		this.hotelIntro = hotelIntro;
	}
	
	public String getHotelImg()
	{
		return hotelImg;
	}
	
	public void setHotelImg(String hotelImg)
	{
		this.hotelImg = hotelImg;
	}
	
	public String getHotelUrl()
	{
		return hotelUrl;
	}
	
	public String getMapUrl()
	{
		return mapUrl;
	}
	
	public void setMapUrl(String mapUrl)
	{
		this.mapUrl = mapUrl;
	}
	
	public void setHotelUrl(String hotelUrl)
	{
		this.hotelUrl = hotelUrl;
	}
	
	public String getHotelStar()
	{
		return hotelStar;
	}
	
	public void setHotelStar(String hotelStar)
	{
		this.hotelStar = hotelStar;
	}
	
	public String getHotelFax()
	{
		return hotelFax;
	}
	
	public void setHotelFax(String hotelFax)
	{
		this.hotelFax = hotelFax;
	}
	
	public String getHotelRemark()
	{
		return hotelRemark;
	}
	
	public void setHotelRemark(String hotelRemark)
	{
		this.hotelRemark = hotelRemark;
	}
	
	public String getHotelAddress()
	{
		return hotelAddress;
	}
	
	public String getHotelTel()
	{
		return hotelTel;
	}
	
	public void setHotelTel(String hotelTel)
	{
		this.hotelTel = hotelTel;
	}
	
	public void setHotelAddress(String hotelAddress)
	{
		this.hotelAddress = hotelAddress;
	}
	
	public String getHotelTimeFrom()
	{
		return hotelTimeFrom;
	}
	
	public void setHotelTimeFrom(String hotelTimeFrom)
	{
		this.hotelTimeFrom = hotelTimeFrom;
	}
	
	public String getHotelTimeTo()
	{
		return hotelTimeTo;
	}
	
	public void setHotelTimeTo(String hotelTimeTo)
	{
		this.hotelTimeTo = hotelTimeTo;
	}
	
	public String getCityCode()
	{
		return cityCode;
	}
	
	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}
	
	public String getHotelCode()
	{
		return hotelCode;
	}
	
	public String getContentsUrl()
	{
		return contentsUrl;
	}
	
	public void setContentsUrl(String contentsUrl)
	{
		this.contentsUrl = contentsUrl;
	}
	
	public void setHotelCode(String hotelCode)
	{
		this.hotelCode = hotelCode;
	}
	
	public SingleHotelModel(JSONObject json) throws JSONException,
			EcommerceException {
		super();
		this.hotelID = json.optString("ID", "");
		this.hotelName = json.optString("Name", "");
		this.hotelTitle = json.optString("Title", "");
		this.hotelIntro = json.optString("Intro", "");
		this.hotelImg = json.optString("Images", "");
		this.hotelUrl = json.optString("Url", "");
		this.hotelStar = json.optString("Star", "");
		this.hotelTel = json.optString("HotelTel", "");
		this.hotelFax = json.optString("Fax", "");
		this.hotelRemark = json.optString("HotelRemark", "");
		this.hotelAddress = json.optString("HotelAddr", "");
		this.hotelTimeFrom = json.optString("TimeFrom", "");
		this.hotelTimeTo = json.optString("TimeTo", "");
		this.cityCode = json.optString("CityCode", "");
		this.hotelCode = json.optString("HotelCode", "");
		this.Offer = json.optString("Offer", "");
		this.Intro = json.optString("Intro", "");
		this.mapUrl = json.optString("MapUrl", "");
		this.contentsUrl = json.optString("ContentsUrl", "");
		this.commentUrl = json.optString("CommentUrl", "");
	}
}
