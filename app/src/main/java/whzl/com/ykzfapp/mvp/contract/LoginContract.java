package whzl.com.ykzfapp.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.UserBean;


public interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void loginSuccess(@NonNull BaseEntity<UserBean> userBaseEntity);
        void loginError();
        void logoutSuccess();

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseEntity<UserBean>> getUsers(String name, String pwd);
    }
}
