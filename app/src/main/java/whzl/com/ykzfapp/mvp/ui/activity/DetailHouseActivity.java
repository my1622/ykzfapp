package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseDetailBean;
import whzl.com.ykzfapp.di.component.DaggerDetailHouseComponent;
import whzl.com.ykzfapp.di.module.DetailHouseModule;
import whzl.com.ykzfapp.mvp.contract.DetailHouseContract;
import whzl.com.ykzfapp.mvp.presenter.DetailHousePresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class DetailHouseActivity extends BaseActivity<DetailHousePresenter>
        implements DetailHouseContract.View,View.OnClickListener {

    @BindView(R.id.tv_communityName)
    TextView tvCommunityName;
    @BindView(R.id.tv_build_no)
    TextView tvBuildNo;
    @BindView(R.id.tv_unit_no)
    TextView tvUnitNo;
    @BindView(R.id.tv_room_no)
    TextView tvRoomNo;
    @BindView(R.id.tv_bedrooms)
    TextView tvBedrooms;
    @BindView(R.id.tv_livingrooms)
    TextView tvLivingrooms;
    @BindView(R.id.tv_cook_rooms)
    TextView tvCookRooms;
    @BindView(R.id.tv_bathrooms)
    TextView tvBathrooms;
    @BindView(R.id.tv_fy_out_path)
    TextView tvFyOutPath;
    @BindView(R.id.tv_fy_path)
    TextView tvFyPath;
    @BindView(R.id.tv_hx_path)
    TextView tvHxPath;
    @BindView(R.id.tv_video_path)
    TextView tvVideoPath;
    @BindView(R.id.tv_voice_path)
    TextView tvVoicePath;
    private String houseId;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar_me)
    Toolbar mToolBar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    private HouseDetailBean mHouseDetail;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerDetailHouseComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .detailHouseModule(new DetailHouseModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_detail_house; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        houseId = getIntent().getStringExtra("houseId");
        mPresenter.requestData(houseId);
        initToolbar();

    }

    private void initToolbar() {
        tvToolbarTitle.setText("房源详情");
        mToolBar.setNavigationIcon(R.mipmap.icon_back);
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
    public void success(HouseDetailBean houseDetail) {
        initText(houseDetail);
    }

    private void initText(HouseDetailBean houseDetail) {
        mHouseDetail=houseDetail;
        tvTitle.setText(houseDetail.getTitle() + "");
        tvCommunityName.setText(houseDetail.getCommunityName() + "");
        tvUnitNo.setText(houseDetail.getUnitNo() + "");
        tvBuildNo.setText(houseDetail.getBuildNo() + "");
        tvRoomNo.setText(houseDetail.getRoomNo() + "");
        tvBedrooms.setText(houseDetail.getBedRooms() + "");
        tvLivingrooms.setText(houseDetail.getLivingRooms() + "");
        tvCookRooms.setText(houseDetail.getCookRooms() + "");
        tvBathrooms.setText(houseDetail.getBathRooms() + "");

        initButtons(tvFyPath,houseDetail.getFyPath());
        initButtons(tvFyOutPath,houseDetail.getFyOutPath());
        initButtons(tvHxPath,houseDetail.getHxPath());
        initButtons(tvVoicePath,houseDetail.getVoicePath());
        initButtons(tvVideoPath,houseDetail.getVideoPath());

    }

    private void initButtons(TextView tv, String str) {
        if ("".equals(str)){
            tv.setText("暂未上传");
        }else{
            tv .setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_fy_path:
                Intent intent = new Intent(this, StaticPicture.class);
                intent.putExtra("path", mHouseDetail.getFyPath());
                startActivity(intent);
                break;
            case R.id.tv_fy_out_path:
                Intent intent1 = new Intent(this, StaticPicture.class);
                intent1.putExtra("path", mHouseDetail.getFyOutPath());
                startActivity(intent1);
                break;
            case R.id.tv_hx_path:
                Intent intent2 = new Intent(this, StaticPicture.class);
                intent2.putExtra("path", mHouseDetail.getHxPath());
                startActivity(intent2);
                break;
            case R.id.tv_video_path:

                break;
            case R.id.tv_voice_path:

                break;
        }
    }
}
