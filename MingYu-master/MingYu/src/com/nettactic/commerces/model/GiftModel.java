package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONObject;

public class GiftModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	/** 礼品ID */
	private int gift_ID;

	/** 礼品图片地址 */
	private String gift_Image = "";

	/** 礼品名称 */
	private String gift_Name = "";

	/** 礼品所需积分 */
	private String gift_Code = "";

	/** 礼品详细信息 */
	private String gift_Other = "";

	private String gift_Point = "0";

	private String change_Num = "1";

	private String diffCode = "0";

	public int getGift_ID()
	{
		return gift_ID;
	}

	public void setGift_ID(int gift_ID)
	{
		this.gift_ID = gift_ID;
	}

	public String getDiffCode()
	{
		return diffCode;
	}

	public void setDiffCode(String diffCode)
	{
		this.diffCode = diffCode;
	}

	public String getGift_Image()
	{
		return gift_Image;
	}

	public void setGift_Image(String gift_Image)
	{
		this.gift_Image = gift_Image;
	}

	public String getChange_Num()
	{
		return change_Num;
	}

	public void setChange_Num(String change_Num)
	{
		this.change_Num = change_Num;
	}

	public String getGift_Name()
	{
		return gift_Name;
	}

	public void setGift_Name(String gift_Name)
	{
		this.gift_Name = gift_Name;
	}

	public String getGift_Code()
	{
		return gift_Code;
	}

	public void setGift_Code(String gift_Code)
	{
		this.gift_Code = gift_Code;
	}

	public String getGift_Other()
	{
		return gift_Other;
	}

	public void setGift_Other(String gift_Other)
	{
		this.gift_Other = gift_Other;
	}

	public String getGift_Point()
	{
		return gift_Point;
	}

	public void setGift_Point(String gift_Point)
	{
		this.gift_Point = gift_Point;
	}

	public GiftModel(JSONObject json)
	{
		super();

		this.gift_ID = json.optInt("ID", 0);
		this.gift_Name = json.optString("Name", "");
		this.gift_Image = json.optString("Image", "");
		this.gift_Code = json.optString("Code", "");
		this.gift_Other = json.optString("Other", "");
		this.gift_Point = json.optString("Point", "0");
	};
}
