package com.nettactic.commerces.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.activity.FillReceiveInfo;
import com.nettactic.commerces.activity.GiftDetailActivity;
import com.nettactic.commerces.model.GiftModel;

public class GiftListAdapter extends MyBaseAdapter
{
	private Activity activity;
	
	private List<GiftModel> list;
	
	private LayoutInflater infalter;
	
	public GiftListAdapter(Activity activity, List<GiftModel> list)
	{
		super(activity);
		this.activity = activity;
		this.list = list;
		infalter = LayoutInflater.from(activity);
	}
	
	@Override
	public int getCount() {
		return 0 != list.size() ? list.size() : 0;
	}
	
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final GiftModel giftModel = list.get(position);
		
		LinearLayout linearLayout = (LinearLayout) infalter.inflate(
				R.layout.item_gift, null);
		ImageView img = (ImageView) linearLayout.findViewById(R.id.gs_img);
		TextView name = (TextView) linearLayout.findViewById(R.id.gs_Name);
		TextView code = (TextView) linearLayout.findViewById(R.id.gs_code);
		Button check = (Button) linearLayout.findViewById(R.id.gs_check);
		
		// 加载网络图片
		loadWebImg(img, giftModel.getGift_Image());
		
		name.setText(giftModel.getGift_Name());
		code.setText(giftModel.getGift_Point());
		check.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, GiftDetailActivity.class);
				intent.putExtra(FillReceiveInfo.GIFT, giftModel);
				activity.startActivity(intent);
			}
		});
		
		return linearLayout;
	}
}
