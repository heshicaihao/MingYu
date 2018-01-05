package com.nettactic.commerces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.nettactic.commerces.R;

/**
 * 关于明宇
 * 
 */
public class AboutMingYuActivity extends BaseActivity implements
		OnClickListener
{
	/**
	 * 最新资讯
	 */
	private TextView tv_News;
	/**
	 * 集团介绍
	 */
	private TextView tv_GroupIntro;
	/**
	 * 品牌介绍
	 */
	private TextView tv_BrandIntro;
	/**
	 * 企业文化
	 */
	private TextView tv_EnterpriseCulture;
	/**
	 * 招商加盟
	 */
	private TextView tv_Join;
	/**
	 * 联系我们
	 */
	private TextView tv_ContactUs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutmingyu);
		setTitle("关于明宇");
		setleftIcon(R.drawable.t_backarr);
		initView();
	}
	
	private void initView()
	{
		tv_News = (TextView) findViewById(R.id.my_News);
		tv_GroupIntro = (TextView) findViewById(R.id.my_GroupIntro);
		tv_BrandIntro = (TextView) findViewById(R.id.my_BrandIntro);
		tv_EnterpriseCulture = (TextView) findViewById(R.id.my_EnterpriseCulture);
		tv_Join = (TextView) findViewById(R.id.my_Join);
		tv_ContactUs = (TextView) findViewById(R.id.my_ContactUs);
		
		tv_News.setOnClickListener(this);
		tv_GroupIntro.setOnClickListener(this);
		tv_BrandIntro.setOnClickListener(this);
		tv_EnterpriseCulture.setOnClickListener(this);
		tv_Join.setOnClickListener(this);
		tv_ContactUs.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v)
	{
		super.onClick(v);
		Intent intent = null;
		
		switch (v.getId())
		{
		case R.id.my_News:
		{
			intent = new Intent(AboutMingYuActivity.this,
					LastNewsActivity.class);
			break;
		}
		case R.id.my_GroupIntro:
		{
			
			intent = new Intent(AboutMingYuActivity.this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.URLKRY, WebViewActivity.ABOUTUS);
			break;
		}
		case R.id.my_BrandIntro:
		{
			intent = new Intent(AboutMingYuActivity.this,
					BrandIntroActivity.class);
			
			// intent = new Intent(AboutMingYuActivity.this,
			// BrandIntroActivity.class);
			
			break;
		}
		case R.id.my_EnterpriseCulture:
		{
			intent = new Intent(AboutMingYuActivity.this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.URLKRY,
					WebViewActivity.ENTERPRISECULTURE);
			break;
		}
		case R.id.my_Join:
		{
			intent = new Intent(AboutMingYuActivity.this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.URLKRY, WebViewActivity.JOIN);
			break;
		}
		case R.id.my_ContactUs:
		{
			intent = new Intent(AboutMingYuActivity.this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.URLKRY, WebViewActivity.CONTACTUS);
			break;
		}
		default:
			break;
		}
		
		if (null != intent)
		{
			
			startActivity(intent);
			
		} else
		{
			
		}
		
	}
}
