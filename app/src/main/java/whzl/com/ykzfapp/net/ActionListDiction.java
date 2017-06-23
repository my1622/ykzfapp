package whzl.com.ykzfapp.net;

import whzl.com.ykzfapp.Config;

/**
 * Created by 01 on 2017/6/22.
 */

public class ActionListDiction {
    public ActionListDiction(String letter ,String name,final SuccessCallback successCallback,final FailCallback failCallback) {

        new NetConnection(Config.SERVER_URL, HttpMethod.GET, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                successCallback.onSuccess(result);
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {

            }
        },Config.ACTION_LIST_DICTIONARY,Config.KEY_LETTER,letter,Config.KEY_NAME,name);

    }
    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
