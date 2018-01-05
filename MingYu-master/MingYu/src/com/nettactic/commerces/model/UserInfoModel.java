package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * 用户信息Model
 * 
 * 
 */
public class UserInfoModel implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private static final UserInfoModel userInfoModel = new UserInfoModel();
	
	/**
	 * 姓
	 */
	private String firstName = "";
	
	/**
	 * 名
	 */
	private String lastName = "";
	
	/**
	 * 中文名字
	 */
	private String chineseName = "";
	
	/**
	 * 账户余额
	 */
	private String balance = "";
	/**
	 * 会员卡类型
	 */
	private String cardType = "";
	
	/**
	 * 卡号
	 */
	private String cardNo = "";
	
	/**
	 * 证件号码
	 */
	private String IDNo = "";
	
	/**
	 * 手机号码
	 */
	private String mobile = "";
	
	/**
	 * 邮箱
	 */
	private String email = "";
	
	/**
	 * 省
	 */
	private String province = "";
	
	/**
	 * 市
	 */
	private String city = "";
	
	/**
	 * 具体地址
	 */
	private String address = "";
	
	/**
	 * 邮编
	 */
	private String postalCode = "";
	
	/**
	 * 性别
	 */
	private String sex = "";
	
	private String totlePoints = "0";
	
	private String UserState = "";
	
	private String Nationality = "";
	private String Language = "";
	private String OfficePhone = "";
	private String HomePhone = "";
	private String Fax = "";
	private String PostalCode = "";
	
	public static UserInfoModel getUserInfoModel()
	{
		return userInfoModel;
		
	}
	
	public void initUserInfoModel(JSONObject jsonObject)
	{
		this.firstName = jsonObject.optString("FirstName");
		this.lastName = jsonObject.optString("LastName");
		this.cardType = jsonObject.optString("CardType");
		this.cardNo = jsonObject.optString("CardNo");
		this.IDNo = jsonObject.optString("IDNo");
		this.mobile = jsonObject.optString("Mobile");
		this.email = jsonObject.optString("Email");
		this.province = jsonObject.optString("Province");
		this.address = jsonObject.optString("Address");
		this.postalCode = jsonObject.optString("PostalCode");
		this.sex = jsonObject.optString("Sex");
		this.totlePoints = jsonObject.optString("TotlePoints", "0");
		this.UserState = jsonObject.optString("UserState", "正常");
		this.chineseName = jsonObject.optString("Name", "");
		this.balance = jsonObject.optString("Balance", "");
		
		this.Nationality = jsonObject.optString("Nationality", "");
		this.Language = jsonObject.optString("Language", "");
		this.OfficePhone = jsonObject.optString("OfficePhone", "");
		this.HomePhone = jsonObject.optString("HomePhone", "");
		this.Fax = jsonObject.optString("Fax", "");
		this.PostalCode = jsonObject.optString("PostalCode", "");
	}
	
	public String getNationality()
	{
		return Nationality;
	}
	
	public void setNationality(String nationality)
	{
		Nationality = nationality;
	}
	
	public String getLanguage()
	{
		return Language;
	}
	
	public void setLanguage(String language)
	{
		Language = language;
	}
	
	public String getOfficePhone()
	{
		return OfficePhone;
	}
	
	public void setOfficePhone(String officePhone)
	{
		OfficePhone = officePhone;
	}
	
	public String getHomePhone()
	{
		return HomePhone;
	}
	
	public void setHomePhone(String homePhone)
	{
		HomePhone = homePhone;
	}
	
	public String getFax()
	{
		return Fax;
	}
	
	public void setFax(String fax)
	{
		Fax = fax;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getUserState()
	{
		return UserState;
	}
	
	public void setUserState(String userState)
	{
		UserState = userState;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getTotlePoints()
	{
		return totlePoints;
	}
	
	public void setTotlePoints(String totlePoints)
	{
		this.totlePoints = totlePoints;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getIDNo()
	{
		return IDNo;
	}
	
	public String getCardNo()
	{
		return cardNo;
	}
	
	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}
	
	public void setIDNo(String iDNo)
	{
		IDNo = iDNo;
	}
	
	public String getMobile()
	{
		return mobile;
	}
	
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getProvince()
	{
		return province;
	}
	
	public void setProvince(String province)
	{
		this.province = province;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getPostalCode()
	{
		return postalCode;
	}
	
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}
	
	public String getSex()
	{
		return sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public String getCardType()
	{
		return cardType;
	}
	
	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}
	
	public String getChineseName()
	{
		return chineseName;
	}
	
	public void setChineseName(String chineseName)
	{
		this.chineseName = chineseName;
	}
	
	public String getBalance()
	{
		return balance;
	}
	
	public void setBalance(String balance)
	{
		this.balance = balance;
	}
	
}
