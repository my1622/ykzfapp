package whzl.com.ykzfapp.tools;

import android.graphics.Typeface;

/**
 * 获取到的系统属性</br>
 * 常用的全局变量
 * @author ll
 *
 */
public class Config {

	public static String SEARCHJOB_URL ="http://192.168.16.177:7007/JyetWeb/";

	public static String BASE_URL = "http://192.168.16.177:7007/";
	/** 状态栏高度 */
	public static int STATUS_HEIGHT = 0;
	
	/** 手机屏幕高度 */
	public static int PHONE_HEIGHT = 0;
	
	/** 手机屏幕宽度 */
	public static int PHONE_WIDTH = 0;
	/** sharedPerference的name值 */
	public static String SHAREDPREFERENCE_NAME = "et_preference";

	/** 判断首页的apk升级是否提示----true：需要升级; false：不需要升级*/
	public static boolean IF_NEED_UPDATE = true;

	public static String SERVICE_BROADCAST_APK_UPDATE = "SERVICE_BROADCAST_APK_UPDATE";

	/** 检查apk版本的接口地址*/
	public static String CHECK_APK_VERSION = "apkeditionAction_apksearch.do";

	/** APK下载地址*/
	public static String APK_UPDATE_URL = "http://218.2.15.139:7003/hafile/apk/";
	
	/** 用户第一次使用app时系统向用户展示E通特色介绍页面,在介绍页面的activity中会向<br/>
	 *  sharedPreference存入 0值。用户通过展示页面进入系统主页后会在sharedPreference中<br/>
	 *  存入一个为1的值，该字符串就是用作存取该值使用的key
	 **/
	public static String CHECKED_SYSINTRODUCE = "checkedIntroduce_key";
	
	/** 将定位获取到的【县区】名称存入sharedPreference中,该变量对应其key*/
	public static String SP_XIANQU_NAME = "name";
	/** 将定位获取到的【详细地址】存入sharedPreference中,该变量对应其key*/
	public static String SP_ADDSTR = "addrStr";
	/** 将定位获取到的【城市名称】存入sharedPreference中,该变量对应其key*/
	public static String SP_CITY = "city";
	/** 将定位获取到的【经度】存入sharedPreference中,该变量对应其key*/
	public static String SP_LATITUDE = "latitude";
	/** 将定位获取到的【纬度】存入sharedPreference中,该变量对应其key*/
	public static String SP_LONGITUDE = "longitude";
	/** 将定位获取到的【运营商信息】存入sharedPreference中,该变量对应其key*/
	public static String SP_OPERATORS = "operators";
	/** 个人基础信息存储到SharedPreference中,该变量是构建SharedPreferenceHelper对象时用到的*/
	public static String USER_INFO_PREFERENCE = "personalShare";
	/** 个人基础信息存储到SharedPreference中,该变量是构建SharedPreferenceHelper对象时用到的*/
	public static String ENTERPRISE_INFO_PREFERENCE = "enterpriseShare";
	/** 系统字体*/
	public static Typeface FONTTYPE;
	/** 请求服务器404 */
	public static String LOADDATA_404 = "加载数据失败,请重试！";
	/**http://192.168.16.177:7007/*/
	/** app与后台通信的接口所在的服务器地址*/
	//public static String BASE_URL_139 = "http://192.168.18.222:7001/whzlsi/";
	public static String BASE_URL_139 = "http://121.30.224.130:8002/whzlsi/";
	//public static String BASE_URL_139 = "http://192.168.16.177:7007/whzlsi/";
	//public static String BASE_URL_139 = "http://218.2.6.10:14021/whzlsi/";
	/** app的web功能所在服务器地址*/
	public static String WEB_URL_139 = "http://192.168.18.222:7001/JyetWeb/";
	//public static String WEB_URL_139 = "http://192.168.16.177:7007/JyetWeb/";
	//public static String WEB_URL_139 = "http://218.2.6.10:14021/JyetWeb/";
	/** app的web功能所在服务器地址*/
	//public static String ORG_URL_139 = "http://192.168.18.222:7001/jyetDT/";
	public static String ORG_URL_139 = "http://121.30.224.130:8002/jyetDT/";
	//public static String ORG_URL_139 = "http://192.168.16.177:7007/jyetDT/";
	//public static String ORG_URL_139 = "http://218.2.6.10:14021/jyetDT/";
//	public static String WEB_URL_139 = "http://192.168.18.130:8080/JyetWeb/";

	/** 服务机构接口地址 */
	public static String FWJG_CHAXUN_INFO = "depAction!list.do";

	/** 五险查询时用到的获取个人医保帐号信息接口 */
	public static String WUXIAN_ACCOUNT_INFO = "ybAction_findAccount.do";
	/** 五险查询时用到的获取医保年度缴费金额信息接口 */
	public static String WUXIAN_JFJE_INFO = "ybAction_jfje.do";
	/** 五险查询时用到的获取医保年度缴费金额信息接口 */
	public static String WUXIAN_JFMX_INFO = "ybAction_jfmx.do";
	/** 五险查询时用到的获取刷卡明细信息接口 */
	public static String WUXIAN_SKMX_INFO = "ybAction_skmx.do";

