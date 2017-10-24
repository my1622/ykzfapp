package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.di.component.DaggerMainComponent;
import whzl.com.ykzfapp.di.module.MainModule;
import whzl.com.ykzfapp.mvp.contract.MainContract;
import whzl.com.ykzfapp.mvp.presenter.MainPresenter;
import whzl.com.ykzfapp.mvp.ui.fragment.HomeFragment;
import whzl.com.ykzfapp.mvp.ui.fragment.MineFragment;
import whzl.com.ykzfapp.mvp.ui.fragment.TodoHListFragment;
import whzl.com.ykzfapp.mvp.ui.widget.Tab;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.tabhost)
    FragmentTabHost tabhost;
/*    @BindView(R.id.navigation_view)
    NavigationView navigationView;
   @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;*/


    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>();
    private long mLastClickTime;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initTab();

    }

    private void initTab() {
        mInflater = LayoutInflater.from(this);
        Tab tab_home = new Tab(R.string.house_list, R.drawable.selector_icon_home, HomeFragment.class);
        Tab tab_me = new Tab(R.string.todo_house_list, R.drawable.selector_todo_hlist, TodoHListFragment.class);
        Tab tab_more = new Tab(R.string.mine, R.drawable.selector_icon_mine, MineFragment.class);


        mTabs.add(tab_home);
        mTabs.add(tab_me);
        mTabs.add(tab_more);


        mTabhost = (FragmentTabHost) findViewById(R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab t : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(t.getTitle()));
            tabSpec.setIndicator(buildIndicator(t));
            mTabhost.addTab(tabSpec, t.getFragment(), null);
        }

        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
        mTabhost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.colorbackground));
    }
    private View buildIndicator(Tab tab) {

        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView textView = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        textView.setText(tab.getTitle());
        return view;
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
