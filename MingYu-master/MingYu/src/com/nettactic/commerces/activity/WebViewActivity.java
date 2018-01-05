package com.nettactic.commerces.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.nettactic.commerces.R;

public class WebViewActivity extends BaseActivity
{
	private WebView webView;
	
	// public static final String WEBURL = "WebUrl";
	
	public static final String URLKRY = "UrlKey";
	
	public static final String ENTERPRISECULTURE = "enterpriseculture";
	public static final String JOIN = "join";
	public static final String CONTACTUS = "contactus";
	public static final String ABOUTUS = "aboutus";
	public static final String ITEMS = "items";
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_webview);
		
		Intent intent = getIntent();
		String value = null;
		if (intent.hasExtra(URLKRY))
		{
			value = intent.getStringExtra(URLKRY);
			webView = (WebView) findViewById(R.id.webView);
			webView.getSettings().setJavaScriptEnabled(true);
			
			if (value.equals(ENTERPRISECULTURE))
			{
				setTitle("企业文化");
				
			} else if (value.equals(JOIN))
			{
				setTitle("招商加盟");
				
			} else if (value.equals(CONTACTUS))
			{
				setTitle("联系我们");
				
			} else if (value.equals(ABOUTUS))
			{
				setTitle("集团介绍");
				
			} else if (value.equals(ITEMS))
			{
				setTitle("条款");
				
			} else
			{
				setTitle("");
			}
			
		} else
		{
			
		}
		setleftIcon(R.drawable.t_backarr);
		webView.loadUrl("file:///android_asset/" + value + ".html");
		
	}
}
