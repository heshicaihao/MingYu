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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.widget.ViewPagerControl;

/**
 * 会议与婚礼以及本地
 * 
 * @author yaguang
 * 
 *         HotelCode=MYLY &TypeCode=7 会议：7；当地：4
 */
public class Single_MettingWedding_Loc_Activity extends BaseActivity implements
		ReqResultImpl
{
	public static final String METTING = "metting";
	public static final String LOCAL = "local";
	private final String SEVEN = "7";
	private final String FOUR = "4";
	private TextView name;
	private TextView tel;
	private TextView addr;
	private Button btn_order;
	private ListView listView;
	
	private String[] imageList;
	private String[] nameList;
	private String[] urlList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metting_wedding_loc);
		setTitle("");
		setleftIcon(R.drawable.t_backarr);
		
		ViewPagerControl vpc = new ViewPagerControl(this);
		vpc.initViewPager(null);
		
		Intent intent = getIntent();
		initView(intent);
		// 会议与婚礼
		if (intent.hasExtra(METTING))
		{
			setTitle("会议与婚礼");
			initMettingDate();
			
		}
		// 本地
		else if (intent.hasExtra(LOCAL))
		{
			setTitle("当地");
			initLocalDate();
		}
	}
	
	private void initView(Intent intent)
	{
		int[] color = intent
				.getIntArrayExtra(SingleHotelHomeActivity.TITLECOLOR);
		setTitleColor(color[0]);
		name = (TextView) findViewById(R.id.single_hotelname);
		tel = (TextView) findViewById(R.id.single_tel);
		addr = (TextView) findViewById(R.id.single_addr);
		btn_order = (Button) findViewById(R.id.single_Order);
		listView = (ListView) findViewById(R.id.single_listview);
		
		name.setText(intent.getStringExtra("HotelName"));
		tel.setText(intent.getStringExtra("HotelTel"));
		addr.setText(intent.getStringExtra("HotelAddr"));
		btn_order.setBackgroundColor(color[1]);
		btn_order.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				Intent i = new Intent(Single_MettingWedding_Loc_Activity.this,
						OnlineOrderActivity.class);
				startActivity(i);
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				Intent intent = new Intent(
						Single_MettingWedding_Loc_Activity.this,
						SpecialOffersDetailActivity.class);
				intent.putExtra(METTING, "");
				intent.putExtra(SpecialOffersDetailActivity.TITLE,
						nameList[arg2]);
				intent.putExtra(SpecialOffersDetailActivity.CONTENTURL,
						urlList[arg2]);
				startActivity(intent);
			}
		});
	}
	
	private void initMettingDate()
	{
		if (ActivityUtil.isEmpty(SingleHotelHomeActivity.hotelCode))
		{
			alert("数据有误，请重试");
		} else
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("TypeCode", SEVEN));
			params.add(new BasicNameValuePair("HotelCode",
					SingleHotelHomeActivity.hotelCode));
			new LoadingAsync(this, RequestMethod.METHOD_HOTELMEETINGORLOCAL,
					params).execute();
		}
		
	}
	
	private void initLocalDate()
	{
		if (ActivityUtil.isEmpty(SingleHotelHomeActivity.hotelCode))
		{
			alert("数据有误，请重试");
		} else
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("TypeCode", FOUR));
			params.add(new BasicNameValuePair("HotelCode",
					SingleHotelHomeActivity.hotelCode));
			new LoadingAsync(this, RequestMethod.METHOD_HOTELMEETINGORLOCAL,
					params).execute();
		}
		
	}
	
	private class MAdapter extends BaseAdapter
	{
		
		@Override
		public int getCount()
		{
			return nameList.length;
		}
		
		@Override
		public Object getItem(int arg0)
		{
			return nameList[arg0];
		}
		
		@Override
		public long getItemId(int arg0)
		{
			return arg0;
		}
		
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2)
		{
			
			arg1 = (View) LayoutInflater.from(
					Single_MettingWedding_Loc_Activity.this).inflate(
					R.layout.group_item_layout, null);
			TextView text = (TextView) arg1.findViewById(R.id.room_name);
			text.setText(nameList[arg0]);
			return arg1;
		}
		
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		JSONObject date;
		try
		{
			date = jsonResult.getJSONObject(Constant.JSON_DATA);
			JSONArray imageArray = date.optJSONArray("Images");
			if (null != imageArray && imageArray.length() > 0)
			{
				
				imageList = new String[imageArray.length()];
				for (int i = 0; i < imageArray.length(); i++)
				{
					imageList[i] = imageArray.optJSONObject(i).optString(
							"Image");
				}
				ViewPagerControl vc = new ViewPagerControl(this);
				vc.initViewPager(imageList);
			}
			
			JSONArray contArray = date.optJSONArray("List");
			nameList = new String[contArray.length()];
			urlList = new String[contArray.length()];
			for (int i = 0; i < contArray.length(); i++)
			{
				JSONObject value = contArray.optJSONObject(i);
				nameList[i] = value.optString("Name");
				urlList[i] = value.optString("IntroUrl");
			}
			
			MAdapter madapter = new MAdapter();
			listView.setAdapter(madapter);
		} catch (JSONException e)
		{
			alert("获取数据失败，请重试");
		}
		
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		alert("获取数据失败，请重试");
	}
}
