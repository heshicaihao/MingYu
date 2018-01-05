package com.nettactic.commerces.adapter;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.model.HotelModel;
import com.nettactic.commerces.util.ActivityUtil;

/**
 * 优惠精选页面适配器
 * 
 * 
 * 
 */
public class SpecialOffersAdapter extends MyBaseAdapter
{
	private Context context;
	
	private List<HotelModel> list;
	
	private LayoutInflater inflater;
	
	public SpecialOffersAdapter(Context context, List<HotelModel> list)
	{
		super(context);
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
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
		
		HotelModel hotelModel = list.get(position);
		convertView = inflater.inflate(R.layout.item_specialoffers, null);
		
		TextView tv_Title = (TextView) convertView.findViewById(R.id.ps_title);
		TextView tv_Intro = (TextView) convertView.findViewById(R.id.ps_intro);
		ImageView wv = (ImageView) convertView.findViewById(R.id.ps_img);
		
		tv_Title.setText(ActivityUtil.isEmpty(hotelModel.getHotelTitle()) ? hotelModel
				.getHotelName() : hotelModel.getHotelTitle());
		tv_Intro.setText(Html.fromHtml(hotelModel.getHotelIntro()));
		loadWebImg(wv, hotelModel.getHotelImg());
		// tv_Share.setOnClickListener(new OnClickListener()
		//
		// {
		//
		// @Override
		// public void onClick(View v)
		// {
		// Intent intent = new Intent(Intent.ACTION_SEND);
		// intent.setType("text/plain");
		// intent.putExtra(Intent.EXTRA_TEXT, "psm.getPs_Title()");
		// context.startActivity(Intent.createChooser(intent, "分享酒店"));
		// }
		// });
		return convertView;
	}
}
