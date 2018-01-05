package com.nettactic.commerces.service;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import com.nettactic.commerces.service.exception.EcommerceException;

/**
 * Hotel服务类
 * 
 */
public abstract class MingYuService
{
	/**
	 * 用户登录
	 * 
	 * @throws EcommerceException
	 */
	public JSONObject login(List<NameValuePair> list) throws EcommerceException
	{
		return null;
	}

	/**
	 * 注册
	 * 
	 * @throws EcommerceException
	 */
	public JSONObject register(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;
	};

	/**
	 * 找回密码
	 * 
	 * @throws EcommerceException
	 */
	public JSONObject modifyPassWord(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;
	};

	/**
	 * 修改密码
	 * 
	 * @throws EcommerceException
	 */
	public JSONObject changePassWord(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;
	};

	/**
	 * 心跳
	 * 
	 * @throws EcommerceException
	 */
	public JSONObject angelBeat(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;
	};

	/**
	 * 获取国家列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getCountry() throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取语言列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getLanguage() throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取身份证件类型列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getlIDCardType() throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取省份列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getProvince() throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取目的地酒店列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getHotels() throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取目的地城市列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getDestinationCity() throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取目的地城市列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getRoomTypeList(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取全国酒店列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getAllHotels(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取餐饮娱乐
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getDinner() throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取优惠精选
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject specialOffers() throws EcommerceException
	{
		return null;

	}

	/**
	 * 预订
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject reserveHotel(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;

	}

	/**
	 * 获取礼品商城商品
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getGiftByKind(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;

	}

	/**
	 * 根据HotelCode获取酒店信息
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getHotelInfoByHotelCode(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;

	}

	/**
	 * 根据HotelCode获取酒店房型列表
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getRoomList(List<NameValuePair> list)
			throws EcommerceException
	{
		return null;

	}

}
