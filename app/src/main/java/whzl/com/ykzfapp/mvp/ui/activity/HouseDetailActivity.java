package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseDetailBean;
import whzl.com.ykzfapp.di.component.DaggerHouseDetailComponent;
import whzl.com.ykzfapp.di.module.HouseDetailModule;
import whzl.com.ykzfapp.mvp.contract.HouseDetailContract;
import whzl.com.ykzfapp.mvp.model.api.Api;
import whzl.com.ykzfapp.mvp.presenter.HouseDetailPresenter;
import whzl.com.ykzfapp.mvp.ui.widget.GlideTool;

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

    @BindView(R.id.iv_blueprint)
    ImageView ivBluePrint;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.iv_voice)
    ImageView ivVoice;
 
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


    MapView mMapView=null;

    private BaiduMap mBaiduMap;
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
        mMapView= (MapView) findViewById(R.id.bmapView);
        houseId = getIntent().getStringExtra("houseId");
        mPresenter.requestData(houseId);
        initToolbar();

    }

    private void initToolbar() {
        tvToolbarTitle.setText("详情");
        mToolBar.setNavigationIcon(R.mipmap.icon_back);
        mToolBar.setNavigationOnClickListener(v -> finish());

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
        initBulePrint(houseDetail);
        initVideo(houseDetail);
        initAudio(houseDetail);
        initBaiduMap(houseDetail);

    }

    private void initBaiduMap(HouseDetailBean houseDetail) {

        mBaiduMap=mMapView.getMap();
        LatLng point = new LatLng(Double.valueOf(houseDetail.getLat()),
                Double.valueOf(houseDetail.getLng()));
        MapStatus msu =  new MapStatus.Builder()
                .target(point)
                .zoom(17)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(msu);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);


        //定义Maker坐标点


//构建Marker图标

        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_gcoding);

//构建MarkerOption，用于在地图上添加Marker

        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);

//在地图上添加Marker，并显示

        mBaiduMap.addOverlay(option);
    }

    private void initAudio(HouseDetailBean houseDetail) {
        String[] url = houseDetail.getVoicePath().split(",");
        ivVoice.setOnClickListener(v -> {
            MediaPlayer player  =   new MediaPlayer();
                try {
                    player.setDataSource(Api.APP_DOMAIN+url[0]);
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        });

    }

    private void initVideo(HouseDetailBean houseDetail) {
        JCVideoPlayerStandard player = (JCVideoPlayerStandard) findViewById(R.id.player_video);
        String[] url = houseDetail.getVideoPath().split(",");
        boolean setUp = player.setUp(Api.APP_DOMAIN+url[0], JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
       if (setUp) {
            Glide.with(HouseDetailActivity.this).load(R.drawable.banner).into(player.thumbImageView);
        }


    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    private void initBulePrint(HouseDetailBean houseDetail) {
        String[] url = houseDetail.getFyOutPath().split(",");
        GlideTool.showImage(Api.APP_DOMAIN+url[0],ivBluePrint,this);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }


}
