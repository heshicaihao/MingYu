package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.DinnerAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.BrandModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.exception.EcommerceException;

/**
 * 餐饮娱乐
 * 
 */
public class DinnerActivity extends BaseActivity implements ReqResultImpl
{
	private ListView listView;
	
	private List<BrandModel> list = new ArrayList<BrandModel>();
	
	private DinnerAdapter dinnerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dinner);
		setTitle("餐饮美食");
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.home);
		
		initView();
		initData();
		
	}
	
	private void initView()
	{
		listView = (ListView) findViewById(R.id.ce_listview);
		
	}
	
	private void initData()
	{
		list = new ArrayList<BrandModel>();
		dinnerAdapter = new DinnerAdapter(this, list);
		listView.setAdapter(dinnerAdapter);
		
		new LoadingAsync(this, RequestMethod.METHOD_GETDINNER).execute();
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				Intent intent = new Intent(DinnerActivity.this,
						DinnerDetailActivity.class);
				Bundle bundle = new Bundle();
				BrandModel brandModel = list.get(arg2);
				bundle.putSerializable("brandModel", brandModel);
				intent.putExtra("bundle", bundle);
				startActivity(intent);
				
			}
		});
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		try
		{
			JSONArray data = jsonResult.getJSONArray(Constant.JSON_DATA);
			
			BrandModel brandModel;
			for (int i = 0; i < data.length(); i++)
			{
				brandModel = new BrandModel(data.optJSONObject(i));
				list.add(brandModel);
			}
			dinnerAdapter.notifyDataSetChanged();
			
		} catch (JSONException e)
		{
			e.printStackTrace();
		} catch (EcommerceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("网络异常,请重试");
	}
}
