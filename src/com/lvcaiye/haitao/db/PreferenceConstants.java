package com.lvcaiye.haitao.db;

public class PreferenceConstants {
	public static String ACCOUNT 	= "account";	//登陆成功用后记录的用户名和密码
	public static String PASSWORD 	= "password";	
	public static String XACCOUNT 	= "xaccount";	//用来显示用的
	public static String XPASSWORD 	= "xpassword";	
	
	public static String PUSHUID 	= "pushuid";	//百度云推送的uid
	public static String JIHUO = "jihuo";	  //登录状态0未激活1已激活2用户不存在3密码错
	
	
	
	public static String APP_V = "appv";	
	public static String QServer = "qserver";	//当前服务器类型1，内网2，外网
	public static String Server = "server";	
	public static String NServer = "nserver";	//内网服务器
	
	public static String WEIXING = "weixing";	
	
	public static String IMEI = "imei";	  //是否已激活，0、未激活，1、不激活，2、已激活，3、过期

	public static String UID = "uid";	  // 设备id，对应服务器的id	
	public static String CHUGANG = "chugang";	  //1为出港2为进港
	public static String NEWMSG = "newmsg";	  //新消息数量
	
	
	public static String REALNAME = "realname";	  // 设备id，对应服务器的id
	public static String MOBILE = "mobile";	  // 设备id，对应服务器的id
	public static String BOATNAME = "boatname";	  // 设备id，对应服务器的id
	public static String IDCARD = "idcard";	  // 设备id，对应服务器的id
	public static String ZHIWU = "zhiwu";	  // 设备id，对应服务器的id
	
	public static String ZHENGSHUNAME1 	= "zhengshuname1";	  // 证书名称1
	public static String ZHENGSHUHAO1 	= "zhengshuhao1";	  // 证书号码1
	public static String ZHENGSHUNAME2 	= "zhengshuname2";	  // 证书名称2
	public static String ZHENGSHUHAO2 	= "zhengshuhao2";	  // 证书号码2
	public static String ZHENGSHUNAME3 	= "zhengshuname3";	  // 证书名称3
	public static String ZHENGSHUHAO3 	= "zhengshuhao3";	  // 证书号码3
	public static String ZHENGSHUNAME4 	= "zhengshuname4";	  // 证书名称4
	public static String ZHENGSHUHAO4 	= "zhengshuhao4";	  // 证书号码4
	public static String ZHENGSHUNAME5 	= "zhengshuname5";	  // 证书名称5
	public static String ZHENGSHUHAO5 	= "zhengshuhao5";	  // 证书号码5
	public static String ZHENGSHUNAME6 	= "zhengshuname6";	  // 证书名称6
	public static String ZHENGSHUHAO6 	= "zhengshuhao6";	  // 证书号码6
	
	
	public static String SZHENGSHUNAME1 	= "szhengshuname1";	  // 证书名称1
	public static String SZHENGSHUHAO1 	= "szhengshuhao1";	  // 证书号码1
	public static String SZHENGSHUNAME2 	= "szhengshuname2";	  // 证书名称2
	public static String SZHENGSHUHAO2 	= "szhengshuhao2";	  // 证书号码2
	public static String SZHENGSHUNAME3 	= "szhengshuname3";	  // 证书名称3
	public static String SZHENGSHUHAO3 	= "szhengshuhao3";	  // 证书号码3
	public static String SZHENGSHUNAME4 	= "szhengshuname4";	  // 证书名称4
	public static String SZHENGSHUHAO4 	= "szhengshuhao4";	  // 证书号码4
	public static String SZHENGSHUNAME5 	= "szhengshuname5";	  // 证书名称5
	public static String SZHENGSHUHAO5 	= "szhengshuhao5";	  // 证书号码5
	public static String SZHENGSHUNAME6 	= "szhengshuname6";	  // 证书名称6
	public static String SZHENGSHUHAO6 	= "szhengshuhao6";	  // 证书号码6
	

