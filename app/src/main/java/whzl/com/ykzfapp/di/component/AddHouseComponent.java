package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import whzl.com.ykzfapp.di.module.AddHouseModule;

import whzl.com.ykzfapp.mvp.ui.activity.AddHouseActivity;

@ActivityScope
@Component(modules = AddHouseModule.class, dependencies = AppComponent.class)
public interface AddHouseComponent {
    void inject(AddHouseActivity activity);
}