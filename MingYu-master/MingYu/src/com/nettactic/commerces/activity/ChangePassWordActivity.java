package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 修改密码
 * 
 * currentPwd newPwd confirmPwd
 * 
 * 
 */
public class ChangePassWordActivity extends BaseActivity implements
		ReqResultImpl
{
	private EditText et_CurrentPWD, et_NewPWD, et_ConfirmPwd;
	
	private Button btn_submit;
	
	private List<NameValuePair> params;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepassword);
		setTitle("修改密码");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
	}
	
	private void initView()
	{
		params = new ArrayList<NameValuePair>();
		
		et_CurrentPWD = (EditText) findViewById(R.id.cpw_currentpwd);
		et_NewPWD = (EditText) findViewById(R.id.cpw_newpwd);
		et_ConfirmPwd = (EditText) findViewById(R.id.cpw_confirmpwd);
		btn_submit = (Button) findViewById(R.id.cpw_submit);
		btn_submit.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				if (et_CurrentPWD.getText().toString()
						.equals(et_NewPWD.getText().toString()))
				{
					alert("两次输入的密码不一致");
				} else
				{
					UserInfoModel userInfoModel = UserInfoModel
							.getUserInfoModel();
					params.add(new BasicNameValuePair("CardNo", userInfoModel
							.getCardNo()));
					params.add(new BasicNameValuePair("OldPwd", et_CurrentPWD
							.getText().toString()));
					params.add(new BasicNameValuePair("NewPwd", et_NewPWD
							.getText().toString()));
					new LoadingAsync(ChangePassWordActivity.this,
							RequestMethod.METHOD_CHANGEPASSWORD, params)
							.execute();
				}
			}
		});
	}
	
	/**
	 * 1003 没有传CardNo 1004卡号错误 1006 超时 1001成功
	 */
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		alert(jsonResult.toString());
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("网络异常,请重试");
	}
}
