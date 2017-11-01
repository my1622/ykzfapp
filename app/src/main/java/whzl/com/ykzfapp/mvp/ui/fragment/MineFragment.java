package whzl.com.ykzfapp.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xiaosu.pulllayout.SimplePullLayout;
import com.xiaosu.pulllayout.base.BasePullLayout;

import java.util.List;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.bean.UserBean;
import whzl.com.ykzfapp.di.component.DaggerMineComponent;
import whzl.com.ykzfapp.di.module.MineModule;
import whzl.com.ykzfapp.mvp.contract.MineContract;
import whzl.com.ykzfapp.mvp.presenter.MinePresenter;
import whzl.com.ykzfapp.mvp.ui.activity.DetailHouseActivity;
import whzl.com.ykzfapp.mvp.ui.activity.FollowUpActivity;
import whzl.com.ykzfapp.mvp.ui.activity.LoginActivity;
import whzl.com.ykzfapp.mvp.ui.activity.SignActivity;
import whzl.com.ykzfapp.mvp.ui.activity.StateUpdateActivity;
import whzl.com.ykzfapp.mvp.ui.activity.UpdateHouseActivity;
import whzl.com.ykzfapp.mvp.ui.adapter.MyHouListAdapter;
import whzl.com.ykzfapp.utils.ACache;
import whzl.com.ykzfapp.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static whzl.com.ykzfapp.mvp.ui.widget.GlideTool.cleanGlideDiskCache;
import static whzl.com.ykzfapp.utils.DataCleanManager.getTotalCacheSize;


public class MineFragment extends BaseFragment<MinePresenter>
        implements MineContract.View ,View.OnClickListener{

    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.toolbar_me)
    Toolbar mToolbar;
    @BindView(R.id.text_sign)
    TextView textSign;
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.mFilterContentView)
    SimplePullLayout mFilterContentView;

    ACache mCache;
    MyHouListAdapter mAdatper;
    @BindView(R.id.text_dept_name)
    TextView textDeptName;
    @BindView(R.id.text_full_name)
    TextView textFullName;
    @BindView(R.id.text_group_name)
    TextView textGroupName;

    private int page;
    private UserBean userBean;
    private boolean pullToRefresh = true;


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMineComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mineModule(new MineModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mCache = ACache.get(getContext());
        userBean = (UserBean) mCache.getAsObject(getString(R.string.user_bean));
        initToolBar();
        initUser();
        initRecycleView();

        textSign.setOnClickListener(this);
        ArmsUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(getActivity()));


    }

    private void initToolBar() {

        mToolbar.setNavigationIcon(R.drawable.ico_clean);
        mToolbar.inflateMenu(R.menu.toolbar_menu_me);
        mToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("确认退出登录？")
                        .setNegativeButton("取消", (dialog1, which) -> {

                        })
                        .setPositiveButton("退出登录", (dialog12, which) -> {

                            //mPresenter.logout();
                            ACache aCache = ACache.get(getContext());
                            aCache.clear();
                            getActivity().finish();
                            startActivity(new Intent(getContext(), LoginActivity.class));

                        }).show();


            }
            return true;
        });

        mToolbar.setNavigationOnClickListener(v -> {

            try {
                String cacheSize = getTotalCacheSize(getContext());

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示")
                        .setMessage("缓存大小为" + cacheSize + "，确定要清理缓存么？")
                        .setNegativeButton("取消", (dialog1, which) -> {

                        })
                        .setPositiveButton("确定", (dialog12, which) -> {
                            /*String name = mCache.getAsString("name");
                            String password = mCache.getAsString("password");
                            clearAllCache(getContext());
                            ACache aCache = ACache.get(getContext());
                            aCache.put("name",name);
                            aCache.put("password",password);*/
                            cleanGlideDiskCache();

                        }).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private void initUser() {
        textDeptName.setText(userBean.getDeptName());
        textFullName.setText(userBean.getFullName());
        textGroupName.setText(userBean.getGroupName());
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdatper.setEnableLoadMore(true);
        freshHouseList();

    }

    private void freshHouseList() {

        page = 1;
        getData();
    }

    protected void getData() {
        mPresenter.requestData(userBean.getName(), userBean.getPassword(), page);
    }

    private void initRecycleView() {
        mFilterContentView.setOnPullListener(new BasePullLayout.OnPullCallBackListener() {
            @Override
            public void onRefresh() {
                pullToRefresh = true;
                freshHouseList();
            }

            @Override
            public void onLoad() {
                pullToRefresh = false;
                page++;
                getData();

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdatper = MyHouListAdapter
                .builder()
                .build();

        mRecyclerView.setAdapter(mAdatper);
        mAdatper.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getContext(), DetailHouseActivity.class);
            intent.putExtra("houseId",String.valueOf(((MyHouListAdapter)adapter).getItem(position).getId()));
            startActivity(intent);
        });
        mAdatper.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.btn_updata:
                    Intent intent=new Intent(getContext(), FollowUpActivity.class);
                    intent.putExtra("houseId",String.valueOf(((MyHouListAdapter)adapter).getItem(position).getId()));
                    startActivity(intent);
                    break;
                case R.id.btn_info_state_update:
                    Intent intent1=new Intent(getContext(), StateUpdateActivity.class);
                    intent1.putExtra("houseId",String.valueOf(((MyHouListAdapter)adapter).getItem(position).getId()));
                    startActivity(intent1);
                    break;
                case R.id.btn_modify:
                    Intent intent2 = new Intent(getContext(), UpdateHouseActivity.class);
                    intent2.putExtra("houseId", String.valueOf(((MyHouListAdapter) adapter).getItem(position).getId()));
                    startActivity(intent2);
                    break;
                default:
                    break;

            }

        });

    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }

    @Override
    public void logoutSuccess() {
        ToastUtil.show(getContext(), "您已退出");

    }

    @Override
    public void showHListData(List<HouseListBean> datas) {
        if (pullToRefresh) {
            mAdatper.getData().clear();
        }
        mAdatper.notifyDataSetChanged();

        if (page == 1 && datas.size() == 0) {
            tvNoData.setVisibility(View.VISIBLE);
        }else{
            tvNoData.setVisibility(View.GONE);
        }

        if (datas.size() != 0) {
            mFilterContentView.finishPull("加载成功", true);
            mAdatper.addData(datas);

        } else {
            mAdatper.setEnableLoadMore(false);
            mFilterContentView.finishPull("没有更多数据了", true);
            /// ToastUtil.show(getContext(),"未找到符合条件的结果！");
        }
    }

    @Override
    public void onDestroy() {

        DefaultAdapter.releaseAllHolder(mRecyclerView);//super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_sign:
                startActivity(new Intent(getContext(), SignActivity.class));


                break;
            default:
                break;
        }
    }
}
