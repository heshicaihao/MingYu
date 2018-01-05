package com.nettactic.commerces.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.model.CreditsModel;

public class CreditsLogAdapter extends BaseAdapter
{
	private Context ctx;
	
	private List<CreditsModel> list;
	
	public CreditsLogAdapter(Context ctx, List<CreditsModel> list)
	{
		super();
		this.ctx = ctx;
		this.list = list;
	}
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return list.size() > 0 ? list.size() : 0;
	}
	
	@Override
	public Object getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return list.get(arg0);
	}
	
	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return arg0;
	}
	
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2)
	{
		CreditsModel creditsModel = list.get(arg0);
		convertView = LayoutInflater.from(ctx).inflate(
				R.layout.item_creditslogadapter, null);
		
		TextView ArrivalDate = (TextView) convertView
				.findViewById(R.id.ic_ArrivalDate);
		
		TextView DepartureDate = (TextView) convertView
				.findViewById(R.id.ic_DepartureDate);
		
		TextView BookerCardType = (TextView) convertView
				.findViewById(R.id.ic_BookerCardType);
		
		TextView TotalRev = (TextView) convertView
				.findViewById(R.id.ic_TotalRev);
		
		TextView FbRev = (TextView) convertView.findViewById(R.id.ic_FbRev);
		
		TextView OtherRev = (TextView) convertView
				.findViewById(R.id.ic_OtherRev);
		
		TextView Points = (TextView) convertView.findViewById(R.id.ic_Points);
		
		TextView Nights = (TextView) convertView.findViewById(R.id.ic_Nights);
		ArrivalDate.setText(creditsModel.getArrivalDate());
		
		DepartureDate.setText(creditsModel.getDepartureDate());
		
		if ("0".equals(creditsModel.getBookerCardType()))
		{
			
			BookerCardType.setText("喜悦卡");
		} else if ("1".equals(creditsModel.getBookerCardType()))
		{
			
			BookerCardType.setText("诚悦卡");
		} else if ("2".equals(creditsModel.getBookerCardType()))
		{
			BookerCardType.setText("优悦卡");
			
		} else if ("3".equals(creditsModel.getBookerCardType()))
		{
			
			BookerCardType.setText("尊悦卡");
		}
		
		TotalRev.setText(creditsModel.getTotalRev());
		
		FbRev.setText(creditsModel.getFbRev());
		
		OtherRev.setText(creditsModel.getOtherRev());
		
		Points.setText(creditsModel.getPoints());
		
		Nights.setText(creditsModel.getNights());
		return convertView;
	}
	
}
