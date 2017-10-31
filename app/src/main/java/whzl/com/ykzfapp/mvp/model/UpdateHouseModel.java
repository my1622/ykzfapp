package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.HouseDetailBean;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.mvp.contract.UpdateHouseContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class UpdateHouseModel extends BaseModel implements UpdateHouseContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public UpdateHouseModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
    @Override
    public Observable<BaseEntity<HouseDetailBean>> requestData(String houseId) {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .getHouseDetail(houseId);
    }

    @Override
    public Observable<BaseEntity<HouseListBean>> updataHouse(String loginName, String loginPwd, String houseId, String title, String communityId, String unitNo, String buildNo, String roomNo, String bedRooms, String livingRooms, String cookRooms, String bathRooms, String fyPath, String fyOutPath, String hxPath, String vdoPath, String voicePath) {
         return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .updataHouse(loginName,loginPwd,houseId,
                        title,communityId,buildNo,unitNo,roomNo,
                        bedRooms, livingRooms,cookRooms ,bathRooms,
                        fyPath,fyOutPath,hxPath,vdoPath,voicePath);
    }
}