package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.CommunityBean;
import whzl.com.ykzfapp.mvp.contract.CommunityContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class CommunityModel extends BaseModel implements CommunityContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public CommunityModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseEntity<List<CommunityBean>>> requestData() {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .getCommunity();
    }

}