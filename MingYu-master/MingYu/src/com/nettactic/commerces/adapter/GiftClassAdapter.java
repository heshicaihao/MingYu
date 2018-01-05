package com.nettactic.commerces.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.util.ActivityUtil;

public class GiftClassAdapter extends MyBaseAdapter
{
	
	private Context context;
	
	private String[] giftClass;
	
	private LayoutInflater infalter;
	
	private int selectedCity = 0;
	
	public GiftClassAdapter(Context context, String[] giftClass)
	{
		super(context);
		this.context = context;
		this.giftClass = giftClass;
		infalter = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount()
	{
		return giftClass.length;
	}
	
	@Override
	public Object getItem(int position)
	{
		return giftClass[position];
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = infalter.inflate(R.layout.item_cityitems, null);
		ImageView bar = (ImageView) convertView.findViewById(R.id.ci_bar);
		TextView tv_Content = (TextView) convertView
				.findViewById(R.id.ci_CityName);
		if (selectedCity == position)
		{
			bar.setVisibility(View.VISIBLE);
			tv_Content.setCompoundDrawablesWithIntrinsicBounds(null, null,
					context.getResources().getDrawable(R.drawable.t_rightarr),
					null);
		}
		tv_Content.setText(giftClass[position]);
		return convertView;
	}
	
	public void setSelectedCity(int id)
	{
		selectedCity = id;
		this.notifyDataSetChanged();
	}
	
	public String getPointString()
	{
		return null;
		
	}
}
