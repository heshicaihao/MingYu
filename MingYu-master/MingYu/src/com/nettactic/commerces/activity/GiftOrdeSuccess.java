package com.nettactic.commerces.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.nettactic.commerces.R;

public class GiftOrdeSuccess extends BaseActivity
{
	private TextView tip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_giftordersuccess);
		setTitle("兑换成功");
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.home);
		
		tip = (TextView) findViewById(R.id.tip);
		int getway = getIntent().getIntExtra("GETWAY", 0);
		if (getway == 0)
		{
			tip.setText("礼品将在二十个工作日内派送到该酒店，请自行前往酒店领取");
		} else if (getway == 1)
		{
			tip.setText("礼品将在二十个工作日内寄出，请注意查收\n查询电话：8628-65251773");
		}
	}
}
