package com.nettactic.commerces.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.activity.CR_RoomInfoActivity;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.SetInfoImpl;
import com.nettactic.commerces.model.RoomModel;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.util.LogUtil;

/**
 * 房型选择适配器
 * 
 */
public class ChooseRoomAdapter extends MyBaseAdapter
{
	
	private final String[] breakfast = new String[]
	{ "", "单早", "双早" };
	private static final String TAG = "ChooseRoomAdapter";
	
	private List<RoomModel> list = new ArrayList<RoomModel>();
	
	private Context ctx;
	
	private SetInfoImpl setRoomInfoImpl;
	
	public ChooseRoomAdapter(List<RoomModel> list, Context ctx)
	{
		super(ctx);
		this.list = list;
		this.ctx = ctx;
		setRoomInfoImpl = (SetInfoImpl) ctx;
	}
	
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
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		if (MyConfig.ifDebug)
		{
			LogUtil.i(TAG, position + "");
		}
		final RoomModel RM = list.get(position);
		
		convertView = LayoutInflater.from(ctx).inflate(
				R.layout.item_chooseroom, null);
		LinearLayout ll = (LinearLayout) convertView
				.findViewById(R.id.ll_hotel);
		ImageView image = (ImageView) convertView
				.findViewById(R.id.chooseroom_image);
		TextView tv_RoomName = (TextView) convertView
				.findViewById(R.id.chooseroom_name);
		TextView tv_StartPrice = (TextView) convertView
				.findViewById(R.id.chooseroom_startprice);
		TextView tv_break = (TextView) convertView
				.findViewById(R.id.chooseroom_break);
		TextView tv_markprice = (TextView) convertView
				.findViewById(R.id.chooseroom_markprice);
		TextView tv_Finish = (TextView) convertView
				.findViewById(R.id.chooseroom_Finish);
		TextView tv_Type = (TextView) convertView.findViewById(R.id.type);
		Button btn_Order = (Button) convertView
				.findViewById(R.id.chooseroom_order);
		LinearLayout ll_price = (LinearLayout) convertView
				.findViewById(R.id.ll_price);
		
		if (ActivityUtil.isEmpty(RM.getRoomType()))
		{
			
		} else
		{
			tv_Type.setVisibility(View.VISIBLE);
			tv_Type.setText(RM.getRoomType());
			
		}
		
		loadWebImg(image, RM.getRoomTypeImages());
		tv_RoomName.setText(RM.getRoomTypeName());
		tv_StartPrice.setText("￥" + RM.getFirstPrice());
		tv_break.setText(breakfast[RM.getIsBreakfast()]);
		
		if (RM.getMarketPrice().equals("0.00"))
		{
			ll_price.setVisibility(View.GONE);
		} else
		{
			
			tv_markprice.setText("￥" + RM.getMarketPrice());
		}
		if (Boolean.TRUE.equals(RM.isHaveRoom()))
		{
			tv_Finish.setVisibility(View.GONE);
			btn_Order.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					setRoomInfoImpl.setInfo(RM);
					
				}
			});
		} else
		{
			btn_Order.setVisibility(View.GONE);
		}
		
		ll.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				View layout = LayoutInflater.from(ctx).inflate(
						R.layout.dialog_roomtype_intro, null);
				ImageView room = (ImageView) layout.findViewById(R.id.roomImg);
				TextView content = (TextView) layout.findViewById(R.id.content);
				loadWebImg(room, RM.getRoomTypeImages());
				content.setText(Html.fromHtml(RM.getRoomTypeDes()));
				new AlertDialog.Builder(ctx).setView(layout).show();
				
			}
		});
		
		return convertView;
	}
}
