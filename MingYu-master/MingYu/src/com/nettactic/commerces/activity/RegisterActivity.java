package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.EditTexts;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.util.LogUtil;
import com.nettactic.commerces.widget.DialogTool;

/**
 * 会员注册
 * 
 * @author ruan
 * 
 */
public class RegisterActivity extends BaseActivity implements ReqResultImpl
{
	
	private static final String TAG = "RegisterActivity";
	
	private RadioGroup rg_Call;
	
	private CheckBox cb_Check;
	
	private TextView tv_ViewTerm;
	
	private Button btn_register;
	
	private Map<EditTexts, EditText> editTextsMap;
	
	private Map<EditTexts, String> errorTips;
	
	private Map<EditTexts, String> keys;
	
	private List<EditTexts> editTextsList;
	
	private List<NameValuePair> params;
	
	public static final String RULE = "items";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nettactic.commerce.activity.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		setTitle("注册");
		setleftIcon(R.drawable.t_backarr);
		
		params = new ArrayList<NameValuePair>();
		
		initView();
		
	}
	
	private void initView()
	{
		
		rg_Call = (RadioGroup) findViewById(R.id.reg_Call);
		cb_Check = (CheckBox) findViewById(R.id.checkBox1);
		tv_ViewTerm = (TextView) findViewById(R.id.reg_ViewTerm);
		btn_register = (Button) findViewById(R.id.reg_Register);
		tv_ViewTerm.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		
		initEditTextsList();
		
		initEditTextsMap();
		
		initErrorTips();
		
		initKeys();
		
		btn_register.setOnClickListener(this);
		tv_ViewTerm.setOnClickListener(this);
	}
	
	/**
	 * 初始化EditText对应的Key
	 */
	private void initKeys()
	{
		keys = new HashMap<Constant.EditTexts, String>();
		keys.put(EditTexts.FIRSTNAME, "firstName");
		keys.put(EditTexts.LASTNAME, "lastName");
		keys.put(EditTexts.IDNO, "IDNo");
		keys.put(EditTexts.PHONE, "mobile");
		keys.put(EditTexts.EMAIL, "email");
		keys.put(EditTexts.ADDR, "address");
		keys.put(EditTexts.POSTALCODE, "postalCode");
		keys.put(EditTexts.GENDER, "gender");
	}
	
	/**
	 * 初始化错误提示信息
	 */
	private void initErrorTips()
	{
		errorTips = new HashMap<Constant.EditTexts, String>();
		errorTips.put(EditTexts.FIRSTNAME, "姓氏");
		errorTips.put(EditTexts.LASTNAME, "名字");
		errorTips.put(EditTexts.IDNO, "身份证号");
		errorTips.put(EditTexts.PHONE, "手机号码");
		errorTips.put(EditTexts.EMAIL, "邮箱");
		errorTips.put(EditTexts.ADDR, "地址");
		errorTips.put(EditTexts.POSTALCODE, "邮编");
	}
	
	private void initEditTextsMap()
	{
		editTextsMap = new HashMap<Constant.EditTexts, EditText>();
		editTextsMap.put(EditTexts.FIRSTNAME,
				(EditText) findViewById(R.id.reg_FirstName));
		editTextsMap.put(EditTexts.LASTNAME,
				(EditText) findViewById(R.id.reg_lastName));
		editTextsMap.put(EditTexts.IDNO,
				(EditText) findViewById(R.id.reg_IDNum));
		editTextsMap.put(EditTexts.PHONE,
				(EditText) findViewById(R.id.reg_PhoneNum));
		editTextsMap.put(EditTexts.EMAIL,
				(EditText) findViewById(R.id.reg_EMail));
		editTextsMap.put(EditTexts.ADDR,
				(EditText) findViewById(R.id.reg_Address));
		editTextsMap.put(EditTexts.POSTALCODE,
				(EditText) findViewById(R.id.reg_PostalCode));
	}
	
	private void initEditTextsList()
	{
		editTextsList = new ArrayList<EditTexts>()
		{
			/**
             * 
             */
			private static final long serialVersionUID = 1L;
			
			{
				add(EditTexts.FIRSTNAME);
				add(EditTexts.LASTNAME);
				add(EditTexts.IDNO);
				add(EditTexts.PHONE);
				add(EditTexts.EMAIL);
				add(EditTexts.ADDR);
				add(EditTexts.POSTALCODE);
			}
		};
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.reg_Register:
		{
			if (checkValuesOfTypeIn())
			{
				// 先生
				if (rg_Call.getCheckedRadioButtonId() == R.id.reg_Men)
				{
					params.add(new BasicNameValuePair(keys
							.get(EditTexts.GENDER), "M"));
				}
				
				// 女士
				else if (rg_Call.getCheckedRadioButtonId() == R.id.reg_Women)
				{
					
					params.add(new BasicNameValuePair(keys
							.get(EditTexts.GENDER), "F"));
				}
				new LoadingAsync(RegisterActivity.this,
						RequestMethod.METHOD_REGISTER, params).execute();
			} else
			{
				
			}
			
			break;
		}
		case R.id.reg_ViewTerm:
		{
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.URLKRY, RULE);
			startActivity(intent);
			break;
		}
		default:
			break;
		}
		
		super.onClick(v);
	}
	
	/**
	 * 验证用户输入的信息是否符合规范
	 */
	private boolean checkValuesOfTypeIn()
	{
		params.clear();
		EditText et;
		String tip;
		String key;
		for (EditTexts item : editTextsList)
		{
			et = editTextsMap.get(item);
			key = keys.get(item);
			tip = errorTips.get(item);
			
			if (ActivityUtil.verifyField(item, et.getText().toString().trim()))
			{
				params.add(new BasicNameValuePair(key, et.getText().toString()
						.trim()));
			} else
			{
				alert("请填写有效的" + tip);
				ActivityUtil.Vibrate(this, et);
				return false;
			}
		}
		
		if (cb_Check.isChecked())
		{
			
		} else
		{
			ActivityUtil.Vibrate(this, cb_Check);
			alert("请先阅读我们的使用条款！");
			return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nettactic.commerce.service.impl.ReqResultImpl#reqResultSuccess(org
	 * .json.JSONObject)
	 */
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		
		Log.d(TAG, "jsonResult : " + jsonResult);
		JSONObject data = jsonResult.optJSONObject(Constant.JSON_DATA);
		if (!ActivityUtil.isEmpty(data.optString("CardNo"))
				&& !ActivityUtil.isEmpty(data.optString("Password")))
		{
			String str = "注册成功！卡号为" + data.optString("CardNo") + "初始密码为"
					+ data.optString("Password") + "并以短信的形式发送到您的手机，请查收";
			showLoginDialog(str);
		} else
		{
			alert("注册失败，请稍后重试或者联系我们的客服热线。");
		}
		// alert("注册成功，请登录！");
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nettactic.commerce.service.impl.ReqResultImpl#reqResultFail(int)
	 */
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
		if (MyConfig.ifDebug)
		{
			LogUtil.d(TAG, methodName.toString());
		}
		switch (errorCode)
		{
		case 3004:
		{
			alert("注册信息已被使用");
			break;
		}
		default:
		{
			alert("相同电子邮箱的会员卡号已经存在！请重试或者联系我们的客服。");
			break;
		}
		}
		
	}
	
	private void showLoginDialog(String tip)
	{
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				this)
				.setIcon(this.getResources().getDrawable(R.drawable.circle))
				.setTitle("提示")
				.setMessage(tip)
				.setPositiveButton("登录", new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						Intent intent = new Intent(RegisterActivity.this,
								LoginActivity.class);
						startActivity(intent);
						RegisterActivity.this.finish();
					}
				})
				.setNegativeButton("修改密码",
						new DialogInterface.OnClickListener()
						{
							
							@Override
							public void onClick(DialogInterface arg0, int arg1)
							{
								Intent intent = new Intent(
										RegisterActivity.this,
										ModifyPassWordActivity.class);
								startActivity(intent);
								RegisterActivity.this.finish();
							}
						});
		// 创建一个普通对话框
		dialog = builder.create();
		dialog.show();
		
	}
}
