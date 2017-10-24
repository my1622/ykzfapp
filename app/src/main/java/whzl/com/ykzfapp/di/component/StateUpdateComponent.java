package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import whzl.com.ykzfapp.di.module.StateUpdateModule;

import whzl.com.ykzfapp.mvp.ui.activity.StateUpdateActivity;

@ActivityScope
@Component(modules = StateUpdateModule.class, dependencies = AppComponent.class)
public interface StateUpdateComponent {
    void inject(StateUpdateActivity activity);
}