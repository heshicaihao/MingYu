package com.nettactic.commerces.constant;

/**
 * 此类主要用来存储Http地址，以及文件地址
 * 
 * 
 */
public final class Constant
{
	public static final String DATAFORMAT = "yyyy/MM/dd";
	
	public static final String DOTNET_HTTPS_PREFIX = "HTTPS";
	
	public static final String HTTP_POST = "http_post";
	
	public static final String HTTP_GET = "http_get";
	
	public static final String JSON_STATUS = "status";
	
	public static final String JSON_STATUS_NOTE = "statusnote";
	
	public static final String JSON_DATA = "data";
	
	public static final String NAME = "Name";
	
	public static final String VALUE = "Value";
	
	public static final int OK = -1;
	
	public static final int CANCEL = -2;
	
	public static final String IF_FIRSTLOGIN = "if_firstlogin";
	
	public static final String IF_LOCATION = "if_location";
	
	public static final String IF_PUSH = "if_push";
	
	public static final String YES = "yes";
	
	public static final String NO = "no";
	
	/* 接口请求数据的key */
	public static final String HOTELCODE = "HotelCode";
	
	public static final String HOTELNAME = "HotelName";
	
	public static final String ROOMNUM = "RoomNum";
	
	public static final String RESERVATIONTYPE = "ReservationType";
	
	public static final String ROOMTYPECODE = "RoomTypeCode";
	
	public static final String ROOMTYPENAME = "RoomTypeName";
	
	public static final String ROOMUNITPRICE = "RoomUnitprice";
	
	public static final String ROOMPRICECODE = "RoomPriceCode";
	
	public static final String FIRSTNAME = "FirstName";
	
	public static final String LASTNAME = "LastName";
	
	public static final String EMAIL = "Email";
	
	public static final String MOBILE = "Mobile";
	
	public static final String REMARK = "Remark";
	
	public static final String IDENTIFY = "Identify";
	
	public static final String ADULTS = "Adults";
	
	public static final String CHILDREN = "Children";
	
	public static final String ARRIVALTIME = "ArrivalTime";
	
	public static final String DEPARTURETIME = "DepartureTime";
	
	public static final String CUSTACCOUNT = "CustAccount";
	
	public static final String EXPRESSCHECKIN = "ExpressCheckIn";
	
	public static final String TOTALPRICE = "TotalPrice";
	
	public static final String ORDERNO = "OrderNo";
	
	public static final String CARDTYPE = "CardType";// 会员卡类型
	
	public static final String CARDNO = "CardNo";
	
	public static final String CARDPASSWORD = "CardPassWord";// 会员卡密码
	
	/**
	 * 主要使用在注册页面的字段验证
	 * 
	 * @author ruan
	 * 
	 */
	public static enum EditTexts
	{
		FIRSTNAME,
		
		LASTNAME,
		
		GENDER,
		
		IDNO,
		
		PHONE,
		
		EMAIL,
		
		ADDR,
		
		POSTALCODE;
		
	}
	
	/**
	 * 接口请求方法名
	 * 
	 */
	public static enum RequestMethod
	{
		TARGET_NAMES_PACE,
		
		METHOD_LOGIN,
		
		METHOD_REGISTER,
		
		/** 获取目的地城市列表 */
		METHOD_GETDESTINATIONCITY,
		
		/** 获取酒店列表 */
		METHOD_GETHOTELS,
		
		/** 获取全国酒店列表 */
		METHOD_GETALLHOTELS,
		
		/** 餐饮娱乐 */
		METHOD_GETDINNER,
		
		/** 根据礼品种类获取礼品 */
		METHOD_GETGIFTBYKIND,
		
		/** 优惠精选 */
		METHOD_SPECIALOFFERS,
		
		/** 找回密码 */
		METHOD_MODIFYPASSWORD,
		
		/** 修改密码 */
		METHOD_CHANGEPASSWORD,
		
		METHOD_ORDERINTERFACE,
		
		METHOD_ANGELBEAT,
		
		METHOD_HOTELINFOBYHOTELCODE,
		
		METHOD_ROOMLIST,
		
		METHOD_LOGINOUT,
		
		METHOD_GETMYORDERLIST,
		
		METHOD_GETORDERLISTBYEMAIL,
		
		METHOD_MODIFYINFOMARTION,
		
		METHOD_CANCELMYORDER,
		
		METHOD_CANCELORDER,
		
		METHOD_FEEDBACK,
		
		METHOD_FINDPSW,
		
		METHOD_MODIFYMYORDER,
		
		METHOD_MODIFYORDER,
		
		METHOD_GIFTEXCHANGE,
		
		METHOD_PAYORDER,
		
		METHOD_GUARANTEERULE,
		
		METHOD_GETEXPENSERECORD,
		
		METHOD_GETNEWSINFOR,
		
