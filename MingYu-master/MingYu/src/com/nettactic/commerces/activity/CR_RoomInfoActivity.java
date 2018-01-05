package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.ChooseRoomAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.SetInfoImpl;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.model.RoomModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.widget.DialogTool;

/**
 * 房型列表
 * 
 * @author ruan
 * 
 */
public class CR_RoomInfoActivity extends BaseActivity implements ReqResultImpl,
		SetInfoImpl
{
	
	private final String TAG = "CR_RoomInfoActivity";
	
	private ChooseRoomAdapter chooseRoomAdapter = null;
	
	private ListView listView;
	
	private OrderModel orderModel;
	
	private List<RoomModel> roomList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_chooseroomadapter);
		initView();
		initData();
	}
	
	private void initView()
	{
		
		listView = (ListView) findViewById(R.id.chooseRoom_listView);
	}
	
	/**
	 * HotelCode 酒店code Arrival 入住时间 Departure 离店时间
	 * 
	 * Adults 成人数 Children 孩子 ExtraBed 加床数 RoomNum 房间数
	 * 
	 * CustAccount 协议客户预订号 可选 CardNo 登录之后的卡号 可选 cardType cardPassword
	 */
	public void initData()
	{
		
		orderModel = OrderModel.getOrderModel();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("hotelCode", orderModel
				.getHotelCode()));
		params.add(new BasicNameValuePair("Arrival", orderModel
				.getArrivalTime()));
		params.add(new BasicNameValuePair("Departure", orderModel
				.getLeaveTime()));
		// params.add(new BasicNameValuePair("Adults",
		// orderModel.getAdultsNum()));
		// params.add(new BasicNameValuePair("Children", orderModel
		// .getChildrenNum()));
		// params.add(new BasicNameValuePair("ExtraBed", "0"));
		// params.add(new BasicNameValuePair("RoomNum",
		// orderModel.getRoom_num()));
		if (Boolean.TRUE.equals(MyConfig.isLogin))
		{
			/* 自动登录功能 */
			String[] keys = new String[3];
			keys[0] = LoginActivity.CARDTYPE;
			keys[1] = LoginActivity.CARDNO;
			keys[2] = LoginActivity.CARDPASSWORD;
			
			Map<String, String> map = ActivityUtil.getConfig(this, keys);
			
			if (!ActivityUtil.isEmpty(map.get(LoginActivity.CARDTYPE))
					&& !ActivityUtil.isEmpty(map.get(LoginActivity.CARDNO))
					&& !ActivityUtil.isEmpty(map
							.get(LoginActivity.CARDPASSWORD)))
			{
				
				params.add(new BasicNameValuePair("cardNo", map
						.get(LoginActivity.CARDNO)));
				
				params.add(new BasicNameValuePair("cardType", map
						.get(LoginActivity.CARDTYPE)));
				params.add(new BasicNameValuePair("cardPassword", map
						.get(LoginActivity.CARDPASSWORD)));
			}
		} else
		{
		}
		params.add(new BasicNameValuePair("CustAccount", orderModel
				.getCustAccount()));
		
		new LoadingAsync(CR_RoomInfoActivity.this,
				RequestMethod.METHOD_ROOMLIST, params).execute();
		
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener()
	{
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3)
		{
			if (roomList.get(arg2).isIfShowDetail())
			{
				roomList.get(arg2).setIfShowDetail(false);
			} else
			{
				roomList.get(arg2).setIfShowDetail(true);
			}
			chooseRoomAdapter.notifyDataSetChanged();
		}
		
	};
	
	private void tryAgain()
	{
		DialogTool.createNormalDialog(this, "获取房型信息失败，请重试",
				new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						initData();
						
					}
				}).show();
		
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		try
		{
			JSONObject resultData = jsonResult
					.getJSONObject(Constant.JSON_DATA);
			JSONArray roomArray = resultData.getJSONArray("RoomRateDetail");
			
			if (roomArray.length() > 0)
			{
				String str_Type = "";
				RoomModel roomModel;
				roomList = new ArrayList<RoomModel>(roomArray.length());
				for (int i = 0; i < roomArray.length(); i++)
				{
					roomModel = new RoomModel(roomArray.getJSONObject(i));
					if (!roomModel.getRoomType().equals(str_Type))
					{
						
						str_Type = roomModel.getRoomType();
						
					} else
					{
						roomModel.setRoomType("");
					}
					roomList.add(roomModel);
				}
				
				chooseRoomAdapter = new ChooseRoomAdapter(roomList, this);
				listView.setAdapter(chooseRoomAdapter);
				listView.setOnItemClickListener(onItemClickListener);
			} else
			{
				tryAgain();
			}
		} catch (JSONException e)
		{
			tryAgain();
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		tryAgain();
	}
	
	@Override
	public void setInfo(Object object)
	{
		
		RoomModel RM = (RoomModel) object;
		checkRoomInfo(RM);
		
		Intent intent = new Intent(this, FillCheckInInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("OrderModel", orderModel);
		intent.putExtra(TAG, bundle);
		startActivity(intent);
		
	}
	
	private void checkRoomInfo(RoomModel RM)
	{
		if (null != RM.getRoomTypeName())
		{
			orderModel.setRoomTypeName(RM.getRoomTypeName());
		}
		if (null != RM.getRoomTypeCode())
		{
			orderModel.setRoomTypeCode(RM.getRoomTypeCode());
		}
		if (null != RM.getFirstPrice())
		{
			orderModel.setRoomUnitprice(RM.getFirstPrice());
		}
		if (null != RM.getRateCode())
		{
			orderModel.setRateCode(RM.getRateCode());
		}
	}
	
}
