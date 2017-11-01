package whzl.com.ykzfapp.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.HouseListBean;


public interface AddHouseContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void success(HouseListBean houseListBean);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseEntity<HouseListBean>> addHouse(String loginName, String loginPwd, String title, String communityId, String unitNo, String buildNo, String roomNo, String bedRooms, String livingRooms, String cookRooms, String bathRooms, String fyPath, String fyOutPath, String hxPath, String vdoPath, String voicePath);

    }
}
