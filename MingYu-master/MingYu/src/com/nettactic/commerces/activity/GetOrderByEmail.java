package com.nettactic.commerces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;

public class GetOrderByEmail extends BaseActivity
{
	private EditText et_Email, et_OrderNo;
	
	private Button btn_Check, btn_Login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getorderbyemail);
		setTitle("查询订单");
		setleftIcon(R.drawable.t_backarr);
		initView();
	}
	
	private void initView()
	{
		et_Email = (EditText) findViewById(R.id.gobe_Email);
		et_OrderNo = (EditText) findViewById(R.id.gobe_OrderNo);
		btn_Check = (Button) findViewById(R.id.gobe_submit);
		btn_Login = (Button) findViewById(R.id.gobe_Login);
		
		btn_Login.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(GetOrderByEmail.this,
						LoginActivity.class);
				startActivity(intent);
				GetOrderByEmail.this.finish();
			}
		});
		btn_Check.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(GetOrderByEmail.this,
						MyOrderActivity.class);
				intent.putExtra(Constant.EMAIL,
						null != et_Email.getText() ? et_Email.getText()
								.toString() : "");
				intent.putExtra(Constant.ORDERNO,
						null != et_OrderNo.getText() ? et_OrderNo.getText()
								.toString() : "");
				startActivity(intent);
			}
		});
	}
	
}
