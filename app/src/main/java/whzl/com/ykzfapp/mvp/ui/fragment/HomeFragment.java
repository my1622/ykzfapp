package whzl.com.ykzfapp.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.adapter.DropMenuAdapter;
import whzl.com.ykzfapp.adapter.FilterUrl;
import whzl.com.ykzfapp.bean.DictionaryBean;
import whzl.com.ykzfapp.di.component.DaggerHomeComponent;
import whzl.com.ykzfapp.di.module.HomeModule;
import whzl.com.ykzfapp.mvp.contract.HomeContract;
import whzl.com.ykzfapp.mvp.presenter.HomePresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeFragment extends BaseFragment<HomePresenter>
        implements HomeContract.View, OnFilterDoneListener {


    @BindView(R.id.fl_to_search)
    RelativeLayout flToSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.mFilterContentView)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    Unbinder unbinder;


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
        View rootView=inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mPresenter.getDictionary();

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
    }





    @Override
    public void dictionarySuccess(List<DictionaryBean> dictionaryBeans) {
        ArrayList<DictionaryBean> address = new ArrayList<>();
        ArrayList<DictionaryBean> salePrice = new ArrayList<>();
        ArrayList<DictionaryBean> bedRooms = new ArrayList<>();
        ArrayList<DictionaryBean> area = new ArrayList<>();
        for (DictionaryBean b:dictionaryBeans){
            if ("region".equals(b.getLetter())){
                address.add(b);
                continue;
            }else if ("salePrice".equals(b.getLetter())){
                salePrice.add(b);
                continue;
            }else if ("bedRooms".equals(b.getLetter())){
                bedRooms.add(b);
                continue;
            }else if ("area".equals(b.getLetter())){
                area.add(b);
                continue;
            }

        }
        String[] titleList = new String[]{"区域", "价格", "租金", "更多"};
        dropDownMenu.setMenuAdapter(new DropMenuAdapter(getContext(), titleList, this
                , address, salePrice, bedRooms, area));
    }
}
