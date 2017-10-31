package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.CityBean;
import whzl.com.ykzfapp.bean.CommunityBean;
import whzl.com.ykzfapp.di.component.DaggerCommunityComponent;
import whzl.com.ykzfapp.di.module.CommunityModule;
import whzl.com.ykzfapp.mvp.contract.CommunityContract;
import whzl.com.ykzfapp.mvp.presenter.CommunityPresenter;
import whzl.com.ykzfapp.mvp.ui.adapter.CityAdapter;
import whzl.com.ykzfapp.mvp.ui.widget.DividerItemDecoration;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class CommunityActivity extends BaseActivity<CommunityPresenter> implements CommunityContract.View {

    @BindView(R.id.toolbar_me)
    Toolbar mToolBar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;


    private RecyclerView mRv;
    private CityAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas = new ArrayList<>();

    private SuspensionDecoration mDecoration;


    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;


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
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mAdapter = new CityAdapter(mDatas, (view, id ,name) -> {
            Intent intent = new Intent();
            intent.putExtra("communityId",id);
            intent.putExtra("communityName",name);
            setResult(RESULT_OK, intent);
            finish();

        }


        );
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(CommunityActivity.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        mPresenter.requestData();

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


    @Override
    public void success(List<CommunityBean> communitys) {
        mDatas = new ArrayList<>();
        for (int i = 0; i < communitys.size(); i++) {
            CityBean cityBean = new CityBean();
            //设置小区名称
            cityBean.setCity(communitys.get(i).getName());
            cityBean.setId(communitys.get(i).getId());
            mDatas.add(cityBean);
        }
        mAdapter.setDatas(mDatas);
        mAdapter.notifyDataSetChanged();

        //设置数据
        mIndexBar.setmSourceDatas(mDatas)
                .invalidate();
        mDecoration.setmDatas(mDatas);
    }
}
