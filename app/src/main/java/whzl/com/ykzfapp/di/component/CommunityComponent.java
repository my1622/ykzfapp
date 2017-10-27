package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import whzl.com.ykzfapp.di.module.CommunityModule;

import whzl.com.ykzfapp.mvp.ui.activity.CommunityActivity;

@ActivityScope
@Component(modules = CommunityModule.class, dependencies = AppComponent.class)
public interface CommunityComponent {
    void inject(CommunityActivity activity);
}