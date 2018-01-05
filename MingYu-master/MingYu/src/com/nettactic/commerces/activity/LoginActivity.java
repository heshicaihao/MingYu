package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.ServerConstant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.widget.DialogTool;

/**
 * 登录
 * 
 * @author ruan
 * 
 */
public class LoginActivity extends BaseActivity implements ReqResultImpl
{
	private static final String TAG = "LoginActivity";
	
	/**
	 * 喜悦卡:0 诚悦卡:1 优悦卡:2 尊悦卡:3
	 */
	public static final String CARDTYPE = "CardType";
	
	public static final String CARDNO = "CardNo";
	
	public static final String CARDPASSWORD = "CardPassword";
	
	public static final String IFAUTOLOGIN = "IfAutoLogin";
	
	private TextView tv_ChoiceCardType, tv_reg, tv_modify;
	
	private EditText et_CardNum, et_Psw;
	
	private Button btn_login;
	
	private LinearLayout ll_CardType, ll_CardNum, ll_Psw;
	
	private int cardType = 0;
	
	private String[] menus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("登录");
		setleftIcon(R.drawable.t_backarr);
		menus = getResources().getStringArray(R.array.card_type);
		initView();
		
	}
	
	@Override
	protected void onResume()
	{
		initData();
		super.onResume();
	}
	
	private void initView()
	{
		tv_ChoiceCardType = (TextView) findViewById(R.id.login_CardType);
		et_CardNum = (EditText) findViewById(R.id.login_CardNum);
		et_Psw = (EditText) findViewById(R.id.login_CardPsw);
		btn_login = (Button) findViewById(R.id.login_Login);
		tv_reg = (TextView) findViewById(R.id.login_reg);
		tv_modify = (TextView) findViewById(R.id.login_modify_psw);
		
		ll_CardType = (LinearLayout) findViewById(R.id.login_ll_type);
		ll_CardNum = (LinearLayout) findViewById(R.id.login_ll_CardNum);
		ll_Psw = (LinearLayout) findViewById(R.id.login_ll_psw);
		
		tv_ChoiceCardType.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		tv_reg.setOnClickListener(this);
		tv_modify.setOnClickListener(this);
	}
	
	private void initData()
	{
		
		String[] keys = new String[3];
		keys[0] = CARDTYPE;
		keys[1] = CARDNO;
		keys[2] = CARDPASSWORD;
		
		Map<String, String> map = ActivityUtil.getConfig(this, keys);
		if (null != map)
		{
			
			if (!ActivityUtil.isEmpty(map.get(CARDTYPE))
					&& !ActivityUtil.isEmpty(map.get(CARDNO))
					&& !ActivityUtil.isEmpty(map.get(CARDPASSWORD)))
			{
				
				tv_ChoiceCardType.setText(menus[Integer.valueOf(map
						.get(CARDTYPE))]);
				et_CardNum.setText(map.get(CARDNO));
				et_Psw.setText(map.get(CARDPASSWORD));
			} else
			{
				
			}
		} else
		{
			
		}
		
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.login_CardType:
		{
			
			initCardTypeDialog();
			break;
		}
		case R.id.login_Login:
		{
			// 点击登录按钮，提交用户的登录信息
			if (checkLogin())
			{
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(CARDTYPE, String
						.valueOf(cardType)));
				params.add(new BasicNameValuePair(CARDNO, et_CardNum.getText()
						.toString()));
				params.add(new BasicNameValuePair(CARDPASSWORD, et_Psw
						.getText().toString()));
				LoadingAsync LA = (LoadingAsync) new LoadingAsync(
						LoginActivity.this, RequestMethod.METHOD_LOGIN, params)
						.execute();
			} else
			{
				
			}
			
			break;
		}
		case R.id.login_reg:
		{
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.login_modify_psw:
		{
			Intent intent = new Intent(this, ModifyPassWordActivity.class);
			startActivity(intent);
			break;
		}
		
		default:
			break;
		}
		
		super.onClick(v);
	}
	
	private void initCardTypeDialog()
	{
		
		DialogTool.createListDialog(this, R.drawable.circle, "请选择卡类型", menus,
				new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						cardType = which;
						tv_ChoiceCardType.setText(menus[which]);
					}
				}).show();
	}
	
	private boolean checkLogin()
	{
		if (null != tv_ChoiceCardType.getText().toString())
		{
			
		} else
		{
			ActivityUtil.Vibrate(this, ll_CardType);
			return false;
		}
		
		if (null != et_CardNum.getText().toString())
		{
		} else
		{
			ActivityUtil.Vibrate(this, ll_CardNum);
			return false;
		}
		
		if (null != et_Psw.getText().toString())
		{
			
		} else
		{
			ActivityUtil.Vibrate(this, ll_Psw);
			return false;
		}
		return true;
		
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		
		if (MyConfig.isLogin)
		{
			
			/** 登录成功跳转到首页 */
			Intent intent = new Intent(this, MingYuActivity.class);
			startActivity(intent);
		} else
		{
			
			MyConfig.isLogin = true;
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			userInfoModel.initUserInfoModel(jsonResult
					.optJSONObject(Constant.JSON_DATA));
			userInfoModel.setCardType(String.valueOf(cardType));
			userInfoModel.setCardNo(et_CardNum.getText().toString());
			
			// 保存用户信息到本地以便进行快速登录的操作
			Map<String, String> map = new HashMap<String, String>();
			map.put(IFAUTOLOGIN, Constant.YES);
			map.put(CARDTYPE, String.valueOf(cardType));
			map.put(CARDNO, et_CardNum.getText().toString());
			map.put(CARDPASSWORD, et_Psw.getText().toString());
			ActivityUtil.saveConfig(LoginActivity.this, map);
			
			/** 登录成功，启动心跳 */
			// Intent serviceIntent = new Intent(LoginActivity.this,
			// AngelBeatService.class);
			// serviceIntent.setAction(AngelBeatService.ACTION);
			// startService(serviceIntent);
			
			finish();
		}
		
	}
	
	/**
	 * 1002：session验证失败 1003：其他错误 1004：卡号错误 1005：卡密码错误 1006：卡过期 0000：权限不够
	 */
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		if (errorCode == ServerConstant.ReturnCode.VALIDATOR_CONNECT_TIMEOUT)
		{
			alert("网络连接超时，请检查您的网络");
		} else if (errorCode == ServerConstant.ReturnCode.STATUS_INTENAL_ERROR)
		{
			alert("网络异常，请检查您的网络");
			
		} else if (errorCode == ServerConstant.ReturnCode.OTHER_ERROR)
		{
			alert("网络异常，请稍后重试");
			
		} else
		{
			alert("用户名或密码错误");
		}
		
	}
}
