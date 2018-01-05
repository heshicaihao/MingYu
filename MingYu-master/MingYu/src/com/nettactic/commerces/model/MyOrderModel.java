package com.nettactic.commerces.model;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * 订单记录
 * 
 * @author ruan
 * 
 */
public class MyOrderModel implements Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private String OrderNo = "";
	
	private String HotelName = "";
	
	private String Chname = "";
	
	private String Roomtype = "";
	
	private String Money = "";
	
	private String ArrivalDate = "";
	
	private String DepartureDate = "";
	
	private String OrderState = "";
	
	private String PayState = "";
	
	private String LastName = "";
	
	private String FirstName = "";
	
	private String Email = "";
	
	private String Phone = "";
	
	private String OfficePhone = "";
	
	private String Identify = "";
	
	private String Remark = "";
	
	private String ReservationType = "";
	
	public MyOrderModel(JSONObject JSONObject)
	{
		super();
		
		this.OrderNo = JSONObject.optString("OrderNo");
		
		this.HotelName = JSONObject.optString("HotelName");
		
		this.Chname = JSONObject.optString("Chname");
		
		this.Roomtype = JSONObject.optString("Roomtype");
		
		this.Money = JSONObject.optString("Money");
		
		this.ArrivalDate = JSONObject.optString("ArrivalDate");
		
		this.DepartureDate = JSONObject.optString("DepartureDate");
		
		this.OrderState = JSONObject.optString("OrderState");
		
		this.PayState = JSONObject.optString("PayState");
		
		this.LastName = JSONObject.optString("LastName");
		
		this.FirstName = JSONObject.optString("FirstName");
		
		this.Email = JSONObject.optString("Email");
		
		this.Phone = JSONObject.optString("Phone");
		
		this.OfficePhone = JSONObject.optString("OfficePhone");
		
		this.Identify = JSONObject.optString("Identify");
		
		this.Remark = JSONObject.optString("Remark");
		
		this.ReservationType = JSONObject.optString("ReservationType");
		
	}
	
	public String getOrderNo() {
		return OrderNo;
	}
	
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	
	public String getHotelName() {
		return HotelName;
	}
	
	public String getRemark() {
		return Remark;
	}
	
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	public String getPayState() {
		return PayState;
	}
	
	public void setPayState(String payState) {
		PayState = payState;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getPhone() {
		return Phone;
	}
	
	public void setPhone(String phone) {
		Phone = phone;
	}
	
	public String getOfficePhone() {
		return OfficePhone;
	}
	
	public void setOfficePhone(String officePhone) {
		OfficePhone = officePhone;
	}
	
	public String getIdentify() {
		return Identify;
	}
	
	public void setIdentify(String identify) {
		Identify = identify;
	}
	
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	
	public String getChname() {
		return Chname;
	}
	
	public void setChname(String chname) {
		Chname = chname;
	}
	
	public String getRoomtype() {
		return Roomtype;
	}
	
	public String getReservationType() {
		return ReservationType;
	}

	public void setReservationType(String reservationType) {
		ReservationType = reservationType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setRoomtype(String roomtype) {
		Roomtype = roomtype;
	}
	
	public String getMoney() {
		return Money;
	}
	
	public void setMoney(String money) {
		Money = money;
	}
	
	public String getArrivalDate() {
		return ArrivalDate;
	}
	
	public void setArrivalDate(String arrivalDate) {
		ArrivalDate = arrivalDate;
	}
	
	public String getDepartureDate() {
		return DepartureDate;
	}
	
	public void setDepartureDate(String departureDate) {
		DepartureDate = departureDate;
	}
	
	public String getOrderState() {
		return OrderState;
	}
	
	public void setOrderState(String orderState) {
		OrderState = orderState;
	}
	
}