	/** 通知公告列表信息接口 */
	public static String TONGZHI_LIST_INFO = "tzggAction_getTzgg.do";
	/** 通知公告详细信息接口 */
	public static String TONGZHI_DETAIL_INFO = "tzggAction_getDetail.do";

	/** 招聘会列表信息接口 */
	public static String ZPH_LIST_INFO = "cb33Action_zphxx.do";
	/** 招聘会详细信息接口 */
	public static String ZPH_DETAIL_INFO = "cb35Action_jobfairlist.do";
	/** 通知公告详细信息接口 */
//	public static String TONGZHI_DETAIL_INFO = "newsAction_getDetail.do";
	//==========================简历管理=====================================
	/** 简历列表信息接口地址*/
	public static String RESUME_LIST = "cc21Action_list.do";
	/** 删除简历接口*/
	public static String RESUME_DELETE = "cc21Action_delete.do";
	/** 教育背景接口*/
	public static String RESUME_EDUCATION_INFO = "cc0fAction_list.do";
	/** 培训状况列表、详情接口 */
	public static String RESUME_PEIXUN_INFO = "cc0iAction_list.do";
	/** 培训状况新增接口 */
	public static String RESUME_PEIXUN_ADD = "cc0iAction_add.do";
	/** 培训状况更新接口 */
	public static String RESUME_PEIXUN_UPDATE = "cc0iAction_update.do";
	/** 培训状况删除接口 */
	public static String RESUME_PEIXUN_DELETE = "cc0iAction_delete.do";
	/** 工作经历列表、详情接口*/
	public static String RESUME_JINGLI_INFO = "cc0bAction_list.do";
	/** 工作经历新增接口*/
	public static String RESUME_JINGLI_ADD = "cc0bAction_add.do";
	/** 工作经历更新接口*/
	public static String RESUME_JINGLI_UPDATE = "cc0bAction_update.do";
	/** 工作经历删除接口*/
	public static String RESUME_JINGLI_DELETE = "cc0bAction_delete.do";

	/** 持证情况接口*/
	public static String RESUME_CHIZHENG_INFO = "cc0eAction_list.do";
	/** 基本信息接口*/
	public static String RESUME_BASEINFO = "cc20Action_getxx.do";
	//==========================简历管理END=====================================
	/** data/data/com.whzl.jyet/databases */
	public static String DATABASE_PATH="data/data/com.whzl.dtjy.activity/databases";
	/** dbjyet.db */
	public static String DATABASE_NAME="dbjyet.db";
	/** 数据库存储位置： data/data/com.whzl.jyet/databases/dbjyet.db */
	public static String DATABASE_FILENAME = DATABASE_PATH + "/" + DATABASE_NAME;

	/**====================SharedPreference用到的常量 value=========================*/

	/** 接口访问登录名*/
	public static String INTERFACE_LOGINNAME="wd";
	/** 接口访问登录密码*/
	public static String INTERFACE_LOGINPWD="4008809192";
	/**app登录用户名称key--存储、检索用户是否登录时从SharedPreference存、取值的key*/
	public static String APP_LOGINNAME_KEY = "cb20.awb002";
	/**app登录用户密码key--存储、检索用户是否登录时从SharedPreference存、取值的key*/
	public static String APP_LOGINPWD_KEY = "cb20.awb004";
	/***
	 * app用户登录状态值：用作表示登录者是个人还是企业
	 * APP_LOGIN_STATE = 1; 表示个人登录
	 * APP_LOGIN_STATE = 2; 表示企业登录
	 * APP_LOGIN_STATE = 0; 表示没有人登录到APP或注销 */
	public static int APP_LOGIN_STATE = Config.APP_LOGOUT;
	/** app用户登录状态 -- 个人登录状态*/
	public static int APP_PERSON_LOGIN = 1;
	/** app用户登录状态 -- 企业登录状态*/
	public static int APP_COMPANY_LOGIN = 2;
	/** app用户登录状态 -- 注销状态*/
	public static int APP_LOGOUT = 0;
	/** 环信帐号注册默认密码--企业、个人共用*/
	public static String HUANXIN_PWD = "123";
	/** 腾讯开发平台---应用的AppId*/
	public static final String APP_ID = "1104797330";
	/** 腾讯开发平台---应用的APP KEY*/
	public static final String APP_KEY = "iLhFK17BZZFSNp4J";

	/**
	 * 某个功能会有多个打开方式,如求职登记功能的进入途径有 1.从个人注册页面，2.从主页面进入
	 * */
	public static final String FUNCTION_ENTRANCE = "functionEntrance";

	/** 后台统计所有终端数据时需要用到该字段,key是cc20.bcb209
	 * 值等于5,表示当前向后台请求数据的终端是手机
	 * */
	public static final String MOBILE_CODE = "5";

	//======================错误消息处理时用到的全局变量====================================
	/** 没有对应的消息错误码时用此变量*/
	public static final String OPERATE_FAILED = "操作失败,请重试";


}
