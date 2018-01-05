package com.nettactic.commerces.model;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class HomeMenuItem
{
	public String mTitle;

	public Drawable mIconRes;

	public Class<? extends Activity> c;

	public HomeMenuItem(String mTitle, Drawable mIconRes,
			Class<? extends Activity> c)
	{
		super();
		this.mTitle = mTitle;
		this.mIconRes = mIconRes;
		this.c = c;
	}
}
