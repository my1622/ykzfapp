package whzl.com.ykzfapp.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ant.liao.GifView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import whzl.com.ykzfapp.R;

/**
 * 系统工具类
 * @author ll
 * @date 2015/04/01
 */
@SuppressLint({ "SimpleDateFormat", "ShowToast" })
public class Util {

	private static String classPath = "com.whzl.dtjy.common.Util";
	/**
	 * 初始化系统属性（手机宽度、高度、状态栏高度、字体）
	 * @param act 调用组件activity对象
	 */
	public static void initSystemProperty(Activity act){
		DisplayMetrics dm = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 获取手机高度
		Config.PHONE_HEIGHT = dm.heightPixels;
		// 获取手机宽度
		Config.PHONE_WIDTH = dm.widthPixels;
		// 获取手机状态栏高度
		Rect frame = new Rect();
		act.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		Config.STATUS_HEIGHT = frame.top;
		Log.i("手机状态栏高度、手机高度、手机宽度：", Config.STATUS_HEIGHT+" -- "+
				Config.PHONE_HEIGHT+" -- "+
				Config.PHONE_WIDTH);
		// 初始化字体
		AssetManager asset = act.getAssets();
		Config.FONTTYPE = Typeface.createFromAsset(asset, "youyuan.ttf");
	}
	
	/**
	 * 日志打印方法
	 * @param content
	 */
	public static void logPrint(String content){
		SimpleDateFormat format = null;
		Date date = null;
		try {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = new Date(System.currentTimeMillis());
			if(content!=null ){
				Log.i(format.format(date)+"打印日志", content);
			}
		} catch (Exception e) {
			Util.logPrint("日志打印方法异常"+e.getMessage());
			e.printStackTrace();
		} finally{
			format = null;
			date = null;
		}
	}
	
	/**
	 * 异常捕捉到的消息打印方法
	 * @param e Exception类对象
	 */
	public static void logPrintException(Exception e, String packagePath ){
		SimpleDateFormat format = null;
		Date date = null;
		packagePath = (packagePath!=null&&packagePath.length()>0)?packagePath:"";

		try {
			format = new SimpleDateFormat("HH:mm:ss");
			date = new Date(System.currentTimeMillis());
			if(e!=null && e.getMessage()!=null){
				Log.i(format.format(date)+"捕捉异常的消息: "+packagePath,e.getMessage());
			}else{
				Log.i(format.format(date) + "捕捉异常的消息 "+packagePath,"e==null或e.getMessage()==null");
			}
		} catch (Exception e1) {
			Util.logPrint("logPrintException方法异常"+e1.getMessage());
			e.printStackTrace();
		} finally{
			format = null;
			date = null;
		}
	}

	/**
	 * @desc activity跳转方法
	 * @param act 调用组件activity对象
	 * @param cls 目标activity
	 * @param isFinish true表示要finish掉调用activity; false则不用finish
	 * @param bundle 需要传递到目标activity的参数,通过Bundle封装
	 */
	public static void activitySkip(Activity act, Class<?> cls, boolean isFinish
			, Bundle bundle){
		Intent it = null;
		try{
			if(act!= null){
				it = new Intent();
				it.setClass(act, cls);
				if(bundle != null && !bundle.isEmpty()){
					it.putExtras(bundle);	
				}
				act.startActivity(it);
				if(isFinish){
					act.finish();
				}
			}
		}catch(Exception e){
			Util.logPrint(e.getMessage());
		}
	}

	/**
	 * 该方法用作判断用户是否登录，如果没有登录则跳转到登录界面，如果已经登录则跳转到目标页面
	 * @param act 当前activity对象
	 * @param objActivityPath 目标activity所在的包路径（反射实例化对象）
	 * @param isFinish 是否finish掉当前activity
	 */
/*	public static void checkIfLoggedIn(Activity act, String objActivityPath , boolean isFinish){
		SharedPreferenceHelper helper = new SharedPreferenceHelper(act, Config.USER_INFO_PREFERENCE);
		if(helper.doSearchString("cc20.awc002").length()>0){
			Bundle bundle =	new Bundle();
			String URL = Config.BASE_URL_139+"cc20Action_login.do";
			SerAjaxParams params = new SerAjaxParams();
			params.put("cc20.awc002", helper.doSearchString("cc20.awc002"));
			params.put("cc20.awb004", helper.doSearchString("cc20.awb004"));
			params.put("name", helper.doSearchString("name"));
			params.put("pwd", helper.doSearchString("pwd"));

			bundle.putString("url", URL);
			bundle.putSerializable("params", params);
			bundle.putString("className", objActivityPath);
			Util.activitySkip(act, NetLoadViewActivity.class, isFinish, bundle);
		}else{
			Util.showToast(act, "您需要登录到系统后才可以进入该功能！");
			Util.activitySkip(act, PersonalLoginActivity.class, isFinish, null);
		}


	}*/

