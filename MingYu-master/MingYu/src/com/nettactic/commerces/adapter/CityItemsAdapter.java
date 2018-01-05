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

public class CityItemsAdapter extends MyBaseAdapter
{
	
	private Context context;
	
	private List<String> list;
	
	private LayoutInflater infalter;
	
	private int selectedCity = 0;
	
	public CityItemsAdapter(Context context, List<String> list) {
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
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		String cityName = list.get(position);
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
		tv_Content.setText(cityName);
		return convertView;
	}
	
	public void setSelectedCity(int id)
	{
		selectedCity = id;
		this.notifyDataSetChanged();
	}
}
