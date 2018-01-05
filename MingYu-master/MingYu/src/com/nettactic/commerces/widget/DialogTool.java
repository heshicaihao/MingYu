package com.nettactic.commerces.widget;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.widget.DatePicker;
import android.widget.Toast;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.impl.DataPickerDialogImpl;

/**
 * 对话框封装类
 * 
 */
public class DialogTool
{
	
	public static Dialog createNormalDialog(Context ctx, String Msg,
			DialogInterface.OnClickListener listener)
	{
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(ctx.getResources().getDrawable(R.drawable.circle));
		// 设置对话框的标题
		builder.setTitle("提示");
		// 设置对话框的显示内容
		builder.setMessage(Msg);
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setPositiveButton("重试", listener);
		// 创建一个普通对话框
		dialog = builder.create();
		return dialog;
	}
	
	public static Dialog createNoDateDialog(Context ctx, String Msg,
			DialogInterface.OnClickListener listener)
	{
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(ctx.getResources().getDrawable(R.drawable.circle));
		// 设置对话框的标题
		builder.setTitle("提示");
		// 设置对话框的显示内容
		builder.setMessage(Msg);
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setPositiveButton("该酒店暂无更多信息", listener);
		// 创建一个普通对话框
		dialog = builder.create();
		return dialog;
	}
	
	public static Dialog createListDialog(Context ctx, int iconId,
			String title, String[] items,
			DialogInterface.OnClickListener listener)
	{
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				ctx);
		// 设置对话框的图标
		builder.setIcon(iconId);
		// 设置对话框的标题
		builder.setTitle(title);
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		builder.setItems(items, listener);
		// 创建一个列表对话框
		dialog = builder.create();
		return dialog;
	}
	
	public static Dialog createRadioDialog(final Context ctx, int iconId,
			CharSequence[] items, String title,
			DialogInterface.OnClickListener listener)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setIcon(ctx.getResources().getDrawable(iconId));
		builder.setTitle(title);
		builder.setSingleChoiceItems(items, -1, listener);
		AlertDialog alert = builder.create();
		return alert;
	}
	
	// public static Dialog createCheckBoxDialog(
	// Context ctx,
	// int iconId,
	// String title,
	// int itemsId,
	// boolean[] flags,
	// android.content.DialogInterface.OnMultiChoiceClickListener listener,
	// String btnName, OnClickListener listener2)
	// {
	// Dialog dialog = null;
	// android.app.AlertDialog.Builder builder = new
	// android.app.AlertDialog.Builder(
	// ctx);
	// // 设置对话框的图标
	// builder.setIcon(iconId);
	// // 设置对话框的标题
	// builder.setTitle(title);
	// builder.setMultiChoiceItems(itemsId, flags, listener);
	// // 添加一个按钮
	// builder.setPositiveButton(btnName, listener2);
	// // 创建一个复选对话框
	// dialog = builder.create();
	// return dialog;
	// }
	
	public static DatePickerDialog createDatePickerDialog(Context ctx,
			String msg, final String type)
	{
		final DataPickerDialogImpl d = (DataPickerDialogImpl) ctx;
		
		int iDay, iMonth, iYear;
		Calendar cal = Calendar.getInstance();
		iDay = cal.get(Calendar.DAY_OF_MONTH);
		iMonth = cal.get(Calendar.MONTH);
		iYear = cal.get(Calendar.YEAR);
		
		DatePickerDialog dateDlg = new DatePickerDialog(ctx,
				new DatePickerDialog.OnDateSetListener()
				{
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth)
					{
						
						Time chosenDate = new Time();
						chosenDate.set(dayOfMonth, monthOfYear, year);
						long dtDob = chosenDate.toMillis(true);
						CharSequence strDate = DateFormat.format(
								Constant.DATAFORMAT, dtDob);
						d.setDataToPage(type, strDate.toString());
						
					}
				}, iYear, iMonth, iDay);
		dateDlg.setMessage(msg);
		
		return dateDlg;
	}
	
	/**
	 * 
	 * 带有限制的日期选择器
	 * 
	 * @param ctx
	 * @param msg
	 * @param type
	 * @param diff
	 * @return [参数说明]
	 * @return Dialog [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	/*
	 * public static Dialog createDatePickerDialog(Context ctx, String msg,
	 * final String type, final int diff) { final int iDay; final int iMonth;
	 * final int iYear;
	 * 
	 * final DataPickerDialogImpl d = (DataPickerDialogImpl) ctx;
	 * 
	 * Calendar cal = Calendar.getInstance(); iDay =
	 * cal.get(Calendar.DAY_OF_MONTH); iMonth = cal.get(Calendar.MONTH); iYear =
	 * cal.get(Calendar.YEAR);
	 * 
	 * LayoutInflater l = LayoutInflater.from(ctx); View v =
	 * l.inflate(R.layout.datapicker_dialog, null); final DatePicker datePicker
	 * = (DatePicker) v.findViewById(R.id.datepicker); datePicker.init(iYear,
	 * iMonth, iDay, new OnDateChangedListener() {
	 * 
	 * @Override public void onDateChanged(DatePicker view, int year, int
	 * monthOfYear, int dayOfMonth) {
	 * 
	 * if (isDateAfter(view)) { view.init(iYear + 10, iMonth, iDay, this); } if
	 * (isDateBefore(view)) { view.init(iYear, iMonth, iDay, this); } Time
	 * chosenDate = new Time(); chosenDate.set(dayOfMonth, monthOfYear, year);
	 * long dtDob = chosenDate.toMillis(true); CharSequence strDate =
	 * DateFormat.format(Constant.DATAFORMAT, dtDob); d.setDataToPage(type,
	 * strDate.toString()); }
	 * 
	 * int compare_date(String DATE1, String DATE2) {
	 * 
	 * SimpleDateFormat df = new SimpleDateFormat(Constant.DATAFORMAT); try {
	 * Date dt1 = df.parse(DATE1); Date dt2 = df.parse(DATE2); if (dt1.getTime()
	 * > dt2.getTime()) { System.out.println("dt1 在dt2前"); return 1; } else if
	 * (dt1.getTime() < dt2.getTime()) { System.out.println("dt1在dt2后"); return
	 * -1; } else { return 0; } } catch (Exception exception) {
	 * exception.printStackTrace(); } return 0; } });
	 * 
	 * Dialog dialog = new
	 * AlertDialog.Builder(ctx).setTitle(datePicker.getYear() + "年" +
	 * (datePicker.getMonth() + 1) + "月" + datePicker.getDayOfMonth() + "日")
	 * .setView(v) .setNeutralButton("设置", new DialogInterface.OnClickListener()
	 * {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * 
	 * // text.setText(datePicker.getYear() + "年" // + (datePicker.getMonth() +
	 * 1) + "月" // + datePicker.getDayOfMonth() + "日"); } })
	 * .setNegativeButton("取消", new DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * dialog.cancel();
	 * 
	 * } }) .create();
	 * 
	 * return dialog;
	 * 
	 * }
	 */
	
	// public static ProgressDialog createProgressDialog(Context ctx)
	// {
	// final ProgressDialog progressDialog = new ProgressDialog(ctx);
	// progressDialog.setProgressStyle(R.style.CustomProgressDialog);
	// progressDialog.setCancelable(true);
	// return progressDialog;
	//
	// }
	
}
