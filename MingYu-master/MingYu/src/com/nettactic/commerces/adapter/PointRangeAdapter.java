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

public class PointRangeAdapter extends MyBaseAdapter
{
	
	private Context context;
	
	private String[] minPoint;
	private String[] maxPoint;
	
	private LayoutInflater infalter;
	
	private int selectedCity = 0;
	
	public PointRangeAdapter(Context context, String[] minPoint,
			String[] maxPoint) {
		super(context);
		this.context = context;
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
		infalter = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount()
	{
		return minPoint.length;
	}
	
	@Override
	public Object getItem(int position)
	{
		return minPoint[position];
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
		String pointRangeDes;
		if (ActivityUtil.isEmpty(minPoint[position])
				&& ActivityUtil.isEmpty(maxPoint[position]))
		{
			pointRangeDes = "不限";
		} else
		{
			pointRangeDes = position >= (minPoint.length - 1) ? minPoint[position]
					: minPoint[position] + " - " + maxPoint[position] + "分";
		}
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
		tv_Content.setText(pointRangeDes);
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