	/**
	 * @desc 跳转到个人中心（方法会判断用户是否登录,依据不同情况做相应跳转处理）
	 * @param act 调用组件activity对象
	 * @param isFinish true表示要finish掉调用activity; false则不用finish
	 */
	/*public static void skipToPersonCentre(Activity act, boolean isFinish){
		SharedPreferenceHelper helper = new SharedPreferenceHelper(act, Config.USER_INFO_PREFERENCE);
		//SharedPreferenceHelper enterHelper = new SharedPreferenceHelper(act, Config.ENTERPRISE_INFO_PREFERENCE);

		if(helper.doSearchString("cc20.awc002").length()>0){
			Bundle bundle =	new Bundle();
			String URL = Config.BASE_URL_139+"cc20Action_login.do";
			SerAjaxParams params = new SerAjaxParams();
			params.put("cc20.awc002", helper.doSearchString("cc20.awc002"));
			params.put("cc20.awb004", helper.doSearchString("cc20.awb004"));
			params.put("name", helper.doSearchString("name"));
			params.put("pwd", helper.doSearchString("pwd"));
			bundle.putString("url", URL);
			bundle.putSerializable("params", params);
			bundle.putString("className", "com.whzl.dtjy.activity.personalCentre.PersonalCentreActivity");
			Util.activitySkip(act, NetLoadViewActivity.class, isFinish, bundle);
		}
//		else if(enterHelper.doSearchString("cb20.awb002").length()>0){
//			Bundle bundle =	new Bundle();
//			String URL = Config.BASE_URL_139+ "cb20Action_login.do";
//			SerAjaxParams params = new SerAjaxParams();
//			params.put("cb20.awb002", enterHelper.doSearchString("cb20.awb002"));
//			params.put("cb20.awb004", enterHelper.doSearchString("cb20.awb004"));
//			params.put("name", enterHelper.doSearchString("name"));
//			params.put("pwd", enterHelper.doSearchString("pwd"));
//
//			bundle.putString("url", URL);
//			bundle.putSerializable("params", params);
//			bundle.putString("className", "com.whzl.dtjy.activity.enterpriseCentre.EnterpriseCentreActivity");
//			Util.activitySkip(act, NetLoadViewActivity.class, isFinish, bundle);
//
//		}
		else {
			Util.activitySkip(act, DtPerLoginActivity.class, isFinish, null);
		}
	}

	public static void skipToCompanyCentre(Activity act, boolean isFinish){
		SharedPreferenceHelper enterHelper = new SharedPreferenceHelper(act, Config.ENTERPRISE_INFO_PREFERENCE);
		if(enterHelper.doSearchString("cb20.awb002").length()>0){
			Bundle bundle =	new Bundle();
			String URL = Config.BASE_URL_139+ "cb20Action_login.do";
			SerAjaxParams params = new SerAjaxParams();
			params.put("cb20.awb002", enterHelper.doSearchString("cb20.awb002"));
			params.put("cb20.awb004", enterHelper.doSearchString("cb20.awb004"));
			params.put("name", enterHelper.doSearchString("name"));
			params.put("pwd", enterHelper.doSearchString("pwd"));

			bundle.putString("url", URL);
			bundle.putSerializable("params", params);
			bundle.putString("className", "com.whzl.dtjy.activity.enterpriseCentre.EnterpriseCentreActivity");
			Util.activitySkip(act, NetLoadViewActivity.class, isFinish, bundle);

		} else {
			Util.activitySkip(act, LoginActivity.class, isFinish, null);
		}
	}
	*/

	/**
	 * 重写makeText方法,LENGTH_LONG较长时间显示弹出消息
	 * @param message 需要提示的消息
	 */
	public static void showToast(Activity act, String message){
		if(act != null){
			if(message != null){
				Util.logPrint("app调用了showToast方法");
				Toast.makeText(act, message, Toast.LENGTH_SHORT).show();
			}
		}else{
			logPrint("调用showToast方法异常,可能是Context对象为null");
		}
	}

	/**
	 * 重写makeText方法，LENGTH_SHORT短暂显示消息
	 * @param message 需要提示的消息
	 */
	public static void showToast_Short(Activity act, String message){
		if(act != null){
			if(message != null){
				Util.logPrint("app调用了showToast方法");
				Toast.makeText(act, message, Toast.LENGTH_SHORT).show();
			}
		}else{
			logPrint("调用showToast方法异常,可能是Context对象为null");
		}
	}

