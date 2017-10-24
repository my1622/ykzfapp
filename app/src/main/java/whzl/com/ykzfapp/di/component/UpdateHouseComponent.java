package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import whzl.com.ykzfapp.di.module.UpdateHouseModule;

import whzl.com.ykzfapp.mvp.ui.activity.UpdateHouseActivity;

@ActivityScope
@Component(modules = UpdateHouseModule.class, dependencies = AppComponent.class)
public interface UpdateHouseComponent {
    void inject(UpdateHouseActivity activity);
}