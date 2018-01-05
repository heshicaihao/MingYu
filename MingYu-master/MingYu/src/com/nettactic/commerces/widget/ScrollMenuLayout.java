package com.nettactic.commerces.widget;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.impl.SelectHasChangeImpl;

@SuppressLint("NewApi")
public class ScrollMenuLayout
{
	private ArrayList<TextView> menuList = null;
	
	private Activity activity;
	
	private int count = 0;
	
	private SelectHasChangeImpl selectHasChangeImpl;
	
	public ScrollMenuLayout(Activity activity, SlideMenu slideMenu)
	{
		this.activity = activity;
		menuList = new ArrayList<TextView>();
		selectHasChangeImpl = (SelectHasChangeImpl) slideMenu;
	}
	
	public View getSlideMenuLinearLayout(String[] menuTextViews, int layoutWidth)
	{
		LinearLayout menuLinerLayout = new LinearLayout(activity);
		menuLinerLayout.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout.LayoutParams menuLinerLayoutParames = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		menuLinerLayoutParames.gravity = Gravity.CENTER_HORIZONTAL;
		
		for (int i = 0; i < menuTextViews.length; i++)
		{
			TextView tvMenu = new TextView(activity);
			tvMenu.setTag(count);
			tvMenu.setLayoutParams(new LayoutParams(layoutWidth / 3,
					LayoutParams.WRAP_CONTENT));
			tvMenu.setPadding(0, 10, 0, 10);
			tvMenu.setText(menuTextViews[i]);
			tvMenu.setTextColor(Color.GRAY);
			tvMenu.setTextSize(15);
			tvMenu.setGravity(Gravity.CENTER_HORIZONTAL);
			tvMenu.setOnClickListener(SlideMenuOnClickListener);
			tvMenu.setBackgroundDrawable(activity.getResources().getDrawable(
					R.drawable.buttom_line));
			
			count++;
			
			if (count == 1)
			{
				tvMenu.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0,
						R.drawable.red_up_arrow);
				tvMenu.setTextColor(Color.rgb(180, 30, 34));
			}
			
			menuLinerLayout.addView(tvMenu, menuLinerLayoutParames);
			menuList.add(tvMenu);
		}
		
		return menuLinerLayout;
		
	}
	
	OnClickListener SlideMenuOnClickListener = new OnClickListener()
	{
		
		@SuppressLint("NewApi")
		@Override
		public void onClick(View v)
		{
			int menuTag = Integer.valueOf(v.getTag().toString());
			setSelectedItemBackground(menuTag);
			slideMenuOnChange(menuTag);
		}
	};
	
	public void slideMenuOnChange(int menuTag)
	{
		// Resources resources = activity.getResources();
		//
		// LayoutInflater inflater = activity.getLayoutInflater();
		// ViewGroup llc = (ViewGroup)
		// activity.findViewById(R.id.linearLayoutContent);
		// llc.removeAllViews();
		selectHasChangeImpl.changeSelected(Integer.valueOf(menuTag));
	}
	
	public void setSelectedItemBackground(int menuTag)
	{
		for (int i = 0; i < menuList.size(); i++)
		{
			if (i == menuTag)
			{
				menuList.get(i).setCompoundDrawablesWithIntrinsicBounds(0, 0,
						0, R.drawable.red_up_arrow);
				menuList.get(i).setTextColor(Color.rgb(180, 30, 34));
				
			} else
			{
				menuList.get(i).setCompoundDrawablesWithIntrinsicBounds(0, 0,
						0, 0);
				menuList.get(i).setTextColor(Color.GRAY);
			}
			
		}
	}
}