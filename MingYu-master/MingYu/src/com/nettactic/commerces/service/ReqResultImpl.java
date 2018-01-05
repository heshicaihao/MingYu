package com.nettactic.commerces.service;

import org.json.JSONObject;

import com.nettactic.commerces.constant.Constant.RequestMethod;

/**
 * 服务器返回数据处理接口
 * 
 * 
 * 
 */
public interface ReqResultImpl
{

	/** 正常获取到服务器返回的结果 */
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult);

	/** 非正常获取到服务器返回的结果 */
	public void reqResultFail(RequestMethod methodName, int errorCode);
}
