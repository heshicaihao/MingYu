package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.amap.OneMarkerActivity;
import com.nettactic.amap.SimpleNaviRouteActivity;
import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.HotelModel;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.model.RoomModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.exception.EcommerceException;
import com.nettactic.commerces.widget.SlideMenu;

/**
 * 选择房型
 * 
 */
public class ChooseRoomActivity extends BaseActivity implements ReqResultImpl

{
	public static final String TAG = "ChooseRoomActivity";
	
	private ImageView iv_HotelLogo;
	
	private TextView tv_Hoteladd, tv_HotelPhone, tv_Login;
	
	private HotelModel hotelModel;
	
	private List<RoomModel> roomList;
	
	private OrderModel orderModel;
	
	private LocalActivityManager mlam;
	
	private LinearLayout linearLayout, ll_addr, ll_tel;
	
	private String[][] latn;
	private String titleName;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooseroom);
		setTitle("");
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.phone);
		
		mlam = new LocalActivityManager(this, false);
		mlam.dispatchCreate(savedInstanceState);
		
		orderModel = OrderModel.getOrderModel();
		
		latn = new String[3][3];
		
		initView();
	}
	
	@Override
	protected void onResume()
	{
		mlam.dispatchResume();
		if (MyConfig.isLogin)
		{
			tv_Login.setVisibility(View.GONE);
		} else
		{
			tv_Login.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View arg0)
				{
					Intent intent = new Intent(ChooseRoomActivity.this,
							LoginActivity.class);
					startActivity(intent);
					
				}
			});
		}
		super.onResume();
	}
	
	@Override
	protected void onPause()
	{
		mlam.dispatchPause(isFinishing());
		super.onPause();
		
	}
	
	private void initView()
	{
		
		linearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
		ll_addr = (LinearLayout) findViewById(R.id.ll_addr);
		ll_tel = (LinearLayout) findViewById(R.id.ll_tel);
		
		iv_HotelLogo = (ImageView) findViewById(R.id.ivHotelLog);
		tv_Hoteladd = (TextView) findViewById(R.id.ivHotelAddr);
		tv_HotelPhone = (TextView) findViewById(R.id.ivHotelTel);
		tv_Login = (TextView) findViewById(R.id.ivHotellogin);
		linearLayout.getBackground().setAlpha(100);
		
		ll_addr.setOnClickListener(this);
		ll_tel.setOnClickListener(this);
		Intent intent = getIntent();
		titleName = intent.getStringExtra("HotelName");
		orderModel.setHotelName(titleName);
		setTitle(titleName);
		loadWebImg(iv_HotelLogo, intent.getStringExtra("ImageUrl"));
		tv_Hoteladd.setText(intent.getStringExtra("HotelAddr"));
		tv_HotelPhone.setText(intent.getStringExtra("HotelTel"));
		
		orderModel.setHotelName(intent.getStringExtra("HotelName"));
		orderModel.setHotelAddr(intent.getStringExtra("HotelAddr"));
		orderModel.setHotelTel(intent.getStringExtra("HotelTel"));
		
		WindowManager wm = this.getWindowManager();
		int height = wm.getDefaultDisplay().getHeight();
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, height / 3);
		iv_HotelLogo.setLayoutParams(layoutParams);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("hotelCode", orderModel
				.getHotelCode()));
		new LoadingAsync(ChooseRoomActivity.this,
				RequestMethod.METHOD_HOTELINFOBYHOTELCODE, params).execute();
	}
	
	private void initData()
	{
		
		String[][] menus =
		{
		{ getResources().getString(R.string.room_price),
				getResources().getString(R.string.more_hotelintro),
				getResources().getString(R.string.hotel_map) } };
		
		List<Class<? extends Activity>> list = new ArrayList<Class<? extends Activity>>()
		{
			private static final long serialVersionUID = 1L;
			{
				add(CR_RoomInfoActivity.class);
				add(MoreHotelIntroActivity.class);
				add(OneMarkerActivity.class);
			}
		};
		
		SlideMenu slideMenu = new SlideMenu(this, mlam, menus, list);
		slideMenu.initScrollMenu();
		
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.homeTitle_Right:
			callMingYu();
			break;
		
		case R.id.ll_addr:
			if (null != orderModel.getLat() && orderModel.getLat().length > 0)
			{
				Intent intent = new Intent(this, SimpleNaviRouteActivity.class);
				intent.setAction("need title bar");
				intent.putExtra("title", titleName);
				startActivity(intent);
			} else
			{
				alert("暂无相关信息");
			}
			break;
		
		case R.id.ll_tel:
			callMingYu();
			break;
		
		default:
			break;
		}
		super.onClick(v);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nettactic.commerce.service.impl.ReqResultImpl#reqResultSuccess(java
	 * .lang.String, org.json.JSONObject)
	 */
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		if (methodName.equals(RequestMethod.METHOD_HOTELINFOBYHOTELCODE))
		{
			try
			{
				
				JSONObject resultData = jsonResult
						.getJSONObject(Constant.JSON_DATA);
				JSONObject hotelInfo = resultData.getJSONObject("HotelInfo");
				
				hotelModel = new HotelModel(hotelInfo, roomList);
				
				/** 加载数据进缓存 */
				orderModel.setHotelImgUrl(hotelModel.getHotelImg());
				orderModel.setHotelIntro(hotelModel.getHotelIntro());
				orderModel.setHotelMapUrl(hotelModel.getMapUrl());
				orderModel.setDinner(hotelModel.getDinner());
				orderModel.setWedding(hotelModel.getWedding());
				orderModel.setNearBy(hotelModel.getNearBy());
				
				initData();
			} catch (JSONException e)
			{
				
				alert("网络异常,请重试");
				
			} catch (EcommerceException e)
			{
				alert("网络异常,请重试");
			}
		} else
		{
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nettactic.commerce.service.impl.ReqResultImpl#reqResultFail(java.
	 * lang.String, int)
	 */
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("网络异常,请重试");
		
	}
	
}
