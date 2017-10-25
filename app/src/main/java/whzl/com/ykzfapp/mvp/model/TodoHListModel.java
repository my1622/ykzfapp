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
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.mvp.contract.TodoHListContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class TodoHListModel extends BaseModel implements TodoHListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public TodoHListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseEntity<List<HouseListBean>>> listHouseByUser(String name, String password, String pageNum, String rows) {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .listHouseByUser(name, password,pageNum,rows);
    }
}