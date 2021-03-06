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
import whzl.com.ykzfapp.bean.FollowListBean;
import whzl.com.ykzfapp.mvp.contract.FollowUpContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class FollowUpModel extends BaseModel implements FollowUpContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public FollowUpModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseEntity<List<FollowListBean>>> requestData(String houseId) {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .getFollowList(houseId);
    }

    @Override
    public Observable<BaseEntity<FollowListBean>> addFollowUp(String loginName, String loginPwd, String houseId, String followContent) {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .addFollowUp(loginName,loginPwd,houseId,followContent);
    }
}