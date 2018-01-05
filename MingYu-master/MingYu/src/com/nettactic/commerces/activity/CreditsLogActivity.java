package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.CreditsLogAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.CreditsModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 积分记录
 * 
 * @author ruan
 * 
 */
@SuppressLint("CutPasteId")
public class CreditsLogActivity extends BaseActivity implements ReqResultImpl
{
	private TextView tv_Total, tv_Gift;
	
	private ListView lv_Credits;
	
	private List<CreditsModel> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creditslog);
		setTitle("消费记录");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initData();
	}
	
	private void initView()
	{
		
		tv_Total = (TextView) findViewById(R.id.cl_Total);
		tv_Gift = (TextView) findViewById(R.id.cl_Gift);
		lv_Credits = (ListView) findViewById(R.id.cl_ListView);
		
		tv_Gift.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				Intent intent = new Intent(CreditsLogActivity.this,
						GiftShopActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	private void initData()
	{
		UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
		tv_Total.setText(userInfoModel.getTotlePoints());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("CardNo", userInfoModel.getCardNo()));
		new LoadingAsync(this, RequestMethod.METHOD_GETEXPENSERECORD, params)
				.execute();
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		JSONObject resultData;
		try
		{
			resultData = jsonResult.getJSONObject(Constant.JSON_DATA);
			JSONArray creditsArray = resultData.getJSONArray("list");
			if (creditsArray.length() > 0)
			{
				CreditsModel creditsModel;
				list = new ArrayList<CreditsModel>();
				for (int i = 0; i < creditsArray.length(); i++)
				{
					creditsModel = new CreditsModel(
							creditsArray.getJSONObject(i));
					list.add(creditsModel);
				}
				lv_Credits.setAdapter(new CreditsLogAdapter(this, list));
			} else
			{
				alert("当前没有消费记录");
			}
			
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
		alert("当当前没有消费记录");
		// goToErrorPage(errorCode);
		// finish();
		
	}
	
}
