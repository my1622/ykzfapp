package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import whzl.com.ykzfapp.di.module.LoginModule;
import whzl.com.ykzfapp.mvp.ui.activity.LoginActivity;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}