package com.nettactic.commerces.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.OrderModel;

public class HotelMapActivity extends Activity
{
	private WebView webView;
	
	private final static String TAG = "HotelMapActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotelmap);
		webView = (WebView) findViewById(R.id.webView);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		
		OrderModel ordelModel = OrderModel.getOrderModel();
		
		if (MyConfig.ifDebug)
		{
			Log.d(TAG,
					"ordelModel.getHotelMapUrl() "
							+ ordelModel.getHotelMapUrl());
		}
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(ordelModel.getHotelMapUrl());
		
	}
}
