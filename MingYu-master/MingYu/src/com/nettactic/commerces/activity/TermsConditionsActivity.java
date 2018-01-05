package com.nettactic.commerces.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 条款与细则
 * 
 * @author ruan
 * 
 */
public class TermsConditionsActivity extends BaseActivity implements
		ReqResultImpl
{

	private TextView tv_MemberShip, tv_NoticeForUser, tv_ExpenseTip,
			tv_InteRule;

	/** 会员卡使用须知 */
	public static String HYKSXZ = "hyksxz";

	/** 会员资格与等级 */
	public static String HYZDJ = "hyzdj";

	/** 消费提示 */
	public static String XFTS = "xfts";

	/** 积分规则 */
	public static String JFGZ = "jfgz";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_termsconditions);
		setTitle("条款与细则");
		setleftIcon(R.drawable.t_backarr);
		initView();
		initData();
	}

	private void initView()
	{
		tv_MemberShip = (TextView) findViewById(R.id.tc_MemberShip);
		tv_NoticeForUser = (TextView) findViewById(R.id.tc_NoticeForUser);
		tv_ExpenseTip = (TextView) findViewById(R.id.tc_ExpenseTip);
		tv_InteRule = (TextView) findViewById(R.id.tc_InteRule);

		tv_MemberShip.setOnClickListener(this);
		tv_NoticeForUser.setOnClickListener(this);
		tv_ExpenseTip.setOnClickListener(this);
		tv_InteRule.setOnClickListener(this);
	}

	private void initData()
	{

	}

	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent(this, WebViewActivity.class);
		switch (v.getId())
		{
		case R.id.tc_MemberShip:
		{
			intent.putExtra(WebViewActivity.URLKRY, HYZDJ);
			break;
		}
		case R.id.tc_NoticeForUser:
		{

			intent.putExtra(WebViewActivity.URLKRY, HYKSXZ);
			break;
		}
		case R.id.tc_ExpenseTip:
		{
			intent.putExtra(WebViewActivity.URLKRY, XFTS);

			break;
		}
		case R.id.tc_InteRule:
		{
			intent.putExtra(WebViewActivity.URLKRY, JFGZ);

			break;
		}

		default:
			break;
		}
		startActivity(intent);
		super.onClick(v);
	}

	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		// TODO Auto-generated method stub

	}

}
