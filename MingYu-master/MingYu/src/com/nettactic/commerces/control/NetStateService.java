package com.nettactic.commerces.control;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.widget.Toast;

import com.nettactic.commerces.util.LogUtil;

/**
 * 网络状态监听类
 */
public class NetStateService extends Service
{
	private ConnectivityManager connectivityManager;

	private NetworkInfo info;

	private BroadcastReceiver mReceiver = new BroadcastReceiver()
	{

		@Override
		public void onReceive(Context context, Intent intent)
		{
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION))
			{
				if (MyConfig.ifDebug)
				{
					LogUtil.d("mark", "网络状态已经改变");
				}
				connectivityManager = (ConnectivityManager)

				getSystemService(Context.CONNECTIVITY_SERVICE);
				info = connectivityManager.getActiveNetworkInfo();
				if (info != null && info.isAvailable())
				{
					String name = info.getTypeName();
					if (MyConfig.ifDebug)
					{
						LogUtil.d("mark", "当前网络名称：" + name);
						// Toast.makeText(context,
						// "网络已连接" + name,
						// Toast.LENGTH_SHORT).show();
					}

				} else
				{
					if (MyConfig.ifDebug)
					{
						Toast.makeText(context, "网络连接失败，请检查您的网络",
								Toast.LENGTH_SHORT).show();
					}
					// Dialog dialog =
					// DialogTool.createNormalDialog(getApplicationContext(),
					// R.drawable.default_bg,
					// "提示",
					// "请检查你的网络连接",
					// "确认",
					// new OnClickListener()
					// {
					//
					// @Override
					// public void onClick(DialogInterface dialog,
					// int which)
					// {
					// System.exit(0);
					// }
					// });
					// dialog.setCancelable(false);
					// dialog.getWindow()
					// .setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
					// dialog.show();
				}
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mReceiver, mFilter);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		return super.onStartCommand(intent, flags, startId);
	}
}