package com.nettactic.commerces.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.util.ActivityUtil;

/**
 * 更多酒店介绍
 * 
 */
public class MoreHotelIntroActivity extends BaseActivity implements
		OnClickListener
{
	private static final String TAG = "MoreHotelIntroActivity";
	
	private TextView tv_Intro, tv_Dinner, tv_Meet, tv_local, tv_DinnerCon,
			tv_MeetCon, tv_localCon;
	private OrderModel orderModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_morehotelintro);
		initView();
		initData();
	}
	
	private void initView() {
		
		tv_Intro = (TextView) findViewById(R.id.intro);
		tv_Dinner = (TextView) findViewById(R.id.dinner);
		tv_Meet = (TextView) findViewById(R.id.metting);
		tv_local = (TextView) findViewById(R.id.around);
		tv_DinnerCon = (TextView) findViewById(R.id.dinner_con);
		tv_MeetCon = (TextView) findViewById(R.id.metting_con);
		tv_localCon = (TextView) findViewById(R.id.around_con);
		
		tv_local.setOnClickListener(this);
		tv_Dinner.setOnClickListener(this);
		tv_Meet.setOnClickListener(this);
	}
	
	private void initData() {
		orderModel = OrderModel.getOrderModel();
		tv_Intro.setText(Html.fromHtml(orderModel.getHotelIntro()));
		tv_DinnerCon.setText(Html.fromHtml(orderModel.getDinner()));
		tv_MeetCon.setText(Html.fromHtml(orderModel.getWedding()));
		tv_localCon.setText(Html.fromHtml(orderModel.getNearBy()));
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.dinner)
		{
			if (tv_DinnerCon.getVisibility() == View.VISIBLE)
			{
				tv_DinnerCon.setVisibility(View.GONE);
			} else
			{
				if (ActivityUtil.isEmpty(orderModel.getDinner()))
				{
					alert("暂无更多信息，敬请期待");
				} else
				{
					tv_DinnerCon.setVisibility(View.VISIBLE);
				}
			}
			
		} else if (v.getId() == R.id.metting)
		{
			if (tv_MeetCon.getVisibility() == View.VISIBLE)
			{
				tv_MeetCon.setVisibility(View.GONE);
			} else
			{
				if (ActivityUtil.isEmpty(orderModel.getWedding()))
				{
					alert("暂无更多信息，敬请期待");
				} else
				{
					tv_MeetCon.setVisibility(View.VISIBLE);
				}
			}
		} else if (v.getId() == R.id.around)
		{
			if (tv_localCon.getVisibility() == View.VISIBLE)
			{
				tv_localCon.setVisibility(View.GONE);
			} else
			{
				if (ActivityUtil.isEmpty(orderModel.getNearBy()))
				{
					alert("暂无更多信息，敬请期待");
				} else
				{
					tv_localCon.setVisibility(View.VISIBLE);
				}
			}
		}
		super.onClick(v);
	}
}
