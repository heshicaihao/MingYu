package com.nettactic.commerces.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import com.nettactic.commerces.constant.ServerConstant;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.service.exception.EcommerceException;

/**
 * Http网络请求类
 * 
 * @author ruan
 * 
 */
public class DotNetUtil extends GetHttpClient
{
	private static final String TAG = "DoNetUtil";

	/**
	 * Http Get 请求
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public static synchronized String httpGetRequest(String httpUrl)
			throws EcommerceException
	{
		if (MyConfig.ifDebug)
		{
			LogUtil.d(TAG, "+++++httpGetRequest" + httpUrl);
		}
		// HttpGet连接对象
		HttpGet httpRequest = new HttpGet(httpUrl);
		// 取得HttpClient对象
		HttpClient httpclient = getHttpClient();
		int errorCode = 0;
		try
		{
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);

			// 请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				// 取得返回的字符串
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				// mTextView.setText(strResult);
				if (MyConfig.ifDebug)
				{
					LogUtil.d(TAG, "+++++httpPostResult" + httpUrl + "\r\n"
							+ strResult);
				}
				return strResult;
			} else
			{
				errorCode = ServerConstant.ReturnCode.OTHER_ERROR;
				if (MyConfig.ifDebug)
				{
					LogUtil.e(TAG, "##### No Result Return");
				}
				throw new EcommerceException(errorCode);
			}
		} catch (ClientProtocolException e)
		{
			errorCode = ServerConstant.ReturnCode.STATUS_INTENAL_ERROR;
			if (MyConfig.ifDebug)
			{
				LogUtil.e(TAG, "##### ClientProtocolException");
				// e.printStackTrace();
			}

		} catch (IOException e)
		{
			errorCode = ServerConstant.ReturnCode.VALIDATOR_CONNECT_TIMEOUT;
			if (MyConfig.ifDebug)
			{
				LogUtil.e(TAG, "##### IOException");
				// e.printStackTrace();
			}
		}
		throw new EcommerceException(errorCode);
	}

	/**
	 * Http Post请求
	 * 
	 * @return
	 * @throws EcommerceException
	 */
	public static synchronized String httpPostRequest(String httpUrl,
			List<NameValuePair> params, String httpEntityStr)
			throws EcommerceException
	{
		if (MyConfig.ifDebug)
		{
			LogUtil.d(TAG, "请求参数+++++httpPostRequest" + httpUrl + " params : "
					+ params);
		}
		int errorCode = 0;
		try
		{
			// HttpPost连接对象
			HttpPost httpPostRequest = new HttpPost(httpUrl);
			// 设置字符集
			HttpEntity httpEntity = new UrlEncodedFormEntity(params,
					httpEntityStr);
			// 请求httpRequest
			httpPostRequest.setEntity(httpEntity);
			// 取得默认的HttpClient
			HttpClient httpClient = getHttpClient();
			// 取得HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpPostRequest);

			if (MyConfig.ifDebug)
			{
				LogUtil.d(TAG, "++++++HttpStatus"
						+ httpResponse.getStatusLine().getStatusCode());
			}
			// HttpStatus.SC_OK表示连接成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				// 取得返回的字符串
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				if (MyConfig.ifDebug)
				{
					LogUtil.d(TAG, "+++++httpPostResult" + httpUrl + "\r\n"
							+ strResult);
				}
				return strResult;
			} else
			{
				errorCode = ServerConstant.ReturnCode.OTHER_ERROR;
				if (MyConfig.ifDebug)
				{
					LogUtil.e(TAG, "##### No Result Return");
				}
				throw new EcommerceException(errorCode);
			}
		} catch (UnsupportedEncodingException e)
		{

			errorCode = ServerConstant.ReturnCode.STATUS_INTENAL_ERROR;

			if (MyConfig.ifDebug)
			{
				LogUtil.e(TAG, "##### UnsupportedEncodingException");
				// e.printStackTrace();
			}
			throw new EcommerceException(errorCode);
		} catch (ClientProtocolException e)
		{
			errorCode = ServerConstant.ReturnCode.STATUS_INTENAL_ERROR;
			if (MyConfig.ifDebug)
			{
				LogUtil.e(TAG, "##### ClientProtocolException");
				// e.printStackTrace();
			}
		} catch (ParseException e)
		{
			errorCode = ServerConstant.ReturnCode.STATUS_INTENAL_ERROR;
			if (MyConfig.ifDebug)
			{
				LogUtil.e(TAG, "##### ParseException");
				// e.printStackTrace();
			}
		} catch (IOException e)
		{
			errorCode = ServerConstant.ReturnCode.VALIDATOR_CONNECT_TIMEOUT;
			if (MyConfig.ifDebug)
			{
				LogUtil.e(TAG, "##### IOException");
				// e.printStackTrace();
			}
		}
		throw new EcommerceException(errorCode);
	}
}
