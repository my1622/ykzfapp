package whzl.com.ykzfapp.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.HouseDetailBean;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.mvp.contract.UpdateHouseContract;


@ActivityScope
public class UpdateHousePresenter extends BasePresenter<UpdateHouseContract.Model, UpdateHouseContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public UpdateHousePresenter(UpdateHouseContract.Model model, UpdateHouseContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    public void requestData(String houseId){
        mModel.requestData(houseId)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {

                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<BaseEntity<HouseDetailBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseEntity<HouseDetailBean> houseDetailBeanBaseEntity) {
                        mRootView.success(houseDetailBeanBaseEntity.getObj());
                    }
                });
    }

    public void updataHouse(String loginName,String loginPwd,String houseId,
                            String title,String CommunityId,String unitNo,
                            String buildNo,String roomNo,String bedRooms,
                            String livingRooms,String cookRooms,String bathRooms,
                            String fyPath,String fyOutPath,String hxPath,
                            String vdoPath,String voicePath){
        mModel.updataHouse(loginName,loginPwd,houseId,
                title,CommunityId,unitNo,buildNo,roomNo,
                bedRooms, livingRooms,    cookRooms  ,bathRooms,
                fyPath,fyOutPath,hxPath,vdoPath,voicePath)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {

                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<BaseEntity<HouseListBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseEntity<HouseListBean> house) {
                        mRootView.success(house.getObj());
                    }
                });
    }
}
