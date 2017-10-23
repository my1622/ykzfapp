package whzl.com.ykzfapp.base;

import android.content.Context;
import android.content.res.Resources;

import com.jess.arms.base.BaseApplication;

/**
 * APPLICATION
 */
public class MyBaseApplication extends BaseApplication {

    private static MyBaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }




}
