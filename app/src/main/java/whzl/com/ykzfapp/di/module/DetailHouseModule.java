package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.DetailHouseContract;
import whzl.com.ykzfapp.mvp.model.DetailHouseModel;


@Module
public class DetailHouseModule {
    private DetailHouseContract.View view;

    /**
     * 构建DetailHouseModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public DetailHouseModule(DetailHouseContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    DetailHouseContract.View provideDetailHouseView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    DetailHouseContract.Model provideDetailHouseModel(DetailHouseModel model) {
        return model;
    }
}