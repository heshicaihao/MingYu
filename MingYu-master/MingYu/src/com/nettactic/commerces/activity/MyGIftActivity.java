package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.nettactic.alipay.PayHelper;
import com.nettactic.alipay.Result;
import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.MyGiftAdapter;
import com.nettactic.commerces.adapter.MyOrderAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.SetInfoImpl;
import com.nettactic.commerces.model.MyGiftModel;
import com.nettactic.commerces.model.MyOrderModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.exception.EcommerceException;

public class MyGIftActivity extends BaseActivity implements ReqResultImpl
{
	private ListView lv_OrderList;
	
	private MyGiftAdapter myGiftAdapter;
	
	private List<MyGiftModel> list;
	
	private String orderNo = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myorder);
		setTitle("我的兑换");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initData();
	}
	
	private void initView()
	{
		lv_OrderList = (ListView) findViewById(R.id.mo_OrderList);
		
		list = new ArrayList<MyGiftModel>();
		myGiftAdapter = new MyGiftAdapter(this, list);
		lv_OrderList.setAdapter(myGiftAdapter);
	}
	
	/* 加载会员订单 */
	private void initData()
	{
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
		params.add(new BasicNameValuePair("CardNo", userInfoModel.getCardNo()));
		
		new LoadingAsync(this, RequestMethod.METHOD_GETMYCHANGES, params)
				.execute();
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		switch (methodName)
		{
		case METHOD_GETMYCHANGES:
		{
			
			MyGiftModel myGiftModel;
			JSONArray data = jsonResult.optJSONArray(Constant.JSON_DATA);
			if (null != list && list.size() > 0)
			{
				list.clear();
			}
			for (int i = 0; i < data.length(); i++)
			{
				try
				{
					myGiftModel = new MyGiftModel(data.optJSONObject(i));
					list.add(myGiftModel);
				} catch (JSONException e)
				{
					alert("当前没有兑换记录");
					
				} catch (EcommerceException e)
				{
					alert("当前没有兑换记录");
				}
			}
			myGiftAdapter.notifyDataSetChanged();
			
			break;
		}
		
		default:
			break;
		}
		
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
		switch (methodName)
		{
		
		case METHOD_GETMYCHANGES:
		{
			alert("当前没有兑换记录");
			break;
		}
		default:
			break;
		}
		
	}
	
}
