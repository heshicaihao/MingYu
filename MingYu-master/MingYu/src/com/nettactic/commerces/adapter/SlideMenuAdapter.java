package com.nettactic.commerces.adapter;

import java.util.ArrayList;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class SlideMenuAdapter extends PagerAdapter
{
	private static final String TAG = "SlideMenuAdapter";

	ArrayList<View> menuViews;

	public SlideMenuAdapter(ArrayList<View> menuViews)
	{
		super();
		this.menuViews = menuViews;
	}

	@Override
	public int getCount()
	{
		return menuViews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object)
	{
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2)
	{
		((ViewPager) arg0).removeView(menuViews.get(arg1));
	}

	@Override
	public Object instantiateItem(View arg0, int arg1)
	{
		((ViewPager) arg0).addView(menuViews.get(arg1));

		return menuViews.get(arg1);
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object)
	{
		// TODO Auto-generated method stub
		super.setPrimaryItem(container, position, object);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1)
	{

	}

	@Override
	public Parcelable saveState()
	{
		return null;
	}

	@Override
	public void startUpdate(View arg0)
	{

	}

	@Override
	public void finishUpdate(View arg0)
	{

	}
}
