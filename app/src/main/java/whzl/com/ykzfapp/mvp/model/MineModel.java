package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.mvp.contract.MineContract;
import whzl.com.ykzfapp.mvp.model.api.service.UserService;


@ActivityScope
public class MineModel extends BaseModel implements MineContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MineModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseEntity<String>> logout() {
        return mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .logout();



    }
}