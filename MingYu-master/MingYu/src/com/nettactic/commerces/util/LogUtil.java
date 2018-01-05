package com.nettactic.commerces.util;

import android.util.Log;

/**
 * 日志辅助类
 * 
 */
public final class LogUtil
{
	/**
	 * 默认日志tag
	 */
	private static final String DEFAULT_TAG = "NULL";

	/**
	 * Send a {@link Log#VERBOSE} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int v(String TAG, String msg)
	{

		return Log.v(TAG, msg);
	}

	/**
	 * Send a {@link Log#VERBOSE} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int v(String TAG, String msg, Throwable tr)
	{
		return Log.v(TAG, msg, tr);
	}

	/**
	 * Send a {@link Log#VERBOSE} log message and log the exception.
	 * 
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int v(String msg, Throwable tr)
	{
		return Log.v(DEFAULT_TAG, msg, tr);
	}

	/**
	 * Send a {@link Log#DEBUG} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int d(String TAG, String msg)
	{
		return Log.d(TAG, msg);
	}

	/**
	 * Send a {@link Log#DEBUG} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int d(String TAG, String msg, Throwable tr)
	{
		return Log.d(TAG, msg, tr);
	}

	/**
	 * Send a {@link Log#DEBUG} log message and log the exception.
	 * 
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int d(String msg, Throwable tr)
	{
		return Log.d(DEFAULT_TAG, msg, tr);
	}

	/**
	 * Send an {@link Log#INFO} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int i(String TAG, String msg)
	{
		return Log.i(TAG, msg);
	}

	/**
	 * Send a {@link Log#INFO} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int i(String TAG, String msg, Throwable tr)
	{
		return Log.i(TAG, msg, tr);
	}

	/**
	 * Send a {@link Log#INFO} log message and log the exception.
	 * 
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int i(String msg, Throwable tr)
	{
		return Log.i(DEFAULT_TAG, msg, tr);
	}

	/**
	 * Send a {@link Log#WARN} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int w(String TAG, String msg)
	{
		return Log.w(TAG, msg);
	}

	/**
	 * Send a {@link Log#WARN} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int w(String TAG, String msg, Throwable tr)
	{
		return Log.w(TAG, msg, tr);
	}

	/**
	 * Checks to see whether or not a log for the specified tag is loggable at
	 * the specified level.
	 * 
	 * The default level of any tag is set to INFO. This means that any level
	 * above and including INFO will be logged. Before you make any calls to a
	 * logging method you should check to see if your tag should be logged. You
	 * can change the default level by setting a system property: 'setprop
	 * log.tag.&lt;YOUR_LOG_TAG> &lt;LEVEL>' Where level is either VERBOSE,
	 * DEBUG, INFO, WARN, ERROR, ASSERT, or SUPPRESS. SUPRESS will turn off all
	 * logging for your tag. You can also create a local.prop file that with the
	 * following in it: 'log.tag.&lt;YOUR_LOG_TAG>=&lt;LEVEL>' and place that in
	 * /data/local.prop.
	 * 
	 * @param tag
	 *            The tag to check.
	 * @param level
	 *            The level to check.
	 * @return Whether or not that this is allowed to be logged.
	 * @throws IllegalArgumentException
	 *             is thrown if the tag.length() > 23.
	 */
	public static native boolean isLoggable(String TAG, int level);

	/**
	 * 
	 * Send a {@link Log#WARN} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param tr
	 *            An exception to log
	 **/
	public static int w(String TAG, Throwable tr)
	{
		return Log.w(TAG, tr);
	}

	/**
	 * Send an {@link Log#ERROR} log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static int e(String TAG, String msg)
	{
		return Log.e(TAG, msg);
	}

	/**
	 * Send a {@link Log#ERROR} log message and log the exception.
	 * 
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int e(String msg, Throwable tr)
	{
		return Log.e(DEFAULT_TAG, msg, tr);
	}

	/**
	 * Send a {@link Log#ERROR} log message and log the exception.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static int e(String TAG, String msg, Throwable tr)
	{
		return Log.e(TAG, msg, tr);
	}

	/**
	 * Handy function to get a loggable stack trace from a Throwable
	 * 
	 * @param tr
	 *            An exception to log
	 */
	public static String getStackTraceString(Throwable tr)
	{
		return Log.getStackTraceString(tr);
	}

	@SuppressWarnings("unchecked")
	public static String makeLogTag(Class cls)
	{
		return "Androidpn_" + cls.getSimpleName();
	}

	private LogUtil()
	{
	}
}