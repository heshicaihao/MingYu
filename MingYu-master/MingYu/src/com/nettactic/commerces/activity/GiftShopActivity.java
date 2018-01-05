package com.nettactic.commerces.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.nettactic.commerces.R;
import com.nettactic.commerces.adapter.GiftListAdapter;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.model.GiftModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.origamilabs.library.views.StaggeredGridView;

public class GiftShopActivity extends BaseActivity implements ReqResultImpl,
		OnClickListener
{
	private LinearLayout ll_Title, ll_GiftClass, ll_KeyWord;
	
	private ImageView tv_More;
	private RelativeLayout rl_Code;
	
	private TextView tv_Code;
	private Button btn_Search;
	
	private EditText et_keyWord;
	
	private StaggeredGridView gv_GiftList;
	
	private GiftListAdapter giftListAdapter;
	
	private String[] giftTypeCode;
	
	private final String[] menus =
	{ "热门礼品", "推荐礼品", "商务旅行", "时尚生活", "明宇酒店产品", "会员礼券", "数码家电 ", "明宇订制礼品",
			"杂志期刊" };
	private List<TextView> textList = new ArrayList<TextView>();
	private int count = 0;
	
	private static int menuID = 0;
	private static String minPointValue;
	private static String maxPointValue;
	private static String keyWord;
	
	private List<GiftModel> giftList = new ArrayList<GiftModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_giftlist);
		setTitle("礼品商城");
		setleftIcon(R.drawable.t_backarr);
		
		giftTypeCode = getResources().getStringArray(R.array.GiftTypeCode);
		
		initView();
		initDate();
	}
	
	private void initView()
	{
		
		ll_Title = (LinearLayout) findViewById(R.id.title_linearlayout);
		
		ll_GiftClass = (LinearLayout) findViewById(R.id.gift_class);
		ll_KeyWord = (LinearLayout) findViewById(R.id.ll_keyWord);
		rl_Code = (RelativeLayout) findViewById(R.id.code_RL);
		btn_Search = (Button) findViewById(R.id._Search);
		
		tv_More = (ImageView) findViewById(R.id.more);
		tv_Code = (TextView) findViewById(R.id.code_TV);
		et_keyWord = (EditText) findViewById(R.id.onOrder_KeyWord);
		gv_GiftList = (StaggeredGridView) findViewById(R.id.gl_list);
		
		et_keyWord.setOnEditorActionListener(OnEditorActionListener);
		tv_More.setOnClickListener(this);
		rl_Code.setOnClickListener(this);
		btn_Search.setOnClickListener(this);
		
		initMenuList();
	}
	
	private void initDate()
	{
		
		giftListAdapter = new GiftListAdapter(this, giftList);
		gv_GiftList.setAdapter(giftListAdapter);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("Kind", giftTypeCode[menuID]));
		
		params.add(new BasicNameValuePair("MinPoint", ActivityUtil
				.isEmpty(minPointValue) ? "" : minPointValue));
		
		params.add(new BasicNameValuePair("MaxPoint", ActivityUtil
				.isEmpty(maxPointValue) ? "" : maxPointValue));
		
		params.add(new BasicNameValuePair("KeyWord", ActivityUtil
				.isEmpty(keyWord) ? "" : keyWord));
		
		new LoadingAsync(this, RequestMethod.METHOD_GETGIFTBYKIND, params)
				.execute();
	}
	
	private void initMenuList()
	{
		for (int i = 0; i < menus.length; i++)
		{
			getSlideMenuLinearLayout(menus[i]);
		}
		changeColor();
	}
	
	private OnEditorActionListener OnEditorActionListener = new TextView.OnEditorActionListener()
	{
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
		{
			if (actionId == EditorInfo.IME_ACTION_SEARCH
					|| (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
			{
				keyWord = et_keyWord.getText().toString();
				initDate();
				return true;
			}
			return false;
		}
	};
	
	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.code_RL)
		{
			Intent intent = new Intent(this, PointRangeActivity.class);
			startActivityForResult(intent, PointRangeActivity.POINTFROMCODE);
		} else if (v.getId() == R.id.more)
		{
			Intent intent = new Intent(this, GiftClassActivity.class);
			intent.putExtra(GiftClassActivity.GIFTCLASSKRY, menuID);
			startActivityForResult(intent, GiftClassActivity.GIFTCLASSCODE);
			
		} else if (v.getId() == R.id._Search)
		{
			keyWord = et_keyWord.getText().toString();
			if (ActivityUtil.isEmpty(keyWord))
			{
				alert("请输入搜索关键字");
			} else
			{
				initDate();
				ll_GiftClass.setVisibility(View.GONE);
				ll_KeyWord.setVisibility(View.GONE);
				rl_Code.setVisibility(View.GONE);
				btn_Search.setVisibility(View.GONE);
			}
		}
		super.onClick(v);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == PointRangeActivity.POINTFROMCODE)
		{
			String[] str = data
					.getStringArrayExtra(PointRangeActivity.POINTKEY);
			if (ActivityUtil.isEmpty(str[0]))
			{
				tv_Code.setText("不限");
			} else
			{
				tv_Code.setText(str[0] + "分");
			}
			minPointValue = str[1];
			maxPointValue = str[2];
			
			// 刷新数据
			initDate();
		} else if (requestCode == GiftClassActivity.GIFTCLASSCODE)
		{
			menuID = data.getIntExtra(GiftClassActivity.GIFTCLASSKRY, 0);
			initDate();
			changeColor();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		
		JSONArray data = jsonResult.optJSONArray(Constant.JSON_DATA);
		GiftModel giftModel;
		if (null != giftList && giftList.size() > 0)
		{
			giftList.clear();
		}
		
		for (int i = 0; i < data.length(); i++)
		{
			giftModel = new GiftModel(data.optJSONObject(i));
			giftList.add(giftModel);
		}
		giftListAdapter.notifyDataSetChanged();
		
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("没有适合该筛选条件的礼品，请更改帅选条件后重试");
		if (null != giftList && giftList.size() > 0)
		{
			giftList.clear();
		}
		giftListAdapter.notifyDataSetChanged();
	}
	
	public void getSlideMenuLinearLayout(String menuText)
	{
		
		TextView tvMenu = new TextView(this);
		tvMenu.setTag(count);
		tvMenu.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tvMenu.setPadding(0, 10, 10, 10);
		tvMenu.setText(menuText);
		tvMenu.setTextColor(Color.GRAY);
		tvMenu.setTextSize(15);
		tvMenu.setGravity(Gravity.CENTER_HORIZONTAL);
		tvMenu.setOnClickListener(SlideMenuOnClickListener);
		count++;
		ll_Title.addView(tvMenu);
		textList.add(tvMenu);
		
	}
	
	OnClickListener SlideMenuOnClickListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			menuID = Integer.valueOf(v.getTag().toString());
			initDate();
			changeColor();
		}
	};
	
	private void changeColor()
	{
		for (TextView tv : textList)
		{
			tv.setTextColor(getResources().getColor(R.color.textDark));
		}
		textList.get(menuID).setTextColor(
				getResources().getColor(R.color.MytextRed));
	}
	
	@Override
	protected void onDestroy()
	{
		menuID = 0;
		minPointValue = "";
		maxPointValue = "";
		keyWord = "";
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (ll_GiftClass.getVisibility() == View.GONE)
			{
				keyWord = "";
				ll_GiftClass.setVisibility(View.VISIBLE);
				ll_KeyWord.setVisibility(View.VISIBLE);
				rl_Code.setVisibility(View.VISIBLE);
				btn_Search.setVisibility(View.VISIBLE);
				initDate();
				return false;
			} else
			{
				
				this.finish();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
