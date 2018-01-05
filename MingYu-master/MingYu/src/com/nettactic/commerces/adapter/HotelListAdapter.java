package com.nettactic.commerces.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.activity.ChooseRoomActivity;
import com.nettactic.commerces.model.HotelListModel;
import com.nettactic.commerces.model.OrderModel;

/**
 * 酒店列表适配器
 */
public class HotelListAdapter extends MyBaseAdapter
{
	
	private Activity activity;
	private List<HotelListModel> HotelListModelList;
	
	private LayoutInflater inflater;
	
	public HotelListAdapter(Activity activity,
			List<HotelListModel> HotelListModelList)
	{
		super(activity);
		this.activity = activity;
		this.HotelListModelList = HotelListModelList;
		inflater = LayoutInflater.from(activity);
	}
	
	@Override
	public int getCount()
	{
		return null != HotelListModelList ? HotelListModelList.size() : 0;
	}
	
	@Override
	public Object getItem(int position)
	{
		return null != HotelListModelList ? HotelListModelList.get(position)
				: null;
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final HotelListModel model = HotelListModelList.get(position);
		
		convertView = inflater.inflate(R.layout.item_hotellist, null);
		ImageView img = (ImageView) convertView.findViewById(R.id.img);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		// TextView tip = (TextView) convertView.findViewById(R.id.tip);
		TextView addr = (TextView) convertView.findViewById(R.id.addr);
		TextView tel = (TextView) convertView.findViewById(R.id.tel);
		TextView price = (TextView) convertView.findViewById(R.id.price);
		TextView price_start = (TextView) convertView
				.findViewById(R.id.price_start);
		TextView order = (TextView) convertView.findViewById(R.id.order);
		
		loadWebImg(img, model.getHotelImg());
		name.setText(model.getHotelName());
		// tip.setText(model.get);
		addr.setText(model.getHotelAddr());
		tel.setText(model.getHotelTel());
		price.setText(model.getLowPrice());
		
		Drawable myImage = activity.getResources().getDrawable(
				R.drawable.picebg);
		myImage.setBounds(0, 0, 70, 30);
		price_start.setCompoundDrawables(null, null, myImage, null);
		
		order.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				OrderModel om = OrderModel.getOrderModel();
				om.setHotelCode(model.getHotelCode());
				double[] d = new double[2];
				d[0] = Double.parseDouble(model.getLatitude());
				d[1] = Double.parseDouble(model.getLongitude());
				om.setLat(d);
				
				Intent intent = new Intent(activity, ChooseRoomActivity.class);
				intent.putExtra("ImageUrl", model.getHotelImg());
				intent.putExtra("HotelName", model.getHotelName());
				intent.putExtra("HotelAddr", model.getHotelAddr());
				intent.putExtra("HotelTel", model.getHotelTel());
				activity.startActivity(intent);
			}
		});
		return convertView;
	}
}
