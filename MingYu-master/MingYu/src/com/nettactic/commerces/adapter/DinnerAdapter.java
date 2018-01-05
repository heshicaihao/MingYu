package com.nettactic.commerces.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.model.BrandModel;

public class DinnerAdapter extends MyBaseAdapter
{
	
	private Context context;
	
	private List<BrandModel> list;
	
	private LayoutInflater infalter;
	
	public DinnerAdapter(Context context, List<BrandModel> list)
	{
		super(context);
		this.context = context;
		this.list = list;
		infalter = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount()
	{
		return list.size();
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
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		BrandModel brandModel = list.get(position);
		convertView = infalter.inflate(R.layout.item_dinner, null);
		
		ImageView WIV = (ImageView) convertView.findViewById(R.id.ce_img);
		TextView tv_Content = (TextView) convertView
				.findViewById(R.id.ce_content);
		loadWebImg(WIV, brandModel.getBrand_LogUrl());
		tv_Content.setText(brandModel.getBrand_Introl());
		return convertView;
	}
	
}
