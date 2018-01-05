package com.nettactic.commerces.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nettactic.commerces.R;
import com.nettactic.commerces.constant.Constant;
import com.nettactic.commerces.constant.Constant.RequestMethod;
import com.nettactic.commerces.control.LoadingAsync;
import com.nettactic.commerces.control.MyConfig;
import com.nettactic.commerces.model.AreaChoiceStrings;
import com.nettactic.commerces.model.GiftModel;
import com.nettactic.commerces.model.HotelModel;
import com.nettactic.commerces.model.UserInfoModel;
import com.nettactic.commerces.service.ReqResultImpl;
import com.nettactic.commerces.service.exception.EcommerceException;
import com.nettactic.commerces.util.ActivityUtil;
import com.nettactic.commerces.util.LogUtil;
import com.nettactic.commerces.widget.DialogTool;

/**
 * 兑换礼品时填写收获信息
 * 
 * @author ruan
 * 
 */
public class FillReceiveInfo extends BaseActivity implements ReqResultImpl
{
	
	private int[] city =
	{ R.array.beijin_province_item, R.array.tianjin_province_item,
			R.array.heibei_province_item, R.array.shanxi1_province_item,
			R.array.neimenggu_province_item, R.array.liaoning_province_item,
			R.array.jilin_province_item, R.array.heilongjiang_province_item,
			R.array.shanghai_province_item, R.array.jiangsu_province_item,
			R.array.zhejiang_province_item, R.array.anhui_province_item,
			R.array.fujian_province_item, R.array.jiangxi_province_item,
			R.array.shandong_province_item, R.array.henan_province_item,
			R.array.hubei_province_item, R.array.hunan_province_item,
			R.array.guangdong_province_item, R.array.guangxi_province_item,
			R.array.hainan_province_item, R.array.chongqing_province_item,
			R.array.sichuan_province_item, R.array.guizhou_province_item,
			R.array.yunnan_province_item, R.array.xizang_province_item,
			R.array.shanxi2_province_item, R.array.gansu_province_item,
			R.array.qinghai_province_item, R.array.linxia_province_item,
			R.array.xinjiang_province_item, R.array.hongkong_province_item,
			R.array.aomen_province_item, R.array.taiwan_province_item };
	
	private int[] countyOfBeiJing =
	{ R.array.beijin_city_item };
	
	private int[] countyOfTianJing =
	{ R.array.tianjin_city_item };
	
	private int[] countyOfHeBei =
	{ R.array.shijiazhuang_city_item, R.array.tangshan_city_item,
			R.array.qinghuangdao_city_item, R.array.handan_city_item,
			R.array.xingtai_city_item, R.array.baoding_city_item,
			R.array.zhangjiakou_city_item, R.array.chengde_city_item,
			R.array.cangzhou_city_item, R.array.langfang_city_item,
			R.array.hengshui_city_item };
	
	private int[] countyOfShanXi1 =
	{ R.array.taiyuan_city_item, R.array.datong_city_item,
			R.array.yangquan_city_item, R.array.changzhi_city_item,
			R.array.jincheng_city_item, R.array.shuozhou_city_item,
			R.array.jinzhong_city_item, R.array.yuncheng_city_item,
			R.array.xinzhou_city_item, R.array.linfen_city_item,
			R.array.lvliang_city_item };
	
	private int[] countyOfNeiMengGu =
	{ R.array.huhehaote_city_item, R.array.baotou_city_item,
			R.array.wuhai_city_item, R.array.chifeng_city_item,
			R.array.tongliao_city_item, R.array.eerduosi_city_item,
			R.array.hulunbeier_city_item, R.array.bayannaoer_city_item,
			R.array.wulanchabu_city_item, R.array.xinganmeng_city_item,
			R.array.xilinguolemeng_city_item, R.array.alashanmeng_city_item };
	
	private int[] countyOfLiaoNing =
	{ R.array.shenyang_city_item, R.array.dalian_city_item,
			R.array.anshan_city_item, R.array.wushun_city_item,
			R.array.benxi_city_item, R.array.dandong_city_item,
			R.array.liaoning_jinzhou_city_item, R.array.yingkou_city_item,
			R.array.fuxin_city_item, R.array.liaoyang_city_item,
			R.array.panjin_city_item, R.array.tieling_city_item,
			R.array.zhaoyang_city_item, R.array.huludao_city_item };
	
