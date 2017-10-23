package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import whzl.com.ykzfapp.di.component.DaggerLoginComponent;
import whzl.com.ykzfapp.di.module.LoginModule;
import whzl.com.ykzfapp.mvp.contract.LoginContract;
import whzl.com.ykzfapp.mvp.presenter.LoginPresenter;

import whzl.com.ykzfapp.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * @author my creat at 2017-10-22
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


}
