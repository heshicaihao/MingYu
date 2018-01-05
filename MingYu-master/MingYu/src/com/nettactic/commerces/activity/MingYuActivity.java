package com.nettactic.commerces.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;

/**
 * 明宇会
 * 
 */
public class MingYuActivity extends BaseActivity implements ReqResultImpl
{
	private Button btn_Login, btn_Regist;
	
	private TextView tv_Tip;
	
	private LinearLayout ll_MyOder, ll_CreditsLog, ll_GiftShop, ll_UserInfo,
			ll_About, ll_Own, ll_LoginOut, ll_Button, ll_MyChange;
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_mingyu);
		setTitleImage(R.drawable.small_logo);
		setleftIcon(R.drawable.t_backarr);
		
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
		btn_Login = (Button) findViewById(R.id.my_Login);
		btn_Regist = (Button) findViewById(R.id.my_Registe);
		tv_Tip = (TextView) findViewById(R.id.my_Tip);
		ll_MyOder = (LinearLayout) findViewById(R.id.my_MyOrder);
		ll_CreditsLog = (LinearLayout) findViewById(R.id.my_CreditsLog);
		ll_GiftShop = (LinearLayout) findViewById(R.id.my_GiftShop);
		ll_UserInfo = (LinearLayout) findViewById(R.id.my_UserInfo);
		ll_About = (LinearLayout) findViewById(R.id.my_AboutMingYu);
		ll_Own = (LinearLayout) findViewById(R.id.my_own);
		ll_LoginOut = (LinearLayout) findViewById(R.id.my_LoginOut);
		ll_Button = (LinearLayout) findViewById(R.id.my_button);
		ll_MyChange = (LinearLayout) findViewById(R.id.my_myChange);
		
		btn_Login.setOnClickListener(this);
		btn_Regist.setOnClickListener(this);
		ll_MyOder.setOnClickListener(this);
		ll_CreditsLog.setOnClickListener(this);
		ll_GiftShop.setOnClickListener(this);
		ll_UserInfo.setOnClickListener(this);
		ll_About.setOnClickListener(this);
		ll_Own.setOnClickListener(this);
		ll_LoginOut.setOnClickListener(this);
		ll_MyChange.setOnClickListener(this);
	}
	
	private void initData()
	{
		
		if (MyConfig.isLogin)
		{
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			String call = "";
			if (userInfoModel.getSex().equals("M"))
			{
				call = "先生";
			} else if (userInfoModel.getSex().equals("F"))
			{
				
				call = "女士";
			}
			
			tv_Tip.setText(userInfoModel.getFirstName()
					+ userInfoModel.getLastName() + ",欢迎您！" + "	状态:"
					+ userInfoModel.getUserState() + "\n可用积分："
					+ userInfoModel.getTotlePoints() + "分	账户余额："
					+ userInfoModel.getBalance() + "元");
			// 尊敬的 阮先生\n欢迎您来到会员社区，会员状态为:活跃
			ll_Button.setVisibility(View.GONE);
		} else
		{
			tv_Tip.setText("欢迎您来明宇会");
		}
		
		if (Boolean.TRUE.equals(MyConfig.isLogin))
		{
			ll_LoginOut.setVisibility(View.VISIBLE);
			ll_MyChange.setVisibility(View.VISIBLE);
		} else
		{
			
			ll_LoginOut.setVisibility(View.GONE);
			ll_MyChange.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onClick(View v)
	{
		Intent intent;
		switch (v.getId())
		{
		case R.id.my_Login:
		{
			intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			
			break;
		}
		case R.id.my_Registe:
		{
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			
			break;
		}
		case R.id.my_MyOrder:
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
			break;
		}
		// 积分记录
		case R.id.my_CreditsLog:
		{
			if (MyConfig.isLogin)
			{
				intent = new Intent(this, CreditsLogActivity.class);
				startActivity(intent);
			} else
			{
				alert("您还未登录，请登录！");
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
			break;
		}
		// 礼品商场
		case R.id.my_GiftShop:
		{
			// intent = new Intent(this, GiftShopActivity.class);
			// startActivity(intent);
			
			intent = new Intent(this, GiftShopActivity.class);
			startActivity(intent);
			break;
		}
		// 个人信息
		case R.id.my_UserInfo:
		{
			if (Boolean.TRUE.equals(MyConfig.isLogin))
			{
				intent = new Intent(this, UserInfoActivity.class);
				startActivity(intent);
			} else
			{
				alert("您还未登录，请登录！");
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
			break;
		}
		// 关于明宇
		case R.id.my_AboutMingYu:
		{
			intent = new Intent(this, MingYuHuiActivity.class);
			startActivity(intent);
			break;
		}
		// 条款与细则
		case R.id.my_own:
		{
			intent = new Intent(this, SpecialOffersActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.my_myChange:
		{
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			intent = new Intent(this, MyGIftActivity.class);
			intent.putExtra(Constant.ORDERNO, userInfoModel.getCardNo());
			startActivity(intent);
			break;
		}
		// 注销
		case R.id.my_LoginOut:
		{
			
			if (null == dialog || !dialog.isShowing())
			{
				android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
						this);
				// 设置对话框的图标
				builder.setIcon(this.getResources().getDrawable(
						R.drawable.circle));
				// 设置对话框的标题
				builder.setTitle("提示");
				// 设置对话框的显示内容
				builder.setMessage("确认退出登录吗？");
				// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener()
						{
							
							@Override
							public void onClick(DialogInterface arg0, int arg1)
							{
								
								MyConfig.isLogin = false;
								initData();
								Map<String, String> map = new HashMap<String, String>();
								ActivityUtil.saveConfig(MingYuActivity.this,
										LoginActivity.IFAUTOLOGIN, Constant.NO);
								alert("已退出登录");
								ll_Button.setVisibility(View.VISIBLE);
								
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener()
						{
							
							@Override
							public void onClick(DialogInterface arg0, int arg1)
							{
								dialog.cancel();
							}
						});
				dialog = builder.create();
				dialog.show();
				// 创建一个普通对话框
				
			}
			
			break;
		}
		
		default:
			break;
		}
		super.onClick(v);
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		/** 登录成功，启动心跳 */
		switch (methodName)
		{
		case METHOD_LOGINOUT:
		{
			MyConfig.isLogin = false;
			// Intent serviceIntent = new Intent(this,
			// AngelBeatService.class);
			// serviceIntent.setAction(AngelBeatService.ACTION);
			// stopService(serviceIntent);
			initData();
			Map<String, String> map = new HashMap<String, String>();
			ActivityUtil.saveConfig(this, LoginActivity.IFAUTOLOGIN,
					Constant.NO);
			
			alert("已退出登录");
			ll_Button.setVisibility(View.VISIBLE);
			break;
		}
		
		default:
			break;
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		/** 登录成功，启动心跳 */
		switch (methodName)
		{
		case METHOD_LOGINOUT:
		{
			
			alert("注销异常，请稍后重试");
			break;
		}
		
		default:
			break;
		}
		
	}
}
