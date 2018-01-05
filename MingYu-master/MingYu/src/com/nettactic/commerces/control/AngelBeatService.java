package com.nettactic.commerces.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.nettactic.commerces.activity.LoginActivity;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;

/**
 * 天使的心跳Service
 * 
 */
public class AngelBeatService extends Service implements ReqResultImpl
{

	public static final String ACTION = "com.nettactic.commerce.control.AngelBeatService";

	private String cardNum;

	private List<NameValuePair> params;

	private Handler handler;

	private Runnable runnable;

	@Override
	public void onCreate()
	{
		System.out.println("onCreate");

		super.onCreate();
		handler = new Handler();
		cardNum = ActivityUtil.getConfig(this, LoginActivity.CARDNO);
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("CardNo", cardNum));
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		System.out.println("onBind");
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId)
	{

		runnable = new Runnable()
		{
			public void run()
			{
				angelBeat();

				handler.postDelayed(this, MyConfig.ANGELBEAT_TIME);
				// postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
			}
		};

		handler.postDelayed(runnable, 1000); // 开始Timer

		super.onStart(intent, startId);
	}

	private void angelBeat()
	{

		new LoadingAsync(this, RequestMethod.METHOD_ANGELBEAT, params)
				.execute();
	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		// TODO Auto-generated method stub
		System.out.println("onUnbind");
		return super.onUnbind(intent);

	}

	@Override
	public void onDestroy()
	{
		handler.removeCallbacks(runnable);
		System.out.println("onDestory");
		super.onDestroy();
	}

	/**
	 * 1003 没有传CardNo 1004卡号错误 1006 超时 1001成功
	 */
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		System.out.println("jsonResult" + jsonResult);

	}

	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		System.out.println("errorCode" + errorCode);
	}
}