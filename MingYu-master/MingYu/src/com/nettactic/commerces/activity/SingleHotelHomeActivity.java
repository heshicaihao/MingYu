package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.SingleHotelModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.exception.EcommerceException;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.widget.DialogTool;

/**
 * 单体酒店首页
 * 
 */
public class SingleHotelHomeActivity extends BaseActivity implements
		ReqResultImpl
{
	
	private static final String TAG = "SingelHotelHomeActivity";
	public static final String TITLECOLOR = "titlecolor";
	public static final String COMMENT = "Comment";
	public static String hotelCode = "";
	private Button btn_Order;
	
	private TextView tv_Name;
	private TextView tv_Addr;
	private TextView tv_Tel;
	private TextView tv_Room;
	private TextView tv_Dinner;
	private TextView tv_Metting;
	private TextView tv_Local;
	private TextView tv_Special;
	private TextView tv_Comment;
	
	private SingleHotelModel singleHotelModel;
	
	private int[] color = new int[2];
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_singlehotel_home);
		setTitle("");
		setleftIcon(R.drawable.t_backarr);
		initView();
		initData();
		
	}
	
	@Override
	protected void onDestroy()
	{
		hotelCode = "";
		super.onDestroy();
	}
	
	private void initView()
	{
		btn_Order = (Button) findViewById(R.id.single_Order);
		tv_Name = (TextView) findViewById(R.id.single_hotelname);
		tv_Addr = (TextView) findViewById(R.id.single_addr);
		tv_Tel = (TextView) findViewById(R.id.single_tel);
		tv_Room = (TextView) findViewById(R.id.single_roomlist);
		tv_Dinner = (TextView) findViewById(R.id.single_dinner);
		tv_Metting = (TextView) findViewById(R.id.single_metting);
		tv_Local = (TextView) findViewById(R.id.single_local);
		tv_Special = (TextView) findViewById(R.id.single_Special_offers);
		tv_Comment = (TextView) findViewById(R.id.single_comment);
		
		btn_Order.setOnClickListener(this);
		tv_Room.setOnClickListener(this);
		tv_Dinner.setOnClickListener(this);
		tv_Metting.setOnClickListener(this);
		tv_Local.setOnClickListener(this);
		tv_Special.setOnClickListener(this);
		tv_Comment.setOnClickListener(this);
	}
	
	private void initData()
	{
		Intent intent = getIntent();
		if (intent.hasExtra("HotelCode"))
		{
			hotelCode = intent.getStringExtra("HotelCode");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("HotelCode", hotelCode));
			new LoadingAsync(this, RequestMethod.METHOD_SINGELHOTELHOME, params)
					.execute();
		} else
		{
			alert("数据有误，请重试");
		}
	}
	
	private void getColor(String title)
	{
		// <!-- 明宇豪雅：header：#373f42 botton：#c7835c -->
		//
		//
		// <!-- 宇豪罗曼：header：#23444e botton：#9f8615 -->
		//
		//
		// <!-- 明宇丽雅：header：#196c92 botton：#db6034 -->
		//
		//
		// <!-- 明宇尚雅：header：#94aa41 botton：#d5ad8d -->
		
		if (title.contains("豪雅"))
		{
			color[0] = R.color.HYHead;
			color[1] = R.color.HYButton;
			
		} else if (title.contains("罗曼"))
		{
			color[0] = R.color.LMHead;
			color[1] = R.color.LMButton;
		} else if (title.contains("丽雅"))
		{
			color[0] = R.color.LYHead;
			color[1] = R.color.LYButton;
		} else if (title.contains("尚雅"))
		{
			color[0] = R.color.SYHead;
			color[1] = R.color.SYButton;
		}
		
	}
	
	private void initDate2View()
	{
		setTitle(singleHotelModel.getHotelName());
		
		getColor(singleHotelModel.getHotelName());
		if (0 != color[0] && 0 != color[1])
		{
			setTitleColor(color[0]);
			btn_Order.setBackgroundColor(getResources().getColor(color[1]));
		}
		tv_Name.setText(singleHotelModel.getHotelName());
		tv_Addr.setText(singleHotelModel.getHotelAddress());
		tv_Tel.setText(singleHotelModel.getHotelTel());
	}
	
	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.single_Order)
		{
			Intent intent = new Intent(this, OnlineOrderActivity.class);
			startActivity(intent);
			
		} else if (v.getId() == R.id.single_roomlist)
		{
			Intent intent = new Intent(this, SingleRoomListActivity.class);
			if (0 != color[0] && 0 != color[1])
			{
				intent.putExtra(TITLECOLOR, color);
			}
			intent.putExtra("HotelName", singleHotelModel.getHotelName());
			intent.putExtra("HotelTel", singleHotelModel.getHotelTel());
			intent.putExtra("HotelAddr", singleHotelModel.getHotelAddress());
			
			startActivity(intent);
			
		} else if (v.getId() == R.id.single_dinner)
		{
			Intent intent = new Intent(this, SpecialOffersActivity.class);
			if (0 != color[0] && 0 != color[1])
			{
				intent.putExtra(TITLECOLOR, color);
			}
			intent.putExtra(SpecialOffersActivity.FORMSINGLE, true);
			startActivity(intent);
		} else if (v.getId() == R.id.single_metting)
		{
			Intent intent = new Intent(this,
					Single_MettingWedding_Loc_Activity.class);
			if (0 != color[0] && 0 != color[1])
			{
				intent.putExtra(TITLECOLOR, color);
			}
			intent.putExtra(Single_MettingWedding_Loc_Activity.METTING, "");
			intent.putExtra("HotelName", singleHotelModel.getHotelName());
			intent.putExtra("HotelTel", singleHotelModel.getHotelTel());
			intent.putExtra("HotelAddr", singleHotelModel.getHotelAddress());
			startActivity(intent);
			
		} else if (v.getId() == R.id.single_local)
		{
			Intent intent = new Intent(this,
					Single_MettingWedding_Loc_Activity.class);
			if (0 != color[0] && 0 != color[1])
			{
				intent.putExtra(TITLECOLOR, color);
			}
			intent.putExtra(Single_MettingWedding_Loc_Activity.LOCAL, "");
			intent.putExtra("HotelName", singleHotelModel.getHotelName());
			intent.putExtra("HotelTel", singleHotelModel.getHotelTel());
			intent.putExtra("HotelAddr", singleHotelModel.getHotelAddress());
			startActivity(intent);
			
		} else if (v.getId() == R.id.single_Special_offers)
		{
			Intent intent = new Intent(this, SpecialOffersActivity.class);
			intent.putExtra(TITLECOLOR, color);
			startActivity(intent);
			
		} else if (v.getId() == R.id.single_comment)
		{
			if (ActivityUtil.isEmpty(singleHotelModel.getCommentUrl()))
			{
				alert("该酒店暂不支持点评功能，敬请期待");
			} else
			{
				Intent intent = new Intent(this,
						SpecialOffersDetailActivity.class);
				if (0 != color[0] && 0 != color[1])
				{
					intent.putExtra(TITLECOLOR, color);
				}
				intent.putExtra(COMMENT, "");
				intent.putExtra(SpecialOffersDetailActivity.TITLE, "点评");
				intent.putExtra(SpecialOffersDetailActivity.CONTENTURL,
						singleHotelModel.getCommentUrl());
				startActivity(intent);
				
			}
		}
		super.onClick(v);
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		try
		{
			JSONObject date = jsonResult.getJSONObject(Constant.JSON_DATA);
			singleHotelModel = new SingleHotelModel(date);
			initDate2View();
		} catch (JSONException e)
		{
			alert("获取数据异常，请重试。");
		} catch (EcommerceException e)
		{
			alert("获取数据异常，请重试。");
		}
		Log.d(TAG, "reqResultSuccess" + jsonResult.toString());
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("对不起，该酒店暂无更多信息");
		
	}
}
