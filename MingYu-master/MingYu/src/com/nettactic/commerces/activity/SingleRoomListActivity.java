package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.SingleRoomModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;

public class SingleRoomListActivity extends BaseActivity implements
		ReqResultImpl
{
	private TextView tv_Name;
	private ExpandableListView elv_Rooms;
	private List<SingleRoomModel> singleRoomList;
	private List<String> roomNameList;
	private int[] color;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_roomlist);
		setTitle("");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initDate();
	}
	
	private void initView()
	{
		tv_Name = (TextView) findViewById(R.id.hotelname);
		elv_Rooms = (ExpandableListView) findViewById(R.id.expendlist);
		
		Intent intent = getIntent();
		setTitle(intent.getStringExtra("HotelName"));
		tv_Name.setText(intent.getStringExtra("HotelName"));
		
		color = intent.getIntArrayExtra(SingleHotelHomeActivity.TITLECOLOR);
		setTitleColor(color[0]);
	}
	
	private void initDate()
	{
		if (ActivityUtil.isEmpty(SingleHotelHomeActivity.hotelCode))
		{
			alert("数据有误，请重试");
		} else
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("HotelCode",
					SingleHotelHomeActivity.hotelCode));
			new LoadingAsync(this, RequestMethod.METHOD_HOTELROOMINFO, params)
					.execute();
		}
		
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		try
		{
			String intro = jsonResult.getJSONObject(Constant.JSON_DATA)
					.optString("RoomIntro", "");
			
			JSONArray roomArray = jsonResult.getJSONObject(Constant.JSON_DATA)
					.getJSONArray("Rooms");
			
			singleRoomList = new ArrayList<SingleRoomModel>();
			roomNameList = new ArrayList<String>();
			SingleRoomModel singleRoomModel;
			
			for (int i = 0; i < roomArray.length(); i++)
			{
				singleRoomModel = new SingleRoomModel(
						roomArray.getJSONObject(i));
				roomNameList.add(singleRoomModel.getName());
				singleRoomList.add(singleRoomModel);
			}
			
			if (ActivityUtil.isEmpty(intro))
			{
				
			} else
			{
				roomNameList.add(0, "概述");
				SingleRoomModel introModel = new SingleRoomModel();
				introModel.setName("概述");
				introModel.setIntro(intro);
				singleRoomList.add(0, introModel);
			}
			
			initButtomView();
			elv_Rooms.setAdapter(new MyExpandableListViewAdapter(this));
			
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		// TODO Auto-generated method stub
		
	}
	
	class MyExpandableListViewAdapter extends BaseExpandableListAdapter
	{
		
		private Context context;
		
		public MyExpandableListViewAdapter(Context context)
		{
			this.context = context;
		}
		
		@Override
		public int getGroupCount()
		{
			return roomNameList.size();
		}
		
		@Override
		public int getChildrenCount(int groupPosition)
		{
			return 1;
		}
		
		@Override
		public Object getGroup(int groupPosition)
		{
			return roomNameList.get(groupPosition);
		}
		
		@Override
		public Object getChild(int groupPosition, int childPosition)
		{
			return singleRoomList.get(groupPosition);
		}
		
		@Override
		public long getGroupId(int groupPosition)
		{
			return groupPosition;
		}
		
		@Override
		public long getChildId(int groupPosition, int childPosition)
		{
			return childPosition;
		}
		
		@Override
		public boolean hasStableIds()
		{
			return true;
		}
		
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent)
		{
			convertView = (View) getLayoutInflater().from(context).inflate(
					R.layout.group_item_layout, null);
			TextView txt = (TextView) convertView.findViewById(R.id.room_name);
			
			txt.setText(roomNameList.get(groupPosition));
			return convertView;
		}
		
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent)
		{
			convertView = (View) getLayoutInflater().from(context).inflate(
					R.layout.child_item_layout, null);
			TextView txt = (TextView) convertView.findViewById(R.id.intro);
			ImageView img = (ImageView) convertView.findViewById(R.id.img);
			SingleRoomModel srm = singleRoomList.get(groupPosition);
			txt.setText(Html.fromHtml(srm.getIntro()));
			if (ActivityUtil.isEmpty(srm.getImageUrl()))
			{
				
			} else
			{
				loadWebImg(img, srm.getImageUrl());
			}
			return convertView;
		}
		
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition)
		{
			return true;
		}
		
	}
	
	private void initButtomView()
	{
		Intent intent = getIntent();
		View buttomView = (View) getLayoutInflater().from(this).inflate(
				R.layout.order_buttomlayout, null);
		
		TextView name = (TextView) buttomView
				.findViewById(R.id.single_hotelname);
		TextView tel = (TextView) buttomView.findViewById(R.id.single_tel);
		TextView addr = (TextView) buttomView.findViewById(R.id.single_addr);
		Button btn_order = (Button) buttomView.findViewById(R.id.single_Order);
		
		name.setText(intent.getStringExtra("HotelName"));
		tel.setText(intent.getStringExtra("HotelTel"));
		addr.setText(intent.getStringExtra("HotelAddr"));
		btn_order.setBackgroundColor(color[1]);
		btn_order.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(SingleRoomListActivity.this,
						OnlineOrderActivity.class);
				startActivity(intent);
				
			}
		});
		elv_Rooms.addFooterView(buttomView);
	}
}