	private int[] countyOfJiLin =
	{ R.array.changchun_city_item, R.array.jilin_city_item,
			R.array.siping_city_item, R.array.liaoyuan_city_item,
			R.array.tonghua_city_item, R.array.baishan_city_item,
			R.array.songyuan_city_item, R.array.baicheng_city_item,
			R.array.yanbian_city_item };
	
	private int[] countyOfHeiLongJiang =
	{ R.array.haerbing_city_item, R.array.qiqihaer_city_item,
			R.array.jixi_city_item, R.array.hegang_city_item,
			R.array.shuangyashan_city_item, R.array.daqing_city_item,
			R.array.heilongjiang_yichun_city_item, R.array.jiamusi_city_item,
			R.array.qitaihe_city_item, R.array.mudanjiang_city_item,
			R.array.heihe_city_item, R.array.suihua_city_item,
			R.array.daxinganling_city_item };
	
	private int[] countyOfShangHai =
	{ R.array.shanghai_city_item };
	
	private int[] countyOfJiangSu =
	{ R.array.nanjing_city_item, R.array.wuxi_city_item,
			R.array.xuzhou_city_item, R.array.changzhou_city_item,
			R.array.nanjing_suzhou_city_item, R.array.nantong_city_item,
			R.array.lianyungang_city_item, R.array.huaian_city_item,
			R.array.yancheng_city_item, R.array.yangzhou_city_item,
			R.array.zhenjiang_city_item, R.array.jiangsu_taizhou_city_item,
			R.array.suqian_city_item };
	
	private int[] countyOfZheJiang =
	{ R.array.hangzhou_city_item, R.array.ningbo_city_item,
			R.array.wenzhou_city_item, R.array.jiaxing_city_item,
			R.array.huzhou_city_item, R.array.shaoxing_city_item,
			R.array.jinhua_city_item, R.array.quzhou_city_item,
			R.array.zhoushan_city_item, R.array.zejiang_huzhou_city_item,
			R.array.lishui_city_item };
	
	private int[] countyOfAnHui =
	{ R.array.hefei_city_item, R.array.wuhu_city_item,
			R.array.bengbu_city_item, R.array.huainan_city_item,
			R.array.maanshan_city_item, R.array.huaibei_city_item,
			R.array.tongling_city_item, R.array.anqing_city_item,
			R.array.huangshan_city_item, R.array.chuzhou_city_item,
			R.array.fuyang_city_item, R.array.anhui_suzhou_city_item,
			R.array.chaohu_city_item, R.array.luan_city_item,
			R.array.haozhou_city_item, R.array.chizhou_city_item,
			R.array.xuancheng_city_item };
	
	private int[] countyOfFuJian =
	{ R.array.huzhou_city_item, R.array.xiamen_city_item,
			R.array.putian_city_item, R.array.sanming_city_item,
			R.array.quanzhou_city_item, R.array.zhangzhou_city_item,
			R.array.nanp_city_item, R.array.longyan_city_item,
			R.array.ningde_city_item };
	
	private int[] countyOfJiangXi =
	{ R.array.nanchang_city_item, R.array.jingdezhen_city_item,
			R.array.pingxiang_city_item, R.array.jiujiang_city_item,
			R.array.xinyu_city_item, R.array.yingtan_city_item,
			R.array.ganzhou_city_item, R.array.jian_city_item,
			R.array.jiangxi_yichun_city_item, R.array.jiangxi_wuzhou_city_item,
			R.array.shangrao_city_item };
	
	private int[] countyOfShanDong =
	{ R.array.jinan_city_item, R.array.qingdao_city_item,
			R.array.zaobo_city_item, R.array.zaozhuang_city_item,
			R.array.dongying_city_item, R.array.yantai_city_item,
			R.array.weifang_city_item, R.array.jining_city_item,
			R.array.taian_city_item, R.array.weihai_city_item,
			R.array.rizhao_city_item, R.array.laiwu_city_item,
			R.array.linxi_city_item, R.array.dezhou_city_item,
			R.array.liaocheng_city_item, R.array.shandong_bingzhou_city_item,
			R.array.heze_city_item };
	
	private int[] countyOfHeNan =
	{ R.array.zhenshou_city_item, R.array.kaifang_city_item,
			R.array.luoyang_city_item, R.array.kaipingshan_city_item,
			R.array.anyang_city_item, R.array.hebi_city_item,
			R.array.xinxiang_city_item, R.array.jiaozuo_city_item,
			R.array.buyang_city_item, R.array.xuchang_city_item,
			R.array.leihe_city_item, R.array.sanmenxia_city_item,
			R.array.nanyang_city_item, R.array.shangqiu_city_item,
			R.array.xinyang_city_item, R.array.zhoukou_city_item,
			R.array.zhumadian_city_item };
	
