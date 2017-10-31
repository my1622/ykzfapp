package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.SignBean;
import whzl.com.ykzfapp.mvp.contract.SignContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class SignModel extends BaseModel implements SignContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SignModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseEntity<SignBean>> sign(String loginName, String loginPwd, int signCommunityId, String signInfo) {
        return  mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .sign(loginName,loginPwd,signCommunityId,
                        signInfo);
    }
}