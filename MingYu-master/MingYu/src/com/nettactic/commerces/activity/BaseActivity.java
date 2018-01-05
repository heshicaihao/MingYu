package com.nettactic.commerces.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nettactic.commerces.R;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.util.LogUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 基本Activity类，处理一些公共的事件
 * 
 */
public class BaseActivity extends Activity implements OnClickListener
{
	private static final String TAG = "BaseActivity";
	
	RelativeLayout rl_title;
	
	/** 公共标题 */
	TextView tv_Title;
	
	ImageView iv_left;
	
	ImageView iv_right;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// /*设置全局的异常捕获类，捕获APP所有异常*/
		// CrashHandler crashHandler = CrashHandler.getInstance();
		// crashHandler.init(BaseActivity.this);
	}
	
	/**
	 * 设置此页面的标题
	 * 
	 * @param title
	 */
	void setTitle(String title)
	{
		if (!(this instanceof OnClickListener))
		{
			return;
		}
		initView();
		tv_Title.setText(title);
		
	}
	
	void setTitleImage(int drawableID)
	{
		if (!(this instanceof OnClickListener))
		{
			return;
		}
		initView();
		tv_Title.setBackgroundResource(drawableID);
	}
	
	void setTitleAlpha()
	{
		tv_Title.getBackground().setAlpha(1);
	}
	
	private void initView()
	{
		rl_title = (RelativeLayout) findViewById(R.id.title);
		tv_Title = (TextView) findViewById(R.id.homeTitle_);
		iv_left = (ImageView) findViewById(R.id.homeTitle_Left);
		iv_right = (ImageView) findViewById(R.id.homeTitle_Right);
		
		iv_left.setOnClickListener(this);
		iv_right.setOnClickListener(this);
	}
	
	void setTitleBackGround(int drawableID)
	{
		rl_title.setBackgroundResource(drawableID);
	}
	
	public void setTitleColor(int color)
	{
		try
		{
			rl_title.setBackgroundColor(getResources().getColor(color));
		} catch (NotFoundException e)
		{
			Log.e(TAG, e + "");
		}
	}
	
	void setleftIcon(int drawableID)
	{
		iv_left.setPadding(20, 10, 10, 10);
		iv_left.setImageResource(drawableID);
		
	}
	
	void setRightIcon(int drawableID)
	{
		iv_right.setImageResource(drawableID);
		
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.homeTitle_Left:
		{
			if (MyConfig.ifDebug)
			{
				LogUtil.d(TAG, "back");
			}
			finish();
			break;
		}
		case R.id.homeTitle_Right:
		{
			if (MyConfig.ifDebug)
			{
				LogUtil.d(TAG, "home");
			}
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			break;
		}
		}
	}
	
	/** 弹出简单信息提示 */
	public void alert(String msg)
	{
		Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
		
	}
	
	public void loadWebImg(ImageView img, String imgUrl)
	{
		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)// 设置图片以如何的编码方式显示
				.showImageOnLoading(R.drawable.default_bg) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.drawable.default_bg)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.default_bg) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true).build();
		ImageLoader.getInstance().displayImage(imgUrl, img, options);
		// bitmapUtils.display(img, imgUrl, new BitmapLoadCallBack<View>()
		// {
		//
		// @Override
		// public void onLoadCompleted(View container, String uri,
		// Bitmap bitmap, BitmapDisplayConfig config,
		// BitmapLoadFrom from)
		// {
		// ImageView image = (ImageView) container;
		// image.setImageBitmap(bitmap);
		// }
		//
		// @Override
		// public void onLoadFailed(View container, String uri,
		// Drawable drawable)
		// {
		// container.setBackgroundResource(R.drawable.default_bg);
		// }
		// });
	}
	
	public void callMingYu()
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
				+ "400-009-000"));
		startActivity(intent);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		return super.onKeyDown(keyCode, event);
	}
}
