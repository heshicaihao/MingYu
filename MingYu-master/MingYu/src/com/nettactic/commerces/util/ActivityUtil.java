package com.nettactic.commerces.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.EditTexts;
import com.nettactic.commerces.control.MyConfig;

/**
 * Activity公用的操作类
 * 
 * 
 * 
 */
public class ActivityUtil
{
	
	private static final String TAG = "ActivityUtil";
	
	private static final String CONFIG = "com.nettactic.commerce.util.ActivityUtil";
	
	/**
	 * 保存用户信息到本地数据缓存中
	 * 
	 * @param ctx
	 * @param key
	 * @param value
	 */
	public static void saveConfig(Context ctx, String key, String value)
	{
		if (null == ctx || TextUtils.isEmpty(key))
		{
			return;
		}
		Editor edit = ctx.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
				.edit();
		if (TextUtils.isEmpty(value))
		{
			edit.remove(key);
		} else
		{
			edit.putString(key, value);
		}
		edit.commit();
	}
	
	/**
	 * 保存用户信息到本地数据缓存中
	 * 
	 * @param ctx
	 * @param config
	 */
	public static void saveConfig(Context ctx, Map<String, String> config)
	{
		if (null == ctx || null == config || config.isEmpty())
		{
			return;
		}
		SharedPreferences pre = ctx.getSharedPreferences(CONFIG,
				Context.MODE_PRIVATE);
		
		Editor edit = pre.edit();
		Set<String> key = config.keySet();
		String value = null;
		for (String k : key)
		{
			value = config.get(k);
			if (TextUtils.isEmpty(value))
			{
				edit.remove(k);
			} else
			{
				edit.putString(k, value);
			}
		}
		edit.commit();
	}
	
	/**
	 * 获取到本地缓存数据
	 * 
	 * @param ctx
	 * @param key
	 * @return String
	 */
	public static String getConfig(Context ctx, String key)
	{
		if (null == ctx || TextUtils.isEmpty(key))
		{
			return null;
		}
		return ctx.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
				.getString(key, "");
	}
	
	/**
	 * 获取到本地缓存数据
	 * 
	 * @param ctx
	 * @param key
	 * @return HashMap<String, String>
	 */
	public static Map<String, String> getConfig(Context ctx, String[] key)
	{
		if (null == ctx || null == key || key.length < 1)
		{
			return null;
		}
		SharedPreferences pre = ctx.getSharedPreferences(CONFIG,
				Context.MODE_PRIVATE);
		if (null == pre)
		{
			return null;
		}
		Map<String, String> result = new HashMap<String, String>(key.length);
		for (int i = 0; i < key.length; i++)
		{
			result.put(key[i], pre.getString(key[i], ""));
		}
		return result;
	}
	
	// public static boolean clearUserInfo(Context ctx, String[] key)
	// {
	// if (null == ctx || null == key || key.length < 1)
	// {
	// return false;
	// }
	// SharedPreferences pre = ctx.getSharedPreferences(CONFIG,
	// Context.MODE_PRIVATE);
	// if (null == pre)
	// {
	// return false;
	// }
	//
	// SharedPreferences.Editor editor = pre.edit();
	// for (String k : key)
	// {
	//
	// editor.remove(k);
	// }
	//
	// editor.commit();
	// return true;
	// }
	
	/**
	 * 切割以@分割的字符串
	 */
	public static String split(String str)
	{
		String[] str0 = str.split("@");
		return str0[0];
	}
	
	/**
	 * 从网络加载图片文件到本地
	 * 
	 * IOUtils是Apache提供的一个数据包，用来简化IO操作
	 */
	public static void copyUrl(String srcUrl, OutputStream destOutputstream)
	{
		InputStream is = null;
		HttpEntity entity = null;
		HttpClient client = null;
		try
		{
			client = new DefaultHttpClient();
			HttpGet get = new HttpGet(srcUrl);
			HttpResponse response = client.execute(get);
			entity = response.getEntity();
			is = entity.getContent();
			IOUtils.copy(is, destOutputstream);
		} catch (IOException ex)
		{
			entity = null;
			is = null;
			destOutputstream = null;
		} finally
		{
			if (entity != null)
			{
				try
				{
					entity.consumeContent();
				} catch (IOException e)
				{
					
				}
			}
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(destOutputstream);
		}
	}
	
	/**
	 * 判断一个服务是否正在运行
	 * 
	 * @param mContext
	 * @param className
	 * @return boolean
	 */
	public static boolean isServiceRunning(Context mContext, String className)
	{
		
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		
		if (!(serviceList.size() > 0))
		{
			return false;
		}
		
		for (int i = 0; i < serviceList.size(); i++)
		{
			if (serviceList.get(i).service.getClassName().equals(className) == true)
			{
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
	
	/**
	 * View 的摆动和手机的震动
	 * 
	 * @param activity
	 * @param v
	 */
	public static void Vibrate(final Activity activity, View v)
	{
		Animation shake = AnimationUtils.loadAnimation(activity, R.anim.shake);
		
		Vibrator vib = (Vibrator) activity
				.getSystemService(Service.VIBRATOR_SERVICE);
		
		v.startAnimation(shake);
		vib.vibrate(300);
		
	}
	
	/**
	 * 字段类型的正则表达式验证
	 * 
	 * @param ETS
	 * @param value
	 * @return
	 */
	public static boolean verifyField(EditTexts ETS, String value)
	{
		if (MyConfig.ifDebug)
		{
			LogUtil.d(TAG, "ETS:" + ETS.name() + "value" + value);
		}
		if (null == ETS || "".equals(ETS) || ActivityUtil.isEmpty(value))
		{
			return false;
		}
		
		String strPattern = "";
		switch (ETS)
		{
		case FIRSTNAME:
		case LASTNAME:
		case ADDR:
			return true;
		case IDNO:
		{
			strPattern = "\\d{15}|\\d{18}";
			break;
		}
		case PHONE:
		{
			strPattern = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
			break;
		}
		case EMAIL:
		{
			
			// strPattern =
			// "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
			strPattern = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
			// strPattern =
			// "/^[0-9a-zA-Z]+@(([0-9a-zA-Z]+)[.])+[a-z]{2,4}$/i";
			break;
		}
		case POSTALCODE:
		{
			strPattern = "[1-9]\\d{5}(?!\\d)";
			break;
		}
		default:
		{
			break;
		}
		}
		// return checkPattern(strPattern, value);
		return true;
	}
	
	private static boolean checkPattern(String strPattern, String value)
	{
		if ("" != strPattern)
		{
			Pattern p = Pattern.compile(strPattern);
			Matcher m = p.matcher(value);
			System.out.println(m.matches() + "-value--" + value);
			return m.matches();
		} else
		{
			return false;
		}
	}
	
	/**
	 * 判断一个String类型的数据是否为空
	 */
	public static boolean isEmpty(String value)
	{
		if (null == value || "".equals(value) || value.length() < 1)
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	/**
	 * 判断一个List是否为空
	 */
	public static boolean isEmpty(List list)
	{
		if (null == list || list.size() < 1)
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public static int compareDate(String DATE1, String DATE2)
	{
		
		DateFormat df = new SimpleDateFormat(Constant.DATAFORMAT);
		try
		{
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime())
			{
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime())
			{
				System.out.println("dt1在dt2后");
				return -1;
			} else
			{
				return 0;
			}
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return 0;
	}
}
