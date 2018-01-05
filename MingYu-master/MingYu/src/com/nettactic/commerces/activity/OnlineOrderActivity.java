package com.nettactic.commerces.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.DataPickerDialogImpl;
import com.nettactic.commerces.impl.SetInfoImpl;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.util.LogUtil;
import com.nettactic.commerces.widget.DialogTool;

/**
 * OnlineOrder
 * 
 * @author Administrator
 * 
 */
public class OnlineOrderActivity extends BaseActivity implements
		DataPickerDialogImpl, ReqResultImpl
{
	public static final String TAG = "SearchAndOrderActivity";
	
	public static final String PICKER_NUM = "pick_Num";
	
	public static final String ROOMLIST = "roomlist";
	
	public static final String LEFT = "Left";
	
	public static final String RIGHT = "right";
	
	private static final String CHECKIN_DATA = "checkInData";
	
	private static final String CHECKOUT_DATA = "checkOutData";
	
	public static final String CITYLIST = "cityList";
	
	public static final String SELECTEDCITYID = "selectedCityId";
	
	public final static int CITYFROMCODE = 2001;
	
	public final static int CITYBACKCODE = 2002;
	
	private int selectedCityID = 0;
	
	/**
	 * 会员身份标识 0：非会员; 1：会员; 2：协议用户 ps.默认为非会员
	 */
	private int MEMBER_SHIP = 0;
	
	/**
	 * key ：城市名称 value ：CityCode
	 */
	private Map<String, String> cityMap = null;
	
	private OrderModel orderModel;
	
	/**
	 * 从cityMap中分离出来的“城市”数组
	 */
	private ArrayList<String> cityItems;
	
	private int cardType = 0;
	
	private static SetInfoImpl setInfoImpl;
	
	private TextView te_Country;
	private TextView te_City;
	private LinearLayout li_InTime;
	private LinearLayout li_OutTime;
	private TextView tv_InTimeDay;
	private TextView tv_InTimeMonth;
	private TextView tv_OutTimeDay;
	private TextView tv_OutTimeMonth;
	private EditText et_KeyWord;
	private RadioGroup ra_item_ID;
	private LinearLayout bu_Order;
	
	private TextView te_CheckOrder;
	private LinearLayout li_Member;
	private LinearLayout li_CardType;
	private TextView te_choiceCardType;
	private LinearLayout li_CardNo;
	private EditText ed_CardNum;
	private LinearLayout li__Psw;
	private EditText ed_Psw;
	// private LinearLayout li_Agreement;
	// private EditText ed_ProtocolNum;
	
	private List<NameValuePair> getRoomListParams;
	
	public static final int INTIME = 1000;
	public static final int OUTTIME = 1001;
	public static final int DATAKEY = 1111;
	public static final String DATAVALUE = "datavalue";
	private Date in_IntentDate = null;
	private Date out_IntentDate = null;
	public static String FROMBRADLIST = "fromBradlist";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_onlineorder);
		setTitle("在线预订");
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.t_phone);
		orderModel = OrderModel.getOrderModel();
		initView();
		initData();
	}
	
	public static SetInfoImpl getSetInfoImpl()
	{
		return setInfoImpl;
	}
	
	@Override
	protected void onResume()
	{
		if (MyConfig.isLogin)
		{
			ra_item_ID.check(R.id.item_ID_member);
			ra_item_ID.setVisibility(View.GONE);
			te_choiceCardType.setVisibility(View.GONE);
			ed_Psw.setVisibility(View.GONE);
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			ed_CardNum.setText(userInfoModel.getCardNo());
			ed_CardNum.setFocusable(false);
			li_CardType.setVisibility(View.GONE);
			li__Psw.setVisibility(View.GONE);
		}
		super.onResume();
	}
	
	private void initView()
	{
		
		te_Country = (TextView) findViewById(R.id.onOrder_Country);
		te_City = (TextView) findViewById(R.id.onOrder_City);
		li_InTime = (LinearLayout) findViewById(R.id.onOrder_InTime);
		li_OutTime = (LinearLayout) findViewById(R.id.onOrder_OutTime);
		tv_InTimeDay = (TextView) findViewById(R.id.onOrder_InTimeDay);
		tv_InTimeMonth = (TextView) findViewById(R.id.onOrder_InTimeMonth);
		tv_OutTimeDay = (TextView) findViewById(R.id.onOrder_OutTimeDay);
		tv_OutTimeMonth = (TextView) findViewById(R.id.onOrder_OutTimeMonth);
		et_KeyWord = (EditText) findViewById(R.id.onOrder_KeyWord);
		et_KeyWord.setOnEditorActionListener(OnEditorActionListener);
		
		ra_item_ID = (RadioGroup) findViewById(R.id.item_ID);
		
		bu_Order = (LinearLayout) findViewById(R.id.onOrder_Order);
		te_CheckOrder = (TextView) findViewById(R.id.onOrder_CheckOrder);
		li_CardType = (LinearLayout) findViewById(R.id.onOrder_CardType);
		te_choiceCardType = (TextView) findViewById(R.id.onOrder_choiceCardType);
		li_CardNo = (LinearLayout) findViewById(R.id.onOrder_CardNo);
		ed_CardNum = (EditText) findViewById(R.id.onOrder_CardNum);
		li__Psw = (LinearLayout) findViewById(R.id.onOrder__Psw);
		ed_Psw = (EditText) findViewById(R.id.onOrder_Psw);
		li_Member = (LinearLayout) findViewById(R.id.onOrder_Member);
		// li_Agreement = (LinearLayout) findViewById(R.id.onOrder_Agreement);
		// ed_ProtocolNum = (EditText) findViewById(R.id.onOrder_ProtocolNum);
		
		te_City.setOnClickListener(this);
		li_InTime.setOnClickListener(this);
		li_OutTime.setOnClickListener(this);
		te_choiceCardType.setOnClickListener(this);
		te_CheckOrder.setOnClickListener(this);
		
		//
		bu_Order.setOnClickListener(this);
		ra_item_ID.setOnCheckedChangeListener(OnCheckedChangeListener);
		
	}
	
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onClick(View v)
	{
		Intent intent = null;
		int selectedID = v.getId();
		if (selectedID == R.id.onOrder_City)
		{
			intent = new Intent(this, ChooseCityActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt(SELECTEDCITYID, selectedCityID);
			bundle.putStringArrayList(CITYLIST, cityItems);
			intent.putExtras(bundle);
			startActivityForResult(intent, CITYFROMCODE);
			
		} else if (selectedID == R.id.onOrder_Order)
		{
			hotelSearch();
		} else if (selectedID == R.id.onOrder_InTime)
		{
			intent = new Intent(this, CalendarViewActivity.class);
			startActivityForResult(intent, INTIME);
			
		} else if (selectedID == R.id.onOrder_OutTime)
		{
			intent = new Intent(this, CalendarViewActivity.class);
			intent.putExtra(DATAVALUE, in_IntentDate);
			startActivityForResult(intent, OUTTIME);
			
		} else if (selectedID == R.id.onOrder_choiceCardType)
		{
			initCardTypeDialog();
		} else if (selectedID == R.id.onOrder_CheckOrder)
		{
			if (MyConfig.isLogin)
			{
				intent = new Intent(this, MyOrderActivity.class);
				startActivity(intent);
			} else
			{
				intent = new Intent(this, GetOrderByEmail.class);
				startActivity(intent);
			}
		} else if (selectedID == R.id.homeTitle_Right)
		{
			callMingYu();
			
		}
		super.onClick(v);
	}
	
	private void hotelSearch()
	{
		
		/*
		 * http://www.minyounhotels.com/sdk/InformationService.ashx?tag=
		 * GetHotelsBySurrounding
		 * &Country=中国&CityCode=215&Indate=2014/12/06&OutDate
		 * =2014/12/07&Nearby_Loc=&CustomKey=&CustomValue=
		 */
		if (null != cityMap && !cityMap.isEmpty())
		{
			getRoomListParams = new ArrayList<NameValuePair>();
			getRoomListParams.add(new BasicNameValuePair("Country",
					ActivityUtil.isEmpty(te_Country.getText().toString()) ? ""
							: te_Country.getText().toString()));
			
			getRoomListParams.add(new BasicNameValuePair("CityCode", cityMap
					.get(cityItems.get(selectedCityID))));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			
			getRoomListParams.add(new BasicNameValuePair("Indate", dateFormat
					.format(in_IntentDate)));
			getRoomListParams.add(new BasicNameValuePair("OutDate", dateFormat
					.format(out_IntentDate)));
			
			getRoomListParams.add(new BasicNameValuePair("Nearby_Loc",
					ActivityUtil.isEmpty(te_Country.getText().toString()) ? ""
							: et_KeyWord.getText().toString()));
			
			int CheckedRadioButtonId = ra_item_ID.getCheckedRadioButtonId();
			
			if (CheckedRadioButtonId == R.id.item_ID_nomember)
			{
				new LoadingAsync(OnlineOrderActivity.this,
						RequestMethod.METHOD_GETHOTELSBYSURROUNDING,
						getRoomListParams).execute();
				
			} else if (CheckedRadioButtonId == R.id.item_ID_member)
			{
				if (MyConfig.isLogin)
				{
					new LoadingAsync(OnlineOrderActivity.this,
							RequestMethod.METHOD_GETHOTELSBYSURROUNDING,
							getRoomListParams).execute();
				} else
				{
					
					doLogin();
				}
				
			}
			// else if (CheckedRadioButtonId == R.id.item_ID_agreement)
			// {
			//
			// if (ActivityUtil.isEmpty(ed_ProtocolNum.getText().toString()))
			// {
			// ActivityUtil.Vibrate(OnlineOrderActivity.this, ed_ProtocolNum);
			// } else
			// {
			//
			// getRoomListParams.add(new BasicNameValuePair("CustomValue",
			// ed_ProtocolNum.getText().toString()));
			// new LoadingAsync(OnlineOrderActivity.this,
			// RequestMethod.METHOD_GETHOTELSBYSURROUNDING,
			// getRoomListParams).execute();
			// }
			//
			// } else
			// {
			//
			// }
			orderModel.setArrivalTime(dateFormat.format(in_IntentDate));
			orderModel.setLeaveTime(dateFormat.format(out_IntentDate));
			orderModel.setArrivalTime(dateFormat.format(in_IntentDate));
		} else
		{
			alert("未搜索到相关信息，请更改信息后重试");
		}
	}
	
	private OnEditorActionListener OnEditorActionListener = new TextView.OnEditorActionListener()
	{
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
		{
			if (actionId == EditorInfo.IME_ACTION_SEARCH
					|| (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
			{
				hotelSearch();
				return true;
			}
			return false;
		}
	};
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == CITYFROMCODE)
		{
			selectedCityID = data.getIntExtra(SELECTEDCITYID, 0);
			te_City.setText(cityItems.get(selectedCityID));
			orderModel.setCity(cityItems.get(selectedCityID));
		} else if (requestCode == INTIME || requestCode == OUTTIME)
		{
			
			if (null == data && resultCode == DATAKEY)
			{
				alert("选择的日期有误，请重试");
			} else
			{
				Date date = (Date) data.getSerializableExtra(DATAVALUE);
				Locale locale = Locale.getDefault();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						this.getString(R.string.month_name_format), locale);
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				
				switch (requestCode)
				{
				case INTIME:
				{
					in_IntentDate = date;
					tv_InTimeDay.setText(dayFormat.format(date));
					tv_InTimeMonth.setText(DateToWeek(date)
							+ simpleDateFormat.format(date) + "年");
					break;
				}
				case OUTTIME:
				{
					out_IntentDate = date;
					tv_OutTimeDay.setText(dayFormat.format(date));
					tv_OutTimeMonth.setText(DateToWeek(date)
							+ simpleDateFormat.format(date) + "年");
					break;
				}
				
				default:
					break;
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
	 * 日期变量转成对应的星期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToWeek(Date date)
	{
		final int WEEKDAYS = 7;
		
		final String[] WEEK =
		{ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayIndex < 1 || dayIndex > WEEKDAYS)
		{
			return null;
		}
		
		return WEEK[dayIndex - 1];
	}
	
	public void initData()
	{
		
		orderModel.clearPrivateData();
		
		final Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.DAY_OF_YEAR, 1);
		Date nextDate = nextYear.getTime();
		out_IntentDate = nextDate;
		
		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.DAY_OF_YEAR, 0);
		Date lastDate = lastYear.getTime();
		in_IntentDate = lastDate;
		
		SimpleDateFormat orderTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		orderModel.setOrderTime(orderTimeFormat.format(lastDate));
		
		Locale locale = Locale.getDefault();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				this.getString(R.string.month_name_format), locale);
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		
		tv_InTimeDay.setText(dayFormat.format(lastDate));
		tv_InTimeMonth.setText(DateToWeek(lastDate)
				+ simpleDateFormat.format(lastDate) + "年");
		
		tv_OutTimeDay.setText(dayFormat.format(nextDate));
		tv_OutTimeMonth.setText(DateToWeek(nextDate)
				+ simpleDateFormat.format(nextDate) + "年");
		
		new LoadingAsync(OnlineOrderActivity.this,
				RequestMethod.METHOD_GETDESTINATIONCITY).execute();
		
	}
	
	private OnCheckedChangeListener OnCheckedChangeListener = new OnCheckedChangeListener()
	{
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			switch (checkedId)
			{
			// 非会员
			case R.id.item_ID_nomember:
			{
				orderModel.setCustAccount("");
				li_Member.setVisibility(View.GONE);
				// li_Agreement.setVisibility(View.GONE);
				MEMBER_SHIP = 0;
				break;
			}
			// 会员
			case R.id.item_ID_member:
			{
				orderModel.setCustAccount("");
				li_Member.setVisibility(View.VISIBLE);
				// li_Agreement.setVisibility(View.GONE);
				MEMBER_SHIP = 1;
				break;
			}
			// // 协议用户
			// case R.id.item_ID_agreement:
			// {
			// li_Member.setVisibility(View.GONE);
			// li_Agreement.setVisibility(View.VISIBLE);
			// MEMBER_SHIP = 2;
			// break;
			// }
			}
		}
	};
	
	// 执行登录方法
	private void doLogin()
	{
		
		if (ActivityUtil.isEmpty(te_choiceCardType.getText().toString())
				|| ActivityUtil.isEmpty(ed_CardNum.getText().toString())
				|| ActivityUtil.isEmpty(ed_Psw.getText().toString()))
		{
			if (ActivityUtil.isEmpty(te_choiceCardType.getText().toString()))
			{
				ActivityUtil.Vibrate(OnlineOrderActivity.this,
						te_choiceCardType);
				
			} else
			{
				
				if (ActivityUtil.isEmpty(ed_CardNum.getText().toString()))
				{
					ActivityUtil.Vibrate(OnlineOrderActivity.this, ed_CardNum);
					
				} else
				{
					if (ActivityUtil.isEmpty(ed_Psw.getText().toString()))
					{
						ActivityUtil.Vibrate(OnlineOrderActivity.this, ed_Psw);
					} else
					{
						
					}
				}
			}
		} else
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("CardType", String
					.valueOf(cardType)));
			params.add(new BasicNameValuePair("CardNo", ed_CardNum.getText()
					.toString()));
			params.add(new BasicNameValuePair("CardPassword", ed_Psw.getText()
					.toString()));
			LoadingAsync LA = (LoadingAsync) new LoadingAsync(
					OnlineOrderActivity.this, RequestMethod.METHOD_LOGIN,
					params).execute();
		}
	}
	
	private void initCardTypeDialog()
	{
		final String[] menus = getResources().getStringArray(R.array.card_type);
		DialogTool.createListDialog(this, R.drawable.circle, "请选择卡类型", menus,
				new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						cardType = which;
						te_choiceCardType.setText(menus[which]);
					}
				}).show();
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		switch (methodName)
		{
		case METHOD_GETDESTINATIONCITY:
		{
			JSONArray result = jsonResult.optJSONArray(Constant.JSON_DATA);
			
			if (MyConfig.ifDebug)
			{
				LogUtil.d("METHOD_GETDESTINATIONCITY", result.toString());
			}
			
			cityMap = new HashMap<String, String>();
			cityItems = new ArrayList<String>();
			
			for (int i = 0; i < result.length(); i++)
			{
				JSONObject JO = result.optJSONObject(i);
				
				cityItems.add(null != JO.optString("Name") ? JO
						.optString("Name") : "");
				
				cityMap.put(JO.optString("Name"), JO.optString("CityCode"));
			}
			
			/* 设置城市选项的默认值，默认为获取到的数组的首项 */
			te_City.setText(cityItems.get(0));
			orderModel.setCity(cityItems.get(0));
			break;
			
		}
		// 获取酒店列表
		case METHOD_GETHOTELSBYSURROUNDING:
		{
			// int CheckedRadioButtonId = ra_item_ID.getCheckedRadioButtonId();
			
			// if (CheckedRadioButtonId == R.id.item_ID_agreement)
			// {
			// orderModel.setCustAccount(ed_ProtocolNum.getText().toString());
			// }
			
			try
			{
				JSONArray result = jsonResult.optJSONArray(Constant.JSON_DATA);
				
				Intent in = getIntent();
				// if (in.getAction().equals(FROMBRADLIST))
				// {
				// Intent intent = new Intent(this, HotelListActivity.class);
				// startActivity(intent);
				// } else
				// {
				Intent intent = new Intent(this, HotelListActivity.class);
				intent.putExtra(SELECTEDCITYID, cityItems.get(selectedCityID));
				intent.putExtra(ROOMLIST, result.toString());
				startActivity(intent);
			} catch (Exception e)
			{
				alert("未搜索到相关信息，请更改信息后重试");
			}
			// }
			break;
		}
		// 登录成功
		case METHOD_LOGIN:
		{
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			userInfoModel.initUserInfoModel(jsonResult
					.optJSONObject(Constant.JSON_DATA));
			userInfoModel.setCardType(String.valueOf(cardType));
			userInfoModel.setCardNo(ed_CardNum.getText().toString());
			
			// 保存用户信息到本地以便进行快速登录的操作
			Map<String, String> map = new HashMap<String, String>();
			map.put(LoginActivity.IFAUTOLOGIN, Constant.YES);
			map.put(LoginActivity.CARDTYPE, String.valueOf(cardType));
			map.put(LoginActivity.CARDNO, ed_CardNum.getText().toString());
			map.put(LoginActivity.CARDPASSWORD, ed_Psw.getText().toString());
			
			MyConfig.isLogin = true;
			ActivityUtil.saveConfig(OnlineOrderActivity.this, map);
			
			new LoadingAsync(OnlineOrderActivity.this,
					RequestMethod.METHOD_GETHOTELSBYSURROUNDING,
					getRoomListParams).execute();
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
		case METHOD_GETDESTINATIONCITY:
			
		case METHOD_GETHOTELSBYSURROUNDING:
		{
			alert("未搜索到相关信息，请更改信息后重试");
			break;
		}
		// 登录成功
		case METHOD_LOGIN:
		{
			alert("卡号或密码输入有误");
			break;
		}
		default:
			break;
		}
	}
	
	/**
	 * HotelName HotelCode CityCode
	 */
	/*
	 * @Override public void setInfo(Object object) { Map<String, String> map =
	 * (Map<String, String>) object;
	 * tv_Cities.setText(cityCodeMap.get(map.get("CityCode")));
	 * tv_Hotels.setText(map.get("HotelName"));
	 * 
	 * orderModel.setHotelName(map.get("HotelName"));
	 * orderModel.setHotelCode(map.get("HotelCode")); }
	 */
	
	@Override
	protected void onDestroy()
	{
		orderModel.clearPrivateData();
		super.onDestroy();
	}
	
	@Override
	public Void setDataToPage(String type, String data)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
