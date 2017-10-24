package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import whzl.com.ykzfapp.di.module.FollowUpModule;

import whzl.com.ykzfapp.mvp.ui.activity.FollowUpActivity;

@ActivityScope
@Component(modules = FollowUpModule.class, dependencies = AppComponent.class)
public interface FollowUpComponent {
    void inject(FollowUpActivity activity);
}