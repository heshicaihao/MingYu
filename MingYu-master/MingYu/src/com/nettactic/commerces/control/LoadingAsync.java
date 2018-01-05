package com.nettactic.commerces.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.ServiceControlCenter;
import com.nettactic.commerces.service.exception.EcommerceException;

/**
 * 数据加载 线程控制类
 * 
 * @author ruan
 * 
 */
public final class LoadingAsync extends
		AsyncTask<Void, Void, Map<String, Object>>
{
	private static final String TAG = "LoadingAsync";
	
	private ServiceControlCenter serviceControlCenter;
	
	private ReqResultImpl reqResultImpl;
	
	private RequestMethod methodName;
	
	private List<NameValuePair> requestParams;
	
	private Dialog dialog;
	
	public LoadingAsync(Context context, RequestMethod methodName)
	{
		super();
		this.methodName = methodName;
		reqResultImpl = (ReqResultImpl) context;
		initService(context);
		
	}
	
	public LoadingAsync(Context context, RequestMethod methodName,
			List<NameValuePair> params)
	{
		super();
		this.methodName = methodName;
		this.requestParams = params;
		reqResultImpl = (ReqResultImpl) context;
		initService(context);
	}
	
	private void initService(Context ctx) {
		serviceControlCenter = ServiceControlCenter.getServiceControlCenter();
		dialog = new Dialog(ctx, R.style.Translucent_NoTitle);
		dialog.setContentView(R.layout.progressbar);
		dialog.setCancelable(true);
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		dialog.show();
		
	}
	
	@Override
	protected Map<String, Object> doInBackground(Void... params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject jsonResult = null;
		try
		{
			if (null != requestParams && requestParams.size() > 0)
			{
				jsonResult = serviceControlCenter.getResult(methodName,
						requestParams);
			} else
			{
				jsonResult = serviceControlCenter.getResult(methodName);
				
			}
		} catch (EcommerceException e)
		{
			/* 返回值时捕获到异常 */
			resultMap.put(Constant.JSON_STATUS, e.getErrCode());
			return resultMap;
		}
		
		/* 返回状态正常 */
		resultMap.put(Constant.JSON_DATA, jsonResult);
		
		return resultMap;
	}
	
	@Override
	protected void onPostExecute(Map<String, Object> resultMap) {
		
		/* 如果resultMap中加入了值，则说明程序抛出了异常，此时异常信息在status中存储 */
		if (resultMap.containsKey(Constant.JSON_STATUS))
		{
			reqResultImpl.reqResultFail(methodName, Integer.valueOf(String
					.valueOf(resultMap.get(Constant.JSON_STATUS))));
		} else
		{
			reqResultImpl.reqResultSuccess(methodName,
					(JSONObject) resultMap.get(Constant.JSON_DATA));
		}
		dialog.dismiss();
		super.onPostExecute(resultMap);
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}
	
}
