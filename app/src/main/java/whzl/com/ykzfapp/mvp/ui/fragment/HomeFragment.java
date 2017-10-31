package whzl.com.ykzfapp.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xiaosu.pulllayout.SimplePullLayout;
import com.xiaosu.pulllayout.base.BasePullLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.adapter.DropMenuAdapter;
import whzl.com.ykzfapp.adapter.FilterUrl;
import whzl.com.ykzfapp.bean.DictionaryBean;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.bean.UserBean;
import whzl.com.ykzfapp.commom.Constant;
import whzl.com.ykzfapp.di.component.DaggerHomeComponent;
import whzl.com.ykzfapp.di.module.HomeModule;
import whzl.com.ykzfapp.mvp.contract.HomeContract;
import whzl.com.ykzfapp.mvp.presenter.HomePresenter;
import whzl.com.ykzfapp.mvp.ui.activity.FollowUpActivity;
import whzl.com.ykzfapp.mvp.ui.activity.HouseDetailActivity;
import whzl.com.ykzfapp.mvp.ui.activity.StateUpdateActivity;
import whzl.com.ykzfapp.mvp.ui.adapter.HomeListAdapter;
import whzl.com.ykzfapp.utils.ACache;
import whzl.com.ykzfapp.utils.ToastUtil;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeFragment extends BaseFragment<HomePresenter>
        implements HomeContract.View, OnFilterDoneListener {

    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.simple_pullLayout)
    SimplePullLayout mSimplePullLayout;


    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    ACache mCache;
    HomeListAdapter mAdatper;
    private int page;
    private UserBean userBean;
    private boolean pullToRefresh = true;
    String title, region, salePrice, bedRooms,area;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getDictionary();
        mCache = ACache.get(getContext());
        userBean = (UserBean) mCache.getAsObject(getString(R.string.user_bean));
        editText.setOnKeyListener((v, keyCode, event) -> {
            //修改回车键功能
            if(keyCode== KeyEvent.KEYCODE_ENTER) {
                ((InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                String searchContext = editText.getText().toString().trim();

                if (TextUtils.isEmpty(searchContext)) {

                    ToastUtil.show(getContext(), "输入内容为空");

                } else {
                    title=searchContext;
                    freshHouseList();
                }
                return true;
            }
            return false;
        });

        initRecycleView();
        ArmsUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(getActivity()));

    }
    private void freshHouseList() {
        pullToRefresh=true;
        page = 1;
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdatper.setEnableLoadMore(true);
        freshHouseList();

    }

    protected void getData() {
        mPresenter.requestData(title, region, salePrice, bedRooms,area,page);
    }
    private void initRecycleView() {
        mSimplePullLayout.setOnPullListener(new BasePullLayout.OnPullCallBackListener() {
            @Override
            public void onRefresh() {

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
        mAdatper = HomeListAdapter
                .builder()
                .build();

        mRecyclerView.setAdapter(mAdatper);
        mAdatper.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getContext(), HouseDetailActivity.class);
            intent.putExtra("houseId",String.valueOf(((HomeListAdapter)adapter).getItem(position).getId()));
            startActivity(intent);
        });
        mAdatper.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.btn_updata:
                    Intent intent=new Intent(getContext(), FollowUpActivity.class);
                    intent.putExtra("houseId",String.valueOf(((HomeListAdapter)adapter).getItem(position).getId()));
                    startActivity(intent);
                    break;
                case R.id.btn_info_state_update:
                    Intent intent1=new Intent(getContext(), StateUpdateActivity.class);
                    intent1.putExtra("houseId",String.valueOf(((HomeListAdapter)adapter).getItem(position).getId()));
                    startActivity(intent1);
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
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        dropDownMenu.setPositionIndicatorText(FilterUrl.instance().position, FilterUrl.instance().positionTitle);
        dropDownMenu.close();
        if (FilterUrl.instance().position == 0) {
            region=FilterUrl.instance().positionId;

            freshHouseList();
            mAdatper.notifyDataSetChanged();
        }
        if (FilterUrl.instance().position == 1) {
            salePrice=FilterUrl.instance().positionId;

            freshHouseList();
            mAdatper.notifyDataSetChanged();
        }
        if (FilterUrl.instance().position == 2) {
            bedRooms= FilterUrl.instance().positionId;

            freshHouseList();
            mAdatper.notifyDataSetChanged();
        }
        if (FilterUrl.instance().position == 3) {
            area= FilterUrl.instance().positionId;

            freshHouseList();
            mAdatper.notifyDataSetChanged();
        }
    }


    @Override
    public void dictionarySuccess(List<DictionaryBean> dictionaryBeans) {
        Constant.DICTIONARY=dictionaryBeans;
        ArrayList<DictionaryBean> address = new ArrayList<>();
        ArrayList<DictionaryBean> salePrice = new ArrayList<>();
        ArrayList<DictionaryBean> bedRooms = new ArrayList<>();
        ArrayList<DictionaryBean> area = new ArrayList<>();
        address.add(new DictionaryBean("","不限"));
        salePrice.add(new DictionaryBean("","不限"));
        bedRooms.add(new DictionaryBean("","不限"));
        area.add(new DictionaryBean("","不限"));

        for (DictionaryBean b : dictionaryBeans) {
            if ("region".equals(b.getLetter())) {
                address.add(b);
                continue;
            } else if ("salePrice".equals(b.getLetter())) {
                salePrice.add(b);
                continue;
            } else if ("bedRooms".equals(b.getLetter())) {
                bedRooms.add(b);
                continue;
            } else if ("area".equals(b.getLetter())) {
                area.add(b);
                continue;
            }

        }
        String[] titleList = new String[]{"区域", "价格", "房型", "更多"};

        dropDownMenu.setMenuAdapter(new DropMenuAdapter(getContext(), titleList, this
                , address, salePrice, bedRooms, area));

    }

    @Override
    public void showHListData(List<HouseListBean> datas) {
        if (pullToRefresh) {
            mAdatper.getData().clear();
        }
        mAdatper.notifyDataSetChanged();

        if (page==1 && datas.size() == 0) {
            tvNoData.setVisibility(View.VISIBLE);
        }else{
            tvNoData.setVisibility(View.GONE);
        }
        if (datas.size() != 0) {
            mSimplePullLayout.finishPull("加载成功", true);
            mAdatper.addData(datas);

        } else {
            mAdatper.setEnableLoadMore(false);
            mSimplePullLayout.finishPull("没有更多数据了", true);
            /// ToastUtil.show(getContext(),"未找到符合条件的结果！");
        }
    }


}
