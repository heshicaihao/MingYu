package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 个人信息
 * 
 * @author ruan
 * 
 */
public class UserInfoActivity extends BaseActivity implements ReqResultImpl
{
	private EditText et_LastName, et_ID, et_Mobile, et_Mail, et_Addr, et_Card,
			et_Nationality, et_Language, et_OfficePhone, et_HomePhone, et_Fax,
			et_PostalCode;
	
	private TextView tv_ChangePsw, tv_ModifyPsw;
	
	private Button btn_Submit;
	
	private RadioGroup rg_Sex;
	
	private String sex;
	
	private static final String MEN = "M";
	
	private static final String WOMEN = "F";
	
	private UserInfoModel userInfoModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		setTitle("个人信息");
		setleftIcon(R.drawable.t_backarr);
		userInfoModel = UserInfoModel.getUserInfoModel();
		
		initView();
		initData();
		
	}
	
	private void initView()
	{
		et_LastName = (EditText) findViewById(R.id.ui_LastName);
		et_ID = (EditText) findViewById(R.id.ui_ID);
		et_Mobile = (EditText) findViewById(R.id.ui_Mobile);
		et_Mail = (EditText) findViewById(R.id.ui_Mail);
		et_Addr = (EditText) findViewById(R.id.ui_Addr);
		et_Card = (EditText) findViewById(R.id.ui_Card);
		
		et_Nationality = (EditText) findViewById(R.id.ui_Nationality);
		et_Language = (EditText) findViewById(R.id.ui_Language);
		et_OfficePhone = (EditText) findViewById(R.id.ui_OfficePhone);
		et_HomePhone = (EditText) findViewById(R.id.ui_HomePhone);
		et_Fax = (EditText) findViewById(R.id.ui_Fax);
		et_PostalCode = (EditText) findViewById(R.id.ui_PostalCode);
		
		tv_ChangePsw = (TextView) findViewById(R.id.ui_ChangePsw);
		tv_ModifyPsw = (TextView) findViewById(R.id.ui_ModifyPsw);
		
		btn_Submit = (Button) findViewById(R.id.ui_submit);
		rg_Sex = (RadioGroup) findViewById(R.id.ui_Sex);
		
		tv_ChangePsw.setOnClickListener(this);
		tv_ModifyPsw.setOnClickListener(this);
		btn_Submit.setOnClickListener(this);
		rg_Sex.setOnCheckedChangeListener(OnCheckedChangeListener);
	}
	
	private void initData()
	{
		et_Card.setText(userInfoModel.getCardNo());
		et_LastName.setText(userInfoModel.getFirstName()
				+ userInfoModel.getLastName());
		et_ID.setText(userInfoModel.getIDNo());
		et_Mobile.setText(userInfoModel.getMobile());
		et_Mail.setText(userInfoModel.getEmail());
		et_Addr.setText(userInfoModel.getAddress());
		
		et_Nationality.setText(userInfoModel.getNationality());
		et_Language.setText(userInfoModel.getLanguage());
		et_OfficePhone.setText(userInfoModel.getOfficePhone());
		et_HomePhone.setText(userInfoModel.getHomePhone());
		et_Fax.setText(userInfoModel.getFax());
		et_PostalCode.setText(userInfoModel.getPostalCode());
		
		if (userInfoModel.getSex().equals(MEN))
		{
			
			rg_Sex.check(R.id.ui_Men);
			sex = MEN;
		} else if (userInfoModel.getSex().equals(WOMEN))
		{
			rg_Sex.check(R.id.ui_Women);
			sex = WOMEN;
		} else
		{
			
		}
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.ui_ChangePsw:
		{
			Intent intent = new Intent(this, ChangePassWordActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.ui_ModifyPsw:
		{
			Intent intent = new Intent(this, ModifyPassWordActivity.class);
			startActivity(intent);
			
			break;
		}
		case R.id.ui_submit:
		{
			
			changeUserInfo();
			break;
		}
		
		default:
			break;
		}
		super.onClick(v);
	}
	
	private void changeUserInfo()
	{
		userInfoModel = UserInfoModel.getUserInfoModel();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("CardNo", userInfoModel.getCardNo()));
		params.add(new BasicNameValuePair("FirstName", ""));
		params.add(new BasicNameValuePair("LastName", et_LastName.getText()
				.toString()));
		params.add(new BasicNameValuePair("Email", et_Mail.getText().toString()));
		params.add(new BasicNameValuePair("Mobile", et_Mobile.getText()
				.toString()));
		params.add(new BasicNameValuePair("Identify", et_ID.getText()
				.toString()));
		params.add(new BasicNameValuePair("Sex", sex));
		params.add(new BasicNameValuePair("Address", et_Addr.getText()
				.toString()));
		params.add(new BasicNameValuePair("PostalCode", ""));
		new LoadingAsync(this, RequestMethod.METHOD_MODIFYINFOMARTION, params)
				.execute();
	}
	
	private OnCheckedChangeListener OnCheckedChangeListener = new OnCheckedChangeListener()
	{
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			switch (checkedId)
			{
			case R.id.ui_Men:
			{
				sex = MEN;
				break;
			}
			case R.id.ui_Women:
			{
				sex = WOMEN;
				break;
			}
			}
		}
	};
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		
		alert("修改信息成功");
		
		userInfoModel.setFirstName("");
		userInfoModel.setLastName(et_LastName.getText().toString());
		userInfoModel.setEmail(et_Mail.getText().toString());
		userInfoModel.setMobile(et_Mobile.getText().toString());
		userInfoModel.setIDNo(et_ID.getText().toString());
		userInfoModel.setSex(sex);
		userInfoModel.setAddress(et_Addr.getText().toString());
		userInfoModel.setPostalCode("");
		
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
		alert("修改信息异常，请核对信息后重试");
	}
	
}
