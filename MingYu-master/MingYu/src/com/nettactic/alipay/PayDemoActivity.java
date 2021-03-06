package com.nettactic.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.nettactic.commerces.R;

public class PayDemoActivity extends FragmentActivity
{
	
	/** 支付宝所需要的Key */
	private static final String PARTNER = "2088111041684369";
	
	private static final String SELLER = "minyoun_zfb@163.com";
	
	private static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAPi5mrFP8caM0TAwhRKlYefkoY5sWkxXQWgGhf4wM+5dBmkJvgXZE0VZgdcuBuKHNQs83XenxS8yWAkU05fPX194n5w+wbkZwJnFWpg+NlxgAUFk/DLwE52ppvPZHTcF6rrWqZTuGT5syje2r3zHCD00bQCTc1So/Uw0bdVLGVBRAgMBAAECgYEA4hBgR3Sv/ZSTtUcETFRAznIBXN+imuxbqF71fnj1Wlr2rpWKPup0F34pAxj0bQQS9CnX6R91m1YYqfhh8QMsXGEj04IqV8vCxClFTvKmYOTcLKZ0XSIpNTiWmlO96OyBzygdI64j8UuX73sXLpdW/KXNqiQXJvjxP1jzbbCx23kCQQD9jrHvVXL79hz5Nd+B3KMXfMNcjOu18etvmWx4vtuxAroHhxwdYhUNC83cdDLeFHRltnB7KUZ8piRrFnqVlCLPAkEA+x797w8xfJjU4lBD+XwQlR/tTsRxvPrFEanO8yLHR+XqeD//lly43F1h/eQ55yP0JIMcQ0uvFtv9OH64EHii3wJBAMHSP2AVMJU8F//EV7scHquImon3ywLFD9S6MfnG44bW779rX2lynWbgnBel13B9sFBD+o2bCp+kHPijMUd/+m8CQQCzmvnxtkMlo0V3TAMh9+sATWJ0GKpkiFLUjEf4u6IeOUHWmadaypxo990zMr17JMrkJmXbd9EVn2fftTo+hZmvAkBXE2do4wDqxfudvaeClIHlILHZYvr1oRY5HOsqeJ6fCm3cMQvuoYvOauQtyGiraZHqxRCxhrg1VAE2YEjTX/vG";
	
	private static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	// public static final String PARTNER = "";
	// public static final String SELLER = "";
	// public static final String RSA_PRIVATE = "";
	// public static final String RSA_PUBLIC = "";
	
	private static final int SDK_PAY_FLAG = 1;
	
	private static final int SDK_CHECK_FLAG = 2;
	
	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg) {
			switch (msg.what)
			{
				case SDK_PAY_FLAG:
				{
					Result resultObj = new Result((String) msg.obj);
					String resultStatus = resultObj.resultStatus;
					
					// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
					if (TextUtils.equals(resultStatus, "9000"))
					{
						Toast.makeText(PayDemoActivity.this, "支付成功",
								Toast.LENGTH_SHORT).show();
					} else
					{
						// 判断resultStatus 为非“9000”则代表可能支付失败
						// “8000”
						// 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
						if (TextUtils.equals(resultStatus, "8000"))
						{
							Toast.makeText(PayDemoActivity.this, "支付结果确认中",
									Toast.LENGTH_SHORT).show();
							
						} else
						{
							Toast.makeText(PayDemoActivity.this, "支付失败",
									Toast.LENGTH_SHORT).show();
							
						}
					}
					break;
				}
				case SDK_CHECK_FLAG:
				{
					Toast.makeText(PayDemoActivity.this, "检查结果为：" + msg.obj,
							Toast.LENGTH_SHORT).show();
					break;
				}
				default:
					break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allhotes);
	}
	
	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 
	 */
	public void pay(View v) {
		String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
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
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(PayDemoActivity.this);
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
	 * check whether the device has authentication alipay account.
	 * 查询终端设备是否存在支付宝认证账户
	 * 
	 */
	public void check(View v) {
		Runnable checkRunnable = new Runnable()
		{
			
			@Override
			public void run() {
				PayTask payTask = new PayTask(PayDemoActivity.this);
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
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String subject, String body, String price) {
		// 合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";
		
		// 卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
		
		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		
		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";
		
		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";
		
		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";
		
		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
				+ "\"";
		
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
	public String getOutTradeNo() {
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
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}
	
	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}
	
}
