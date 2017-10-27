package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import whzl.com.ykzfapp.di.module.UploadFileModule;
import whzl.com.ykzfapp.mvp.ui.activity.AudioActivity;
import whzl.com.ykzfapp.mvp.ui.activity.PictureActivity;
import whzl.com.ykzfapp.mvp.ui.activity.VideoActivity;


@ActivityScope
@Component(modules = UploadFileModule.class, dependencies = AppComponent.class)
public interface UploadFileComponent {
    void inject(AudioActivity audioActivity);
    void inject(PictureActivity pictureActivity);
    void inject(VideoActivity videoActivity);
}