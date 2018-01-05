package com.nettactic.commerces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.nettactic.commerces.R;

public class RestaurantDetail extends BaseActivity
{
	private WebView webView;
	
	public static final String RESTAURANTDETAIL = "RestaurantDetail";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		setTitle("餐厅简介");
		setleftIcon(R.drawable.t_backarr);
		
		Intent intent = getIntent();
		if (intent.hasExtra(RESTAURANTDETAIL))
		{
			String value = intent.getStringExtra(RESTAURANTDETAIL);
			
			webView = (WebView) findViewById(R.id.webView);
			webView.loadUrl(value);
		} else
		{
			
		}
		
	}
}
