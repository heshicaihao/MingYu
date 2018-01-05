package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.widget.DialogTool;

/**
 * 找回密码
 * 
 * 
 * cardNo mobleNo cardType
 */
public class ModifyPassWordActivity extends BaseActivity implements
		ReqResultImpl
{
	private EditText et_CardNum, et_Mobile, et_CardType;
	
	private Button btn_submit;
	
	private List<NameValuePair> params;
	
	private int cardType = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifypassword);
		setTitle("找回密码");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
	}
	
	private void initView()
	{
		
		params = new ArrayList<NameValuePair>();
		
		et_CardNum = (EditText) findViewById(R.id.m_CardNum);
		et_Mobile = (EditText) findViewById(R.id.m_Mobile);
		et_CardType = (EditText) findViewById(R.id.m_CardType);
		btn_submit = (Button) findViewById(R.id.m_submit);
		
		et_CardType.setOnClickListener(this);
		btn_submit.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.m_submit:
		{
			
			changePsw();
			break;
		}
		case R.id.m_CardType:
		{
			initCardTypeDialog();
			break;
		}
		
		default:
			break;
		}
		super.onClick(v);
	}
	
	private void changePsw()
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("CardType", String.valueOf(cardType)));
		params.add(new BasicNameValuePair("CardNo", null != et_CardNum
				.getText() ? et_CardNum.getText().toString() : ""));
		params.add(new BasicNameValuePair("MobileNo", null != et_Mobile
				.getText() ? et_Mobile.getText().toString() : ""));
		
		new LoadingAsync(this, RequestMethod.METHOD_FINDPSW, params).execute();
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
						et_CardType.setText(menus[which]);
					}
				}).show();
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		
		alert("请留意您的密码重置短信");
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
		alert("您输入的账号信息不匹配，请重新输入");
		
	}
}
