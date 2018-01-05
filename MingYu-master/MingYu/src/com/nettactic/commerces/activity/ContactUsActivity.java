package com.nettactic.commerces.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.nettactic.commerces.R;

/**
 * 联系我们
 * 
 */
public class ContactUsActivity extends BaseActivity
{
	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		setTitle("联系我们");
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.phone);

		wv = (WebView) findViewById(R.id.webView);
		// wv.setWebViewClient(new WebViewClient()
		// {
		// public boolean shouldOverrideUrlLoading(WebView view, String url)
		// {
		// view.loadUrl(url);
		// return true;
		// }
		// });
		wv.loadUrl("file:///android_asset/link.html");

	}

}
