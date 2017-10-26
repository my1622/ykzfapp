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
import whzl.com.ykzfapp.bean.DictionaryBean;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.mvp.contract.HomeContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class HomeModel extends BaseModel implements HomeContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<BaseEntity<List<DictionaryBean>>> getDictionary() {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .getDictionary();
    }

    @Override
    public Observable<BaseEntity<List<HouseListBean>>> listHouse(String title, String region, String salePrice, String bedRooms, String area, String pageNum, String rows) {
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .listHouse(title,region,salePrice,bedRooms,area,pageNum,rows);
    }
}