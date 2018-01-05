package com.nettactic.commerces.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;

/**
 * 信息反馈
 * 
 * @author ruan
 * 
 */
public class OpinionActivity extends BaseActivity implements ReqResultImpl
{
	private RadioGroup bg_Member, bg_Sex;
	private CheckBox cb_Check;
	LinearLayout ll_in, ll_out;
	private EditText et_Name, et_Mobile, et_Email, et_Msg, et_InData,
			et_OutData;
	
	private Button btn_Submit;
	
	private String[] hotels;
	
	private int SelectedId = 0;
	
	private final int INTIME = 2000;
	private final int OUTTIME = 2001;
	public final String DATAVALUE = "datavalue";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messagefeedback);
		setTitle("意见反馈");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initData();
	}
	
	private void initView()
	{
		ll_in = (LinearLayout) findViewById(R.id.ll_indate);
		ll_out = (LinearLayout) findViewById(R.id.ll_outdate);
		bg_Member = (RadioGroup) findViewById(R.id.ifMem);
		bg_Sex = (RadioGroup) findViewById(R.id.sex);
		et_InData = (EditText) findViewById(R.id.et_indate);
		et_OutData = (EditText) findViewById(R.id.et_outdate);
		et_Name = (EditText) findViewById(R.id.mfb_Call);
		et_Mobile = (EditText) findViewById(R.id.mfb_Mobile);
		et_Email = (EditText) findViewById(R.id.mfb_Email);
		et_Msg = (EditText) findViewById(R.id.mfb_Msg);
		btn_Submit = (Button) findViewById(R.id.mfb_Submit);
		
		btn_Submit.setOnClickListener(this);
		ll_in.setOnClickListener(this);
		ll_out.setOnClickListener(this);
		et_InData.setOnClickListener(this);
		et_OutData.setOnClickListener(this);
		
	}
	
	private void initData()
	{
		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.DAY_OF_YEAR, 0);
		Date lastDate = lastYear.getTime();
		in_IntentDate = lastDate;
		
		UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
		et_Name.setText(userInfoModel.getLastName()
				+ userInfoModel.getFirstName());
		et_Mobile.setText(userInfoModel.getMobile());
		et_Email.setText(userInfoModel.getEmail());
		
		// new LoadingAsync(OpinionActivity.this,
		// RequestMethod.METHOD_GETHOTELS)
		// .execute();
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.mfb_Submit:
		{
			submitMsg();
			break;
		}
		
		case R.id.et_indate:
		case R.id.ll_indate:
		{
			Intent intent = new Intent(this, CalendarViewActivity.class);
			startActivityForResult(intent, INTIME);
			break;
		}
		case R.id.et_outdate:
		case R.id.ll_outdate:
		{
			Intent intent = new Intent(this, CalendarViewActivity.class);
			intent.putExtra(DATAVALUE, in_IntentDate);
			startActivityForResult(intent, OUTTIME);
			break;
		}
		
		default:
			break;
		}
		super.onClick(v);
	}
	
	private Date in_IntentDate;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		
		Date date = (Date) data.getSerializableExtra(DATAVALUE);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		switch (requestCode)
		{
		case INTIME:
		{
			in_IntentDate = date;
			et_InData.setText(simpleDateFormat.format(date));
			break;
		}
		case OUTTIME:
		{
			et_OutData.setText(simpleDateFormat.format(date));
			break;
		}
		
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
	 * 1. isMember 是否为明宇会会员 0否1是
	 * 
	 * 2. Name 姓名
	 * 
	 * 3. Gender M男/F女性别
	 * 
	 * 4. Phone 手机号
	 * 
	 * 5. Email 邮箱
	 * 
	 * 6. Content 意见反馈内容
	 * 
	 * 7. 入住日期Indate
	 * 
	 * 8. 离店日期Outdate
	 * 
	 * 其中 1，2，7，8 传空
	 */
	private void submitMsg()
	{
		if (!ActivityUtil.isEmpty(et_Msg.getText().toString()))
		{
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("isMember", ""));
			// params.add(new BasicNameValuePair("isMember", bg_Member
			// .getCheckedRadioButtonId() == R.id.yes ? "1" : "0"));
			params.add(new BasicNameValuePair("Gender", bg_Sex
					.getCheckedRadioButtonId() == R.id.male ? "M" : "F"));
			params.add(new BasicNameValuePair("Name", ""));
			// params.add(new BasicNameValuePair("Name",
			// null != et_Name.getText() ? et_Name.getText().toString() : ""));
			params.add(new BasicNameValuePair("Phone", null != et_Mobile
					.getText() ? et_Mobile.getText().toString() : ""));
			params.add(new BasicNameValuePair("Email", null != et_Email
					.getText() ? et_Email.getText().toString() : ""));
			
			params.add(new BasicNameValuePair("Content", null != et_Msg
					.getText() ? et_Msg.getText().toString() : ""));
			params.add(new BasicNameValuePair("Indate", ""));
			params.add(new BasicNameValuePair("Outdate", ""));
			// params.add(new BasicNameValuePair("Indate",
			// null != et_InData.getText() ? et_InData.getText().toString()
			// : ""));
			// params.add(new BasicNameValuePair("Outdate", null != et_OutData
			// .getText() ? et_OutData.getText().toString() : ""));
			
			new LoadingAsync(this, RequestMethod.METHOD_FEEDBACK, params)
					.execute();
		} else
		{
			alert("请填写意见反馈信息后重试");
		}
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		switch (methodName)
		{
		case METHOD_FEEDBACK:
		{
			alert("提交成功，感谢您的支持");
			finish();
			break;
		}
		
		default:
			break;
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("网络异常，请稍候重试");
	}
	
}
