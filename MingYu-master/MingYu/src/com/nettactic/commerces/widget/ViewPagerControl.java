package com.nettactic.commerces.widget;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.nettactic.commerces.R;
import com.nettactic.commerces.activity.HomeActivity;
import com.nettactic.commerces.service.LoadComplete;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 多处用到的类似首页滚动图片的效果
 * 
 * @author yaguang
 *
 */
public class ViewPagerControl implements OnPageChangeListener
{
	// 组件占屏幕的scale之一
	private final int SCALE = 3;
	
	private Activity activity;
	
	private ViewPager viewPager;
	
	private ImageView[] mImageViews;
	private ImageView[] tips;
	
	private LoadComplete loadComplete;
	
	public ViewPagerControl(Activity activity)
	{
		super();
		this.activity = activity;
	}
	
	// public ViewPagerControl(HomeActivity activity)
	// {
	// super();
	// this.activity = activity;
	// this.loadComplete = activity;
	// }
	
	public void initViewPager(String[] urlImages)
	{
		if (null != mImageViews && mImageViews.length > 0)
		{
			mImageViews = null;
		}
		if (null != tips && tips.length > 0)
		{
			tips = null;
		}
		
		FrameLayout frameLayout = (android.widget.FrameLayout) activity
				.findViewById(R.id.framelayout);
		ViewGroup group = (ViewGroup) frameLayout.findViewById(R.id.viewGroup);
		viewPager = (ViewPager) frameLayout.findViewById(R.id.viewPager);
		if (group.getChildCount() > 0)
		{
			group.removeAllViews();
		}
		WindowManager wm = activity.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		
		if (activity.getClass().equals(HomeActivity.class))
		{
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, height / 2 - 50);
			frameLayout.setLayoutParams(layoutParams);
		} else
		{
			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, height / SCALE);
			frameLayout.setLayoutParams(layoutParams);
			
		}
		
		int[] images = null;
		if (null != urlImages && urlImages.length > 0)
		{
			tips = new ImageView[urlImages.length];
			mImageViews = new ImageView[urlImages.length];
		} else
		{
			
			images = new int[]
			{ R.drawable.t_bg };
			tips = new ImageView[images.length];
			mImageViews = new ImageView[images.length];
		}
		
		// 将点点加入到ViewGroup中
		for (int i = 0; i < tips.length; i++)
		{
			ImageView imageView = new ImageView(activity);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			tips[i] = imageView;
			if (i == 0)
			{
				tips[i].setBackgroundResource(R.drawable.t_whitepoint);
			} else
			{
				tips[i].setBackgroundResource(R.drawable.t_redpoint);
			}
			
			LinearLayout.LayoutParams groupLayoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			groupLayoutParams.leftMargin = 5;
			groupLayoutParams.rightMargin = 5;
			group.addView(imageView, groupLayoutParams);
			
			LayoutInflater inflater = LayoutInflater.from(activity);
			ImageView layout = (ImageView) inflater.inflate(R.layout.myimage,
					null);
			ViewGroup.LayoutParams ViewGroupParams = new ViewGroup.LayoutParams(
					new ViewGroup.LayoutParams(width, height / SCALE));
			layout.setLayoutParams(ViewGroupParams);
			layout.setScaleType(ScaleType.FIT_XY);
			layout.setContentDescription("加载中...");
			mImageViews[i] = layout;
			if (null != urlImages && urlImages.length > 0)
			{
				loadWebImg(layout, urlImages[i]);
			} else
			{
				layout.setBackgroundResource(images[i]);
			}
		}
		
		// 设置Adapter
		viewPager.setAdapter(new MyAdapter());
		// 设置监听，主要是设置点点的背景
		viewPager.setOnPageChangeListener(this);
		// 设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
		viewPager.setCurrentItem((mImageViews.length) * 100);
		
		if (null != loadComplete)
		{
			loadComplete.loadComplete();
		}
		
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		
	}
	
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		
	}
	
	@Override
	public void onPageSelected(int arg0)
	{
		setImageBackground(arg0 % mImageViews.length);
		
	}
	
	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems)
	{
		for (int i = 0; i < tips.length; i++)
		{
			if (i == selectItems)
			{
				tips[i].setBackgroundResource(R.drawable.t_whitepoint);
			} else
			{
				tips[i].setBackgroundResource(R.drawable.t_redpoint);
			}
		}
	}
	
	public class MyAdapter extends PagerAdapter
	{
		
		@Override
		public int getCount()
		{
			return mImageViews.length;
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}
		
		@Override
		public void destroyItem(View container, int position, Object object)
		{
			// ((ViewPager) container).removeView(mImageViews[position
			// % mImageViews.length]);
			
		}
		
		/**
		 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
		 */
		@Override
		public Object instantiateItem(View container, int position)
		{
			try
			{
				((ViewPager) container).addView(mImageViews[position], 0);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return mImageViews[position];
		}
		
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
		// BitmapDisplayConfig bitmapDisplayConfig = new BitmapDisplayConfig();
		// bitmapDisplayConfig.setShowOriginal(true);
		// bitmapDisplayConfig.setAutoRotation(true);
		// bitmapUtils.display(img, imgUrl, bitmapDisplayConfig,
		// new BitmapLoadCallBack<View>()
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
		// container.setBackgroundResource(R.drawable.t_bg);
		// }
		// });
	}
}
