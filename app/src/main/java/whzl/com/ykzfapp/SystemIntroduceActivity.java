package whzl.com.ykzfapp;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import whzl.com.ykzfapp.tools.Util;

/**
 * 介绍系统的三个动画页面,当app第一次使用时会被调用,在系统<br/>
 * 设置中也提供进入的入口
 */

public class SystemIntroduceActivity extends Activity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Util.logPrint("系统介绍页面activity返回键点击");

		}
		return false;
	}

}
