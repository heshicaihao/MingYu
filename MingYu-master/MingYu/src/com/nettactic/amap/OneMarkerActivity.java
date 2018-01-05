package com.nettactic.amap;

import android.R.color;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.nettactic.commerces.R;
import com.nettactic.commerces.activity.BaseActivity;
import com.nettactic.commerces.model.OrderModel;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.widget.DialogTool;

/**
 * AMapV1地图中简单介绍一些Marker的用法.
 */
public class OneMarkerActivity extends BaseActivity implements
		OnMarkerClickListener, OnInfoWindowClickListener, OnMapLoadedListener,
		InfoWindowAdapter
{
	private AMap aMap;
	private MapView mapView;
	
	private static final String TITLE = "title";
	private static final String HOTELNAME = "HOTELNAME";
	private static final String LONGITUDELIST = "longitudeList";
	private static final String LATITUDELIST = "latitudeList";
	
	private RelativeLayout rl_title;
	private TextView tv_Title;
	private ImageView iv_left;
	private String titleName;
	private OrderModel orderModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marker_activity);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState); // 此方法必须重写
		CameraUpdateFactory.zoomBy(18);
		
		rl_title = (RelativeLayout) findViewById(R.id.title);
		tv_Title = (TextView) findViewById(R.id.homeTitle_);
		iv_left = (ImageView) findViewById(R.id.homeTitle_Left);
		
		Intent intent = getIntent();
		titleName = intent.getStringExtra(TITLE);
		orderModel = OrderModel.getOrderModel();
		if (ActivityUtil.isEmpty(titleName))
		{
			titleName = orderModel.getHotelName();
		}
		
		if (intent.getAction() == "need title bar")
		{
			tv_Title.setText(titleName);
			iv_left.setPadding(20, 10, 10, 10);
			iv_left.setImageResource(R.drawable.t_backarr);
			iv_left.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0)
				{
					OneMarkerActivity.this.finish();
					
				}
			});
		} else
		{
			rl_title.setVisibility(View.GONE);
		}
		
		init();
	}
	
	/**
	 * 初始化AMap对象
	 */
	private void init()
	{
		if (aMap == null)
		{
			aMap = mapView.getMap();
			setUpMap();
		}
	}
	
	private void setUpMap()
	{
		aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
		addMarkersToMap();// 往地图上添加marker
	}
	
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
		mapView.onResume();
	}
	
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
		mapView.onPause();
	}
	
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}
	
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mapView.onDestroy();
	}
	
	/**
	 * 在地图上添加marker
	 */
	private LatLng LatLng;
	
	private void addMarkersToMap()
	{
		OrderModel om = OrderModel.getOrderModel();
		double[] d = om.getLat();
		LatLng = new LatLng(d[0], d[1]);
		
		BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_gcoding);
		aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).position(LatLng)
				.icon(bitmapDescriptor).title(titleName).draggable(true));
		
	}
	
	/**
	 * 对marker标注点点击响应事件
	 */
	@Override
	public boolean onMarkerClick(final Marker marker)
	{
		// ToastUtil.show(this, "你点击的是" + marker.getTitle());
		return false;
	}
	
	/**
	 * 监听点击infowindow窗口事件回调
	 */
	@Override
	public void onInfoWindowClick(Marker marker)
	{
		// ToastUtil.show(this, "你点击了infoWindow窗口" + marker.getTitle());
	}
	
	/**
	 * 监听amap地图加载成功事件回调
	 */
	@Override
	public void onMapLoaded()
	{
		// 设置所有maker显示在当前可视区域地图中
		// aMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng));
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng, 10));
	}
	
	/**
	 * 监听自定义infowindow窗口的infocontents事件回调
	 */
	@Override
	public View getInfoContents(Marker marker)
	{
		return null;
	}
	
	private void getInfoDialog(Marker marker)
	{
		
		final LatLng ll = marker.getPosition();
		final CharSequence[] items = new CharSequence[]
		{ "公交路线", "自驾路线", "步行路线" };
		DialogTool.createRadioDialog(OneMarkerActivity.this,
				R.drawable.t_mingyu, items, titleName + "路线规划",
				new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						Intent intent = new Intent(OneMarkerActivity.this,
								SimpleNaviRouteActivity.class);
						double[] d = new double[2];
						orderModel = OrderModel.getOrderModel();
						d[0] = ll.latitude;
						d[1] = ll.longitude;
						intent.putExtra(SimpleNaviRouteActivity.LAT, d);
						intent.putExtra(SimpleNaviRouteActivity.PLANTYPE, arg1);
						intent.putExtra(SimpleNaviRouteActivity.CITYNAME,
								orderModel.getCity());
						startActivity(intent);
						
					}
				}).show();
		
	}
	
	/**
	 * 监听自定义infowindow窗口的infowindow事件回调
	 */
	@Override
	public View getInfoWindow(final Marker marker)
	{
		String titleInfo = marker.getTitle();
		
		Button button = new Button(getApplicationContext());
		
		if (titleInfo != null)
		{
			button.setText(titleInfo);
			button.setTextColor(OneMarkerActivity.this.getResources().getColor(
					color.black));
			button.setBackgroundResource(R.drawable.popup);
			button.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.golden_arrow, 0);
			button.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View arg0)
				{
					getInfoDialog(marker);
					
				}
			});
		} else
		{
			button.setText("");
		}
		
		return button;
	}
	
}
