package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.control.NetStateService;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;

/**
 * 启动页动画
 * 
 */
public class StartUpActivity extends Activity implements ReqResultImpl {

	private Map<String, String> map;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);

		// 启动网络状态的监听
		Intent intent = new Intent(StartUpActivity.this, NetStateService.class);
		startService(intent);

		autoLogin();
	}

	/**
	 * 自动登录功能
	 */
	private void autoLogin() {
		if (!ActivityUtil.isEmpty(ActivityUtil.getConfig(this,
				LoginActivity.IFAUTOLOGIN))
				&& Constant.YES.equals(ActivityUtil.getConfig(this,
						LoginActivity.IFAUTOLOGIN))) {

			/* 自动登录功能 */
			String[] keys = new String[3];
			keys[0] = LoginActivity.CARDTYPE;
			keys[1] = LoginActivity.CARDNO;
			keys[2] = LoginActivity.CARDPASSWORD;

			map = ActivityUtil.getConfig(this, keys);

			if (!ActivityUtil.isEmpty(map.get(LoginActivity.CARDTYPE))
					&& !ActivityUtil.isEmpty(map.get(LoginActivity.CARDNO))
					&& !ActivityUtil.isEmpty(map
							.get(LoginActivity.CARDPASSWORD))) {

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair(LoginActivity.CARDTYPE, map
						.get(LoginActivity.CARDTYPE)));
				params.add(new BasicNameValuePair(LoginActivity.CARDNO, map
						.get(LoginActivity.CARDNO)));
				params.add(new BasicNameValuePair(LoginActivity.CARDPASSWORD,
						map.get(LoginActivity.CARDPASSWORD)));
				new LoadingAsync(this, RequestMethod.METHOD_LOGIN, params)
						.execute();
			} else {
				gotoHomePage();
			}
		} else {
			gotoHomePage();
		}
	}

	private void gotoHomePage() {
		Intent activityIntent = new Intent(StartUpActivity.this,
				HomeActivity.class);
		StartUpActivity.this.startActivity(activityIntent);
		finish();
	}

	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult) {
		MyConfig.isLogin = true;
		UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
		userInfoModel.initUserInfoModel(jsonResult
				.optJSONObject(Constant.JSON_DATA));
		userInfoModel.setCardType(map.get(LoginActivity.CARDTYPE));
		userInfoModel.setCardNo(map.get(LoginActivity.CARDNO));

		gotoHomePage();

	}

	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode) {
		gotoHomePage();
	}

}
