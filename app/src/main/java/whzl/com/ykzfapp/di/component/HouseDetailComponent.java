package whzl.com.ykzfapp.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import whzl.com.ykzfapp.di.module.HouseDetailModule;
import whzl.com.ykzfapp.mvp.ui.activity.HouseDetailActivity;

@ActivityScope
@Component(modules = HouseDetailModule.class, dependencies = AppComponent.class)
public interface HouseDetailComponent {
    void inject(HouseDetailActivity activity);
}