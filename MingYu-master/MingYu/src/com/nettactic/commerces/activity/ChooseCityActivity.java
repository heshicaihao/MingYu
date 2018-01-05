package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.CityItemsAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 选择城市
 * 
 */
public class ChooseCityActivity extends BaseActivity
{
	private ListView listView;
	private List<String> cityItems;
	private int selectedCityID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choosecity);
		setTitle("选择城市");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
	}
	
	private void initView()
	{
		Intent intent = getIntent();
		
		if (intent.getExtras() != null)
		{
			Bundle bundle = intent.getExtras();
			selectedCityID = bundle.getInt(OnlineOrderActivity.SELECTEDCITYID);
			cityItems = bundle.getStringArrayList(OnlineOrderActivity.CITYLIST);
		}
		
		listView = (ListView) findViewById(R.id.ce_listview);
		listView.setOnItemClickListener(onItemClickListener);
		
		CityItemsAdapter cia = new CityItemsAdapter(this, cityItems);
		listView.setAdapter(cia);
		cia.setSelectedCity(selectedCityID);
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener()
	{
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id)
		{
			selectedCityID = position;
			setBackIntent();
			
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (KeyEvent.KEYCODE_BACK == keyCode)
		{
			setBackIntent();
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void setBackIntent()
	{
		Intent intent = new Intent();
		intent.putExtra(OnlineOrderActivity.SELECTEDCITYID, selectedCityID);
		setResult(OnlineOrderActivity.CITYBACKCODE, intent);
		finish();
	}
}
