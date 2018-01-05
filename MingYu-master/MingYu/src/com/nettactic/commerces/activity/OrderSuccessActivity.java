package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nettactic.alipay.PayHelper;
import com.nettactic.alipay.Result;
import com.nettactic.amap.OneMarkerActivity;
import com.nettactic.amap.SimpleNaviRouteActivity;
import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 预订成功
 * 
 * 预订成功订单信息展示页面
 * 
 * @author ruan
 * 
 */
public class OrderSuccessActivity extends FragmentActivity implements
		ReqResultImpl
{
	private static final String TAG = "OrderSuccessActivity";
	
	private TextView tv_OrderID, tv_OrderStatus, tv_OrderTime, tv_TotalPrice,
			tv_HotelName, tv_HotelTel, tv_HotelAddr, tv_Payment, tv_InData,
			tv_OutData, tv_KeepTime, tv_Tip;
	
	private LinearLayout ll_pay, ll_again, ll_map, ll_cancel;
	
	private OrderModel orderModel;
	
	// 0<item>喜悦卡</item>
	private final static String XYK = "0";
	
	// 1<item>诚悦卡</item>
	private final static String CYK = "1";
	
	// 2<item>优悦卡</item>
	private final static String YYK = "2";
	
	// 3<item>尊悦卡</item>
	private final static String ZYK = "3";
	
	private static final int SDK_PAY_FLAG = 1;
	
	private static final int SDK_CHECK_FLAG = 2;
	
	private static final String ALIPAY = "支付宝支付";
	private static final String DESKPAY = "到店支付";
	/** 支付状态 */
	private boolean payStatus = false;
	
	private TextView tv_Title;
	
	private TextView tv_TimeTip;
	
	private ImageView iv_left;
	
	private ImageView iv_right;
	
	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case SDK_PAY_FLAG:
			{
				Result resultObj = new Result((String) msg.obj);
				String resultStatus = resultObj.resultStatus;
				
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000"))
				{
					Toast.makeText(OrderSuccessActivity.this, "支付成功",
							Toast.LENGTH_SHORT).show();
					payStatus = true;
					buttonControl();
					tv_OrderStatus.setText("支付成功");
				} else
				{
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”
					// 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000"))
					{
						Toast.makeText(OrderSuccessActivity.this, "支付结果确认中",
								Toast.LENGTH_SHORT).show();
						tv_OrderStatus.setText("支付结果确认中");
					} else
					{
						Toast.makeText(OrderSuccessActivity.this, "支付失败",
								Toast.LENGTH_SHORT).show();
						
					}
				}
				break;
			}
			case SDK_CHECK_FLAG:
			{
				Toast.makeText(OrderSuccessActivity.this, "检查结果为：" + msg.obj,
						Toast.LENGTH_SHORT).show();
				tv_OrderStatus.setText(msg.obj + "");
				break;
			}
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ordersuccess);
		setTitle("");
		
		initView();
		initData();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		buttonControl();
	}
	
	private void initView()
	{
		tv_Title = (TextView) findViewById(R.id.homeTitle_);
		tv_TimeTip = (TextView) findViewById(R.id.payTimetip);
		iv_left = (ImageView) findViewById(R.id.homeTitle_Left);
		iv_right = (ImageView) findViewById(R.id.homeTitle_Right);
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.phone);
		
		tv_OrderID = (TextView) findViewById(R.id.os_OrderID);
		tv_OrderStatus = (TextView) findViewById(R.id.os_OrderStatus);
		tv_OrderTime = (TextView) findViewById(R.id.os_OrderTime);
		tv_TotalPrice = (TextView) findViewById(R.id.os_TotalPrice);
		tv_HotelName = (TextView) findViewById(R.id.os_HotelName);
		tv_HotelTel = (TextView) findViewById(R.id.os_HotelTel);
		tv_HotelAddr = (TextView) findViewById(R.id.os_HotelAddr);
		tv_Payment = (TextView) findViewById(R.id.os_PayMent);
		tv_InData = (TextView) findViewById(R.id.os_InTime);
		tv_OutData = (TextView) findViewById(R.id.os_OutTime);
		tv_KeepTime = (TextView) findViewById(R.id.os_KeepTime);
		tv_Tip = (TextView) findViewById(R.id.os_Tip);
		
		ll_pay = (LinearLayout) findViewById(R.id.pay);
		ll_again = (LinearLayout) findViewById(R.id.again);
		ll_map = (LinearLayout) findViewById(R.id.map);
		ll_cancel = (LinearLayout) findViewById(R.id.cancelOrder);
		
		ll_pay.setOnClickListener(OnClickListener);
		ll_again.setOnClickListener(OnClickListener);
		ll_map.setOnClickListener(OnClickListener);
		ll_cancel.setOnClickListener(OnClickListener);
		
	}
	
	private void initData()
	{
		orderModel = OrderModel.getOrderModel();
		tv_Title.setText(orderModel.getHotelName());
		
		tv_OrderID.setText(orderModel.getOrderNo());
		tv_OrderStatus.setText(orderModel.getOrderStatus());
		tv_OrderTime.setText(orderModel.getOrderTime());
		tv_TotalPrice.setText("￥" + orderModel.getTotalPrice());
		tv_HotelName.setText(orderModel.getHotelName());
		tv_HotelTel.setText(orderModel.getHotelTel());
		tv_HotelAddr.setText(orderModel.getHotelAddr());
		if (orderModel.getPayMent().equals(FillCheckInInfoActivity.ALIPY))
		{
			tv_Payment.setText(ALIPAY);
			
		} else if (orderModel.getPayMent().equals(FillCheckInInfoActivity.DESK))
		{
			tv_Payment.setText(DESKPAY);
			
		}
		tv_InData.setText(orderModel.getArrivalTime());
		tv_OutData.setText(orderModel.getLeaveTime());
		
		if (orderModel.getPayMent().equals(FillCheckInInfoActivity.ALIPY))
		{
			
			tv_KeepTime.setText(orderModel.getLeaveTime());
			tv_Tip.setText("您的预订房间将保留至离店当天中午12：00，逾期将自动取消，敬请谅解。如有任何疑问请致电预订中心：400-009-000/400-0009-000");
			
		} else if (orderModel.getPayMent().equals(FillCheckInInfoActivity.DESK))
		{
			tv_KeepTime.setText(orderModel.getArrivalTime() + "  18:00 前");
			
			tv_Tip.setText("您的预订房间将保留至入驻当天 18:00，逾期将自动取消，敬请谅解。如有任何疑问请致电预订中心：400-009-000/400-0009-000");
		}
		
	}
	
	OnClickListener OnClickListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0)
		{
			
			switch (arg0.getId())
			{
			case R.id.pay:
			{
				// FIXME 价格一定要修改
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("OrderNo", orderModel
						.getOrderNo()));
				new LoadingAsync(OrderSuccessActivity.this,
						RequestMethod.METHOD_CHECKTIME, params).execute();
				
				break;
			}
			case R.id.again:
			{
				Intent intent = new Intent(OrderSuccessActivity.this,
						OnlineOrderActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.map:
			{
				Intent intent = new Intent(OrderSuccessActivity.this,
						OneMarkerActivity.class);
				intent.setAction("need title bar");
				startActivity(intent);
				break;
			}
			case R.id.cancelOrder:
			{
				cancelOrder();
				break;
			}
			case R.id.homeTitle_Right:
			{
				callMingYu();
				break;
			}
			case R.id.homeTitle_Left:
			{
				Intent intent = new Intent(OrderSuccessActivity.this,
						HomeActivity.class);
				startActivity(intent);
				break;
			}
			
			default:
				break;
			}
			
		}
	};
	
	private void alert(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_LONG).show();
	}
	
	private void buttonControl()
	{
		if (orderModel.getPayMent().equals(FillCheckInInfoActivity.ALIPY))
		{
			if (payStatus)
			{
				
				ll_pay.setVisibility(View.GONE);
				tv_TimeTip.setVisibility(View.GONE);
				ll_again.setVisibility(View.VISIBLE);
				ll_map.setVisibility(View.VISIBLE);
				ll_cancel.setVisibility(View.VISIBLE);
			} else
			{
				
				ll_again.setVisibility(View.GONE);
				ll_map.setVisibility(View.GONE);
				ll_cancel.setVisibility(View.GONE);
			}
			
		} else if (orderModel.getPayMent().equals(FillCheckInInfoActivity.DESK))
		{
			ll_pay.setVisibility(View.GONE);
			tv_TimeTip.setVisibility(View.GONE);
			
		}
	}
	
	public void callMingYu()
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
				+ "400-009-000"));
		startActivity(intent);
	}
	
	void setleftIcon(int drawableID)
	{
		iv_left.setPadding(20, 10, 10, 10);
		iv_left.setImageResource(drawableID);
		
	}
	
	void setRightIcon(int drawableID)
	{
		iv_right.setImageResource(drawableID);
		
	}
	
	private void cancelOrder()
	{
		if (orderModel.getPayMent().equals(FillCheckInInfoActivity.DESK))
		{
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("OrderNo", orderModel
					.getOrderNo()));
			
			if (MyConfig.isLogin)
			{
				/* 取消会员订单 */
				UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
				params.add(new BasicNameValuePair("CardNo", userInfoModel
						.getCardNo()));
				new LoadingAsync(OrderSuccessActivity.this,
						RequestMethod.METHOD_CANCELMYORDER, params).execute();
			} else
			{
				/* 取消闪客订单 */
				new LoadingAsync(OrderSuccessActivity.this,
						RequestMethod.METHOD_CANCELORDER, params).execute();
			}
			
		} else if (orderModel.getPayMent()
				.equals(FillCheckInInfoActivity.ALIPY))
		{
			
			if (payStatus)
			{
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("orderno", orderModel
						.getOrderNo()));
				params.add(new BasicNameValuePair("email", orderModel
						.getEmail()));
				new LoadingAsync(OrderSuccessActivity.this,
						RequestMethod.METHOD_BACKPAY, params).execute();
				
			} else
			{
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("OrderNo", orderModel
						.getOrderNo()));
				
				if (MyConfig.isLogin)
				{
					/* 取消会员订单 */
					UserInfoModel userInfoModel = UserInfoModel
							.getUserInfoModel();
					params.add(new BasicNameValuePair("CardNo", userInfoModel
							.getCardNo()));
					new LoadingAsync(OrderSuccessActivity.this,
							RequestMethod.METHOD_CANCELMYORDER, params)
							.execute();
				} else
				{
					/* 取消闪客订单 */
					new LoadingAsync(OrderSuccessActivity.this,
							RequestMethod.METHOD_CANCELORDER, params).execute();
				}
				
			}
		}
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		switch (methodName)
		{
		/** 取消散客的订单 */
		case METHOD_CANCELORDER:
		{
			alert("取消订单成功");
			go2Home();
			break;
		}
		/** 取消会员的订单 */
		case METHOD_CANCELMYORDER:
		{
			alert("取消订单成功");
			go2Home();
			break;
		}
		/** 取消会员的订单 */
		case METHOD_BACKPAY:
		{
			alert("取消订单成功");
			go2Home();
			break;
		}
		/** 订单可继续支付 */
		case METHOD_CHECKTIME:
		{
			String data = jsonResult.optString(Constant.JSON_DATA, "0");
			if ("1".equals(data))
			{
				PayHelper helper = new PayHelper(OrderSuccessActivity.this,
						mHandler);
				String orderInfo = PayHelper.getOrderInfo(
						orderModel.getHotelName()
								+ orderModel.getRoomTypeName(),
						orderModel.getOrderNo() + orderModel.getFirstName()
								+ orderModel.getLastName()
								+ orderModel.getHotelIntro()
								+ orderModel.getRoomTypeName()
								+ orderModel.getRoomTypeCode(),
						orderModel.getTotalPrice(), orderModel.getOrderNo());
				helper.pay(orderInfo);
			} else
			{
				alert("订单已经超时，请重新预订");
			}
			
			break;
		}
		
		default:
			break;
		}
		
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
		switch (methodName)
		{
		
		/** 取消会员的订单 */
		/** 取消散客的订单 */
		case METHOD_CANCELMYORDER:
		case METHOD_CANCELORDER:
		{
			alert("取消订单失败，请重试");
			break;
		}
		case METHOD_CHECKTIME:
		{
			alert("订单已经超时，请重新预订");
			break;
		}
		/** 取消会员的订单 */
		case METHOD_BACKPAY:
		{
			alert("申请退款异常，请重试，或致电我们的客服");
			break;
		}
		default:
			break;
		}
		
	}
	
	private void go2Home()
	{
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		orderModel.clearPrivateData();
		OrderSuccessActivity.this.finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			go2Home();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
