package com.nettactic.commerces.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nettactic.commerces.R;
import com.nettactic.commerces.activity.OnlineOrderActivity;
import com.nettactic.commerces.activity.SingleHotelHomeActivity;
import com.nettactic.commerces.model.BrandModel;
import com.nettactic.commerces.model.HotelModel;
import com.nettactic.commerces.model.OrderModel;

/**
 * 品牌列表适配器
 */
public class BrandlistAdapter extends MyBaseAdapter
{
	
	private Activity activity;
	
	private List<BrandModel> list;
	
	private LayoutInflater inflater;
	
	public BrandlistAdapter(Activity activity, List<BrandModel> list)
	{
		super(activity);
		this.activity = activity;
		this.list = list;
		inflater = LayoutInflater.from(activity);
	}
	
	@Override
	public int getCount()
	{
		return null != list ? list.size() : 0;
	}
	
	@Override
	public Object getItem(int position)
	{
		return null != list ? list.get(position) : null;
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final BrandModel BModel = list.get(position);
		
		convertView = inflater.inflate(R.layout.item_brandlist, null);
		TextView iv_Log = (TextView) convertView
				.findViewById(R.id.hl_list_name);
		TextView tv_desc = (TextView) convertView
				.findViewById(R.id.hl_list_intro);
		ImageView iv_BrandImg = (ImageView) convertView
				.findViewById(R.id.hl_BrandImg);
		LinearLayout ll_List = (LinearLayout) convertView
				.findViewById(R.id.hl_list_layout);
		
		String name = BModel.getBrand_name();
		iv_Log.setText(name);
		loadWebImg(iv_BrandImg, BModel.getBrand_Img());
		
		tv_desc.setText(BModel.getBrand_Introl());
		
		TextView tv_Name = null;
		TextView tv_Order = null;
		/** 遍历出此品牌下的所有酒店显示到页面上 */
		for (final HotelModel hotelModel : BModel.getHotelList())
		{
			
			LinearLayout linearLayout = null;
			if (linearLayout == null)
			{
				linearLayout = (LinearLayout) inflater.inflate(
						R.layout.item_brandllistadapter, null);
				
				tv_Name = (TextView) linearLayout
						.findViewById(R.id.hla_hotelname);
				tv_Order = (TextView) linearLayout.findViewById(R.id.hla_Order);
				ll_List.addView(linearLayout);
				
			}
			tv_Name.setText(hotelModel.getHotelName());
			final String hotecode = hotelModel.getHotelCode();
			tv_Name.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View arg0)
				{
					// Intent intent = new Intent(activity,
					// HotelDetailActivity.class);
					// intent.putExtra("HotelCode", hotelModel.getHotelCode());
					// intent.putExtra("CityCode", hotelModel.getCityCode());
					// activity.startActivity(intent);
					if ("0".equals(hotelModel.getOffer()))
					{
						Toast.makeText(activity, "敬请期待", Toast.LENGTH_LONG)
								.show();
					} else if ("1".equals(hotelModel.getOffer()))
					{
						Intent intent = new Intent(activity,
								SingleHotelHomeActivity.class);
						intent.putExtra("HotelCode", hotecode);
						activity.startActivity(intent);
					}
				}
			});
			if ("0".equals(hotelModel.getOffer()))
			{
				tv_Order.setVisibility(View.INVISIBLE);
				
			} else if ("1".equals(hotelModel.getOffer()))
			{
				tv_Name.setTypeface(null, Typeface.BOLD);
				tv_Name.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.mark, 0);
				tv_Order.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View v)
					{
						OrderModel om = OrderModel.getOrderModel();
						om.clearPrivateData();
						om.setHotelName(hotelModel.getHotelName());
						om.setHotelCode(hotelModel.getHotelCode());
						Intent intent = new Intent(activity,
								OnlineOrderActivity.class);
						intent.setAction(OnlineOrderActivity.FROMBRADLIST);
						activity.startActivity(intent);
					}
				});
			}
		}
		
		return convertView;
	}
}
