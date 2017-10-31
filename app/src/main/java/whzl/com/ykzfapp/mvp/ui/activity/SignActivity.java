package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
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
import whzl.com.ykzfapp.bean.SignBean;
import whzl.com.ykzfapp.bean.UserBean;
import whzl.com.ykzfapp.di.component.DaggerSignComponent;
import whzl.com.ykzfapp.di.module.SignModule;
import whzl.com.ykzfapp.mvp.contract.SignContract;
import whzl.com.ykzfapp.mvp.presenter.SignPresenter;
import whzl.com.ykzfapp.utils.ACache;
import whzl.com.ykzfapp.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SignActivity extends BaseActivity<SignPresenter>
        implements SignContract.View ,View.OnClickListener{

    ACache mCache;
    private static final int REQUEST_CODE_COMMUNITY=0;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar_me)
    Toolbar toolbarMe;
    @BindView(R.id.tv_commName)
    TextView tvCommName;
    @BindView(R.id.ed_sign_remarks)
    EditText edSignRemarks;
    @BindView(R.id.progress_btn)
    CircularProgressButton progressBtn;
    private int communitId;
    private UserBean userBean;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSignComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .signModule(new SignModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_sign; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mCache=ACache.get(SignActivity.this);
        userBean = (UserBean) mCache.getAsObject(getResources().getString(R.string.user_bean));

        initToolbar();
        progressBtn.setOnClickListener(this);
        tvCommName.setOnClickListener(this);
    }

    private void initToolbar() {
        tvToolbarTitle.setText("小区签到");
        toolbarMe.setNavigationIcon(R.mipmap.icon_back);
        toolbarMe.setNavigationOnClickListener(v -> finish());
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_commName:
                startActivityForResult(new Intent(this, CommunityActivity.class),REQUEST_CODE_COMMUNITY);
                break;
            case R.id.progress_btn:
                progressBtn.startAnimation();
                if (TextUtils.isEmpty(edSignRemarks.getText().toString().trim())){
                    ToastUtil.show(SignActivity.this,"请输入签到备注");
                    revertProgressBtn();
                    return;
                }
                if (communitId==0){
                    ToastUtil.show(SignActivity.this,"请选择小区");
                    revertProgressBtn();
                    return;
                }
                mPresenter.sign(userBean.getName(),userBean.getPassword(),communitId,edSignRemarks.getText().toString().trim());
            default:
                break;
        }
    }

    private void revertProgressBtn() {
        progressBtn.stopAnimation();
        progressBtn.revertAnimation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_COMMUNITY:
                     communitId = data.getIntExtra("communityId", 0);
                    tvCommName.setText(data.getStringExtra("communityName"));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void success(SignBean s) {
        ToastUtil.show(SignActivity.this,"签到成功");
        revertProgressBtn();
        finish();

    }
}
