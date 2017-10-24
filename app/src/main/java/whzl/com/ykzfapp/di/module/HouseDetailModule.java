package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.HouseDetailContract;
import whzl.com.ykzfapp.mvp.model.HouseDetailModel;


@Module
public class HouseDetailModule {
    private HouseDetailContract.View view;

    /**
     * 构建HouseDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HouseDetailModule(HouseDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HouseDetailContract.View provideHouseDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HouseDetailContract.Model provideHouseDetailModel(HouseDetailModel model) {
        return model;
    }
}