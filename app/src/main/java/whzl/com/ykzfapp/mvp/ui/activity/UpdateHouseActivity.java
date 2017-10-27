package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseDetailBean;
import whzl.com.ykzfapp.di.component.DaggerUpdateHouseComponent;
import whzl.com.ykzfapp.di.module.UpdateHouseModule;
import whzl.com.ykzfapp.mvp.contract.UpdateHouseContract;
import whzl.com.ykzfapp.mvp.presenter.UpdateHousePresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static whzl.com.ykzfapp.R.id.tv_fy_path;


public class UpdateHouseActivity extends BaseActivity<UpdateHousePresenter>
        implements UpdateHouseContract.View ,View.OnClickListener{



    @BindView(R.id.tv_commName)
    TextView tvCommName;
    @BindView(R.id.ed_build_no)
    EditText edBuildNo;
    @BindView(R.id.ed_unit_no)
    EditText edUnitNo;
    @BindView(R.id.ed_room_no)
    EditText edRoomNo;
    @BindView(R.id.ed_bedrooms)
    EditText edBedrooms;
    @BindView(R.id.ed_livingrooms)
    EditText edLivingrooms;
    @BindView(R.id.ed_cookingrooms)
    EditText edCookingrooms;
    @BindView(R.id.ed_bathrooms)
    EditText edBathrooms;
    @BindView(R.id.tv_fy_out_path)
    TextView tvFyOutPath;
    @BindView(tv_fy_path)
    TextView tvFyPath;
    @BindView(R.id.tv_hx_path)
    TextView tvHxPath;
    @BindView(R.id.tv_video_path)
    TextView tvVideoPath;
    @BindView(R.id.tv_voice_path)
    TextView tvVoicePath;
    private String houseId;

    @BindView(R.id.toolbar_me)
    Toolbar mToolBar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_right)
    TextView tvTitleRight;

    @BindView(R.id.ed_title)
    EditText edTitle;


    private static final int REQUEST_CODE_FYPATH=1;
    private static final int REQUEST_CODE_FY_OUT_PATH=2;
    private static final int REQUEST_CODE_HX_PATH=3;
    private static final int REQUEST_CODE_VIDEO_PATH = 4;
    private static final int REQUEST_CODE_AUDIO_PATH = 5;
    private String fyPath;
    private String fyOutPath;
    private String hxPath;
    private String videoPath;
    private String audiofyPath;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerUpdateHouseComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .updateHouseModule(new UpdateHouseModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_update_house; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        houseId = getIntent().getStringExtra("houseId");
        mPresenter.requestData(houseId);
        initToolbar();
    }

    private void initToolbar() {
        tvToolbarTitle.setText("房源详情");
        tvTitleRight.setText("保存");
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setOnClickListener(v ->{
            //mPresenter.save()
                }

        );
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
        edTitle.setText(houseDetail.getTitle() + "");
        tvCommName.setText(houseDetail.getCommunityName());

        edUnitNo.setText(houseDetail.getUnitNo() + "");
        edBuildNo.setText(houseDetail.getBuildNo() + "");
        edRoomNo.setText(houseDetail.getRoomNo() + "");
        edBedrooms.setText(houseDetail.getBedRooms() + "");
        edLivingrooms.setText(houseDetail.getLivingRooms() + "");
        edCookingrooms.setText(houseDetail.getCookRooms() + "");
        edBathrooms.setText(houseDetail.getBathRooms() + "");
        tvCommName.setOnClickListener(this);

        initButtons(tvFyPath,houseDetail.getFyPath());
        initButtons(tvFyOutPath,houseDetail.getFyOutPath());
        initButtons(tvHxPath,houseDetail.getHxPath());
        initButtons(tvVoicePath,houseDetail.getVoicePath());
        initButtons(tvVideoPath,houseDetail.getVideoPath());
        
        
    }

    private void initButtons(TextView tv, String str) {
        if ("".equals(str)){
            tv.setText("点击上传");
        }else{
            tv.setText("点击修改");

        }
        tv .setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_commName:
                startActivity(new Intent(this, CommunityActivity.class));

                break;

            case tv_fy_path:
                Intent intent = new Intent(this, PictureActivity.class);
                intent.putExtra("title", "上传室内图片");
                intent.putExtra("type", "图片");
                startActivityForResult(intent,REQUEST_CODE_FYPATH);
                break;
            case R.id.tv_fy_out_path:
                Intent intent1 = new Intent(this, PictureActivity.class);
                intent1.putExtra("title", "上传室外图片");
                intent1.putExtra("type", "图片");

                startActivityForResult(intent1,REQUEST_CODE_FY_OUT_PATH);

                break;
            case R.id.tv_hx_path:
                Intent intent2 = new Intent(this, PictureActivity.class);
                intent2.putExtra("title", "上传室户型图片");
                intent2.putExtra("type", "图片");
                startActivityForResult(intent2,REQUEST_CODE_HX_PATH);

                break;
            case R.id.tv_video_path:
                Intent intent3 = new Intent(this, VideoActivity.class);
                intent3.putExtra("title", "上传现场视频");
                intent3.putExtra("type", "视频");
                startActivityForResult(intent3,REQUEST_CODE_VIDEO_PATH);

                break;
            case R.id.tv_voice_path:
                Intent intent4 = new Intent(this, AudioActivity.class);
                intent4.putExtra("title", "上传现场录音");
                intent4.putExtra("type", "音频");
                startActivityForResult(intent4,REQUEST_CODE_AUDIO_PATH);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_FYPATH:
                    fyPath = data.getStringExtra("backData");
                    break;
                case REQUEST_CODE_FY_OUT_PATH:
                    fyOutPath = data.getStringExtra("backData");
                    break;
                case REQUEST_CODE_HX_PATH:
                    hxPath = data.getStringExtra("backData");
                    break;
                case REQUEST_CODE_VIDEO_PATH:
                    videoPath = data.getStringExtra("backData");
                    break;
                case REQUEST_CODE_AUDIO_PATH:
                    audiofyPath = data.getStringExtra("backData");
                    break;
                default:
                    break;


            }

        }
    }
}
