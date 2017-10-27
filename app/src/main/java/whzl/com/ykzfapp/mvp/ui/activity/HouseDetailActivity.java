package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseDetailBean;
import whzl.com.ykzfapp.di.component.DaggerHouseDetailComponent;
import whzl.com.ykzfapp.di.module.HouseDetailModule;
import whzl.com.ykzfapp.mvp.contract.HouseDetailContract;
import whzl.com.ykzfapp.mvp.model.api.Api;
import whzl.com.ykzfapp.mvp.presenter.HouseDetailPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static whzl.com.ykzfapp.commom.Constant.getFitmentType;
import static whzl.com.ykzfapp.commom.Constant.getOrientatioan;
import static whzl.com.ykzfapp.commom.Constant.getRegion;


public class HouseDetailActivity extends BaseActivity<HouseDetailPresenter> implements HouseDetailContract.View {




    @BindView(R.id.toolbar_me)
    Toolbar mToolBar;
    @BindView(R.id.slider)
    SliderLayout mSliderLayout;
    @BindView(R.id.custom_indicator)
    PagerIndicator mPagerIndicator;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.tv_title)
    TextView tvTitle;
 
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_fwlx)
    TextView tvFwlx;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_serviceCharge)
    TextView tvServiceCharge;
    @BindView(R.id.tv_agencyFee)
    TextView tvAgencyFee;
    @BindView(R.id.tv_tag1)
    TextView tvTag1;
    @BindView(R.id.tv_tag2)
    TextView tvTag2;
    @BindView(R.id.tv_tag3)
    TextView tvTag3;
    @BindView(R.id.tv_tag4)
    TextView tvTag4;
    @BindView(R.id.tv_communityName)
    TextView tvCommunityName;
    @BindView(R.id.tv_unitPrice)
    TextView tvUnitPrice;
    @BindView(R.id.tv_orientation)
    TextView tvOrientation;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_located_floor_total_floors)
    TextView tvLocatedFloorTotalFloors;
    @BindView(R.id.tv_fitmentType)
    TextView tvFitmentType;
    @BindView(R.id.tv_createdOn)
    TextView tvCreatedOn;
    @BindView(R.id.tv_viewTimes)
    TextView tvViewTimes;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_checkFullName)
    TextView tvCheckFullName;
    @BindView(R.id.tv_checkTel)
    TextView tvCheckTel;

    private String houseId;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerHouseDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .houseDetailModule(new HouseDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_house_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        houseId = getIntent().getStringExtra("houseId");
        mPresenter.requestData(houseId);
        initToolbar();

    }

    private void initToolbar() {
        tvToolbarTitle.setText("详情");
        mToolBar.setNavigationIcon(R.mipmap.icon_back);
        mToolBar.setNavigationOnClickListener((View.OnClickListener) v -> finish());

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
        initSlider(houseDetail.getFyPath());
        initText(houseDetail);

    }

    private void initText(HouseDetailBean houseDetail) {
        tvTitle.setText(houseDetail.getTitle()+"");
         tvAddress.setText(houseDetail.getCommunityAddress()+"");

         tvFwlx.setText(houseDetail.getBedRooms() + "室"
                 + houseDetail.getLivingRooms() + "厅"
                 + houseDetail.getCookRooms() + "厨"
                 + houseDetail.getBathRooms() + "卫"
                 + " | " + houseDetail.getArea() + "m²");
         tvPrice.setText(String.valueOf(houseDetail.getSalePrice())+"万");
         tvServiceCharge.setText(" ￥"+houseDetail.getServiceCharge()+"元");
         tvAgencyFee.setText(" ￥"+houseDetail.getAgencyFee()+"元");
        List<String> tags = houseDetail.getTagsArray();
        switch (tags.size()){
            case 1:
                tvTag1.setText(tags.get(0));
                tvTag2.setVisibility(View.GONE);
                tvTag3.setVisibility(View.GONE);
                tvTag4.setVisibility(View.GONE);
                break;
            case 2:
                tvTag1.setText(tags.get(0));
                tvTag2.setText(tags.get(1));
                tvTag3.setVisibility(View.GONE);
                tvTag4.setVisibility(View.GONE);
                break;

            case 3:
                tvTag1.setText(tags.get(0));
                tvTag2.setText(tags.get(1));
                tvTag3.setText(tags.get(2));
                tvTag4.setVisibility(View.GONE);
                break;
            case 4:
                tvTag1.setText(tags.get(0));
                tvTag2.setText(tags.get(1));
                tvTag3.setText(tags.get(2));
                tvTag4.setText(tags.get(3));
                break;
        }


         tvCommunityName.setText(houseDetail.getCommunityName()+"");
         tvUnitPrice.setText(String.valueOf(houseDetail.getUnitPrice())+"元/平米");
         tvOrientation.setText(getOrientatioan(houseDetail.getOrientation())+"");
         tvRegion.setText(getRegion(houseDetail.getRegion()+""));
         tvLocatedFloorTotalFloors.setText(houseDetail.getLocatedFloor()+"/"+houseDetail.getTotalFloors()+"层"+"");
         tvFitmentType.setText(getFitmentType(houseDetail.getFitmentType()+""));
        String[] creatOn = houseDetail.getCreatedOn().split(" ");
         tvCreatedOn.setText(creatOn[0]+"发布");
         tvViewTimes.setText(houseDetail.getViewTimes()+"");
         tvContact.setText(houseDetail.getContact()+"");
         tvPhone.setText(houseDetail.getPhone()+"");
         tvCheckFullName.setText(houseDetail.getCheckFullName()+"");
         tvCheckTel.setText(houseDetail.getCheckTel()+"");
    }



    private void initSlider(String fyPath) {
        String[] strings = fyPath.split(",");

        DefaultSliderView[] DefaultSliderViews = new DefaultSliderView[strings.length];

        for (int i = 0; i < strings.length; i++) {
            DefaultSliderViews[i] = new DefaultSliderView(this);
            DefaultSliderViews[i]

                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .image(Api.APP_DOMAIN + strings[i]);
            mSliderLayout.addSlider(DefaultSliderViews[i]);
        }
        mSliderLayout.setCustomIndicator(mPagerIndicator);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        mSliderLayout.setDuration(3000);
    }

    @Override
    protected void onStop() {
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }


}
