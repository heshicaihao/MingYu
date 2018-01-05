/**
 * 
 */
package com.nettactic.commerces.control;

/**
 * 配置文件类
 * 
 */
public class MyConfig
{
	/** FIXME 是否需要打印日志 在调试的时候需要打印，正式发布时不需要 */
	public static final boolean ifDebug = true;

	/** 用户是否登录识别标识 */
	public static boolean isLogin = false;

	/** 设置请求超时10秒钟 */
	public static final int REQUEST_TIMEOUT = 10 * 1000;

	/** 设置等待数据超时时间10秒钟 */
	public static final int SO_TIMEOUT = 10 * 1000;

	/** 心跳频率 */
	public static final long ANGELBEAT_TIME = 1000 * 60 * 3;

}
