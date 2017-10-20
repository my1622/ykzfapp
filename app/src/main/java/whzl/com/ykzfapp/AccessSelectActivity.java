package whzl.com.ykzfapp;

import android.os.Bundle;

import net.tsz.afinal.FinalActivity;

import whzl.com.ykzfapp.tools.Util;

/**
 *
 * 系统入口选择activity, 该activity无界面只负责判断调用
 * 哪个activity作为初始界面
 *
 * Created by my on 2017/6/22.
 */

public class AccessSelectActivity extends FinalActivity{

    private boolean mark = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化系统属性
        Util.initSystemProperty(AccessSelectActivity.this);
//		preference = new SharedPreferenceHelper(AccessSelectActivity.this);
//		if (-1 == preference.doSearchInt(Config.CHECKED_SYSINTRODUCE)) {
//			Util.logPrint("选择进入系统介绍功能区");
//			mark = true;
//		} else if (1 == preference.doSearchInt(Config.CHECKED_SYSINTRODUCE)) {
//			Util.logPrint("选择进入系统主页");
//			mark = false;
//		} else {
//			Util.logPrint("调用SharedPreference异常,默认进入系统主页");
//			mark = false;
//		}
        if (mark) {
            Util.activitySkip(AccessSelectActivity.this,
                    GuildeActivity.class, true,null);
        } else {
            Util.activitySkip(AccessSelectActivity.this,
                    WelcomeActivity.class, true,null);
        }
    }



}
