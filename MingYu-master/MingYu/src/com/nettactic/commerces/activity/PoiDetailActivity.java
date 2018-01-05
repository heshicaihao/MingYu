package com.nettactic.commerces.activity;

import android.os.Bundle;

import com.nettactic.commerces.R;

public class PoiDetailActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		setTitle("DetailActivity");
		setleftIcon(R.drawable.home);
		setRightIcon(R.drawable.phone);

	}
}
