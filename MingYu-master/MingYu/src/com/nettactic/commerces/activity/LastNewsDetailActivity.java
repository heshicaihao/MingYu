package com.nettactic.commerces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.nettactic.commerces.R;

/**
 * 新闻详情
 * 
 * 
 */
public class LastNewsDetailActivity extends BaseActivity
{
	private WebView webView;
	
	public static final String CONTENTURL = "ContentUrl";
	
	private String contentUrl = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		setTitle("最新资讯");
		setleftIcon(R.drawable.t_backarr);
		initView();
		initData();
	}
	
	private void initView()
	{
		webView = (WebView) findViewById(R.id.webView);
		
	}
	
	private void initData()
	{
		
		Intent intent = getIntent();
		if (intent.hasExtra(CONTENTURL))
		{
			contentUrl = intent.getStringExtra(CONTENTURL);
			webView.loadUrl(contentUrl);
		} else
		{
			
		}
		
	}
	
}
