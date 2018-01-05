/**
 * 
 */
package com.nettactic.commerces.util;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import com.nettactic.commerces.control.MyConfig;

/**
 * 关于网络请求的配置 如：请求超时的时间，数据返回超时时间
 * 
 */
public class GetHttpClient
{
	public static HttpClient getHttpClient()
	{
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams,
				MyConfig.REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, MyConfig.SO_TIMEOUT);
		HttpClient client = new DefaultHttpClient(httpParams);
		return client;
	}
}
