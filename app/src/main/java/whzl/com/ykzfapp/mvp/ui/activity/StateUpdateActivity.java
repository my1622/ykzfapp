package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.bean.UserBean;
import whzl.com.ykzfapp.di.component.DaggerStateUpdateComponent;
import whzl.com.ykzfapp.di.module.StateUpdateModule;
import whzl.com.ykzfapp.mvp.contract.StateUpdateContract;
import whzl.com.ykzfapp.mvp.presenter.StateUpdatePresenter;
import whzl.com.ykzfapp.utils.ACache;
import whzl.com.ykzfapp.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class StateUpdateActivity extends BaseActivity<StateUpdatePresenter>
        implements StateUpdateContract.View,View.OnClickListener {

    ACache mCache;
    private String name,password;
    private UserBean userBean;
    private String houseId;

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar_me)
    Toolbar toolbarMe;
    @BindView(R.id.tv_yes)
    TextView tvYes;
    @BindView(R.id.tv_not)
    TextView tvNot;
    @BindView(R.id.ed_infostat_updata)
    EditText edInfostatUpdata;
    @BindView(R.id.progress_btn)
    CircularProgressButton progressBtn;

    boolean isCheck=true;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerStateUpdateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .stateUpdateModule(new StateUpdateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_state_update; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar();
    }

    private void initToolbar() {
        tvToolbarTitle.setText("房源状态");
        toolbarMe.setNavigationIcon(R.mipmap.icon_back);
        toolbarMe.setNavigationOnClickListener(v -> killMyself());
        tvYes.setOnClickListener(this);
        tvNot.setOnClickListener(this);
        progressBtn.setOnClickListener(this);

        mCache = ACache.get(StateUpdateActivity.this);
        userBean= (UserBean) mCache.getAsObject(getString(R.string.user_bean));
        name =  userBean.getName();
        password =  userBean.getPassword();
        houseId = getIntent().getStringExtra("houseId");
    }


    @Override
    public void showLoading() {
        progressBtn.startAnimation();
    }

    @Override
    public void hideLoading() {
        progressBtn.revertAnimation();
        killMyself();
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_yes:
                isCheck=true;

                Drawable showPwdDrawable = getResources().getDrawable(R.drawable.redio_buttom_checked);
                tvYes.setCompoundDrawablesRelativeWithIntrinsicBounds(showPwdDrawable,null, null , null);
                Drawable showPwdDrawable1 = getResources().getDrawable(R.drawable.redio_button_unchecked);
                tvNot.setCompoundDrawablesRelativeWithIntrinsicBounds(showPwdDrawable1,null, null , null);
                break;
            case R.id.tv_not:
                isCheck=false;
                Drawable showPwdDrawable2 = getResources().getDrawable(R.drawable.redio_buttom_checked);
                tvNot.setCompoundDrawablesRelativeWithIntrinsicBounds(showPwdDrawable2,null, null , null);
                Drawable showPwdDrawable3 = getResources().getDrawable(R.drawable.redio_button_unchecked);
                tvYes.setCompoundDrawablesRelativeWithIntrinsicBounds(showPwdDrawable3,null, null , null);
                break;
            case R.id.progress_btn:
                if (TextUtils.isEmpty(edInfostatUpdata.getText().toString().trim())){
                    ToastUtil.show(StateUpdateActivity.this,"请输入状态变更");
                    return;
                }
                progressBtn.startAnimation();
                String infoState;
                if (isCheck) {
                    infoState = "103";
                }else{
                    infoState = "102";
                }
                mPresenter.infoStateUpdate(name, password, houseId, infoState,edInfostatUpdata.getText().toString().trim());
                break;
            default:

                break;
        }
    }

    @Override
    public void success(HouseListBean houseListBean) {
        ToastUtil.show(this,"房源状态变更成功");
    }
}
