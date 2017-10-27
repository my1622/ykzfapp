package whzl.com.ykzfapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import whzl.com.ykzfapp.mvp.contract.UploadFileContract;
import whzl.com.ykzfapp.mvp.model.UploadFileModel;


@Module
public class UploadFileModule {
    private UploadFileContract.View view;

    /**
     * 构建UploadFileModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public UploadFileModule(UploadFileContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UploadFileContract.View provideUploadFileView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UploadFileContract.Model provideUploadFileModel(UploadFileModel model) {
        return model;
    }
}