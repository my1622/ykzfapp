package whzl.com.ykzfapp.mvp.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.bean.UserBean;
import whzl.com.ykzfapp.di.component.DaggerLoginComponent;
import whzl.com.ykzfapp.di.module.LoginModule;
import whzl.com.ykzfapp.mvp.contract.LoginContract;
import whzl.com.ykzfapp.mvp.presenter.LoginPresenter;
import whzl.com.ykzfapp.utils.ACache;
import whzl.com.ykzfapp.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * @author my creat at 2017-10-22
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private boolean isFirstLogin;
    ACache mCache;
    @BindView(R.id.username)
    AutoCompleteTextView username;
    @BindView(R.id.password)
    EditText password;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        mCache = ACache.get(LoginActivity.this);
        String name,password;
        try {

            isFirstLogin=true;
            name =  mCache.getAsString("name");
            password=mCache.getAsString("password");
            if (name!=null && password!=null){
                isFirstLogin=false;
                mPresenter.login(name, password);
            }

        } catch (Exception e) {
            isFirstLogin=true;
            e.printStackTrace();
        }


    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
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


    public void AmtempToLogin(View view) {
        if (TextUtils.isEmpty(username.getText().toString().trim())){
            ToastUtil.show(this,"用户名不能为空");

            return;
        }
        if (TextUtils.isEmpty(password.getText().toString().trim())){
            ToastUtil.show(this,"密码不能为空");

            return;
        }

            mPresenter.login(username.getText().toString().trim(), password.getText().toString().trim());
        //startActivity(new Intent(LoginActivity.this,StateUpdateActivity.class));
    }

    @Override
    public void loginSuccess(@NonNull BaseEntity<UserBean> userBaseEntity) {

        mCache.put(getString(R.string.user_bean), userBaseEntity.getObj());
        if (isFirstLogin) {
            mCache.put("name", username.getText().toString().trim());
            mCache.put("password", password.getText().toString().trim());
        }
        ToastUtil.show(LoginActivity.this, getString(R.string.login_success));
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        this.finish();

    }

    @Override
    public void loginError() {
        ToastUtil.show(this,"用户名或者密码错误");
    }

    @Override
    public void logoutSuccess() {

    }


}
