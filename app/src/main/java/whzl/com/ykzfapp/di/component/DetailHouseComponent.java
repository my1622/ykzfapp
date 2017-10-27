package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import whzl.com.ykzfapp.di.module.DetailHouseModule;

import whzl.com.ykzfapp.mvp.ui.activity.DetailHouseActivity;

@ActivityScope
@Component(modules = DetailHouseModule.class, dependencies = AppComponent.class)
public interface DetailHouseComponent {
    void inject(DetailHouseActivity activity);
}