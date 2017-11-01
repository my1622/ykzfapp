package whzl.com.ykzfapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.bean.HouseListBean;
import whzl.com.ykzfapp.bean.UserBean;
import whzl.com.ykzfapp.di.component.DaggerAddHouseComponent;
import whzl.com.ykzfapp.di.module.AddHouseModule;
import whzl.com.ykzfapp.mvp.contract.AddHouseContract;
import whzl.com.ykzfapp.mvp.presenter.AddHousePresenter;
import whzl.com.ykzfapp.utils.ACache;
import whzl.com.ykzfapp.utils.ToastUtil;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static whzl.com.ykzfapp.R.id.tv_fy_path;


public class AddHouseActivity extends BaseActivity<AddHousePresenter>
        implements AddHouseContract.View, View.OnClickListener {


    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_right)
    TextView tvTitleRight;
    @BindView(R.id.toolbar_me)
    Toolbar mToolBar;
    @BindView(R.id.ed_title)
    EditText edTitle;
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


    ACache mCache;

    private static final int REQUEST_CODE_COMMUNITY = 0;
    private static final int REQUEST_CODE_FYPATH = 1;
    private static final int REQUEST_CODE_FY_OUT_PATH = 2;
    private static final int REQUEST_CODE_HX_PATH = 3;
    private static final int REQUEST_CODE_VIDEO_PATH = 4;
    private static final int REQUEST_CODE_AUDIO_PATH = 5;
    private int communitId;
    private String fyPath;
    private String fyOutPath;
    private String hxPath;
    private String vdoPath;
    private String voicePath;
    private String name, password;
    private UserBean userBean;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAddHouseComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addHouseModule(new AddHouseModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_add_house; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mCache = ACache.get(AddHouseActivity.this);
        userBean = (UserBean) mCache.getAsObject(getString(R.string.user_bean));
        name = userBean.getName();
        password = userBean.getPassword();
        fyPath = "";
        fyOutPath = "";
        hxPath = "";
        vdoPath = "";
        voicePath = "";
        initToolbar();
        initButtons();
        tvCommName.setOnClickListener(this);
    }

    private void initButtons() {
        initButtons(tvFyPath);
        initButtons(tvFyOutPath);
        initButtons(tvHxPath);
        initButtons(tvVoicePath);
        initButtons(tvVideoPath);
    }

    private void initButtons(TextView tv) {
        tv.setOnClickListener(this);
    }

    private void initToolbar() {
        tvToolbarTitle.setText("房源详情");
        tvTitleRight.setText("保存");
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setOnClickListener(v -> {
                    if (TextUtils.isEmpty(edTitle.getText().toString().trim())) {
                        ToastUtil.show(AddHouseActivity.this, "请输入房源标题");
                        return;
                    }
                    if (TextUtils.isEmpty(edUnitNo.getText().toString().trim())
                            || TextUtils.isEmpty(edBuildNo.getText().toString().trim())
                            || TextUtils.isEmpty(edRoomNo.getText().toString().trim())) {
                        ToastUtil.show(AddHouseActivity.this, "请输入门牌号");
                        return;
                    }
                    if (TextUtils.isEmpty(edLivingrooms.getText().toString().trim())
                            || TextUtils.isEmpty(edCookingrooms.getText().toString().trim())
                            || TextUtils.isEmpty(edBathrooms.getText().toString().trim())
                            || TextUtils.isEmpty(edBedrooms.getText().toString().trim())) {
                        ToastUtil.show(AddHouseActivity.this, "请输入户型几室");
                        return;
                    }
                    if (communitId == 0) {
                        ToastUtil.show(AddHouseActivity.this, "请输入小区");
                        return;
                    }
                    tvTitleRight.setEnabled(false);
                    mPresenter.addHouse(name, password,
                            edTitle.getText().toString().trim(),
                            String.valueOf(communitId),
                            edUnitNo.getText().toString().trim(),
                            edBuildNo.getText().toString().trim(),
                            edRoomNo.getText().toString().trim(),
                            edBedrooms.getText().toString().trim(),
                            edLivingrooms.getText().toString().trim(),
                            edCookingrooms.getText().toString().trim(),
                            edBathrooms.getText().toString().trim(),
                            fyPath, fyOutPath, hxPath, vdoPath, voicePath);


                }

        );
        mToolBar.setNavigationIcon(R.mipmap.icon_back);
        mToolBar.setNavigationOnClickListener(v -> killMyself());


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        tvTitleRight.setEnabled(true);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_commName:
                startActivityForResult(new Intent(this, CommunityActivity.class), REQUEST_CODE_COMMUNITY);

                break;

            case R.id.tv_fy_path:
                Intent intent = new Intent(this, PictureActivity.class);
                intent.putExtra("title", "上传室内图片");
                intent.putExtra("type", "图片");
                startActivityForResult(intent, REQUEST_CODE_FYPATH);
                break;
            case R.id.tv_fy_out_path:
                Intent intent1 = new Intent(this, PictureActivity.class);
                intent1.putExtra("title", "上传室外图片");
                intent1.putExtra("type", "图片");

                startActivityForResult(intent1, REQUEST_CODE_FY_OUT_PATH);

                break;
            case R.id.tv_hx_path:
                Intent intent2 = new Intent(this, PictureActivity.class);
                intent2.putExtra("title", "上传室户型图片");
                intent2.putExtra("type", "图片");
                startActivityForResult(intent2, REQUEST_CODE_HX_PATH);

                break;
            case R.id.tv_video_path:
                Intent intent3 = new Intent(this, VideoActivity.class);
                intent3.putExtra("title", "上传现场视频");
                intent3.putExtra("type", "视频");
                startActivityForResult(intent3, REQUEST_CODE_VIDEO_PATH);

                break;
            case R.id.tv_voice_path:
                Intent intent4 = new Intent(this, AudioActivity.class);
                intent4.putExtra("title", "上传现场录音");
                intent4.putExtra("type", "音频");
                startActivityForResult(intent4, REQUEST_CODE_AUDIO_PATH);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_COMMUNITY:
                    communitId = data.getIntExtra("communityId", 0);
                    tvCommName.setText(data.getStringExtra("communityName"));
                    break;
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
                    vdoPath = data.getStringExtra("backData");
                    break;
                case REQUEST_CODE_AUDIO_PATH:
                    voicePath = data.getStringExtra("backData");
                    break;
                default:
                    break;


            }

        }
    }

    @Override
    public void success(HouseListBean houseListBean) {
        ToastUtil.show(this,"增加成功");
        killMyself();

    }
}
