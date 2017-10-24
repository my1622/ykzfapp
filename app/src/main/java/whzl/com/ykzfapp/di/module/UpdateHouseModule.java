package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.UpdateHouseContract;
import whzl.com.ykzfapp.mvp.model.UpdateHouseModel;


@Module
public class UpdateHouseModule {
    private UpdateHouseContract.View view;

    /**
     * 构建UpdateHouseModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public UpdateHouseModule(UpdateHouseContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UpdateHouseContract.View provideUpdateHouseView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UpdateHouseContract.Model provideUpdateHouseModel(UpdateHouseModel model) {
        return model;
    }
}