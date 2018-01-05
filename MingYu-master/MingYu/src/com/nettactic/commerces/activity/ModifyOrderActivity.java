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

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.MyOrderModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 找回密码 cardNo mobleNo cardType
 */
public class ModifyOrderActivity extends BaseActivity implements ReqResultImpl
{
	public static final String ORDERNO = "OrderNo";

	public static final String LASTNAME = "lastName";

	public static final String FIRSTNAME = "firstName";

	public static final String PHONE = "phone";

	public static final String EMAIL = "email";

	public static final String REMARK = "Remark";

	public static final String SEX = "sex";

	private EditText et_LastName, et_FirstName, et_Mobile, et_Email, et_Msg;

	private RadioGroup RG;

	private Button btn_submit;

	private List<NameValuePair> params;

	private MyOrderModel myOrderModel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifyorder);
		setTitle("修改订单");
		setleftIcon(R.drawable.t_backarr);

		initView();
		initData();
	}

	private void initView()
	{
		params = new ArrayList<NameValuePair>();

		et_LastName = (EditText) findViewById(R.id.mpw_LastName);
		et_FirstName = (EditText) findViewById(R.id.mpw_FirstName);
		et_Mobile = (EditText) findViewById(R.id.mpw_Mobile);
		et_Email = (EditText) findViewById(R.id.mpw_Email);
		et_Msg = (EditText) findViewById(R.id.mpw_SpecialMsg);

		btn_submit = (Button) findViewById(R.id.mpw_Submit);

		btn_submit.setOnClickListener(this);
	}

	private void initData()
	{

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		myOrderModel = (MyOrderModel) bundle.getSerializable("myOrderModel");

		et_LastName.setText(myOrderModel.getLastName());
		et_FirstName.setText(myOrderModel.getFirstName());
		et_Mobile.setText(myOrderModel.getPhone());
		et_Email.setText(myOrderModel.getEmail());
		et_Msg.setText(myOrderModel.getRemark());
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{

		case R.id.mpw_Submit: {

			modifyOrderInfo();
			break;
		}

		default:
			break;
		}
		super.onClick(v);
	}

	private void modifyOrderInfo()
	{
		// CardNo，
		// OrderNo
		// FirstName = "";//名字
		// LastName = "";//姓
		// Email = "";
		// Mobile = "";
		// Remark = "";//特殊说明

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("OrderNo", myOrderModel.getOrderNo()));

		params.add(new BasicNameValuePair("FirstName", null != et_FirstName
				.getText() ? et_FirstName.getText().toString() : ""));

		params.add(new BasicNameValuePair("LastName", null != et_LastName
				.getText() ? et_LastName.getText().toString() : ""));

		params.add(new BasicNameValuePair("Email",
				null != et_Email.getText() ? et_Email.getText().toString() : ""));

		params.add(new BasicNameValuePair("Mobile",
				null != et_Mobile.getText() ? et_Mobile.getText().toString()
						: ""));

		params.add(new BasicNameValuePair("Remark",
				null != et_Msg.getText() ? et_Msg.getText().toString() : ""));
		if (MyConfig.isLogin)
		{
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			params.add(new BasicNameValuePair("CardNo", userInfoModel
					.getCardNo()));
			new LoadingAsync(this, RequestMethod.METHOD_MODIFYMYORDER, params)
					.execute();
		} else
		{
			new LoadingAsync(this, RequestMethod.METHOD_MODIFYORDER, params)
					.execute();

		}
	}

	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{

		alert("修改订单成功");
		finish();
	}

	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{

		alert("网络异常,请重试");

	}
}
