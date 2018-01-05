package com.nettactic.commerces.activity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.EditTexts;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.util.LogUtil;

/**
 * 填写入住订单信息
 */
public class FillCheckInInfoActivity extends BaseActivity implements
		OnCheckedChangeListener, ReqResultImpl
{
	public static final String TAG = "FillCheckInInfoActivity";
	
	public static final String ALIPY = "11";
	
	public static final String DESK = "1";
	
	private RadioGroup rg_PayMent;
	
	private EditText et_LastName, et_Mobile, et_EMail, et_SpecialNeed;
	
	private Button btn_Submit;
	
	private OrderModel orderModel;
	
	private Map<EditTexts, EditText> editTextMap;
	
	private List<EditTexts> editTextsList;
	
	private RadioButton rb_alipy, rb_desk;
	private TextView payTip;
	
	private ImageView iv_Down, iv_up;
	private TextView tv_Num;
	private TextView tv_TotalPrice;
	
	private int roomNum = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fillcheckininfo);
		setTitle("");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initData();
	}
	
	private void initView()
	{
		payTip = (TextView) findViewById(R.id.payTip);
		et_LastName = (EditText) findViewById(R.id.fci_LastName);
		et_Mobile = (EditText) findViewById(R.id.fci_mobile);
		et_EMail = (EditText) findViewById(R.id.fci_email);
		et_SpecialNeed = (EditText) findViewById(R.id.fci_SpecialNeed);
		iv_Down = (ImageView) findViewById(R.id.down);
		iv_up = (ImageView) findViewById(R.id.up);
		tv_Num = (TextView) findViewById(R.id.num);
		tv_TotalPrice = (TextView) findViewById(R.id.TotalPrice);
		
		rg_PayMent = (RadioGroup) findViewById(R.id.fci_PayMent);
		rb_alipy = (RadioButton) findViewById(R.id.fci_Alipy);
		rb_desk = (RadioButton) findViewById(R.id.fci_Desk);
		
		btn_Submit = (Button) findViewById(R.id.fci_submit);
		
		rg_PayMent.setOnCheckedChangeListener(this);
		btn_Submit.setOnClickListener(this);
		iv_Down.setOnClickListener(this);
		iv_up.setOnClickListener(this);
		
	}
	
	private void initData()
	{
		
		orderModel = OrderModel.getOrderModel();
		setTitle(orderModel.getHotelName());
		getTotalPrice();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("HotelCode", orderModel
				.getHotelCode()));
		params.add(new BasicNameValuePair("RateCode", orderModel.getRateCode()));
		params.add(new BasicNameValuePair("Arrival", orderModel
				.getArrivalTime()));
		params.add(new BasicNameValuePair("Departure", orderModel
				.getLeaveTime()));
		new LoadingAsync(this, RequestMethod.METHOD_GUARANTEERULE, params)
				.execute();
		
		editTextMap = new HashMap<Constant.EditTexts, EditText>();
		editTextMap.put(EditTexts.LASTNAME, et_LastName);
		editTextMap.put(EditTexts.PHONE, et_Mobile);
		editTextMap.put(EditTexts.EMAIL, et_EMail);
		
		editTextsList = new ArrayList<Constant.EditTexts>()
		{
			{
				add(EditTexts.LASTNAME);
				add(EditTexts.PHONE);
				add(EditTexts.EMAIL);
			}
		};
		
		if (MyConfig.isLogin)
		{
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			et_LastName.setText(userInfoModel.getLastName());
			et_Mobile.setText(userInfoModel.getMobile());
			et_EMail.setText(userInfoModel.getEmail());
		}
		
	}
	
	private boolean checkFitInfo()
	{
		for (int i = 0; i < editTextMap.size(); i++)
		{
			
			if (ActivityUtil.verifyField(editTextsList.get(i),
					editTextMap.get(editTextsList.get(i)).getText().toString()))
			{
				
			} else
			{
				ActivityUtil.Vibrate(this,
						editTextMap.get(editTextsList.get(i)));
				return false;
			}
		}
		return true;
	}
	
	private void setInfo2OrderModel()
	{
		
		orderModel.setRoom_num(String.valueOf(roomNum));
		orderModel
				.setLastName(null != et_LastName.getText().toString() ? et_LastName
						.getText().toString() : "");
		orderModel.setMobile(null != et_Mobile.getText().toString() ? et_Mobile
				.getText().toString() : "");
		orderModel.setEmail(null != et_EMail.getText().toString() ? et_EMail
				.getText().toString() : "");
		orderModel
				.setSpecialNeed(null != et_SpecialNeed.getText().toString() ? et_SpecialNeed
						.getText().toString() : "");
		
	}
	
	//
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		switch (checkedId)
		{
		// 支付宝支付
		case R.id.fci_Alipy:
		{
			orderModel.setPayMent(ALIPY);
			break;
		}
		// 前台支付
		case R.id.fci_Desk:
		{
			orderModel.setPayMent(DESK);
			break;
		}
		
		default:
			break;
		}
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.fci_submit:
		{
			if (checkFitInfo())
			{
				setInfo2OrderModel();
				if (orderModel.getPayMent().equals(ALIPY))
				{
					submitOrder();
					
				} else if (orderModel.getPayMent().equals(DESK))
				{
					submitOrder();
					
				}
				if (v.getId() == R.id.homeTitle_Right)
				{
					callMingYu();
				} else
				{
					
				}
			} else
			{
				
			}
			
			break;
		}
		case R.id.up:
		{
			
			roomNum++;
			tv_Num.setText(String.valueOf(roomNum));
			orderModel.setRoom_num(String.valueOf(roomNum));
			getTotalPrice();
			break;
		}
		case R.id.down:
		{
			if (roomNum > 1)
			{
				roomNum--;
				tv_Num.setText(String.valueOf(roomNum));
				orderModel.setRoom_num(String.valueOf(roomNum));
				getTotalPrice();
			} else
			{
				alert("房间数至少为一间");
			}
			break;
		}
		default:
			break;
		}
		
		super.onClick(v);
	}
	
	/** 提交订单 */
	@SuppressLint("SimpleDateFormat")
	private void submitOrder()
	{

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair(Constant.HOTELCODE, orderModel
				.getHotelCode()));
		params.add(new BasicNameValuePair(Constant.HOTELNAME, orderModel
				.getHotelName()));
		params.add(new BasicNameValuePair(Constant.ROOMNUM, orderModel
				.getRoom_num()));
		
		params.add(new BasicNameValuePair(Constant.RESERVATIONTYPE, orderModel
				.getPayMent()));
		params.add(new BasicNameValuePair(Constant.ROOMTYPECODE, orderModel
				.getRoomTypeCode()));
		params.add(new BasicNameValuePair(Constant.ROOMTYPENAME, orderModel
				.getRoomTypeName()));
		params.add(new BasicNameValuePair(Constant.ROOMUNITPRICE, orderModel
				.getRoomUnitprice()));
		params.add(new BasicNameValuePair(Constant.ROOMPRICECODE, orderModel
				.getRateCode()));
		
		/** 用户填写的信息 */
		params.add(new BasicNameValuePair(Constant.FIRSTNAME, orderModel
				.getFirstName()));
		params.add(new BasicNameValuePair(Constant.LASTNAME, orderModel
				.getLastName()));
		params.add(new BasicNameValuePair(Constant.EMAIL, orderModel.getEmail()));
		params.add(new BasicNameValuePair(Constant.MOBILE, orderModel
				.getMobile()));
		params.add(new BasicNameValuePair(Constant.REMARK, orderModel
				.getSpecialNeed()));
		params.add(new BasicNameValuePair(Constant.IDENTIFY, orderModel
				.getIDNo()));
		// params.add(new BasicNameValuePair(Constant.ADULTS, orderModel
		// .getAdultsNum()));
		// params.add(new BasicNameValuePair(Constant.CHILDREN, orderModel
		// .getChildrenNum()));
		// params.add(new BasicNameValuePair(Constant.EXPRESSCHECKIN, "0"));
		params.add(new BasicNameValuePair(Constant.ARRIVALTIME, orderModel
				.getArrivalTime()));
		params.add(new BasicNameValuePair(Constant.DEPARTURETIME, orderModel
				.getLeaveTime()));
		params.add(new BasicNameValuePair(Constant.CUSTACCOUNT, orderModel
				.getCustAccount()));// 协议客户
		
		if (MyConfig.isLogin)
		{
			String[] keys = new String[3];
			keys[0] = LoginActivity.CARDTYPE;
			keys[1] = LoginActivity.CARDNO;
			keys[2] = LoginActivity.CARDPASSWORD;
			
			Map<String, String> map = ActivityUtil.getConfig(this, keys);
			if (null != map.get(LoginActivity.CARDTYPE)
					&& null != map.get(LoginActivity.CARDNO)
					&& null != map.get(LoginActivity.CARDPASSWORD))
			{
				
				params.add(new BasicNameValuePair(Constant.CARDTYPE, map
						.get(LoginActivity.CARDTYPE)));
				params.add(new BasicNameValuePair(Constant.CARDNO, map
						.get(LoginActivity.CARDNO)));
				params.add(new BasicNameValuePair(Constant.CARDPASSWORD, map
						.get(LoginActivity.CARDPASSWORD)));
			} else
			{
				
			}
		}
		params.add(new BasicNameValuePair(Constant.TOTALPRICE, orderModel
				.getTotalPrice()));
		new LoadingAsync(this, RequestMethod.METHOD_ORDERINTERFACE, params)
				.execute();
	}
	
	private void getTotalPrice()
	{
		try
		{
			SimpleDateFormat df = new SimpleDateFormat(Constant.DATAFORMAT);
			long from = df.parse(orderModel.getArrivalTime()).getTime();
			long to = df.parse(orderModel.getLeaveTime()).getTime();
			BigDecimal days = new BigDecimal((to - from)
					/ (1000 * 60 * 60 * 24));
			
			/** 总价格计算 */
			BigDecimal dec_RoomUnitprice = null;
			BigDecimal dec_RoomNum = null;
			if (null != orderModel.getRoom_num())
			{
				dec_RoomNum = new BigDecimal(orderModel.getRoom_num());
			}
			if (null != orderModel.getRoomUnitprice())
			{
				
				dec_RoomUnitprice = new BigDecimal(
						orderModel.getRoomUnitprice());
			}
			if (null != dec_RoomUnitprice && null != dec_RoomNum)
			{
				String totalPrice = dec_RoomUnitprice.multiply(dec_RoomNum)
						.multiply(days).toString();
				orderModel.setTotalPrice(totalPrice);
				tv_TotalPrice.setText(orderModel.getTotalPrice());
			}
			LogUtil.d(
					TAG,
					"orderModel.getRoom_num()" + orderModel.getRoom_num()
							+ "orderModel.getRoomUnitprice()"
							+ orderModel.getRoomUnitprice());
		} catch (Exception e)
		{
			// e.printStackTrace();
		}
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		try
		{
			switch (methodName)
			{
			case METHOD_GUARANTEERULE:
			{
				LogUtil.d(TAG, "reqResultSuccess " + jsonResult.toString());
				JSONObject data = jsonResult.getJSONObject(Constant.JSON_DATA);
				String rule = data.optString("Rule");
				if (rule.contains("11"))
				{
					rb_alipy.setVisibility(View.VISIBLE);
					payTip.setVisibility(View.VISIBLE);
				} else
				{
					
				}
				break;
			}
			case METHOD_ORDERINTERFACE:
			{
				
				JSONObject data = jsonResult.getJSONObject(Constant.JSON_DATA);
				String orderNo = data.optString("OrderNo");
				orderModel.setOrderNo(orderNo);
				
				Intent intent = new Intent(FillCheckInInfoActivity.this,
						OrderSuccessActivity.class);
				startActivity(intent);
				
				break;
			}
			
			default:
				break;
			}
		} catch (JSONException e)
		{
			alert("数据异常，请稍后重试！");
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		switch (methodName)
		{
		case METHOD_GUARANTEERULE:
		{
			LogUtil.d(TAG, "reqResultFail :" + errorCode + "");
			break;
		}
		
		case METHOD_ORDERINTERFACE:
		{
			alert("网络异常，请稍后重试！");
			break;
		}
		
		default:
			break;
		}
		
	}
	
}
