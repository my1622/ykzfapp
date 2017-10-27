package whzl.com.ykzfapp.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.mvp.model.api.Api;
import whzl.com.ykzfapp.mvp.ui.widget.GlideTool;

/**
 * Created by my on 2017/10/27.
 */

public class StaticPicture extends BaseActivity {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar_me)
    Toolbar toolbarMe;
    @BindView(R.id.ll_group)
    LinearLayout llGroup;
    private String path;
    private String[] strPath;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_static_picture;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar();
        path = getIntent().getStringExtra("path");
        strPath = path.split(",");
        initStativPictures();
    }

    private void initStativPictures() {


        ImageView[] imageViews = new ImageView[strPath.length];
        for (int i = 0; i < strPath.length; i++) {
            imageViews[i] = new ImageView(this);
            imageViews[i].setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));  //设置图片宽高

            GlideTool.showImage(Api.APP_DOMAIN+strPath[i], imageViews[i], this);
            llGroup.addView(imageViews[i]); //动态添加图片
        }
    }

    private void initToolbar() {
        tvToolbarTitle.setText("图片详情");
        toolbarMe.setNavigationIcon(R.mipmap.icon_back);
        toolbarMe.setNavigationOnClickListener(v -> {
            finish();
        });
    }


}
