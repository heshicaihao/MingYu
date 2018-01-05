package com.nettactic.amap;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.view.RouteOverLay;
import com.nettactic.amap.utils.ToastUtil;
import com.nettactic.amap.utils.Utils;
import com.nettactic.commerces.MyApplication;
import com.nettactic.commerces.R;
import com.nettactic.commerces.activity.BaseActivity;

/**
 * 路径规划页面
 * */
public class SimpleNaviRouteActivity extends BaseActivity implements
		OnClickListener, AMapNaviListener, LocationSource, AMapLocationListener
{
	public static final String PLANTYPE = "planType";
	public static final String LAT = "lat";
	public static final String CITYNAME = "cityname";
	
	/** 0： "公交路线",1： "自驾路线",2:"步行路线" */
	private int planType = 0;
	private double[] lat = new double[2];
	private String cityname = "";
	
	// 地图和导航资源
	private MapView mMapView;
	private AMap mAMap;
	private AMapNavi mAMapNavi;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	
	// 起点终点坐标
	private NaviLatLng mNaviBegin;
	private NaviLatLng mNaviEnd;
	// 起点终点列表
	private ArrayList<NaviLatLng> mBeginPoints = new ArrayList<NaviLatLng>();
	private ArrayList<NaviLatLng> mEndPoints = new ArrayList<NaviLatLng>();
	// 规划线路
	private RouteOverLay mRouteOverLay;
	// 是否驾车和是否计算成功的标志
	private boolean mIsDriveMode = true;
	private boolean isDrive = true;
	private boolean mIsCalculateRouteSuccess = false;
	private LocationManagerProxy mLocationManagerProxy;
	
	private RelativeLayout rl_title;
	private TextView tv_Title;
	private ImageView iv_left;
	private ImageView iv_Right;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marker_activity);
		
		Intent intent = getIntent();
		if (intent.hasExtra(PLANTYPE) && intent.hasExtra(LAT)
				&& intent.hasExtra(CITYNAME))
		{
			planType = intent.getIntExtra(PLANTYPE, 0);
			lat = intent.getDoubleArrayExtra(LAT);
			cityname = intent.getStringExtra(CITYNAME);
			
			mNaviEnd = new NaviLatLng(lat[0], lat[1]);
			
		}
		initView(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
	}
	
	// 初始化View
	private void initView(Bundle savedInstanceState)
	{
		
		rl_title = (RelativeLayout) findViewById(R.id.title);
		tv_Title = (TextView) findViewById(R.id.homeTitle_);
		iv_left = (ImageView) findViewById(R.id.homeTitle_Left);
		iv_Right = (ImageView) findViewById(R.id.homeTitle_Right);
		
		tv_Title.setText(cityname + "路线规划");
		iv_left.setPadding(20, 10, 10, 10);
		iv_left.setImageResource(R.drawable.t_backarr);
		iv_left.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				finish();
				MyApplication.getInstance().deleteActivity(
						SimpleNaviRouteActivity.this);
				
			}
		});
		
		mAMapNavi = AMapNavi.getInstance(this);
		mAMapNavi.setAMapNaviListener(this);
		// mStartPoints.clear();
		mEndPoints.clear();
		mEndPoints.add(mNaviEnd);
		
		mMapView = (MapView) findViewById(R.id.map);
		mMapView.onCreate(savedInstanceState);
		mAMap = mMapView.getMap();
		mRouteOverLay = new RouteOverLay(mAMap, null);
		setUpMap();
	}
	
	private Marker marker;// 定位雷达小图标
	
	private void setUpMap()
	{
		ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
		giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.point1));
		giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.point2));
		giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.point3));
		giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.point4));
		giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.point5));
		giflist.add(BitmapDescriptorFactory.fromResource(R.drawable.point6));
		marker = mAMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
				.icons(giflist).period(50));
		// 自定义系统定位小蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_marker));// 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
		myLocationStyle.strokeWidth(0.1f);// 设置圆形的边框粗细
		mAMap.setMyLocationStyle(myLocationStyle);
		mAMap.setMyLocationRotateAngle(180);
		mAMap.setLocationSource(this);// 设置定位监听
		mAMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
		mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
		mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
		
		// 设置定位的类型为定位模式：定位（AMap.LOCATION_TYPE_LOCATE）、跟随（AMap.LOCATION_TYPE_MAP_FOLLOW）
		// 地图根据面向方向旋转（AMap.LOCATION_TYPE_MAP_ROTATE）三种模式
		
		mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		
		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用destroy()方法
		// 其中如果间隔时间为-1，则定位只定一次
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);
		
		mLocationManagerProxy.setGpsEnable(false);
	}
	
	// 计算驾车路线
	private void calculateDriveRoute()
	{
		mAMapNavi.calculateDriveRoute(mBeginPoints, mEndPoints, null,
				AMapNavi.DrivingDefault);
		
	}
	
	// 计算步行路线
	private void calculateFootRoute()
	{
		mAMapNavi.calculateWalkRoute(mNaviBegin, mNaviEnd);
	}
	
	// 计算公交路线
	private void calculateBusRoute()
	{
		mAMapNavi.calculateWalkRoute(mNaviBegin, mNaviEnd);
	}
	
	private void showToast(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	
	private void startGPSNavi()
	{
		if ((isDrive && mIsDriveMode && mIsCalculateRouteSuccess)
				|| (!isDrive && !mIsDriveMode && mIsCalculateRouteSuccess))
		{
			Intent gpsIntent = new Intent(SimpleNaviRouteActivity.this,
					SimpleNaviActivity.class);
			Bundle bundle = new Bundle();
			bundle.putBoolean(Utils.ISEMULATOR, false);
			bundle.putInt(Utils.ACTIVITYINDEX, Utils.SIMPLEROUTENAVI);
			gpsIntent.putExtras(bundle);
			gpsIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(gpsIntent);
		} else
		{
			showToast("请先进行相对应的路径规划，再进行导航");
		}
	}
	
	@Override
	public void onClick(View v)
	{/*
	 * switch (v.getId()) { case R.id.car_navi_route: mIsCalculateRouteSuccess =
	 * false; mIsDriveMode = true; calculateDriveRoute(); break; case
	 * R.id.car_navi_emulator: startEmulatorNavi(true); break; case
	 * R.id.car_navi_navi: startGPSNavi(true); break; case R.id.foot_navi_route:
	 * mIsCalculateRouteSuccess = false; mIsDriveMode = false;
	 * calculateFootRoute(); break; case R.id.foot_navi_emulator:
	 * startEmulatorNavi(false); break; case R.id.foot_navi_navi:
	 * startGPSNavi(false); break;
	 * 
	 * }
	 */
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			// Intent intent = new Intent(SimpleNaviRouteActivity.this,
			// MainStartActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			//
			// startActivity(intent);
			finish();
			MyApplication.getInstance().deleteActivity(this);
			
		}
		return super.onKeyDown(keyCode, event);
	}
	
	// --------------------导航监听回调事件-----------------------------
	@Override
	public void onArriveDestination()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onArrivedWayPoint(int arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onCalculateRouteFailure(int arg0)
	{
		showToast("暂不支持此路段的路线规划，请选择其他交通方式");
		mIsCalculateRouteSuccess = false;
	}
	
	@Override
	public void onCalculateRouteSuccess()
	{
		AMapNaviPath naviPath = mAMapNavi.getNaviPath();
		if (naviPath == null)
		{
			return;
		}
		// 获取路径规划线路，显示到地图上
		mRouteOverLay.setRouteInfo(naviPath);
		mRouteOverLay.addToMap();
		mIsCalculateRouteSuccess = true;
		
		switch (planType)
		{
		case 0:
		{
			
			break;
		}
		case 1:
		case 2:
		{
			setRightTitle();
			break;
		}
		
		default:
			break;
		}
	}
	
	@Override
	public void onEndEmulatorNavi()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onGetNavigationText(int arg0, String arg1)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onGpsOpenStatus(boolean arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onInitNaviFailure()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onInitNaviSuccess()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onLocationChange(AMapNaviLocation arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onNaviInfoUpdated(AMapNaviInfo arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onReCalculateRouteForTrafficJam()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onReCalculateRouteForYaw()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStartNavi(int arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onTrafficStatusUpdate()
	{
		// TODO Auto-generated method stub
		
	}
	
	// ------------------生命周期重写函数---------------------------
	
	@Override
	public void onResume()
	{
		super.onResume();
		mMapView.onResume();
		
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		mMapView.onPause();
		deactivate();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mMapView.onDestroy();
		// 删除监听
		AMapNavi.getInstance(this).removeAMapNaviListener(this);
		
	}
	
	@Override
	public void onProviderDisabled(String arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onProviderEnabled(String arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2)
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 此方法已经废弃
	 */
	@Override
	public void onLocationChanged(Location location)
	{
	}
	
	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation)
	{
		if (mListener != null && amapLocation != null)
		{
			if (amapLocation.getAMapException().getErrorCode() == 0)
			{
				float bearing = mAMap.getCameraPosition().bearing;
				mAMap.setMyLocationRotateAngle(bearing);// 设置小蓝点旋转角度
				
				Double geoLat = amapLocation.getLatitude();
				Double geoLng = amapLocation.getLongitude();
				mNaviBegin = new NaviLatLng(geoLat, geoLng);
				mBeginPoints.add(mNaviBegin);
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
				switch (planType)
				{
				case 0:
				{
					calculateBusRoute();
					isDrive = false;
					break;
				}
				case 1:
				{
					calculateDriveRoute();
					mIsDriveMode = true;
					isDrive = true;
					break;
				}
				case 2:
				{
					mIsDriveMode = false;
					isDrive = false;
					calculateFootRoute();
					
					break;
				}
				
				default:
					ToastUtil.show(this, "获取数据异常，请重试");
					break;
				}
				
			} else
			{
				ToastUtil.show(this, "定位失败，请重试");
			}
		}
	}
	
	private void setRightTitle()
	{
		iv_Right.setPadding(10, 10, 20, 10);
		iv_Right.setImageResource(R.drawable.tips_route_new);
		iv_Right.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				startGPSNavi();
			}
		});
	}
	
	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener)
	{
		mListener = listener;
		if (mAMapLocationManager == null)
		{
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用destroy()方法
			// 其中如果间隔时间为-1，则定位只定一次
			mAMapLocationManager.requestLocationData(
					LocationProviderProxy.AMapNetwork, 60 * 1000, 10, this);
		}
	}
	
	/**
	 * 停止定位
	 */
	@Override
	public void deactivate()
	{
		mListener = null;
		if (mAMapLocationManager != null)
		{
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
	}
}
