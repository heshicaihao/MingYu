package com.nettactic.commerces.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.SetInfoImpl;
import com.nettactic.commerces.model.MyGiftModel;
import com.nettactic.commerces.model.MyOrderModel;
import com.nettactic.commerces.model.UserInfoModel;

/**
 * 我的订单页面适配器
 * 
 * @author ruan
 * 
 */
public class MyGiftAdapter extends BaseAdapter
{
	private Activity activity;
	
	private List<MyGiftModel> list;
	
	private LayoutInflater infalter;
	
	public MyGiftAdapter(Activity activity, List<MyGiftModel> list)
	{
		super();
		this.activity = activity;
		this.list = list;
		infalter = LayoutInflater.from(activity);
	}
	
	@Override
	public int getCount()
	{
		return 0 != list.size() ? list.size() : 0;
	}
	
	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2)
	{
		contentView = infalter.inflate(R.layout.item_mygift, null);
		
		final MyGiftModel myOrderModel = list.get(arg0);
		
		TextView tv_HotelName = (TextView) contentView
				.findViewById(R.id.mo_HotelName);
		
		TextView tv_Roomtype = (TextView) contentView
				.findViewById(R.id.mo_Roomtype);
		
		TextView tv_ArrivalDate = (TextView) contentView
				.findViewById(R.id.mo_ArrivalDate);
		
		TextView tv_OrderState = (TextView) contentView
				.findViewById(R.id.mo_OrderState);
		
		TextView tv_TimeState = (TextView) contentView
				.findViewById(R.id.TimeState);
		
		tv_HotelName.setText(myOrderModel.getGiftname());
		
		tv_Roomtype.setText(myOrderModel.getCode());
		
		tv_ArrivalDate.setText(myOrderModel.getDate());
		
		tv_OrderState.setText(myOrderModel.getPoint());
		tv_TimeState.setText(myOrderModel.getOrderstatus());
		
		return contentView;
	}
}
