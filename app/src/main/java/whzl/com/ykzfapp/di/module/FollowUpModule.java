package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.FollowUpContract;
import whzl.com.ykzfapp.mvp.model.FollowUpModel;


@Module
public class FollowUpModule {
    private FollowUpContract.View view;

    /**
     * 构建FollowUpModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FollowUpModule(FollowUpContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    FollowUpContract.View provideFollowUpView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    FollowUpContract.Model provideFollowUpModel(FollowUpModel model) {
        return model;
    }
}