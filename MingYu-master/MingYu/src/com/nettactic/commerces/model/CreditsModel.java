package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * 积分记录Model类
 * 
 * @author ruan
 * 
 */
public class CreditsModel implements Serializable
{

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private String ArrivalDate = "";

	private String DepartureDate = "";

	private String BookerCardType = "";

	private String TotalRev = "";

	private String FbRev = "";

	private String OtherRev = "";

	private String Points = "";

	private String Nights = "";

	public CreditsModel(JSONObject jsonObject)
	{
		super();
		ArrivalDate = jsonObject.optString("ArrivalDate");

		DepartureDate = jsonObject.optString("DepartureDate");

		BookerCardType = jsonObject.optString("BookerCardType");

		TotalRev = jsonObject.optString("TotalRev");

		FbRev = jsonObject.optString("FbRev");

		OtherRev = jsonObject.optString("OtherRev");

		Points = jsonObject.optString("Points");

		Nights = jsonObject.optString("Nights");

	}

	public String getArrivalDate()
	{
		return ArrivalDate;
	}

	public void setArrivalDate(String arrivalDate)
	{
		ArrivalDate = arrivalDate;
	}

	public String getDepartureDate()
	{
		return DepartureDate;
	}

	public void setDepartureDate(String departureDate)
	{
		DepartureDate = departureDate;
	}

	public String getBookerCardType()
	{
		return BookerCardType;
	}

	public void setBookerCardType(String bookerCardType)
	{
		BookerCardType = bookerCardType;
	}

	public String getTotalRev()
	{
		return TotalRev;
	}

	public void setTotalRev(String totalRev)
	{
		TotalRev = totalRev;
	}

	public String getFbRev()
	{
		return FbRev;
	}

	public void setFbRev(String fbRev)
	{
		FbRev = fbRev;
	}

	public String getOtherRev()
	{
		return OtherRev;
	}

	public void setOtherRev(String otherRev)
	{
		OtherRev = otherRev;
	}

	public String getPoints()
	{
		return Points;
	}

	public void setPoints(String points)
	{
		Points = points;
	}

	public String getNights()
	{
		return Nights;
	}

	public void setNights(String nights)
	{
		Nights = nights;
	}

}
