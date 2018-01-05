package com.nettactic.commerces;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap.CompressFormat;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * 全局常量类
 */
public class MyApplication extends Application
{
	// http://192.168.1.109:81/
	// http://www.minyounhotels.com/
	public boolean m_bKeyRight = true;
	
	public static MyApplication mInstance = null;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		mInstance = this;
		// FIXME 是否需要启动崩溃日志记录
		// CrashHandler crashHandler = CrashHandler.getInstance();
		// // 注册crashHandler
		// crashHandler.init(getApplicationContext());
		// 发送以前没发送的报告(可选)
		// crashHandler.sendPreviousReportsToServer();
		initImageLoader();
	}
	
	private void initImageLoader()
	{
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.memoryCacheExtraOptions(480, 800)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
				.writeDebugLogs() // Remove for release app
				.build();// 开始构建
		ImageLoader.getInstance().init(config);// 全局初始化此配置
	}
	
	// 单例模式中获取唯一的MyApplication实例
	public static MyApplication getInstance()
	{
		if (null == mInstance)
		{
			mInstance = new MyApplication();
		}
		return mInstance;
	}
	
	// 添加Activity到容器中
	public void addActivity(Activity activity)
	{
		activities.add(activity);
	}
	
	public void deleteActivity(Activity activity)
	{
		activities.remove(activity);
	}
	
	// finish
	public void exit()
	{
		for (Activity activity : activities)
		{
			activity.finish();
		}
		activities.clear();
		
	}
}