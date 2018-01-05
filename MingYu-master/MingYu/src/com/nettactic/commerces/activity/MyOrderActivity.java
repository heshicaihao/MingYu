package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.nettactic.alipay.PayHelper;
import com.nettactic.alipay.Result;
import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.MyOrderAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.SetInfoImpl;
import com.nettactic.commerces.model.MyOrderModel;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 我的预订
 * 
 * @author ruan
 * 
 */
public class MyOrderActivity extends BaseActivity implements ReqResultImpl,
		SetInfoImpl
{
	private ListView lv_OrderList;
	
	private MyOrderAdapter myOrderAdapter;
	
	private List<MyOrderModel> list;
	
	private String email = "";
	
	private String orderNo = "";
	private static final int SDK_PAY_FLAG = 1;
	
	private static final int SDK_CHECK_FLAG = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myorder);
		setTitle("我的预订");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		Intent intent = getIntent();
		if (intent.hasExtra(Constant.EMAIL)
				&& intent.hasExtra(Constant.ORDERNO))
		{
			email = intent.getStringExtra(Constant.EMAIL);
			orderNo = intent.getStringExtra(Constant.ORDERNO);
			initDataByEmail();
		} else
		{
			
			initData();
		}
	}
	
	private void initView()
	{
		lv_OrderList = (ListView) findViewById(R.id.mo_OrderList);
		
		list = new ArrayList<MyOrderModel>();
		myOrderAdapter = new MyOrderAdapter(this, list);
		lv_OrderList.setAdapter(myOrderAdapter);
	}
	
	/* 加载会员订单 */
	private void initData()
	{
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
		params.add(new BasicNameValuePair("CardNo", userInfoModel.getCardNo()));
		
		new LoadingAsync(this, RequestMethod.METHOD_GETMYORDERLIST, params)
				.execute();
	}
	
	/** 加载闪客订单 */
	private void initDataByEmail()
	{
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Email", email));
		params.add(new BasicNameValuePair("OrderNo", orderNo));
		
		new LoadingAsync(this, RequestMethod.METHOD_GETORDERLISTBYEMAIL, params)
				.execute();
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		switch (methodName)
		{
		case METHOD_GETMYORDERLIST:
		case METHOD_GETORDERLISTBYEMAIL:
		{
			
			MyOrderModel myOrderModel;
			JSONArray data = jsonResult.optJSONArray(Constant.JSON_DATA);
			if (null != list && list.size() > 0)
			{
				list.clear();
			}
			for (int i = 0; i < data.length(); i++)
			{
				myOrderModel = new MyOrderModel(data.optJSONObject(i));
				list.add(myOrderModel);
			}
			myOrderAdapter.notifyDataSetChanged();
			
			if (null == list || list.size() < 1)
			{
				alert("查无此单，请核对填写的信息");
			}
			break;
		}
		/** 取消散客的订单 */
		case METHOD_CANCELORDER:
		{
			alert("取消订单成功");
			initDataByEmail();
			
			break;
		}
		/** 取消会员的订单 */
		case METHOD_CANCELMYORDER:
		{
			alert("取消订单成功");
			initData();
			break;
		}
		/** 取消会员的订单 */
		case METHOD_BACKPAY:
		{
			alert("取消订单成功");
			// initData();
			break;
		}
		/** 订单可继续支付 */
		case METHOD_CHECKTIME:
		{
			String data = jsonResult.optString(Constant.JSON_DATA, "0");
			if ("1".equals(data))
			{
				
				PayHelper helper = new PayHelper(MyOrderActivity.this, mHandler);
				String orderInfo = PayHelper.getOrderInfo(om.getHotelName(),
						om.getOrderNo() + om.getFirstName() + om.getLastName(),
						om.getMoney(), om.getOrderNo());
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
		
		case METHOD_GETMYORDERLIST:
		case METHOD_GETORDERLISTBYEMAIL:
		{
			alert("当前没有订单记录");
			break;
		}
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
		default:
			break;
		}
		
	}
	
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
					Toast.makeText(MyOrderActivity.this, "支付成功",
							Toast.LENGTH_SHORT).show();
					if (MyConfig.isLogin)
					{
						initData();
					} else
					{
						initDataByEmail();
					}
				} else
				{
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”
					// 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000"))
					{
						Toast.makeText(MyOrderActivity.this, "支付结果确认中",
								Toast.LENGTH_SHORT).show();
					} else
					{
						Toast.makeText(MyOrderActivity.this, "支付失败",
								Toast.LENGTH_SHORT).show();
						
					}
				}
				break;
			}
			case SDK_CHECK_FLAG:
			{
				Toast.makeText(MyOrderActivity.this, "检查结果为：" + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};
	
	private MyOrderModel om;
	
	@Override
	public void setInfo(Object object)
	{
		om = (MyOrderModel) object;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("OrderNo", om.getOrderNo()));
		new LoadingAsync(this, RequestMethod.METHOD_CHECKTIME, params)
				.execute();
		
	}
}
