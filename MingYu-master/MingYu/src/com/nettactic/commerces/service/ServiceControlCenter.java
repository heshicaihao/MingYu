package com.nettactic.commerces.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.ServerConstant;
import com.nettactic.commerces.constant.Constant.HttpAddress;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.service.exception.EcommerceException;
import com.nettactic.commerces.util.DotNetUtil;

/**
 * 服务器数据请求是的数据中继处理类
 * 
 * @author ruan
 * 
 */
public class ServiceControlCenter
{
	private Integer status;
	
	private static final ServiceControlCenter serviceControlCenter = new ServiceControlCenter();
	
	private static Map<RequestMethod, HttpAddress> actionMapping;
	
	static
	{
		actionMapping = new HashMap<RequestMethod, HttpAddress>();
		
		actionMapping.put(RequestMethod.METHOD_GETAPPBANNER,
				HttpAddress.DOTNET_GETAPPBANNER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETMYCHANGES,
				HttpAddress.DOTNET_GETMYCHANGES_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_LOGIN,
				HttpAddress.DOTNET_LOGIN_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_REGISTER,
				HttpAddress.DOTNET_REGISTER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_CHANGEPASSWORD,
				HttpAddress.DOTNET_CHANGEPASSWORD_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_MODIFYPASSWORD,
				HttpAddress.DOTNET_MODIFYPASSWORD_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_ANGELBEAT,
				HttpAddress.DOTNET_ANGELBEAT_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETDESTINATIONCITY,
				HttpAddress.DOTNET_GETDESTINATIONCITY_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETHOTELS,
				HttpAddress.DOTNET_GETHOTELS_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETALLHOTELS,
				HttpAddress.DOTNET_GETALLHOTELS_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETDINNER,
				HttpAddress.DOTNET_GETDINNER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_SPECIALOFFERS,
				HttpAddress.DOTNET_SPECIALOFFERS_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_ORDERINTERFACE,
				HttpAddress.DOTNET_ORDERINTERFACE_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETGIFTBYKIND,
				HttpAddress.DOTNET_GETGIFTBYKIND_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_CHECKTIME,
				HttpAddress.DOTNET_CHECKTIME_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_HOTELINFOBYHOTELCODE,
				HttpAddress.DOTNET_HOTELINFOBYHOTELCODE_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_ROOMLIST,
				HttpAddress.DOTNET_ROOMLIST_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_MODIFYINFOMARTION,
				HttpAddress.DOTNET_MODIFYINFOMARTION_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_LOGINOUT,
				HttpAddress.DOTNET_LOGINOUT_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETMYORDERLIST,
				HttpAddress.DOTNET_GETMYORDERLIST_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_CANCELMYORDER,
				HttpAddress.DOTNET_CANCELMYORDER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_CANCELORDER,
				HttpAddress.DOTNET_CANCELORDER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETEXPENSERECORD,
				HttpAddress.DOTNET_GETEXPENSERECORD_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETORDERLISTBYEMAIL,
				HttpAddress.DOTNET_GETORDERLISTBYEMAIL_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_FEEDBACK,
				HttpAddress.DOTNET_FEEDBACK_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_MODIFYMYORDER,
				HttpAddress.DOTNET_MODIFYMYORDER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_MODIFYORDER,
				HttpAddress.DOTNET_MODIFYORDER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_FINDPSW,
				HttpAddress.DOTNET_FINDPSW_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GIFTEXCHANGE,
				HttpAddress.DOTNET_GIFTEXCHANGE_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_PAYORDER,
				HttpAddress.DOTNET_PAYORDER_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GUARANTEERULE,
				HttpAddress.DOTNET_GUARANTEERULE_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETHOTELSBYSURROUNDING,
				HttpAddress.DOTNET_GETHOTELSBYSURROUNDING_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_GETNEWSINFOR,
				HttpAddress.DOTNET_GETNEWSINFOR_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_SINGELHOTELHOME,
				HttpAddress.DOTNET_SINGELHOTELHOME_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_HOTELROOMINFO,
				HttpAddress.DOTNET_HOTELROOMINFO_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_HOTELDINNERINFOBYHOTELCODE,
				HttpAddress.DOTNET_HOTELDINNERINFOBYHOTELCODE_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_HOTELMEETINGORLOCAL,
				HttpAddress.DOTNET_HOTELMEETINGORLOCAL_SOAP);
		
		actionMapping.put(RequestMethod.METHOD_BACKPAY,
				HttpAddress.DOTNET_BACKPAY_SOAP);
		
	}
	
	public static ServiceControlCenter getServiceControlCenter()
	{
		return serviceControlCenter;
	}
	
