package com.nettactic.commerces.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.SlideMenuAdapter;
import com.nettactic.commerces.impl.SelectHasChangeImpl;

public class SlideMenu implements SelectHasChangeImpl
{
	private Activity activity;
	
	private ViewPager titleViewPager = null;
	
	private ViewPager contentViewPager = null;
	
	private ScrollMenuLayout scrollMenuLayout;
	
	private ArrayList<View> viewList = null;
	
	private ArrayList<View> menuViewsList = null;
	
	private LocalActivityManager mlam;
	
	private String[][] menus;
	
	private List<Class<? extends Activity>> list;
	
	private String[] intentValue;
	
	public static final String INTENTVALUE = "intentValue";
	
	public SlideMenu(Activity activity, LocalActivityManager mlam,
			String[][] menus, List<Class<? extends Activity>> list)
	{
		super();
		this.activity = activity;
		this.mlam = mlam;
		this.menus = menus;
		this.list = list;
	}
	
	public SlideMenu(Activity activity, LocalActivityManager mlam,
			String[][] menus, List<Class<? extends Activity>> list,
			String[] intentValue)
	{
		super();
		this.activity = activity;
		this.mlam = mlam;
		this.menus = menus;
		this.list = list;
		this.intentValue = intentValue;
	}
	
	/**
	 * 加载ScrollMenu菜单
	 * 
	 * @param menus
	 * @param list
	 */
	public void initScrollMenu() {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		
		menuViewsList = new ArrayList<View>();
		scrollMenuLayout = new ScrollMenuLayout(activity, this);
		for (int i = 0; i < menus.length; i++)
		{
			menuViewsList.add(scrollMenuLayout.getSlideMenuLinearLayout(
					menus[i], screenWidth));
		}
		
		titleViewPager = (ViewPager) activity.findViewById(R.id.slideMenu);
		contentViewPager = (ViewPager) activity
				.findViewById(R.id.linearLayoutContent);
		
		viewList = new ArrayList<View>();
		
		if (null != intentValue && intentValue.length > 0)
		{
			loadActivity2ViewList(list, intentValue);
			
		} else
		{
			
			loadActivity2ViewList(list);
		}
		
		titleViewPager.setAdapter(new SlideMenuAdapter(menuViewsList));
		contentViewPager.setAdapter(new SlideMenuAdapter(viewList));
		
		// 设置contentViewPager的滑动监听
		contentViewPager.setOnPageChangeListener(OnPageChangeListener);
		
	}
	
	private void loadActivity2ViewList(List<Class<? extends Activity>> list) {
		for (int i = 0; i < list.size(); i++)
		{
			Intent intent = new Intent(activity, list.get(i));
			View v = mlam.startActivity("activity" + i, intent).getDecorView();
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			v.setLayoutParams(params);
			viewList.add(v);
			
		}
	}
	
	private void loadActivity2ViewList(List<Class<? extends Activity>> list,
			String[] intentValue) {
		for (int i = 0; i < list.size(); i++)
		{
			
			Intent intent = new Intent(activity, list.get(i));
			intent.putExtra(INTENTVALUE, intentValue[i]);
			View v = mlam.startActivity("activity" + i, intent).getDecorView();
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			v.setLayoutParams(params);
			viewList.add(v);
			
		}
	}
	
	private OnPageChangeListener OnPageChangeListener = new OnPageChangeListener()
	{
		
		@Override
		public void onPageSelected(int arg0) {
			scrollMenuLayout.setSelectedItemBackground(arg0);
			
			titleViewPager.setCurrentItem((arg0 / 3));
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	
	@Override
	public void changeSelected(int tag) {
		contentViewPager.setCurrentItem(tag);
	}
	
	public void changeTitleAndContentSelectedTo1() {
		titleViewPager.setCurrentItem(0);
		contentViewPager.setCurrentItem(0);
	}
}
