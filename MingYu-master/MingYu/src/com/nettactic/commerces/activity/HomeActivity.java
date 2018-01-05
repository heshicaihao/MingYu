package com.nettactic.commerces.activity;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.nettactic.commerces.MyApplication;
import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.impl.SetActivityViewImpl;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.widget.ViewPagerControl;

/**
 * APP首页主要功能是加载滑动菜单，以及数据初始化工作
 * 
 * @author ruan
 */
public class HomeActivity extends BaseActivity implements SetActivityViewImpl,
		ViewFactory, ReqResultImpl {

	private static final String TAG = "HomeActivity";

	private static final String STATE_ACTIVE_POSITION = "net.simonvt.menudrawer.samples.ContentSample.activePosition";

	private static final String STATE_CONTENT_TEXT = "net.simonvt.menudrawer.samples.ContentSample.contentText";

	private int ACTIVE_POSITION = -1;

	private MenuDrawer mMenuDrawer;

	private String mContentText;

	private MyApplication app;

	private RelativeLayout rel_MingYu, rel_SpecialOffers, rel_OnlineOrder,
			rel_More;

	/** 侧边菜单栏可点击按钮 */
	private LinearLayout ll_Order, ll_SpecialOff, ll_Dinner, ll_News, ll_About,
			ll_Login, ll_MyOrder, ll_opinion;

	private TextView tv_my, tv_Login;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);
		app = (MyApplication) this.getApplication();

		if (inState != null) {
			ACTIVE_POSITION = inState.getInt(STATE_ACTIVE_POSITION);
			mContentText = inState.getString(STATE_CONTENT_TEXT);
		}

		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_CONTENT);
		mMenuDrawer.setContentView(R.layout.activity_home);
		setTitleImage(R.drawable.t_log);
		setleftIcon(R.drawable.t_menu);
		setRightIcon(R.drawable.t_mingyuhui);
		setTitleBackGround(R.drawable.t_titlebg);

		initView();
		initScrollMenu();
	}

	@Override
	protected void onResume() {
		if (MyConfig.isLogin) {
			tv_Login.setText("退出登录");
		}
		super.onResume();
	}

	/**
	 * 初始化首页侧面菜单
	 */
	private void initScrollMenu() {
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout LinearLayout = (LinearLayout) inflater.inflate(
				R.layout.item_homemenu, null);
		ll_Order = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_Order);
		ll_SpecialOff = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_SpecialOff);
		ll_Dinner = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_Dinner);
		ll_News = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_News);
		ll_About = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_About);
		ll_Login = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_Login);
		ll_MyOrder = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_MyOrder);
		ll_opinion = (android.widget.LinearLayout) LinearLayout
				.findViewById(R.id.menu_Opinion);
		tv_my = (TextView) LinearLayout.findViewById(R.id.mingyuhui);
		tv_Login = (TextView) LinearLayout.findViewById(R.id.login);

		tv_my.setOnClickListener(this);
		ll_Order.setOnClickListener(this);
		ll_SpecialOff.setOnClickListener(this);
		ll_Dinner.setOnClickListener(this);
		ll_News.setOnClickListener(this);
		ll_About.setOnClickListener(this);
		ll_Login.setOnClickListener(this);
		ll_MyOrder.setOnClickListener(this);
		ll_opinion.setOnClickListener(this);

		mMenuDrawer.setMenuView(LinearLayout);
	}

	private void initView() {

		rel_MingYu = (RelativeLayout) findViewById(R.id.home_MingYu);
		rel_OnlineOrder = (RelativeLayout) findViewById(R.id.home_OnlineOrder);
		rel_SpecialOffers = (RelativeLayout) findViewById(R.id.home_SpecialOffers);
		rel_More = (RelativeLayout) findViewById(R.id.home_more);

		rel_MingYu.setOnClickListener(this);
		rel_SpecialOffers.setOnClickListener(this);
		rel_OnlineOrder.setOnClickListener(this);
		rel_More.setOnClickListener(this);
		ViewPagerControl vc = new ViewPagerControl(this);
		vc.initViewPager(null);

		new LoadingAsync(this, Constant.RequestMethod.METHOD_GETAPPBANNER)
				.execute();

	}

	private Dialog dialog = null;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.homeTitle_Left: {

			mMenuDrawer.openMenu();
			break;
		}
		case R.id.homeTitle_Right: {
			if (MyConfig.isLogin) {
				Intent intent = new Intent(this, MingYuActivity.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
			break;
		}
		case R.id.home_MingYu: {

			Intent intent = new Intent(this, MingYuActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.home_SpecialOffers: {

			Intent intent = new Intent(this, DinnerActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.home_OnlineOrder: {

			Intent intent = new Intent(this, OnlineOrderActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.home_more: {

			Intent intent = new Intent(this, LastNewsActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.menu_Order: {

			Intent intent = new Intent(this, OnlineOrderActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.menu_SpecialOff: {

			Intent intent = new Intent(this, SpecialOffersActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.menu_Dinner: {

			Intent intent = new Intent(this, DinnerActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.menu_News: {

			Intent intent = new Intent(this, LastNewsActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.menu_About: {

			Intent intent = new Intent(this, AboutMingYuActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.menu_Login: {
			if (MyConfig.isLogin) {
				if (null == dialog || !dialog.isShowing()) {
					android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
							this);
					// 设置对话框的图标
					builder.setIcon(this.getResources().getDrawable(
							R.drawable.circle));
					// 设置对话框的标题
					builder.setTitle("提示");
					// 设置对话框的显示内容
					builder.setMessage("确认退出登录吗？");
					// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {

									MyConfig.isLogin = false;
									ActivityUtil.saveConfig(HomeActivity.this,
											LoginActivity.IFAUTOLOGIN,
											Constant.NO);
									alert("已退出登录");
									tv_Login.setText("登录");

								}
							});
					builder.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									dialog.cancel();
								}
							});
					dialog = builder.create();
					dialog.show();
					// 创建一个普通对话框

				}
			} else {

				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
			break;
		}
		case R.id.menu_MyOrder: {
			Intent intent;
			if (MyConfig.isLogin) {
				intent = new Intent(this, MyOrderActivity.class);
				startActivity(intent);
			} else {
				intent = new Intent(this, GetOrderByEmail.class);
				startActivity(intent);
			}
			break;
		}
		case R.id.menu_Opinion: {

			Intent intent = new Intent(this, OpinionActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.mingyuhui: {

			Intent intent = new Intent(this, MingYuActivity.class);
			startActivity(intent);
			break;
		}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		final int drawerState = mMenuDrawer.getDrawerState();
		if (drawerState == MenuDrawer.STATE_OPEN
				|| drawerState == MenuDrawer.STATE_OPENING) {
			mMenuDrawer.closeMenu();
			return;
		}

		super.onBackPressed();
	}

	@Override
	public void setActiveView(View v, int position) {
		if (position == ACTIVE_POSITION) {
			mMenuDrawer.setActiveView(v, position);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_ACTIVE_POSITION, ACTIVE_POSITION);
		outState.putString(STATE_CONTENT_TEXT, mContentText);
	}

	@Override
	// 建议在APP整体退出之前调用MapApi的destroy()函数，不要在每个activity的OnDestroy中调用，
	// 避免MapApi重复创建初始化，提高效率
	protected void onDestroy() {

		MyConfig.isLogin = false;
		// Intent serviceIntent = new Intent(this, AngelBeatService.class);
		// serviceIntent.setAction(AngelBeatService.ACTION);
		// stopService(serviceIntent);
		super.onDestroy();
		System.exit(0);
	}

	@Override
	public View makeView() {
		ImageView i = new ImageView(this);
		i.setScaleType(ImageView.ScaleType.FIT_XY);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return i;
	}

	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult) {
		JSONObject result = jsonResult.optJSONObject(Constant.JSON_DATA);

		JSONArray imgs = result.optJSONArray("AppBanner");

		String[] imgsString = new String[imgs.length()];

		for (int i = 0; i < imgs.length(); i++) {
			JSONObject imgObject = imgs.optJSONObject(i);
			imgsString[i] = imgObject.optString("Image");
		}
		ViewPagerControl vc = new ViewPagerControl(this);
		vc.initViewPager(imgsString);
	}

	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode) {
		// ViewPagerControl vc = new ViewPagerControl(this);
		// vc.initViewPager(null);

	}

}
