package com.nettactic.commerces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.nettactic.commerces.R;

/**
 * 优惠精选详情
 * 
 * 
 */
public class SpecialOffersDetailActivity extends BaseActivity
{
	private WebView webView;
	
	public static final String TITLE = "title";
	
	public static final String CONTENTURL = "ContentUrl";
	
	private String title = "";
	
	private String contentUrl = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		setTitle("");
		setleftIcon(R.drawable.t_backarr);
		
		Intent intent = getIntent();
		if (null != intent)
		{
			setTitleColor(intent.getIntExtra(
					SingleHotelHomeActivity.TITLECOLOR, 0));
			initView(intent);
			initData(intent);
			
		} else
		{
			
		}
	}
	
	private void initView(Intent intent)
	{
		if (intent.hasExtra(Single_MettingWedding_Loc_Activity.METTING)
				|| intent.hasExtra(SingleHotelHomeActivity.COMMENT))
		{
			setTitle(intent.getStringExtra(TITLE));
			
		} else
		{
			setTitle("优惠精选详情");
			setRightIcon(R.drawable.share);
		}
		webView = (WebView) findViewById(R.id.webView);
		
	}
	
	private void initData(Intent intent)
	{
		
		if (intent.hasExtra(CONTENTURL))
		{
			title = intent.getStringExtra(TITLE);
			contentUrl = intent.getStringExtra(CONTENTURL);
		}
		
		webView.loadUrl(contentUrl);
		
	}
	
	@Override
	public void onClick(View v)
	{
		super.onClick(v);
		if (v.getId() == R.id.homeTitle_Right)
		{
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, title + contentUrl);
			startActivity(Intent.createChooser(intent, "分享优惠精选"));
		}
		
	}
}
