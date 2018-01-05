package com.nettactic.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

public class PayHelper
{
	private Activity activity;
	private Handler mHandler;
	
	private static final int SDK_PAY_FLAG = 1;
	
	private static final int SDK_CHECK_FLAG = 2;
	/** 支付宝所需要的Key */
	private final static String PARTNER = "2088111041684369";
	
	private final static String SELLER = "2088111041684369";
	
	private final static String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAPi5mrFP8caM0TAwhRKlYefkoY5sWkxXQWgGhf4wM+5dBmkJvgXZE0VZgdcuBuKHNQs83XenxS8yWAkU05fPX194n5w+wbkZwJnFWpg+NlxgAUFk/DLwE52ppvPZHTcF6rrWqZTuGT5syje2r3zHCD00bQCTc1So/Uw0bdVLGVBRAgMBAAECgYEA4hBgR3Sv/ZSTtUcETFRAznIBXN+imuxbqF71fnj1Wlr2rpWKPup0F34pAxj0bQQS9CnX6R91m1YYqfhh8QMsXGEj04IqV8vCxClFTvKmYOTcLKZ0XSIpNTiWmlO96OyBzygdI64j8UuX73sXLpdW/KXNqiQXJvjxP1jzbbCx23kCQQD9jrHvVXL79hz5Nd+B3KMXfMNcjOu18etvmWx4vtuxAroHhxwdYhUNC83cdDLeFHRltnB7KUZ8piRrFnqVlCLPAkEA+x797w8xfJjU4lBD+XwQlR/tTsRxvPrFEanO8yLHR+XqeD//lly43F1h/eQ55yP0JIMcQ0uvFtv9OH64EHii3wJBAMHSP2AVMJU8F//EV7scHquImon3ywLFD9S6MfnG44bW779rX2lynWbgnBel13B9sFBD+o2bCp+kHPijMUd/+m8CQQCzmvnxtkMlo0V3TAMh9+sATWJ0GKpkiFLUjEf4u6IeOUHWmadaypxo990zMr17JMrkJmXbd9EVn2fftTo+hZmvAkBXE2do4wDqxfudvaeClIHlILHZYvr1oRY5HOsqeJ6fCm3cMQvuoYvOauQtyGiraZHqxRCxhrg1VAE2YEjTX/vG";
	
	private final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	public PayHelper(Activity activity, Handler handler)
	{
		super();
		this.activity = activity;
		this.mHandler = handler;
	}
	
	public void pay(String orderInfo)
	{
		String sign = sign(orderInfo);
		try
		{
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();
		
		Runnable payRunnable = new Runnable()
		{
			
			@Override
			public void run()
			{
				// 构造PayTask 对象
				PayTask alipay = new PayTask(activity);
				// 调用支付接口
				String result = alipay.pay(payInfo);
				
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};
		
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}
	
	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param signType
	 *            签名方式
	 * @param content
	 *            待签名订单信息
	 * @return
	 */
	public static String sign(String signType, String content)
	{
		// return Rsa.sign(content, RSA_PRIVATE);
		return null;
	}
	
	/**
	 * check whether the device has authentication alipay account.
	 * 查询终端设备是否存在支付宝认证账户
	 * 
	 */
	public void check(View v)
	{
		Runnable checkRunnable = new Runnable()
		{
			
			@Override
			public void run()
			{
				PayTask payTask = new PayTask(activity);
				boolean isExist = payTask.checkAccountIfExist();
				
				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};
		
		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();
		
	}
	
	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion()
	{
		PayTask payTask = new PayTask(activity);
		String version = payTask.getVersion();
		Toast.makeText(activity, version, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public static String getOrderInfo(String subject, String body,
			String price, String OrderNum)
	{
		// 合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";
		
		// 卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
		
		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + OrderNum + "\"";
		
		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";
		
		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";
		
		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";
		
		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\""
				+ "http://www.minyounhotels.com/CN/return_url_rsa.aspx" + "\"";
		
		// 接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";
		
		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";
		
		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";
		
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";
		
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";
		
		// 调用银行卡支付，需配置此参数，参与签名， 固定值
		// orderInfo += "&paymethod=\"expressGateway\"";
		
		return orderInfo;
	}
	
	/**
	 * get the out_trade_no for an order. 获取外部订单号
	 * 
	 */
	public static String getOutTradeNo()
	{
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		
		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}
	
	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public static String sign(String content)
	{
		return SignUtils.sign(content, RSA_PRIVATE);
	}
	
	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public static String getSignType()
	{
		return "sign_type=\"RSA\"";
	}
}
