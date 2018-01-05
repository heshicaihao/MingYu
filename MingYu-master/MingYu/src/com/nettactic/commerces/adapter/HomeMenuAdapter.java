package com.nettactic.commerces.adapter;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.impl.SetActivityViewImpl;
import com.nettactic.commerces.model.HomeMenuItem;

/**
 * 首页滑动菜单适配器
 * 
 */
public class HomeMenuAdapter extends BaseAdapter
{

	private List<Object> mItems;

	private Activity mActivity;

	private SetActivityViewImpl SAVI;

	public HomeMenuAdapter(Activity activity, List<Object> items)
	{
		mItems = items;
		mActivity = activity;
		SAVI = (SetActivityViewImpl) activity;

	}

	@Override
	public int getCount()
	{
		return mItems.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public int getItemViewType(int position)
	{
		return getItem(position) instanceof HomeMenuItem ? 0 : 1;
	}

	@Override
	public int getViewTypeCount()
	{
		return 2;
	}

	@Override
	public boolean isEnabled(int position)
	{
		return getItem(position) instanceof HomeMenuItem;
	}

	@Override
	public boolean areAllItemsEnabled()
	{
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		Object item = getItem(position);

		if (v == null)
		{
			v = mActivity.getLayoutInflater().inflate(
					R.layout.activity_menu_row_item, parent, false);
		}

		TextView tv = (TextView) v;
		tv.setText(((HomeMenuItem) item).mTitle);
		tv.setCompoundDrawablesWithIntrinsicBounds(
				((HomeMenuItem) item).mIconRes, null, null, null);
		v.setTag(R.id.mdActiveViewPosition, position);

		SAVI.setActiveView(v, position);

		return v;
	}
}