	/**
	 * 流程经过此方法主要是为了接口鉴权
	 * 
	 * @param method
	 * @param list
	 * @return
	 * @throws EcommerceException
	 */
	public JSONObject getResult(RequestMethod method, List<NameValuePair> list)
			throws EcommerceException
	{
		JSONObject json = null;
		EcommerceException e = null;
		try
		{
			json = execute(method, list);
		} catch (EcommerceException ex)
		{
			e = ex;
		}
		
		if (null != e)
		{
			if (ServerConstant.ReturnCode.STATUS_DEV_NO_AUTHORIZED == e
					.getErrCode()
					|| ServerConstant.ReturnCode.STATUS_DEV_AUTHORIZED_TIME_OUT == e
							.getErrCode())
			{
				
				json = execute(method, list);
			} else
			{
				throw e;
			}
		}
		return json;
	}
	
	public JSONObject getResult(RequestMethod method) throws EcommerceException
	{
		JSONObject json = null;
		EcommerceException e = null;
		try
		{
			json = execute(method);
		} catch (EcommerceException ex)
		{
			e = ex;
		}
		
		if (null != e)
		{
			if (ServerConstant.ReturnCode.STATUS_DEV_NO_AUTHORIZED == e
					.getErrCode()
					|| ServerConstant.ReturnCode.STATUS_DEV_AUTHORIZED_TIME_OUT == e
							.getErrCode())
			{
				// DotNetUtil.authenticate();
				json = execute(method);
			} else
			{
				throw e;
			}
		}
		return json;
	}
	
	/**
	 * 执行网络请求，对返回数据进行异常捕获
	 * 
	 * @param method
	 * @param list
	 * @return
	 * @throws EcommerceException
	 */
	@SuppressWarnings("unchecked")
	private JSONObject execute(RequestMethod method, List<NameValuePair> list)
			throws EcommerceException
	{
		
		String result = DotNetUtil.httpPostRequest(actionMapping.get(method)
				.getHttpUrl(), list, "UTF-8");
		
		if (null == result)
		{
			throw new EcommerceException(
					ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
		}
		JSONObject json = null;
		try
		{
			json = new JSONObject(result);
			
		} catch (JSONException e)
		{
			throw new EcommerceException(
					ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
		}
		
		// 判断状态码是否正常返回
		if (!json.isNull(Constant.JSON_STATUS) && json.has(Constant.JSON_DATA))
		{
			try
			{
				status = json.optInt(Constant.JSON_STATUS);
			} catch (Exception e)
			{
				throw new EcommerceException(
						ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
			}
		} else
		{
			throw new EcommerceException(
					ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
		}
		// status = ServerConstant.ReturnCode.getReturnCode(status);
		// switch (status) {
		// case ServerConstant.ReturnCode.STATUS_SUCCESS: {
		// break;
		// }
		// case ServerConstant.ReturnCode.VALIDATOR_USER_NOT_LOGIN:
		// case ServerConstant.ReturnCode.STATUS_NOT_LOGON: {
		// status = ServerConstant.ReturnCode.STATUS_NOT_LOGON;
		// // LoginActivity.clearToken();
		// break;
		// }
		// case ServerConstant.ReturnCode.STATUS_DEV_NO_AUTHORIZED:
		// case ServerConstant.ReturnCode.STATUS_DEV_AUTHORIZED_TIME_OUT: {
		// status = ServerConstant.ReturnCode.STATUS_DEV_NO_AUTHORIZED;
		// break;
		// }
		// case ServerConstant.ReturnCode.STATUS_DEV_NO_PERMISSION: {
		// status = ServerConstant.ReturnCode.STATUS_INTENAL_ERROR;
		// break;
		// }
		// }
		
		// 判断状态码是否是成功的
		if (ServerConstant.ReturnCode.OPERATE_SUCCESS != status)
		{
			throw new EcommerceException(status);
		}
		return json;
		
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject execute(RequestMethod method) throws EcommerceException
	{
		
		String result = DotNetUtil.httpGetRequest(actionMapping.get(method)
				.getHttpUrl());
		
		if (null == result)
		{
			throw new EcommerceException(
					ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
		}
		JSONObject json = null;
		try
		{
			json = new JSONObject(result);
			
		} catch (JSONException e)
		{
			throw new EcommerceException(
					ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
		}
		
		// 判断状态码是否正常返回
		if (!json.isNull(Constant.JSON_STATUS) && json.has(Constant.JSON_DATA))
		{
			try
			{
				status = json.optInt(Constant.JSON_STATUS);
			} catch (Exception e)
			{
				throw new EcommerceException(
						ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
			}
		} else
		{
			throw new EcommerceException(
					ServerConstant.ReturnCode.STATUS_INTENAL_ERROR);
		}
		
		if (ServerConstant.ReturnCode.OPERATE_SUCCESS != status)
		{
			throw new EcommerceException(status);
		}
		return json;
		
	}
}
