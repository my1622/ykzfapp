package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import whzl.com.ykzfapp.mvp.contract.UpdateHouseContract;


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

}