package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.SpecialOffersAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.HotelModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.exception.EcommerceException;
import com.nettactic.commerces.util.ActivityUtil;

/**
 * 优惠精选
 * 
 * 此Activity CateringEntertainmentActivity公用一个Layout.即：
 * R.Layout.activity_cateringentertainment
 * 
 */
public class SpecialOffersActivity extends BaseActivity implements
		ReqResultImpl
{
	private ListView listView;
	
	private List<HotelModel> list;
	
	private SpecialOffersAdapter specialOffersAdapter;
	
	public static final String FORMSINGLE = "fromSingle";
	private boolean fromSingleHotel = false;
	
	private int[] color;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dinner);
		setTitle("餐厅娱乐");
		setleftIcon(R.drawable.t_backarr);
		
		Intent intent = getIntent();
		if (intent.hasExtra(FORMSINGLE))
		{
			fromSingleHotel = true;
			int[] color = intent
					.getIntArrayExtra(SingleHotelHomeActivity.TITLECOLOR);
			setTitleColor(color[0]);
		}
		initView();
		initData();
		
	}
	
	private void initView()
	{
		listView = (ListView) findViewById(R.id.ce_listview);
		
	}
	
	private void initData()
	{
		list = new ArrayList<HotelModel>();
		specialOffersAdapter = new SpecialOffersAdapter(this, list);
		listView.setAdapter(specialOffersAdapter);
		
		if (fromSingleHotel)
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("HotelCode",
					SingleHotelHomeActivity.hotelCode));
			new LoadingAsync(this,
					RequestMethod.METHOD_HOTELDINNERINFOBYHOTELCODE, params)
					.execute();
		} else
		{
			
			new LoadingAsync(this, RequestMethod.METHOD_SPECIALOFFERS)
					.execute();
		}
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				String title = ActivityUtil.isEmpty(list.get(position)
						.getHotelTitle()) ? list.get(position).getHotelName()
						: list.get(position).getHotelTitle();
				
				String url = ActivityUtil.isEmpty(list.get(position)
						.getContentsUrl()) ? list.get(position).getDetailUrl()
						: list.get(position).getContentsUrl();
				
				Intent intent = new Intent(SpecialOffersActivity.this,
						SpecialOffersDetailActivity.class);
				intent.putExtra(SpecialOffersDetailActivity.TITLE, title);
				intent.putExtra(SpecialOffersDetailActivity.CONTENTURL, url);
				startActivity(intent);
			}
		});
		
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		try
		{
			JSONArray jsonArray = null;
			switch (methodName)
			{
			case METHOD_SPECIALOFFERS:
			{
				jsonArray = jsonResult.optJSONArray(Constant.JSON_DATA);
				break;
			}
			case METHOD_HOTELDINNERINFOBYHOTELCODE:
			{
				jsonArray = jsonResult.optJSONObject(Constant.JSON_DATA)
						.optJSONArray("RestaurantItems");
				break;
			}
			
			default:
				break;
			}
			
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.optJSONObject(i);
				
				HotelModel hotelModel = new HotelModel(jsonObject, null);
				list.add(hotelModel);
			}
			specialOffersAdapter.notifyDataSetChanged();
			
		} catch (JSONException e)
		{
			alert("网络异常,请重试");
		} catch (EcommerceException e)
		{
			alert("网络异常,请重试");
		}
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("网络异常,请重试");
		
	}
}