	private int[] countyOfHuBei =
	{ R.array.wuhan_city_item, R.array.huangshi_city_item,
			R.array.shiyan_city_item, R.array.yichang_city_item,
			R.array.xiangpan_city_item, R.array.erzhou_city_item,
			R.array.jinmen_city_item, R.array.xiaogan_city_item,
			R.array.hubei_jinzhou_city_item, R.array.huanggang_city_item,
			R.array.xianning_city_item, R.array.suizhou_city_item,
			R.array.enshi_city_item, R.array.shenglongjia_city_item };
	
	private int[] countyOfHuNan =
	{ R.array.changsha_city_item, R.array.zhuzhou_city_item,
			R.array.xiangtan_city_item, R.array.hengyang_city_item,
			R.array.shaoyang_city_item, R.array.yueyang_city_item,
			R.array.changde_city_item, R.array.zhangjiajie_city_item,
			R.array.yiyang_city_item, R.array.hunan_bingzhou_city_item,
			R.array.yongzhou_city_item, R.array.huaihua_city_item,
			R.array.loudi_city_item, R.array.xiangxi_city_item };
	
	private int[] countyOfGuangDong =
	{ R.array.guangzhou_city_item, R.array.shaoguan_city_item,
			R.array.shenzhen_city_item, R.array.zhuhai_city_item,
			R.array.shantou_city_item, R.array.foshan_city_item,
			R.array.jiangmen_city_item, R.array.zhangjiang_city_item,
			R.array.maoming_city_item, R.array.zhaoqing_city_item,
			R.array.huizhou_city_item, R.array.meizhou_city_item,
			R.array.shanwei_city_item, R.array.heyuan_city_item,
			R.array.yangjiang_city_item, R.array.qingyuan_city_item,
			R.array.dongguan_city_item, R.array.zhongshan_city_item,
			R.array.chaozhou_city_item, R.array.jiyang_city_item,
			R.array.yunfu_city_item };
	
	private int[] countyOfGuangXi =
	{ R.array.nanning_city_item, R.array.liuzhou_city_item,
			R.array.guilin_city_item, R.array.guangxi_wuzhou_city_item,
			R.array.beihai_city_item, R.array.fangchenggang_city_item,
			R.array.qinzhou_city_item, R.array.guigang_city_item,
			R.array.yuelin_city_item, R.array.baise_city_item,
			R.array.hezhou_city_item, R.array.hechi_city_item,
			R.array.laibing_city_item, R.array.chuangzuo_city_item };
	
	private int[] countyOfHaiNan =
	{ R.array.haikou_city_item, R.array.sanya_city_item };
	
	private int[] countyOfChongQing =
	{ R.array.chongqing_city_item };
	
	private int[] countyOfSiChuan =
	{ R.array.chengdu_city_item, R.array.zigong_city_item,
			R.array.panzhihua_city_item, R.array.luzhou_city_item,
			R.array.deyang_city_item, R.array.mianyang_city_item,
			R.array.guangyuan_city_item, R.array.suining_city_item,
			R.array.neijiang_city_item, R.array.leshan_city_item,
			R.array.nanchong_city_item, R.array.meishan_city_item,
			R.array.yibing_city_item, R.array.guangan_city_item,
			R.array.dazhou_city_item, R.array.yaan_city_item,
			R.array.bazhong_city_item, R.array.ziyang_city_item,
			R.array.abei_city_item, R.array.ganmu_city_item,
			R.array.liangshan_city_item };
	
	private int[] countyOfGuiZhou =
	{ R.array.guiyang_city_item, R.array.lupanshui_city_item,
			R.array.zhunyi_city_item, R.array.anshun_city_item,
			R.array.tongren_city_item, R.array.qingxinan_city_item,
			R.array.biji_city_item, R.array.qingdongnan_city_item,
			R.array.qingnan_city_item };
	
	private int[] countyOfYunNan =
	{ R.array.kunming_city_item, R.array.qujing_city_item,
			R.array.yuexi_city_item, R.array.baoshan_city_item,
			R.array.zhaotong_city_item, R.array.lijiang_city_item,
			R.array.simao_city_item, R.array.lingcang_city_item,
			R.array.chuxiong_city_item, R.array.honghe_city_item,
			R.array.wenshan_city_item, R.array.xishuangbanna_city_item,
			R.array.dali_city_item, R.array.dehuang_city_item,
			R.array.nujiang_city_item, R.array.diqing_city_item };
	
