package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.SignContract;
import whzl.com.ykzfapp.mvp.model.SignModel;


@Module
public class SignModule {
    private SignContract.View view;

    /**
     * 构建SignModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SignModule(SignContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SignContract.View provideSignView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SignContract.Model provideSignModel(SignModel model) {
        return model;
    }
}