	public static String APP_VERSION= "app_version";	 
	public static String APP_SERVERDATE = "20141020";	
	
	
	/*-------离线发送部分-----------
	public static String LX_LOGIN_URL 	= "lx_login_url";	//个人信息激活
	public static String LX_LOGIN_PRA	= "lx_login_pra";	 
	
	public static String LX_CG_URL 		= "lx_cg_url";	//出港	
	public static String LX_CG_PRA		= "lx_cg_pra";	 
	public static String LX_CG_STAT		= "lx_cg_stat";	 
	
	public static String LX_GK_URL 		= "lx_gk_url";	//工况正常	
	public static String LX_GK_PRA		= "lx_gk_pra";	 
	
	
	public static String LX_AQ_URL 		= "lx_aq_url";	//存在安全隐患	
	public static String LX_AQ_PRA		= "lx_aq_pra";	 
	public static String LX_AQ_RID 		= "lx_aq_rid";	
	public static String LX_AQ_PIC1		= "lx_aq_pic1";
	public static String LX_AQ_PIC2		= "lx_aq_pic2";
	public static String LX_AQ_PIC3		= "lx_aq_pic3";
	public static String LX_AQ_PIC4		= "lx_aq_pic4";
	public static String LX_AQ_PIC5		= "lx_aq_pic5";
	
	public static String LX_NS_URL 		= "lx_ns_url";	//年审
	public static String LX_NS_PRA		= "lx_ns_pra";
	public static String LX_NS_RID 		= "lx_ns_rid";	
	public static String LX_NS_PIC1		= "lx_ns_pic1";
	public static String LX_NS_PIC2		= "lx_ns_pic2";
	public static String LX_NS_PIC3		= "lx_ns_pic3";
	public static String LX_NS_PIC4		= "lx_ns_pic4";
	public static String LX_NS_PIC5		= "lx_ns_pic5";
	public static String LX_NS_PIC6		= "lx_ns_pic6";
	public static String LX_NS_PIC7		= "lx_ns_pic7";
	public static String LX_NS_PIC8		= "lx_ns_pic8";
	public static String LX_NS_PIC9		= "lx_ns_pic9";
	public static String LX_NS_PIC10	= "lx_ns_pic10";
	
	public static String LX_WF_URL 		= "lx_wf_url";	//违法举报
	public static String LX_WF_PRA		= "lx_wf_pra";
	public static String LX_WF_RID 		= "lx_wf_rid";	
	public static String LX_WF_PIC1		= "lx_wf_pic1";
	public static String LX_WF_PIC2		= "lx_wf_pic2";
	public static String LX_WF_PIC3		= "lx_wf_pic3";
	
	
	public static String LX_ZX_URL 		= "lx_zx_url";	//政策咨询
	public static String LX_ZX_PRA		= "lx_zx_pra";
	

*/

	
/*	public static String TEMP_AQ_URL 		= "temp_aq_url";	//存在安全隐患	
	public static String TEMP_AQ_PRA		= "temp_aq_pra";	 
	public static String TEMP_AQ_RID 		= "temp_aq_rid";	
	public static String TEMP_AQ_PIC1		= "temp_aq_pic1";
	public static String TEMP_AQ_PIC2		= "temp_aq_pic2";
	public static String TEMP_AQ_PIC3		= "temp_aq_pic3";
	public static String TEMP_AQ_PIC4		= "temp_aq_pic4";
	public static String TEMP_AQ_PIC5		= "temp_aq_pic5";*/
	public static String TEMP_AQ_CONTENT		= "temp_aq_content";
	public static String TEMP_ZC_CONTENT		= "temp_zc_content"; //政策咨询
	
	public static String TEMP_NS_DIZHI			= "temp_ns_dizhi";	
	public static String TEMP_NS_YOUBIAN		= "temp_ns_youbian";
	public static String TEMP_NS_CHUANJIGANG	= "temp_ns_chuanjigang";
	public static String TEMP_NS_CAIZHI			= "temp_ns_caizhi";
	public static String TEMP_NS_CHUANCHANG		= "temp_ns_chuangchang";
	public static String TEMP_NS_DUNWEI			= "temp_ns_dunwei";	
	public static String TEMP_NS_GONGLV			= "temp_ns_gonglv";
	public static String TEMP_NS_ZHIZAOCHANG	= "temp_ns_zhizaochang";
	public static String TEMP_NS_SUOYOUREN		= "temp_ns_suoyouren";	
	public static String TEMP_NS_KAIGONGSHIJIAN	= "temp_ns_kaigongshijian";
	public static String TEMP_NS_DIDIAN			= "temp_ns_didian";

	public static String TEMP_GR_NAME			= "temp_gr_name";
	public static String TEMP_GR_CHUANMING		= "temp_gr_chuanming";
	public static String TEMP_GR_ZHIWU			= "temp_gr_zhiwu";	
	public static String TEMP_GR_MOBILE			= "temp_gr_mobile";
	public static String TEMP_GR_IDCARD			= "temp_gr_idcard";
	
	public static String TEMP_GR_ZHENGSHUNAME1 	= "temp_gr_zhengshuname1";	  // 证书名称1
	public static String TEMP_GR_ZHENGSHUHAO1 	= "temp_gr_zhengshuhao1";	  // 证书号码1
	public static String TEMP_GR_ZHENGSHUNAME2 	= "temp_gr_zhengshuname2";	  // 证书名称2
	public static String TEMP_GR_ZHENGSHUHAO2 	= "temp_gr_zhengshuhao2";	  // 证书号码2
	public static String TEMP_GR_ZHENGSHUNAME3 	= "temp_gr_zhengshuname3";	  // 证书名称3
	public static String TEMP_GR_ZHENGSHUHAO3 	= "temp_gr_zhengshuhao3";	  // 证书号码3
	public static String TEMP_GR_ZHENGSHUNAME4 	= "temp_gr_zhengshuname4";	  // 证书名称4
	public static String TEMP_GR_ZHENGSHUHAO4 	= "temp_gr_zhengshuhao4";	  // 证书号码4
	public static String TEMP_GR_ZHENGSHUNAME5 	= "temp_gr_zhengshuname5";	  // 证书名称5
	public static String TEMP_GR_ZHENGSHUHAO5 	= "temp_gr_zhengshuhao5";	  // 证书号码5
	public static String TEMP_GR_ZHENGSHUNAME6 	= "temp_gr_zhengshuname6";	  // 证书名称6
	public static String TEMP_GR_ZHENGSHUHAO6 	= "temp_gr_zhengshuhao6";	  // 证书号码6
	
}
