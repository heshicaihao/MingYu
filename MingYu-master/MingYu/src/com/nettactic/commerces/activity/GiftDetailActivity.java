package com.nettactic.commerces.activity;

import java.math.BigDecimal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.GiftModel;
import com.nettactic.commerces.model.UserInfoModel;

public class GiftDetailActivity extends BaseActivity
{
	private ImageView iv_Log;
	
	private TextView tv_Name, tv_NeedCode, tv_TransWay, tv_GiftDetail;
	
	private EditText et_Num;
	
	private Button btn_Submit;
	
	private GiftModel giftModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_giftdetail);
		setTitle("礼品详情");
		setleftIcon(R.drawable.t_backarr);
		
		initView();
		initData();
	}
	
	private void initView()
	{
		iv_Log = (ImageView) findViewById(R.id.gd_img);
		
		tv_Name = (TextView) findViewById(R.id.gd_Name);
		tv_NeedCode = (TextView) findViewById(R.id.gd_Needcode);
		tv_TransWay = (TextView) findViewById(R.id.gd_TransCast);
		tv_GiftDetail = (TextView) findViewById(R.id.gd_Detail);
		
		et_Num = (EditText) findViewById(R.id.gd_Num);
		
		btn_Submit = (Button) findViewById(R.id.gs_Check);
		
		btn_Submit.setOnClickListener(this);
	}
	
	private void initData()
	{
		Intent intent = getIntent();
		if (intent.hasExtra(FillReceiveInfo.GIFT))
		{
			giftModel = (GiftModel) intent
					.getSerializableExtra(FillReceiveInfo.GIFT);
			loadWebImg(iv_Log, giftModel.getGift_Image());
			tv_Name.setText(giftModel.getGift_Name());
			tv_NeedCode.setText(giftModel.getGift_Point());
			String detail = giftModel.getGift_Other();
			tv_GiftDetail.setText(Html.fromHtml(detail));
		} else
		{
			
		}
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.gs_Check:
			if (MyConfig.isLogin)
			{
				UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
				String userCode = userInfoModel.getTotlePoints();
				String needCode = giftModel.getGift_Point();
				String change_Num = null != et_Num.getText() ? et_Num.getText()
						.toString() : "1";
				
				BigDecimal uc = new BigDecimal(userCode);// 用户拥有的积分
				BigDecimal nc = new BigDecimal(needCode);// 礼品所需积分
				BigDecimal cn = new BigDecimal(change_Num);// 兑换数量
				BigDecimal tc = cn.multiply(nc);// 兑换所需总积分
				
				if (tc.compareTo(BigDecimal.ZERO) == 1)
				{
					if (uc.compareTo(tc) != -1)
					{
						giftModel.setChange_Num(change_Num);
						giftModel.setDiffCode(uc.subtract(nc).toString());
						Intent intent = new Intent(this, FillReceiveInfo.class);
						intent.putExtra(FillReceiveInfo.GIFT, giftModel);
						startActivity(intent);
					} else
					{
						alert("您的积分不足，不能兑换");
					}
				} else
				{
					alert("当前礼品已经兑换完，请兑换其他礼品");
				}
			} else
			{
				alert("您尚未登录，请登录");
			}
			break;
		
		default:
			break;
		}
		super.onClick(v);
		
	}
}
