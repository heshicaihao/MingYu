package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.LastNewsAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.NewsModel;
import com.nettactic.commerces.service.ReqResultImpl;

/**
 * 最新资讯
 * 
 * @author yaguang
 *
 */
public class LastNewsActivity extends BaseActivity implements ReqResultImpl
{
	
	private ListView listView;
	private List<NewsModel> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		setTitle("最新资讯");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initData();
		
	}
	
	private void initView()
	{
		listView = (ListView) findViewById(R.id.news_listview);
		listView.setOnItemClickListener(onItemClickListener);
	}
	
	private void initData()
	{
		new LoadingAsync(LastNewsActivity.this,
				RequestMethod.METHOD_GETNEWSINFOR).execute();
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener()
	{
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3)
		{
			Intent intent = new Intent(LastNewsActivity.this,
					LastNewsDetailActivity.class);
			intent.putExtra(LastNewsDetailActivity.CONTENTURL,
					list.get(position).getContentsUrl());
			startActivity(intent);
		}
	};
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		try
		{
			JSONArray result = jsonResult.optJSONArray(Constant.JSON_DATA);
			NewsModel newsModel;
			list = new ArrayList<NewsModel>();
			
			for (int i = 0; i < result.length(); i++)
			{
				JSONObject JO = result.optJSONObject(i);
				newsModel = new NewsModel(JO);
				list.add(newsModel);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		listView.setAdapter(new LastNewsAdapter(LastNewsActivity.this, list));
		
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
	}
}
