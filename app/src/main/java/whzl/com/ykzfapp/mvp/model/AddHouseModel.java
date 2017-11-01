package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.mvp.contract.AddHouseContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class AddHouseModel extends BaseModel implements AddHouseContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AddHouseModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseEntity<HouseListBean>> addHouse(String loginName, String loginPwd, String title, String communityId, String unitNo, String buildNo, String roomNo, String bedRooms, String livingRooms, String cookRooms, String bathRooms, String fyPath, String fyOutPath, String hxPath, String vdoPath, String voicePath) {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .addHouse(loginName,loginPwd,
                        title,communityId,buildNo,unitNo,roomNo,
                        bedRooms, livingRooms,cookRooms ,bathRooms,
                        fyPath,fyOutPath,hxPath,vdoPath,voicePath);
    }
}