package whzl.com.ykzfapp.app;


import com.baidu.mapapi.SDKInitializer;

import whzl.com.ykzfapp.base.MyBaseApplication;

/**
 * APPLICATION
 */
public class AppApplication extends MyBaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());



    }
}
