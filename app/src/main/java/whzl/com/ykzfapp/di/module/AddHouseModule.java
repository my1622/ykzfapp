package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.AddHouseContract;
import whzl.com.ykzfapp.mvp.model.AddHouseModel;


@Module
public class AddHouseModule {
    private AddHouseContract.View view;

    /**
     * 构建AddHouseModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddHouseModule(AddHouseContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddHouseContract.View provideAddHouseView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddHouseContract.Model provideAddHouseModel(AddHouseModel model) {
        return model;
    }
}