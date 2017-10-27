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
import whzl.com.ykzfapp.mvp.contract.DetailHouseContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class DetailHouseModel extends BaseModel implements DetailHouseContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public DetailHouseModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

}