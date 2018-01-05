package com.nettactic.commerces.constant;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;

import com.nettactic.commerces.R;

/**
 * 服务器请求常量类
 * 
 * 
 * 
 */
public final class ServerConstant
{
	public static class ServiceName
	{
		public static final String LOADING = "loading";

		public static final String REGIST = "regist";

		public static final String GETORDER = "getOrder";
	};

	public static class HttpUrl
	{
		public static final String LoadUrl = "http://www.baidu.com";
	}

	public static class ReturnCode
	{
		/* 服务端返回的错误码 BEGIN */
		public static final int OPERATE_SUCCESS = 1001;// 操作成功

		public static final int FAIL_TO_VERIFY = 1002;// session验证失败

		public static final int OTHER_ERROR = 1003;// 其他错误

		public static final int WRONG_CARDNUMBER = 1004;// 卡号错误

		public static final int WRONG_CARD_PASSWORD = 1005;// 卡密码错误

		public static final int CARD_OUT_OF_DATE = 1006;// 卡过期

		public static final int WRONG_COMPANY_RESEVER_ACCOUNT = 1007;// 公司预订账号错误

		public static final int WRONG_MEMBER_CARDNUMBER = 1008;// 会员卡号错误

		public static final int RESEVER_NUMBER_INEXISTENCE = 1009;// 订单号不存在

		public static final int WRONG_EMAIL = 1010;// email错误

		public static final int WRONG_CUSTOMER_TYPE = 1011;// 客人类型错误

		public static final int UNAUTHORIZED = 1000;// 权限不够

		public static final int STATUS_DEV_NO_AUTHORIZED = 1012;// 未鉴权

		public static final int STATUS_DEV_AUTHORIZED_TIME_OUT = 1013;// 鉴权超时

		/* 服务端返回的错误码 END */

		/* 本地自定义的错误码 BEGIN */
		public static final int STATUS_INTENAL_ERROR = 2001;// 网络错误

		public static final int VALIDATOR_CONNECT_TIMEOUT = 2002;// 链接超时

		/* 本地自定义的错误码 END */

		@SuppressLint("UseSparseArrays")
		public static final Map returnCodeMap = new HashMap<Integer, Integer>()
		{
			{
				put(OPERATE_SUCCESS, R.string.OPERATE_SUCCESS);

				put(FAIL_TO_VERIFY, R.string.FAIL_TO_VERIFY);

				put(OTHER_ERROR, R.string.OTHER_ERROR);

				put(WRONG_CARDNUMBER, R.string.WRONG_CARDNUMBER);

				put(WRONG_CARD_PASSWORD, R.string.WRONG_CARD_PASSWORD);

				put(CARD_OUT_OF_DATE, R.string.CARD_OUT_OF_DATE);

				put(WRONG_COMPANY_RESEVER_ACCOUNT,
						R.string.WRONG_COMPANY_RESEVER_ACCOUNT);

				put(WRONG_MEMBER_CARDNUMBER, R.string.WRONG_MEMBER_CARDNUMBER);

				put(RESEVER_NUMBER_INEXISTENCE,
						R.string.RESEVER_NUMBER_INEXISTENCE);

				put(WRONG_EMAIL, R.string.WRONG_EMAIL);

				put(WRONG_CUSTOMER_TYPE, R.string.WRONG_CUSTOMER_TYPE);

				put(UNAUTHORIZED, R.string.UNAUTHORIZED);

				put(STATUS_DEV_NO_AUTHORIZED, R.string.STATUS_DEV_NO_AUTHORIZED);

				put(STATUS_DEV_AUTHORIZED_TIME_OUT,
						R.string.STATUS_DEV_AUTHORIZED_TIME_OUT);

				put(STATUS_INTENAL_ERROR, R.string.STATUS_INTENAL_ERROR);

				put(VALIDATOR_CONNECT_TIMEOUT,
						R.string.VALIDATOR_CONNECT_TIMEOUT);
			}
		};

	}

	/**
	 * 短信外码列表
	 * 
	 * 
	 * 
	 */
	public static class MSGResultCode
	{
		/**
		 * 发送成功
		 */
		public static final int MSGSUCCESS = 1;

		/**
		 * 帐号或密码为空
		 */
		public static final int MSG_ACCOUNTO_PSW_NULL = -1;

		/**
		 * 下发号码为空
		 */
		public static final int MSG_NUM_NULL = -2;

		/**
		 * 下发内容为空
		 */
		public static final int MSG_CONTENT_NULL = -3;

		/**
		 * 下发号码错误
		 */
		public static final int MSG_NUM_ERROR = -5;

		/**
		 * 没有指定通道
		 */
		public static final int NO_MSG_WAY = -9;

		/**
		 * 下发账户已停用
		 */
		public static final int MSG_NUM_STOP = -10;

		/**
		 * 下发账户余额不足
		 */
		public static final int MSG_NUM_INSUFFICIENT = -11;

		/**
		 * 密码错误次数超限
		 */
		public static final int OUT_MSG_PSW_CHECKTIME = -17;

		/**
		 * 密码错误
		 */
		public static final int MSG_PSWERROR = -18;

		/**
		 * 账户类型不符
		 */
		public static final int NOT_MSGNUM = -19;

		/**
		 * 单次提交超过规定限额，一般为内容超长
		 */
		public static final int MSG_CONTENT_2LONG = -20;

		/**
		 * 系统异常
		 */
		public static final int SYSTEM_ERROR = -99;

	}
}