		METHOD_GETAPPBANNER,
		
		METHOD_GETHOTELSBYSURROUNDING, METHOD_CHECKTIME,
		
		METHOD_SINGELHOTELHOME, METHOD_HOTELROOMINFO, METHOD_HOTELDINNERINFOBYHOTELCODE, METHOD_HOTELMEETINGORLOCAL, METHOD_BACKPAY, METHOD_GETMYCHANGES;
	}
	
	/**
	 * 接口具体地址的枚举类型
	 */
	public static enum HttpAddress
	{
		// http://www.minyounhotels.com/
		/**
		 * 1.登录
		 */
		DOTNET_LOGIN_SOAP(
				"http://www.minyounhotels.com/sdk/ProfileService.ashx?tag=Login"),
		/**
		 * 2.注册
		 */
		DOTNET_REGISTER_SOAP(
				"http://www.minyounhotels.com/sdk/ProfileService.ashx?tag=Register"),
		
		/**
		 * 3.获取目的地城市
		 */
		DOTNET_GETDESTINATIONCITY_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=GetDestinationCity&len=ch"),
		
		/**
		 * 4.获取目的地城市酒店列表
		 */
		DOTNET_GETHOTELS_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=GetHotels&len=ch"),
		
		/**
		 * 5.获取全国酒店列表
		 */
		DOTNET_GETALLHOTELS_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=GetAllHotels"),
		
		/**
		 * 6.餐饮娱乐接口
		 */
		DOTNET_GETDINNER_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=GetDinner"),
		
		/**
		 * 7.优惠精选
		 */
		DOTNET_SPECIALOFFERS_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=SpecialOffers"),
		
		/**
		 * 8.根据分类获取礼品
		 * 
		 */
		DOTNET_GETGIFTBYKIND_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=GetGiftByKind"),
		
		/**
		 * 9.找回密码 cardNo卡号 mobile手机号 cardType卡号类型
		 */
		DOTNET_MODIFYPASSWORD_SOAP(
				"http://www.minyounhotels.com/sdk/ProfileService.ashx?tag=ModifyPassWord"),
		/**
		 * 10.修改密码 OldPwd NewPwd
		 */
		DOTNET_CHANGEPASSWORD_SOAP(
				"http://www.minyounhotels.com/sdk/ProfileService.ashx?tag=ModifyPassWord"),
		/**
		 * 11.预订酒店
		 * 
		 * OrderModel
		 */
		DOTNET_ORDERINTERFACE_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=OrderInterface"),
		/**
		 * 12.心跳
		 * 
		 * CardNo
		 */
		DOTNET_ANGELBEAT_SOAP(
				"http://www.minyounhotels.com/sdk/ProfileService.ashx?tag=AngelBeat"),
		
		/**
		 * 13.根据HotelCode获取酒店信息
		 * 
		 * 参数&HotelCode=myly
		 */
		DOTNET_HOTELINFOBYHOTELCODE_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=HotelInfo"),
		/**
		 * 14.根据HotelCode获取酒店房型列表
		 * 
		 * 参数&HotelCode=mysy
		 */
		DOTNET_ROOMLIST_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=RoomList"),
		/**
		 * 15.积分记录
		 * 
		 */
		DOTNET_GETEXPENSERECORD_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=GetExpenseRecord"),
		
		/**
		 * 16.取消会员订单 &OrderNo=
		 */
		DOTNET_CANCELMYORDER_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=CancelMyOrder"),
		
		/**
		 * 17.取消散客订单 &OrderNo
		 */
		DOTNET_CANCELORDER_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=CancelOrder"),
		
		/**
		 * 18.订单记录
		 * 
		 */
		DOTNET_GETMYORDERLIST_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=GetMyOrderList"),
		
		/**
		 * 19.根据邮箱以及订单号查询订单记录 Email OrderNo
		 */
		DOTNET_GETORDERLISTBYEMAIL_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=GetMyOrderListByEmail"),
		/**
		 * 
		 * 20.修改用户信息
		 */
		DOTNET_MODIFYINFOMARTION_SOAP(
				"http://www.minyounhotels.com/sdk/ProfileService.ashx?tag=ModifyInfomartion"),
		
		/**
		 * 21.退出
		 * 
		 */
		DOTNET_LOGINOUT_SOAP(
				"http://www.minyounhotels.com/sdk/ProfileService.ashx?tag=LoginOut"),
		/**
		 * 22.信息反馈
		 * 
		 */
		DOTNET_FEEDBACK_SOAP(
				"http://www.minyounhotels.com/sdk/UserInfo.ashx?tag=Feedback"),
		
