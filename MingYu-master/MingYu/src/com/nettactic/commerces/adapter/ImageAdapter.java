package com.nettactic.commerces.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	private Context mContext;

	private Integer[] mImgs;

	public ImageAdapter(Context context, Integer[] imgs)
	{
		mContext = context;
		mImgs = imgs;
	}

	public int getCount()
	{
		return mImgs.length;
	}

	public Object getItem(int position)
	{
		return position;
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView image = new ImageView(mContext);
		image.setImageResource(mImgs[position]);
		image.setAdjustViewBounds(true);
		image.setLayoutParams(new Gallery.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		return image;
	}

}
