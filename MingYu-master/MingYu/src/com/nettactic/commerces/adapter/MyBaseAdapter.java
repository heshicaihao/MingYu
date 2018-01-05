package com.nettactic.commerces.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nettactic.commerces.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class MyBaseAdapter extends BaseAdapter
{
	private Context ctx;
	
	public MyBaseAdapter(Context ctx)
	{
		super();
		this.ctx = ctx;
	}
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Object getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void loadWebImg(ImageView img, String imgUrl)
	{
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
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
}
