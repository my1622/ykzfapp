package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.StateUpdateContract;
import whzl.com.ykzfapp.mvp.model.StateUpdateModel;


@Module
public class StateUpdateModule {
    private StateUpdateContract.View view;

    /**
     * 构建StateUpdateModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public StateUpdateModule(StateUpdateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    StateUpdateContract.View provideStateUpdateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    StateUpdateContract.Model provideStateUpdateModel(StateUpdateModel model) {
        return model;
    }
}