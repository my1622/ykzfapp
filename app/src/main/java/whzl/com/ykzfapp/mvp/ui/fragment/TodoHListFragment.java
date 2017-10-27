package whzl.com.ykzfapp.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import whzl.com.ykzfapp.di.component.DaggerTodoHListComponent;
import whzl.com.ykzfapp.di.module.TodoHListModule;
import whzl.com.ykzfapp.mvp.contract.TodoHListContract;
import whzl.com.ykzfapp.mvp.presenter.TodoHListPresenter;
import whzl.com.ykzfapp.mvp.ui.activity.DetailHouseActivity;
import whzl.com.ykzfapp.mvp.ui.activity.UpdateHouseActivity;
import whzl.com.ykzfapp.mvp.ui.adapter.HouseListAdapter;
import whzl.com.ykzfapp.utils.ACache;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class TodoHListFragment extends BaseFragment<TodoHListPresenter>
        implements TodoHListContract.View {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.mFilterContentView)
    SimplePullLayout mFilterContentView;

    ACache mCache;
    HouseListAdapter mAdatper;
    private int page;
    private UserBean userBean;
    private boolean pullToRefresh=true;

    public static TodoHListFragment newInstance() {
        TodoHListFragment fragment = new TodoHListFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerTodoHListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .todoHListModule(new TodoHListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_todo_hlist, container, false);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mCache = ACache.get(getContext());
        userBean= (UserBean) mCache.getAsObject(getString(R.string.user_bean));
        initRecycleView();
        ArmsUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(getActivity()));

        //mPresenter.requestData(userBean.getName(), userBean.getPassword(),true);

    }

    @Override
    public void setData(Object data) {

    }


    protected void getData() {
        mPresenter.requestData(userBean.getName(), userBean.getPassword(),page);
    }

    private void freshHouseList() {

        page = 1;
        getData();
    }

    private void initRecycleView() {
        mFilterContentView.setOnPullListener(new BasePullLayout.OnPullCallBackListener() {
            @Override
            public void onRefresh() {
                pullToRefresh=true;
                freshHouseList();
            }

            @Override
            public void onLoad() {
                pullToRefresh=false;
                    page++;
                    getData();

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdatper = HouseListAdapter

                .builder()
                .setContext(getContext())
                .build();

        mRecyclerView.setAdapter(mAdatper);
        mAdatper.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getContext(), DetailHouseActivity.class);
            intent.putExtra("houseId",String.valueOf(((HouseListAdapter)adapter).getItem(position).getId()));
            startActivity(intent);

        });
        mAdatper.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.btn_modify:
                    Intent intent = new Intent(getContext(), UpdateHouseActivity.class);
                    intent.putExtra("houseId", String.valueOf(((HouseListAdapter) adapter).getItem(position).getId()));
                    startActivity(intent);
                    break;
            }
        });

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
    public void showHListData(List<HouseListBean> datas) {
        if (pullToRefresh){
            mAdatper.getData().clear();
        }
        mAdatper.notifyDataSetChanged();

        if (page==1&&datas.size()==0){
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
    public void onResume() {
        super.onResume();
        mAdatper.setEnableLoadMore(true);
        freshHouseList();

    }
    @Override
    public void onDestroy() {

        DefaultAdapter.releaseAllHolder(mRecyclerView);//super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
    }
}