	/**
	 * getCurrentTime:获取当前时间
	 * @param  @param format
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  WHZL Ver 1.0
	*/
	public static String getCurrentTime(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		String currentTime = sdf.format(date);
		return currentTime;
	}

	
	/**
	 * getCurrentTime:获取当前时间
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  WHZL Ver 1.0
	*/
	public static String getCurrentTime() {
		return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
	}

	/**
	 * 编码转换为对应的内容
	 * @param code 后台返回的错误消息编码
	 * @return 后台返回的错误消息
	 */
	public static String getErrorMsgByCode(String code){
		String errorMsg = "";
		Integer errorCode = 0;
		try{
			if(code!=null && code.length()>0){
				errorCode = Integer.parseInt(code);
			}else{
				return "";
			}
			switch (errorCode){
				//===========验证验证码smsAction_check.do=========
				case 203:
					errorMsg = "验证码过期";
					break;
				case 204:
					errorMsg = "验证码错误";
					break;
				case 205:
					errorMsg = "验证码错误";
					break;
				//===========验证验证码 end=========================
				//===========请求验证码smsAction_sendSms.do=========
				case 207:
					errorMsg = "两分钟内只能请求一次";
					break;
				//===========请求验证码 end=========================
				//===个人会员注册的错误消息cc20Action_register.do=====
				case 411001:
					errorMsg = "用户名不能为空";
					break;
				case 411002:
					errorMsg = "身份证号码不能为空";
					break;
				case 411003:
					errorMsg = "登陆密码不能为空";
					break;
				case 411004:
					errorMsg = "民族不能为空";
					break;
				case 411005:
					errorMsg = "户口性质不能为空";
					break;
				case 411006:
					errorMsg = "文化程度不能为空";
					break;
				case 411007:
					errorMsg = "人员类别不能为空";
					break;
				case 411008:
					errorMsg = "技术等级不能为空";
					break;
				case 411009:
					errorMsg = "手机号码不能为空";
					break;
				case 411010:
					errorMsg = "电子邮箱不能为空";
					break;
				case 411011:
					errorMsg = "身份证号码已存在";
					break;
				//===个人会员注册的错误消息end========================
				//===========个人会员登录cc20Acton_login.do=========
				case 412001:
					errorMsg = "用户名不能为空";
					break;
				case 412002:
					errorMsg = "密码不能为空";
					break;
				case 412003:
					errorMsg = "用户名或密码不存在";
					break;
				//===========个人会员登录end==========================
				case 1000000:
					errorMsg = "操作异常1000000";
					break;
			}
		}catch(Exception e){
			Util.logPrintException(e, classPath);
			return Config.OPERATE_FAILED;
		}
		return errorMsg;
	}

	/**
	 * 页面加载,网络请求等耗时操作的等待界面--gif动画
	 * @param gifView
	 */
	public static GifView initLoadAnimationView(View gifView){
		if(gifView!=null){
			try {
				GifView mView = (GifView) gifView;
				// 设置Gif图片源
				mView.setGifImage(R.drawable.loaddog);
				// 添加监听器
				// netLoad_gif.setOnClickListener(this);
				// 设置显示的大小，拉伸或者压缩
				mView.setShowDimension(285, 220);
				// 设置加载方式：先加载后显示、边加载边显示、只显示第一帧再显示
				mView.setGifImageType(GifView.GifImageType.WAIT_FINISH);
				return mView;
			}catch (Exception e){
				Util.logPrintException(e, classPath);
				return null;
			}
		}
		return null;
	}

	/**
	 * @Desc 获取系统API版本号
	 * @return
	 */
	public static int toGetAPI_Version(){
		int version = android.os.Build.VERSION.SDK_INT;
		Log.i("系统API版本号", "API"+version);
		return version;
	}

	/**
	 * 比较两个日期的大小, 参数1的日期早于日期2则返回true, 参数1的日期迟于日期2则返回false;
	 * @param dateStr1 日期字符串
	 * @param dateStr2 日期字符串
	 * @return
	 */
	public static boolean compareDate(String dateStr1, String dateStr2){
		boolean result = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		dateStr1 = (dateStr1!=null&&dateStr1.length()>0)?dateStr1:"";
		dateStr2 = (dateStr2!=null&&dateStr2.length()>0)?dateStr2:"";
		try {
			Date date1  = dateFormat.parse(dateStr1);
			Date date2= dateFormat.parse(dateStr2);
			if(date1.before(date2)){
				result = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			Util.logPrint("compareDate日期比较方法抛出异常！");
		}
		return result;
	}
}