	private int[] countyOfXiZang =
	{ R.array.lasa_city_item, R.array.changdu_city_item,
			R.array.shannan_city_item, R.array.rgeze_city_item,
			R.array.naqu_city_item, R.array.ali_city_item,
			R.array.linzhi_city_item };
	
	private int[] countyOfShanXi2 =
	{ R.array.xian_city_item, R.array.tongchuan_city_item,
			R.array.baoji_city_item, R.array.xianyang_city_item,
			R.array.weinan_city_item, R.array.yanan_city_item,
			R.array.hanzhong_city_item, R.array.yulin_city_item,
			R.array.ankang_city_item, R.array.shangluo_city_item };
	
	private int[] countyOfGanSu =
	{ R.array.lanzhou_city_item, R.array.jiayuguan_city_item,
			R.array.jinchang_city_item, R.array.baiyin_city_item,
			R.array.tianshui_city_item, R.array.wuwei_city_item,
			R.array.zhangyue_city_item, R.array.pingliang_city_item,
			R.array.jiuquan_city_item, R.array.qingyang_city_item,
			R.array.dingxi_city_item, R.array.longnan_city_item,
			R.array.linxia_city_item, R.array.gannan_city_item };
	
	private int[] countyOfQingHai =
	{ R.array.xining_city_item, R.array.haidong_city_item,
			R.array.haibai_city_item, R.array.huangnan_city_item,
			R.array.hainan_city_item, R.array.guluo_city_item,
			R.array.yushu_city_item, R.array.haixi_city_item };
	
	private int[] countyOfNingXia =
	{ R.array.yinchuan_city_item, R.array.shizuishan_city_item,
			R.array.wuzhong_city_item, R.array.guyuan_city_item,
			R.array.zhongwei_city_item };
	
	private int[] countyOfXinJiang =
	{ R.array.wulumuqi_city_item, R.array.kelamayi_city_item,
			R.array.tulyfan_city_item, R.array.hami_city_item,
			R.array.changji_city_item, R.array.boertala_city_item,
			R.array.bayinguolen_city_item, R.array.akesu_city_item,
			R.array.kemuleisu_city_item, R.array.geshen_city_item,
			R.array.hetian_city_item, R.array.yili_city_item,
			R.array.tacheng_city_item, R.array.aleitai_city_item,
			R.array.shihezi_city_item, R.array.alaer_city_item,
			R.array.tumushihe_city_item, R.array.wujiaqu_city_item };
	
	private int[] countyOfHongKong =
	{};
	
	private int[] countyOfAoMen =
	{};
	
	private int[] countyOfTaiWan =
	{};
	
	public static final String GIFT = "gift";
	
	private ImageView iv_GiftImg;
	
	private TextView tv_GiftName, tv_NeedCode, tv_HasCode, tv_LeaveCode,
			tv_Way;
	
	private EditText et_Num, et_GetName, et_GetMobile, et_GetAddr,
			et_GetRemark;
	
	private Button btn_Check;
	
	private GiftModel giftModel;
	
	private Spinner province_spinner;
	
	private Spinner city_spinner;
	
	private Spinner county_spinner;
	
	private ArrayAdapter<CharSequence> province_adapter;
	
	private ArrayAdapter<CharSequence> city_adapter;
	
	private ArrayAdapter<CharSequence> county_adapter;
	
	private Integer provinceId, cityId;
	
	private String strProvince, strCity, strCounty;
	
	private AreaChoiceStrings areas;
	
	private List<EditText> list;
	
	private int GETWAY = 0;
	
	private LinearLayout ll_Addr, ll_Hotel;
	
	private TextView tv_Citys, tv_Hotels;
	
	private String[] cityItems;
	
	/**
	 * cityCode|List<HotelModel>>
	 */
	private Map<String, List<String>> listHotelMap;
	
	private Map<String, String[]> stringHotelMap;
	
	private Map<String, String> cityMap = null;
	
	private Map<String, String> cityCodeMap = null;
	
	private String[] hotels;
	
	private int requestSuccessNum = 0;
	
