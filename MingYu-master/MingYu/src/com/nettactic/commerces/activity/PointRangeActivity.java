package com.nettactic.commerces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.PointRangeAdapter;
import com.nettactic.commerces.util.ActivityUtil;

public class PointRangeActivity extends BaseActivity
{
	public static final String STR_MINPOINT = "minPoint";
	public static final String STR_MAXPOINT = "maxPoint";
	public static final int POINTFROMCODE = 2003;
	public static final int POINTBACKCODE = 2004;
	
	public static final String POINTKEY = "pointkey";
	
	private ListView listView;
	private static int selectedPointID = 0;
	
	private String[] minPoint;
	private String[] maxPoint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// 和城市选择公用一个layout
		setContentView(R.layout.activity_choosecity);
		setTitle("积分范围");
		setleftIcon(R.drawable.t_backarr);
		
		minPoint = getResources().getStringArray(R.array.minpoint);
		maxPoint = getResources().getStringArray(R.array.maxpoint);
		initView();
	}
	
	private void initView()
	{
		
		listView = (ListView) findViewById(R.id.ce_listview);
		listView.setOnItemClickListener(onItemClickListener);
		
		PointRangeAdapter cia = new PointRangeAdapter(this, minPoint, maxPoint);
		listView.setAdapter(cia);
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
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void setBackIntent(int pointID)
	{
		
		Intent intent = new Intent();
		intent.putExtra(POINTKEY, getPointInfo(pointID));
		setResult(POINTBACKCODE, intent);
		finish();
	}
	
	private String[] getPointInfo(int pointID)
	{
		/*
		 * 0:显示的值 1：min值 2：max值
		 */
		String[] str = new String[3];
		
		if (ActivityUtil.isEmpty(minPoint[pointID])
				&& ActivityUtil.isEmpty(maxPoint[pointID]))
		{
			str[0] = "";
			str[1] = "";
			str[2] = "";
		} else
		{
			str[0] = pointID >= (minPoint.length - 1) ? minPoint[pointID]
					: minPoint[pointID] + " - " + maxPoint[pointID];
			str[1] = minPoint[pointID];
			
			if (ActivityUtil.isEmpty(maxPoint[pointID]))
			{
				str[2] = "";
			} else
			{
				
				str[2] = null != maxPoint[pointID] ? maxPoint[pointID] : "";
			}
		}
		return str;
		
	}
	
	@Override
	protected void onDestroy()
	{
		selectedPointID = 0;
		super.onDestroy();
	}
	
}
