package com.nettactic.commerces.model;

import java.io.Serializable;

import com.nettactic.commerces.activity.FillCheckInInfoActivity;

/**
 * The most important class 订单信息 此类事用户预订房间所有信息的暂时存储类，采用的是单例模式
 * 
 */
public class OrderModel implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private final static OrderModel ORDERMODEL = new OrderModel();
	
	private String country = "中国";
	private String city = "";
	
	private String hotelName = "";
	
	private String hotelImgUrl = "";
	
	private String hotelIntro = "";
	private String hotelAddr = "";
	private String hotelTel = "";
	private String orderTime = "";
	
	private String hotelMapUrl = "";
	
	private String hotelCode = "";
	
	private String arrivalTime = "";
	
	private String leaveTime = "";
	
	private String room_num = "1";
	
	private String adultsNum = "1";
	
	private String childrenNum = "0";
	
	private String specialNeed = "";
	
	private String roomTypeName = "";
	
	private String roomTypeCode = "";
	
	private String chname = "";
	
	private String firstName = "";
	
	private String lastName = "";
	
	private String IDNo = "";
	
	private String extra_Bed = "";
	
	private String ifAssure = "";
	
	private String email = "";
	
	private String mobile = "";
	
	private String sex = "";
	
	private String reservationType = "";
	
	private String remark = "";
	
	private String cardTypeCode = "";
	
	private String cardNumber = "";
	
	private String cardHold = "";
	
	private String rateCode = "";
	
	private String error = "";
	
	private String orderNo = "";
	
	private String custAccount = "";
	
	private String jshCardNo = "";// 当以瑞宾会身份预订时，填写的嘉赏会卡号
	
	private String bookerID = "";// 订房人
	
	private String roomUnitprice = "";// 房间单价
	
	private String payMent = FillCheckInInfoActivity.DESK;// 支付方式
	
	private String totalPrice = "";
	private String dinner = "";
	private String wedding = "";
	private String nearBy = "";
	private String orderStatus = "未支付";
	
	private double[] lat = new double[2];
	
	public String getOrderStatus()
	{
		return orderStatus;
	}
	
	public double[] getLat()
	{
		return lat;
	}
	
	public void setLat(double[] lat)
	{
		this.lat = lat;
	}
	
	public void setOrderStatus(String orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	
	public String getDinner()
	{
		return dinner;
	}
	
	public void setDinner(String dinner)
	{
		this.dinner = dinner;
	}
	
	public String getOrderTime()
	{
		return orderTime;
	}
	
	public void setOrderTime(String orderTime)
	{
		this.orderTime = orderTime;
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
	
	public String getWedding()
	{
		return wedding;
	}
	
	public void setWedding(String wedding)
	{
		this.wedding = wedding;
	}
	
	public String getNearBy()
	{
		return nearBy;
	}
	
	public void setNearBy(String nearBy)
	{
		this.nearBy = nearBy;
	}
	
	private OrderModel()
	{
		super();
		
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getRoomTypeName()
	{
		return roomTypeName;
	}
	
	public void setRoomTypeName(String roomTypeName)
	{
		this.roomTypeName = roomTypeName;
	}
	
	public String getRoomTypeCode()
	{
		return roomTypeCode;
	}
	
	public String getTotalPrice()
	{
		return totalPrice;
	}
	
	public void setTotalPrice(String totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	
	public void setRoomTypeCode(String roomTypeCode)
	{
		this.roomTypeCode = roomTypeCode;
	}
	
	public String getOrderNo()
	{
		return orderNo;
	}
	
	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}
	
	public String getIDNo()
	{
		return IDNo;
	}
	
	public void setIDNo(String iDNo)
	{
		IDNo = iDNo;
	}
	
	public String getHotelImgUrl()
	{
		return hotelImgUrl;
	}
	
	public void setHotelImgUrl(String hotelImgUrl)
	{
		this.hotelImgUrl = hotelImgUrl;
	}
	
	public String getHotelIntro()
	{
		return hotelIntro;
	}
	
	public void setHotelIntro(String hotelIntro)
	{
		this.hotelIntro = hotelIntro;
	}
	
	public String getHotelMapUrl()
	{
		return hotelMapUrl;
	}
	
	public void setHotelMapUrl(String hotelMapUrl)
	{
		this.hotelMapUrl = hotelMapUrl;
	}
	
	public String getCustAccount()
	{
		return custAccount;
	}
	
	public void setCustAccount(String custAccount)
	{
		this.custAccount = custAccount;
	}
	
	public String getHotelName()
	{
		return hotelName;
	}
	
	public void setHotelName(String hotelName)
	{
		this.hotelName = hotelName;
	}
	
	public String getAdultsNum()
	{
		return adultsNum;
	}
	
	public void setAdultsNum(String adultsNum)
	{
		this.adultsNum = adultsNum;
	}
	
	public String getChildrenNum()
	{
		return childrenNum;
	}
	
	public void setChildrenNum(String childrenNum)
	{
		this.childrenNum = childrenNum;
	}
	
	public String getPayMent()
	{
		return payMent;
	}
	
	public void setPayMent(String payMent)
	{
		this.payMent = payMent;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	 * 获取OrderModel的实例
	 * 
	 * @return
	 */
	public static OrderModel getOrderModel()
	{
		
		return OrderModel.ORDERMODEL;
	}
	
	public String getHotelCode()
	{
		return hotelCode;
	}
	
	public void setHotelCode(String hotelCode)
	{
		this.hotelCode = hotelCode;
	}
	
	public String getArrivalTime()
	{
		return arrivalTime;
	}
	
	public void setArrivalTime(String arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	public String getLeaveTime()
	{
		return leaveTime;
	}
	
	public void setLeaveTime(String leaveTime)
	{
		this.leaveTime = leaveTime;
	}
	
	public String getRoom_num()
	{
		return room_num;
	}
	
	public void setRoom_num(String room_num)
	{
		this.room_num = room_num;
	}
	
	public String getChildren()
	{
		return childrenNum;
	}
	
	public void setChildren(String children)
	{
		this.childrenNum = children;
	}
	
	public String getSpecialNeed()
	{
		return specialNeed;
	}
	
	public void setSpecialNeed(String specialNeed)
	{
		this.specialNeed = specialNeed;
	}
	
	public String getChname()
	{
		return chname;
	}
	
	public void setChname(String chname)
	{
		this.chname = chname;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getExtra_Bed()
	{
		return extra_Bed;
	}
	
	public void setExtra_Bed(String extra_Bed)
	{
		this.extra_Bed = extra_Bed;
	}
	
	public String getIfAssure()
	{
		return ifAssure;
	}
	
	public String getRoomUnitprice()
	{
		return roomUnitprice;
	}
	
	public void setRoomUnitprice(String roomUnitprice)
	{
		this.roomUnitprice = roomUnitprice;
	}
	
	public void setIfAssure(String ifAssure)
	{
		this.ifAssure = ifAssure;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getMobile()
	{
		return mobile;
	}
	
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	
	public String getSex()
	{
		return sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public String getReservationType()
	{
		return reservationType;
	}
	
	public void setReservationType(String reservationType)
	{
		this.reservationType = reservationType;
	}
	
	public String getRemark()
	{
		return remark;
	}
	
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	public String getCardTypeCode()
	{
		return cardTypeCode;
	}
	
	public void setCardTypeCode(String cardTypeCode)
	{
		this.cardTypeCode = cardTypeCode;
	}
	
	public String getCardNumber()
	{
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}
	
	public String getCardHold()
	{
		return cardHold;
	}
	
	public void setCardHold(String cardHold)
	{
		this.cardHold = cardHold;
	}
	
	public String getRateCode()
	{
		return rateCode;
	}
	
	public void setRateCode(String rateCode)
	{
		this.rateCode = rateCode;
	}
	
	public String getError()
	{
		return error;
	}
	
	public void setError(String error)
	{
		this.error = error;
	}
	
	public String getJshCardNo()
	{
		return jshCardNo;
	}
	
	public void setJshCardNo(String jshCardNo)
	{
		this.jshCardNo = jshCardNo;
	}
	
	public String getBookerID()
	{
		return bookerID;
	}
	
	public void setBookerID(String bookerID)
	{
		this.bookerID = bookerID;
	}
	
	/**
	 * 清除用户填写的预订信息
	 */
	public void clearPrivateData()
	{
		this.hotelName = "";
		
		this.hotelImgUrl = "";
		
		this.hotelIntro = "";
		
		this.hotelMapUrl = "";
		
		this.hotelCode = "";
		
		this.arrivalTime = "";
		
		this.leaveTime = "";
		
		this.room_num = "1";
		
		this.adultsNum = "1";
		
		this.childrenNum = "0";
		
		this.specialNeed = "";
		
		this.roomTypeName = "";
		
		this.roomTypeCode = "";
		
		this.chname = "";
		
		this.firstName = "";
		
		this.lastName = "";
		
		this.IDNo = "";
		
		this.extra_Bed = "";
		
		this.ifAssure = "";
		
		this.email = "";
		
		this.mobile = "";
		
		this.sex = "";
		
		this.reservationType = "";
		
		this.remark = "";
		
		this.cardTypeCode = "";
		
		this.cardNumber = "";
		
		this.cardHold = "";
		
		this.rateCode = "";
		
		this.error = "";
		
		this.orderNo = "";
		
		this.custAccount = "";
		
		this.jshCardNo = "";// 当以瑞宾会身份预订时，填写的嘉赏会卡号
		
		this.bookerID = "";// 订房人
		
		this.roomUnitprice = "";// 房间单价
		
		this.dinner = "";
		
		this.wedding = "";
		this.city = "";
		this.lat = new double[2];
		this.nearBy = "";
		this.hotelAddr = "";
		this.hotelTel = "";
		this.orderStatus = "未支付";
		this.orderTime = "";
		this.payMent = FillCheckInInfoActivity.DESK;// 支付方式
		this.totalPrice = "";
		
	}
}