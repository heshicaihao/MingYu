package com.nettactic.commerces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.GiftClassAdapter;
import com.nettactic.commerces.adapter.PointRangeAdapter;
import com.nettactic.commerces.util.ActivityUtil;

public class GiftClassActivity extends BaseActivity
{
	public static final int GIFTCLASSCODE = 2005;
	public static final String GIFTCLASSKRY = "giftclasskry";
	private ListView listView;
	private static int selectedPointID = 0;
	
	private final String[] menus =
	{ "热门礼品", "推荐礼品", "商务旅行", "时尚生活", "明宇酒店产品", "会员礼券", "数码家电 ", "明宇订制礼品",
			"杂志期刊" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// 和城市选择公用一个layout
		setContentView(R.layout.activity_choosecity);
		setTitle("礼品分类");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
	}
	
	private void initView()
	{
		
		listView = (ListView) findViewById(R.id.ce_listview);
		listView.setOnItemClickListener(onItemClickListener);
		
		GiftClassAdapter cia = new GiftClassAdapter(this, menus);
		listView.setAdapter(cia);
		selectedPointID = getIntent().getIntExtra(GIFTCLASSKRY, 0);
		cia.setSelectedCity(selectedPointID);
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener()
	{
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id)
		{
			selectedPointID = position;
			
			setBackIntent(selectedPointID);
			
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (KeyEvent.KEYCODE_BACK == keyCode)
		{
			setBackIntent(selectedPointID);
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void setBackIntent(int pointID)
	{
		
		Intent intent = new Intent();
		intent.putExtra(GIFTCLASSKRY, pointID);
		setResult(GIFTCLASSCODE, intent);
		finish();
	}
	
}