		/**
		 * 24.找回密码 CardType CardNo MobleNo
		 */
		DOTNET_FINDPSW_SOAP(
				"http://www.minyounhotels.com/SDK/ProfileService.ashx?tag=FindPassWord"),
		/**
		 * 25.修改会员订单 CardNo， OrderNo FirstName = "";//名字 LastName = "";//姓
		 * Chname = "";//姓名（非必填） Email = ""; Mobile = ""; Remark = "";//特殊说明
		 * Identify = "";//身份证号码 Sex = "M";
		 */
		DOTNET_MODIFYMYORDER_SOAP(
				"http://www.minyounhotels.com/SDK/Order.ashx?tag=ModifyMyOrder"),
		/**
		 * 26.修改散客订单 CardNo， OrderNo FirstName = "";//名字 LastName = "";//姓
		 * Chname = "";//姓名（非必填） Email = ""; Mobile = ""; Remark = "";//特殊说明
		 * Identify = "";//身份证号码 Sex = "M";
		 */
		DOTNET_MODIFYORDER_SOAP(
				"http://www.minyounhotels.com/SDK/Order.ashx?tag=ModifyOrder"),
		
		/**
		 * 27. string userCard = context.Request["CardNo"]; ;//会员卡号 string
		 * GiftID = context.Request["GiftID"];//商品ID string GiftNum =
		 * context.Request["GiftNum"];//商品数量 string Code =
		 * context.Request["Code"];//商品code string Province =
		 * context.Request["Province"];//省 string City =
		 * context.Request["City"];//市 string Area = context.Request["Area"];//区
		 * string Address = context.Request["Address"];//具体地址 string Other =
		 * context.Request["Other"];//备注 string UserName =
		 * context.Request["UserName"];//收货人 string Mobile =
		 * context.Request["Mobile"];//收货人手机 string ExchangeType =
		 * context.Request["ExchangeType"];//收货方式0自提，1送货 string Point =
		 * "";//此商品积分
		 */
		DOTNET_GIFTEXCHANGE_SOAP(
				"http://www.minyounhotels.com/SDK/InformationService.ashx?tag=ExchangePoint"),
		/**
		 * 28。支付成功后通知服务端 &OrderNo=343319
		 */
		DOTNET_PAYORDER_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=PayOrder"),
		/**
		 * 29,查询酒店支持的付款方式 HotelCode RateCode Arrival Departure
		 * 
		 * return 1为前台支付 2信用卡 11支付宝
		 */
		DOTNET_GUARANTEERULE_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=GuaranteeRule"),
		/**
		 * 30,新闻中心
		 * 
		 * return
		 */
		DOTNET_GETNEWSINFOR_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=GetNewsInfor"),
		/**
		 * 31获取首页可滑动图片
		 * 
		 * return
		 */
		DOTNET_GETAPPBANNER_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=GetAppBanner"),
		
		/**
		 * 搜索预订
		 */
		DOTNET_GETHOTELSBYSURROUNDING_SOAP(
				"http://www.minyounhotels.com/sdk/InformationService.ashx?tag=GetHotelsBySurrounding"),
		
		/**
		 * 单体酒店首页信息
		 */
		DOTNET_SINGELHOTELHOME_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=HotelInfoHotelCode"),
		/**
		 * 单体酒店客房信息
		 */
		DOTNET_HOTELROOMINFO_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=HotelRoomInfoByHotelCode"),
		
		/**
		 * 单体酒店餐饮娱乐
		 */
		DOTNET_HOTELDINNERINFOBYHOTELCODE_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=HotelDinnerInfoByHotelCode"),
		
		/**
		 * 会议与婚礼以及本地
		 * 
		 * &HotelCode=MYLY &TypeCode=7 会议：7；当地：4
		 */
		DOTNET_HOTELMEETINGORLOCAL_SOAP(
				"http://www.minyounhotels.com/sdk/AvailabilityService.ashx?tag=HotelMeetingOrLocalInfoByHotelCode"),
		
		/**
		 * 检查订单时间是否超期
		 * 
		 * &HotelCode=MYLY &TypeCode=7 会议：7；当地：4
		 */
		DOTNET_CHECKTIME_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=CheckOrderTimeout"),
		/**
		 * 我的兑换
		 * 
		 * CardNo
		 */
		DOTNET_GETMYCHANGES_SOAP(
				"http://www.minyounhotels.com/sdk/Gift.ashx?tag=GetMyChanges"),
		/**
		 * 退款
		 * 
		 * &HotelCode=MYLY &TypeCode=7 会议：7；当地：4
		 */
		DOTNET_BACKPAY_SOAP(
				"http://www.minyounhotels.com/sdk/Order.ashx?tag=SubmitOrderInfoRefund");
		
		private String httpUrl;
		
		private HttpAddress(String httpUrl)
		{
			this.httpUrl = httpUrl;
		}
		
		public String getHttpUrl()
		{
			return httpUrl;
		}
		
	}
	
}
