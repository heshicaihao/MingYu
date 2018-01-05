package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.nettactic.amap.MoreMarkerActivity;
import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.HotelListAdapter;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.BrandModel;
import com.nettactic.commerces.model.HotelListModel;
import com.nettactic.commerces.service.exception.EcommerceException;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.util.LogUtil;

/**
 * 酒店列表
 * 
 */
public class HotelListActivity extends BaseActivity
{
	private static final String TAG = "AllHotelActivity";
	
	private ListView listview;
	
	private List<BrandModel> list;
	
	private HotelListAdapter hotellistAdapter;
	
	private List<HotelListModel> HotelListModelList;
	
	private double[] longitudeList;
	private double[] latitudeList;
	private String[] hotelNameList;
	
	private static final String TITLE = "title";
	private static final String HOTELNAME = "HOTELNAME";
	private static final String LONGITUDELIST = "longitudeList";
	private static final String LATITUDELIST = "latitudeList";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotelist);
		setTitle("");
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.t_mapnavigation);
		initView();
		initData();
	}
	
	@Override
	protected void onResume()
	{
		if (MyConfig.ifDebug)
		{
			LogUtil.w(TAG, "onResume");
		}
		super.onResume();
	}
	
	private void initView()
	{
		
		listview = (ListView) findViewById(R.id.hl_listview);
		list = new ArrayList<BrandModel>();
	}
	
	private void initData()
	{
		
		setTitle(getIntent().getStringExtra(OnlineOrderActivity.SELECTEDCITYID));
		JSONArray result;
		try
		{
			result = new JSONArray(getIntent().getStringExtra(
					OnlineOrderActivity.ROOMLIST));
			
			HotelListModelList = new ArrayList<HotelListModel>();
			HotelListModel hotelListModel;
			
			longitudeList = new double[result.length()];
			latitudeList = new double[result.length()];
			hotelNameList = new String[result.length()];
			for (int i = 0; i < result.length(); i++)
			{
				
				hotelListModel = new HotelListModel(result.optJSONObject(i));
				HotelListModelList.add(hotelListModel);
				
				hotelNameList[i] = hotelListModel.getHotelName();
				if (!ActivityUtil.isEmpty(hotelListModel.getLongitude())
						&& !ActivityUtil.isEmpty(hotelListModel.getLatitude()))
				{
					longitudeList[i] = Double.parseDouble(hotelListModel
							.getLongitude());
					latitudeList[i] = Double.parseDouble(hotelListModel
							.getLatitude());
				}
			}
			hotellistAdapter = new HotelListAdapter(this, HotelListModelList);
			listview.setAdapter(hotellistAdapter);
		} catch (JSONException e1)
		{
			alert("获取数据失败请重试");
			this.finish();
		} catch (NumberFormatException e)
		{
			alert("数据异常，请重试");
			this.finish();
		}
		//
		catch (EcommerceException e)
		{
			alert("获取数据失败请重试");
			this.finish();
		}
	}
	
	@Override
	public void onClick(View v)
	{
		super.onClick(v);
		switch (v.getId())
		{
		case R.id.homeTitle_Right:
		{
			Intent intent = new Intent(this, MoreMarkerActivity.class);
			intent.setAction("com.nettactic.commerce.activity.HotelListActivity");
			intent.putExtra(
					TITLE,
					getIntent().getStringExtra(
							OnlineOrderActivity.SELECTEDCITYID));
			intent.putExtra(HOTELNAME, hotelNameList);
			intent.putExtra(LONGITUDELIST, longitudeList);
			intent.putExtra(LATITUDELIST, latitudeList);
			startActivity(intent);
			break;
		}
		}
	}
}
