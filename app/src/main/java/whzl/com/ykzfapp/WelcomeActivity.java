package whzl.com.ykzfapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import net.tsz.afinal.FinalActivity;

import whzl.com.ykzfapp.tools.Util;

/**
 * 系统加载activity
 * 
 * @author ll
 * @date 2015/04/02
 */
@SuppressLint("HandlerLeak")
public class WelcomeActivity extends FinalActivity {


	private Message msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置无标题  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.systemloading_layout);
		Util.logPrint("进入系统加载activity,2秒倒计时准备进入系统主页……");
		msg = handler.obtainMessage(0);
		handler.sendMessage(msg);

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:	//启动定时线程
				BusinessThread businessThread = new BusinessThread();
				businessThread.start();
				break;
			case 1: //两秒后开始跳转activity
				Util.activitySkip(WelcomeActivity.this,
						MainActivity.class, true,null);
				break;
			}
		}
	};

	private class BusinessThread extends Thread {
		@Override
		public void run() {
			try {	
				Thread.sleep(2000);
				msg = handler.obtainMessage(1);
				handler.sendMessage(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
