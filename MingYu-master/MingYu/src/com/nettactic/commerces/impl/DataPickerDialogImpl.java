package com.nettactic.commerces.impl;

public interface DataPickerDialogImpl
{

	/**
	 * 设置日期到页面上
	 * 
	 * @param type
	 *            日期类型：入住日期，离店日期
	 * @param data
	 *            用户选择的时间
	 * @return
	 */
	Void setDataToPage(String type, String data);
}
