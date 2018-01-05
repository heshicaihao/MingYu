package com.nettactic.commerces.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.SetInfoImpl;
import com.nettactic.commerces.model.MyOrderModel;
import com.nettactic.commerces.model.UserInfoModel;

/**
 * 我的订单页面适配器
 * 
 * @author ruan
 * 
 */
public class MyOrderAdapter extends BaseAdapter
{
	private Activity activity;
	
	private List<MyOrderModel> list;
	
	private LayoutInflater infalter;
	
	private SetInfoImpl setInfoImpl;
	
	public MyOrderAdapter(Activity activity, List<MyOrderModel> list)
	{
		super();
		this.activity = activity;
		this.setInfoImpl = (SetInfoImpl) activity;
		this.list = list;
		infalter = LayoutInflater.from(activity);
	}
	
	@Override
	public int getCount()
	{
		return 0 != list.size() ? list.size() : 0;
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
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2)
	{
		contentView = infalter.inflate(R.layout.item_myorder, null);
		
		final MyOrderModel myOrderModel = list.get(arg0);
		
		TextView tv_HotelName = (TextView) contentView
				.findViewById(R.id.mo_HotelName);
		
		TextView tv_Roomtype = (TextView) contentView
				.findViewById(R.id.mo_Roomtype);
		
		TextView tv_Money = (TextView) contentView.findViewById(R.id.mo_Money);
		
		TextView tv_ArrivalDate = (TextView) contentView
				.findViewById(R.id.mo_ArrivalDate);
		
		TextView tv_DepartureDate = (TextView) contentView
				.findViewById(R.id.mo_DepartureDate);
		
		TextView tv_OrderState = (TextView) contentView
				.findViewById(R.id.mo_OrderState);
		
		TextView tv_TimeState = (TextView) contentView
				.findViewById(R.id.TimeState);
		
		LinearLayout ll = (LinearLayout) contentView
				.findViewById(R.id.ll_timestate);
		
		Button btn_Cancel = (Button) contentView.findViewById(R.id.mo_Cancel);
		Button btn_CancelandBack = (Button) contentView
				.findViewById(R.id.mo_CancelandBack);
		Button btn_pay = (Button) contentView.findViewById(R.id.pay);
		
		tv_HotelName.setText(myOrderModel.getHotelName());
		
		tv_Roomtype.setText(myOrderModel.getRoomtype());
		
		tv_Money.setText(myOrderModel.getMoney());
		
		tv_ArrivalDate.setText(myOrderModel.getArrivalDate());
		
		tv_DepartureDate.setText(myOrderModel.getDepartureDate());
		if (myOrderModel.getReservationType().equals("1"))
		{
			tv_OrderState.setText("到店支付");
			
		} else if (myOrderModel.getReservationType().equals("11"))
		{
			tv_OrderState.setText("支付宝支付");
		}
		
		if (myOrderModel.getOrderState().contains("已经取消"))
		{
			tv_TimeState.setText("订单已关闭");
		} else
		{
			/** 已经预定，已经支付，受理中 ,已经取消 */
			String state = myOrderModel.getOrderState();
			tv_TimeState.setText(state);
			
			if (state.contains("受理中"))
			{
				
			} else
			{
				
				if (myOrderModel.getReservationType().equals("1"))
				{
					
					btn_Cancel.setVisibility(View.VISIBLE);
					btn_Cancel.setOnClickListener(new OnClickListener()
					{
						
						@Override
						public void onClick(View v)
						{
							List<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("OrderNo",
									myOrderModel.getOrderNo()));
							
							if (MyConfig.isLogin)
							{
								/* 取消会员订单 */
								UserInfoModel userInfoModel = UserInfoModel
										.getUserInfoModel();
								params.add(new BasicNameValuePair("CardNo",
										userInfoModel.getCardNo()));
								new LoadingAsync(activity,
										RequestMethod.METHOD_CANCELMYORDER,
										params).execute();
							} else
							{
								/* 取消闪客订单 */
								new LoadingAsync(activity,
										RequestMethod.METHOD_CANCELORDER,
										params).execute();
							}
						}
					});
					
				} else if (myOrderModel.getReservationType().equals("11"))
				{
					
					if (myOrderModel.getRemark().contains("订单已付款")
							&& myOrderModel.getRemark().contains("支付宝流水号"))
					{
						btn_CancelandBack.setVisibility(View.VISIBLE);
						btn_CancelandBack
								.setOnClickListener(new OnClickListener()
								{
									
									@Override
									public void onClick(View arg0)
									{
										List<NameValuePair> params = new ArrayList<NameValuePair>();
										params.add(new BasicNameValuePair(
												"orderno", myOrderModel
														.getOrderNo()));
										params.add(new BasicNameValuePair(
												"email", myOrderModel
														.getEmail()));
										new LoadingAsync(activity,
												RequestMethod.METHOD_BACKPAY,
												params).execute();
										
									}
								});
					} else
					{
						btn_pay.setVisibility(View.VISIBLE);
						btn_Cancel.setVisibility(View.VISIBLE);
						
						btn_Cancel.setOnClickListener(new OnClickListener()
						{
							
							@Override
							public void onClick(View v)
							{
								List<NameValuePair> params = new ArrayList<NameValuePair>();
								params.add(new BasicNameValuePair("OrderNo",
										myOrderModel.getOrderNo()));
								
								if (MyConfig.isLogin)
								{
									/* 取消会员订单 */
									UserInfoModel userInfoModel = UserInfoModel
											.getUserInfoModel();
									params.add(new BasicNameValuePair("CardNo",
											userInfoModel.getCardNo()));
									new LoadingAsync(activity,
											RequestMethod.METHOD_CANCELMYORDER,
											params).execute();
								} else
								{
									/* 取消闪客订单 */
									new LoadingAsync(activity,
											RequestMethod.METHOD_CANCELORDER,
											params).execute();
								}
							}
						});
						
						btn_pay.setOnClickListener(new OnClickListener()
						{
							
							@Override
							public void onClick(View arg0)
							{
								setInfoImpl.setInfo(myOrderModel);
								
							}
						});
					}
				}
			}
		}
		return contentView;
	}
}
