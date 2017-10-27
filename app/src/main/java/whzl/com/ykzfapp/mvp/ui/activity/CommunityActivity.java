package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.di.component.DaggerCommunityComponent;
import whzl.com.ykzfapp.di.module.CommunityModule;
import whzl.com.ykzfapp.mvp.contract.CommunityContract;
import whzl.com.ykzfapp.mvp.presenter.CommunityPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class CommunityActivity extends BaseActivity<CommunityPresenter> implements CommunityContract.View {

    @BindView(R.id.toolbar_me)
    Toolbar mToolBar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_right)
    EditText edTitleRight;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCommunityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .communityModule(new CommunityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_community; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar();
    }

    private void initToolbar() {
        tvToolbarTitle.setText("选择小区");

        mToolBar.setNavigationIcon(R.drawable.ico_close);
        mToolBar.setNavigationOnClickListener(v -> this.finish());
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
