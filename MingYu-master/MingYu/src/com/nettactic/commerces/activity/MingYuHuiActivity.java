package com.nettactic.commerces.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.nettactic.commerces.R;

/**
 * webView显示的明宇会介绍
 * 
 */
public class MingYuHuiActivity extends BaseActivity
{
	private WebView wv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		setTitle("关于明宇会");
		setleftIcon(R.drawable.home);
		setRightIcon(R.drawable.phone);
		
		wv = (WebView) findViewById(R.id.webView);
		wv.loadUrl("file:///android_asset/aboutmy.html");
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.homeTitle_Right:
			callMingYu();
			break;
		
		default:
			break;
		}
		super.onClick(v);
	}
}
