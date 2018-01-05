package com.nettactic.commerces.activity;

import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.nettactic.commerces.R;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;

/**
 * 日历操作类
 * 
 * @author yaguang
 * 
 */
public class CalendarViewActivity extends BaseActivity {

	private CalendarPickerView calendar;
	private Date myDate = null;
	private Date backDate = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendarview);

		Intent intent = getIntent();
		if (intent.hasExtra(OnlineOrderActivity.DATAVALUE)) // 离店时间
		{
			this.myDate = (Date) intent
					.getSerializableExtra(OnlineOrderActivity.DATAVALUE);

			setTitle("离店时间");

		} else {

			setTitle("入住时间");
		}
		setleftIcon(R.drawable.t_backarr);

		initView();
	}

	private void initView() {
		final Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.DAY_OF_YEAR, 100);

		final Calendar lastYear = Calendar.getInstance();
		if (null != myDate) {
			lastYear.setTime(myDate);
		}
		lastYear.add(Calendar.DAY_OF_YEAR, 0);

		calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
		calendar.init(lastYear.getTime(), nextYear.getTime()) //
				.inMode(SelectionMode.SINGLE) //
				.withSelectedDate(null != myDate ? myDate : new Date());

		calendar.setOnDateSelectedListener(new OnDateSelectedListener() {

			@Override
			public void onDateUnselected(Date date) {

			}

			@Override
			public void onDateSelected(Date date) {
				backDate = date;
				setBackIntent();
			}
		});
	}

	private void setBackIntent() {
		Intent intentData = new Intent();
		intentData.putExtra(OnlineOrderActivity.DATAVALUE,
				null != backDate ? backDate : new Date());
		// 请求代码可以自己设置，这里设置成20
		setResult(OnlineOrderActivity.DATAKEY, intentData);
		// 关闭掉这个Activity
		finish();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			setBackIntent();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
