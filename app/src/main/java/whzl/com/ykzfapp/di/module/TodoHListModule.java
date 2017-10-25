package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;
import whzl.com.ykzfapp.mvp.contract.TodoHListContract;
import whzl.com.ykzfapp.mvp.model.TodoHListModel;


@Module
public class TodoHListModule {
    private TodoHListContract.View view;

    /**
     * 构建TodoHListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TodoHListModule(TodoHListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TodoHListContract.View provideTodoHListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TodoHListContract.Model provideTodoHListModel(TodoHListModel model) {
        return model;
    }



}