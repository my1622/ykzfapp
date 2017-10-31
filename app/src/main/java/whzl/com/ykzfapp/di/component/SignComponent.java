package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import whzl.com.ykzfapp.di.module.SignModule;

import whzl.com.ykzfapp.mvp.ui.activity.SignActivity;

@ActivityScope
@Component(modules = SignModule.class, dependencies = AppComponent.class)
public interface SignComponent {
    void inject(SignActivity activity);
}