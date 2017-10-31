package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.FollowListBean;
import whzl.com.ykzfapp.bean.UserBean;
import whzl.com.ykzfapp.di.component.DaggerFollowUpComponent;
import whzl.com.ykzfapp.di.module.FollowUpModule;
import whzl.com.ykzfapp.mvp.contract.FollowUpContract;
import whzl.com.ykzfapp.mvp.presenter.FollowUpPresenter;
import whzl.com.ykzfapp.mvp.ui.adapter.FollowListAdapter;
import whzl.com.ykzfapp.utils.ACache;
import whzl.com.ykzfapp.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static whzl.com.ykzfapp.R.id.progress_btn;


public class FollowUpActivity extends BaseActivity<FollowUpPresenter> implements FollowUpContract.View {

    ACache mCache;
    private String name,password;
    private UserBean userBean;

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar_me)
    Toolbar toolbarMe;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(progress_btn)
    CircularProgressButton progressBtn;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String houseId;


    FollowListAdapter mAdatper;
    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerFollowUpComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .followUpModule(new FollowUpModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_follow_up; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar();
        mCache = ACache.get(FollowUpActivity.this);
        userBean= (UserBean) mCache.getAsObject(getString(R.string.user_bean));
        name =  userBean.getName();
        password =  userBean.getPassword();
        houseId = getIntent().getStringExtra("houseId");

        mPresenter.requestData(houseId);

        initRecycleView();
        ArmsUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(this));
        progressBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(editText.getText().toString().trim())){
                ToastUtil.show(FollowUpActivity.this,"请输入跟进信息");
                return;
            }
            progressBtn.startAnimation();
            mPresenter.addFollowUp(name,password,houseId,editText.getText().toString().trim());
        });

    }

    private void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdatper = FollowListAdapter
                .builder()
                .build();
        mRecyclerView.setAdapter(mAdatper);
        mAdatper.setOnItemClickListener((adapter, view, position) -> {
            if (position>0) {
                String[] date = ((FollowListAdapter) adapter).getData().get(position).getFollowUpDate().split(" ");
                new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("跟进人："+((FollowListAdapter)adapter).getData().get(position).getFollowUpUser())
                        .setContentText("跟进时间："+date[0]+"\n"
                                +"跟进内容："+((FollowListAdapter)adapter).getData().get(position).getContent())
                        .setConfirmText("ok")
                        .show();
            }

        });
    }

    private void initToolbar() {
        tvToolbarTitle.setText("跟进");
        toolbarMe.setNavigationIcon(R.mipmap.icon_back);
        toolbarMe.setNavigationOnClickListener(v->finish());
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        progressBtn.revertAnimation();
        ((InputMethodManager)this.getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
    public void success(List<FollowListBean> datas) {
        datas.add(0,new FollowListBean("序号","跟进人","跟进内容","跟进时间"));
        mAdatper.getData().clear();
        if (datas.size() != 0) {
            mAdatper.addData(datas);

        }
        mAdatper.notifyDataSetChanged();
    }

    @Override
    public void success(FollowListBean followBeans) {

        ToastUtil.show(this,"添加成功");

        mPresenter.requestData(houseId);
    }
}
