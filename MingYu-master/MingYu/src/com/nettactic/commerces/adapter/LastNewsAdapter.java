package com.nettactic.commerces.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.model.NewsModel;

/**
 * 最新资讯adapter
 * 
 */
public class LastNewsAdapter extends MyBaseAdapter
{
	private Context context;
	
	private List<NewsModel> list;
	
	private LayoutInflater inflater;
	
	public LastNewsAdapter(Context context, List<NewsModel> list)
	{
		super(context);
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount()
	{
		return list.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		NewsModel newsModel = list.get(position);
		convertView = inflater.inflate(R.layout.item_news, null);
		
		TextView tv_Title = (TextView) convertView
				.findViewById(R.id.news_content);
		ImageView wv = (ImageView) convertView.findViewById(R.id.news_img);
		
		tv_Title.setText(newsModel.getTitle());
		loadWebImg(wv, newsModel.getNewsImg());
		return convertView;
	}
}
