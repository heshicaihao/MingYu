package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.BrandlistAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.BrandModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.exception.EcommerceException;

/**
 * 品牌介绍
 * 
 */
public class BrandIntroActivity extends BaseActivity implements ReqResultImpl
{
	private static final String TAG = "AllHotelActivity";
	
	private ListView listview;
	
	private List<BrandModel> list;
	
	private BrandlistAdapter brandlistAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotelist);
		setTitle("品牌介绍");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initData();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void initView() {
		
		listview = (ListView) findViewById(R.id.hl_listview);
		list = new ArrayList<BrandModel>();
	}
	
	private void initData() {
		
		new LoadingAsync(BrandIntroActivity.this,
				RequestMethod.METHOD_GETALLHOTELS).execute();
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult) {
		try
		{
			JSONArray brandArray = jsonResult.getJSONArray(Constant.JSON_DATA);
			BrandModel brandModel;
			for (int i = 0; i < brandArray.length(); i++)
			{
				brandModel = new BrandModel(brandArray.optJSONObject(i));
				list.add(brandModel);
			}
			
			brandlistAdapter = new BrandlistAdapter(this, list);
			listview.setAdapter(brandlistAdapter);
			
		} catch (JSONException e)
		{
			e.printStackTrace();
		} catch (EcommerceException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode) {
		alert("网络异常,请重试");
		
	}
	
}