	/**
	 * key: hotelName value: HotelCode
	 */
	private Map<String, String> hotelCodeMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fillreceiveinfo);
		setTitle("兑换信息");
		setleftIcon(R.drawable.t_backarr);
		
		areas = new AreaChoiceStrings();
		initView();
		loadSpinner();
		initData();
	}
	
	private void initView()
	{
		
		iv_GiftImg = (ImageView) findViewById(R.id.gs_img);
		
		tv_GiftName = (TextView) findViewById(R.id.gs_Name);
		tv_NeedCode = (TextView) findViewById(R.id.gs_Needcode);
		tv_HasCode = (TextView) findViewById(R.id.gs_Hascode);
		tv_LeaveCode = (TextView) findViewById(R.id.gs_Leavecode);
		tv_Way = (TextView) findViewById(R.id.gs_GetWay);
		tv_Citys = (TextView) findViewById(R.id.gs_citys);
		tv_Hotels = (TextView) findViewById(R.id.gs_hotels);
		
		et_Num = (EditText) findViewById(R.id.gs_Num);
		et_GetName = (EditText) findViewById(R.id.gs_GetName);
		et_GetMobile = (EditText) findViewById(R.id.gs_GetMobile);
		et_GetAddr = (EditText) findViewById(R.id.edit_address);
		et_GetRemark = (EditText) findViewById(R.id.gs_Remark);
		
		ll_Addr = (LinearLayout) findViewById(R.id.gs_LL_addr);
		ll_Hotel = (LinearLayout) findViewById(R.id.gs_ll_Hotel);
		
		btn_Check = (Button) findViewById(R.id.gs_Check);
		
		list = new ArrayList<EditText>()
		{
			{
				add(et_Num);
				add(et_GetName);
				add(et_GetMobile);
				add(et_GetAddr);
			}
		};
		
		tv_Citys.setOnClickListener(this);
		tv_Hotels.setOnClickListener(this);
		tv_Way.setOnClickListener(this);
		btn_Check.setOnClickListener(this);
	}
	
	private void initData()
	{
		new LoadingAsync(FillReceiveInfo.this,
				RequestMethod.METHOD_GETDESTINATIONCITY).execute();
		Intent intent = getIntent();
		if (intent.hasExtra(GIFT))
		{
			giftModel = (GiftModel) intent
					.getSerializableExtra(FillReceiveInfo.GIFT);
			loadWebImg(iv_GiftImg, giftModel.getGift_Image());
			tv_GiftName.setText(giftModel.getGift_Name());
			tv_NeedCode.setText(giftModel.getGift_Point());
			UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
			tv_HasCode.setText(userInfoModel.getTotlePoints());
			tv_LeaveCode.setText(giftModel.getDiffCode());
		} else
		{
			
		}
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.gs_GetWay:
		{
			final String[] way = new String[]
			{ "酒店自提", "寄送到达" };
			DialogTool.createListDialog(this, R.drawable.circle, "收货方式", way,
					new DialogInterface.OnClickListener()
					{
						
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							GETWAY = which;
							tv_Way.setText(way[which]);
							switch (which)
							{
							case 0:
							{
								ll_Hotel.setVisibility(View.VISIBLE);
								ll_Addr.setVisibility(View.GONE);
								break;
							}
							case 1:
							{
								ll_Hotel.setVisibility(View.GONE);
								ll_Addr.setVisibility(View.VISIBLE);
								break;
							}
							default:
								break;
							}
						}
					}).show();
			break;
		}
		case R.id.gs_Check:
		{
			if (0 == GETWAY)
			{
				submitInfo();
			} else
			{
				
				if (checkInfo())
				{
					submitInfo();
				} else
				{
					
				}
			}
			break;
		}
		case R.id.gs_citys:
		{
			DialogTool.createListDialog(this, R.drawable.circle, "请选择城市",
					cityItems, new DialogInterface.OnClickListener()
					{
						
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							String selectedCity = cityItems[which];
							hotels = stringHotelMap.get(selectedCity);
							if (null != hotels && hotels.length > 0)
							{
								
								tv_Hotels.setText(hotels[0]);
							}
							tv_Citys.setText(selectedCity);
						}
					}).show();
			
			break;
		}
		case R.id.gs_hotels:
		{
			DialogTool.createListDialog(this, R.drawable.circle, "请选择酒店",
					hotels, new DialogInterface.OnClickListener()
					{
						
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							String hotelName = hotels[which];
							tv_Hotels.setText(hotelName);
						}
					}).show();
			
			break;
		}
		default:
			break;
		}
		super.onClick(v);
	}
	
	private boolean checkInfo()
	{
		if (null == tv_Way.getText() || "" == tv_Way.getText().toString())
		{
			ActivityUtil.Vibrate(this, tv_Way);
			return false;
		} else
		{
			
		}
		
		for (int i = 0; i < list.size(); i++)
		{
			if (null == list.get(i).getText()
					|| "" == list.get(i).getText().toString()
					|| list.get(i).getText().length() < 1)
			{
				ActivityUtil.Vibrate(this, list.get(i));
				return false;
			} else
			{
				
			}
		}
		
		UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
		String userCode = userInfoModel.getTotlePoints();
		String needCode = giftModel.getGift_Point();
		String change_Num = null != et_Num.getText() ? et_Num.getText()
				.toString() : "0";
		
		BigDecimal uc = new BigDecimal(userCode);// 用户拥有的积分
		BigDecimal nc = new BigDecimal(needCode);// 礼品所需积分
		BigDecimal cn = new BigDecimal(change_Num);// 兑换数量
		BigDecimal tc = cn.multiply(nc);// 兑换所需总积分
		
		if (tc.compareTo(BigDecimal.ZERO) == 1)
		{
			if (uc.compareTo(tc) != -1)
			{
				
			} else
			{
				alert("您的积分不足");
				return false;
			}
		} else
		{
			alert("请填写礼品兑换数量");
			return false;
		}
		
		return true;
		
	}
	
	private void extractHotelinfo(JSONArray array)
	{
		try
		{
			listHotelMap = new HashMap<String, List<String>>();
			stringHotelMap = new HashMap<String, String[]>();
			hotelCodeMap = new HashMap<String, String>();
			
			List<String> hotelList;
			
			for (int i = 0; i < cityMap.size(); i++)
			{
				hotelList = new ArrayList<String>();
				listHotelMap.put(cityMap.get(cityItems[i]), hotelList);
			}
			
			HotelModel hotelModel;
			for (int i = 0; i < array.length(); i++)
			{
				JSONObject hotelObject = array.optJSONObject(i);
				hotelModel = new HotelModel(hotelObject, null);
				
				String cityCode = hotelModel.getCityCode();
				hotelList = listHotelMap.get(cityCode);
				hotelList.add(hotelModel.getHotelName());
				hotelCodeMap.put(hotelModel.getHotelName(),
						hotelModel.getHotelCode());
			}
			
			for (int i = 0; i < cityMap.size(); i++)
			{
				
				List<String> list = listHotelMap.get(cityMap.get(cityItems[i]));
				String[] strs = new String[list.size()];
				list.toArray(strs);
				stringHotelMap.put(cityItems[i], strs);
			}
			
			tv_Citys.setText(cityItems[0]);
			hotels = stringHotelMap.get(cityItems[0]);
			tv_Hotels.setText(hotels[0]);
			
		} catch (JSONException e)
		{
			alert("网络异常,请重试");
		} catch (EcommerceException e)
		{
			alert("网络异常,请重试");
		}
	}
	
	@Override
	public void reqResultSuccess(RequestMethod methodName, JSONObject jsonResult)
	{
		JSONArray result = null;
		switch (methodName)
		{
		case METHOD_GETDESTINATIONCITY:
		{
			result = jsonResult.optJSONArray(Constant.JSON_DATA);
			
			if (MyConfig.ifDebug)
			{
				LogUtil.d("METHOD_GETDESTINATIONCITY", result.toString());
			}
			
			cityMap = new HashMap<String, String>();
			cityCodeMap = new HashMap<String, String>();
			cityItems = new String[result.length()];
			
			for (int i = 0; i < result.length(); i++)
			{
				JSONObject JO = result.optJSONObject(i);
				cityItems[i] = JO.optString("Name");
				cityMap.put(JO.optString("Name"), JO.optString("CityCode"));
				cityCodeMap.put(JO.optString("CityCode"), JO.optString("Name"));
			}
			
			requestSuccessNum++;
			new LoadingAsync(FillReceiveInfo.this,
					RequestMethod.METHOD_GETHOTELS).execute();
			break;
			
		}
		case METHOD_GETHOTELS:
		{
			result = jsonResult.optJSONArray(Constant.JSON_DATA);
			extractHotelinfo(result);
			requestSuccessNum++;
			break;
		}
		case METHOD_GIFTEXCHANGE:
		{
			Intent intent = new Intent(this, GiftOrdeSuccess.class);
			intent.putExtra("GETWAY", GETWAY);
			startActivity(intent);
			break;
		}
		
		default:
			break;
		}
		
	}
	
	@Override
	public void reqResultFail(RequestMethod methodName, int errorCode)
	{
		
		switch (methodName)
		{
		case METHOD_GETDESTINATIONCITY:
			
		case METHOD_GETHOTELS:
		{
			alert("获取数据失败请重试");
			break;
		}
		case METHOD_GIFTEXCHANGE:
		{
			alert("提交数据失败请重试");
			break;
		}
		default:
			break;
		}
	}
	
	private void submitInfo()
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		UserInfoModel userInfoModel = UserInfoModel.getUserInfoModel();
		params.add(new BasicNameValuePair("CardNo", userInfoModel.getCardNo()));
		
		params.add(new BasicNameValuePair("GiftID", String.valueOf(giftModel
				.getGift_ID())));
		
		params.add(new BasicNameValuePair("GiftNum", et_Num.getText()
				.toString()));
		params.add(new BasicNameValuePair("Code", giftModel.getGift_Code()));
		params.add(new BasicNameValuePair("Other", null != et_GetRemark
				.getText() ? et_GetRemark.getText().toString() : ""));
		params.add(new BasicNameValuePair("ExchangeType", String
				.valueOf(GETWAY)));// 收货方式0自提，1送货
		params.add(new BasicNameValuePair("Point", giftModel.getGift_Code()));
		
		// 自提
		if (0 == GETWAY)
		{
			params.add(new BasicNameValuePair("Province", null != tv_Citys
					.getText() ? tv_Citys.getText().toString() : ""));
			params.add(new BasicNameValuePair("City", null != tv_Hotels
					.getText() ? tv_Hotels.getText().toString() : ""));
		}
		// 寄送
		else if (1 == GETWAY)
		{
			
			params.add(new BasicNameValuePair("Province", strProvince));
			params.add(new BasicNameValuePair("City", strCity));
			params.add(new BasicNameValuePair("Area", strCounty));
			params.add(new BasicNameValuePair("Address", null != et_GetAddr
					.getText() ? et_GetAddr.getText().toString() : ""));
			params.add(new BasicNameValuePair("UserName", et_GetName.getText()
					.toString()));
			params.add(new BasicNameValuePair("Mobile", et_GetMobile.getText()
					.toString()));
			
		}
		new LoadingAsync(this, RequestMethod.METHOD_GIFTEXCHANGE, params)
				.execute();
	}
	
	private void loadSpinner()
	{
		// display = (EditText) findViewById(R.id.display_edit);
		province_spinner = (Spinner) findViewById(R.id.province_spinner);
		province_spinner.setPrompt("请选择省份");
		province_adapter = ArrayAdapter.createFromResource(this,
				R.array.province_item, android.R.layout.simple_spinner_item);
		province_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		province_spinner.setAdapter(province_adapter);
		province_spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)
			{
				provinceId = province_spinner.getSelectedItemPosition();
				strProvince = province_spinner.getSelectedItem().toString();
				city_spinner = (Spinner) findViewById(R.id.city_spinner);
				if (true)
				{
					Log.v("test", "province: "
							+ province_spinner.getSelectedItem().toString()
							+ provinceId.toString());
					county_spinner = (Spinner) findViewById(R.id.county_spinner);
					city_spinner = (Spinner) findViewById(R.id.city_spinner);
					city_spinner.setPrompt("请选择城市");
					select(city_spinner, city_adapter, city[provinceId]);
					city_spinner
							.setOnItemSelectedListener(new OnItemSelectedListener()
							{
								
								@Override
								public void onItemSelected(AdapterView<?> arg0,
										View arg1, int arg2, long arg3)
								{
									cityId = city_spinner
											.getSelectedItemPosition();
									strCity = city_spinner.getSelectedItem()
											.toString();
									Log.v("test",
											"city: "
													+ city_spinner
															.getSelectedItem()
															.toString()
													+ cityId.toString());
									if (true)
									{
										county_spinner = (Spinner) findViewById(R.id.county_spinner);
										county_spinner.setPrompt("请选择县区");
										switch (provinceId)
										{
										case 0:
											select(county_spinner,
													county_adapter,
													countyOfBeiJing[cityId]);
											break;
										case 1:
											select(county_spinner,
													county_adapter,
													countyOfTianJing[cityId]);
											break;
										case 2:
											select(county_spinner,
													county_adapter,
													countyOfHeBei[cityId]);
											break;
										case 3:
											select(county_spinner,
													county_adapter,
													countyOfShanXi1[cityId]);
											break;
										case 4:
											select(county_spinner,
													county_adapter,
													countyOfNeiMengGu[cityId]);
											break;
										case 5:
											select(county_spinner,
													county_adapter,
													countyOfLiaoNing[cityId]);
											break;
										case 6:
											select(county_spinner,
													county_adapter,
													countyOfJiLin[cityId]);
											break;
										case 7:
											select(county_spinner,
													county_adapter,
													countyOfHeiLongJiang[cityId]);
											break;
										case 8:
											select(county_spinner,
													county_adapter,
													countyOfShangHai[cityId]);
											break;
										case 9:
											select(county_spinner,
													county_adapter,
													countyOfJiangSu[cityId]);
											break;
										case 10:
											select(county_spinner,
													county_adapter,
													countyOfZheJiang[cityId]);
											break;
										case 11:
											select(county_spinner,
													county_adapter,
													countyOfAnHui[cityId]);
											break;
										case 12:
											select(county_spinner,
													county_adapter,
													countyOfFuJian[cityId]);
											break;
										case 13:
											select(county_spinner,
													county_adapter,
													countyOfJiangXi[cityId]);
											break;
										case 14:
											select(county_spinner,
													county_adapter,
													countyOfShanDong[cityId]);
											break;
										case 15:
											select(county_spinner,
													county_adapter,
													countyOfHeNan[cityId]);
											break;
										case 16:
											select(county_spinner,
													county_adapter,
													countyOfHuBei[cityId]);
											break;
										case 17:
											select(county_spinner,
													county_adapter,
													countyOfHuNan[cityId]);
											break;
										case 18:
											select(county_spinner,
													county_adapter,
													countyOfGuangDong[cityId]);
											break;
										case 19:
											select(county_spinner,
													county_adapter,
													countyOfGuangXi[cityId]);
											break;
										case 20:
											select(county_spinner,
													county_adapter,
													countyOfHaiNan[cityId]);
											break;
										case 21:
											select(county_spinner,
													county_adapter,
													countyOfChongQing[cityId]);
											break;
										case 22:
											select(county_spinner,
													county_adapter,
													countyOfSiChuan[cityId]);
											break;
										case 23:
											select(county_spinner,
													county_adapter,
													countyOfGuiZhou[cityId]);
											break;
										case 24:
											select(county_spinner,
													county_adapter,
													countyOfYunNan[cityId]);
											break;
										case 25:
											select(county_spinner,
													county_adapter,
													countyOfXiZang[cityId]);
											break;
										case 26:
											select(county_spinner,
													county_adapter,
													countyOfShanXi2[cityId]);
											break;
										case 27:
											select(county_spinner,
													county_adapter,
													countyOfGanSu[cityId]);
											break;
										case 28:
											select(county_spinner,
													county_adapter,
													countyOfQingHai[cityId]);
											break;
										case 29:
											select(county_spinner,
													county_adapter,
													countyOfNingXia[cityId]);
											break;
										case 30:
											select(county_spinner,
													county_adapter,
													countyOfXinJiang[cityId]);
											break;
										case 31:
											select(county_spinner,
													county_adapter,
													countyOfHongKong[cityId]);
											break;
										case 32:
											select(county_spinner,
													county_adapter,
													countyOfAoMen[cityId]);
											break;
										case 33:
											select(county_spinner,
													county_adapter,
													countyOfTaiWan[cityId]);
											break;
										
										default:
											break;
										}
										
										county_spinner
												.setOnItemSelectedListener(new OnItemSelectedListener()
												{
													
													@Override
													public void onItemSelected(
															AdapterView<?> arg0,
															View arg1,
															int arg2, long arg3)
													{
														strCounty = county_spinner
																.getSelectedItem()
																.toString();
													}
													
													@Override
													public void onNothingSelected(
															AdapterView<?> arg0)
													{
														
													}
													
												});
									}
								}
								
								@Override
								public void onNothingSelected(
										AdapterView<?> arg0)
								{
									// TODO Auto-generated method stub
									
								}
								
							});
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				
			}
		});
		
	}
	
	private void select(Spinner spin, ArrayAdapter<CharSequence> adapter,
			int arry)
	{
		adapter = ArrayAdapter.createFromResource(this, arry,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		// spin.setSelection(0,true);
	}
	
}
