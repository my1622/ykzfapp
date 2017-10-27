package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import whzl.com.ykzfapp.mvp.contract.CommunityContract;


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

}