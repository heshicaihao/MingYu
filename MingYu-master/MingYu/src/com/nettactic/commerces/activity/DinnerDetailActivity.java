package com.nettactic.commerces.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.model.BrandModel;
import com.nettactic.commerces.model.HotelModel;

/**
 * 餐饮娱乐详情
 */
@SuppressLint("ResourceAsColor")
public class DinnerDetailActivity extends BaseActivity
{
	private ImageView iv_BrandLogo;
	
	private TextView tv_BrandIntro;
	
	private LinearLayout ll_Restaurants;
	
	private TextView tv_SpecialOffc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dinnerdetail);
		setTitle("餐饮美食");
		setleftIcon(R.drawable.t_backarr);
		setRightIcon(R.drawable.home);
		
		initView();
		initData();
	}
	
	private void initView()
	{
		iv_BrandLogo = (ImageView) findViewById(R.id.ced_img);
		tv_BrandIntro = (TextView) findViewById(R.id.ced_brandintro_content);
		ll_Restaurants = (LinearLayout) findViewById(R.id.ced_restaurants_content);
		tv_SpecialOffc = (TextView) findViewById(R.id.ced_Special);
		
		tv_SpecialOffc.setOnClickListener(this);
	}
	
	private void initData()
	{
		Intent intent = getIntent();
		Bundle b = intent.getBundleExtra("bundle");
		BrandModel brandModel = (BrandModel) b.getSerializable("brandModel");
		loadWebImg(iv_BrandLogo, brandModel.getBrand_LogUrl());
		tv_BrandIntro.setText(brandModel.getBrand_Introl());
		
		List<HotelModel> list = brandModel.getHotelList();
		
		TextView tv_restaurants = null;
		
		for (int i = 0; i < list.size(); i++)
		{
			tv_restaurants = new TextView(this);
			tv_restaurants.setPadding(0, 10, 0, 0);
			final HotelModel hotelModel = list.get(i);
			final String url = hotelModel.getHotelUrl();
			if (null != url && "" != url && url.length() > 1)
			{
				
				tv_restaurants.setText(hotelModel.getHotelName());
				tv_restaurants
						.setCompoundDrawablesWithIntrinsicBounds(null, null,
								getResources().getDrawable(R.drawable.mark),
								null);
				tv_restaurants.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(View arg0)
					{
						
						Intent intent = new Intent(DinnerDetailActivity.this,
								RestaurantDetail.class);
						intent.putExtra(RestaurantDetail.RESTAURANTDETAIL, url);
						startActivity(intent);
					}
				});
			} else
			{
				
				tv_restaurants.setText(hotelModel.getHotelName());
				tv_restaurants.setTextColor(R.color.textDark);
			}
			ll_Restaurants.addView(tv_restaurants);
		}
	}
	
	@Override
	public void onClick(View v)
	{
		
		if (v.getId() == R.id.ced_Special)
		{
			Intent intent = new Intent(this, SpecialOffersActivity.class);
			startActivity(intent);
		}
		super.onClick(v);
	}
}
